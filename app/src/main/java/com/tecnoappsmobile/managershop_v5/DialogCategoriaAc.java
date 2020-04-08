package com.tecnoappsmobile.managershop_v5;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by DANIEL on 19/10/2016.
 */

public class DialogCategoriaAc extends AppCompatActivity {
    private FragmentTabHost tabs;
    private ArrayList<String> listCategoria= new ArrayList<String>();
    // private String[] categoriaArray;
    private long idSeleccionado;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.categoria_tabs);

        // Creamos las pestañas
        tabs=(FragmentTabHost) findViewById(R.id.tabHostCategoria);
        tabs.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);

        tabs.addTab(tabs.newTabSpec("dialog_tab_add_categoria").setIndicator(getString(R.string.tab_edicion)),DialogCategoria_TabAdd.class,null);
        tabs.addTab(tabs.newTabSpec("categoria_lista").setIndicator(getString(R.string.tab_lista)),DialogCategoria_TabLista.class,null);





/* FUNCIONA */
      Bundle bundle=getIntent().getExtras();
        if (bundle!=null) {
            Log.d("FALLO","Vuelvo a estar dentro");
            listCategoria = bundle.getStringArrayList("categoria");
            Log.d("Elemento categoria:", listCategoria.get(0));
            Log.d("Elemento categoria:", listCategoria.get(1));
            idSeleccionado=bundle.getLong("id_categoria");
        }



     /*   Bundle ft1_bund = new Bundle();
        ft1_bund.putStringArrayList("listCategoria",listCategoria);
        DialogCategoria_TabAdd tabAddCategoria=new DialogCategoria_TabAdd();
        tabAddCategoria.setArguments(ft1_bund); */




    }
    public ArrayList<String> getCategoria(){
        return listCategoria;
    }


    @Override
    public void onResume()
    {
        super.onResume();
        int width=getResources().getDimensionPixelSize(R.dimen.dialog_body_width);
        int height=getResources().getDimensionPixelSize(R.dimen.dialog_body_height);
        getWindow().setLayout(width,height);
    }
}
