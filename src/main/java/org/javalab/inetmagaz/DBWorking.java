package org.javalab.inetmagaz;

import org.javalab.inetmagaz.model.*;


import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.ArrayList;

/**
 * Created by тимур on 19.03.14.
 */
public class DBWorking {
    private static DBWorking instanse;
    private static Connection connection;
    private static  int id;
    private static int itemid;
    private String driver="org.apache.derby.jdbc.EmbeddedDriver";
    private String url="jdbc:derby:magazin;create=true";
    private   DBWorking(){
        try {
            Class.forName(driver);
            connection= DriverManager.getConnection(url);
            Statement statement=connection.createStatement();
            //ResultSet resultSet= statement.executeQuery("SELECT TABLE_NAME FROM  WHERE TABLE_NAME='users' ");
            //if(resultSet.next()){
            try {
                statement.execute(" CREATE TABLE users (id int ,login varchar(25) PRIMARY key ,password varchar (32),role varchar(10))");
            }catch (SQLException e){}
            try {
                statement.execute("CREATE TABLE items (id int ,name varchar (225) PRIMARY key ,coast int,text VARCHAR(255))");
            }catch (SQLException e){}
            try {
                Statement st=connection.createStatement();
                ResultSet rs=st.executeQuery("select id FROM  items");
                int maxid=0;
                while(rs.next()){
                    int n=rs.getInt("id");
                    if(n>maxid){maxid=n;}
                }
                itemid=maxid+1;
            }catch (SQLException e){e.printStackTrace();}
            try {
                Statement st=connection.createStatement();
                ResultSet rs=st.executeQuery("select id FROM  users");
                int maxid=0;
                while(rs.next()){
                    int n=rs.getInt("id");
                    if(n>maxid){maxid=n;}
                }
               id=maxid+1;
            }catch (SQLException e){e.printStackTrace();}
            //}
           // resultSet= statement.executeQuery("SELECT TABLE_NAME FROM TABLE WHERE TABLE_NAME='items' ");
            //if(resultSet.next()){
           //}
            String password="Admin";
            if(haveLogin("Admin")){
            insertuser("Admin", password, true);}

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e){e.printStackTrace();
            System.out.print("creatinderror");
        }

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

    public static DBWorking getinstanse(){
        if(instanse==null){
          instanse= new DBWorking();
        }

        return instanse;
    }
    public boolean haveLogin(String login){
        boolean f=true;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT login FROM users WHERE login=?");
            preparedStatement.setString(1,login);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){f=false;}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return f;

    }

    public void updateitem(int id,int coast,String name,String text){
        try {
           PreparedStatement ps=connection.prepareStatement("UPDATE items SET name=?, coast=? ,text=? where id=?");
            ps.setString(1,name);
            ps.setInt( 2,coast);
            ps.setString( 3,text);
            ps.setInt( 4,id);
            ps.execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void insertuser(String login,String password,boolean admin) {
        Statement statement= null;
        try {
            PreparedStatement pstatement = connection.prepareStatement("INSERT INTO users (id, login, password, role) VALUES(?,?,?,?)");
            String type="";

            if (admin){type="admin";}
            else {type="user";}
            pstatement.setInt(1,id);
            pstatement.setString(2, login);
            pstatement.setString(3,gethesh(password));
            pstatement.setString(4,type);
            pstatement.execute();
            id++;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.print("inserterror");
        }

    }
    public void removeItem(int id){
        try {
            Statement st=connection.createStatement();
            st.execute("DELETE FROM items WHERE id="+id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Item> getall(){
        ArrayList<Item> items=new ArrayList<Item>();
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT id,name,coast,text FROM items");
            while (resultSet.next()){
                int id=resultSet.getInt("id");
                String name=resultSet.getString("name");
                int coast=resultSet.getInt("coast");
                String text=resultSet.getString("text");
                Item item=new Item(id,name,coast,text);
                items.add(item);
                //System.out.println("added");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }
    public  Item getItem(int id){
        Item item=null;
        try {
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery("SELECT id,name,coast,text FROM items WHERE id="+id);
            resultSet.next();
            String name=resultSet.getString("name");
            int coast=resultSet.getInt("coast");
            String text=resultSet.getString("text");
            item=new Item(id,name,coast,text);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }


      
    public void insertitem(String name,int coast,String text){
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("INSERT INTO items(id,name,coast,text )" +
                    "VALUES (?,?,?,?)");
            preparedStatement.setInt(1,itemid);
            preparedStatement.setString(2,name);
            preparedStatement.setInt(3,coast);
            preparedStatement.setString(4,text);
            preparedStatement.execute();
            itemid++;
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public User getuser(String login,String password){
        User user=null;
        try {
            PreparedStatement preparedStatement=connection.prepareStatement("SELECT id,login,password,role FROM users WHERE login=?");
            preparedStatement.setString(1,login);
            ResultSet resultSet=preparedStatement.executeQuery();
            while (resultSet.next()){
                String passw=resultSet.getString("password");
                password=gethesh(password);
                if(password.equals(passw)){
                    int id=resultSet.getInt("id");
                    Usertypes type= Usertypes.not;
                    String t=resultSet.getString("role");//type of user string
                    if(t.equals("admin")){type= Usertypes.ADMIN;}
                    if(t.equals("user")){type= Usertypes.USER;}
                    user=new User(id,login,type);
                }
            }
        }catch (SQLException e){e.printStackTrace();
            System.out.print("geterror");
        }
        return  user;
    }








}
