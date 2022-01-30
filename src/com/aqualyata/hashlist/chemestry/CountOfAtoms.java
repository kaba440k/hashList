package com.aqualyata.hashlist.chemestry;

import com.aqualyata.hashlist.HashMapi;

import java.util.Arrays;

public class CountOfAtoms {

    public Object[] countAtoms(String formula) {
        String[] formulaArray = formula.split("");
        int counter = 0;
        String[] clearFormula = new String[formulaArray.length];
        int clearFormulaCounter = 0;
        boolean typeOfElement = false;
        for (String symbol : formulaArray) {
            if (symbol.equals("(")) {
                typeOfElement = true;
                break;
            }
        }

        if (typeOfElement) {
            while (counter + 1 < formulaArray.length) {
                String element = formulaArray[counter] + formulaArray[counter + 1];
                boolean currentLetterIsCapital = element.matches("[A-Z]{1}[a-z]{1}");
                boolean twoDigitNumber = element.matches("\\d{1}\\d{1}");

                if (currentLetterIsCapital || twoDigitNumber) {
                    clearFormula[clearFormulaCounter] = element;
                    counter += 1;
                } else {
                    clearFormula[clearFormulaCounter] = formulaArray[counter];

                }

                counter += 1;
                clearFormulaCounter += 1;
            }
            clearFormula[clearFormulaCounter] = formulaArray[counter];


        }
        System.out.println(Arrays.toString(clearFormula));
        createElement(clearFormula);
        return null;
    }

    public boolean createElement(String[] clearFormula) {
        String[] elForAdd = new String[10];
        int counterForEl = 0;
        int counterForAdd = 0;
        while (!clearFormula[counterForAdd].equals(")")) {
            counterForAdd += 1;
        }
        while (!clearFormula[counterForAdd].equals("(")) {
            elForAdd[counterForEl] = clearFormula[counterForAdd - 1];
            counterForAdd -= 1;
            counterForEl += 1;
        }
        elForAdd[counterForEl - 1] = null;
        System.out.println(Arrays.toString(elForAdd));
        addToStack(elForAdd);
        return false;

    }

    public void addToStack(String[] elForAdd) {
        int counter = 0;
        HashMapi<String, String> hashListObject = new HashMapi<>();
        while (elForAdd[counter] != null) {
            if (elForAdd[counter].matches("\\p{N}{1,}")) {
                String value = elForAdd[counter];
                counter += 1;
                String key = elForAdd[counter];
                counter += 1;
                if (hashListObject.containsKey(key)) {
                    int newValue = (Integer.parseInt(hashListObject.getValue(key)) + Integer.parseInt(value));
                    hashListObject.add(key, Integer.toString(newValue));}
//                } else {
//                     int newValue = (Integer.parseInt(hashListObject.getValue(key)) + Integer.parseInt(value));
//                     hashListObject.add(key, Integer.toString(newValue));
//                }
            } else {
                String value = "1";
                String key = elForAdd[counter];
                counter += 1;
                if (!hashListObject.containsKey(key)) {
                    hashListObject.add(key, value);
                } else {
                    int newValue = (Integer.parseInt(hashListObject.getValue(key)) + Integer.parseInt(value));
                    hashListObject.add(key, Integer.toString(newValue));
                }
            }
        }
    }
}
