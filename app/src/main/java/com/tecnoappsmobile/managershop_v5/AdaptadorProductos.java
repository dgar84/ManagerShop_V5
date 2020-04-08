package com.tecnoappsmobile.managershop_v5;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by DANIEL on 04/10/2016.
 */

public class AdaptadorProductos extends RecyclerView.Adapter<AdaptadorProductos.ViewHolder> {
    protected Productos productos;
    protected LayoutInflater inflador;
    protected Context contexto;

    private View.OnClickListener onClickListener;


    private double valorInventario;


    public double getValorInventario() {
        return valorInventario;
    }

    public void setValorInventario(double valorInventario) {
        this.valorInventario = valorInventario;
    }
    public void setOnClickListener(View.OnClickListener onClickListener){
        this.onClickListener=onClickListener;
    }

    public AdaptadorProductos(Context contexto, Productos productos){
        this.contexto=contexto;
        this.productos=productos;
        inflador=(LayoutInflater) contexto.getSystemService(contexto.LAYOUT_INFLATER_SERVICE);
    }

    // Creamos el ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder{
       // public TextView categoria,descripcion,precio,unidad;
        public TextView nombre,precio,existencias,valor;
        public ViewHolder(View itemView){
            super(itemView);
            nombre=(TextView) itemView.findViewById(R.id.txtElementoNombre);
            precio=(TextView) itemView.findViewById(R.id.txtElementoPrecio) ;
            existencias=(TextView)itemView.findViewById(R.id.txtElementoExistencia);
            valor=(TextView) itemView.findViewById(R.id.txtElementoValor);
         /*   categoria=(TextView)itemView.findViewById(R.id.textCategoria);
            descripcion=(TextView) itemView.findViewById(R.id.textdescpricion);
            precio=(TextView) itemView.findViewById(R.id.textPrecio);
            unidad=(TextView) itemView.findViewById(R.id.textUnidad); */

        }
    }

    // Creamos el ViewHolder con la vista de un elemento sin personalizar
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        // Iflamos la vista
        valorInventario=0;
        View v=inflador.inflate(R.layout.elemento_inventario,parent,false);
        v.setOnClickListener(onClickListener);
        return new ViewHolder(v);
    }

    // Usando como base el ViewHolder con la vista de un elemento
    @Override
    public void onBindViewHolder(ViewHolder holder,int posicion){
        Producto producto=productos.elemento(posicion);
        personalizaVista(holder,producto);
    }

    public void personalizaVista(ViewHolder holder,Producto producto){
        holder.nombre.setText(producto.getNomProducto());
        holder.precio.setText(String.valueOf(producto.getPrec_mayorista()));
        holder.existencias.setText(String.valueOf(producto.getUnidades()));
        holder.valor.setText(String.valueOf(producto.getUnidades()*producto.getPrec_mayorista()));
        valorInventario=valorInventario+(producto.getUnidades()*producto.getPrec_mayorista());
      /*  holder.categoria.setText(producto.getCategoria());
        holder.descripcion.setText(producto.getDescripcion());
        holder.precio.setText(String.valueOf(producto.getPrec_venta()));
        holder.unidad.setText(String.valueOf(producto.getUnidades())); */
    }
    @Override
    public int getItemCount(){
        return productos.tamInventario();
    }
}
