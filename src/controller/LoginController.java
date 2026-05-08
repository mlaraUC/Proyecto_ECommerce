package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController implements Initializable {
    
    private Runnable onRegisterRequested;
    private Auth auth;
    
    @FXML
    private TextField email;
    @FXML
    private PasswordField pass;
    @FXML
    private Button iniciarSesion;
    @FXML
    private Label lblRegister;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblRegister.setOnMouseClicked(event -> {
            if (onRegisterRequested != null) {
                onRegisterRequested.run();
            }
        });
    }
    
    public void setAuth(Auth auth) {
        this.auth = auth;
    }
    
    public void setOnRegisterRequested(Runnable onRegisterRequested) {
        this.onRegisterRequested = onRegisterRequested;
    }
    
    @FXML
    private void inicioSesion(ActionEvent event){
        String correo = email.getText();
        String password = pass.getText();
        if(correo.trim().isEmpty() || password.trim().isEmpty()) {
            errorMessage("Correo y contraseña son requeridos");
            return;
        }
        int permisos = auth.validarPermisos(correo, password);
        switch (permisos) {
            case 1:
                cargarVista("/proyecto_ecommerce/admin.fxml", "Admin");
                break;
            
           case 2:
                cargarVista("/proyecto_ecommerce/user.fxml", "User");
                break;
                
            default:
                errorMessage("Email o contraseña incorrectos.");
        }
        System.out.println(auth.getUsuarios().size());
    }
    
    private void cargarVista(String ruta, String titulo) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ruta));
            Stage stage = (Stage) iniciarSesion.getScene().getWindow();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.centerOnScreen();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void errorMessage(String context) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(context);
        alert.showAndWait();
    }
}
