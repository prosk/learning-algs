package com.mycompany.tagirvaleev.concurrency;

// Initialization on demand holder idiom
// https://www.youtube.com/live/5YLA29EybMo?feature=share
public class HolderIdiomSingleton {
    int x = 5;

    public static void main(String[] args) {
        System.out.println(HolderIdiomSingleton.getInstance().x);
    }

    static HolderIdiomSingleton getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        static final HolderIdiomSingleton INSTANCE = new HolderIdiomSingleton();
    }
}
