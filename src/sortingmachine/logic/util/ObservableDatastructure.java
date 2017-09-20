/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sortingmachine.logic.util;

/**
 *
 * @author domenik
 */
public interface ObservableDatastructure<T extends Comparable<T>> {
    void setValue(int index, T value);
    T getValue(int index);
    void swap(int index1, int index2);
    int size();
    void addObserver(DataStructureObserver observer);
    T getMax();
    T[] toArray();
}
