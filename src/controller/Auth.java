package controller;

import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class Auth {
    private List<Usuario> usuarios;

    public Auth() {
        this.usuarios = new ArrayList<>();
        usuarios.add(new Usuario("mario@prueba.com", "mario123"));
        usuarios.add(new Usuario("admin@prueba.com", "admin123"));
    }
    
    public boolean validarCredenciales(String email, String pass) {
        if(email==null || pass==null) return false;
        for(Usuario u : usuarios){
            if(u.getEmail().equals(email)){
                return u.getPassword().equals(pass);
            }
        }
        return false;
    }
    
    public int validarPermisos(String email, String pass) {
        boolean isValid = validarCredenciales(email, pass);
        if(!isValid) return -1;
        if(email.equals("admin@prueba.com")) return 1;
        return 2;
    }
}
