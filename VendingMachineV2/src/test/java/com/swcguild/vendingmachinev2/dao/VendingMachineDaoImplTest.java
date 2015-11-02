/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.swcguild.vendingmachinev2.dao;

import com.swcguild.vendingmachinev2.model.Item;
import com.swcguild.vendingmachinev2.model.Money;
import java.lang.reflect.Field;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author Suzanne Ludwig
 */
public class VendingMachineDaoImplTest {

    private VendingMachineDao dao;

    public VendingMachineDaoImplTest() {
    }

    @BeforeClass
    public static void setUpClass() {

    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("test-applicationContext.xml");
        dao = (VendingMachineDao) ctx.getBean("vendingMachingDao");
         Item item = new Item();
        item.setId(5);
        item.setName("Test Food");
        item.setCost(1.25);
        item.setInventory(100);
        dao.updateItem(item);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void getItemById() {
        Item item = new Item();
        item.setId(5);
        item.setName("Test Food");
        item.setCost(1.25);
        item.setInventory(100);
        Item fromDB = dao.getItemById(5);
        assertEquals(item, fromDB);
    }

    @Test
    public void testGetAllItems() {
        List<Item> result = dao.getAllItems();
        assertTrue(result.size() == 2);
    }

    @Test
    public void testGetAllItemsInStock() {
        List<Item> result = dao.getAllItemsInStock();
        assertTrue(result.size() == 1);
    }

    @Test
    public void testUpdateItem() {
        Item item = new Item();
        item.setId(5);
        item.setName("Test Food");
        item.setCost(1.25);
        item.setInventory(99);
        dao.updateItem(item);
        assertTrue(dao.getItemById(5).getInventory() == 99);
    }

    @Test
    public void testGetMoneyTotal() {
        Money money = new Money();
        money.setTotal(2.25);
        dao.setMoney(money);
        assertTrue(dao.getMoneyTotal().equals("2.25"));
    }

    @Test
    public void testResetMoney() {
        Money money = new Money();
        money.setTotal(2.25);
        dao.setMoney(money);
        dao.resetMoney();
        assertTrue(money.getTotal() == 0);
    }

    @Test
    public void testVend() {
        Item item = new Item();
        item.setId(5);
        item.setName("Test Food");
        item.setCost(1.25);
        item.setInventory(99);
        Money money = new Money();
        money.setTotal(0.25);
        dao.setMoney(money);
        assertFalse(dao.vend(item));
        money.setTotal(5.55);
        dao.setMoney(money);
        assertTrue(dao.vend(item));
        assertTrue(item.getInventory() == 98);
        assertTrue(money.getTotal() == 4.30);
    }

    @Test
    public void testAddNickel() {
        Money money = new Money();
        money.setTotal(2.25);
        dao.setMoney(money);
        dao.addNickel();
        assertTrue(money.getTotal() == 2.30);
    }

    @Test
    public void testAddDime() {
        Money money = new Money();
        money.setTotal(2.25);
        dao.setMoney(money);
        dao.addDime();
        assertTrue(money.getTotal() == 2.35);
    }

    @Test
    public void testAddQuarter() {
        Money money = new Money();
        money.setTotal(2.25);
        dao.setMoney(money);
        dao.addQuarter();
        assertTrue(money.getTotal() == 2.50);
    }

    @Test
    public void testAddDollar() {
        Money money = new Money();
        money.setTotal(2.25);
        dao.setMoney(money);
        dao.addDollar();
        assertTrue(money.getTotal() == 3.25);
    }

}
