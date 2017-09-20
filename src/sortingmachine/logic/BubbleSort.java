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
public class BubbleSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    public BubbleSort(ObservableDatastructure<T> input, long delay) {
        super(input, delay);
    }

    @Override
    public ObservableDatastructure<T> sort() {
        int counter = 0;
        boolean hasSwapped = false;
        T tmp;
        for (int i = 0; i < input.size(); i++) {
            hasSwapped = false;
            for (int j = 0; j < input.size() - 1 - counter; j++) {
                if (input.getValue(j).compareTo(input.getValue(j + 1)) > 0) {
                    input.swap(j, j + 1);
                    hasSwapped = true;   
                    try {
                        Thread.sleep(delay);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(BubbleSort.class.getName()).log(Level.SEVERE, null, ex);
                    }  
                }
            }

            if (!hasSwapped) {
                break;
            }

            counter++;
        }
        return input;
    }

}
