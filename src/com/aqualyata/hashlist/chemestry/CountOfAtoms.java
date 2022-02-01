package com.aqualyata.hashlist.chemestry;

import com.aqualyata.hashlist.HashMapi;

import java.util.Arrays;

public class CountOfAtoms {
    public HashMapi<String, String> hashListObject = new HashMapi<>();

    public Object[] countAtoms(String formula) {
        String[] formulaArray = formula.split("");
        String[] clearFormula = new String[formulaArray.length];
        int clearFormulaCounter = 0;
        int counter = 0;
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
        typeOfSubstance(clearFormula);
        return null;
    }


    private void typeOfSubstance(String[] clearFormula) {
        boolean typeOfElement = false;
        for (String symbol : clearFormula) {
            if (symbol == null) {
                break;
            }
            if (symbol.equals("(")) {
                typeOfElement = true;
                break;
            }
        }
        if (!typeOfElement) {
            int counter = 1;
            hashListObject.add("H", "1");
            while (counter-1!=clearFormula.length) {
                if (counter== clearFormula.length) {
                    //todo понять почему на находится контенсКей во всех if
                    if (hashListObject.containsKey(clearFormula[counter - 1])) {
                        String currentValue = Integer.toString(Integer.parseInt(hashListObject.getValue(clearFormula[counter - 1])) + 1);
                        hashListObject.add(clearFormula[counter - 1], currentValue);
                    } else {
                        hashListObject.add(clearFormula[counter - 1], "1");
                    }
                } else if (clearFormula[counter].matches("\\d+")) {
                    if (hashListObject.containsKey(clearFormula[counter - 1])) {
                        String currentValue = Integer.toString(Integer.parseInt(hashListObject.getValue(clearFormula[counter - 1])) + Integer.parseInt(clearFormula[counter]));
                        hashListObject.add(clearFormula[counter - 1], currentValue);
                    } else {
                        hashListObject.add(clearFormula[counter - 1], clearFormula[counter]);
                        counter++;
                    }
                } else {
                    if (!hashListObject.containsKey(clearFormula[counter - 1])){
                        String currentValue = Integer.toString(Integer.parseInt(hashListObject.getValue(clearFormula[counter - 1])) + 1);
                        hashListObject.add(clearFormula[counter - 1], currentValue);
                    } else {
                        hashListObject.add(clearFormula[counter-1], "1");

                    }
                }
                counter++;
            }
        }
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

        while (elForAdd[counter] != null) {
            if (elForAdd[counter].matches("\\p{N}{1,}")) {
                String value = elForAdd[counter];
                counter += 1;
                String key = elForAdd[counter];
                counter += 1;
                if (hashListObject.containsKey(key)) {
                    int newValue = (Integer.parseInt(hashListObject.getValue(key)) + Integer.parseInt(value));
                    hashListObject.add(key, Integer.toString(newValue));
                }
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
