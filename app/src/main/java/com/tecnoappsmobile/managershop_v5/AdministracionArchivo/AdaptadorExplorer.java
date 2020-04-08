package com.tecnoappsmobile.managershop_v5.AdministracionArchivo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tecnoappsmobile.managershop_v5.Archivos;
import com.tecnoappsmobile.managershop_v5.R;

import java.util.ArrayList;


/**
 * Created by danie on 08/12/2016.
 */

public class AdaptadorExplorer extends RecyclerView.Adapter<AdaptadorExplorer.ExplorerViewHolder> {
    protected LayoutInflater inflador;
    protected Context contexto;
    protected View.OnClickListener onClickListener;
    protected ArrayList<Archivos> archivos;


    public AdaptadorExplorer(Context contexto, ArrayList<Archivos> archivos){
        this.contexto=contexto;
        this.archivos=archivos;
        inflador=(LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    // Creamos el viewHolder
    public static class ExplorerViewHolder extends RecyclerView.ViewHolder{
        public TextView archivo,ruta;
        public ImageView icono;

        public ExplorerViewHolder(View itemView){
            super(itemView);
            icono=(ImageView) itemView.findViewById(R.id.imagenExplorer);
            archivo=(TextView) itemView.findViewById(R.id.nombArchivo);
            ruta=(TextView) itemView.findViewById(R.id.nomruta);
        }
    }

    @Override
    public ExplorerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflamos la vista con el elemento de la vista
        View v=inflador.inflate(R.layout.layout_explorer_archivos_item,parent,false);
        v.setOnClickListener(onClickListener);
        return new ExplorerViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ExplorerViewHolder holder, int position) {
        Archivos archivo=archivos.get(position); // guardamos un objeto

        if(archivo!=null){
            holder.ruta.setText(archivo.getRutaArchivo());
            holder.archivo.setText(archivo.getNameFile());

            // Comprobación de que es archivo o directorio
            if(archivo.isFlagDir()){
                holder.icono.setImageResource(R.drawable.carpeta);
            } else {
                holder.icono.setImageResource(R.drawable.archivo);
            }
        }
    }

    @Override
    public int getItemCount() {
        return archivos.size();
    }

    public void setOnRutaClickListener(View.OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }

    // Capturamos el Archivo seleccionado
    public Archivos getArchivo(int i){
        return archivos.get(i);
    }
}

