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
public abstract class SortingAlgorithm<T extends Comparable<T>> {
    
    protected ObservableDatastructure<T> input;
    protected long delay;
    
    public SortingAlgorithm(ObservableDatastructure<T> input, long delay) {
       this.input = input;
       this.delay = delay;
    }

    public abstract ObservableDatastructure<T> sort();
}
