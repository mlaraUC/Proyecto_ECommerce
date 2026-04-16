package controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class ProyectoController implements Initializable {
    
    private Auth auth;
    @FXML
    private TextField email;
    @FXML
    private PasswordField pass;
    @FXML
    private Button iniciarSesion;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        auth = new Auth();
    }
    
    @FXML
    private void inicioSesion(ActionEvent event){
        String correo = email.getText();
        String password = pass.getText();
        int permisos = auth.validarPermisos(correo, password);
        switch (permisos) {
            case 1:
                cargarVista("/proyecto_ecommerce/admin.fxml", "Admin");
                break;
            
           case 2:
                 JOptionPane.showMessageDialog(null,"Bienvenido.","Incio Sesion Correcto",JOptionPane.INFORMATION_MESSAGE);
                break;
                
            default:
                JOptionPane.showMessageDialog(null,"Email o contraseña incorrectos.","Error de autenticación",JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void cargarVista(String ruta, String titulo) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(ruta));
            Stage stage = (Stage) iniciarSesion.getScene().getWindow();
            stage.setTitle(titulo);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}