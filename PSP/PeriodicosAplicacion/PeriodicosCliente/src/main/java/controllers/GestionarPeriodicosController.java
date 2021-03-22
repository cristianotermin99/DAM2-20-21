package controllers;

import com.github.javafaker.Faker;
import dao.modelo.Periodico;
import dao.modelo.TipoUsuario;
import dao.modelo.Usuario;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import servicios.ServiciosArticulosCliente;
import servicios.ServiciosPeriodicosCliente;
import servicios.ServiciosUsuarioCliente;


import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionarPeriodicosController implements Initializable {
    @FXML
    private TextField textAdminAdd;
    @FXML
    private TextField textMailAdd;
    @FXML
    private ListView<Periodico> listPeriodicos;
    @FXML
    private TextField textNombreActualizar;
    @FXML
    private TextField textDirectorActualizar;
    @FXML
    private TextField textPrecioActualizar;
    @FXML
    private TextField textNombreAdd;
    @FXML
    private TextField textPrecioAdd;
    @FXML
    private TextField textDirectorAdd;

    Faker faker = new Faker();

    private PrincipalController principalController;
    @Inject
    ServiciosPeriodicosCliente serviciosPeriodicos;
    @Inject
    ServiciosUsuarioCliente serviciosUsuarios;
    @Inject
    ServiciosArticulosCliente serviciosArticulos;

    Alert alert;

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    private void alert(String titulo, String texto, Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setTitle(titulo);
        alert.setContentText(texto);
        alert.showAndWait();
    }


    public void buttonBorrarPeriodico(ActionEvent actionEvent) {
        try {
            Periodico periodico = listPeriodicos.getSelectionModel().getSelectedItem();
            if (serviciosPeriodicos.validarPeriodico(periodico).isEmpty()) {
                if (serviciosArticulos.getArticulosDeUnPeriodico(periodico.getId())!=null) {
                    if (serviciosPeriodicos.borrarPeriodico(periodico) == 1) {
                        alert("Informacion", "Periodico eliminado con exito", Alert.AlertType.INFORMATION);
                        cargarListaPeriodicos();
                    }
                } else {
                    alert("Advertencia", "No puedes eliminar un periodico que tenga articulos", Alert.AlertType.WARNING);
                }

            } else {
                alert("Error de datos", serviciosPeriodicos.validarPeriodico(periodico), Alert.AlertType.WARNING);
            }


        } catch (Exception e) {
            alert("Error", "Error al eliminar", Alert.AlertType.ERROR);
        }
    }

    public void buttonActualizar(ActionEvent actionEvent) {
        try {
            Periodico periodico = new Periodico(listPeriodicos.getSelectionModel().getSelectedItem().getId(),
                    textNombreActualizar.getText(),
                    Double.parseDouble(textPrecioActualizar.getText()),
                    textDirectorActualizar.getText(), 2);
            if (serviciosPeriodicos.validarPeriodico(periodico).isEmpty()) {
                if (serviciosPeriodicos.actualizarDatosPeriodico(periodico) == 1) {
                    alert("Informacion", "Periodico actualizado con exito", Alert.AlertType.INFORMATION);
                    cargarListaPeriodicos();
                }
            } else {
                alert("Error de datos", serviciosPeriodicos.validarPeriodico(periodico), Alert.AlertType.WARNING);
            }

        } catch (Exception e) {
            alert("Error", "Error al actualizar", Alert.AlertType.ERROR);
        }

    }

    public void buttonAddPeriodico(ActionEvent actionEvent) {
        try {

            String password = faker.name().firstName();
            TipoUsuario tipoUsuario = new TipoUsuario(2, "Administrador periodicos", faker.beer().name());
            Usuario usuarioNuevo = new Usuario(textAdminAdd.getText(),
                    password,
                    textMailAdd.getText(), tipoUsuario);

            if (serviciosUsuarios.validarUsuario(usuarioNuevo).isEmpty()) {
                usuarioNuevo = serviciosUsuarios.insertarAdmin(usuarioNuevo);
                if (usuarioNuevo != null) {
                    alert("Informacion", "Te hemos generado una contraseña: " + password, Alert.AlertType.INFORMATION);
                    alert("Informacion", "Nuevo administrador de periodico añadido", Alert.AlertType.INFORMATION);
                    Periodico periodico = new Periodico(textNombreAdd.getText(),
                            Double.parseDouble(textPrecioAdd.getText()),
                            textDirectorAdd.getText(),
                            usuarioNuevo.getIdUsuario());
                    if (serviciosPeriodicos.validarPeriodico(periodico).isEmpty()) {
                        if (serviciosPeriodicos.insertarPeriodico(periodico) == 1) {
                            alert("Informacion", "Periodico insertado con exito", Alert.AlertType.INFORMATION);
                            cargarListaPeriodicos();
                        } else {
                            alert("Error", "Error en la base de datos", Alert.AlertType.ERROR);
                        }
                    } else {
                        alert("Error de datos", serviciosPeriodicos.validarPeriodico(periodico), Alert.AlertType.WARNING);
                    }
                } else {
                    alert("Error", "Error en la base de datos", Alert.AlertType.ERROR);
                }
            } else {
                alert("Error de datos", serviciosUsuarios.validarUsuario(usuarioNuevo), Alert.AlertType.WARNING);
            }


        } catch (Exception e) {
            alert("Error", "Error al crear", Alert.AlertType.ERROR);
        }
    }

    public void cargarListaPeriodicos() {
        listPeriodicos.getItems().clear();
        listPeriodicos.getItems().addAll(serviciosPeriodicos.getAllPeriodicos());
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alert = new Alert(Alert.AlertType.INFORMATION);
    }
}
