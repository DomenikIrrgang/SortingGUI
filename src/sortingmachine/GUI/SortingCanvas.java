/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortingmachine.GUI;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import sortingmachine.logic.util.DataStructureObserver;
import sortingmachine.logic.util.DatastructureAccess;
import sortingmachine.logic.util.ObservableDatastructure;

/**
 *
 * @author domenik
 */
public class SortingCanvas extends Canvas implements DataStructureObserver {

    private ObservableDatastructure<Integer> data;
    public int changedPosition = -1;
    public int readPosition = -1;
    public Integer maxValue;
    private Graphics offgc;
    private Image offscreen;
    private Dimension d = getSize();
    private int barWidth;
    private double barHeight;

    public SortingCanvas(ObservableDatastructure<Integer> data) {
        setValues(data);
    }

    public ObservableDatastructure<Integer> getValues() {
        return data;
    }

    public void setValues(ObservableDatastructure<Integer> data) {
        this.data = data;
        this.data.addObserver(this);
        maxValue = data.getMax();
    }

    @Override
    public void paint(Graphics g) {

        barWidth = getWidth() / data.size();
        for (int i = 0; i < data.size(); i++) {
            if (i == changedPosition) {
                g.setColor(Color.RED);
                barHeight = ((double) data.getValue(i) / (double) maxValue) * getHeight();
                g.fillRect(i * barWidth + 1, (int) (getHeight() - barHeight), barWidth, (int) (barHeight - 1));
            } else {
                g.setColor(Color.blue);
                barHeight = ((double) data.getValue(i) / (double) maxValue) * getHeight();
                g.fillRect(i * barWidth + 1, (int) (getHeight() - barHeight), barWidth, (int) (barHeight - 1));
            }
            
            g.setColor(Color.BLACK);
            g.drawRect(i * barWidth + 1, (int) (getHeight() - barHeight), barWidth, (int) (barHeight - 1));
        }
    }

    @Override
    public void update(Graphics g) {
        d = getSize();

        offscreen = createImage(d.width, d.height);
        offgc = offscreen.getGraphics();
        offgc.setColor(getBackground());
        offgc.fillRect(0, 0, d.width, d.height);
        offgc.setColor(getForeground());
        paint(offgc);
        g.drawImage(offscreen, 0, 0, this);
    }

    @Override
    public void notify(int index, DatastructureAccess type) {
        switch (type) {
            case READ:
                readPosition = index;
                break;
            case WRITE:
                changedPosition = index;
                break;
        }
    
    }

}
