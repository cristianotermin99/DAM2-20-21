package controllers;

import dao.modelo.Usuario;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class BienvenidaController implements Initializable {
    Alert alert;
    @FXML
    private Label textoBienvenida;

    private PrincipalController principalController;



    public void bienvenidaTexto(Usuario user){
        textoBienvenida.setText("Bienvenid@ "+user.getUser());
    }


    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alert = new Alert(Alert.AlertType.INFORMATION);
    }


}
