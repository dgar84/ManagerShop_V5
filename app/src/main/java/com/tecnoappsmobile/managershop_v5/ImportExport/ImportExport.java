package com.tecnoappsmobile.managershop_v5.ImportExport;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tecnoappsmobile.managershop_v5.R;

import java.util.ArrayList;

/**
 * Created by DANIEL on 27/11/2016.
 */

public class ImportExport extends AppCompatActivity {
    private RecyclerView recylerSelec;
    private AdaptadorSelectorImpExp adaptadorImpExp;
    private RecyclerView.LayoutManager layoutManIm;

    private ArrayList<ListasSelecciones> lista;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_import_export);


        lista=new ArrayList<ListasSelecciones>();

        // Array de strings para los string-array
        String[] titulo;
        String[] info;

        titulo=getResources().getStringArray(R.array.titulo_import);
        info=getResources().getStringArray(R.array.info_import);

        // Creamos la lista con las selecciones
        lista.add(new ListasSelecciones(titulo[0],info[0],R.drawable.ico_importacin_base_de_datos_peq));
        lista.add(new ListasSelecciones(titulo[1],info[1],R.drawable.ico_importacin_base_de_datos_peq));
        lista.add(new ListasSelecciones(titulo[2],info[2],R.drawable.ico_importacin_base_de_datos_peq));
        lista.add(new ListasSelecciones(titulo[3],info[3],R.drawable.ico_importacin_base_de_datos_peq));

        // Creamos el recyclerView
        recylerSelec=(RecyclerView) findViewById(R.id.recyclerviewSeleccion);
        adaptadorImpExp=new AdaptadorSelectorImpExp(this,lista);
       /* RecyclerView.ItemDecoration itemDecoration =
                new DivideItemDecoration(this, DivideItemDecoration.VERTICAL_LIST);
        recylerSelec.addItemDecoration(); */
        recylerSelec.setAdapter(adaptadorImpExp);
        layoutManIm=new LinearLayoutManager(this);
        recylerSelec.setLayoutManager(layoutManIm);

    }
}
