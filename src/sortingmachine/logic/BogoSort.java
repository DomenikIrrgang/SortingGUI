/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sortingmachine.logic;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import sortingmachine.logic.util.ObservableDatastructure;

/**
 *
 * @author domenik
 */
public class BogoSort<T extends Comparable<T>> extends SortingAlgorithm<T> {
    private Random random = new Random();
    private int count;

    public BogoSort(ObservableDatastructure<T> input, long delay) {
        super(input, delay);
    }

    @Override
    public ObservableDatastructure<T> sort() {
        while (!isSorted()) {
            assignRandomOrder();
            try {
                Thread.sleep(delay);
            } catch (InterruptedException ex) {
                Logger.getLogger(BogoSort.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return input;
    }
    
    private void assignRandomOrder() {
            input.swap(random.nextInt(input.size()), random.nextInt(input.size()));   
    }
    
    private boolean isSorted() {
        for (int i = 1; i < input.size(); i++) {
            if (input.getValue(i-1).compareTo(input.getValue(i)) > 0) {
                return false;
            }
        }
        return true;
    }
    
}
