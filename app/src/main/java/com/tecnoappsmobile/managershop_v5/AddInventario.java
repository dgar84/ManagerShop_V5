package com.tecnoappsmobile.managershop_v5;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.speech.RecognizerIntent;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.tecnoappsmobile.managershop_v5.ActivityInventario.BuscarProducto;

import java.io.File;
import java.util.ArrayList;

//import android.support.v7.app.AlertDialog;


/**
 * Created by DANIEL on 28/09/2016.
 */

public class AddInventario extends android.support.v4.app.Fragment implements View.OnClickListener {
    private EditText edCodInterno;
    private EditText edCodBarras;
    private EditText edNomProducto;
    private EditText edUnidades;
    private EditText edDescripcion;
    private EditText edPrecioVenta;
    private EditText edPrecioMayorista;
    private EditText edDescuento;
    private EditText edLocalizacion;

    private Spinner spCategoria;
    private Spinner spDesc;
    private Spinner spTipoDesc;

    private LinearLayout AddBuscar,VolverGuardarBorrar;

    private View v;

    private long id;

    private Button btnCamara,btnSelecImagen,btnBorrar,btnAddNewItem,btnBorrarItem,btnGuardar,btnBuscar,btnVolver;
    private Button btIncrementar,btDecrementar,btnAddEditCategoria;
    private ImageButton btnScanner;

    // reconocimineto de voz
    private ImageButton btnRecNombre,btnRecCode,btnRecCodeInt,btnDescripcion;
    private ImageView fotoProducto;
    private Uri uriFoto=null;
    private String rutaFoto;
    final static int SELECCIONAR_FOTO=2;
    final static int CAPTURAR_FOTO=1;
    final static int SCANNER=3;
    final static int SCANNER2=4;
    private String nomArchivo;
    private Producto producto;
    private Archivos archivos;

 ///   private LinkedList ListCategoria;
    private int idCategoria;
    private int contador;

    private ArrayAdapter<CharSequence> arrayCategoria;

    private ArrayList<String> listaCategoria = new ArrayList<String>();

    // Variables para permisos
    private int SOLICITUD_PERMISO_CAMARA=1;
    private int SOLICITUD_PERMISO_CAMARA2=4;
    private int SOLICITUD_PERMISO_LECTURA=2;
    private int SOLICITUD_PERMISO_ESCRITURA=3;
    public boolean flagPermisos=false;

    private Dialog warningIngreso,warningBorrado;

    private int  _id;


    final static int RECON_PRODUCTO=5;
    final static int RECON_COD_BARRAS=6;
    final static int RECON_COD_INT=7;
    final static int RECON_DESCRIPCION=8;

    // public static ProductosBD productos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.inventario_layout_add, container, false);

        spTipoDesc=(Spinner) v.findViewById(R.id.spTipoDescuento);
        ArrayAdapter<CharSequence> arrayTipDesc= ArrayAdapter.createFromResource(getContext(),R.array.TipoDescuento,android.R.layout.simple_spinner_item);
        arrayTipDesc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipoDesc.setAdapter(arrayTipDesc);

        /* Creación del Spinner con el descuento */
        spDesc=(Spinner) v.findViewById(R.id.spDescuento);
        ArrayAdapter<CharSequence> arrayDesc= ArrayAdapter.createFromResource(getContext(),R.array.Descuento,android.R.layout.simple_spinner_item);
        arrayDesc.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDesc.setAdapter(arrayDesc);

        spCategoria = (Spinner) v.findViewById(R.id.spCategoria);
        arrayCategoria= ArrayAdapter.createFromResource(getContext(),R.array.spCategoria,android.R.layout.simple_spinner_item);
        arrayCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCategoria.setAdapter(arrayCategoria);

        // Inicializamos el contador de existencias
       // contador=0;

        btIncrementar=(Button)v.findViewById(R.id.btIncrementar);
        btIncrementar.setOnClickListener(this);

        btDecrementar=(Button) v.findViewById(R.id.btDecrementar);
        btDecrementar.setOnClickListener(this);

        // Botones de la cámara
        // Botón para hacer la foto
        btnCamara=(Button)v.findViewById(R.id.btnCamara);
        btnCamara.setOnClickListener(this);

        // Botón para seleccionar la foto
        btnSelecImagen=(Button)v.findViewById(R.id.btn_SelecImagen);
        btnSelecImagen.setOnClickListener(this);

        // Botón para borrar la foto
        btnBorrar=(Button)v.findViewById(R.id.btnBorrar);
        btnBorrar.setOnClickListener(this);

        // Botón añadir un nuevo producto
        btnAddNewItem=(Button)v.findViewById(R.id.btnAddNewItem);
        btnAddNewItem.setOnClickListener(this);

