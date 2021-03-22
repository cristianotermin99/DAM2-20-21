package controllers;

import dao.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import lombok.SneakyThrows;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class PrincipalController implements Initializable {
    Alert alert;
    @FXML
    private MenuBar menuBar;
    @FXML
    private Menu menuGestionUsuarios;
    @FXML
    private MenuItem menuItemGestionarCuenta;
    @FXML
    private MenuItem menuItemGestionarUsuarios;
    @FXML
    private Menu menuGestionPeriodicos;
    @FXML
    private MenuItem menuItemGestionarPeriodicos;
    @FXML
    private MenuItem menuItemGestionarArticulos;
    @FXML
    private Menu menuSuscripciones;
    @FXML
    private MenuItem menuItemVerSuscripciones;
    @FXML
    private Menu menuSalir;
    @FXML
    private MenuItem menuItemCerrarSesion;
    @FXML
    private BorderPane borderPaneInicio;

    @Inject
    FXMLLoader fxmlLoaderBienvenida;
    private BienvenidaController bienvenidaController;
    private AnchorPane bienvenidaPantalla;

    @Inject
    FXMLLoader fxmlLoaderLogin;
    private LoginController loginController;
    private AnchorPane loginPantalla;

    @Inject
    FXMLLoader fxmlLoaderUsuarios;
    private UsuariosController usuariosController;
    private AnchorPane usuariosPantalla;

    @Inject
    FXMLLoader fxmlLoaderGestionarCuenta;
    private GestionarCuentaController gestionarCuentaController;
    private AnchorPane gestionarCuentaPantalla;

    @Inject
    FXMLLoader fxmlLoaderGestionarPeriodicos;
    private GestionarPeriodicosController gestionarPeriodicosController;
    private TabPane gestionarPeriodicosPantalla;

    @Inject
    FXMLLoader fxmlLoaderGestionarArticulos;
    private GestionarArticulosController gestionarArticulosController;
    private TabPane gestionarArticulosPantalla;

    @Inject
    FXMLLoader fxmlLoaderGestionarSuscripciones;
    private SuscripcionesController gestionarSuscripcionesController;
    private TabPane gestionarSuscripcionesPantalla;

    private Usuario user;

    public void setUser(Usuario user) {
        this.user = user;
    }

    public Usuario getUser() {
        return user;
    }

    @SneakyThrows
    public void cargarLoginPantalla() {
        try {
            if (loginPantalla == null) {
                loginPantalla=fxmlLoaderLogin.load(getClass().getResourceAsStream("/fxml/login.fxml"));
                loginController = fxmlLoaderLogin.getController();
                loginController.setPrincipalController(this);
                borderPaneInicio.setCenter(loginPantalla);
            }
            if (menuBar.isVisible()) {
                menuBar.setVisible(false);
            }
            loginController.clearCampos();
            borderPaneInicio.setCenter(loginPantalla);
        } catch (Exception e) {
            alert("Error", "Se ha producido un error al cargar Login" + e, Alert.AlertType.ERROR);
        }

    }

    @SneakyThrows
    public void cargarBienvenida() {
        try {
            if (bienvenidaPantalla == null) {
                bienvenidaPantalla=fxmlLoaderBienvenida.load(getClass().getResourceAsStream("/fxml/pantallaBienvenida.fxml"));
                bienvenidaController = fxmlLoaderBienvenida.getController();
                bienvenidaController.setPrincipalController(this);
                borderPaneInicio.setCenter(bienvenidaPantalla);
            }
            bienvenidaController.bienvenidaTexto(user);
            visibleMenuBar();
            borderPaneInicio.setCenter(bienvenidaPantalla);
        } catch (Exception e) {
            alert("Error", "Se ha producido un error al cargar bienvenida" + e, Alert.AlertType.ERROR);
        }
    }

    @SneakyThrows
    public void cargarPantallaGestionarCuenta() {
        try {
            if (gestionarCuentaPantalla == null) {
                gestionarCuentaPantalla=fxmlLoaderGestionarCuenta.load(getClass().getResourceAsStream("/fxml/gestionarCuenta.fxml"));
                gestionarCuentaController = fxmlLoaderGestionarCuenta.getController();
                gestionarCuentaController.setPrincipalController(this);
                borderPaneInicio.setCenter(gestionarCuentaPantalla);
            }
            gestionarCuentaController.mostrarPantallaSegunUsuario(user);
            gestionarCuentaController.rellenarDatos(user);
            borderPaneInicio.setCenter(gestionarCuentaPantalla);
        } catch (Exception e) {
            alert("Error", "Se ha producido un error al cargar gestionar cuentas" + e, Alert.AlertType.ERROR);
        }

    }

    private void visibleMenuBar() {
        menuBar.setVisible(true);
        habilitarMenu();
        switch (user.getTipoUsuario().getIdTipoUsuario()) {
            case 1:
                menuSuscripciones.setVisible(false);
                break;
            case 2:
                menuItemGestionarUsuarios.setVisible(false);
                break;
            case 3:
                menuItemGestionarUsuarios.setVisible(false);
                menuGestionPeriodicos.setVisible(false);
                break;
        }
    }


    @SneakyThrows
    public void cargarPantallaUsuarios() {

        try {
            if (usuariosPantalla == null) {
                usuariosPantalla=fxmlLoaderUsuarios.load(getClass().getResourceAsStream("/fxml/pantallaUsuarios.fxml"));
                usuariosController = fxmlLoaderUsuarios.getController();
                usuariosController.setPrincipalController(this);
                borderPaneInicio.setCenter(usuariosPantalla);
            }
            usuariosController.cargarLista();
            borderPaneInicio.setCenter(usuariosPantalla);
        } catch (Exception e) {
            alert("Error", "Se ha producido un error al cargar pantalla gestionar usuarios" + e, Alert.AlertType.ERROR);
        }

    }

    @SneakyThrows
    public void cargarPantallaGestionPeriodicos() {

        try {
            if (gestionarPeriodicosPantalla == null) {
                gestionarPeriodicosPantalla=fxmlLoaderGestionarPeriodicos.load(getClass().getResourceAsStream("/fxml/pantallaGestionarPeriodicos.fxml"));
                gestionarPeriodicosController = fxmlLoaderGestionarPeriodicos.getController();
                gestionarPeriodicosController.setPrincipalController(this);
                borderPaneInicio.setCenter(gestionarPeriodicosPantalla);
            }
            gestionarPeriodicosController.cargarListaPeriodicos();
            borderPaneInicio.setCenter(gestionarPeriodicosPantalla);
        } catch (Exception e) {
            alert("Error", "Se ha producido un error al cargar pantalla gestionar periodicos" + e, Alert.AlertType.ERROR);
        }

    }

    @SneakyThrows
    public void cargarPantallaGestionArticulos() {

        try {
            if (gestionarArticulosPantalla == null) {
                gestionarArticulosPantalla=fxmlLoaderGestionarArticulos.load(getClass().getResourceAsStream("/fxml/pantallaGestionarArticulos.fxml"));
                gestionarArticulosController = fxmlLoaderGestionarArticulos.getController();
                gestionarArticulosController.setPrincipalController(this);
                borderPaneInicio.setCenter(gestionarArticulosPantalla);
            }
            gestionarArticulosController.cargarListaPeriodicos();
            gestionarArticulosController.cargarListaArticulos();
            gestionarArticulosController.cargarComboAutores();
            gestionarArticulosController.cargarComboTipoArticulo();
            borderPaneInicio.setCenter(gestionarArticulosPantalla);
        } catch (Exception e) {
            alert("Error", "Se ha producido un error al cargar pantalla gestionar usuarios" + e, Alert.AlertType.ERROR);
        }

    }

    @SneakyThrows
    public void cargarPantallaSuscripciones() {

        try {
            if (gestionarSuscripcionesPantalla == null) {
                gestionarSuscripcionesPantalla=fxmlLoaderGestionarSuscripciones.load(getClass().getResourceAsStream("/fxml/pantallaSuscripciones.fxml"));
                gestionarSuscripcionesController = fxmlLoaderGestionarSuscripciones.getController();
                gestionarSuscripcionesController.setPrincipalController(this);
                borderPaneInicio.setCenter(gestionarSuscripcionesPantalla);
            }
            gestionarSuscripcionesController.cargarAllPeriodicos();
            gestionarSuscripcionesController.cargarMisSuscripciones();
            borderPaneInicio.setCenter(gestionarSuscripcionesPantalla);
        } catch (Exception e) {
            alert("Error", "Se ha producido un error al cargar pantalla gestionar usuarios" + e, Alert.AlertType.ERROR);
        }

    }


    private void habilitarMenu() {
        menuBar.getMenus().forEach(menu -> {
            menu.setVisible(true);
            menu.getItems().forEach(item -> item.setVisible(true));
        });
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
        menuBar.setVisible(false);

    }

    public void buttonMenuItemGestionarCuentas(ActionEvent actionEvent) {
        cargarPantallaGestionarCuenta();
    }

    public void buttonMenuItemGestioarUsuarios(ActionEvent actionEvent) {
        cargarPantallaUsuarios();
    }

    public void buttonMenuItemGestionarPeriodicos(ActionEvent actionEvent) {
        cargarPantallaGestionPeriodicos();
    }

    public void buttonMenuItemGestionarArticulos(ActionEvent actionEvent) {
        cargarPantallaGestionArticulos();
    }

    public void buttonMenuItemVerSuscripciones(ActionEvent actionEvent) {
        cargarPantallaSuscripciones();
    }

    public void buttonMenuItemCerrarSesion(ActionEvent actionEvent) {
        cargarLoginPantalla();
    }
}
