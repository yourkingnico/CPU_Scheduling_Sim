package edu.bsu.cs376;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        ObservableList<Integer> NUMBER_JOBS = FXCollections.observableArrayList();
        for(int i =2; i< 21; i++){
            NUMBER_JOBS.add(i);
        }
        final ComboBox jobBox = new ComboBox(NUMBER_JOBS);
        jobBox.setOnAction(event-> makeInputs(jobBox.getSelectionModel().getSelectedItem().toString()));
        return jobBox;
    }

    private void makeInputs(String num){
        int jobNum = Integer.parseInt(num);
        VBox inputBoxes = new VBox();

        for(int i = 1; i <= jobNum; i++){
            inputBoxes.getChildren().add(makeJobInput(i));
        }
        ScrollPane jobScroll = new ScrollPane(inputBoxes);
        jobScroll.setFitToHeight(true);
        mainLayout.setCenter(jobScroll);
    }

    private HBox makeJobInput(int num){
        HBox job = new HBox();
        Label jobNum = new Label("Job Number "+ num);
        jobNum.setPadding(new Insets(10,50,0,0));
        TextArea burst = new TextArea();
        burst.setPromptText("Burst Time");
        burst.setMaxSize(100,8);
        job.getChildren().addAll(jobNum, burst);
        job.setStyle("-fx-border-color: black;");
        job.setPadding(new Insets(10,10,10,10));
        return job;
    }


}
