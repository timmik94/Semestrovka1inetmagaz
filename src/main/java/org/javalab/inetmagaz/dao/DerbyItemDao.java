package org.javalab.inetmagaz.dao;

import org.javalab.inetmagaz.model.Item;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by timur on 11.04.2014.
 */

public class DerbyItemDao implements ItemDao {

    private int itemid;
    private JdbcTemplate jdbcTemplate;

    public DerbyItemDao(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);


        itemid = 0;
        try {
            jdbcTemplate.update("CREATE TABLE items (id int ,name varchar (225) PRIMARY key ,coast int,text VARCHAR(255))");
        } catch (DataIntegrityViolationException e) {
        }
        List<Integer> ids = this.jdbcTemplate.query("select id FROM  items", new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id = resultSet.getInt("id");
                return id;
            }
        });
        int maxid = 0;
        for (int i = 0; i < ids.size(); i++) {
            int idnow = ids.get(i);
            if (idnow > maxid) {
                maxid = idnow;
            }
        }
        itemid = maxid + 1;
    }

    @Override
    public  Item getItem(int id){
        Item item=null;
        try {
            item=jdbcTemplate.queryForObject("SELECT id,name,coast,text FROM items WHERE id=?",new Object[]{id},new RowMapper<Item>() {
                @Override
                public Item mapRow(ResultSet resultSet, int i) throws SQLException {
                    int id=resultSet.getInt("id");
                    String name=resultSet.getString("name");
                    int coast=resultSet.getInt("coast");
                    String text=resultSet.getString("text");
                    Item item1=new Item(id,name,coast,text);
                    return item1;
                }
            });
        }catch (EmptyResultDataAccessException e){}


        return item;
    }

    @Override
    public void removeItem(int id){
        jdbcTemplate.update("DELETE FROM items WHERE id=?",id);

    }

    @Override
    public void updateItem(int id,int coast,String name,String text){
        try {
            jdbcTemplate.update("UPDATE items SET name=?, coast=? ,text=? where id=?", name, coast, text, id);
        }catch (EmptyResultDataAccessException e){}

    }

    @Override
    public List<Item> getall(){
        List<Item> items=jdbcTemplate.query("SELECT id,name,coast,text FROM items",new RowMapper<Item>() {
            @Override
            public Item mapRow(ResultSet resultSet, int i) throws SQLException {
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                int coast=resultSet.getInt("coast");
                String text=resultSet.getString("text");
                Item item=new Item(id,name,coast,text);
                return item;
            }
        });


        return items;
    }

    @Override
    public void insertitem(String name,int coast,String text){
        if(!name.equals("")){
        jdbcTemplate.update("INSERT INTO items(id,name,coast,text ) VALUES (?,?,?,?)",itemid,name,coast,text);
       itemid++;}

    }


}
