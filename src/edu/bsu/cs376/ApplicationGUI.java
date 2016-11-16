package edu.bsu.cs376;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class ApplicationGUI extends Application {
    private Stage window;
    private MainMenu mainMenuPane = new MainMenu();
    private BorderPane FCFSPane = new BorderPane();
    private BorderPane mainLayout = new BorderPane();
    private FCFSLayout fcfsLayout = new FCFSLayout();
    private SJFLayout sjfLayout = new SJFLayout();
    private RoundRobinLayout roundRobin = new RoundRobinLayout();

    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setResizable(false);
        window.setTitle("CPU Scheduling Simulator");
        window.setScene(makeFirstSceneLayout());
        window.show();
    }

    private Scene makeFirstSceneLayout() {
        return new Scene(setMainMenu(), 400, 350);
    }

    private BorderPane setMainMenu() {
        BorderPane mainMenu = mainMenuPane.makeMainMenu();
        mainMenu.setBottom(setCenterPane());
        return mainMenu;
    }

    private VBox setCenterPane() {
        VBox selectMenu = mainMenuPane.makeSelectMenu();
        selectMenu.getChildren().addAll(FCFSButton(), SJFButton(), RRButton());
        selectMenu.setAlignment(Pos.CENTER);
        return selectMenu;
    }

    private Button FCFSButton(){
        Button FCFS = new Button("First Come First Serve");
        FCFS.setCursor(Cursor.HAND);
        FCFS.setOnAction(event -> window.setScene(FCFSInput()));
        return FCFS;
    }

    private Scene FCFSInput(){
        return new Scene(FCFSLayout(), 600, 650);
    }

    private BorderPane FCFSLayout() {
        FCFSPane = new BorderPane();
        mainLayout = new BorderPane();
        setTop("First Come First Serve");
        FCFSPane = fcfsLayout.setMainWindow();
        mainLayout.setCenter(FCFSPane);
        return mainLayout;
    }

    private Button RRButton() {
        Button RR = new Button("Round Robin");
        RR.setCursor(Cursor.HAND);
        RR.setOnAction(event -> window.setScene(roundRobinInput()));
        return RR;
    }

    private Scene roundRobinInput(){
        return new Scene(RRLayout(), 600, 650);
    }

    private BorderPane RRLayout() {
        FCFSPane = new BorderPane();
        mainLayout = new BorderPane();
        setTop("Round Robin");
        FCFSPane = roundRobin.setMainWindow();
        mainLayout.setCenter(FCFSPane);
        return mainLayout;
    }

    private Button SJFButton(){
        Button SJF = new Button("Shortest Job First");
        SJF.setCursor(Cursor.HAND);
        SJF.setOnAction(event -> window.setScene(SJFInput()));
        return SJF;
    }

    private Scene SJFInput(){
        return new Scene(SJFLayout(), 600, 650);
    }

    private BorderPane SJFLayout() {
        FCFSPane = new BorderPane();
        mainLayout = new BorderPane();
        setTop("Shortest Job First");
        FCFSPane = sjfLayout.setMainWindow();
        mainLayout.setCenter(FCFSPane);
        return mainLayout;
    }

    private void setTop(String selected){
        HBox top = new HBox();
        Label algorithm = new Label(selected);
        algorithm.setFont(Font.font(16));
        Button restart = new Button();
        restart.setText("Main Menu");
        restart.setOnAction(event->window.setScene(makeFirstSceneLayout()) );
        top.getChildren().addAll(restart,algorithm);
        top.setSpacing(150);
        mainLayout.setTop(top);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
