/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import dtos.UserDTO;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import utils.DBUtils;

/**
 *
 * @author minhv
 */
public class UserDAO {
    public UserDTO checkLogin(String email, String password) throws SQLException {
        UserDTO result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "Select email,fullName,password,roleID,status\n"
                        + "FROM tblUsers\n"
                        + "WHERE email=? AND password=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, email);
                stm.setString(2, toHexString(getSHA(password)));
                rs = stm.executeQuery();
                if (rs.next()) {
                    String fullName = rs.getString("fullName");
                    String roleID = rs.getString("roleID");
                    Boolean status = rs.getBoolean("status");
                    if(status==true){
                        result = new UserDTO(email, fullName, "", roleID, status);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    

    
    public void createNew(UserDTO user) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="INSERT INTO tblUsers(email, fullName, roleID, password,status) "
                        + " VALUES(?,?,?,?,?)";
                stm=conn.prepareStatement(sql);
                stm.setString(1, user.getEmail());
                stm.setString(2, user.getFullName());
                stm.setString(3, user.getRoleID());
                stm.setString(4, toHexString(getSHA(user.getPassword())));
                stm.setBoolean(5,user.isStatus());
                stm.executeUpdate();
            }  
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    public static byte[] getSHA(String input) throws NoSuchAlgorithmException 
    {   
        MessageDigest md = MessageDigest.getInstance("SHA-256");  
        return md.digest(input.getBytes(StandardCharsets.UTF_8));  
    } 
    
    public static String toHexString(byte[] hash) 
    { 
        BigInteger number = new BigInteger(1, hash);  
        StringBuilder hexString = new StringBuilder(number.toString(16));  
        while (hexString.length() < 32)  
        {  
            hexString.insert(0, '0');  
        }  
        return hexString.toString();  
    } 
}
