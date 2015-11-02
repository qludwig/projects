

package com.swcguild.vendingmachinev2.controller;

import com.swcguild.vendingmachinev2.dao.VendingMachineDao;
import com.swcguild.vendingmachinev2.model.Item;
import java.util.List;
import javax.inject.Inject;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 *
 * @author Suzanne Ludwig
 */

@Controller
public class HomeController {

    private VendingMachineDao dao;
    
    @Inject
    public HomeController(VendingMachineDao dao) {
        this.dao = dao;
    }
    
    @RequestMapping(value={"/", "/home"}, method=RequestMethod.GET)
    public String displayHomePage() {
        return "home";
    }
    
    @RequestMapping(value="/item/{id}", method=RequestMethod.GET)
    @ResponseBody
    public Item getItemById(@PathVariable("id") int id) {
        return dao.getItemById(id);
    }
    
    @RequestMapping(value="/items", method=RequestMethod.GET) 
    @ResponseBody
    public List<Item> getAllItemsInStock() {
        return dao.getAllItemsInStock();
    }
    
    @RequestMapping(value="/item/{id}", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateItem(@PathVariable("id") int id, @Valid @RequestBody Item item) {
        item.setId(id);
        dao.updateItem(item);
    }
    
    @RequestMapping(value="/money", method=RequestMethod.GET) 
    @ResponseBody
    public String getCurrentTotal() {
        return dao.getMoneyTotal();
    }
    
    @RequestMapping(value="/money", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void resetMoney() {
        dao.resetMoney();
    }
    
    @RequestMapping(value="/money/{id}", method=RequestMethod.PUT)
    @ResponseBody
    public boolean vend(@PathVariable("id") int id, @Valid @RequestBody Item item) {
        return dao.vend(item);
    }
    
    @RequestMapping(value="/nickel", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addNickel() {
        dao.addNickel();
    }
    
    @RequestMapping(value="/dime", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addDime() {
        dao.addDime();
    }
    
    @RequestMapping(value="/quarter", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addQuarter() {
        dao.addQuarter();
    }
    
    @RequestMapping(value="/dollar", method=RequestMethod.PUT)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void addDollar() {
        dao.addDollar();
    }
}
