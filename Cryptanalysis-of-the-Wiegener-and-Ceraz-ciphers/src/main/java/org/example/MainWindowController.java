package org.example;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class MainWindowController {

    public TextArea outputingText;
    public TextArea incomingText;
    public TextField keyTextField;

    public Filtration filtration = new Filtration();
    public int [] keyArray;
    public void decryptionButtonClick(ActionEvent actionEvent) {
        if(isCorrectKeyAndInputText())
            outputingText.setText(StartAppView.encryption.decryption());
    }

    public void encryptionButtonClick(ActionEvent actionEvent) {
        if(isCorrectKeyAndInputText())
            outputingText.setText(StartAppView.encryption.encryption());
    }

    public boolean isCorrectKeyAndInputText(){
        StartAppView.encryption.setInputText(filtration.filtrationText(incomingText.getText()));
        if(keyTextField.getText().length() == 0){

        }
        if(keyTextField.getText().length() > 1) {
            try {
                int count = 0;
                String [] result = keyTextField.getText().split(",");
                for (String strInt : result) {
                    if(count == 0)
                        keyArray = new int[result.length];
                    keyArray[count++] = Integer.parseInt(strInt);
                }
                StartAppView.encryption.setKeys(keyArray);
                return true;
            } catch (Exception exception) {
                outputingText.setText("Не коректно введений ключ шифрування!");
                return false;
            }
        }
        else {
            try {
                if (Integer.parseInt(keyTextField.getText()) > 0 && Integer.parseInt(keyTextField.getText()) < Encryption.COUNTLATTERMAX) {
                    StartAppView.encryption.setKey(Integer.parseInt(keyTextField.getText()));
                    return true;
                } else {
                    outputingText.setText("Не коректно введений ключ шифрування!");
                    return false;
                }
            } catch (Exception exception) {
                outputingText.setText("Не коректно введений ключ шифрування!");
                return false;
            }
        }
    }

}
