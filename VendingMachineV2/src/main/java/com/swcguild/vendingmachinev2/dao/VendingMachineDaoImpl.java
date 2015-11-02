

package com.swcguild.vendingmachinev2.dao;

import com.swcguild.vendingmachinev2.model.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

/**
 *
 * @author Suzanne Ludwig
 */
public class VendingMachineDaoImpl implements VendingMachineDao {

    private Money money = new Money();

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }
    
    private DecimalFormat df = new DecimalFormat("#.00");
    
    private static final String SQL_SELECT_ITEM = 
            "select * from items where item_id = ?";
    
    private static final String SQL_SELECT_ALL = 
            "select * from items";
    
    private static final String SQL_SELECT_ALL_IN_STOCK =
            "select * from items where inventory > 0";
    
    private static final String SQL_UPDATE_ITEM =
            "update items set inventory = ? where item_id = ?";
    
    private JdbcTemplate jdbcTemplate;
    
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public Item getItemById(int id) {
        try {
            return jdbcTemplate.queryForObject(SQL_SELECT_ITEM, new ItemMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
    
    @Override
    public List<Item> getAllItems() {
        return jdbcTemplate.query(SQL_SELECT_ALL, new ItemMapper());
    }
    
    @Override
    public List<Item> getAllItemsInStock() {
        return jdbcTemplate.query(SQL_SELECT_ALL_IN_STOCK, new ItemMapper());
    }
    
    @Override
    public void updateItem(Item item) {
        jdbcTemplate.update(SQL_UPDATE_ITEM,
                item.getInventory(),
                item.getId());
    }
    
    @Override
    public String getMoneyTotal() {
        return df.format(money.getTotal()) + "";
    }
    
    @Override
    public void resetMoney() {
        money.setTotal(0);
    }
    
    @Override
    public boolean vend(Item item) {
        if (money.getTotal() - item.getCost() < 0) {
            return false;
        } else {
            item.setInventory(item.getInventory()-1);
            money.setTotal(money.getTotal() - item.getCost());
            updateItem(item);
            return true;
        }
    }
    
    @Override
    public void addNickel() {
        money.addNickel();
    }
    
    @Override
    public void addDime() {
        money.addDime();
    }
    
    @Override
    public void addQuarter() {
        money.addQuarter();
    }
    
    @Override
    public void addDollar() {
        money.addDollar();
    }
    
    private static final class ItemMapper implements ParameterizedRowMapper<Item> {
        @Override
        public Item mapRow(ResultSet rs, int i) throws SQLException {
            Item item = new Item();
            item.setId(rs.getInt("item_id"));
            item.setName(rs.getString("name"));
            item.setCost(rs.getDouble("cost"));
            item.setInventory(rs.getInt("inventory"));
            return item;
        }
    }
    
}
