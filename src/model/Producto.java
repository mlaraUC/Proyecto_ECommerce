package model;

import java.io.Serializable;

public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private String categoria;
    private double precio;
    private String imagen;

    public Producto(String nombre, String categoria, double precio, String imagen) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCategoria() {
        return categoria;
    }

    public double getPrecio() {
        return precio;
    }

    public String getImagen() {
        return imagen;
    }
    
    @Override
    public String toString() {
        return "Producto{" +
            "nombre='" + nombre + '\'' +
            ", categoria='" + categoria + '\'' +
            ", precio=" + precio +
            ", imagen='" + imagen + '\'' +
            '}';
    }
}
