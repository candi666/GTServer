package gestiontaller.gui.controller.clientes;

import gestiontaller.App;
import gestiontaller.gui.controller.HomeController;
import gestiontaller.logic.controller.ClientesManagerTestDataGenerator;
import gestiontaller.logic.interfaces.ClientesManager;
import gestiontaller.logic.bean.ClienteBean;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Carlos
 */
public class ClientesController implements Initializable {
    private static final Logger logger = Logger.getLogger(ClientesController.class.getName());
    private Stage stage;
    private Stage ownerStage;
    private ClientesManager businessLogicController;

    // <editor-fold defaultstate="collapsed" desc="@FXML NODES">
    @FXML
    private TableView<ClienteBean> tvClientes;
    @FXML
    private TableColumn tcId;
    @FXML
    private TableColumn tcDni;
    @FXML
    private TableColumn tcNombre;
    @FXML
    private TableColumn tcApellidos;
    @FXML
    private TableColumn tcEmail;
    @FXML
    private TableColumn tcTelefono;
    @FXML
    private Button btnPrimero;
    @FXML
    private Button btnAnterior;
    @FXML
    private Button btnSiguiente;
    @FXML
    private Button btnUltimo;
    @FXML
    private Button btnHistorial;
    @FXML
    private Button btnModificar;
    @FXML
    private Button btnEliminar;
    @FXML
    private Button btnAnadir;
    @FXML
    private ComboBox<?> cbFiltro;
    @FXML
    private TextField tfBuscar;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnSalir;
    

    // </editor-fold>
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnHistorial.setDisable(true);
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
        
    }

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
        initContextMenu();
        //tcId.setText(HomeController.bundle.getString("app.gui.clientes.title"));
        
    }

    public void setClientesManager(ClientesManager businessLogicController) {
        this.businessLogicController=businessLogicController;
    }
    
    public void initTable(){
        tcId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tcDni.setCellValueFactory(new PropertyValueFactory<>("dni"));
        tcNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tcApellidos.setCellValueFactory(new PropertyValueFactory<>("apellidos"));
        tcEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        tcTelefono.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        tvClientes.getSelectionModel().selectedItemProperty().addListener(this::handleClientesTableSelectionChanged);
        
        ObservableList<ClienteBean> clientesData = FXCollections.observableArrayList(businessLogicController.getAllClientes());
        
        tvClientes.setItems(clientesData);
    }
    
    public void initContextMenu(){
        final ContextMenu cm = new ContextMenu();
        MenuItem cmItem1 = new MenuItem("Eliminar");
        MenuItem cmItem2 = new MenuItem("Modificar");
        
        //ContextMenu Eliminar
        cmItem1.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                actionEliminar();
            }
        });
        cm.getItems().add(cmItem1);
        tvClientes.addEventHandler(MouseEvent.MOUSE_CLICKED,
            new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent e) {
                    if (e.getButton() == MouseButton.SECONDARY)  
                        cm.show(tvClientes, e.getScreenX(), e.getScreenY());
                }
        });
        //ContextMenu Modificar
        cmItem2.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                actionModificar();
            }
        });
        cm.getItems().add(cmItem2);
        tvClientes.addEventHandler(MouseEvent.MOUSE_CLICKED,
            new EventHandler<MouseEvent>() {
                @Override public void handle(MouseEvent e) {
                    if (e.getButton() == MouseButton.SECONDARY)  
                        cm.show(tvClientes, e.getScreenX(), e.getScreenY());
                }
        });
        
    }
    
    private void handleClientesTableSelectionChanged(ObservableValue observable, Object oldValue, Object newValue){
        if(newValue!=null){
            btnHistorial.setDisable(false);
            btnModificar.setDisable(false);
            btnEliminar.setDisable(false);
        }else{
            btnHistorial.setDisable(true);
            btnModificar.setDisable(true);
            btnEliminar.setDisable(true);
        }
        
    }
    
    @FXML
    private void actionEliminar() {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setHeaderText(null);
        alert.setContentText("¿Desea eliminar el cliente?");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            int selectedIndex = tvClientes.getSelectionModel().getSelectedIndex();
            tvClientes.getItems().remove(selectedIndex);
        } 
        
    }
    @FXML
    private void actionModificar() {
        ClienteBean cliente = tvClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            loadCrearMod(cliente);
        }
    }
    @FXML
    private void actionAnadir() {
        loadCrearMod(null);
    }
    @FXML
    private void actionBuscar() {
        int selectedIndex = tvClientes.getSelectionModel().getSelectedIndex();
        tvClientes.getItems().remove(selectedIndex);
    }
    @FXML
    private void actionVolver() {
        stage.close();
        ownerStage.requestFocus();
    }
    
    private void loadCrearMod(ClienteBean cliente) {
        try {
            ClientesManager clientesLogicController = new ClientesManagerTestDataGenerator();
            FXMLLoader loader = new FXMLLoader(App.class.getResource("gui/view/clientes/nuevo_cliente.fxml"));
            AnchorPane root = (AnchorPane) loader.load();
            ClientesCuController ctr = ((ClientesCuController) loader.getController());
            ctr.setStage(new Stage());
            ctr.setClientesManager(clientesLogicController);
            ctr.setClientesController(this);
            
            // En caso de opción Modificar
            if (cliente != null) {
                ctr.setCliente(cliente);
            }

            ctr.setOwnerStage(stage);
            ctr.initStage(root);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Error al cargar ventana nuevo_cliente.fxml.", ex);
        }
    }
    
    public TableView getTableView(){
        return this.tvClientes;
    }
}