        // Botón borrar producto
        btnBorrarItem=(Button)v.findViewById(R.id.btnBorrarItem);
        btnBorrarItem.setOnClickListener(this);

        // Botón para guardar producto
        btnGuardar=(Button) v.findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(this);

        btnBuscar=(Button) v.findViewById(R.id.btnBuscar);
        btnBuscar.setOnClickListener(this);

        btnVolver=(Button) v.findViewById(R.id.btnVolver);
        btnVolver.setOnClickListener(this);

        btnAddEditCategoria=(Button) v.findViewById(R.id.btnAddEditCategoria);
        btnAddEditCategoria.setOnClickListener(this);

        btnScanner=(ImageButton) v.findViewById(R.id.ScanerCode);
        btnScanner.setOnClickListener(this);


        btnRecNombre=(ImageButton) v.findViewById(R.id.microProducto);
        btnRecNombre.setOnClickListener(this);

        btnRecCode=(ImageButton) v.findViewById(R.id.microCode);
        btnRecCode.setOnClickListener(this);

        btnRecCodeInt=(ImageButton) v.findViewById(R.id.microCodInt);
        btnRecCodeInt.setOnClickListener(this);

        btnDescripcion=(ImageButton) v.findViewById(R.id.microDescripcion);
        btnDescripcion.setOnClickListener(this);

        fotoProducto=(ImageView) v.findViewById(R.id.fotoProducto);
        edCodBarras=(EditText) v.findViewById(R.id.edCodBarras);
        edCodInterno=(EditText) v.findViewById(R.id.edCodInterno);
        edNomProducto=(EditText) v.findViewById(R.id.edNomProducto);
        edDescripcion=(EditText) v.findViewById(R.id.edDescripcion);
        edUnidades=(EditText) v.findViewById(R.id.edUnidades);
        edPrecioVenta=(EditText) v.findViewById(R.id.edPrecioVenta);
        edPrecioMayorista=(EditText) v.findViewById(R.id.edPrecioMayorista);
        edDescuento=(EditText) v.findViewById(R.id.edDescuento);
        edLocalizacion=(EditText) v.findViewById(R.id.edLocalizacion);




        // Base de datos
    ///////////////////////////////////////////////////    productos=new ProductosBD(getContext());


        // Edición multiple en la descripción
        /* edDescripcion.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId==edDescripcion.IME_ACTION_DONE){

                }
                return false;
            }
        }); */


        /* Layout de botones en la parte inferior. Al iniciar la aplicación los botones visibles son los del linealLayout: linealAddBuscar.
        Que son los botones Añadir y Buscar. Hay otro LinealLayout que es invisible (linealVolverGuardarBorrar) que será visible cuando se
        seleccione de una lista un producto. Éste último tendrá los botones: Volver, Guardar y Borrar.
         */
        AddBuscar=(LinearLayout)v.findViewById(R.id.linealAddBuscar);
        VolverGuardarBorrar=(LinearLayout) v.findViewById(R.id.linealVolverGuardarBorrar);




        producto=new Producto();
        archivos =new Archivos(edCodBarras.getText().toString(),edCodInterno.getText().toString(),edNomProducto.getText().toString());
        Log.d("Size btnAdd", String.valueOf(spCategoria.getCount()));
        Log.d("Size lista", String.valueOf(listaCategoria.size()));
        for (int iv = 0; iv < spCategoria.getCount(); iv++) { //spCategoria.getCount()
            Log.d("Esto es un elemento",spCategoria.getItemAtPosition(iv).toString());
            Log.d("Value", String.valueOf(iv));
            //  listaCategoria.add(spCategoria.getItemAtPosition(iv).toString()); //
            listaCategoria.add(iv,spCategoria.getItemAtPosition(iv).toString());
        }

        // Para que por defecto al abrir la primera vez esta pestaña no salga el teclado
      /*  edCodBarras.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.onTouchEvent(event);
                InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }

                return true;
            }
        }); */

