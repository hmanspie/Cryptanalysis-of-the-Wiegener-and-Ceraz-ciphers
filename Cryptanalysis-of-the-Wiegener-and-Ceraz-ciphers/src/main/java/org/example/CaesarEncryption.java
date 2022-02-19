package org.example;

import java.util.ArrayList;
import java.util.List;

public class CaesarEncryption extends Encryption {


    public CaesarEncryption(String inputText, int key) {
        super(inputText, key);
    }

    @Override
    public String decryption() {
        List<Character> outputText = new ArrayList<>();
        for(int i = 0; i < this.getInputText().length(); i++) {
            outputText.add(getSymbolCode(Math.floorMod(getCodeSymbol(this.getInputText().charAt(i))
                    - this.getKey(), Encryption.COUNTLATTERMAX)));
        }
        return listCharToString(outputText);
    }

    @Override
    public String encryption() {
        List<Character> outputText = new ArrayList<>();
        for(int i = 0; i < this.getInputText().length(); i++) {
            outputText.add(getSymbolCode(Math.floorMod(getCodeSymbol(this.getInputText().charAt(i))
                    + this.getKey(), Encryption.COUNTLATTERMAX)));
        }
        return listCharToString(outputText);
    }

}



