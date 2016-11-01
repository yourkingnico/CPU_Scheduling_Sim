package edu.bsu.cs376;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

public class FCFSLayout {

    private BorderPane mainLayout = new BorderPane();
    private BorderPane top = new BorderPane();

    public BorderPane setMainWindow(){
        mainLayout.setTop(top());
        return mainLayout;
    }

    private BorderPane top(){
        top.setTop(jobPrompt());
        top.setCenter(makeDropdown());
        return top;
    }

    private Label jobPrompt(){
        Label promptNumber = new Label();
        promptNumber.setText("Please select the number of jobs to schedule");
        promptNumber.setFont(Font.font(16));
        return promptNumber;
    }

    private ComboBox makeDropdown(){
        ObservableList<Integer> NUMBER_JOBS = FXCollections.observableArrayList(1,2,3,4,5,6,7,8,9,10,11,12,13,
                14,15,16,17,18,19,20);
        final ComboBox jobBox = new ComboBox(NUMBER_JOBS);
        jobBox.setOnAction(event-> System.out.println(jobBox.getSelectionModel().getSelectedItem()));
        return jobBox;
    }

}