        // Escondemos el teclado al iniciar
        InputMethodManager imm = (InputMethodManager)v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(edCodBarras.getWindowToken(), 0);
        return v;
    }
    @Override
    public void onClick(View v) {
        int sel = v.getId();

        String cadenaWarning=""; // Cadena de aviso para el diálogo en caso de ingreso de nuevo producto
        // String[] categoria={""};
      ///  ArrayList<String> listaCategoria = new ArrayList<String>();
        // archivos =new Archivos(edCodBarras.getText().toString(),edCodInterno.getText().toString(),edNomProducto.getText().toString());

        switch (sel) {
            case R.id.btnCamara: // Captura de la imagen
                // Indicamos que se tiene que comprobar el acceso a ficheros
                checkPermisos(SOLICITUD_PERMISO_CAMARA);
               /*  Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
                uriFoto= archivos.CrearFicheroJPG(edCodBarras.getText().toString(),edCodInterno.getText().toString(),edNomProducto.getText().toString());
                Toast.makeText(getContext(),uriFoto.toString(),Toast.LENGTH_LONG).show(); //
                i.putExtra(MediaStore.EXTRA_OUTPUT,uriFoto); //uriFoto
                startActivityForResult(i,CAPTURAR_FOTO); */
                break;
            case R.id.btn_SelecImagen:
               // Indicamos que sólo queremos el permiso de seleccionar una foto.
                checkPermisos(SOLICITUD_PERMISO_LECTURA);
              /*  uriFoto=MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                Intent ii=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                ii.setType("image/*");
                startActivityForResult(ii.createChooser(ii,getResources().getText(R.string.titSelecionarFoto)),SELECCIONAR_FOTO);  */
                break;
            case R.id.btnBorrar: // Borrado de foto
                // Creamos la alerta
              new AlertDialog.Builder(getContext())
                    .setTitle(R.string.titBorrado)
                    .setMessage(R.string.mesBorrado)
                    .setPositiveButton(R.string.btnConfirmar, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichButton) {
                            boolean flagBorrado;
                            Toast.makeText(getContext(),archivos.getRutaArchivo(), Toast.LENGTH_LONG).show();
                            if (producto.getFotoProducto()!=null) {
                                Toast.makeText(getContext(),"Borrar archivo", Toast.LENGTH_LONG).show();
                                Toast.makeText(getContext(),archivos.getRutaArchivo(), Toast.LENGTH_LONG).show();

                                File file = new File(archivos.getRutaArchivo());
                               // File file= new File(Environment.getExternalStorageDirectory().toString()+File.separator+"APP_MANAGER_SHOP"+File.separator+"My_PHOTO_PRODUCTS"+File.separator+"img_1475911279.jpg");
                                try {
                                    flagBorrado = file.delete();
                                    if (flagBorrado ==true) {
                                        Toast.makeText(getContext(),"Se ha borrado el archivo", Toast.LENGTH_LONG).show();
                                    } else {
                                        Toast.makeText(getContext(),"No se ha borrado", Toast.LENGTH_LONG).show();
                                    }
                                    actualizarEditView();
                                } catch (Exception e) {
                                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                                }
                               // flagBorrado = archivos.BorradoArchivo(producto.getFotoProducto());

                            }
                        }})
                        .setNegativeButton(android.R.string.cancel, null)
                    .show();

                  break;
            case R.id.btnAddEditCategoria:
                // Crear o editar una categoría
              /*  final EditText entrada = new EditText(getContext());
                entrada.setText("");
                new AlertDialog.Builder(getContext())
                        .setTitle(R.string.titAddEdit)
                        .setMessage(R.string.txtCategoria)
                        .setView(entrada)
                        .setPositiveButton(R.string.btnConfirmar,new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog,int witch){
                                String entCategoria=entrada.getText().toString();
                                Spinner spCategoria = (Spinner) getView().findViewById(R.id.spCategoria);
                                ListCategoria.add(idCategoria,entCategoria);
                                idCategoria+=1;
                                //Creamos el adaptador
                                ArrayAdapter spinner_adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, ListCategoria);
                                //Añadimos el layout para el menú y se lo damos al spinner
                                spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                spCategoria.setAdapter(spinner_adapter);

                        }})
                        .setNegativeButton(android.R.string.cancel, null)
                        .show(); */

            ///    Intent iii=new Intent(getContext(),DialogCategoria.class);
             ///   startActivity(iii);
                   // spCategoria = (Spinner) getView().findViewById(R.id.spCategoria);
              /*      Log.d("Size btnAdd",String.valueOf(spCategoria.getCount()));
                    Log.d("Size lista",String.valueOf(listaCategoria.size()));
                    for (int iv = 0; iv < spCategoria.getCount(); iv++) { //spCategoria.getCount()
                        Log.d("Esto es un elemento",spCategoria.getItemAtPosition(iv).toString());
                        Log.d("Value",String.valueOf(iv));
                      //  listaCategoria.add(spCategoria.getItemAtPosition(iv).toString()); //
                        listaCategoria.add(iv,spCategoria.getItemAtPosition(iv).toString());
                    }
                    */
                  /////  Bundle buListCategoria=new Bundle();
                  /////  buListCategoria.putStringArrayList("categoria",listaCategoria);
                /* Por dialogFragment =========================================
                    DialogCategoria overlay = new DialogCategoria();
                    overlay.setArguments(buListCategoria);
                   FragmentManager fm = getFragmentManager();//getSupportFragmentManager();
              //      DialogCategoria overlay = new DialogCategoria();
                    overlay.show(fm, "dialog_fragment_categoria");
                    */
          /* ===================================          Intent inten=new Intent(getActivity(),DialogCategoriaAc.class);
                    inten.putStringArrayListExtra("categoria",listaCategoria);
                    startActivityForResult(inten,1234); ============================= */
                break;
            case R.id.btnAddNewItem:  // Añadir un nuevo registro a la base de datos
                // Método de captura de los datos y guadado de los atributos en el objeto Producto.
                // Se cogen todos los datos del interfaz.
                producto=capturaAtributos();

                // Se comprueba que tenemos los requisitos mínimos: Nombre producto, Precio de venta y Unidades
                if (producto.getNomProducto().isEmpty() ||
                        producto.getPrec_venta()==0 || producto.getUnidades()==0) {

                    if (producto.getNomProducto().isEmpty() &&
                            producto.getPrec_venta() == 0 && producto.getUnidades() == 0) { // En caso de que no se introduzca ningún campo.
                        cadenaWarning = getResources().getString(R.string.dialog_cont_add);
                    } else { // en el caso de que no se haya ingresado un o dos parámetros
                        if (producto.getNomProducto().isEmpty()) {
                            cadenaWarning = getResources().getString(R.string.warningNom) + "\n";
                        }
                        if (producto.getPrec_venta() == 0) {
                            cadenaWarning += getResources().getString(R.string.warningPrecio) + "\n";
                        }
                        if (producto.getUnidades() == 0) {
                            cadenaWarning += getResources().getString(R.string.warningUnidades);
                        }
                    }

                    // Mostrar dialog avisando de que se debe ingresar como mínimo estos datos
                    warningIngreso(cadenaWarning);
                } else {

                    // Se añade el nuevo producto a la base de datos
                    _id =InitActivity.productos.nuevo(producto);

                    Log.d("DEBUG", Integer.toString(_id));
                   // ListInventario.adaptador.productos.elemento(_id);
                }
                    break;

            case R.id.btnBorrarItem: // Borrar producto
                _id=ListInventario.adaptador.idPosicion((int) id);

                // llamar a cuadro de diálogo para confirmar el borrado
                warningBorrado();
               // MainActivity.productos.borrar(_id);
                break;
            case R.id.btIncrementar:
                incrementaContador(getView());
                break;
            case R.id.btDecrementar:
                decrementaContador(getView());
                break;

            case R.id.btnVolver: // En el caso de pulsar al botón volver retornará
                                 // a layout para añadir un producto nuevo.
                volverView(); // Este método se encarga de vaciar los editores y volver ha hacer visible el layout de los botones de Añadir y Buscar
                break;
            case R.id.btnGuardar: // En este caso hay que actualizar el registro del producto
                capturaAtributos();
                _id=ListInventario.adaptador.idPosicion((int) id);
                InitActivity.productos.actualizarProducto(_id,producto);

              //  ListInventario.adaptador.setCursor(MainActivity.productos.extraeCursor(producto.getCategoria()));
                break;
            case R.id.btnBuscar:

                BuscarProducto dial=new BuscarProducto(getContext());
                DisplayMetrics metrica=getResources().getDisplayMetrics();
                int ancho=metrica.widthPixels;
                int alto=metrica.heightPixels;
                dial.setTitle(R.string.titulo_buscar);
                dial.show();
                dial.getWindow().setLayout(6*ancho/7,3*alto/4);

                if (dial.getFlag_buscar()==1){
                    Intent intent2= new Intent(getContext(), CodeScanner.class);
                    //startActivity(intent1);
                     startActivityForResult(intent2, SCANNER2);
                }
                dial.setDialogResult(new BuscarProducto.DialogBuscaResult(){
                    public void finishDialog(long reg){
                        producto= BuscarProducto.adaptadorBuscar.productoPosicion((int) reg);
                                // adaptadorBuscar.productoPosicion((int) reg);
                        actualizarEditView();
                    }
                });
                break;

            case R.id.ScanerCode:
               /* IntentIntegrator integrator = new IntentIntegrator(this);
                List<String> target = new ArrayList<String>();
                target.add("com.example.daniel.managershop");
                integrator.setTargetApplications(target);
                integrator.initiateScan(); */
                checkPermisos(SOLICITUD_PERMISO_CAMARA2);
               Toast.makeText(getContext(),"Dentro de barras",Toast.LENGTH_LONG).show();
                Intent intent1=new Intent(getContext(),CodeScanner.class);
                //startActivity(intent1);
                startActivityForResult(intent1,SCANNER);
                break;

            case R.id.microProducto:
                reconociminetoVoz(RECON_PRODUCTO);
                break;
            case R.id.microCode:
                reconociminetoVoz(RECON_COD_BARRAS);
                break;
            case R.id.microCodInt:
                reconociminetoVoz(RECON_COD_INT);
                break;
            case R.id.microDescripcion:
                reconociminetoVoz(RECON_DESCRIPCION);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==CAPTURAR_FOTO && resultCode== Activity.RESULT_OK && producto!=null){ //&& uriFoto!=null){
            Toast.makeText(getContext(),"Uri:"+uriFoto.toString(),Toast.LENGTH_LONG).show();
            producto.setFotoProducto(uriFoto.toString()); //uriFoto.toString()
            ponerFoto(fotoProducto,producto.getFotoProducto());
        } else if (requestCode==SELECCIONAR_FOTO && resultCode== Activity.RESULT_OK) {
            Toast.makeText(getContext(), data.getDataString(), Toast.LENGTH_LONG).show();

            uriFoto = data.getData();
            String s = uriFoto.getPath();

            producto.setFotoProducto(data.getData().toString());
            archivos.setRutaArchivo(s);
            Toast.makeText(getContext(), s, Toast.LENGTH_LONG).show();
            ponerFoto(fotoProducto, producto.getFotoProducto());
            //   archivos.setRutaArchivo(producto.getFotoProducto());
        } else if (requestCode==SCANNER && resultCode==Activity.RESULT_OK){
            // Recogemos todos los parámetros
            String codigo =data.getDataString();
            edCodBarras.setText(codigo);
            Toast.makeText(getContext(),codigo,Toast.LENGTH_LONG).show();

        } else if (requestCode==SCANNER2 && resultCode==Activity.RESULT_OK){
            // Recogemos todos los parámetros
            String codigo =data.getDataString();
            edCodBarras.setText(codigo);
            Toast.makeText(getContext(),codigo,Toast.LENGTH_LONG).show();
        } else if (requestCode==RECON_PRODUCTO && resultCode==Activity.RESULT_OK){
            ArrayList<String> speech=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String speechReco=speech.get(0);
            edNomProducto.setText(speechReco);
        } else if (requestCode==RECON_COD_BARRAS && resultCode==Activity.RESULT_OK){
            ArrayList<String> speech=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String speechReco=speech.get(0);
            edCodBarras.setText(speechReco);
        } else if (requestCode==RECON_COD_INT && resultCode==Activity.RESULT_OK){
            ArrayList<String> speech=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String speechReco=speech.get(0);
            edCodInterno.setText(speechReco);
        } else if (requestCode==RECON_DESCRIPCION && resultCode==Activity.RESULT_OK){
            ArrayList<String> speech=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String speechReco=speech.get(0);
            edDescripcion.setText(speechReco);
        } else if (requestCode==1234 && resultCode== Activity.RESULT_OK) {
            // Resultados de: añadir una nueva categoria / modificar categoria o borrar categoria

            // Recogemos todos los parámetros
            String entCategoria = data.getExtras().getString("categoriaAdd");
            String nomCategoria = data.getExtras().getString("NomCategoria");
            int idCategoria = data.getExtras().getInt("Idcategoria");

            // AÑADIR una nueva categoría
            if (idCategoria == 0 && nomCategoria.isEmpty()) {
                Log.d("Se ha añadido", entCategoria);
                //     Spinner spCategoria = (Spinner) getView().findViewById(R.id.spCategoria);
                Log.d("Tamaño", String.valueOf(spCategoria.getCount()));
                listaCategoria.add(spCategoria.getCount(), entCategoria);
                // idCategoria+=1;
                //Creamos el adaptador // ArrayAdapter spinner_adapter
                arrayCategoria = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listaCategoria);
                //Añadimos el layout para el menú y se lo damos al spinner
                arrayCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spCategoria.setAdapter(arrayCategoria);

                // MODIFICAR una categoría
            } else if (entCategoria.isEmpty()) {
                listaCategoria.set(idCategoria, nomCategoria);
                arrayCategoria = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listaCategoria);
                arrayCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spCategoria.setAdapter(arrayCategoria);

                // GUARDAR una categoría
            } else if (entCategoria.isEmpty() && nomCategoria.isEmpty()) {
                listaCategoria.remove(idCategoria);
                arrayCategoria = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, listaCategoria);
                arrayCategoria.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spCategoria.setAdapter(arrayCategoria);
            } else {
                Log.d("AddCategoria", "No se ha contemplado todas las posibilidades");
            }
        }
    }

    @Override
    public void onActivityCreated(Bundle state){
        super.onActivityCreated(state);
        v=getView();
        Bundle extras=getActivity().getIntent().getExtras();
        if(extras!=null){
            id=extras.getLong("id",-1);
            if(id!=-1){
                Log.d("Se ha elegido", String.valueOf(id));
               // producto=Inventario.productos.elemento((int) id);
                producto=ListInventario.adaptador.productoPosicion((int) id);
                actualizarEditView();
            }
        }
    }


    // Método de poner la foto en la Imagen
    protected void ponerFoto(ImageView img, String uri){
        if(uri!=null){
            Toast.makeText(getContext(),uri, Toast.LENGTH_LONG).show();
            img.setImageBitmap(archivos.reducirBitmap(getContext(),uri,512,512));
       //     img.setImageURI(Uri.parse(uri));
        } else {
            img.setImageResource(R.drawable.icono_foto_modificada);
        }
    }


    // Método para incrementar el contador de existencias
    public void incrementaContador(View vista){
        edUnidades=(EditText)vista.findViewById(R.id.edUnidades);
        contador++;
    //    Toast.makeText(getContext(),String.valueOf(contador),Toast.LENGTH_LONG).show();
        edUnidades.setText(""+contador);
    }

    public void decrementaContador(View vista) {
        edUnidades=(EditText)vista.findViewById(R.id.edUnidades);
        contador--;
        if (contador < 0) {
            contador = 0;
        }
     //   Toast.makeText(getContext(),String.valueOf(contador),Toast.LENGTH_LONG).show();
        edUnidades.setText("" + contador);
    }

    // Método para actualizar la vista cuando se ha seleccionado un producto
    private void actualizarEditView(){
        edCodBarras.setText(producto.getCodigoBarras());
        edCodInterno.setText(producto.getCodigoInterno());
        edNomProducto.setText(producto.getNomProducto());
        edDescripcion.setText(producto.getDescripcion());
        edUnidades.setText(String.valueOf(producto.getUnidades()));
        edPrecioVenta.setText(String.valueOf(producto.getPrec_venta()));
        edPrecioMayorista.setText(String.valueOf(producto.getPrec_mayorista()));
        edDescuento.setText(String.valueOf(producto.getNumdescuento()));
        edLocalizacion.setText(producto.getLocalizacion());
        spCategoria.setSelection(arrayCategoria.getPosition(producto.getCategoria()));
        /* Cada vez que se actualice la vista hay que cambiar la visibilidad de los botones.
         Los botones que serán visibles son:
           Volver-> para volver al estado inicial de los editores en blanco.
           Guardar-> Para guardar el producto en la base de datos una vez que este se ha modificado.
           Borrar-> Para borrar el registro de la base de datos.
          Los botones invisibles son:
            Añadir-> Añadir un nuevo producto a la base de datos.
            Buscar-> Buscar un producto.
         */

        // Abrir la imagen en caso de que exista
        String rutaFoto=producto.getFotoProducto();
        if (!rutaFoto.isEmpty()){
            ponerFoto(fotoProducto,rutaFoto);
           /* if (rutaFoto.indexOf("content://")!=-1){ // Si el valor es un entero quiere decir que accedemos desde el content provider
                Uri uriFoto=Uri.parse(rutaFoto);

                try {
                    InputStream is=getContentResolve
                }


            } else { // se accede desde la ruta

            } */
        }

        AddBuscar.setVisibility(View.INVISIBLE);
        VolverGuardarBorrar.setVisibility(View.VISIBLE);
    }

    // Método para volver al estado Inicial (para añadir o buscar un nuevo producto).
    private void volverView(){
        edCodBarras.setText("");
        edCodInterno.setText("");
        edNomProducto.setText("");
        edDescripcion.setText("");
        edUnidades.setText("");
        edPrecioVenta.setText("");
        edPrecioMayorista.setText("");
        edDescuento.setText("");
        edLocalizacion.setText("");
        VolverGuardarBorrar.setVisibility(View.INVISIBLE);
        AddBuscar.setVisibility(View.VISIBLE);
    }

    // Método para capturar los datos del interfaz y gurdado de los atributos del objeto Producto
    private Producto capturaAtributos(){
        int flagDescuento;

        producto.setCodigoBarras(edCodBarras.getText().toString());
        producto.setCodigoInterno(edCodInterno.getText().toString());
        producto.setNomProducto(edNomProducto.getText().toString());
        producto.setCategoria(spCategoria.getItemAtPosition(spCategoria.getSelectedItemPosition()).toString());
        Log.d("Categoria",spCategoria.getItemAtPosition(spCategoria.getSelectedItemPosition()).toString());
        producto.setDescripcion(edDescripcion.getText().toString());

        try {
            producto.setUnidades(Integer.parseInt(edUnidades.getText().toString()));
        } catch (Exception e){
            producto.setUnidades(0);

        }

        try {
            producto.setPrec_venta(Double.parseDouble(edPrecioVenta.getText().toString()));
        } catch (Exception e){
            producto.setPrec_venta(Double.parseDouble("0.0"));
        }

        try {
            producto.setPrec_mayorista(Double.parseDouble(edPrecioMayorista.getText().toString()));
        } catch (Exception e){
            producto.setPrec_mayorista(Double.parseDouble("0.0"));
        }


        Log.d("D",spTipoDesc.getItemAtPosition(spTipoDesc.getSelectedItemPosition()).toString() );
        producto.setTipoDescuento(spTipoDesc.getItemAtPosition(spTipoDesc.getSelectedItemPosition()).toString());

        // Variable que puede ser de precio o %
        String descuento=spDesc.getItemAtPosition(spDesc.getSelectedItemPosition()).toString();
        if (descuento=="%"){
            flagDescuento=1; // Un 1 indica que el tipo de descuento es por porcentaje
        } else {
            flagDescuento=0; // Un 0 indica que el tipo de descuento es por valor
        }
        producto.setFlagdescuento(flagDescuento);

        // Al tener un hint en le editText
        try {
            producto.setNumdescuento(Double.parseDouble(edDescuento.getText().toString()));
        } catch (Exception e){
            producto.setNumdescuento(Double.parseDouble("0.0"));
        }

        try {
            Log.d("DEBUG:",uriFoto.toString());
            Log.d("DEBUG:",producto.getFotoProducto());
            producto.setFotoProducto(producto.getFotoProducto());
        } catch (Exception e){
            Log.e("ERROR: ","No localiza la imagen");
            producto.setFotoProducto("");
        }
        producto.setLocalizacion(edLocalizacion.getText().toString());
        return producto;
    }


    // Checkeo de permisos
    public void checkPermisos(int permiso) {

        switch (permiso){
            case 1:    // Permiso de cámara
                // CHEQUEO DE CÁMARA
                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
                StrictMode.setVmPolicy(builder.build());
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
                        Snackbar.make(getView(), getResources().getString(R.string.permiso_camara), Snackbar.LENGTH_INDEFINITE)
                                .setAction(android.R.string.ok, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                                                SOLICITUD_PERMISO_CAMARA);
                                         checkPermisos(SOLICITUD_PERMISO_ESCRITURA);
                                    }
                                })
                                .show();
                    } else {
                        Log.d("Debug","permisos");
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, SOLICITUD_PERMISO_CAMARA);
                        checkPermisos(SOLICITUD_PERMISO_ESCRITURA);
                    }
                } else {
                    hacerFoto();
                }
                break;
            case 2:
                // Permisos de lectura cuando se accede desde la selección de foto
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
                        Snackbar.make(getView(), getResources().getString(R.string.permiso_fichero_lectura), Snackbar.LENGTH_INDEFINITE)
                                .setAction(android.R.string.ok, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                                                SOLICITUD_PERMISO_LECTURA);
                                        seleccionarFoto();
                                    }
                                })
                                .show();
                    } else {
                        Log.d("Permisos","LECTURA");
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, SOLICITUD_PERMISO_LECTURA);
                    }
                } else {
                    seleccionarFoto();
                }
                break;
            case 3:
                // Permisos de escritura cuando se accede desde el botón CAMARA
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                        Snackbar.make(getView(), getResources().getString(R.string.permiso_fichero_escritura), Snackbar.LENGTH_INDEFINITE)
                                .setAction(android.R.string.ok, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                                                SOLICITUD_PERMISO_ESCRITURA);
                                        hacerFoto();
                                    }
                                })
                                .show();
                    } else {
                        Log.d("Permisos","ESCRITURA");
                        ActivityCompat.requestPermissions(getActivity() , new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, SOLICITUD_PERMISO_ESCRITURA);
                    }
                } else {
                    hacerFoto();
                }
                break;

            case 4:    // Permiso de cámara
                // CHEQUEO DE CÁMARA
