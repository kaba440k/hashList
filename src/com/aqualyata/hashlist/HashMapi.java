package com.aqualyata.hashlist;

import com.aqualyata.hashlist.base.Pair;
import com.aqualyata.hashlist.base.SimpleCollection;
import com.aqualyata.hashlist.exceptions.KeyExistException;

import java.util.Arrays;


public class HashMapi<K, V> implements SimpleCollection<K, V> {

    private static final int DEFAULT_SIZE = 5;
    private static final int MAX_COLLISION_COUNT = 3;

    @SuppressWarnings("unchecked")
    private Object[][] arr = new Object[DEFAULT_SIZE][MAX_COLLISION_COUNT];

    private int currentSize = DEFAULT_SIZE;

    ///////////////////////////////////////////////////////////////////////////
    //                          simpleCollection
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public int size() {
        int count = 0;
        for (Object[] b : arr) {
            for (Object a : b) {
                if (a != null) {
                    count += 1;
                }
            }
        }
        return count;
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }


    @Override
    public boolean contains(V value) {
        return false;
    }

    @Override
    public boolean containsKey(K key) {
        return false;
    }

    @Override
    public boolean add(K key, V value) {
        int counter = 0;
        int hash = key.hashCode();
        int newIndex = newIndex(hash);
        //сделать проверку на то, что counter > MAX_COLLISION_COUNT, тогда делается перерасчет DEFAULT_SIZE*2 и ищется новый hash и т д и тп если ты не вспомнишь ублюдок я сильно стукну себя коленом
        while (counter != MAX_COLLISION_COUNT && (Pair<K, V>) arr[newIndex][counter] != null) {
            if (((Pair<K, V>) arr[newIndex][counter]).getKey() != key) {
                counter += 1;
            } else {
                throw new KeyExistException();
            }
        }
        Pair<K, V> addElement = new Pair<K, V>(key, value);
        arr[newIndex][counter] = addElement;
        System.out.println(Arrays.deepToString(arr));
        return true;
    }

    @Override
    public boolean remove(K key) {
        int counter = 0;
        int hash = key.hashCode();
        int newIndex = newIndex(hash);
        while (counter != MAX_COLLISION_COUNT) {
            boolean isKeyFound = arr[newIndex][counter] != null
                    && ((Pair<K, V>) arr[newIndex][counter]).getKey().hashCode() == hash;
            if (isKeyFound) {
                break;
            } else {
                counter += 1;
            }
        }
        if (counter == MAX_COLLISION_COUNT) {
            return false;
        }
        arr[newIndex][counter] = null;
        System.out.println(Arrays.toString(arr[newIndex]));
        return true;
    }

    @Override
    public boolean removeValue(V value) {
        return false;
    }

    @Override
    public void clear() {

    }

    ///////////////////////////////////////////////////////////////////////////
    //                              private
    ///////////////////////////////////////////////////////////////////////////

    private int newIndex(int hash) {
        return hash & DEFAULT_SIZE - 1;
    }

    private int getNextSize(){
        return currentSize*2;
    }

    private Object[][] expand(){
        int nextSize = getNextSize();
        Object[][] expandedArr = new Object[nextSize][MAX_COLLISION_COUNT];
        return null;
    }

}

