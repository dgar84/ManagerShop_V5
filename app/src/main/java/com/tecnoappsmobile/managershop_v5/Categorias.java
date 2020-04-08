package com.tecnoappsmobile.managershop_v5;

import java.util.ArrayList;

/**
 * Created by DANIEL on 24/11/2016.
 */

public interface Categorias {
    String elementCategoria(int p);
    int addCategoria(String name);
   // int nuevo(); // Añade el producto al inventario y devuelve el id
    boolean borrarCategoria(String deleteCat); // borrar la categoria y saca el string de la categoría
                                    // en caso de que existan productos con esa categoría.
                                    // True: si se puede borrar
                                    // False: si no se puede borrar
    int tamCategoria();
    void actualizarCategoria(int id, String name);
    ArrayList<String> sacarCategorias();
}
