package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class AdminController implements Initializable {
    
    @FXML
    private StackPane contentPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadView("/proyecto_ecommerce/dashboard.fxml");
    }    
    
    public void loadView(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent view = loader.load();
            contentPane.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
