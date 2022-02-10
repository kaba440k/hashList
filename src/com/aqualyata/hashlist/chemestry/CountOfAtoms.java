package com.aqualyata.hashlist.chemestry;

import com.aqualyata.hashlist.HashMapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountOfAtoms {
    public HashMapi<String, Integer> hashListObject = new HashMapi<>();

    // TODO: 03.02.2022 заменить все листы строк в arraylist во всех методах!
    public String main(String formula){

        typeOfSubstance(countAtoms(formula));
        return hashListObject.toString();
    }


    public ArrayList<String> countAtoms(String formula) {
        int counter = 0;
        ArrayList<String> clearFormulaArr = new ArrayList<>();
        List<String> formulaArray = Arrays.asList(formula.split(""));
        while (counter<formulaArray.size()){
            //todo while->for
            if(counter+1== formulaArray.size()){
                clearFormulaArr.add(formulaArray.get(counter));
                break;
            }
            String element = formulaArray.get(counter) + formulaArray.get(counter + 1);
            if (element.matches("[A-Z][a-z]|\\d{2,}")) {
                //todo создать regex в начале файла
                clearFormulaArr.add(element);
                counter++;
            } else {
                clearFormulaArr.add(formulaArray.get(counter));
            }
            counter++;
        }
       return clearFormulaArr;
        //todo return clearFormula
    }
    //todo с 41 по 61 вынести все в отдельную функцию, а countAtoms сделать мейном
    
    private void typeOfSubstance(ArrayList<String> clearFormulaArr) {
        //todo метод переделать в bool и в зависимости от него в мейн файле нужно вызывать след методы
        String[] clearFormula = clearFormulaArr.toArray(new String[0]);
        for (String symbol : clearFormula) {
            if (symbol.equals("(")) {
                hooksIsTrue(clearFormula);
                break;
            }
        }
        int constantFactor = 1;
        hooksIsFalse(clearFormula, constantFactor);
    }

    private void hooksIsFalse(String[] clearFormula, int coef) {
        //todo переименовать методы false и true в parceDifficultFormula и Simple
        int counter = 1;
        while (counter - 1 != clearFormula.length && clearFormula[counter - 1] != null) {
            // TODO: 03.02.2022 переделать в for 
            if (counter == clearFormula.length) {
                if (hashListObject.containsKey(clearFormula[counter - 1])) {
                    Integer currentValue = hashListObject.getValue(clearFormula[counter - 1]) + coef;
                    hashListObject.add(clearFormula[counter - 1], currentValue);
                } else {
                    hashListObject.add(clearFormula[counter - 1], coef);
                }
            } else if (clearFormula[counter].matches("\\d+")) {
                if (hashListObject.containsKey(clearFormula[counter - 1])) {
                    Integer currentValue = hashListObject.getValue(clearFormula[counter - 1]) + Integer.parseInt(clearFormula[counter]) * coef;
                    hashListObject.add(clearFormula[counter - 1], currentValue);
                    counter++;
                } else {
                    hashListObject.add(clearFormula[counter - 1], Integer.parseInt(clearFormula[counter]) * coef);
                    counter++;
                }
            } else {
                if (hashListObject.containsKey(clearFormula[counter - 1])) {
                    Integer currentValue = hashListObject.getValue(clearFormula[counter - 1]) + coef;
                    hashListObject.add(clearFormula[counter - 1], currentValue);
                } else {
                    hashListObject.add(clearFormula[counter - 1], coef);
                }
            }
            counter++;
        }
    }


    private void hooksIsTrue(String[] clearFormula) {
        int counterForAdd = 0;
        int coef = 1;
        int anyCoef = clearFormula.length - 1;
        while (counterForAdd<=clearFormula.length-1) {
            // TODO: 03.02.2022 превратить в for 
            if (counterForAdd!=0){
                if (clearFormula[counterForAdd-1].equals(")")){
                    break;
                }
            }
            ArrayList<String> list = new ArrayList<>();
            while (!clearFormula[counterForAdd].equals("(")) {
                if (clearFormula[counterForAdd].equals(")")){
                    break;
                }
                list.add(clearFormula[counterForAdd]);
                counterForAdd ++;
            }
            String[] firstElement = list.toArray(new String[0]);
            hooksIsFalse(firstElement, coef);
            if (clearFormula[anyCoef].matches("\\d+")){
                // TODO: 03.02.2022 обьявить все regex вверху 
                coef = Integer.parseInt(clearFormula[anyCoef]) * coef;
                anyCoef-=2;
            } else {
                anyCoef-=1;
            }
            counterForAdd += 1;
        }
    }
}