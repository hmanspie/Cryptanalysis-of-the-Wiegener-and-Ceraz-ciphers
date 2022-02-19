package org.example;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HackWindowController {

    public TextArea incomingText;
    public TextArea outputingText;
    public TextField resultTextField;
    public TextField lenghtKeyTextField;
    public TextField fragmentCount;

    public char [] segment;
    public List<Segment> segments;


    public void crackButtonClick(ActionEvent actionEvent) {
        int count = 0;
        int fragCount = 0;
        segments = new ArrayList<>();
        try {
             fragCount = Integer.parseInt(fragmentCount.getText());
        } catch (NumberFormatException e){ outputingText.setText("Не коректно введений сегмент!!!");}
        segment = new char[fragCount];
        StartAppView.encryption.setInputText(new Filtration().filtrationText(incomingText.getText()));
        char [] arrSymbol = StartAppView.encryption.getInputText().toCharArray();
        segments = searchSegment(segments, segment, arrSymbol, fragCount);
        int [] differenceDistances = new int[segments.size()];
        for (Segment s: segments) {
            differenceDistances[count++] = s.getPositionTwo() - s.getPositionOne();
        }
        if(differenceDistances.length > 0) {
            int keyLenght = GCD.findGCD(differenceDistances);
            int[] keys = decryptCipher(keyLenght, StartAppView.encryption.getInputText());
            String strKey = "";
            for (int i = 0; i < keys.length; i++) {
                strKey += keys[i] + " ";
            }
            resultTextField.setText(strKey);
            lenghtKeyTextField.setText(String.valueOf(keyLenght));
            WiegenerEncryption wiegenerEncryption = new WiegenerEncryption(StartAppView.encryption.getInputText(), keys);
            outputingText.setText(wiegenerEncryption.decryption());
        }
    }
    public static List<Segment> searchSegment(List<Segment> segments, char [] segment,  char [] arrSymbol, int fragCount) {

        for(int i = 0; i < arrSymbol.length - fragCount; i++) {
            for (int k = 0; k < fragCount; k++) {
                segment[k] = arrSymbol[i + k];
            }
            int flag = 0;
            for (int j = i; j < arrSymbol.length - fragCount - 3; j++) {
                flag = 0;
                for (int l = 0; l < fragCount; l++) {

                    if (segment[l] == arrSymbol[j + l + 3]) {
                        flag++;
                    }
                }
                if (flag == fragCount) {
                    String str = "";
                    for (int l = 3; l < fragCount + 3; l++) {
                        str += String.valueOf(arrSymbol[j + l]);
                    }
                    segments.add(new Segment(str, i, j + 3));
                }
            }
        }
        return segments;
    }

    public static int[] decryptCipher(int keyLength, String text) {
        int[] key = new int[keyLength];
        double[] probability = new double[] { 0.080,  0.016,  0.045,   0.017,   0.030,   0.085,  0.009,  0.016,
                                              0.073,  0.012,  0.0348,  0.0434,  0.032,   0.067,  0.1098, 0.028,
                                              0.0474, 0.0547, 0.0631,  0.026,   0.0026,  0.0096, 0.0048, 0.0145,
                                              0.0071, 0.0036, 0.00037, 0.01898, 0.01735, 0.0033, 0.0063, 0.02001 };
        ArrayList<String> groups = groupText(text, keyLength);
        for (int i = 0; i < keyLength; ++i) {
            double MG;
            double maxMG = 0;
            for (int g = 0; g < 32; ++g) {
                String subCipher = groups.get(i);
                Map<Character, Integer> frequencies = countEveryWord(subCipher);
                MG = transvection(frequencies, probability, g, subCipher.length());
                if (MG >= maxMG) {
                    maxMG = MG;
                    key[i] = g;
                }
            }
        }
        return key;
    }

    public static ArrayList<String> groupText(String text, int keyLength) {
        ArrayList<String> groups = new ArrayList<String>();
        for (int i = 0; i < keyLength; ++i) {
            StringBuilder tempGroup = new StringBuilder();
            for (int j = 0; i + j * keyLength < text.length(); ++j) {
                tempGroup.append(text.charAt(i + j * keyLength));
            }
            groups.add(tempGroup.toString());
        }
        return groups;
    }

    public static Map<Character, Integer> countEveryWord(String text) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < 32; ++i) {
            map.put((char) (i + 1072), 0);
        }

        for (int i = 0; i < text.length(); ++i) {
            char current = text.charAt(i);
            map.put(current, map.get(current) + 1);
        }
        return map;
    }

    public static double transvection(Map<Character, Integer> frequencies, double[] w, int g, int subTextLength) {
        double MG = 0.0D;
        for (char ch = 'а'; ch <= 'я'; ++ch) {
            double fi = frequencies.get(ch) * 1.0 / subTextLength;
            int index = mod(0, 32, ch - 'а' - g);
            MG += fi * w[index];
        }
        return MG;
    }

    public static int mod(int start,int end,int result) {
        int res;
        int difference = end - start;
        int resultFromStart = result - start;
        res = start + resultFromStart % difference;
        while(res < start) {
            res += difference;
        }
        return res;
    }
}
