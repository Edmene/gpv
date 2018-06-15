package app.utils;

public class TransformMaskeredInput {
    private static char[] filteredCharacters = {'.',',','-','(',')',' '};
    public static String format(String input){
        for(char character : filteredCharacters){
            input=input.replaceAll(String.valueOf("\\"+character),"");
        }
        return input;
    }

    public static String format(String input, String replacement){
        for(char character : filteredCharacters){
            input=input.replaceAll(String.valueOf("\\"+character),replacement);
        }
        return input;
    }
}
