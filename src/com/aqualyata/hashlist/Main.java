package com.aqualyata.hashlist;


import com.aqualyata.hashlist.chemestry.CountOfAtoms;

import java.util.HashMap;
import java.util.Map;

class Main {

    public static void main(String[] args) {
        HashMapi<String, Float> hashListExe = new HashMapi<>();
//        hashListExe.add("a", 12F);
//        hashListExe.add("a", hashListExe.getValue("a")+5);
//
//        System.out.println(hashListExe.getValue("a"));

        CountOfAtoms countOfAtomsExe = new CountOfAtoms();
        countOfAtomsExe.countAtoms("K4(ON(SH3H5)2)2");

    }

}
