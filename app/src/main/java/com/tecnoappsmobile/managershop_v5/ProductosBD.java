package com.tecnoappsmobile.managershop_v5;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by DANIEL on 24/10/2016.
 */

public class ProductosBD extends SQLiteOpenHelper implements Productos,Categorias {
    ArrayList<Producto> productos = new ArrayList<Producto>();

    private String[] categoriaListIn;

    private ArrayList<String> categoriasList;
    private static ProductosBD instance;

    private String nombre;
    private SQLiteDatabase.CursorFactory factory;
    int version;


    // Creamos el constructor ProductosBD
    public ProductosBD(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version){
        super(context,nombre,factory,version);
        this.nombre=nombre;
        this.factory=factory;
        this.version=version;
        categoriaListIn = context.getResources().getStringArray(R.array.spCategoria);
    }


    // Para tener sólo una instancia a la base de datos.
    // Fuente: http://stackoverflow.com/questions/23387405/android-database-cannot-perform-this-operation-because-the-connection-pool-has
    public static synchronized ProductosBD getInstance(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version)
    {
        if (instance == null)
            instance = new ProductosBD(context,nombre,factory,version);

        return instance;
    }


    // Sentencia para crear la tabla de los productos
    @Override
    public void onCreate(SQLiteDatabase db){

        // Creamos la tabla de categroia de los productos
        db.execSQL("CREATE TABLE categorias ("+"idCategoria INTEGER PRIMARY KEY AUTOINCREMENT, nomcategoria TEXT NOT NULL)");

        // Creamos la tabla de productos
        db.execSQL("CREATE TABLE productos ("+"_id INTEGER PRIMARY KEY AUTOINCREMENT,"+"codigoBarras TEXT,codigoInterno TEXT"+
                ",nomProducto TEXT, categoria TEXT,descripcion TEXT,unidades INTEGER,prec_venta REAL,prec_mayorista REAL,tipoDescuento TEXT"+
                ",numdescuento INTEGER, flagdescuento INTEGER,localizacion TEXT, foto TEXT, FOREIGN KEY (categoria) REFERENCES categoria(nomcategoria))");


       // Toast.makeText(get,"Se ha crado la tabla",Toast.LENGTH_LONG).show();
        Log.d("Dentro de ProductosDB","Se ha creado la tabla");

        db.execSQL("INSERT INTO categorias VALUES (null,'"+categoriaListIn[0]+"')");
        db.execSQL("INSERT INTO categorias VALUES (null,'"+categoriaListIn[1]+"')");

        db.execSQL("INSERT INTO productos VALUES (null,"+"'8788426722560','',"+"'El gran libro de Android- 5ºEdicion'"
                +",'Libro'"+",'Libro para aprender a programar en android'"+", 20, 29.6, 25"+
                ", 'Otro'"+", 0, 1,"+"'Localizado en el almacen 1 estantería nº2','')"); // Localizado en el almacen 1 estantería nº2
        db.execSQL("INSERT INTO productos VALUES (null,"+"'9788441536258','',"+"'Java 8-5ºEdición'"
                +",'Libro'"+",'Libro para aprender a programar Java'"+",10,69.3,58"+
                ", 'Otro'"+", 0, 1,"+"'Localizado en el almacen 1 estantería nº6','')"); // Localizado en el almacen 1 estantería n:6
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int OldVersion, int newVersion){
        // En caso de una nueva versión habría que actualizar las tablas
    }

    // Método para guardar productos en la base de datos
    public void guardarProducto(String codigoBarras, String codigoInterno, String nomProducto, String categoria, String descripcion
            , int unidades, double prec_venta, double prec_mayorista, String tipoDescuento, int numdescuento, boolean flagdescuento
            , String localizacion, String foto){
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("INSERT INTO produtos (codigoBarras,codigoInterno,nomProducto,categoria,descripcion,unidades,"+
                " prec_venta,prec_mayorista,tipoDescuento,numdescuento,flagdescuento,localizacion,foto) VALUES (null, '"
                +codigoBarras+"', '"+codigoInterno+"', '"+nomProducto+"','"+categoria+"','"
                +descripcion+"',"+unidades+","+prec_venta+","+prec_mayorista+",'"+tipoDescuento+"',"+numdescuento+","+flagdescuento
                +",'"+localizacion+"','"+foto+"')");
       // db.close();
    }

