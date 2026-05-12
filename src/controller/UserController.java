package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class UserController implements Initializable {
    
    @FXML
    private StackPane contentPane;
    @FXML
    private VBox userMenu;
    @FXML
    private HBox userLogout;

    private boolean visible = false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        loadView("/proyecto_ecommerce/shop.fxml");
    }
    
    public void loadView(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
            Parent view = loader.load();
            contentPane.getChildren().setAll(view);
            contentPane.setVisible(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    private void toggleUserMenu() {
        visible = !visible;
        
        userMenu.setVisible(visible);
        userMenu.setManaged(visible);
    }
    
    @FXML
    private void logout() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyecto_ecommerce/proyecto.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) userLogout.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.centerOnScreen();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
