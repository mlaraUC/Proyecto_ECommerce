package model;

import java.io.Serializable;

public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private String rol;
    private String email;
    private String password;

    public Usuario(String nombre, String rol, String email, String password) {
        this.nombre = nombre;
        this.rol = rol;
        this.email = email;
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRol() {
        return rol;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
    
    @Override
    public String toString() {
        return "Usuario{" + 
            "nombre='" + nombre + 
            "', rol='" + rol + 
            "', email='" + email +
            "', password='" + password +"}";
    }
}
