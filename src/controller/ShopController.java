package controller;

import javafx.scene.image.Image;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import model.Producto;
import persistence.ProductoFileManager;

public class ShopController implements Initializable {
    
    @FXML
    private TilePane tileProductos;
    
    private ProductoFileManager fileManager;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fileManager = new ProductoFileManager();
        cargarProductos();
    }

    private void cargarProductos() {
        tileProductos.getChildren().clear();

        List<Producto> productos = fileManager.cargar();
        
        if(productos.isEmpty()) {
            productos = List.of(
                new Producto("Audífonos Over-Ear Studio", "AUDIO", 149.99, "/images/products/audifonos.png"),
                new Producto("Reloj Analógico Clásico", "WATCHES", 89.99, "/images/products/reloj.png"),
                new Producto("Sofá 3 Plazas Beige", "FURNITURE", 749.00, "/images/products/sofa.png"),
                new Producto("Tenis Deportivos Mujer", "FOOTWEAR", 79.99, "/images/products/tenis.png"),
                new Producto("Cámara Réflex Digital", "PHOTOGRAPHY", 349.99, "/images/products/camara.png"),
                new Producto("Anillo Plata con Gema", "JEWELRY", 119.99, "/images/products/anillo.png")
            );
            fileManager.guardarTodos(productos);
        }

        for (Producto producto : productos) {
            tileProductos.getChildren().add(crearCard(producto));
        }
    }
    
    private VBox crearCard(Producto producto) {
        Image imagenProducto = new Image(getClass().getResourceAsStream(producto.getImagen())) {};
        ImageView imagen = new ImageView(imagenProducto);
        imagen.setFitWidth(160);
        imagen.setFitHeight(160);
        imagen.setPreserveRatio(true);

        Label categoria = new Label(producto.getCategoria());
        categoria.setStyle("-fx-font-size: 10px; -fx-text-fill: #777;");

        Label nombre = new Label(producto.getNombre());
        nombre.setWrapText(true);
        nombre.setStyle("-fx-font-weight: bold;");

        Label precio = new Label("$" + producto.getPrecio());
        precio.setStyle("-fx-font-size: 14px;");

        Button boton = new Button("Add to Cart");
        boton.setMaxWidth(Double.MAX_VALUE);
        boton.cursorProperty().setValue(Cursor.HAND);

        VBox card = new VBox(8, imagen, categoria, nombre, precio, boton);
        card.setPrefWidth(180);
        String estiloBase =
            "-fx-background-color: white;" +
            "-fx-padding: 10;" +
            "-fx-background-radius: 12;" +
            "-fx-border-radius: 12;" +
            "-fx-border-color: #e5e5e5;";
        card.setStyle(estiloBase);
        
        card.setOnMouseEntered(e -> {
            card.setStyle(
                "-fx-background-color: white;" +
                "-fx-padding: 10;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-color: #ddd;" +
                "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0, 0, 4);"
            );
        });

        card.setOnMouseExited(e -> {
            card.setStyle(estiloBase);
        });

        return card;
    }
}