package com.tecnoappsmobile.managershop_v5;

import android.app.Fragment;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tecnoappsmobile.managershop_v5.ImportExport.ImportExport;

/**
 * Created by DANIEL on 28/09/2016.
 */

public class Fragment_Principal extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_principal,container);

        // Botón de acceso a ventana de inventario
        Button btnInventario=(Button) v.findViewById(R.id.btn_inventario);
        btnInventario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),Inventario.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    startActivity(i);
                }
            }
        });

       Button btnImport=(Button) v.findViewById(R.id.btn_ImportExport);
        btnImport.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i=new Intent(getContext(), ImportExport.class);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                    startActivity(i);
                }
            }
        });
        return v;
    }
}