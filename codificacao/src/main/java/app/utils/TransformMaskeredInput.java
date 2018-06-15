package app.utils;

import java.util.ArrayList;

public class TransformMaskeredInput {
    private static char[] filteredCharacters = {'.',',','-','(',')',' '
            ,'"','!','$','#','%','&','*','_','+','=','[',']','^','`',
    ':','\\','/'};
    public static String format(String input){
        for(char character : filteredCharacters){
            input=input.replaceAll(String.valueOf("\\"+character),"");
        }
        return input;
    }

    public static String format(String input, ArrayList<String> skipCheck){
        for(char character : filteredCharacters){
            if(!skipCheck.contains(String.valueOf(character))) {
                input = input.replaceAll(String.valueOf("\\" + character), "");
            }
        }
        return input;
    }

    public static String format(String input, String replacement){
        for(char character : filteredCharacters){
            input=input.replaceAll(String.valueOf("\\"+character),replacement);
        }
        return input;
    }

    public static String format(String input, String replacement, ArrayList<String> skipCheck){
        for(char character : filteredCharacters){
            if(!skipCheck.contains(String.valueOf(character))) {
                input = input.replaceAll(String.valueOf("\\" + character), replacement);
            }
        }
        return input;
    }
}
