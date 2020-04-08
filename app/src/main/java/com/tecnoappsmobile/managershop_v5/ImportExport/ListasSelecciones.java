package com.tecnoappsmobile.managershop_v5.ImportExport;

/**
 * Created by DANIEL on 27/11/2016.
 */

public class ListasSelecciones {
    private String titulo;
    private String info;
    private int foto;

    public ListasSelecciones(String titulo,String info, int foto){
        this.titulo=titulo;
        this.info=info;
        this.foto=foto;
    }


    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
