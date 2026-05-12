package persistence;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UsuarioFileManager {
    private static final String ARCHIVO = "usuarios.dat";
    
    public void guardarUsuario(Usuario usuario) {
        File archivo = new File(ARCHIVO);
        boolean existe = archivo.exists();

        try (ObjectOutputStream oos = existe
                ? new AppendableObjectOutputStream(new FileOutputStream(archivo, true))
                : new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(usuario);
        } catch (IOException e) {
            System.err.println("Error al guardar usuario: " + e.getMessage());
        }
    }
    
    public List<Usuario> cargar() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return null;

        List<Usuario> lista = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            while (true) {
                lista.add((Usuario) ois.readObject());
            }
        } catch (EOFException e) {
            
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }

        return lista;
    }
    
    private static class AppendableObjectOutputStream extends ObjectOutputStream {
        public AppendableObjectOutputStream(OutputStream out) throws IOException {
            super(out);
        }

        @Override
        protected void writeStreamHeader() throws IOException {
            reset();
        }
    }
}
