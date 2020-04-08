package com.tecnoappsmobile.managershop_v5.AdministracionArchivo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.tecnoappsmobile.managershop_v5.Archivos;
import com.tecnoappsmobile.managershop_v5.DivideItemDecoration;
import com.tecnoappsmobile.managershop_v5.R;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by danie on 08/12/2016.
 */

public class ExplorerArchivos extends AppCompatActivity {
    private RecyclerView recylerArchivos;
    private AdaptadorArchivos adaptadorArchivos;
    private RecyclerView.LayoutManager layoutManArchivo;

    private ArrayList<String> direcc;

    private String SDCARD_EXT=""; // Ruta de la tarjeta

    private File Dir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_explorer_archivos);

        // Creamos el recyclerView
        recylerArchivos=(RecyclerView) findViewById(R.id.recyclerviewExplorer);

        // Capturamos la ruta pulsada
        Intent intent=getIntent();
        Bundle extras=intent.getExtras();
        SDCARD_EXT=extras.getString("root");

        Dir=new File(SDCARD_EXT);

        listaArchivos(Dir);
     /*   recylerArchivos.setAdapter(adaptadorArchivos);
        layoutManArchivo=new LinearLayoutManager(recylerArchivos.getContext());
        recylerArchivos.setLayoutManager(layoutManArchivo);
        recylerArchivos.addItemDecoration(new DivideItemDecoration(recylerArchivos.getContext())); */

        // Añadidmos el escuchador de los elementos del recyclerView
        adaptadorArchivos.setOnRutaClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Archivos archivoElegido=adaptadorArchivos.getArchivo((int) recylerArchivos.getChildAdapterPosition(view));
                Log.d("Seleccion archivo",archivoElegido.getNameFile());
                Log.d("Seleccion ruta",archivoElegido.getRutaArchivo());
                if(archivoElegido.isFlagDir()){ // es un directorio
                    if(archivoElegido.getNameFile().equals("...")){

                        // Esto sirve para capturar el root
                        String[] charsPath=Dir.getAbsolutePath().split("/");
                        String ultimoPath="";

                        for (int i=1;i<charsPath.length;i++){
                            if(i!=charsPath.length-1){
                                ultimoPath=ultimoPath+"/"+charsPath[i];
                            }
                        }
                        listaArchivos(new File(ultimoPath));
                        adaptadorArchivos.notifyDataSetChanged();
                        return;
                    } else {
                        Dir=new File(archivoElegido.getRutaArchivo());
                        Log.d("Ruta elegida ",archivoElegido.getRutaArchivo().toString());
                        listaArchivos(Dir);
                        adaptadorArchivos.notifyDataSetChanged();
                        return;
                    }
                } else { // si es archivo.

                }

            }
        });

    }

    public void listaArchivos(File Dir){
        boolean flag; // flag:
                        // true es un directorio
                        // false es un archivo
        String titulo=Dir.getPath();


        this.setTitle(titulo); // Colocamos el titulo de la ruta

        File todosArchivos[] =Dir.listFiles(); // Listamos todos los archivos de la ruta
        if(Dir.listFiles()==null){
            Toast.makeText(getApplicationContext(),"Carpeta vacia",Toast.LENGTH_SHORT).show();
            return;
        }

        // Creamos dos tipos de array uno para los archivos y otro para los directorios
        ArrayList<File> archivosFile=new ArrayList<File>();
        ArrayList<File> directorioFile=new ArrayList<File>();

        // Creamos un array auxiliar para ordenarlos primero los directorios y después los archivos
        ArrayList<File> AllFiles=new ArrayList<File>();

        ArrayList<Archivos> archivos=new ArrayList<Archivos>();

        // Recorremos todo la ruta
        for(int i=0;i<todosArchivos.length;i++){
            // no dejar que se abra la ruta android_secure
            if(todosArchivos[i].getName().equals(".android_secure")) continue;

            // Si es un directorio
            if(todosArchivos[i].isDirectory()){
                directorioFile.add(todosArchivos[i]);
            }

            // Si es un archivo
            if(todosArchivos[i].isFile()){
                archivosFile.add(todosArchivos[i]);
            }
        }


        // Ponemos primero los directorios
        AllFiles.addAll(directorioFile);

        // Ponemos después los archivos
        AllFiles.addAll(archivosFile);

        // Si es root ponemos los ...
        if(!Dir.getAbsolutePath().equals(SDCARD_EXT)){
            archivos.add(new Archivos("...","","")); // Añadir el directorio raiz del directorio
        }

        // Recorremos todos los archivos y directorios
        for(int i=0;i<AllFiles.size();i++){
            File aux=AllFiles.get(i);

            if(aux.isDirectory()) {
                flag = true;
                archivos.add(new Archivos(aux.getName(), aux.getPath(),""));
                Log.d("directorio ",aux.getPath());
            } else { // es un archivo
                flag=false;
                archivos.add(new Archivos(aux.getName(),aux.getPath(),""));
                Log.d("archivo ",aux.getName());
            }
        }
        adaptadorArchivos=new AdaptadorArchivos(this,archivos);
        recylerArchivos.setAdapter(adaptadorArchivos);
        layoutManArchivo=new LinearLayoutManager(recylerArchivos.getContext());
        recylerArchivos.setLayoutManager(layoutManArchivo);
        recylerArchivos.addItemDecoration(new DivideItemDecoration(recylerArchivos.getContext()));
      /*  recylerArchivos.setAdapter(adaptadorArchivos);
        layoutManArchivo=new LinearLayoutManager(this);
        recylerArchivos.setLayoutManager(layoutManArchivo); */
    }
}