package com.tecnoappsmobile.managershop_v5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by DANIEL on 17/10/2016.
 */

public class AdaptadorCategoria extends RecyclerView.Adapter<AdaptadorCategoria.ViewHolder> {
   // protected Categorias productos;
    protected LayoutInflater inflador;
    protected Context contexto;

   protected ArrayList<String> categoriasList;
    protected String[] categorias;
    private View.OnClickListener onClickListener;

    private String catego;

   // private SparseBooleanArray selectedItems; // Para marcar en un reciclerView


    public AdaptadorCategoria(Context contexto,ArrayList<String> categoriasList){
        this.contexto=contexto;
     //   this.productos=productos;
       // this.categorias=categorias;
        this.categoriasList=categoriasList;
        inflador=(LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
      //  selectedItems=new SparseBooleanArray();
    }

    // Creamos el ViewHolder
    public class ViewHolder extends RecyclerView.ViewHolder {  // Ponemos el implements View.OnClickListener
        public TextView categoria;
        public Button btnDeleteCategoria;
     //   public LinearLayout selec_categoria;

        public ViewHolder(View itemView) {
            super(itemView);
            categoria = (TextView) itemView.findViewById(R.id.textDialogCategoria);
            btnDeleteCategoria=(Button) itemView.findViewById(R.id.btnDeleteCategoria);

          //  selec_categoria=(LinearLayout) itemView.findViewById(R.id.selec_categoria);
        }

     /*   @Override
        public void onClick(View v) {
            if(selectedItems.get(getAdapterPosition(),false)){
                v.setSelected(false);
            } else {
                selectedItems.put(getAdapterPosition(),true);
                v.setSelected(true);
            }
        }*/
    }

    // Creamos el ViewHolder con la vista de un elemento sin personalizar
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Iflamos la vista
        View v=inflador.inflate(R.layout.categoria_elemento,parent,false);
        v.setOnClickListener(onClickListener);
        return new ViewHolder(v);
    }

    // Usando como base el ViewHolder con la vista de un elemento
    @Override
    public void onBindViewHolder(ViewHolder holder, final int posicion){
      /*  catego=categorias[posicion];

        for (int i=0;i<categorias.length;i++){
            categoriasList.add(i,categorias[i]);
        }

        Log.d("Categoria",catego);
        Log.d("List 0",categorias[0]);
        Log.d("List 1",categorias[1]); */
        Log.d("Elemento Categoria",categoriasList.get(posicion));
        catego=categoriasList.get(posicion);
        holder.btnDeleteCategoria.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                /// button click event
                Log.d("Posicion","Dentro");
                categoriasList.remove(posicion);
            }

        }
        );
        personalizarVista(holder,catego);
      //  holder.selec_categoria.setSelected(selectedItems.get(posicion,false));
    }

    public void personalizarVista(ViewHolder holder,String catego){
        holder.categoria.setText(catego);

    }
    @Override
    public int getItemCount(){
        Log.d("categoria",String.valueOf(categoriasList.size()));
        return categoriasList.size();
    }
    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }


}
