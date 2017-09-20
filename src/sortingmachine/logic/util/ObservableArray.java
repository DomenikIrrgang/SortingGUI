/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sortingmachine.logic.util;

/**
 *
 * @author domenik
 * @param <T>
 */
public class ObservableArray<T extends Comparable<T>> implements ObservableDatastructure<T>{
    
    private T[] input;
    private DataStructureObserver observer;
    
    public ObservableArray(T[] input) {
        this.input = input;
    }

    @Override
    public void setValue(int index, T value) {
        input[index] = value;
        observer.notify(index, DatastructureAccess.WRITE);
    }

    @Override
    public void swap(int index1, int index2) {
        T tmp = getValue(index2);
        setValue(index2, getValue(index1));
        setValue(index1, tmp);
    }

    @Override
    public int size() {
        return input.length;
    }

    @Override
    public void addObserver(DataStructureObserver observer) {
       this.observer = observer;
    }

    @Override
    public T getValue(int index) {
        observer.notify(index, DatastructureAccess.READ);
        return input[index];
    }

    @Override
    public T getMax() {
        T tmp = input[0];
        for (T t : input) {
            if (tmp.compareTo(t) < 0) {
                tmp = t;
            }
        }
        return tmp;
    }

    @Override
    public T[] toArray() {
        return input;
    }
    
}
