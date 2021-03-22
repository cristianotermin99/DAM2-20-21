package controllers;

import com.github.javafaker.Faker;
import dao.modelo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import servicios.*;
import servicios.implServicios.*;

import javax.inject.Inject;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class SuscripcionesController implements Initializable {

    @FXML
    private ListView<Periodico> listPeriodicos;
    @FXML
    private ListView<Suscripcion> listMisSuscripciones;
    @FXML
    private ListView<Articulo> listArticulosDeUnPeriodico;
    @FXML
    private AnchorPane anchorPantallaArticulo;
    @FXML
    private Label textTitular;
    @FXML
    private Label textDescripcion;
    Alert alert;
    private PrincipalController principalController;
    Faker faker;

    @Inject
    ServiciosPeriodicos serviciosPeriodicos;
    @Inject
    ServiciosLectores serviciosLectores;
    @Inject
    ServiciosSuscripciones serviciosSuscripciones;
    @Inject
    ServiciosArticulos serviciosArticulos;
    @Inject
    ServiciosArticulosLeidos serviciosArticulosLeidos;

    Lector lector;

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    private void alert(String titulo, String texto, Alert.AlertType type) {
        alert.setAlertType(type);
        alert.setTitle(titulo);
        alert.setContentText(texto);
        alert.showAndWait();
    }

    public void buttonSuscribirse(ActionEvent actionEvent) {
        try {
            lector = serviciosLectores.getLector(principalController.getUser());
            Suscripcion suscripcion = new Suscripcion(lector.getIdLector(),
                    listPeriodicos.getSelectionModel().getSelectedItem().getId(),
                    LocalDate.now(), null);
            if (serviciosSuscripciones.validarSuscripcion(suscripcion).isEmpty()) {
                if (serviciosSuscripciones.insertarSuscripcion(suscripcion) == 1) {
                    alert("Informacion", "Suscripcion realizada", Alert.AlertType.INFORMATION);
                } else {
                    alert("Error", "Error base de datos", Alert.AlertType.ERROR);
                }
            } else {
                alert("Error de datos", serviciosSuscripciones.validarSuscripcion(suscripcion), Alert.AlertType.WARNING);
            }
            cargarMisSuscripciones();

        } catch (Exception e) {
            alert("Error", "Error al actualizar articulo", Alert.AlertType.ERROR);
        }
    }

    public void buttonBorrarSuscripcion(ActionEvent actionEvent) {
        try {
            lector = serviciosLectores.getLector(principalController.getUser());
            Suscripcion suscripcion = new Suscripcion(listMisSuscripciones.getSelectionModel().getSelectedItem().getIdSuscripcion(),
                    lector.getIdUsuario(),
                    listMisSuscripciones.getSelectionModel().getSelectedItem().getIdPeriodico(),
                    null, LocalDate.now());
            if (serviciosSuscripciones.validarSuscripcion(suscripcion).isEmpty()) {
                if (serviciosSuscripciones.desuscribirse(suscripcion) == 1) {
                    alert("Informacion", "Te has dado de baja de este periodico", Alert.AlertType.INFORMATION);
                } else {
                    alert("Error", "Error base de datos", Alert.AlertType.ERROR);
                }
            } else {
                alert("Error de datos", serviciosSuscripciones.validarSuscripcion(suscripcion), Alert.AlertType.WARNING);
            }
            cargarMisSuscripciones();

        } catch (Exception e) {
            alert("Error", "Error al actualizar articulo", Alert.AlertType.ERROR);
        }
    }

    public void buttonLeerArticulo(ActionEvent actionEvent) {

        try {
            faker = new Faker();
            lector = serviciosLectores.getLector(principalController.getUser());
            ArticuloLeido articuloLeido = new ArticuloLeido(lector.getIdUsuario(),
                    listArticulosDeUnPeriodico.getSelectionModel().getSelectedItem().getIdArticulo(),
                    faker.random().nextInt(0, 10));
            if (serviciosArticulosLeidos.validarArticuloLeido(articuloLeido).isEmpty()) {
                mostrarArticulo(listArticulosDeUnPeriodico.getSelectionModel().getSelectedItem().getTitular(),
                        listArticulosDeUnPeriodico.getSelectionModel().getSelectedItem().getDescripcion());
                if (serviciosArticulosLeidos.comprobarArticuloLeido(articuloLeido) == 0) {
                    serviciosArticulosLeidos.leerArticulo(articuloLeido);
                } else if (serviciosArticulosLeidos.comprobarArticuloLeido(articuloLeido) == 1){
                    articuloLeido.setRating(faker.random().nextInt(0,10));
                    serviciosArticulosLeidos.actualizarRatingArticulo(articuloLeido);
                }else{
                    alert("Error", "Error base de datos", Alert.AlertType.ERROR);

                }
            } else {
                alert("Error de datos", serviciosArticulosLeidos.validarArticuloLeido(articuloLeido), Alert.AlertType.WARNING);
            }
            cargarMisSuscripciones();

        } catch (Exception e) {
            alert("Error", "Error al actualizar articulo", Alert.AlertType.ERROR);
        }
    }

    public void buttonCerrarArticulo(ActionEvent actionEvent) {
        anchorPantallaArticulo.setVisible(false);
    }

    public void cargarAllPeriodicos() {
        listPeriodicos.getItems().clear();
        listPeriodicos.getItems().setAll(serviciosPeriodicos.getAllPeriodicos());
    }

    public void cargarMisSuscripciones() {
        lector = serviciosLectores.getLector(principalController.getUser());
        listMisSuscripciones.getItems().clear();
        listMisSuscripciones.getItems().addAll(serviciosSuscripciones.getSuscripcionesLector(lector));
    }

    private void mostrarArticulo(String titular, String descripcion) {
        anchorPantallaArticulo.setVisible(true);
        textTitular.setText(titular);
        textDescripcion.setText(descripcion);
    }


    public void suscripcionSeleccionada(MouseEvent mouseEvent) {
        listArticulosDeUnPeriodico.getItems().clear();
        listArticulosDeUnPeriodico.getItems().addAll(
                serviciosArticulos.getArticulosDeUnPeriodico(
                        listMisSuscripciones.getSelectionModel().getSelectedItem().getIdPeriodico()));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        alert = new Alert(Alert.AlertType.INFORMATION);
    }


}
