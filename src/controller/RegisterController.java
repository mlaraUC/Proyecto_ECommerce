package controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController implements Initializable {
    
    private Runnable onLoginRequested;
    private Auth auth;
    
    @FXML
    private TextField username;
    @FXML
    private TextField email;
    @FXML
    private PasswordField pass;
    @FXML
    private Button register;
    @FXML
    private Label lblSignIn;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lblSignIn.setOnMouseClicked(event -> {
            if (onLoginRequested != null) {
                onLoginRequested.run();
            }
        });
    }
    
    public void setAuth(Auth auth) {
        this.auth = auth;
    }

    public void setOnLoginRequested(Runnable onLoginRequested) {
        this.onLoginRequested = onLoginRequested;
    }
    
    @FXML
    private void registro(ActionEvent event){
        String user = username.getText();
        String correo = email.getText();
        String password = pass.getText();
        if(user.trim().isEmpty() || correo.trim().isEmpty() || password.trim().isEmpty()) {
            errorMessage("Todos los campos son requeridos");
            return;
        }
        auth.crearUsuario(user, correo, password);
        limpiarCampos();
        System.out.println(auth.getUsuarios().size());
    }
    
    private void errorMessage(String context) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(context);
        alert.showAndWait();
    }
    
    private void limpiarCampos() {
        username.setText("");
        email.setText("");
        pass.setText("");
    }
}
