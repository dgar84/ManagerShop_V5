package com.tecnoappsmobile.managershop_v5;

import android.content.Context;
import android.database.Cursor;

/**
 * Created by DANIEL on 05/11/2016.
 */

public class AdaptadorProductosBD extends AdaptadorProductos {
    protected Cursor cursor;

    public AdaptadorProductosBD(Context contexto, Productos productos, Cursor cursor){
        super(contexto,productos);
        this.cursor=cursor;
    }

    public Cursor getCursor(){
        return cursor;
    }

    public void setCursor(Cursor cursor){
        this.cursor=cursor;
    }

    public Producto productoPosicion(int posicion){
        cursor.moveToPosition(posicion);
        return ProductosBD.extraeProducto(cursor);
    }

    public int idPosicion(int posicion){
        cursor.moveToPosition(posicion);
        return cursor.getInt(0);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder,int posicion){
        Producto producto=productoPosicion(posicion);
      //  double valor=ProductosBD.calculoValorTotal();
        personalizaVista(holder,producto);
    }
    @Override
    public int getItemCount(){
        return cursor.getCount();
    }
}
