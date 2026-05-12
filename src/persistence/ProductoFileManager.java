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
import model.Producto;

public class ProductoFileManager {
    private static final String ARCHIVO = "productos.dat";
    
    public void guardarProducto(Producto producto) {
        File archivo = new File(ARCHIVO);
        boolean existe = archivo.exists();
        try (ObjectOutputStream oos = existe
                ? new AppendableObjectOutputStream(new FileOutputStream(archivo, true))
                : new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(producto);
        } catch (IOException e) {
            System.err.println("Error al guardar producto: " + e.getMessage());
        }
    }
    
    public List<Producto> cargar() {
        File archivo = new File(ARCHIVO);
        if (!archivo.exists()) return new ArrayList<>();
        List<Producto> lista = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            while (true) {
                lista.add((Producto) ois.readObject());
            }
        } catch (EOFException e) {
            // fin del archivo, normal
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error al cargar productos: " + e.getMessage());
        }
        return lista;
    }
    
    public void guardarTodos(List<Producto> productos) {
        File archivo = new File(ARCHIVO);
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            for (Producto p : productos) {
                oos.writeObject(p);
            }
        } catch (IOException e) {
            System.err.println("Error al guardar productos: " + e.getMessage());
        }
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