    public ArrayList<Producto> listarProductos() {
      //  ArrayList<Producto> productos = new ArrayList<Producto>();
        Boolean flagDescuento;


        SQLiteDatabase db=getReadableDatabase();
        Cursor cursor=db.rawQuery("SELECT codigoBarras,codigoInterno,nomProducto,categoria,descripcion,unidades," +
                ",prec_venta,prec_mayorista,tipoDescuento,numdescuento,flagdescuento,localizacion,foto FROM produtos ORDER BY categoria",null);
        while (cursor.moveToNext()){
           /* if (cursor.getInt(10)==0){
                flagDescuento=false;
            } else {
                flagDescuento=true;
            }*/
            productos.add(new Producto(cursor.getString(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)
                    ,cursor.getInt(6),cursor.getDouble(7),cursor.getDouble(8),cursor.getString(9),cursor.getInt(10)
                    ,cursor.getInt(11),cursor.getString(12),cursor.getString(13)));
        }
        cursor.close();
       // db.close();
        return productos;
    }

    @Override
    public Producto elemento(int id) {
        Producto producto=null;
        SQLiteDatabase bd=getReadableDatabase();
        Cursor cursor=bd.rawQuery("SELECT * FROM productos where _id="+ id,null);
        Log.d("Consulta:","SELECT * FROM productos where _id="+ String.valueOf(id));
        if( cursor.moveToNext()){
            producto=extraeProducto(cursor);
        }
        cursor.close();
      //  bd.close();    // HE QUITADO
        return producto;
    }

    @Override
    public void addproducto(Producto producto) {
        productos.add(producto);
    }



    // Para un nuevo producto
    @Override
    public int nuevo(Producto producto) {
        int _id=-1;
        SQLiteDatabase bd=getWritableDatabase();
       //  bd.execSQL("PRAGMA foreign_keys = ON");
        bd.execSQL("INSERT INTO productos (codigoBarras,codigoInterno,nomProducto,categoria,descripcion,unidades," +
                " prec_venta,prec_mayorista,tipoDescuento,numdescuento,flagdescuento,localizacion,foto) VALUES ('"+producto.getCodigoBarras()+"'," +
                "'"+producto.getCodigoInterno()+"', '"+producto.getNomProducto()+"','"+producto.getCategoria()+"','"
                +producto.getDescripcion()+"',"+producto.getUnidades()+","+producto.getPrec_venta()+","+
                producto.getPrec_mayorista()+",'"+producto.getTipoDescuento()+"',"+producto.getNumdescuento()+","
                +producto.isFlagdescuento() + ",'"+producto.getLocalizacion()+"','"+producto.getFotoProducto()+"')");

        Cursor c=bd.rawQuery("SELECT _id FROM productos WHERE _id=(SELECT MAX(_id) FROM productos)",null);
        if (c.moveToFirst()){
            _id=c.getInt(0);
        }
        c.close();
        bd.close();
        productos.add(producto);
        // productos.size()-1
        return _id;
    }



    @Override
    public void borrar(int d) {
        SQLiteDatabase bd=getWritableDatabase();
        bd.execSQL("DELETE FROM productos where _id="+d);
       // productos.remove(d-1);
    }

    @Override
    public int tamInventario() {
        return productos.size();
    }

    @Override
    public void actualizarProducto(int id, Producto producto) {

        // productos.set(id,producto);
        SQLiteDatabase bd=getWritableDatabase();
        bd.execSQL("UPDATE productos SET codigoBarras='"+producto.getCodigoBarras()+
                "', codigoInterno='"+producto.getCodigoInterno()+
                "', nomProducto='"+producto.getNomProducto()+
                "', categoria='"+producto.getCategoria()+
                "', descripcion='"+producto.getDescripcion()+
                "', unidades="+producto.getUnidades()+
                ",  prec_venta="+producto.getPrec_venta()+
                ",  prec_mayorista="+producto.getPrec_mayorista()+
                ",  tipoDescuento='"+producto.getTipoDescuento()+
                "', numdescuento="+producto.getNumdescuento()+
                ", flagdescuento="+producto.isFlagdescuento()+
                ",  localizacion='"+producto.getLocalizacion()+
                "', foto='"+producto.getFotoProducto()+"'"+
                " WHERE _id="+id);
       // bd.close();
    }

    // Método para calcular el valor total del costo de las existencias en el inventario
    public double calculoValorTotal(){
        double valor=0;
        SQLiteDatabase bd=getReadableDatabase();
        Cursor cu=bd.rawQuery("SELECT SUM(TOTAL_UNI) as TOTAL FROM (SELECT (unidades*prec_mayorista) as TOTAL_UNI FROM productos group by _id)",null);
        if (cu.moveToFirst()){
            valor=cu.getDouble(0);
        }
        cu.close();
      //  bd.close();
        return valor;
    }



