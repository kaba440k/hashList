package com.aqualyata.hashlist;

import com.aqualyata.hashlist.base.Pair;
import com.aqualyata.hashlist.base.SimpleCollection;
import com.aqualyata.hashlist.exceptions.KeyExistException;

import java.util.Arrays;


public class HashMapi<K, V> implements SimpleCollection<K, V> {

    private static final int DEFAULT_SIZE = 5;
    private static final int MAX_COLLISION_COUNT = 10;

    @SuppressWarnings("unchecked")
    private Object[][] arr;

    private int currentSize;

    public HashMapi() {
        this.arr = new Object[DEFAULT_SIZE][MAX_COLLISION_COUNT];
        this.currentSize = DEFAULT_SIZE;
    }

    private HashMapi(int size) {
        this.arr = new Object[size][MAX_COLLISION_COUNT];
        this.currentSize = size;
    }


    ///////////////////////////////////////////////////////////////////////////
    //                          simpleCollection
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public int size() {
        int count = 0;
        for (Object[] row : arr) {
            for (Object column : row) {
                if (column != null) {
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
        for (Object[] row : arr) {
            for (Object column : row) {
                if (column != null) {
                    if (((Pair<K, V>) column).getValue().equals(value)) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    @Override
    public boolean containsKey(K key) {
        int hash = key.hashCode();
        int newIndex = newIndex(hash);
        for (Object element : arr[newIndex]) {
            if (element != null) {
                if (((Pair<K, V>) element).getKey() == key) {
                    return true;
                }
            }
        }
        return false;
//        while (counter != MAX_COLLISION_COUNT) {
//            boolean isKeyFound = arr[newIndex][counter] != null
//                    && ((Pair<K, V>) arr[newIndex][counter]).getKey().hashCode() == hash;
//            if (isKeyFound) {
//                return true;
//            } else {
//                counter += 1;
//            }
//        }
//        return false;
    }

    @Override
    public boolean add(K key, V value) {
        int counter = 0;
        int hash = key.hashCode();
        int newIndex = newIndex(hash);
        while (counter != MAX_COLLISION_COUNT && (Pair<K, V>) arr[newIndex][counter] != null) {
            if (((Pair<K, V>) arr[newIndex][counter]).getKey() != key) {
                counter += 1;
            } else {
                remove(key);
                add(key, value);
                return true;
            }
        }
        if (counter == MAX_COLLISION_COUNT) {
            expand();
            counter = 0;
            newIndex = newIndex(hash);
            while (counter != MAX_COLLISION_COUNT && (Pair<K, V>) arr[newIndex][counter] != null) {
                if (((Pair<K, V>) arr[newIndex][counter]).getKey() != key) {
                    counter += 1;
                } else {
                    throw new KeyExistException();
                }
            }
        }
        Pair<K, V> addElement = new Pair<K, V>(key, value);
        arr[newIndex][counter] = addElement;
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
        return true;
    }

    @Override
    public boolean removeValue(V value) {
        boolean check = false;
        int counterRow = 0;
        for (Object[] row : arr) {
            int counterColumn = 0;
            for (Object column : row) {
                if (column != null) {
                    if (((Pair<K, V>) arr[counterRow][counterColumn]).getValue().equals(value)) {
                        arr[counterRow][counterColumn] = null;
                        check = true;
                    }
                    counterColumn += 1;
                }
            }
            counterRow += 1;
        }
        if (check = true) {
            return true;
        }
        return false;
    }

    @Override
    public void clear() {
        Arrays.fill(arr, null);
    }

    @Override
    public V getValue(K key) {
        int hash = key.hashCode();
        int newIndex = newIndex(hash);
        for (Object element : arr[newIndex]) {
            if (element != null) {
                if (((Pair<K, V>) element).getKey() == key) {
                    return ((Pair<K, V>) element).getValue();
                }
            }
        }
        return null;
    }


    ///////////////////////////////////////////////////////////////////////////
    //                              private
    ///////////////////////////////////////////////////////////////////////////

    private int newIndex(int hash) {
        return hash % (currentSize - 1);
    }

    private int getNextSize() {
        return currentSize * 2;
    }

    private void expand() {
        int nextSize = getNextSize();
        HashMapi<K, V> expandedList = new HashMapi<>(nextSize);
        for (Object[] row : arr) {
            for (Object column : row) {
                if (column != null) {
                    Pair<K, V> pair = (Pair<K, V>) column;
                    expandedList.add(pair.getKey(), pair.getValue());
                }
            }
        }
        clear();
        this.arr = expandedList.getObjArr();
        this.currentSize = nextSize;
    }

    private Object[][] getObjArr() {
        return arr;
    }

}

