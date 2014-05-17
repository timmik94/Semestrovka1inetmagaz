package org.javalab.inetmagaz.dao;

import org.javalab.inetmagaz.model.User;
import org.javalab.inetmagaz.model.Usertypes;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/**
 * Created by timur on 10.04.2014.
 */
public class DerbyUserDao implements UserDao {

    private static  int id;
    private JdbcTemplate jdbcTemplate;
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate=new JdbcTemplate(dataSource);
    }
    public DerbyUserDao(DataSource dataSource){
        jdbcTemplate=new JdbcTemplate(dataSource);

        id=0;
        try {
            this.jdbcTemplate.update(" CREATE TABLE users (id int ,login varchar(25) PRIMARY key ,password varchar (32),role varchar(10))");
        }catch (DataIntegrityViolationException e){}

        List<Integer> ids= this.jdbcTemplate.query("select id FROM  users", new RowMapper<Integer>() {
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
        String password="Admin";
        if(havelogin("Admin")){
            insertUser("Admin", password, true);}
    }

    //@Override
    //return user if password right, null if password wrong
    public User getUser(String login,String password){

        String p="";
        try {

         p=jdbcTemplate.queryForObject("SELECT id,login,password,role FROM users WHERE login=?",new Object[]{login},
                new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                String passw=resultSet.getString("password");
                return passw;
            }
        });}catch (EmptyResultDataAccessException e){return null;}
        User user=null;
        if(p.equals(gethesh(password))){
         user=jdbcTemplate.queryForObject("SELECT id,login,password,role FROM users WHERE login=?",new Object[]{login},
                 new RowMapper<User>() {
            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
               int id=resultSet.getInt("id");
               String login=resultSet.getString("login");
               String usertype=resultSet.getString("role");
               Usertypes type=Usertypes.not;
                if(usertype.equals("admin")){type= Usertypes.ADMIN;}
                if(usertype.equals("user")){type= Usertypes.USER;}
                User us=new User(id,login,type);
                return us;
            }
        });}

        return  user;
    }

    @Override
    public User getUser(String login) {
        User user=null;
       try {
           user=jdbcTemplate.queryForObject("SELECT id,login,password,role FROM users WHERE login=?",new Object[]{login},
                   new RowMapper<User>() {
                       @Override
                       public User mapRow(ResultSet resultSet, int i) throws SQLException {
                           int id=resultSet.getInt("id");
                           String login=resultSet.getString("login");
                           String usertype=resultSet.getString("role");
                           Usertypes type=Usertypes.not;
                           if(usertype.equals("admin")){type= Usertypes.ADMIN;}
                           if(usertype.equals("user")){type= Usertypes.USER;}
                           User us=new User(id,login,type);
                           return us;
                       }
                   });
       }catch (EmptyResultDataAccessException e){}


        return  user;
    }


    private String gethesh(String inputString){
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");  // DSA, RSA, MD5, SHA-1, SHA-256
            byte[] messageDigest = md.digest(inputString.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashtext = number.toString(16);

            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public boolean insertUser(String login,String password,boolean admin) {
        Statement statement= null;
        if(havelogin(login)){
            String type="";
            if (admin){type="admin";}
            else {type="user";}
            jdbcTemplate.update("INSERT INTO users (id, login, password, role) VALUES(?,?,?,?)",id,login,gethesh(password),type);
            id++;
            return true;
        }else {return false;}

    }

    @Override
    public boolean havelogin(String login){
        boolean f=true;
        try {
            String s=jdbcTemplate.queryForObject("SELECT login FROM users WHERE login=?", new RowMapper<String>() {
                @Override
                public String mapRow(ResultSet resultSet, int i) throws SQLException {
                    String log=resultSet.getString("login");
                    return log;
                }
            },login); f=false;
        }catch (EmptyResultDataAccessException e){f=true;}
        return f;

    }
}
