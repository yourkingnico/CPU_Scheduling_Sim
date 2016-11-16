package edu.bsu.cs376;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class RoundRobinLayout extends FCFSLayout {

    private TextArea qArea;

    public void makeInputs(String num){
        int jobNum = Integer.parseInt(num);
        VBox inputBoxes = new VBox();
        for(int i = 1; i <= jobNum; i++){
            inputBoxes.getChildren().add(makeJobInput(i));
        }
        inputBoxes.getChildren().add(quantum());
        ScrollPane jobScroll = new ScrollPane(inputBoxes);
        jobScroll.setFitToHeight(true);
        mainLayout.setCenter(jobScroll);
    }

    private HBox quantum(){
        HBox q = new HBox();
        Label quantum = new Label("Quantum");
        q.setPadding(new Insets(10,50,0,0));
        qArea = new TextArea();
        qArea.setPromptText("q");
        qArea.setMaxSize(100,8);
        q.getChildren().addAll(quantum, qArea);
        q.setStyle("-fx-border-color: black;");
        q.setPadding(new Insets(10,10,10,10));
        return q;
    }
    @Override
    protected boolean hasInvalidInput() {
        for (TextArea time : times) {
            if (!time.getText().matches("[0-9]+")) {
                return true;
            }
        }
        if (!qArea.getText().matches("[0-9]+")) {
            return true;
        }
        return false;
    }
    @Override
    public void simulate(){
        boolean complete = false;
        int q = Integer.valueOf(qArea.getText());
        int[][] jobs = new int[times.size()][times.size()];
        int job;
        String output = "";
        for(int i = 0; i < times.size(); i++){
            job = i+1 ;
            jobs[i][0]=(job);
            jobs[i][1]=(Integer.valueOf(times.get(i).getText()));
        }
        while (!complete){
            int check = 0;
            for (int[] job1 : jobs) {
                int hold = job1[1];
                if(hold <= 0){
                    check++;
                }
                else{
                    job1[1] = hold - q;
                    if(job1[1] <= 0){
                        output = output + "  Job " + job1[0] + ", " + hold + ", ";
                    }
                    else {
                        output = output + "  Job " + job1[0] + ", " + (hold - job1[1]) + ", ";
                    }
                }
                if(check == times.size()){
                    complete = true;
                }
            }
        }
        showOutput(output);
    }
}

