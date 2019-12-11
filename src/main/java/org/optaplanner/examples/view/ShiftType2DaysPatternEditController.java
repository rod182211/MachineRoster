package org.optaplanner.examples.view;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;


import org.optaplanner.database.RosterService;
import org.optaplanner.database.RosterServiceImpl;
import org.optaplanner.examples.nurserostering.domain.ShiftType;
import org.optaplanner.examples.nurserostering.domain.pattern.ShiftType2DaysPattern;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

public class ShiftType2DaysPatternEditController implements Initializable {

	
	@FXML
	private ComboBox<ShiftType> dayIndex0ShiftTypefield;
	@FXML
	private ComboBox<ShiftType> dayIndex1ShiftTypefield;
	
	@FXML
	private TextField dayweight;
	@FXML
	private TextField codefield;


	private Stage dialogStage;

	private ShiftType2DaysPattern shift2daydata;

	private boolean okClicked = false;

	
	private RosterService rosterService = new RosterServiceImpl();
	private ObservableList<ShiftType2DaysPattern> shift2daydataList = FXCollections.observableArrayList();

	public ObservableList<ShiftType2DaysPattern> getShiftType2DaysPatternList() {
		if (!shift2daydataList.isEmpty())
			shift2daydataList.clear();
		shift2daydataList = FXCollections.observableList((List<ShiftType2DaysPattern>) rosterService.listShiftType2DaysPattern());
		return shift2daydataList;
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

	public void setShiftType2DaysPatternDetails(ShiftType2DaysPattern shift2daydata) {
		this.shift2daydata = shift2daydata;
		
		getShiftList();
		dayIndex0ShiftTypefield.setItems(shiftList);
	    dayIndex0ShiftTypefield.setValue(shift2daydata.getDayIndex0ShiftType());
	    dayIndex1ShiftTypefield.setValue(shift2daydata.getDayIndex1ShiftType());
		dayIndex1ShiftTypefield.setItems(shiftList);
		codefield.setText(shift2daydata.getCode());
		dayweight.setText(Integer.toString(shift2daydata.getWeight()));
		
		

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
	
		String tempweightval = dayweight.getText();
		int weightval = Integer.parseInt(tempweightval);
		ShiftType dayIndex0 = dayIndex0ShiftTypefield.getSelectionModel().getSelectedItem();
		shift2daydata.setCode(codefield.getText());
		shift2daydata.setDayIndex0ShiftType(dayIndex0);
		ShiftType dayIndex1 = dayIndex1ShiftTypefield.getSelectionModel().getSelectedItem();
		shift2daydata.setDayIndex1ShiftType(dayIndex1);
		shift2daydata.setWeight(weightval);
		okClicked = true;
		rosterService.updateShiftType2DaysPattern(shift2daydata);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Information Dialog");
		alert.setHeaderText(null);
		alert.setContentText("Update Succesfull!");
		alert.showAndWait();
		dialogStage.close();
			
		
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
	/*
	 * private boolean isInputValid() { String errorMessage = "";
	 * 
	 * if (Id.getText() == null || Id.getText().length() == 0) { errorMessage +=
	 * "No valid Request!\n"; }
	 * 
	 * if (errorMessage.length() == 0) { return true; } else { // Show the error
	 * message. Alert alert = new Alert(AlertType.ERROR);
	 * alert.initOwner(dialogStage); alert.setTitle("Invalid Fields");
	 * alert.setHeaderText("Please correct invalid fields");
	 * alert.setContentText(errorMessage);
	 * 
	 * alert.showAndWait();
	 * 
	 * return false; } }
	 */
}
