/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortingmachine.logic;

import static java.lang.Thread.State.values;
import java.util.logging.Level;
import java.util.logging.Logger;
import sortingmachine.logic.util.ObservableArray;
import sortingmachine.logic.util.ObservableDatastructure;

/**
 *
 * @author domenik
 */
public class MergeSort<T extends Comparable<T>> extends SortingAlgorithm<T> {

    private T[] helper;

    public MergeSort(ObservableDatastructure<T> input, long delay) {
        super(input, delay);
    }

    @Override
    public ObservableDatastructure<T> sort() {
        helper = input.toArray().clone();
        mergesort(0, input.size() - 1);
        return input;
    }

   private void mergesort(int low, int high) {
    if (low < high) {
      int middle = low + (high - low) / 2;
      mergesort(low, middle);
      mergesort(middle + 1, high);
      merge(low, middle, high);
    }
  }

  private void merge(int low, int middle, int high) {
    for (int i = low; i <= high; i++) {
      helper[i] = input.getValue(i);
    }

    int i = low;
    int j = middle + 1;
    int k = low;

    while (i <= middle && j <= high) {
      if (helper[i].compareTo(helper[j]) <= 0) {
          input.setValue(k, helper[i]);
        i++;
      } else {
          input.setValue(k, helper[j]);
        j++;
      }
      k++;
        try {
            Thread.sleep(delay);
        } catch (InterruptedException ex) {
            Logger.getLogger(MergeSort.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    while (i <= middle) {
      input.setValue(k, helper[i]);
      k++;
      i++;
    }

  }
}
