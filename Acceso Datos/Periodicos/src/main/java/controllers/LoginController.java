package controllers;

import dao.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import servicios.ServiciosLectores;
import servicios.implServicios.ServiciosUsuariosImpl;
import servicios.implServicios.ServiciosLectoresImpl;
import servicios.ServiciosUsuarios;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    Alert alert;
    @FXML
    private TextField textUser;
    @FXML
    private TextField textPassword;

    private PrincipalController principalController;

    @Inject
    ServiciosUsuarios serviciosUsuarios;

    public LoginController() {

    }

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    public void buttonReset(ActionEvent actionEvent) {
        textUser.setText("");
        textPassword.setText("");
    }

    public void clearCampos() {
        textUser.setText("");
        textPassword.setText("");
    }


    public boolean buttonLogin(ActionEvent actionEvent) {
        try {
            if (textUser.getText().isEmpty() || textPassword.getText().isEmpty()) {
                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setContentText("Te has dejado algun campo sin rellenar");
                a.showAndWait();

            } else {
                Usuario user = serviciosUsuarios.comprobarUsuario(textUser.getText(), textPassword.getText());

                if (user != null) {
                    principalController.setUser(user);

                    if (user.getPrimeraVez() == 1) {
                        principalController.cargarPantallaGestionarCuenta();
                        alert("Advertencia", "Al ser la primera vez que accedes, te obligo a que cambies tu contraseña", Alert.AlertType.INFORMATION);
                    } else {
                        principalController.cargarBienvenida();
                    }

                } else {
                    alert("Error", "Datos erroneos", Alert.AlertType.ERROR);
                }
            }
        } catch (Exception e) {
            alert("Error", "Se ha producido un error al logear" + e, Alert.AlertType.ERROR);
        }
        return false;
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
