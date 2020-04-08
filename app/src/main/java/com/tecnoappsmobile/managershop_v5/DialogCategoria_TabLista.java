package com.tecnoappsmobile.managershop_v5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by DANIEL on 17/10/2016.
 */

public class DialogCategoria_TabLista extends Fragment implements View.OnClickListener{

    private RecyclerView recyclerViewCategoria;
    public static AdaptadorCategoria adaptadorCategoria;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> categorias;
  //  private String[] categorias
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v=inflater.inflate(R.layout.categoria_lista,container,false);
        recyclerViewCategoria=(RecyclerView) v.findViewById(R.id.recyclerviewCategoria);
      /*  Bundle bundle=getArguments();

        //here is your list array
        categorias=bundle.getStringArray("listCategoria"); */
      //  DialogCategoriaAc act=(DialogCategoriaAc) getActivity();
      //  categorias=act.getCategoria();

        categorias=InitActivity.productos.sacarCategorias();

        Button btnVolverList=(Button) v.findViewById(R.id.btnVolverDialog);
        btnVolverList.setOnClickListener(this);
        return v;
    }

    @Override
    public void onActivityCreated(Bundle state){
        String[] categoriaArray=new String[categorias.size()];
        super.onActivityCreated(state);

        categorias=InitActivity.productos.sacarCategorias();
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerViewCategoria.setLayoutManager(layoutManager);
        Log.d("Size",String.valueOf(categorias.size()));
        for(int i=0;i<categorias.size();i++){
            categoriaArray[i]=categorias.get(i);
        }
        // categoriaArray
        adaptadorCategoria=new AdaptadorCategoria(getContext(),categorias);
        recyclerViewCategoria.addItemDecoration(new DivideItemDecoration(getContext()));
        adaptadorCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),DialogCategoriaAc.class);
                Log.d("Seleccionado en fragme",String.valueOf((long) recyclerViewCategoria.getChildAdapterPosition(v)));
                i.putExtra("id_categoria",(long) recyclerViewCategoria.getChildAdapterPosition(v));
                i.putExtra("visivility",true);
                i.putExtra("categoria",categorias);
                startActivity(i);
               /* Bundle bundle = new Bundle();
                bundle.putLong("id_categoria",(long) recyclerViewCategoria.getChildAdapterPosition(v));
                DialogCategoria_TabAdd ft1 = new DialogCategoria_TabAdd(); */
            }
        });
        recyclerViewCategoria.setAdapter(adaptadorCategoria);

      /*  adaptador.setCursor(MainActivity.fichasBD.extraeCursor());
        adaptador.notifyDataSetChanged(); */


    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnVolverDialog) {
            getActivity().finish();
        }

    }
}
