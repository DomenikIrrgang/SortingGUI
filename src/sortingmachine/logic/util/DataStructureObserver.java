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
public interface DataStructureObserver {
    void notify(int index, DatastructureAccess type);
}
