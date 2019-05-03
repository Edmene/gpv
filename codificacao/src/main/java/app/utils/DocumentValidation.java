package app.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class DocumentValidation {
    private final ArrayList<Integer>  cpfFirstDigitWeight = new ArrayList<>(Arrays.asList(10,9,8,7,6,5,4,3,2));
    private final ArrayList<Integer>  cpfSecondDigitWeight = new ArrayList<>(Arrays.asList(11,10,9,8,7,6,5,4,3,2));

    private final ArrayList<Integer> cnhDigitWeight = new ArrayList<>(Arrays.asList(9,8,7,6,5,4,3,2,1));
    private final ArrayList<String> blacklist = new ArrayList<>(Arrays.asList("00000000000", "11111111111", "22222222222", "33333333333", "44444444444",
            "55555555555", "66666666666", "77777777777", "88888888888", "99999999999"));

    public boolean validateCpf(String cpf){

        if(blacklist.contains(cpf)){
            return false;
        }

        String digits = cpf.substring(0,8);
        String validationDigits = cpf.substring(9,10);

        Integer calcDigitOne = digitCalculation(digits, cpfFirstDigitWeight, 11, true);
        Integer calcDigitTwo = digitCalculation(digits.concat(String.valueOf(calcDigitOne)), cpfSecondDigitWeight, 11, true);

        String validation = calcDigitOne + String.valueOf(calcDigitTwo);

        return validation == validationDigits;
    }

    public boolean validateChn(String cnh){
        String digits = cnh.substring(0,8);
        String validationDigits = cnh.substring(9,10);
        int descDig2 = 0;

        if(blacklist.contains(cnh)) {
            return false;
        }

        Integer calcDigitOne = digitCalculation(digits, cnhDigitWeight, 11, false);
        if(calcDigitOne >= 10){
            descDig2 = 2;
            calcDigitOne = 0;
        }
        List cnhDigitWeightList = Arrays.asList(cnhDigitWeight.toArray());
        Collections.reverse(cnhDigitWeightList);
        Integer calcDigitTwo = digitCalculation(digits, cnhDigitWeightList, 11, false);
        if (calcDigitTwo >= 10){
            calcDigitTwo = 0;
        }
        else{
            calcDigitTwo -= descDig2;
        }

        String validation = calcDigitOne + String.valueOf(calcDigitTwo);

        return validation == validationDigits;
    }

    // BaseMinus signals what type of return will be, either base minus mod of sum or mod of sum.

    private Integer digitCalculation(String digits, List<Integer> weightMash, Integer base, boolean baseMinus){
        int total = 0;
        for (int i = 0; i < digits.length(); i++){
            total += (Integer.parseInt(String.valueOf(digits.charAt(i))) * weightMash.get(i));
        }
        if(baseMinus) {
            return base - (total % base);
        }
        else{
            return total % base;
        }
    }
}
