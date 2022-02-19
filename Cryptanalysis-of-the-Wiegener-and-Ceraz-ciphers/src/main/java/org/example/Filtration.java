package org.example;

public class Filtration implements IFiltration{

    @Override
    public String deleteAllNumbers(String inputString) {

        return inputString.replaceAll("\\d", "");
    }

    @Override
    public String removeAllSpaces(String inputString) {
        return inputString.replaceAll("\\s", "");
    }

    @Override
    public String removePunctuationAndCharacters(String inputString) {
        return inputString.replaceAll("[-+.^:,!№;%?*()_=@#${}|/'–]","");
    }

    @Override
    public String convertUppercaseLettersToLowercase(String inputString) {
        return inputString.toLowerCase();
        //return inputString;
    }

    public String filtrationText(String inputText){
        String text = this.deleteAllNumbers(inputText);
        text = this.removeAllSpaces(text);
        text = this.removePunctuationAndCharacters(text);
        text = this.convertUppercaseLettersToLowercase(text);
        return text;
    }
}
