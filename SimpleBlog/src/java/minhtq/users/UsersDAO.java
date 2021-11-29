/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.users;

import minhtq.utils.DBHealper;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;

/**
 *
 * @author CND
 */
public class UsersDAO implements Serializable {

    List<UsersDTO> list;
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public List<UsersDTO> getList() {
        return list;
    }

    private void closeDB() throws NamingException, SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public boolean checkLogin(String email, String password) throws NamingException, SQLException {
        
        try{
            con = DBHealper.makeConnection();
            if(con != null){
                String sql = "Select email "
                        + "From tbl_Account "
                        + "Where email = ? And password = ?";
               stm = con.prepareStatement(sql);
               stm.setString(1, email);
               stm.setString(2, password);
               
               rs = stm.executeQuery();
               if(rs.next()){
                   return true;
               }
                
            }
        }finally{
            closeDB();
        }
        return false;
    }
    
    public String getFullName(String email) throws NamingException, SQLException{
        String fullname = "";
        try {
            con = DBHealper.makeConnection();
            if (con != null) {
                String sql = "Select name "
                        + "From tbl_Account "
                        + "Where email = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);

                rs = stm.executeQuery();
                while (rs.next()) {
                    fullname = rs.getString("name");
                }
            }
        } finally {
            closeDB();    
        }
        return fullname;
    
    }
    
    public boolean insertAccount(String email, String password, String name, boolean role, String status) 
            throws NamingException, SQLException{
        try{
            con = DBHealper.makeConnection();
            if(con != null){
            String sql = "Insert into tbl_Account(email, password, name, role, status) "
                    + "Values(?, ?, ?, ?, ?)";
            stm = con.prepareStatement(sql);
            stm.setString(1, email);
            stm.setString(2, password);
            stm.setString(3, name);
            stm.setBoolean(4, role);
            stm.setString(5, status);
            int row = stm.executeUpdate();
            if(row > 0){
                return true;
            }
        }
        }finally{
            closeDB();
        }
        return false;
        
    }
    
    public UsersDTO getAccount(String email, String password) throws NamingException, SQLException{
        
        try {
            con = DBHealper.makeConnection();
            if(con != null){
                String sql = "SELECT email, password, name, role, status "
                        + "FROM tbl_Account "
                        + "WHERE email = ? "
                        + "AND password = ? ";
                stm = con.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, password);
                rs = stm.executeQuery();
                if(rs.next()){
                    String name = rs.getString("name");
                    boolean role = rs.getBoolean("role");
                    String status = rs.getString("status");
                    UsersDTO dto = new UsersDTO(email, password, name, email, role);
                    return dto; 
                }
            }  
        }finally{
            closeDB();
        }
        return null;
    }
  
}
