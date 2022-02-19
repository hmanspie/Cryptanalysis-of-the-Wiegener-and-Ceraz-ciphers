package org.example;

import java.util.ArrayList;
import java.util.List;

public class WiegenerEncryption extends Encryption {

    public WiegenerEncryption(String inputText, int [] keys) {
        super(inputText, keys);
    }

    @Override
    public String decryption() {
        int k = 0;
        List<Character> outputText = new ArrayList<>();
        int [] keys = this.getKeys();
        for(int i = 0; i < this.getInputText().length(); i++) {
            outputText.add(getSymbolCode(Math.floorMod(getCodeSymbol(this.getInputText().charAt(i))
                    - keys[k++], Encryption.COUNTLATTERMAX)));
            if(k == keys.length)  k = 0;
        }
        return listCharToString(outputText);
    }

    @Override
    public String encryption() {
        int k = 0;
        List<Character> outputText = new ArrayList<>();
        int [] keys = this.getKeys();
        for(int i = 0; i < this.getInputText().length(); i++) {
            outputText.add(getSymbolCode(Math.floorMod(getCodeSymbol(this.getInputText().charAt(i))
                    + keys[k++], Encryption.COUNTLATTERMAX)));
            if(k == keys.length)  k = 0;
        }
        return listCharToString(outputText);
    }


}
