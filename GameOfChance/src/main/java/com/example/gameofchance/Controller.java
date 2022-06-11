package com.example.gameofchance;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    public ChoiceBox choiceBox;
    public Label firstDice;
    public Label secondDice;
    public Button btnFirstRoll;
    public Button btnSecondRoll;
    public Label moneyText;
    public Label showResult;
    public Button btnPlay;

    ObservableList<String> guesses = FXCollections.observableArrayList("Lower", "Higher");

    public void initialize() {
        choiceBox.setValue("Lower");
        choiceBox.setItems(guesses);
    }

    public void rollFirst(ActionEvent actionEvent) {
        int roll1 = (int) (Math.random() * 6 + 1);
        firstDice.setText("" + roll1);
        btnSecondRoll.setDisable(false);
    }

    public void rollSecond(ActionEvent actionEvent) {
        int roll2 = (int) (Math.random() * 6 + 1);
        secondDice.setText("" + roll2);
        int roll1 = Integer.parseInt(firstDice.getText());
        boolean win = false;
        if (roll1 > roll2 && choiceBox.getValue().equals("Lower")) {
            win = true;
        }
        if (roll2 > roll1 && choiceBox.getValue().equals("High")) {
            win = true;
        }
        int money = Integer.parseInt(moneyText.getText());
        if (win) {
            money += 10;
            showResult.setText("You Win! $10 added to the pool!");
        } else {
            money -= 10;
            showResult.setText("You Lose! $10 deducted from the pool!");
        }
        moneyText.setText("" + money);
        btnFirstRoll.setDisable(true);
        btnSecondRoll.setDisable(true);
        btnPlay.setDisable(false);
    }


    public void playAgain(ActionEvent actionEvent) {
        int money = Integer.parseInt(moneyText.getText());
        if (money == 0) {
            showResult.setText("You cannot play again with $0!");
        } else {
            btnPlay.setDisable(true);
            btnFirstRoll.setDisable(false);
            showResult.setText("");
            firstDice.setText("");
            secondDice.setText("");
        }
    }
}