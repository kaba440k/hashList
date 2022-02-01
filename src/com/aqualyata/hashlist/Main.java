package com.aqualyata.hashlist;


import com.aqualyata.hashlist.chemestry.CountOfAtoms;

import java.util.HashMap;
import java.util.Map;

class Main {

    public static void main(String[] args) {
        HashMapi<String, Float> hashListExe = new HashMapi<>();
        hashListExe.add("a", 12F);
        System.out.println(hashListExe.containsKey("a"));


        System.out.println(hashListExe.getValue("a"));

        CountOfAtoms countOfAtomsExe = new CountOfAtoms();
        countOfAtomsExe.countAtoms("HHH");

    }

}
