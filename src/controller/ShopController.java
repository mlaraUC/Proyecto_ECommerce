package controller;

import javafx.scene.image.Image;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.SVGPath;
import model.Producto;
import persistence.ProductoFileManager;
import persistence.WishlistFileManager;

public class ShopController implements Initializable {
    
    @FXML
    private TilePane tileProductos;
    
    private ProductoFileManager fileManager;
    private WishlistFileManager wishlistFileManager;
    private List<Producto> listaDeseos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        fileManager = new ProductoFileManager();
        wishlistFileManager = new WishlistFileManager();
        listaDeseos = wishlistFileManager.cargar();
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
        Image imagenProducto = new Image(getClass().getResourceAsStream(producto.getImagen()));
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

        final double ALTURA_BOTON = 34;

        Button boton = new Button("Add to Cart");
        boton.setMaxWidth(Double.MAX_VALUE);
        boton.setPrefHeight(ALTURA_BOTON);
        boton.setCursor(Cursor.HAND);
        boton.setStyle(
                "-fx-background-color: #1a3f6f;"
                + "-fx-text-fill: white;"
                + "-fx-font-weight: bold;"
                + "-fx-font-size: 13px;"
                + "-fx-background-radius: 8;"
                + "-fx-border-radius: 8;"
                + "-fx-cursor: hand;"
        );

        boton.setOnMouseEntered(e -> boton.setStyle(
                "-fx-background-color: #15325a;"
                + "-fx-text-fill: white;"
                + "-fx-font-weight: bold;"
                + "-fx-font-size: 13px;"
                + "-fx-background-radius: 8;"
                + "-fx-border-radius: 8;"
                + "-fx-cursor: hand;"
        ));
        boton.setOnMouseExited(e -> boton.setStyle(
                "-fx-background-color: #1a3f6f;"
                + "-fx-text-fill: white;"
                + "-fx-font-weight: bold;"
                + "-fx-font-size: 13px;"
                + "-fx-background-radius: 8;"
                + "-fx-border-radius: 8;"
                + "-fx-cursor: hand;"
        ));
        HBox.setHgrow(boton, Priority.ALWAYS);

        String svgPath
                = "M10 18.35L8.55 17.05C6.86667 15.5333 5.475 14.225 4.375 13.125C3.275 12.025 2.4 11.0375 "
                + "1.75 10.1625C1.1 9.2875 0.645833 8.48333 0.3875 7.75C0.129167 7.01667 0 6.26667 0 5.5C0 "
                + "3.93333 0.525 2.625 1.575 1.575C2.625 0.525 3.93333 0 5.5 0C6.36667 0 7.19167 0.183333 "
                + "7.975 0.55C8.75833 0.916667 9.43333 1.43333 10 2.1C10.5667 1.43333 11.2417 0.916667 "
                + "12.025 0.55C12.8083 0.183333 13.6333 0 14.5 0C16.0667 0 17.375 0.525 18.425 1.575C19.475 "
                + "2.625 20 3.93333 20 5.5C20 6.26667 19.8708 7.01667 19.6125 7.75C19.3542 8.48333 18.9 "
                + "9.2875 18.25 10.1625C17.6 11.0375 16.725 12.025 15.625 13.125C14.525 14.225 13.1333 "
                + "15.5333 11.45 17.05L10 18.35ZM10 15.65C11.6 14.2167 12.9167 12.9875 13.95 11.9625C14.9833 "
                + "10.9375 15.8 10.0458 16.4 9.2875C17 8.52917 17.4167 7.85417 17.65 7.2625C17.8833 6.67083 "
                + "18 6.08333 18 5.5C18 4.5 17.6667 3.66667 17 3C16.3333 2.33333 15.5 2 14.5 2C13.7167 2 "
                + "12.9917 2.22083 12.325 2.6625C11.6583 3.10417 11.2 3.66667 10.95 4.35H9.05C8.8 3.66667 "
                + "8.34167 3.10417 7.675 2.6625C7.00833 2.22083 6.28333 2 5.5 2C4.5 2 3.66667 2.33333 3 3C"
                + "2.33333 3.66667 2 4.5 2 5.5C2 6.08333 2.11667 6.67083 2.35 7.2625C2.58333 7.85417 3 "
                + "8.52917 3.6 9.2875C4.2 10.0458 5.01667 10.9375 6.05 11.9625C7.08333 12.9875 8.4 14.2167 10 15.65Z";

