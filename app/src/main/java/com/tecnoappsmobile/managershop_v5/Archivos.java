package com.tecnoappsmobile.managershop_v5;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by DANIEL on 07/10/2016.
 */

public class Archivos {
    private String codigoBarras;
    private String codigoInterno;
    private String nomProducto;


    // datos para la administración de archivos
    // Se utiliza el misma variable que en las fotos: RutaArchivo
    private String nameFile;
    private boolean flagDir;

    private Uri uriFoto;
    private String RutaArchivo;

    private static final String NOMBRE_CARPETA="APP_MANAGER_SHOP";
    private static final String FOTOS_CARPETA="My_PHOTO_PRODUCTS";


    //
    public String getRutaArchivo() {
        return RutaArchivo;
    }
    public void setRutaArchivo(String rutaArchivo) {
        RutaArchivo = rutaArchivo;
    }

    // Nombre del archivo
    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }

    // Flag para averiguar si es un directorio o un archivo
    public boolean isFlagDir() {
        return flagDir;
    }

    public void setFlagDir(boolean flagDir) {
        this.flagDir = flagDir;
    }

    public Uri getUriFoto(){
        return uriFoto;
    }

    public Archivos(){

    }

    public Archivos(String codigoBarras, String codigoInterno, String nomProducto){
        this.codigoBarras=codigoBarras;
        this.codigoInterno=codigoInterno;
        this.nomProducto=nomProducto;
    }


    // Método para crear ficheros JPG
    public Uri CrearFicheroJPG(String codigoBarras, String codigoInterno, String nomProducto){
        String nomArchivo;
        String tarjetaSD= Environment.getExternalStorageDirectory().toString();

        // Creamos las carpetas si no están en el móvil
        File apliDir=new File(tarjetaSD+ File.separator+NOMBRE_CARPETA);
        if (!apliDir.exists()){
            Log.d("DEBUG","CREADO APP CREADO");
            apliDir.mkdir();
        }

        // Creamos el subdirectorio
        File SubDirFoto=new File(apliDir.getPath()+ File.separator+FOTOS_CARPETA);
        if (!SubDirFoto.exists()){
            SubDirFoto.mkdir();
            Log.d("DEBUG","CREADO APP FOTO CREADO");
        }

        // Para crear el archivo de la foto
        if (codigoBarras.isEmpty()==false && nomProducto.isEmpty()==false){
            nomArchivo=nomProducto+"_"+codigoBarras+".jpg";
        } else if (nomProducto.isEmpty()==false && codigoInterno.isEmpty()==false){
            nomArchivo=nomProducto+"_"+codigoInterno+".jpg";
        } else {
            nomArchivo="img_"+ System.currentTimeMillis()/1000+".jpg";
        }
        RutaArchivo=SubDirFoto.getPath()+ File.separator+nomArchivo;
        uriFoto= Uri.fromFile(new File(RutaArchivo));
        return uriFoto;
    }

    // Método para reducir el tamaño de la imagen
    public static Bitmap reducirBitmap(Context contexto, String uri, int maxAncho, int maxAlto){
        try{
            final BitmapFactory.Options options=new BitmapFactory.Options();
            options.inJustDecodeBounds=true;
            // Uri.parse(uri)
            BitmapFactory.decodeStream(contexto.getContentResolver().openInputStream(Uri.parse(uri)),null,options);

            options.inSampleSize=(int) Math.max(Math.ceil(options.outWidth/maxAncho), Math.ceil(options.outHeight/maxAlto));
            options.inJustDecodeBounds=false;
            return BitmapFactory.decodeStream(contexto.getContentResolver().openInputStream(Uri.parse(uri)),null,options);
        } catch (FileNotFoundException e){
            Toast.makeText(contexto,"Fichero o recurso no encontrado", Toast.LENGTH_LONG).show();
            e.printStackTrace();
            return null;
        }
    }

    public boolean BorradoArchivo(String pathAchivo){
        try {
            File file = new File(pathAchivo);
            boolean borrado = file.delete();
            return borrado;
       /* } catch(FileNotFoundException fileNotFound){
            Log.e("ERROR BORRADO",fileNotFound.getMessage());
            return false;*/
        } catch (Exception e){
            Log.e("ERROR DE BORRADO",e.getMessage());
            return false;
        }
    }

   /* private String getRealPathFromURI(Uri contentURI) {
        Cursor cursor = getContentResolver().query(contentURI, null, null, null, null);
        if (cursor == null) { // Source is Dropbox or other similar local file path
            return contentURI.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }*/
}
