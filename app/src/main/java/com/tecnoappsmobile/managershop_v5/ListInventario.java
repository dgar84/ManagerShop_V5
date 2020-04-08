package com.tecnoappsmobile.managershop_v5;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DANIEL on 04/10/2016.
 */

public class ListInventario extends Fragment {

    private RecyclerView recyclerView;
   // public AdaptadorProductos adaptador;
    public static AdaptadorProductosBD adaptador;
    private RecyclerView.LayoutManager layoutManager;
    private TextView valorText;
    // private Productos productos; // Se utiliza para cuando es un vector
   /// public static ProductosBD productos;
    private Context context;
    private TextView total;

    private Spinner spFiltroCategoria;
    private ArrayAdapter<CharSequence> arrayCategoria;

    private String selCategoria;


    //OnHeadlineSelectedListener mCallback;

    // La actividad contenedora debe implementar esta interfaz
    /* public interface OnHeadlineSelectedListener {
        public void onArticleSelected(long position);
    } */

   /* @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        // Nos aseguramos de que la actividad contenedora haya implementado la
        // interfaz de retrollamada. Si no, lanzamos una excepción
        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " debe implementar OnHeadlineSelectedListener");
        }
    } */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ArrayList<String> listCategoria = new ArrayList<String>(); // Será el array con todas las categorías más una que contemple todas

        // Inicializamos seleccion de categoría
        selCategoria=getContext().getResources().getString(R.string.selecAllCategorias);
        View v = inflater.inflate(R.layout.inventario_lista, container, false);
        recyclerView=(RecyclerView) v.findViewById(R.id.recyclerview);
     //   productos=new ProductosVector();    // Se utiliza para cuando se usan los vectores para almacenar

        Log.d("Tag ListInventario","Estoy dentro de create Base de datos");
     ///   productos=new ProductosBD(getContext(),"DBProductos",null,1);

        // Calcular el valor total
        total=(TextView) v.findViewById(R.id.textPercioTotal);
        total.setText(String.valueOf(InitActivity.productos.calculoValorTotal()));

        spFiltroCategoria = (Spinner) v.findViewById(R.id.spFiltroCategoria);

        // Poblar el filtro de categorías
        //String[]
        ArrayList<String> listCategoriaSQL=InitActivity.productos.sacarCategorias();
        for(int i=0;i<listCategoriaSQL.size()+1;i++){
            if(i==0){
                listCategoria.add(getContext().getResources().getString(R.string.selecAllCategorias));
            } else {
                listCategoria.add(i, listCategoriaSQL.get(i - 1));
            }
        }

        ArrayAdapter<CharSequence> dataAdapter =  new ArrayAdapter(getContext(),android.R.layout.simple_spinner_item, listCategoria);

      //  arrayCategoria= ArrayAdapter.createFromResource(getContext(),R.array.spCategoria,android.R.layout.simple_spinner_item);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spFiltroCategoria.setAdapter(dataAdapter);

        spFiltroCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapter, View view,
                                       int posicion, long id) {
               // Aquí se codifica la lógica que se ejecutará al seleccionar un elemento del Spinner.
                selCategoria = adapter.getItemAtPosition(posicion).toString();
                Log.d("Se ha seleccionado",selCategoria);
                adaptador=new AdaptadorProductosBD(getContext(),InitActivity.productos,InitActivity.productos.extraeCursor(selCategoria));

                // Volvemos a calcular el total
                total.setText(String.valueOf(InitActivity.productos.calculoValorTotal()));

                /* Extra */
                adaptador.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i=new Intent(getContext(),Inventario.class);
                        i.putExtra("id",(long) recyclerView.getChildAdapterPosition(v));
                        Log.d("se selecionado", String.valueOf((long) recyclerView.getChildAdapterPosition(v)));
                        startActivity(i);
                    }
                });
                recyclerView.setAdapter(adaptador);
            }


            @Override
            public void onNothingSelected(AdapterView<?> adapter) {
                }
        });
        Log.d("Tras salir",selCategoria);
        adaptador=new AdaptadorProductosBD(getContext(),InitActivity.productos,InitActivity.productos.extraeCursor(selCategoria));


   /*  Cambio     productos=new ProductosVector();

        adaptador=new AdaptadorProductos(v.getContext(),productos);
        recyclerView.setAdapter(adaptador);
        layoutManager=new LinearLayoutManager(v.getContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addItemDecoration(new DivideItemDecoration(getContext()));
        valorText=(TextView)v.findViewById(R.id.textPercioTotal);
        valorText.setText(String.valueOf(adaptador.getValorInventario()));   */
       /* RecyclerView.ItemDecoration itemDecoration =
                new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST);
        myRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        recyclerView.addItemDecoration(itemDecoration); */
       /* adaptador.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                mCallback.onArticleSelected((long) recyclerView.getChildAdapterPosition(v));
            }
        }); */
        return v;

    }

    @Override
    public void onActivityCreated(Bundle state){
        super.onActivityCreated(state);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DivideItemDecoration(getContext()));
      //  layoutManager.setAutoMeasureEnabled(true);
    //    productos=new ProductosVector();

    //    adaptador=new AdaptadorProductos(getContext(),productos);  // Se utiliza para cuando es en vector


        // Esto sobra
     /*   adaptador=new AdaptadorProductosBD(getContext(),productos,productos.extraeCursor(selCategoria));
        adaptador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getContext(),Inventario.class);
                i.putExtra("id",(long) recyclerView.getChildAdapterPosition(v));
                Log.d("se selecionado",String.valueOf((long) recyclerView.getChildAdapterPosition(v)));
                startActivity(i);
            }
        }); */
        recyclerView.setAdapter(adaptador);
    }

}
