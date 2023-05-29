package com.example.project_i.Phieu_Nhap_Xuat;


import com.example.project_i.DataSearch;
import com.example.project_i.HangHoa.HangHoa_Module;
import com.example.project_i.KetNoi_Database.DBConnection;

import com.example.project_i.KhachHang.KhachHang_Module;
import impl.org.controlsfx.autocompletion.AutoCompletionTextFieldBinding;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.controlsfx.control.tableview2.cell.TextField2TableCell;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import impl.org.controlsfx.autocompletion.SuggestionProvider;
import org.controlsfx.control.textfield.TextFields;


import javax.swing.*;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

public class phieuNhap_Controller implements Initializable {

    @FXML
    private Button luuPhieu_Button;

    @FXML
    private TextField maHang, diaChi_TextField,sdt_TextField;

    @FXML
    private TextField maKH,soPhieu_TextField;

    @FXML
    private DatePicker ngayPS_Field;

    @FXML
    private Button taoPhieu_Button;


    @FXML
    private TableView<ctPhieu_Nhap_Xuat_Module> phieuNhap_Table = new TableView<ctPhieu_Nhap_Xuat_Module>();
    @FXML
    private TableColumn<ctPhieu_Nhap_Xuat_Module, Double> soLuong_Column;

    @FXML
    private TableColumn<ctPhieu_Nhap_Xuat_Module, String> tenHang_Column = new TableColumn<ctPhieu_Nhap_Xuat_Module,String>("TenHang");

    @FXML
    private TableColumn<ctPhieu_Nhap_Xuat_Module, Integer> maHang_Column;

    @FXML
    private TableColumn<ctPhieu_Nhap_Xuat_Module, Double> thanhTien_Column;

    @FXML
    private TableColumn<ctPhieu_Nhap_Xuat_Module, String> donViTinh_Column;

    @FXML
    private TableColumn<ctPhieu_Nhap_Xuat_Module, Double> giaNhap_Column;

    @FXML
    private TableView<KhachHang_Module> search_Table;
    @FXML
    private TableColumn<KhachHang_Module, Integer> searchID_Column;

    @FXML
    private TableColumn<KhachHang_Module, String> searchName_Column;


    @FXML
    private TableColumn<KhachHang_Module, String> searchDiaChi_Column;
    @FXML
    private TableColumn<KhachHang_Module, String> searchSDT_Column;
    public Connection conn = null ;
    public PreparedStatement preparedStatement = null;
    public ResultSet resultSet = null;
    ObservableList<ctPhieu_Nhap_Xuat_Module> phieuNhap_Data;

    int maPhieu;
    public void setTaoPhieu_Button(){
        taoPhieu_Button.setDisable(true);
        soPhieu_TextField.setDisable(false);
        ngayPS_Field.setDisable(false);
        maHang.setDisable(false);
        maKH.setDisable(false);
        phieuNhap_Table.setDisable(false);
        luuPhieu_Button.setDisable(false);
        ngayPS_Field.setValue(java.time.LocalDate.now());
        conn = DBConnection.ConnectionDB();
        String sql = "phieuNhap_add ?";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setDate(1, Date.valueOf(ngayPS_Field.getValue()));
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                maPhieu = resultSet.getInt(1);
                soPhieu_TextField.setText("PHNHAP_"+resultSet.getString(1));
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }


    public void setNhap_Button(){
        String string = maHang.getText();
        String[] parts =string.split(" -");
        String sql = "ctPhieuNhap_add ?,?";
        try {
            preparedStatement = conn.prepareStatement(sql);
            preparedStatement.setInt(1, maPhieu);
            preparedStatement.setInt(2, Integer.parseInt(parts[0]));
            preparedStatement.execute();
            JOptionPane.showMessageDialog(null, "ADD");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        maHang.clear();
        setCellQuanLyHH_Table();
    }
    public void loadDataFromDatabase() {
        try {
            preparedStatement = conn.prepareStatement("phieuNhap_show ?");
            preparedStatement.setInt(1, maPhieu);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                phieuNhap_Data.add(new ctPhieu_Nhap_Xuat_Module(resultSet.getInt(1),resultSet.getInt(1)+" - "+resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4),resultSet.getDouble(5),resultSet.getDouble(6)));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        phieuNhap_Table.setItems(phieuNhap_Data);
    }
    public void setCellQuanLyHH_Table(){
        List<String> list = new ArrayList<>();
        try {
            preparedStatement = conn.prepareStatement("select * from danhMucHH");

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String text= resultSet.getString(2);

                list.add(new String(String.valueOf(resultSet.getInt(1))+" - "+resultSet.getString(2)));
            }
        }catch (SQLException e){

        }
        TextFields.bindAutoCompletion(maHang, list);
        phieuNhap_Table.getItems().clear();
        tenHang_Column.setCellValueFactory(new PropertyValueFactory<ctPhieu_Nhap_Xuat_Module,String>("tenHang"));
        donViTinh_Column.setCellValueFactory(new PropertyValueFactory<ctPhieu_Nhap_Xuat_Module,String>("donViTinh"));
        soLuong_Column.setCellValueFactory(new PropertyValueFactory<ctPhieu_Nhap_Xuat_Module,Double>("soLuong"));
        giaNhap_Column.setCellValueFactory(new PropertyValueFactory<ctPhieu_Nhap_Xuat_Module,Double>("giaNhap"));
        thanhTien_Column.setCellValueFactory(new PropertyValueFactory<ctPhieu_Nhap_Xuat_Module,Double>("total"));
        phieuNhap_Table.setEditable(true);

        loadDataFromDatabase();
    }
    int index = -1;
    int index1 = -1;
    public String curentID;
    @FXML
    void getSelected(MouseEvent event1){
        index = phieuNhap_Table.getSelectionModel().getSelectedIndex();
        if (index <= -1) {
            return ;
        }
    }

