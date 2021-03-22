package controllers;

import dao.modelo.Lector;
import dao.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import servicios.ServiciosLectoresCliente;
import servicios.ServiciosUsuarioCliente;


import javax.inject.Inject;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class GestionarCuentaController implements Initializable {

    @FXML
    private Button buttonActualizar;
    @FXML
    private TextField textContraseñaAntigua;
    @FXML
    private TextField textNuevaContraseña;
    @FXML
    private TextField textConfirmarContraseña;
    @FXML
    private TextField textNombreUsuario;
    @FXML
    private TextField textEmail;
    @FXML
    private Label labelNombreUsuario;
    @FXML
    private Label labelCorreo;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelFechaNacimiento;
    @FXML
    private TextField textNombre;
    @FXML
    public DatePicker dateFechaNacimiento;

    Alert alert;

    @Inject
    ServiciosUsuarioCliente serviciosUsuarios;
    @Inject
    ServiciosLectoresCliente serviciosLectores;

    private PrincipalController principalController;


    public void mostrarPantallaSegunUsuario(Usuario usuario) {
        if (usuario.getPrimeraVez() == 1) {
            verModoPrimeraVez();
        } else {
            if (usuario.getTipoUsuario().getIdTipoUsuario() != 3) {
                verModoUser();
            } else {
                habilitarCampos();
            }
        }
    }

    private void verModoUser() {
        buttonActualizar.setVisible(true);
        labelNombreUsuario.setVisible(true);
        labelCorreo.setVisible(true);
        textNombreUsuario.setVisible(true);
        textEmail.setVisible(true);
        labelNombre.setVisible(false);
        labelFechaNacimiento.setVisible(false);
        textNombre.setVisible(false);
        dateFechaNacimiento.setVisible(false);
    }

    private void verModoPrimeraVez() {
        buttonActualizar.setVisible(false);
        labelNombreUsuario.setVisible(false);
        labelCorreo.setVisible(false);
        labelNombre.setVisible(false);
        labelFechaNacimiento.setVisible(false);
        textNombreUsuario.setVisible(false);
        textEmail.setVisible(false);
        textNombre.setVisible(false);
        dateFechaNacimiento.setVisible(false);
    }

    private void habilitarCampos() {
        buttonActualizar.setVisible(true);
        labelNombreUsuario.setVisible(true);
        labelCorreo.setVisible(true);
        labelNombre.setVisible(true);
        labelFechaNacimiento.setVisible(true);
        textNombreUsuario.setVisible(true);
        textEmail.setVisible(true);
        textNombre.setVisible(true);
        dateFechaNacimiento.setVisible(true);
    }

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    public void buttonNuevaPassword(ActionEvent actionEvent) {
        Usuario usuario = principalController.getUser();
        try {
            if (usuario.getPassword().equals(serviciosUsuarios.bytesToHex(textContraseñaAntigua.getText()))) {
                if (textNuevaContraseña.getText().equals(textConfirmarContraseña.getText())) {
                    if (serviciosUsuarios.cambiarContraseña(usuario, textNuevaContraseña.getText()) == 1) {
                        alert("Informacion", "Contraseña actualizada", Alert.AlertType.CONFIRMATION);
                        if (usuario.getTipoUsuario().getIdTipoUsuario() != 3) {
                            verModoUser();
                        } else {
                            habilitarCampos();
                        }
                    }
                } else {
                    alert("Advertencia", "La contraseña nueva y confirmar no coinciden", Alert.AlertType.WARNING);
                }
            } else {
                alert("Advertencia", "La contraseña antigua que has introducido no era correcta", Alert.AlertType.WARNING);
            }
        } catch (Exception e) {
            alert("Error", "Se ha producido un error al cambiar la contraseña" + e, Alert.AlertType.ERROR);
        }

    }

    public void buttonActualizarDatos(ActionEvent actionEvent) {
        Usuario usuario = principalController.getUser();
        String nombreUsuario = textNombreUsuario.getText();
        String email = textEmail.getText();
        if (usuario.getTipoUsuario().getIdTipoUsuario() != 3) {
            if (serviciosUsuarios.actualizarDatos(usuario, nombreUsuario, email) == 1) {
                alert("Informacion", "Datos actualizados", Alert.AlertType.CONFIRMATION);
            }

        } else {
            String nombrePropio = textNombre.getText();
            LocalDate fechaNacimiento = dateFechaNacimiento.getValue();
            if (serviciosLectores.actualizarDatosLector(usuario, nombrePropio, fechaNacimiento) == 1) {
                alert("Informacion", "Datos de lector actualizados", Alert.AlertType.CONFIRMATION);
            }

        }
    }

    public void rellenarDatos(Usuario usuario) {
        if (usuario.getTipoUsuario().getIdTipoUsuario() != 3) {
            rellenarDatosUsuario(usuario);
        } else {
            rellenarDatosLector(serviciosLectores.getLector(usuario));
        }
    }

    private void rellenarDatosUsuario(Usuario usuario) {
        textNombreUsuario.setText(usuario.getUser());
        textEmail.setText(usuario.getMail());

    }

    private void rellenarDatosLector(Lector lector) {

        rellenarDatosUsuario(lector);
        textNombre.setText(((Lector) lector).getNombre());
        dateFechaNacimiento.setValue(((Lector) lector).getBirth());


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
