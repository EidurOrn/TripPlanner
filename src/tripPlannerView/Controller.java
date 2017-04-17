package tripPlannerView;


import com.sun.xml.internal.fastinfoset.util.StringArray;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.time.LocalDate;
import java.util.ArrayList;

public class Controller {
    private Stage stage;
    private DatePicker DatePick_In = new DatePicker();
    public DatePicker DatePick_Out = new DatePicker();
    public DatePicker Hotel_Check_In = new DatePicker();
    public DatePicker Hotel_Check_Out = new DatePicker();
    private TextField txtField_Name = new TextField();
    private TextField txtField_Phone = new TextField();
    private TextField txtField_Email = new TextField();
    private TextField txtField_SSD = new TextField();

    public ArrayList<String> getPurchaser() {
        return Purchaser;
    }

    private ArrayList<String> Purchaser= new ArrayList<>();


    public String getDateIn(){
        return DatePick_In.getValue().toString();
    }


    //fall sem initializar check in glugga í datepicker og gerir það að verkum að ekki er hægt að velja dagsetningu
    //fyrir daginn í dag. @FXML yfirskrifar automatískt init fall. Þar sem að scene builder er bara með event handlera.
    @FXML
    void initialize() {
        DatePick_In.setValue(LocalDate.now());
        final Callback<DatePicker, DateCell> todayDate = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(LocalDate.now())) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        DatePick_In.setDayCellFactory(todayDate);
    }

    //Fall sem yfirskrifar reiti í datepicker og gerir það að verkum að ekki er hægt að velja dagsetningar fyrir
    //check in dagsetningu.
    public void datePickedOut(){
         DatePick_Out.setValue(DatePick_In.getValue().plusDays(1));
            final Callback<DatePicker, DateCell> fligtDayOut = new Callback<DatePicker, DateCell>() {
                @Override
                public DateCell call(final DatePicker datePicker) {
                    return new DateCell() {
                        @Override
                        public void updateItem(LocalDate item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item.isBefore(DatePick_In.getValue().plusDays(1))) {
                                setDisable(true);
                                setStyle("-fx-background-color: #ffc0cb;");
                            }
                        }
                    };
                }
            };
        DatePick_Out.setDayCellFactory(fligtDayOut);
    }


    public void hotelCheckin(){
        Hotel_Check_In.setValue(DatePick_Out.getValue());
        final Callback<DatePicker, DateCell> hoteldayIn = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(DatePick_Out.getValue())){
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };


        Hotel_Check_In.setDayCellFactory(hoteldayIn);
    }

    public void hotelCheckOut(){
        Hotel_Check_Out.setValue(Hotel_Check_In.getValue().plusDays(1));
        final Callback<DatePicker, DateCell> hotelOut = new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        if (item.isBefore(Hotel_Check_In.getValue().plusDays(1))) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        Hotel_Check_Out.setDayCellFactory(hotelOut);
    }


    public void bookTrip(){
        if(txtField_Name.getText().trim().isEmpty() || txtField_Email.getText().trim().isEmpty() || txtField_Phone.getText().trim().isEmpty() || txtField_SSD.getText().trim().isEmpty()) {
            ifFieldEmpty();
        } else {
            Purchaser.add(txtField_Name.getText());
            Purchaser.add(txtField_Email.getText());
            Purchaser.add(txtField_Phone.getText());
            Purchaser.add(txtField_SSD.getText());
        }

    }

    private void ifFieldEmpty() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information missing");
        alert.setHeaderText(null);
        alert.setContentText("You did not fill in all information");

        alert.showAndWait();
    }



}
