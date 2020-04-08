package com.tecnoappsmobile.managershop_v5;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by DANIEL on 21/09/2016.
 */

public class Inventario extends AppCompatActivity {
  //  public static Productos productos; // Se utiliza la declaración para cuando se almacena tipo vector
    public static ProductosBD productos;
    private int contador;
    private FragmentTabHost tab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.inventario_fragment);
      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar); */

        contador=0;
    //    productos=new ProductosVector();   // Se utiliza para cuando se almacena tipo vector



     /*   ImageView img=(ImageView) findViewById(R.id.imageView);
        img.setImageResource(android.R.drawable.ic_btn_speak_now); */

        // Creamos las pestañas
        tab=(FragmentTabHost) findViewById(R.id.tabHost);
        tab.setup(this,getSupportFragmentManager(),android.R.id.tabcontent);

        tab.addTab(tab.newTabSpec("inventario_layout_add").setIndicator(getString(R.string.tab_edicion)),AddInventario.class, null);
    //    tab.addTab(tab.newTabSpec("inventario_layout_edit").setIndicator(getString(R.string.tab_editar)),EditInventario.class, null);
        tab.addTab(tab.newTabSpec("inventario_lista").setIndicator(getString(R.string.tab_lista)),ListInventario.class, null);


        Bundle bundle=getIntent().getExtras();
        String categoria=getIntent().getStringExtra("categoria");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_inventario, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }




}
