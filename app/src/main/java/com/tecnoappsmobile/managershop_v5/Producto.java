package com.tecnoappsmobile.managershop_v5;

/**
 * Created by DANIEL on 04/10/2016.
 */

public class Producto {

    private String codigoBarras;
    private String codigoInterno;
    private String nomProducto;
    private String categoria;
    private String descripcion;
    private int unidades;
    private double prec_venta;
    private double prec_mayorista;
    private String tipoDescuento;
    private double numdescuento;
    private int flagdescuento; // 1-> true-> Indica que el descuento es por porcentaje
                                    // 0-> false-> Indica que el descuento se hace directamente al precio
    private String localizacion;

    private String fotoProducto;

    public String getFotoProducto() {
        return fotoProducto;
    }

    public void setFotoProducto(String fotoProducto) {
        this.fotoProducto = fotoProducto;
    }

    public String getCodigoBarras() {
        return codigoBarras;
    }

    public void setCodigoBarras(String codigoBarras) {
        this.codigoBarras = codigoBarras;
    }

    public String getCodigoInterno() {
        return codigoInterno;
    }

    public void setCodigoInterno(String codigoInterno) {
        this.codigoInterno = codigoInterno;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public double getPrec_venta() {
        return prec_venta;
    }

    public void setPrec_venta(double prec_venta) {
        this.prec_venta = prec_venta;
    }

    public double getPrec_mayorista() {
        return prec_mayorista;
    }

    public void setPrec_mayorista(double prec_mayorista) {
        this.prec_mayorista = prec_mayorista;
    }

    public String getTipoDescuento() {
        return tipoDescuento;
    }

    public void setTipoDescuento(String tipoDescuento) {
        this.tipoDescuento = tipoDescuento;
    }

    public double getNumdescuento() {
        return numdescuento;
    }

    public void setNumdescuento(double numdescuento) {
        this.numdescuento = numdescuento;
    }

    public int isFlagdescuento() {
        return flagdescuento;
    }

  /*  public void setFlagdescuento(boolean flagdescuento) {
        this.flagdescuento = flagdescuento;
    }*/
    public void setFlagdescuento(int flagdescuento){
        this.flagdescuento=flagdescuento;
    }
    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

    public Producto(){
       /* unidades=0;
        prec_mayorista=0;
        prec_venta=0;
        tipoDescuento="Otro";
        numdescuento=0;
        flagdescuento=1; */
    }

    public Producto(String codigoBarras, String codigoInterno, String nomProducto, String categoria, String descripcion, int unidades
            , double prec_venta, double prec_mayorista, String tipoDescuento, double numdescuento
            , int flagdescuento, String localizacion, String fotoProducto){
        this.codigoBarras=codigoBarras;
        this.codigoInterno=codigoInterno;
        this.nomProducto=nomProducto;
        this.categoria=categoria;
        this.descripcion=descripcion;
        this.unidades=unidades;
        this.prec_venta=prec_venta;
        this.prec_mayorista=prec_mayorista;
        this.tipoDescuento=tipoDescuento;
        this.numdescuento=numdescuento;
        this.flagdescuento=flagdescuento;
        this.localizacion=localizacion;
        this.fotoProducto=fotoProducto;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "Codigo de barras='" + codigoBarras + '\'' +
                ", Codigo interno='" + codigoInterno + '\'' +
                ", Nombre del producto='" + nomProducto + '\'' +
                ", Categoría=" + categoria +
                ", Descripción='" + descripcion + '\'' +
                ", Unidades=" + unidades +
                ", Precio de venta='" + prec_venta + '\'' +
                ", Precio al mayorista='" + prec_mayorista + '\'' +
                ", Tipo de descuento=" + tipoDescuento +
                ", Número de descuento=" + numdescuento +
                ", flagDescuento=" + flagdescuento +
                ", Localización=" + localizacion +
                ", fotoProducto=" + fotoProducto +
                '}';
    }

}
