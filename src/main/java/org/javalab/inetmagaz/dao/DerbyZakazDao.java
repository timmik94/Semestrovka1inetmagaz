package org.javalab.inetmagaz.dao;

import org.javalab.inetmagaz.model.Status;
import org.javalab.inetmagaz.model.Zakaz;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by timur on 11.05.2014.
 */
public class DerbyZakazDao implements ZakazDao {
    private JdbcTemplate jdbcTemplate;
    private static int id;
    public DerbyZakazDao(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);
        id=0;
        try {
            this.jdbcTemplate.update(" CREATE TABLE zakaz (id int PRIMARY key  ,login varchar(25) ,coun int,status varchar(10),item int)");
        }catch (DataIntegrityViolationException e){}
        List<Integer> ids= this.jdbcTemplate.query("select id FROM  zakaz", new RowMapper<Integer>() {
            @Override
            public Integer mapRow(ResultSet resultSet, int i) throws SQLException {
                Integer id=resultSet.getInt("id");
                return id;
            }
        });
        int maxid=0;
        for (int i=0;i<ids.size();i++){
            int idnow=ids.get(i);
            if(idnow>maxid){maxid=idnow;}
        }
        id=maxid+1;
    }
    @Override
    public void setzakaz(Zakaz zakaz) {
        String status="";
        Status st=zakaz.getStatus();
        switch (st){
            case Accepted: status="assepted";break;
            case sended:status="sended";break;
            case done:status="done";break;
            case canceld:status="canceld";break;
            case ready:status="ready";break;

        }

        jdbcTemplate.update("INSERT INTO zakaz(id,login,coun,status,item) VALUES(?,?,?,?,?) ",id,zakaz.getUserlogin(),zakaz.getCount(),
                status,zakaz.getItemid());
        id++;

    }

    @Override
    public List<Zakaz> getzakaz(final String login) {
        List<Zakaz> zakazs=jdbcTemplate.query("SELECT id,login,coun,status,item FROM zakaz WHERE login=?",new Object[]{login},new RowMapper<Zakaz>() {
            @Override
            public Zakaz mapRow(ResultSet resultSet, int i) throws SQLException {
                int id=resultSet.getInt("id");
                String log=login;
                int coun=resultSet.getInt("coun");
                String st=resultSet.getString("status");
                Status status=null;
               if(st.equals("assepted")){status=Status.Accepted;}
                if(st.equals("sended")){status=Status.sended;}
                if(st.equals("done")){status=Status.done;}
                if(st.equals("canceld")){status=Status.canceld;}
                if(st.equals("ready")){status=Status.ready;}
                int item=resultSet.getInt("item");
                Zakaz z=new Zakaz(id,log,item,coun,status);
                return z;
            }
        });


        return zakazs;

    }

    @Override
    public void updatestatus(int zakazid, Status status) {
        String st="";
        switch (status){
            case Accepted: st="assepted";break;
            case sended:st="sended";break;
            case done:st="done";break;
            case canceld:st="canceld";break;
            case ready:st="ready";break;

        }
        jdbcTemplate.update("UPDATE zakaz SET status=? where id=?",st,zakazid);

    }
}
