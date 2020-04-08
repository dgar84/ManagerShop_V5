package com.tecnoappsmobile.managershop_v5.ImportExport;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tecnoappsmobile.managershop_v5.R;

import java.util.ArrayList;

/**
 * Created by DANIEL on 26/11/2016.
 */

public class AdaptadorSelectorImpExp extends RecyclerView.Adapter<AdaptadorSelectorImpExp.MenuViewHolder> {

    protected LayoutInflater inflador;
    protected Context contexto;

    protected ArrayList<ListasSelecciones> datos;

    public AdaptadorSelectorImpExp(Context contexto,ArrayList<ListasSelecciones> datos){
        this.contexto=contexto;
        this.datos=datos;
        inflador=(LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // Creamos el viewHolder
    public static class MenuViewHolder extends RecyclerView.ViewHolder{
        public TextView titulo,info;
        public ImageView icono;

        public MenuViewHolder(View itemView){
            super(itemView);
            icono=(ImageView) itemView.findViewById(R.id.ico_import_db);
            titulo=(TextView) itemView.findViewById(R.id.import_tit);
            info=(TextView) itemView.findViewById(R.id.import_info);
        }
    }

    // Creamos el view holder sin personalizar
    @Override
    public MenuViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Inflamos la vista con el elemento de la vista
        View v=inflador.inflate(R.layout.layout_import_export_element,parent,false);
        return new MenuViewHolder(v);
    }

    @Override
    public void onBindViewHolder(MenuViewHolder holder,int posicion){
        ListasSelecciones item=datos.get(posicion);

        holder.icono.setImageResource(item.getFoto());
        holder.titulo.setText(item.getTitulo());
        holder.info.setText(item.getInfo());

    }

    // número de items
    @Override
    public int getItemCount(){
        return datos.size();
    }

}
