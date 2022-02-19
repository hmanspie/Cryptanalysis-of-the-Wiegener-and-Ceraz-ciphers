package org.example;

import java.util.List;

public abstract class Encryption {

    public final static int COUNTLATTERMAX = 32;
    private String inputText;
    private int key;
    private int [] keys;

    public Encryption(String inputText, int key) {
        this.inputText = inputText;
        this.key = key;
    }

    public Encryption(String inputText, int[] keys) {
        this.inputText = inputText;
        this.keys = keys;
    }

    public int[] getKeys() {
        return keys;
    }

    public void setKeys(int[] keys) {
        this.keys = keys;
    }

    public String getInputText() {
        return inputText;
    }

    public void setInputText(String inputText) {
        this.inputText = inputText;
    }

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public abstract String decryption();

    public abstract String encryption();

    public int getCodeSymbol(char symbol) {
        return (int)symbol - 1072;
        //return (int)symbol - 65;
    }

    public char getSymbolCode(int code) {
        return (char)(code + 1072);
        //return (char)(code + 65);
    }

    public String listCharToString(List<Character> outputText){
        StringBuilder sb = new StringBuilder();
        for (Character symbol : outputText) { sb.append(symbol); }
        return sb.toString();
    }


}
