package controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import model.Producto;
import persistence.WishlistFileManager;

public class WishlistController implements Initializable {

    @FXML
    private TilePane tileWishlist;

    private WishlistFileManager wishlistFileManager;
    private List<Producto> listaDeseos;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        wishlistFileManager = new WishlistFileManager();
        listaDeseos = wishlistFileManager.cargar();
        cargarWishlist();
    }

    private void cargarWishlist() {
        tileWishlist.getChildren().clear();

        if (listaDeseos.isEmpty()) {
            Label vacio = new Label("No tienes productos en tu wishlist.");
            vacio.setStyle("-fx-text-fill: #777; -fx-font-size: 14px;");
            tileWishlist.getChildren().add(vacio);
            return;
        }

        for (Producto producto : listaDeseos) {
            tileWishlist.getChildren().add(crearCard(producto));
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
        );
        boton.setOnMouseEntered(e -> boton.setStyle(
                "-fx-background-color: #15325a;"
                + "-fx-text-fill: white;"
                + "-fx-font-weight: bold;"
                + "-fx-font-size: 13px;"
                + "-fx-background-radius: 8;"
                + "-fx-border-radius: 8;"
        ));
        boton.setOnMouseExited(e -> boton.setStyle(
                "-fx-background-color: #1a3f6f;"
                + "-fx-text-fill: white;"
                + "-fx-font-weight: bold;"
                + "-fx-font-size: 13px;"
                + "-fx-background-radius: 8;"
                + "-fx-border-radius: 8;"
        ));

        HBox filaBotones = new HBox(boton);
        filaBotones.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(boton, Priority.ALWAYS);

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
