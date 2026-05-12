package controller;

import java.util.ArrayList;
import java.util.List;
import model.Usuario;
import persistence.UsuarioFileManager;

public class Auth {
    private List<Usuario> usuarios;
    private UsuarioFileManager fileManager;

    public Auth() {
        this.fileManager = new UsuarioFileManager();
        List<Usuario> cargados = fileManager.cargar();
        
        if (cargados != null && !cargados.isEmpty()) {
        this.usuarios = cargados;
        } else {
            this.usuarios = new ArrayList<>();
            usuarios.add(new Usuario("Mario Lara", "cliente", "mario@prueba.com", "mario123"));
            usuarios.add(new Usuario("Admin", "admin", "admin@prueba.com", "admin123"));
            usuarios.add (new Usuario("Luis Perez", "cliente", "luis@prueba.com", "luis321"));
            
            for (Usuario u : usuarios) {
                fileManager.guardarUsuario(u);
            }
        }
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
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
        Usuario user = encontrarUsuario(email);
        if(user.getRol().equals("admin")) return 1;
        return 2;
    }
    
    private Usuario encontrarUsuario(String email) {
        Usuario user = null;
        for(Usuario u : usuarios){
            if(u.getEmail().equals(email)){
                user = u;
            }
        }
        return user;
    }
    
    public void crearUsuario(String user, String rol, String email, String pass){
        Usuario newUser = new Usuario(user, rol, email, pass);
        usuarios.add(newUser);
        fileManager.guardarUsuario(newUser);
    }
}
