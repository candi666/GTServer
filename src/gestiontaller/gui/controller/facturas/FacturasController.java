/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gestiontaller.gui.controller.facturas;

import gestiontaller.App;
import gestiontaller.logic.controller.FacturasManagerTestDataGenerator;
import gestiontaller.logic.interfaces.FacturasManager;
import gestiontaller.logic.javaBean.FacturaBean;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class FacturasController implements Initializable {

    private static final Logger logger = Logger.getLogger(FacturasController.class.getName());
    private Stage stage;
    private Stage ownerStage;
    private FacturasManager facturasLogicController;
    private ObservableList<FacturaBean> facturasData;
    private static final int maxrows = 22;
    private int pageindex;
    private int totalpages;

    // <editor-fold defaultstate="collapsed" desc="@FXML NODES">
    @FXML
    private Button btnPrimero;
    @FXML
    private Button btnAnterior;
    @FXML
    private Button btnSiguiente;
    @FXML
    private Button btnUltimo;
    @FXML
    private Label lblPagina;
    @FXML
    private Button btnPagado;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnAnadir;
    @FXML
    private ComboBox<String> cbFiltro;
    @FXML
    private TextField tfBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private CheckBox chbPendientes;
    @FXML
    private Button btnSalir;
    @FXML
    private TableView<FacturaBean> tvFacturas;
    @FXML
    private TableColumn<FacturaBean, SimpleIntegerProperty> tcId;
    @FXML
    private TableColumn<FacturaBean, SimpleStringProperty> tcFecha;
    @FXML
    private TableColumn<FacturaBean, SimpleStringProperty> tcFechaVenc;
    @FXML
    private TableColumn<FacturaBean, SimpleIntegerProperty> tcIdReparacion;
    @FXML
    private TableColumn<FacturaBean, SimpleIntegerProperty> tcIdCliente;
    @FXML
    private TableColumn<FacturaBean, SimpleDoubleProperty> tcTotal;
    @FXML
    private TableColumn tcPagada;

    // </editor-fold>
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initialStatus();
        InitRowDoubleClickEvent();
        InitButtonListeners();
    }

    /* -----------------------------------------------------------------------*/
 /*                        METODOS DE INICIALIZACIÓN                       */
 /* -----------------------------------------------------------------------*/
    /**
     * Conecta Stage a controlador
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Inicializa la stage
     *
     * @param root Elemento Parent del fxml
     */
    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.setTitle("Gestión de taller - Clientes");
        stage.setResizable(true);

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(ownerStage);
        stage.setOnShowing(this::handleWindowShowing);
        stage.setMaxWidth(1024);
        stage.setMinWidth(748);
        stage.setMaxHeight(1024);
        stage.setMinHeight(748);
        stage.show();

    }

    /**
     * Establece owner stage
     *
     * @param ownerStage
     */
    public void setOwnerStage(Stage ownerStage) {
        this.ownerStage = ownerStage;
    }

    /**
     * Handle on window showing
     *
     * @param event
     */
    private void handleWindowShowing(WindowEvent event) {
        initTable();
    }

    public void setFacturasManager(FacturasManager facturasLogicController) {
        this.facturasLogicController = facturasLogicController;
    }

    /**
     * Establece estado inicial para los elementos de la ventana.
     */
    private void initialStatus() {
        btnPrimero.setDisable(true);
        btnAnterior.setDisable(true);
        lblPagina.setText("0");
        btnEliminar.setDisable(true);
        btnModificar.setDisable(true);
        btnAnadir.setDisable(false);
    }

    /**
     * Formato y carga de datos a tabla.
     */
    private void initTable() {
        pageindex = 1;
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        tcFechaVenc.setCellValueFactory(new PropertyValueFactory<>("fechavenc"));
        tcIdReparacion.setCellValueFactory(new PropertyValueFactory<>("idreparacion"));
        tcIdCliente.setCellValueFactory(new PropertyValueFactory<>("idcliente"));
        tcTotal.setCellValueFactory(new PropertyValueFactory<>("total"));

        /* Definición de columna Pagado.
         * Definir un comportamiento cuando sea true y otro para false.
         * De momento SI o NO, por implementar cambio de color o iconos.
         */
        tcPagada.setSortable(false);
        tcPagada.setCellValueFactory(new PropertyValueFactory<>("pagada"));
        facturasData = FXCollections.observableArrayList(facturasLogicController.getAllFacturas());
        totalpages = facturasData.size() / maxrows;

        tvFacturas.getSelectionModel().selectedItemProperty().addListener(this::handleFacturasTableSelectionChanged);
        //tvFacturas.setItems(facturasData);
        tvFacturas.setItems(FXCollections.observableArrayList(facturasData.subList(0, maxrows)));

        lblPagina.setText(pageindex + " / " + totalpages);
    }

    @FXML
    private void goToPage(int pageindex) {
        this.pageindex = pageindex;
        int fromIndex;
        int toIndex;
        totalpages = facturasData.size() / maxrows;

        if (pageindex == 1) { // Si primera pagina
            fromIndex = 0;
            toIndex = maxrows;

            btnPrimero.setDisable(true);
            btnAnterior.setDisable(true);
            btnSiguiente.setDisable(false);
            btnUltimo.setDisable(false);
        } else if (pageindex == totalpages) { // Si ultima pagina
            fromIndex = pageindex * maxrows;
            toIndex = facturasData.size();

            btnPrimero.setDisable(false);
            btnAnterior.setDisable(false);
            btnSiguiente.setDisable(true);
            btnUltimo.setDisable(true);
        } else { // Resto de paginas
            fromIndex = ((pageindex - 1) * maxrows);
            toIndex = ((pageindex - 1) * maxrows) + maxrows;

            btnSiguiente.setDisable(false);
            btnUltimo.setDisable(false);
            btnPrimero.setDisable(false);
            btnAnterior.setDisable(false);
        }

        tvFacturas.setItems(FXCollections.observableArrayList(facturasData.subList(fromIndex, toIndex)));
        lblPagina.setText(pageindex + " / " + totalpages);

    }

    /* -----------------------------------------------------------------------*/
 /*                            ACCIONES BOTONES                            */
 /* -----------------------------------------------------------------------*/
    /**
     * Acción borrar factura
     */
    @FXML
    private void actionEliminar() {
        facturasData.remove(tvFacturas.getSelectionModel().getSelectedItem());
        tvFacturas.getItems().remove(tvFacturas.getSelectionModel().getSelectedItem());
        totalpages = facturasData.size()/maxrows;

        if (pageindex>1 && tvFacturas.getItems().size()<1) {
            goToPage(pageindex-1);
        }
   
        System.out.println("datasize: " + facturasData.size());
        System.out.println("totalpages: " + totalpages);
        lblPagina.setText(pageindex + " / " + totalpages);
        
        tvFacturas.refresh();

    }

    /**
     * Acción modificar factura
     */
    @FXML
    private void actionModificar() {
        FacturaBean factura = tvFacturas.getSelectionModel().getSelectedItem();
        if (factura != null) {
            loadCrearMod(factura);
        }
    }

    /**
     * Acción crear factura
     */
    @FXML
    private void actionCrear() {
        loadCrearMod(null);
    }

    /**
     * Cambiar estado de la factura
     */
    @FXML
    private void actionPagar() {
        FacturaBean factura = tvFacturas.getSelectionModel().getSelectedItem();
        if (factura != null) {
            if (factura.getPagada()) {
                factura.setPagada(false);
            } else {
                factura.setPagada(true);
            }
            tvFacturas.refresh();
        }
    }

    /**
     * Buscar
     */
    @FXML
    private void actionBuscar() {
        // TODO Implementar busqueda en bases de datos.
        // +++ De momento utilizaremos filter para pruebas.
    }

    /**
     * Cierra stage actual y enfoca el owner stage
     */
    @FXML
    private void actionVolver() {
        stage.close();
        ownerStage.requestFocus();
    }

    /**
     * Carga ventana Crear/Modificar factura. Si pasamos null se abre una
     * ventana para nueva factura. Si le pasamos la factura seleccionada se abre
     * una venatana para modificar.
     *
     * @param factura factura seleccionada en la tabla. Para nueva factura
     * utiizar null.
     */
    private void loadCrearMod(FacturaBean factura) {
        try {
            FacturasManager facturasLogicController = new FacturasManagerTestDataGenerator();
            FXMLLoader loader = new FXMLLoader(App.class.getResource("gui/view/facturas/nueva_factura.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            FacturasCuController ctr = ((FacturasCuController) loader.getController());
            ctr.setStage(new Stage());
            ctr.setFacturasManager(facturasLogicController);
            ctr.setFacturasController(this);

            // En caso de opción Modificar
            if (factura != null) {
                ctr.setFactura(factura);
            }

            ctr.setOwnerStage(stage);
            ctr.initStage(root);
        } catch (IOException ex) {
            ex.printStackTrace();
            logger.log(Level.SEVERE, "Error al cargar ventana nueva_factura.fxml.", ex);
        }
    }

    private void InitButtonListeners() {
        btnSiguiente.setOnAction(e -> goToPage(pageindex + 1));
        btnAnterior.setOnAction(e -> goToPage(pageindex - 1));
        btnPrimero.setOnAction(e -> goToPage(1));
        btnUltimo.setOnAction(e -> goToPage(totalpages));

    }

    /* -----------------------------------------------------------------------*/
 /*                           EVENTOS DE TABLA                             */
 /* -----------------------------------------------------------------------*/
    /**
     * Listener para seleccion en la tabla. Escucha si se ha seleccionado algun
     * elemento
     *
     * @param observable
     * @param oldValue
     * @param newValue
     */
    private void handleFacturasTableSelectionChanged(ObservableValue observable, Object oldValue, Object newValue) {
        if (newValue != null) {
            btnModificar.setDisable(false);
            btnEliminar.setDisable(false);
        } else {
            btnModificar.setDisable(true);
            btnEliminar.setDisable(true);
        }
    }

    /**
     * Accion al hacer doble click sobre row de la tabla
     */
    public void InitRowDoubleClickEvent() {
        tvFacturas.setRowFactory(tv -> {
            TableRow<FacturaBean> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 2 && (!row.isEmpty())) {
                    try {

                        loadCrearMod(tvFacturas.getSelectionModel().getSelectedItem());
                    } catch (Exception ex) {
                        logger.info("Error al cargar ventana nuevo cliente");
                    }

                }
            });
            return row;
        });
    }

    /* -----------------------------------------------------------------------*/
 /*                               MISC                                     */
 /* -----------------------------------------------------------------------*/
    public TableView getTableView() {
        return this.tvFacturas;
    }
}
