package controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import okhttp3.OkHttpClient;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {
    Alert alert;

    @FXML
    private BorderPane borderPaneInicio;

    private AnchorPane pantallaLogin;
    private LoginController loginController;

    private AnchorPane pantallaCesta;
    private CestaController cestaController;

    private AnchorPane pantallaProductos;
    private ProductosController productosController;

    OkHttpClient okHttpClient;

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public void setOkHttpClient(OkHttpClient okHttpClient) {
        this.okHttpClient = okHttpClient;
    }

    public void cargarLoginPantalla() {
        try {
            if (pantallaLogin == null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pantallaLogin.fxml"));
                pantallaLogin = loader.load();
                loginController = loader.getController();
                loginController.setBorderPane(this);
                borderPaneInicio.setCenter(pantallaLogin);
            }
            borderPaneInicio.setCenter(pantallaLogin);
        } catch (Exception e) {
            alert("Error al cargar pantalla login", "" + e, Alert.AlertType.ERROR);
        }
    }

    public void cargarCestaPantalla(List<String> productosCesta) {
        try {
            if (pantallaCesta == null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pantallaCesta.fxml"));
                pantallaCesta = loader.load();
                cestaController = loader.getController();
                cestaController.setBorderPane(this);
                borderPaneInicio.setCenter(pantallaCesta);
            }
            cestaController.cargarProductosCesta(productosCesta);
            borderPaneInicio.setCenter(pantallaCesta);
        } catch (Exception e) {
            alert("Error al cargar pantalla cesta", "" + e, Alert.AlertType.ERROR);
        }
    }

    public void cargarProductosPantalla() {
        try {
            if (pantallaProductos == null) {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/pantallaProductos.fxml"));
                pantallaProductos = loader.load();
                productosController = loader.getController();
                productosController.setBorderPane(this);
                borderPaneInicio.setCenter(pantallaProductos);
            }
            productosController.cargarTodosLosProductos();
            borderPaneInicio.setCenter(pantallaProductos);
        } catch (Exception e) {
            alert("Error al cargar pantalla productos", "" + e, Alert.AlertType.ERROR);
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
        cargarLoginPantalla();
    }
}
