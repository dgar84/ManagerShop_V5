package com.tecnoappsmobile.managershop_v5;

/**
 * Created by DANIEL on 04/10/2016.
 */

public interface Productos {
        Producto elemento(int p);
        void addproducto(Producto producto);
        int nuevo(Producto producto); // Añade el producto al inventario y devuelve el id
        void borrar(int d);
        int tamInventario();
        void actualizarProducto(int id, Producto producto);
}
