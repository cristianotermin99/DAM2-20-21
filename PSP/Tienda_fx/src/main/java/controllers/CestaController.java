package controllers;

import config.Configuration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import okhttp3.*;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class CestaController implements Initializable {
    @FXML
    private ListView<String> listCestaConMisProductos;
    PrincipalController principalController;
    Alert alert;

    public void setBorderPane(PrincipalController principalController) {
        this.principalController = principalController;
    }

    public void buttonComprarProductosDeLaCesta(ActionEvent actionEvent) {
    }

    public void buttonAtras(ActionEvent actionEvent) {
    }

    public void buttonLogout(ActionEvent actionEvent) {
    }

    private void alert(String titulo, String texto, Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setTitle(titulo);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    public void cargarProductosCesta(List<String> productosCesta) throws IOException {
            listCestaConMisProductos.getItems().clear();
            listCestaConMisProductos.getItems().addAll(productosCesta);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alert = new Alert(Alert.AlertType.INFORMATION);
    }
}
