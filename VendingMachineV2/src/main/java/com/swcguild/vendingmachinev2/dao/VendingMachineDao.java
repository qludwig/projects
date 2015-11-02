

package com.swcguild.vendingmachinev2.dao;

import com.swcguild.vendingmachinev2.model.Item;
import com.swcguild.vendingmachinev2.model.Money;
import java.util.List;

/**
 *
 * @author Suzanne Ludwig
 */
public interface VendingMachineDao {
    
    public void setMoney(Money money);
    
    public Money getMoney();
    
    public Item getItemById(int id);
    
    public List<Item> getAllItems();
    
    public List<Item> getAllItemsInStock();
    
    public void updateItem(Item item);
    
    public String getMoneyTotal();
    
    public void resetMoney();
    
    public boolean vend(Item item);
    
    public void addNickel();
    
    public void addDime();
    
    public void addQuarter();
    
    public void addDollar();
}
