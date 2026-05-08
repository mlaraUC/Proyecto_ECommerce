package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.layout.StackPane;

public class ProyectoController implements Initializable {
    
    private Auth auth;
    
    @FXML
    private StackPane contentPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        auth = new Auth();
        showLogin();
    }
    
    public void showLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyecto_ecommerce/login.fxml"));
            Parent view = loader.load();

            LoginController loginController = loader.getController();
            loginController.setOnRegisterRequested(this::showRegister);
            loginController.setAuth(auth);

            contentPane.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void showRegister() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/proyecto_ecommerce/register.fxml"));
            Parent view = loader.load();
            
            RegisterController registerController = loader.getController();
            registerController.setOnLoginRequested(this::showLogin);
            registerController.setAuth(auth);
            
            contentPane.getChildren().setAll(view);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}