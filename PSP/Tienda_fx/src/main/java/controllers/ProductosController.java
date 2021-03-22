package controllers;

import config.Configuration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import okhttp3.*;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class ProductosController implements Initializable {
    @FXML
    private ListView<String> listProductos;
    PrincipalController principalController;
    Alert alert;

    public void setBorderPane(PrincipalController principalController) {
        this.principalController = principalController;
    }

    public void buttonAddProductosALaCesta(ActionEvent actionEvent) throws IOException {
        List<String> productosSeleccionados=listProductos.getSelectionModel().getSelectedItems();
        String url = Configuration.getInstance().getRutaTomcat() + "producto";
        OkHttpClient clientOK = principalController.getOkHttpClient();
        RequestBody formBody = new FormBody.Builder()
                .add("listaProductos", String.valueOf(productosSeleccionados))
                .add("accionProductosCesta", "add")
                .build();


        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response resp = clientOK.newCall(request).execute();

        System.out.println(resp.code());
        System.out.println(resp.message());
        List<String> productos = Arrays.asList(resp.body().string().split(","));
        if (!productos.isEmpty()) {
            principalController.cargarCestaPantalla(productos);
        } else {
            alert("Error de datos", "", Alert.AlertType.WARNING);
        }

        clientOK.connectionPool().evictAll();
    }

    private void alert(String titulo, String texto, Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setTitle(titulo);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    public void cargarTodosLosProductos() throws IOException {
        String url = Configuration.getInstance().getRutaTomcat() + "producto";

        OkHttpClient clientOK = principalController.getOkHttpClient();

        RequestBody formBody = new FormBody.Builder()
                .add("accionProductosCesta", "cargar")
                .build();

        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();

        Response resp = clientOK.newCall(request).execute();
        System.out.println(resp.code());
        System.out.println(resp.message());
        List<String> productos = Arrays.asList(resp.body().string().split(","));
        if (!productos.isEmpty()) {
            listProductos.getItems().clear();
            listProductos.getItems().addAll(productos);
        } else {
            alert("Error de datos", "", Alert.AlertType.WARNING);
        }

        clientOK.connectionPool().evictAll();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alert = new Alert(Alert.AlertType.INFORMATION);
        listProductos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }
}
