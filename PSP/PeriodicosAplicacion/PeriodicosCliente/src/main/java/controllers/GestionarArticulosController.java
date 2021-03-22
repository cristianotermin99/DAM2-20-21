package controllers;

import dao.modelo.Articulo;
import dao.modelo.Autor;
import dao.modelo.Periodico;
import dao.modelo.TipoArticulo;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import servicios.ServiciosArticulosCliente;
import servicios.ServiciosAutoresCliente;
import servicios.ServiciosPeriodicosCliente;

import javax.inject.Inject;
import java.net.URL;
import java.util.ResourceBundle;

public class GestionarArticulosController implements Initializable {
    @FXML
    private ComboBox<Autor> comboAutores;
    @FXML
    private ComboBox<TipoArticulo> comboTipoArticulos;
    @FXML
    private ListView<Periodico> listPeriodicos;
    @FXML
    private TextField textNombreAutor;
    @FXML
    private TextField textApellidosAutor;

    @FXML
    private ListView<Articulo> listArticulos;

    @FXML
    private TextField textTitularAdd;
    @FXML
    private TextField textDescripcionAdd;
    @FXML
    private TextField textTitularActualizar;
    @FXML
    private TextField textDescripcionActualizar;


    PrincipalController principalController;

    Alert alert;

    @Inject
    ServiciosArticulosCliente serviciosArticulos;
    @Inject
    ServiciosAutoresCliente serviciosAutores;
    @Inject
    ServiciosPeriodicosCliente serviciosPeriodicos;

   public void buttonAddArticulo(ActionEvent actionEvent) {
       try {
            Articulo articulo = new Articulo(textTitularAdd.getText(),
                    textDescripcionAdd.getText(),
                    listPeriodicos.getSelectionModel().getSelectedItem().getId(),
                    comboTipoArticulos.getSelectionModel().getSelectedItem().getId(),
                    comboAutores.getSelectionModel().getSelectedItem().getId());
            articulo = serviciosArticulos.addArticulo(articulo);
            if (serviciosArticulos.validarArticulos(articulo).isEmpty()) {
                if (articulo!=null) {
                    alert("Informacion", "Articulo añadido con exito", Alert.AlertType.INFORMATION);
                    listArticulos.getItems().add(articulo);}
                cargarListaArticulos();
            } else {
                alert("Error de datos", serviciosArticulos.validarArticulos(articulo), Alert.AlertType.WARNING);
            }


        } catch (Exception e) {
            alert("Error", "Error al añadir articulo", Alert.AlertType.ERROR);
        }
    }

    public void buttonDeleteArticulo(ActionEvent actionEvent) {
        try {
            Articulo articulo = listArticulos.getSelectionModel().getSelectedItem();
            if (serviciosArticulos.validarArticulos(articulo).isEmpty()) {
                if (serviciosArticulos.borrarArticulo(articulo) == 1) {
                    alert("Informacion", "Articulo borrado con exito", Alert.AlertType.INFORMATION);
                }else{
                    alert("Error", "Error base de datos", Alert.AlertType.ERROR);
                }
            } else {
                alert("Error de datos", serviciosArticulos.validarArticulos(articulo), Alert.AlertType.WARNING);
            }
            cargarListaArticulos();

        } catch (Exception e) {
            alert("Error", "Error al borrar articulo", Alert.AlertType.ERROR);
        }
    }

    public void buttonActualizar(ActionEvent actionEvent) {
       try {
            Articulo articulo = new Articulo(listArticulos.getSelectionModel().getSelectedItem().getIdArticulo(),
                    textTitularActualizar.getText(),
                    textDescripcionActualizar.getText(),
                    listArticulos.getSelectionModel().getSelectedItem().getIdPeriodico(),
                    listArticulos.getSelectionModel().getSelectedItem().getIdTipoArticulo(),
                    listArticulos.getSelectionModel().getSelectedItem().getIdAutor());
            if (serviciosArticulos.validarArticulos(articulo).isEmpty()) {
                if (serviciosArticulos.actualizarArticulo(articulo) == 1) {
                    alert("Informacion", "Articulo actualizado con exito", Alert.AlertType.INFORMATION);
                }else{
                    alert("Error", "Error base de datos", Alert.AlertType.ERROR);
                }
            } else {
                alert("Error de datos", serviciosArticulos.validarArticulos(articulo), Alert.AlertType.WARNING);
            }
            cargarListaArticulos();

        } catch (Exception e) {
            alert("Error", "Error al actualizar articulo", Alert.AlertType.ERROR);
        }
    }

    public void buttonAddAutor(ActionEvent actionEvent) {
        try {
           Autor autor=new Autor(textNombreAutor.getText(),textApellidosAutor.getText());
            autor=serviciosAutores.insertarAutor(autor);
            if (serviciosAutores.validarAutor(autor).isEmpty()) {
                if (autor!=null) {
                    alert("Informacion", "Autor añadido con exito", Alert.AlertType.INFORMATION);
                    comboAutores.getItems().add(autor);
                }
            } else {
                alert("Error de datos", serviciosAutores.validarAutor(autor), Alert.AlertType.WARNING);
            }

        } catch (Exception e) {
            alert("Error", "Error al actualizar articulo", Alert.AlertType.ERROR);
        }
    }

    public void cargarComboAutores(){
        comboAutores.getItems().clear();
        comboAutores.getItems().addAll(serviciosAutores.getAutores());
    }

    public void cargarComboTipoArticulo(){
        comboTipoArticulos.getItems().clear();
        comboTipoArticulos.getItems().addAll(serviciosArticulos.getTiposArticulos());
    }



    public void cargarListaPeriodicos(){
        listPeriodicos.getItems().clear();
        listPeriodicos.getItems().addAll(serviciosPeriodicos.getAllPeriodicos());
    }

    public void cargarListaArticulos() {
        listArticulos.getItems().clear();
        listArticulos.getItems().addAll(serviciosArticulos.getAllArticulos());
    }

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
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
