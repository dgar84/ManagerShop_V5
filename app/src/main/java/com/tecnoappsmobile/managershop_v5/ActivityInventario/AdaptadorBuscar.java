package com.tecnoappsmobile.managershop_v5.ActivityInventario;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tecnoappsmobile.managershop_v5.Producto;
import com.tecnoappsmobile.managershop_v5.Productos;
import com.tecnoappsmobile.managershop_v5.ProductosBD;
import com.tecnoappsmobile.managershop_v5.R;

/**
 * Created by DANIEL on 29/11/2016.
 */

public class AdaptadorBuscar extends RecyclerView.Adapter<AdaptadorBuscar.BuscarViewHolder> implements View.OnClickListener {

    protected Cursor cursor;
    protected Productos productos;
    protected Producto producto;
    protected LayoutInflater inflador;

    private Context contexto;
    private View.OnClickListener listenerBusca;

    public Cursor getCursor() {
        return cursor;
    }

    public void setCursor(Cursor cursor) {
        this.cursor = cursor;
    }


    public AdaptadorBuscar(Context contexto, Productos productos,Cursor cursor){
        //super(contexto,productos);
        this.productos=productos;
        this.contexto=contexto;
       // this.producto=producto;
        this.cursor=cursor;
        inflador=(LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }


    // Creamos el ViewHolder con los elementos
    public static class BuscarViewHolder extends RecyclerView.ViewHolder {
        public TextView nombre,codigoBarras;

        public BuscarViewHolder(View itemView){
            super(itemView);
            codigoBarras=(TextView) itemView.findViewById(R.id.textCodigoBuscar);
            nombre=(TextView) itemView.findViewById(R.id.textNombreBuscar);
        }
    }

    // Creamos el ViewHolder con la vista de un elemento sin personalizar
    @Override
    public BuscarViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Inflamos la vista desde XML
        View v=inflador.inflate(R.layout.layout_buscar_producto_element,parent, false);
        v.setOnClickListener(this);
        return new BuscarViewHolder(v);
    }


    public Producto productoPosicion(int posicion){
        cursor.moveToPosition(posicion);
        return ProductosBD.extraeProducto(cursor);
    }

    // Personalizamos el ViewHolder
    @Override
    public void onBindViewHolder(BuscarViewHolder holder,int posicion){
        Producto producto=productoPosicion(posicion);
        holder.nombre.setText(producto.getNomProducto());
        holder.codigoBarras.setText(producto.getCodigoBarras());
    }

    @Override
    public int getItemCount(){
        return cursor.getCount();
    }


    // Métodos para la captura del item
    public void setOnClickListener(View.OnClickListener listenerBusca){
        this.listenerBusca=listenerBusca;
    }

    @Override
    public void onClick(View view){
        if (listenerBusca!=null){
            listenerBusca.onClick(view);
        }
    }

}
