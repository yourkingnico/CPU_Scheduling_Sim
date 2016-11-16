package edu.bsu.cs376;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class FCFSLayout {

    protected BorderPane mainLayout = new BorderPane();
    private BorderPane top = new BorderPane();
    private ComboBox jobBox;
    ArrayList<TextArea> times = new ArrayList();
    private Button calculate = new Button();

    public BorderPane setMainWindow(){
        mainLayout.setCenter(null);
        mainLayout.setBottom(null);
        mainLayout.setTop(top());
        return mainLayout;
    }

    private BorderPane top(){
        top.setTop(jobPrompt());
        top.setCenter(calculateButton());
        top.setBottom(makeDropdown());
        return top;
    }

    private Label jobPrompt(){
        Label promptNumber = new Label();
        promptNumber.setText("Please select the number of jobs to schedule");
        promptNumber.setFont(Font.font(16));
        return promptNumber;
    }

    private Button calculateButton(){
        calculate.setText("Calculate");
        calculate.setOnAction(event -> {
            if(hasInvalidInput()){
                mainLayout.setBottom(error());
            }
            else {
                simulate();
            }
        });
        calculate.setVisible(false);
        return calculate;
    }

    public void simulate(){
        String output = "";
        for(int i = 0; i < times.size(); i++){
            output +="  Job " + (i+1) + "," + times.get(i).getText() + ", ";
        }
        showOutput(output);
    }

    protected boolean hasInvalidInput(){
        for (TextArea time : times) {
            if (!time.getText().matches("[0-9]+")) {
                return true;
            }
        }
        return false;
    }

    public void showOutput(String output){
        VBox bottom = new VBox();
        Label out = new Label();
        out.setText(output);
        out.setFont(Font.font(14));
        out.setWrapText(true);
        bottom.getChildren().addAll(outputText(), out, reset());
        mainLayout.setBottom(bottom);
        calculate.setVisible(false);
        jobBox.setVisible(false);
    }

    private Label outputText() {
        Label output = new Label();
        output.setText("Simulated Job Order: (Job, Burst Time ) ");
        output.setFont(Font.font(16));
        return output;
    }


    private Button reset(){
        Button reset = new Button();
        reset.setText("reset");
        reset.setOnAction(event-> setMainWindow());
        return reset;
    }

    private ComboBox makeDropdown(){
        times = new ArrayList();
        ObservableList<Integer> NUMBER_JOBS = FXCollections.observableArrayList();
        for(int i =2; i< 21; i++){
            NUMBER_JOBS.add(i);
        }
        jobBox = new ComboBox(NUMBER_JOBS);
        jobBox.setOnAction(event->{
            makeInputs(jobBox.getSelectionModel().getSelectedItem().toString());
            calculateButton().setVisible(true);
        } );
        return jobBox;
    }

    public void makeInputs(String num){
        int jobNum = Integer.parseInt(num);
        VBox inputBoxes = new VBox();
        for(int i = 1; i <= jobNum; i++){
            inputBoxes.getChildren().add(makeJobInput(i));
        }
        ScrollPane jobScroll = new ScrollPane(inputBoxes);
        jobScroll.setFitToHeight(true);
        mainLayout.setCenter(jobScroll);
    }

    protected HBox makeJobInput(int num){
        HBox job = new HBox();
        Label jobNum = new Label("Job Number "+ num);
        jobNum.setPadding(new Insets(10,50,0,0));
        TextArea burst = new TextArea();
        burst.setPromptText("Burst Time");
        burst.setMaxSize(100,8);
        job.getChildren().addAll(jobNum, burst);
        job.setStyle("-fx-border-color: black;");
        job.setPadding(new Insets(10,10,10,10));
        times.add(burst);
        return job;
    }

    private Label error(){
        Label error = new Label();
        error.setText("Error: input must be number value");
        error.setTextFill(Color.RED);
        error.setFont(Font.font(20));
        return error;
    }
}
