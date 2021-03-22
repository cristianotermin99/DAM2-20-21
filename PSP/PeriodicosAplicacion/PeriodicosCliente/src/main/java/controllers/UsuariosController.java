package controllers;

import com.github.javafaker.Faker;
import dao.modelo.Lector;
import dao.modelo.TipoUsuario;
import dao.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import servicios.ServiciosLectoresCliente;
import servicios.ServiciosUsuarioCliente;


import javax.inject.Inject;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class UsuariosController implements Initializable {
    @FXML
    private ListView<Usuario> listUsuarios;
    @FXML
    private TextField textNombreUser;
    @FXML
    private TextField textMail;
    @FXML
    private TextField textNombre;
    @FXML
    private Label labelNombre;
    @FXML
    private Label labelFechaNacimiento;
    @FXML
    private DatePicker dateFechaNacimiento;

    Faker faker = new Faker();

    private Alert alertConfirmacion;

    @Inject
    ServiciosUsuarioCliente serviciosUsuarios;
    @Inject
    ServiciosLectoresCliente serviciosLectores;

    Alert alert;
    private PrincipalController principalController;

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    private void alert(String titulo, String texto, Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setTitle(titulo);
        alert.setContentText(texto);
        alert.showAndWait();
    }


    public void buttonAddUser(ActionEvent actionEvent) {

        try {
            TipoUsuario tipoUsuario=new TipoUsuario(3,"Lector", faker.beer().name());
            Lector lector=new Lector(textNombreUser.getText(),
                    faker.artist().name(),
                    textMail.getText(),
                    tipoUsuario,textNombre.getText(),dateFechaNacimiento.getValue());
            String password=lector.getPassword();
            if (serviciosLectores.validarLector(lector).isEmpty()){
                lector=serviciosLectores.addLector(lector);
                if (lector != null) {
                    alert("Informacion", "Te hemos generado una contraseña: " + password, Alert.AlertType.INFORMATION);
                    alert("Informacion", "Nuevo lector añadido", Alert.AlertType.INFORMATION);
                    listUsuarios.getItems().add(lector);
                }else{
                    alert("Error", "Error en la base de datos", Alert.AlertType.ERROR);
                }
            }else{
                alert("Advertencia", "El lector no es valido", Alert.AlertType.WARNING);
            }

        } catch (Exception e) {
            alert("Error", "Error al añadir usuario", Alert.AlertType.ERROR);
        }

    }

    public void buttonDelete(ActionEvent actionEvent) {

        try {
            Usuario usuario = listUsuarios.getSelectionModel().getSelectedItem();
            if (serviciosUsuarios.validarUsuario(usuario).isEmpty()) {
                alertConfirmacion.setContentText("Va a eliminar un lector, si el lector tiene suscripciones tambien desapareceran\n¿Desea continuar?");
                Optional<ButtonType> decision = alertConfirmacion.showAndWait();
                if (decision.get()==ButtonType.OK){
                    if (serviciosUsuarios.deleteUser(usuario) == 1) {
                        alert("Informacion", "Usuario eliminado con exito", Alert.AlertType.INFORMATION);
                        cargarLista();
                    }
                }
            } else {
                alert("Error de datos", serviciosUsuarios.validarUsuario(usuario), Alert.AlertType.WARNING);
            }


        } catch (Exception e) {
            alert("Error", "Error al eliminar", Alert.AlertType.ERROR);
        }
    }

    public void cargarLista() {
        listUsuarios.getItems().clear();
        listUsuarios.getItems().addAll(serviciosUsuarios.getAllUsers());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alertConfirmacion = new Alert(Alert.AlertType.CONFIRMATION);

        alert = new Alert(Alert.AlertType.INFORMATION);
    }
}
