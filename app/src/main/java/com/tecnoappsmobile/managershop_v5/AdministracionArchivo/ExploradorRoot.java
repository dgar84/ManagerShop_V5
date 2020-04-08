package com.tecnoappsmobile.managershop_v5.AdministracionArchivo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.tecnoappsmobile.managershop_v5.R;

import java.io.File;
import java.util.ArrayList;

// import com.example.daniel.managershop.AdministracionArchivo.;
// import com.example.daniel.managershop.AdministracionArchivo;

/**
 * Created by danie on 08/12/2016.
 */

public class ExploradorRoot extends AppCompatActivity {
    private GridView sdsGrid;
    private ArrayList<String> sdCards; // rutas de almacenamiento
    public AdaptadorRoot adaptadorRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_explorer_root);
        sdsGrid=(GridView) findViewById(R.id.gridIcono);
        sdCards=getAllRoot(); // Extraemos todas las rutas

        adaptadorRoot=new AdaptadorRoot(this,sdCards);
        sdsGrid.setAdapter(adaptadorRoot);

        // Manejamos los click del gridLayout
        sdsGrid.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
               Intent intent=new Intent(ExploradorRoot.this, com.tecnoappsmobile.managershop_v5.AdministracionArchivo.ExplorerArchivos.class);
                intent.putExtra("root",sdCards.get(i));
                startActivity(intent);
            }
        });

    }

    public ArrayList<String> getAllRoot(){
        ArrayList<String> roots=new ArrayList<String>();

        // Almacenamiento EXTERNO
        //Comprobamos el estado de la memoria externa (tarjeta SD)
        String estado = Environment.getExternalStorageState();

        if(estado.equals(Environment.MEDIA_MOUNTED) || estado.equals(Environment.MEDIA_MOUNTED_READ_ONLY)){
            File ruta_sd = Environment.getExternalStorageDirectory();
            roots.add(ruta_sd.getAbsolutePath().toString());
        }
        return roots;
    }
}
