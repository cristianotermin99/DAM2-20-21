package controllers;

import config.Configuration;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import modelo.Usuario;
import okhttp3.*;
import servicios.Servicios;


import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    @FXML
    private PasswordField textPassword;
    @FXML
    private TextField textUser;
    PrincipalController principalController;
    Alert alert;

    public void setBorderPane(PrincipalController principalController) {
        this.principalController = principalController;
    }

    public void buttonLogin(ActionEvent actionEvent) throws IOException {
        Servicios servicios = new Servicios();
        Usuario usuario = new Usuario(textUser.getText(), textPassword.getText());

        if (servicios.validarUsuario(usuario).isEmpty()) {
            String url = Configuration.getInstance().getRutaTomcat() + "login";
            CookieManager cookieManager = new CookieManager();
            cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
            principalController.setOkHttpClient(new OkHttpClient.Builder().cookieJar(new JavaNetCookieJar(cookieManager)).build());
            OkHttpClient clientOK = principalController.getOkHttpClient();
            RequestBody formBody = new FormBody.Builder()
                    .add("usuario", textUser.getText())
                    .add("password", textPassword.getText())
                    .build();


            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();

            Response resp = clientOK.newCall(request).execute();

            System.out.println(resp.code());
            System.out.println(resp.message());
            if (resp.body().string().equals("Usuario valido")) {
                principalController.cargarProductosPantalla();
            } else {
                alert("Error de datos", "Usuario no existe", Alert.AlertType.WARNING);
            }

            clientOK.connectionPool().evictAll();
        } else {
            alert("Error de datos", servicios.validarUsuario(usuario), Alert.AlertType.WARNING);
        }


    }

    private void alert(String titulo, String texto, Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setTitle(titulo);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alert = new Alert(Alert.AlertType.INFORMATION);
    }
}
