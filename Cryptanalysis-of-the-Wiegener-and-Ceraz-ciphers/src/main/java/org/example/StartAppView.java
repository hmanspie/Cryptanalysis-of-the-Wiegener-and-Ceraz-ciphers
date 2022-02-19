package org.example;

import javafx.event.ActionEvent;

import java.io.IOException;

public class StartAppView {

    public static Encryption encryption;
    public void cerazCiphersButtonClick(ActionEvent actionEvent) throws IOException {
        App.setRoot("MainWindowView");
        encryption = new CaesarEncryption("", 0);
    }

    public void wiegenerCiphersButtonClick(ActionEvent actionEvent) throws IOException {
        App.setRoot("MainWindowView");
        encryption = new WiegenerEncryption("", null);
    }

    public void hackButtonClick(ActionEvent actionEvent) throws IOException {
        App.setRoot("HackWindowView");
        encryption = new WiegenerEncryption("", null);
    }
}