    public static Producto extraeProducto(Cursor cursor){
        Producto producto=new Producto();

        producto.setCodigoBarras(cursor.getString(1));
        producto.setCodigoInterno(cursor.getString(2));
        producto.setNomProducto(cursor.getString(3));
        producto.setCategoria(cursor.getString(4));
        producto.setDescripcion(cursor.getString(5));
        producto.setUnidades(cursor.getInt(6));
        producto.setPrec_venta(cursor.getDouble(7));
        producto.setPrec_mayorista(cursor.getDouble(8));
        producto.setTipoDescuento(cursor.getString(9));
        producto.setNumdescuento(cursor.getDouble(10));
        producto.setFlagdescuento(cursor.getInt(11));
        producto.setLocalizacion(cursor.getString(12));
        producto.setFotoProducto(cursor.getString(13));
        return producto;
    }

    public Cursor extraeCursor(String selCategoria){
        String consulta;
        Log.d("Dentro de la DB",selCategoria);
        if(selCategoria.equals("Todas las categorías")) { // Poner en otro idioma || selCategoria=="")
            consulta="SELECT * FROM productos";

        } else {
            consulta = "SELECT * FROM productos WHERE categoria='" + selCategoria+"'";
            Log.d("Consulta",consulta);
        }
        SQLiteDatabase bd=getReadableDatabase();
        return bd.rawQuery(consulta,null);
    }


    //================================= Métodos para las categorias de los productos ==================

    // Método para sacar las categorías
    public ArrayList<String> sacarCategorias(){
        categoriasList=new ArrayList<String>();
        SQLiteDatabase bd=getReadableDatabase();
        Cursor cu=bd.rawQuery("SELECT nomcategoria FROM categorias",null);

        int i=0;
        while (cu.moveToNext()){
            categoriasList.add(i,cu.getString(0));
            i++;
        }
        cu.close();
      //  bd.close();
        return categoriasList;
    }


    @Override
    public String elementCategoria(int p) {
        String selecCateogira="";
        SQLiteDatabase bde=getReadableDatabase();
        Cursor cu=bde.rawQuery("SELECT nomcategoria FROM categorias WHERE idCategoria="+p,null);
        if (cu.moveToFirst()) {
            selecCateogira = cu.getString(0);
        }
        cu.close();
      //  bde.close();
        return selecCateogira;
    }

    // Método para insertar una nueva categoría
    @Override
    public int addCategoria(String name) {
        int idCategoria_aux=0;

        SQLiteDatabase bd=getWritableDatabase();
      //  bd.execSQL("PRAGMA foreign_keys = ON");  No hace falta
        bd.execSQL("INSERT INTO categorias (nomcategoria) VALUES ('"+name+"')");
        Cursor c=bd.rawQuery("SELECT idCategoria FROM categorias WHERE idCategoria = (SELECT MAX(idCategoria) FROM categorias)",null);
        if (c.moveToFirst()) {
            idCategoria_aux = c.getInt(0);
        }
        c.close();
     //   bd.close();
        categoriasList.add(name);
        Log.d("Cursor", String.valueOf(idCategoria_aux));
        return idCategoria_aux;
    }

    @Override
    public boolean borrarCategoria(String deleteCat) {
        //String cateDelete;

        // Sacamos el nombre de la categoria
        // cateDelete=elementCategoria(d);


        SQLiteDatabase bd=getWritableDatabase();
        // Compruebo que en productos no haya esa categoría
        Cursor c=bd.rawQuery("SELECT * FROM productos WHERE categoria='"+deleteCat+"'",null);

        if (c!=null && c.getCount()>0){ // Tenemos registros por tanto hay que lanzar un aviso
            c.close();
           // bd.close();
            return false;

        } else { // en otro caso se podrá hacer el delete
            c.close(); //Cerramos el anterior cursor

            Log.d("Borrado id",deleteCat);
            bd.execSQL("DELETE FROM categorias WHERE nomcategoria='"+deleteCat+"'");
         //   bd.close();
            return true;
        }
    }

    @Override
    public int tamCategoria() {
        return 0;
    }

    @Override
    public void actualizarCategoria(int id, String name) {
        SQLiteDatabase bd=getWritableDatabase();
        bd.execSQL("UPDATE categorias SET nomcategoria ='"
                +name+"' WHERE idCategoria ="+id);
     //   bd.close();
    }

    public Cursor extraeCursorCat(){
        String consulta="SELECT * FROM categorias";
        SQLiteDatabase bd=getReadableDatabase();
        return bd.rawQuery(consulta,null);
    }

    // ============================== Métodos para la búsqueda de productos =================================
    public Cursor extraeCursorNomCod(String buscar){
        String consulta;
        if (buscar.isEmpty()){ // En el caso inicial se muestran todos los productos
            consulta="SELECT * FROM productos";
        } else {
            consulta = "SELECT * FROM productos WHERE nomProducto LIKE '%" + buscar + "%' OR codigoBarras LIKE '%" + buscar + "%'";
        }
        SQLiteDatabase bd=getReadableDatabase();
        return bd.rawQuery(consulta,null);
    }
}
