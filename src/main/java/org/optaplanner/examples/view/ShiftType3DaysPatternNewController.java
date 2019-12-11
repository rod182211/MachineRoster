package org.optaplanner.examples.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import org.optaplanner.database.RosterService;
import org.optaplanner.database.RosterServiceImpl;
import org.optaplanner.examples.nurserostering.domain.ShiftType;
import org.optaplanner.examples.nurserostering.domain.pattern.ShiftType3DaysPattern;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ShiftType3DaysPatternNewController implements Initializable {

	@FXML
	private TextField Id;

	@FXML
	private ComboBox<ShiftType> dayIndex0ShiftTypefield;
	@FXML
	private ComboBox<ShiftType> dayIndex1ShiftTypefield;
	@FXML
	private ComboBox<ShiftType> dayIndex2ShiftTypefield;
	
	@FXML
	private TextField dayweight;
	@FXML
	private TextField codefield;


	private Stage dialogStage;

	private ShiftType3DaysPattern shift3daydata;

	private boolean okClicked = false;

	
	private RosterService rosterService = new RosterServiceImpl();
	private ObservableList<ShiftType3DaysPattern> shift3daydataList = FXCollections.observableArrayList();

	public ObservableList<ShiftType3DaysPattern> getShiftType3DaysPatternList() {
		if (!shift3daydataList.isEmpty())
			shift3daydataList.clear();
		shift3daydataList = FXCollections.observableList((List<ShiftType3DaysPattern>) rosterService.listShiftType3DaysPattern());
		return shift3daydataList;
	}

	private ObservableList<ShiftType3DaysPattern> shift3daydataListId = FXCollections.observableArrayList();

	public ObservableList<ShiftType3DaysPattern> getShiftType3DaysPatternListId() {
		if (!shift3daydataListId.isEmpty())
			shift3daydataListId.clear();
		shift3daydataListId = FXCollections.observableList((List<ShiftType3DaysPattern>) rosterService.listShiftType3DaysPatternId());
		return shift3daydataListId;
	}
	private ObservableList<ShiftType> shiftList = FXCollections.observableArrayList();

	public ObservableList<ShiftType> getShiftList() {
		if (!shiftList.isEmpty())
			shiftList.clear();
		shiftList = FXCollections.observableList((List<ShiftType>) rosterService.listShiftType());
		return shiftList;
	}


	/**
	 * Initializes the controller class. This method is automatically called after
	 * the fxml file has been loaded.
	 */
	public void initialize(URL location, ResourceBundle resources) {

	}

	/**
	 * Sets the stage of this dialog.
	 * 
	 * @param dialogStage
	 */
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

	/**
	 * Sets the employee to be edited in the dialog.
	 * 
	 * @param employee
	 */

	public void setShiftType3DaysPattern(ShiftType3DaysPattern shift3daydata) {
		this.shift3daydata = shift3daydata;
		
		getShiftList();
		dayIndex0ShiftTypefield.setItems(shiftList);
		
		dayIndex1ShiftTypefield.setItems(shiftList);
		
		dayIndex2ShiftTypefield.setItems(shiftList);
	
		
		

	}

	/**
	 * Returns true if the user clicked OK, false otherwise.
	 * 
	 * @return
	 */
	public boolean isOkClicked() {
		return okClicked;
	}

	/**
	 * Called when the user clicks ok.
	 */
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			String tempweightval = dayweight.getText();
			int weightval = Integer.parseInt(tempweightval);
			ShiftType dayIndex0 = dayIndex0ShiftTypefield.getSelectionModel().getSelectedItem();
			shift3daydata.setCode(codefield.getText());
			shift3daydata.setDayIndex0ShiftType(dayIndex0);
			ShiftType dayIndex1 = dayIndex1ShiftTypefield.getSelectionModel().getSelectedItem();
			shift3daydata.setDayIndex1ShiftType(dayIndex1);
			ShiftType dayIndex2 = dayIndex2ShiftTypefield.getSelectionModel().getSelectedItem();
			shift3daydata.setDayIndex2ShiftType(dayIndex2);
			shift3daydata.setWeight(weightval);
			okClicked = true;
			rosterService.addShiftType3DaysPattern(shift3daydata);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Information Dialog");
			alert.setHeaderText(null);
			alert.setContentText("Update Succesfull!");
			alert.showAndWait();
			dialogStage.close();
		}
	}

	/**
	 * Called when the user clicks cancel.
	 */
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}

	/**
	 * Validates the user input in the text fields.
	 * 
	 * @return true if the input is valid
	 */
	private boolean isInputValid() {
		String errorMessage = "";

		if (dayweight.getText() == null || dayweight.getText().length() == 0) {
			errorMessage += "No valid Request!\n";
		}

		if (errorMessage.length() == 0) {
			return true;
		} else {
			// Show the error message.
			Alert alert = new Alert(AlertType.ERROR);
			alert.initOwner(dialogStage);
			alert.setTitle("Invalid Fields");
			alert.setHeaderText("Please correct invalid fields");
			alert.setContentText(errorMessage);

			alert.showAndWait();

			return false;
		}
	}
}
