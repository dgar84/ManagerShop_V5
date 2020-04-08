package com.tecnoappsmobile.managershop_v5;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class InitActivity extends AppCompatActivity {
    public static ProductosBD productos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_init);

        // Creamos la base de datos
        // Base de datos
        // productos=new ProductosBD(this);
        productos=new ProductosBD(this,"DBProductos",null,1);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        productos.close();
    }
}
