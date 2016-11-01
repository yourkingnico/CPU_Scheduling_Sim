package edu.bsu.cs376;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class MainMenu {
    Label title = new Label();
    Label question = new Label();
    int TEXT_SIZE = 18;

    public BorderPane makeMainMenu() {
        BorderPane centralPane = new BorderPane();
        BorderPane top = new BorderPane();
        top.setTop(setMenuTitle());
        //top.setCenter(setMenuPrompt());
        centralPane.setTop(top);
        centralPane.setCenter(setMenuPrompt());
        top.setStyle("-fx-background-color: #999999;");
        //centralPane.setStyle("-fx-background-color: #00CC99;");
        BorderPane.setAlignment(title, Pos.CENTER);
        return centralPane;
    }

    public Label setMenuTitle() {
        title.setText("Main Menu");
        title.setFont(Font.font("Arial", FontWeight.BOLD, TEXT_SIZE));
        title.setTextFill(Color.WHITE);
        return title;
    }

    public Label setMenuPrompt() {
        question.setText("Please Select A Scheduling Algorithm To Simulate");
        question.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        return question;
    }


    public VBox makeSelectMenu() {
        return new VBox(30);
    }

}
