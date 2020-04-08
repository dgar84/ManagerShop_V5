package com.tecnoappsmobile.managershop_v5;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DANIEL on 04/10/2016.
 */

public class ProductosVector implements Productos {

    protected List<Producto> vectorProductos = ejemploProductos();

    public ProductosVector() {
        vectorProductos = ejemploProductos();
    }

    @Override
    public Producto elemento(int p) {
        return vectorProductos.get(p);
    }

    @Override
    public void addproducto(Producto producto) {
        vectorProductos.add(producto);
    }

    @Override
    public int nuevo(Producto producto) {
        vectorProductos.add(producto);
        return vectorProductos.size()-1;
    }

    @Override
    public void borrar(int d) {
        vectorProductos.remove(d);
    }

    @Override
    public int tamInventario() {
        return vectorProductos.size();
    }

    @Override
    public void actualizarProducto(int id, Producto producto) {
        vectorProductos.set(id, producto);
    }

    public static ArrayList<Producto> ejemploProductos() {
        ArrayList<Producto> productos = new ArrayList<Producto>();
        productos.add(new Producto("8788426722560", "",
                "El gran libro de Android- 5ºEdición","Libro","Libro para aprender a programar en android", 20, 29.6, 25
                , "Otro", 0, 1,
                "Localizado en el almacen 1 estantería nº2",""));
        productos.add(new Producto("9788441536258", "",
                "Java 8- 5ºEdición","Libro","Libro para aprender a programar en Java", 10, 69.3, 58
                , "Otro", 0, 1,
                "Localizado en el almacen 1 estantería nº6",""));
        return productos;
    }
}
