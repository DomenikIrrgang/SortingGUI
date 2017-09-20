/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortingmachine.GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import sortingmachine.logic.BogoSort;
import sortingmachine.logic.BubbleSort;
import sortingmachine.logic.InsertionSort;
import sortingmachine.logic.MergeSort;
import sortingmachine.logic.QuickSort;
import sortingmachine.logic.SortingAlgorithm;
import sortingmachine.logic.util.ObservableArray;
import sortingmachine.logic.util.ObservableDatastructure;

/**
 *
 * @author domenik
 */
public class MainFrame extends JFrame {

    private SortingCanvas canvas;
    private SortingAlgorithm<Integer> sorter;
    
    private SortThread sortThread;
    private RenderThread renderThread;

    private Panel optionPane;
    private JButton quickSortButton;
    private JButton insertionSortButton;
    private JButton bubbleSortButton;
    private JButton bogoSortButton;
    private JButton mergeSortButton;
    
    private JTextField delayInput;
    private JTextField countInput;

    private long delay = 1;
    private int maxValue = 10000;
    private int count = 30;
    
    private long startTime = 0;
    private long endTime = 0;

    public MainFrame() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLayout(new BorderLayout());

        canvas = new SortingCanvas(getRandomNumbers(maxValue, count));
        canvas.setVisible(false);

        getContentPane().add(BorderLayout.CENTER, canvas);
        setVisible(true);

        optionPane = new Panel();
        optionPane.setLayout(new GridLayout(10, 1));
        optionPane.setPreferredSize(new Dimension(150, getHeight()));

        quickSortButton = new JButton();
        quickSortButton.addMouseListener(new QuickSortClick());
        quickSortButton.setText("Quicksort");
        
        bubbleSortButton = new JButton();
        bubbleSortButton.addMouseListener(new BubbleSortClick());
        bubbleSortButton.setText("Bubblesort");
        
        insertionSortButton = new JButton();
        insertionSortButton.addMouseListener(new InsertionSortClick());
        insertionSortButton.setText("Insertionsort");
        
        bogoSortButton = new JButton();
        bogoSortButton.addMouseListener(new BogoSortClick());
        bogoSortButton.setText("Bogosort");
        
        mergeSortButton = new JButton();
        mergeSortButton.addMouseListener(new MergeSortClick());
        mergeSortButton.setText("Mergesort");
        
        delayInput = new JTextField("Delay");
        delayInput.setPreferredSize(new Dimension(100, 10));
        delayInput.setText("0");
        
        countInput = new JTextField("Count");
        countInput.setPreferredSize(new Dimension(100, 10));
        countInput.setText("100");

        optionPane.add(quickSortButton);
        optionPane.add(mergeSortButton);
        optionPane.add(bubbleSortButton);
        optionPane.add(insertionSortButton);
        optionPane.add(bogoSortButton);
        optionPane.add(delayInput);
        optionPane.add(countInput);
        getContentPane().add(BorderLayout.EAST, optionPane);

        setExtendedState(MAXIMIZED_BOTH);
    }

    private void startSort() {
        
        if (sortThread != null) {
            sortThread.suspend();
            renderThread.suspend();
        }
        
        canvas.setVisible(true);
        pack();
        sortThread = new SortThread();
        renderThread = new RenderThread();
        renderThread.start();
        startTime = System.nanoTime();
        sortThread.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
       
    }

    private ObservableDatastructure getRandomNumbers(int maxValue, int count) {
        Random random = new Random();
        Integer[] result = new Integer[count];
        for (int i = 0; i < count; i++) {
            result[i] = random.nextInt(maxValue) + 1;
        }
        return new ObservableArray(result);
    }

    private class QuickSortClick extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            count = Integer.parseInt(countInput.getText());
            canvas.setValues(getRandomNumbers(maxValue, count));
            delay = Integer.parseInt(delayInput.getText());
            sorter = new QuickSort<Integer>(canvas.getValues(), delay);
            startSort();
        }

    }
    
    private class MergeSortClick extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            count = Integer.parseInt(countInput.getText());
            canvas.setValues(getRandomNumbers(maxValue, count));
            delay = Integer.parseInt(delayInput.getText());
            sorter = new MergeSort<Integer>(canvas.getValues(), delay);
            startSort();
        }

    }
    
    private class BubbleSortClick extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            count = Integer.parseInt(countInput.getText());
            canvas.setValues(getRandomNumbers(maxValue, count));
            delay = Integer.parseInt(delayInput.getText());
            sorter = new BubbleSort<Integer>(canvas.getValues(), delay);
            startSort();
        }

    }
    
    private class BogoSortClick extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            count = Integer.parseInt(countInput.getText());
            canvas.setValues(getRandomNumbers(maxValue, count));
            delay = Integer.parseInt(delayInput.getText());
            sorter = new BogoSort<Integer>(canvas.getValues(), delay);
            startSort();
        }

    }
    
    private class InsertionSortClick extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            count = Integer.parseInt(countInput.getText());
            canvas.setValues(getRandomNumbers(maxValue, count));
            delay = Integer.parseInt(delayInput.getText());
            sorter = new InsertionSort<Integer>(canvas.getValues(), delay);
            startSort();
        }

    }

    private class SortThread extends Thread {

        @Override
        public void run() {
            sorter.sort();
            endTime = System.nanoTime();
            renderThread.suspend();
            setTitle((endTime - startTime) / 1000000 + "." + (endTime - startTime) % 1000000 + " ms");
        }
    }
    
    private class RenderThread extends Thread {

        private long lastFrame;
        private int fps = 30;
        
        @Override
        public void run() {
            lastFrame = System.currentTimeMillis();
            while (true) {
                if (lastFrame + 1000 / fps >= System.currentTimeMillis()) {
                    canvas.repaint();
                    lastFrame = System.currentTimeMillis();
                }
            }
            
        }
    }

}
