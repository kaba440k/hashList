package com.aqualyata.hashlist.chemestry;

import com.aqualyata.hashlist.HashMapi;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CountOfAtoms {
    public HashMapi<String, Integer> hashListObject = new HashMapi<>();
    private final String combinationRegex = "[A-Z][a-z]|\\d{2,}";
    private final String numberRegex = "\\d+";
    private final String voidRegex = "";

    // TODO: 03.02.2022 заменить все листы строк в arraylist во всех методах! -
    public String addFormula(String formula) {
        if (typeOfSubstance(createClearFormula(formula))) {
            parceDifficultFormula(createClearFormula(formula));
        } else {
            parceSimpleFormula(createClearFormula(formula), 1);
        }
        return hashListObject.toString();
    }


    private ArrayList<String> createClearFormula(String formula) {
        ArrayList<String> clearFormulaArr = new ArrayList<>();
        List<String> formulaArray = Arrays.asList(formula.split(voidRegex));
        for (int i = 0; i < formulaArray.size(); i++) {
            if (i + 1 == formulaArray.size()) {
                clearFormulaArr.add(formulaArray.get(i));
                break;
            }
            String element = formulaArray.get(i) + formulaArray.get(i + 1);
            if (element.matches(combinationRegex)) {
                //todo создать regex в начале файла +
                clearFormulaArr.add(element);
                i++;
            } else {
                clearFormulaArr.add(formulaArray.get(i));
            }
        }
        return clearFormulaArr;
        //todo return clearFormula +
    }
    //todo с 41 по 61 вынести все в отдельную функцию, а countAtoms сделать мейном +

    private boolean typeOfSubstance(ArrayList<String> clearFormulaArr) {
        //todo метод переделать в bool и в зависимости от него в мейн файле нужно вызывать след методы +
        return clearFormulaArr.contains("(");
    }

    private void parceSimpleFormula(ArrayList<String> clearFormulaArr, int coef) {
        String[] clearFormula = clearFormulaArr.toArray(new String[0]);
        //todo переименовать методы false и true в parceDifficultFormula и Simple +
        for (int counter = 1; counter - 1 != clearFormula.length; counter++) {
            // TODO: 03.02.2022 переделать в for +
            if (counter == clearFormula.length) {
                if (hashListObject.containsKey(clearFormula[counter - 1])) {
                    Integer currentValue = hashListObject.getValue(clearFormula[counter - 1]) + coef;
                    hashListObject.add(clearFormula[counter - 1], currentValue);
                } else {
                    hashListObject.add(clearFormula[counter - 1], coef);
                }
            } else if (clearFormula[counter].matches(numberRegex)) {
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
        }
    }

    private void parceDifficultFormula(ArrayList<String> clearFormulaArr) {
        String[] clearFormula = clearFormulaArr.toArray(new String[0]);
        int coef = 1;
        int step = 0;
        int anyCoef = 0;
        boolean checkStep = false;
        for (int i = 0; !checkStep; i++) {
            if (clearFormula[i].equals("(")) {
                step++;
            }
            if (clearFormula[i].equals(")")) {
                step--;
                if (step == 0) {
                    checkStep = true;
                    if (i + 1 != clearFormula.length) {
                        anyCoef = i + 1;
                    } else {
                        clearFormula = clearFormulaArr.toArray(new String[clearFormulaArr.size() + 1]);
                        clearFormula[clearFormula.length - 1] = "1";
                        anyCoef = clearFormula.length - 1;
                    }
                }
            }
        }
        int lastSymbol = anyCoef;
        // TODO: 03.02.2022 превратить в for +
        for (int counterForAdd = 0; counterForAdd <= clearFormula.length; counterForAdd++) {
            if (counterForAdd != 0) {
                if (clearFormula[counterForAdd - 1].equals(")")) {
                    if (lastSymbol != clearFormula.length && clearFormula[lastSymbol].matches(numberRegex)) {
                        ArrayList<String> listElement = new ArrayList<>();
                        for (int i = lastSymbol + 1; i < clearFormula.length; i++) {
                            listElement.add(clearFormula[i]);
                        }
                        parceSimpleFormula(listElement, 1);
                        break;
                    } else if (lastSymbol != clearFormula.length && !clearFormula[lastSymbol].matches(numberRegex)) {
                        ArrayList<String> listElement = new ArrayList<>();
                        for (int i = lastSymbol; i < clearFormula.length; i++) {
                            listElement.add(clearFormula[i]);
                        }
                        parceSimpleFormula(listElement, 1);
                        break;
                    } else {
                        break;
                    }
                }
            }
            ArrayList<String> list = new ArrayList<>();
            while (!clearFormula[counterForAdd].equals("(")) {
                if (clearFormula[counterForAdd].equals(")")) {
                    break;
                }
                list.add(clearFormula[counterForAdd]);
                counterForAdd++;
            }
//            String[] firstElement = list.toArray(new String[0]);
            parceSimpleFormula(list, coef);
            if (clearFormula[anyCoef].matches(numberRegex)) {
                // TODO: 03.02.2022 обьявить все regex вверху +
                coef = Integer.parseInt(clearFormula[anyCoef]) * coef;
                anyCoef -= 2;

            }
        }
    }
}