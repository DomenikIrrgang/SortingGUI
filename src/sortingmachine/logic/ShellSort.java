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
public class ShellSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    public ShellSort(ObservableDatastructure<T> input, long delay) {
        super(input, delay);
    }

    @Override
    public ObservableDatastructure<T> sort() {
        return null;
    }

}