//                StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
   //             StrictMode.setVmPolicy(builder.build());
                if (ContextCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.CAMERA)) {
                        Snackbar.make(getView(), getResources().getString(R.string.permiso_camara), Snackbar.LENGTH_INDEFINITE)
                                .setAction(android.R.string.ok, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA},
                                                SOLICITUD_PERMISO_CAMARA);
                                        checkPermisos(SOLICITUD_PERMISO_ESCRITURA);
                                    }
                                })
                                .show();
                    } else {
                        Log.d("Debug","permisos");
                        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CAMERA}, SOLICITUD_PERMISO_CAMARA);

                    }
                }

        }

    }


    @Override
    public void onRequestPermissionsResult (int requestCode, String[] permissions, int[] grantResults){
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("Permisos", String.valueOf(grantResults[0]));
        Log.d("Permisos", String.valueOf(grantResults[1]));
        Log.d("Permisos", String.valueOf(grantResults[2]));
        if (requestCode == SOLICITUD_PERMISO_CAMARA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                hacerFoto();
            }
        } else if(requestCode==SOLICITUD_PERMISO_LECTURA){
            if (grantResults.length > 0 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                seleccionarFoto();
            }
        } else if(requestCode==SOLICITUD_PERMISO_ESCRITURA) {
            if (grantResults.length > 0 && grantResults[2] == PackageManager.PERMISSION_GRANTED) {
                hacerFoto();
            }
        }
    }

    // Método para hacer la foto
    public void hacerFoto(){
        Intent i = new Intent("android.media.action.IMAGE_CAPTURE");
        uriFoto= archivos.CrearFicheroJPG(edCodBarras.getText().toString(),edCodInterno.getText().toString(),edNomProducto.getText().toString());
        Toast.makeText(getContext(),uriFoto.toString(), Toast.LENGTH_LONG).show(); //
        i.putExtra(MediaStore.EXTRA_OUTPUT,uriFoto); //uriFoto
        startActivityForResult(i,CAPTURAR_FOTO);
    }

    // Método de seleccionar foto
    public void seleccionarFoto(){
        uriFoto= MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        Intent ii=new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        ii.setType("image/*");
        startActivityForResult(ii.createChooser(ii,getResources().getText(R.string.titSelecionarFoto)),SELECCIONAR_FOTO);
    }

    // AVISO: en caso de que no se hayan ingresado los datos mínimos para efectuar un nuevo registro
    public void warningIngreso(String dialogContent){
        warningIngreso = new Dialog(getActivity(), R.style.Theme_Dialog_Translucent);

        // Deshabilitamos el titulo por defecto
        warningIngreso.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // obligamos al usuario a tener que pulsar los botones
        warningIngreso.setCancelable(false);

        warningIngreso.setContentView(R.layout.dialog_warning);

        TextView titulo = (TextView) warningIngreso.findViewById(R.id.titulo);
        titulo.setText(R.string.dialog_tit);

        TextView contenido = (TextView) warningIngreso.findViewById(R.id.contenido);
        contenido.setText(dialogContent);

        ((Button) warningIngreso.findViewById(R.id.aceptar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                warningIngreso.dismiss();
            }
        });

        warningIngreso.show();
    }

    // AVISO de confirmación de que se desea borrar
    public void warningBorrado(){
        warningBorrado = new Dialog(getActivity(), R.style.Theme_Dialog_Translucent);

        // Deshabilitamos el titulo por defecto
        warningBorrado.requestWindowFeature(Window.FEATURE_NO_TITLE);

        // obligamos al usuario a tener que pulsar los botones
        warningBorrado.setCancelable(false);

        warningBorrado.setContentView(R.layout.dialog_warning);

        TextView titulo = (TextView) warningBorrado.findViewById(R.id.titulo);
        titulo.setText(R.string.dialog_tit_aviso);

        TextView contenido = (TextView) warningBorrado.findViewById(R.id.contenido);
        contenido.setText(R.string.dialog_aviso_borrado);

        ((Button) warningBorrado.findViewById(R.id.aceptar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _id=ListInventario.adaptador.idPosicion((int) id);
                InitActivity.productos.borrar(_id);
                ListInventario.adaptador.setCursor(InitActivity.productos.extraeCursor(producto.getCategoria()));
                ListInventario.adaptador.notifyDataSetChanged();

                // Hay que poner a cero los parámtros de los editores
                volverView();

                warningBorrado.dismiss();
            }
        });

        // Añadir el botón de cancelar dinámicamente
       /*  LinearLayout linBoton=(LinearLayout) v.findViewById(R.id.botonera);
        Button btnCancelarWar=new Button(getContext());
        btnCancelarWar.setText(android.R.string.cancel);
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        btnCancelarWar.setLayoutParams(layoutParams);
        btnCancelarWar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                warningBorrado.dismiss();
            }
        }); */

        ((Button) warningBorrado.findViewById(R.id.cancelar)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                warningBorrado.dismiss();
            }
        });

        warningBorrado.show();
    }


    private void reconociminetoVoz(int requestCode){
        Intent intentReco=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        // CONFIGURACIÓN DE LA LENGUA
        intentReco.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,"es-ES");

        try {
            startActivityForResult(intentReco,requestCode);
        } catch (ActivityNotFoundException e){
            Toast.makeText(getContext(),"No reconoce la voz",Toast.LENGTH_LONG).show();
        }

    }

}
