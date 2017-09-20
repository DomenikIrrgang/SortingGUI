/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortingmachine.logic;

import java.util.logging.Level;
import java.util.logging.Logger;
import sortingmachine.logic.util.ObservableDatastructure;

/**
 *
 * @author domenik
 */
public class InsertionSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    public InsertionSort(ObservableDatastructure<T> input, long delay) {
        super(input, delay);
    }

    @Override
    public ObservableDatastructure<T> sort() {

        T temp;
        for (int i = 1; i < input.size(); i++) {
            temp = input.getValue(i);
            int j = i;
            while (j > 0 && input.getValue(j - 1).compareTo(temp) > 0) {
                input.setValue(j, input.getValue(j - 1));
                j--;
            }
            input.setValue(j, temp);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(InsertionSort.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return input;

    }

}
