package com.aqualyata.hashlist;



class Main {

    public static void main(String[] args) {
        HashMapi<String, Float> hashListExe = new HashMapi<>();

        System.out.println(hashListExe.size());
//        System.out.println(hashListExe.MAX_COLLISION_COUNT);
        hashListExe.add("a", 12f);
        hashListExe.add("b", 12f);
        hashListExe.add("c", 14f);
        System.out.println(hashListExe.removeValue(12f));
//        HashMapi<String, Float> hashListExe2 = new HashMapi<>();
//        System.out.println(hashListExe2.MAX_COLLISION_COUNT);
//        hashListExe2.MAX_COLLISION_COUNT = 5;
//        System.out.println(hashListExe2.MAX_COLLISION_COUNT);
//        System.out.println(hashListExe.MAX_COLLISION_COUNT);










        System.out.println(hashListExe.size());

    }

}
