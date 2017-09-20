/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortingmachine.logic;

import sortingmachine.logic.util.ObservableDatastructure;

/**
 *
 * @author domenik
 */
public class QuickSort<T extends Comparable<T>> extends SortingAlgorithm<T>{
    
    public QuickSort(ObservableDatastructure<T> input, long delay) {
        super(input, delay);
    }

    @Override
    public ObservableDatastructure<T> sort() {
        qSort(input, 0, input.size() - 1);
        return input;
    }
    
    public void qSort(ObservableDatastructure<T> x, int links, int rechts) {
        if (links < rechts) {
            int i = partition(x, links, rechts);
            qSort(x, links, i - 1);
            qSort(x, i + 1, rechts);
        }
    }

    public int partition(ObservableDatastructure<T> x, int links, int rechts) {
        T pivot;
        int i, j, help;
        pivot = x.getValue(rechts);
        i = links;
        j = rechts - 1;
        while (i <= j) {
            if (x.getValue(i).compareTo(pivot) > 0) {
                x.swap(i, j);
                j--;
            } else {
                i++;
            }
        }
        x.swap(i, rechts);
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
        }

        return i;
    }

}
