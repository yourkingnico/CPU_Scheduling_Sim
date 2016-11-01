package edu.bsu.cs376;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ApplicationGUI extends Application {
    private Stage window;
    private MainMenu mainMenuPane = new MainMenu();
    private BorderPane FCFSPane = new BorderPane();
    private BorderPane center = new BorderPane();
    private FCFSLayout fcfsLayout = new FCFSLayout();

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
        return new Scene(FCFSLayout(), 700, 650);
    }

    private BorderPane FCFSLayout() {
        FCFSPane = fcfsLayout.setMainWindow();
        //center = fcfsLayout.makeSceneInstance();
        //FCFSPane.setBottom(center);
        return FCFSPane;
    }

    private Button RRButton() {
        Button RR = new Button("Round Robin");
        RR.setCursor(Cursor.HAND);
        //RR.setOnAction(event -> {

        //});
        return RR;
    }

    private Button SJFButton(){
        Button SJF = new Button("Shortest Job First");
        SJF.setCursor(Cursor.HAND);
        //goToCalculator.setOnAction(event -> {

        //});
        return SJF;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