    @FXML
    void setSearch_Table_Pick(MouseEvent event1){
        index1 = phieuNhap_Table.getSelectionModel().getSelectedIndex();
        if (index1 <= -1) {
            return ;
        }
    }


    public void escapeKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER ){
            setNhap_Button();
        }
    }

    ObservableList<KhachHang_Module> khachHang_Data = FXCollections.observableArrayList();
    @FXML
    void setMaKH(KeyEvent event) {
        searchID_Column.setCellValueFactory(new PropertyValueFactory<>("maKH"));
        searchName_Column.setCellValueFactory(new PropertyValueFactory<>("tenKh"));
        searchDiaChi_Column.setCellValueFactory(new PropertyValueFactory<>("diaChi"));
        searchSDT_Column.setCellValueFactory(new PropertyValueFactory<>("sdt"));
        khachHang_Data = DBConnection.getKH_Data();
        search_Table.setItems(khachHang_Data);
        FilteredList<KhachHang_Module> filteredData = new FilteredList<>(khachHang_Data, b -> true);
        maKH.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(needed -> {
                if (newValue == null || newValue.isEmpty()) {
                    search_Table.setVisible(false);
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if (needed.getTenKh().toLowerCase().indexOf(lowerCaseFilter) != -1 ) {
                    search_Table.setVisible(true);
                    return true; // Filter matches name
                } else {
                    search_Table.setVisible(true);
                    return false;
                } // Does not match.
            });
        });
        SortedList<KhachHang_Module> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(search_Table.comparatorProperty());
        search_Table.setItems(sortedData);
    }

    @FXML
    private AnchorPane phieuNhap_Pane;
    public void setPhieuNhap_Pane(KeyEvent event){
        if (event.getCode() == KeyCode.F5 ){
            setTaoPhieu_Button();
        }
    }
    /*@FXML
    public static <T,S> void setAutoCompleteTableColumn(TableColumn<T,S> column, List items){
        column.setCellFactory(param -> {
            return new TableCell<T, S>(){
                final TextField textField = new TextField();

                @Override
                protected void updateItem(S item, boolean empty) {
                    super.updateItem(item, empty);

                    if(item == null){
                        setGraphic(null);
                    }else {
                        editableProperty().addListener((obs, oldValue, newValue)->{
                            if(newValue){
                                setGraphic(textField);
                            }else{
                                setText(item.toString());
                            }
                        });
                        AutoCompletionBinding<T> binding = TextFields.bindAutoCompletion(textField,items);
                        binding.setOnAutoCompleted(event ->{
                            //handleCompleted.accept(event.getCompletion());
                        });
                    }

                }
            };
        });


    }*/

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        final TextFieldTableCell[] textFieldTableCell = {new TextFieldTableCell<>()};
        //search_Table.setColumnResizePolicy(TableView.UNCONSTRAINED_RESIZE_POLICY);
        conn = DBConnection.ConnectionDB();
        phieuNhap_Data = FXCollections.observableArrayList();
        tenHang_Column.setCellFactory(TextFieldTableCell.forTableColumn());
        donViTinh_Column.setCellFactory(ListCell.);
        donViTinh_Column.setCellValueFactory(L);
        //donViTinh_Column.setCellFactory(TextField2TableCell.forTableColumn());
        //donViTinh_Column.setCellValueFactory(TextFieldTableCell.forTableColumn());

        tenHang_Column.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent<ctPhieu_Nhap_Xuat_Module, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ctPhieu_Nhap_Xuat_Module, String> event) {
                System.out.println("Dame");

            }
        });
        String[] b = {"s","d"};
        AutoCompletionBinding<String> autoCompletionBinding;

        int index = -1;
        tenHang_Column.setOnEditStart(new EventHandler<TableColumn.CellEditEvent<ctPhieu_Nhap_Xuat_Module, String>>() {
            @Override
            public void handle(TableColumn.CellEditEvent<ctPhieu_Nhap_Xuat_Module, String> event) {
                diaChi_TextField.setText("llslslsl");
                //tenHang_Column.setCellValueFactory(new PropertyValueFactory<ctPhieu_Nhap_Xuat_Module,String>("textField"));
                //textFieldTableCell[0].setId("dame");
                String[] a = {"a","b","c"};
                sdt_TextField.setText(textFieldTableCell[0].getText());
                textFieldTableCell[0].setId("a");
                autoCompletionBinding = TextFields.bindAutoCompletion(textFieldTableCell[0],a);

                //TableColumn.DEFAULT_CELL_FACTORY;
            }
        });
        donViTinh_Column.setCellFactory(TextFieldTableCell.forTableColumn());



        List<String> list = new ArrayList<>();
        try {
            preparedStatement = conn.prepareStatement("select * from danhMucHH");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String text= resultSet.getString(2);

                list.add(new String(String.valueOf(resultSet.getInt(1))+" - "+resultSet.getString(2)));
            }
        }catch (SQLException e){

        }
        TextFields.bindAutoCompletion(maHang, list);
        //TextFields.bindAutoCompletion(, search_Table);
    }
}
