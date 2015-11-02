

package com.swcguild.vendingmachinev2.model;

/**
 *
 * @author Suzanne Ludwig
 */
public class Money {
    private double total;
    
    public Money() {
        total = 0;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    public void addNickel() {
        total += 0.05;
    }
    
    public void addDime() {
        total += 0.10;
    }
    
    public void addQuarter() {
        total += 0.25;
    }
    
    public void addDollar() {
        total += 1;
    }
}
