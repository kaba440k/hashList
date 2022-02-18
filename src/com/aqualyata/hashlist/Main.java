package com.aqualyata.hashlist;


import com.aqualyata.hashlist.chemestry.CountOfAtoms;

class Main {

    public static void main(String[] args) {
        HashMapi<String, Float> hashListExe = new HashMapi<>();
        CountOfAtoms countOfAtomsExe = new CountOfAtoms();
        System.out.println(countOfAtomsExe.addFormula("K4(ON(SO3)2)2HMg3"));

    }

}