        SVGPath iconoCorazon = new SVGPath();
        iconoCorazon.setContent(svgPath);
        iconoCorazon.setFill(Color.TRANSPARENT);
        iconoCorazon.setStroke(Color.web("#6b8cba"));
        iconoCorazon.setStrokeWidth(0.8);
        iconoCorazon.setScaleX(0.75);
        iconoCorazon.setScaleY(0.75);
        
        if (listaDeseos.contains(producto)) {
            iconoCorazon.setFill(Color.web("#e74c3c"));
            iconoCorazon.setStroke(Color.web("#e74c3c"));
        } else {
            iconoCorazon.setFill(Color.TRANSPARENT);
            iconoCorazon.setStroke(Color.web("#6b8cba"));
        }
        iconoCorazon.setStrokeWidth(0.8);

        StackPane contenedorIcono = new StackPane(iconoCorazon);
        contenedorIcono.setAlignment(Pos.CENTER);
        contenedorIcono.setPrefSize(20, 20);

        String estiloFavBase
                = "-fx-background-color: #dce8f5;"
                + "-fx-background-radius: 8;"
                + "-fx-border-radius: 8;"
                + "-fx-padding: 0;"
                + "-fx-cursor: hand;";

        String estiloFavHover
                = "-fx-background-color: #c8d9ed;"
                + "-fx-background-radius: 8;"
                + "-fx-border-radius: 8;"
                + "-fx-padding: 0;"
                + "-fx-cursor: hand;";

        Button btnFavorito = new Button();
        btnFavorito.setGraphic(contenedorIcono);
        btnFavorito.setCursor(Cursor.HAND);
        btnFavorito.setPrefWidth(ALTURA_BOTON);
        btnFavorito.setPrefHeight(ALTURA_BOTON);
        btnFavorito.setMinWidth(ALTURA_BOTON);
        btnFavorito.setMaxHeight(ALTURA_BOTON);
        btnFavorito.setStyle(estiloFavBase);

        btnFavorito.setOnMouseEntered(e -> btnFavorito.setStyle(estiloFavHover));
        btnFavorito.setOnMouseExited(e -> btnFavorito.setStyle(estiloFavBase));

        btnFavorito.setOnAction(e -> {
            boolean estaActivo = iconoCorazon.getFill().equals(Color.web("#e74c3c"));
            if (estaActivo) {
                iconoCorazon.setFill(Color.TRANSPARENT);
                iconoCorazon.setStroke(Color.web("#6b8cba"));
                listaDeseos.remove(producto);
            } else {
                iconoCorazon.setFill(Color.web("#e74c3c"));
                iconoCorazon.setStroke(Color.web("#e74c3c"));
                listaDeseos.add(producto);
            }
            wishlistFileManager.guardarTodos(listaDeseos);
        });

        HBox filaBotones = new HBox(6, boton, btnFavorito);
        filaBotones.setAlignment(Pos.CENTER_LEFT);

        String estiloBase
                = "-fx-background-color: white;"
                + "-fx-padding: 10;"
                + "-fx-background-radius: 12;"
                + "-fx-border-radius: 12;"
                + "-fx-border-color: #e5e5e5;";

        VBox card = new VBox(8, imagen, categoria, nombre, precio, filaBotones);
        card.setPrefWidth(180);
        card.setStyle(estiloBase);

        card.setOnMouseEntered(e -> card.setStyle(
                "-fx-background-color: white;"
                + "-fx-padding: 10;"
                + "-fx-background-radius: 12;"
                + "-fx-border-radius: 12;"
                + "-fx-border-color: #ddd;"
                + "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 10, 0, 0, 4);"
        ));
        card.setOnMouseExited(e -> card.setStyle(estiloBase));

        return card;
    }
}