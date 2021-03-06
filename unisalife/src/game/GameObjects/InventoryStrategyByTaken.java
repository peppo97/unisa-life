/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.GameObjects;

import java.util.Comparator;
import java.util.List;

/**
 * InventoryStrategy implementation that allows the insertion of elements in the inventory according to the natural order of their taken date.
 * @author cmarino
 */
public class InventoryStrategyByTaken implements GameInventoryStrategy, Comparator<Item>{

    @Override
    public int compare(Item o1, Item o2) {
        
        if(o1 == null && o2 == null)
            return 0;
        if(o1 == null )
            return -1;
        if(o2 == null)
            return 1;
        
        return o2.getTaken().compareTo(o1.getTaken());
        
    }

    @Override
    public int addItem(List l, Item i) {
        
        if(l.contains(i))
            return -1;
        
        i.setTaken();
        l.add(0, i);
        return 0;
        
    }
    
    
    
    
    
    
    
    
    
}
