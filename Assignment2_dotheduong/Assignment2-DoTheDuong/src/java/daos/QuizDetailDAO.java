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
import java.util.ArrayList;
import java.util.List;
import utils.DBUtils;

/**
 *
 * @author minhv
 */
public class QuizDetailDAO {
    public void createQuizDetail(String quizID,String questionID,int selectedAnswer,boolean result) throws SQLException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="INSERT INTO tblQuizDetail\n" +
                           "values(?,?,?,?,1)";
                stm=conn.prepareStatement(sql);
                stm.setString(1,quizID);
                stm.setString(2,questionID);
                stm.setInt(3,selectedAnswer);
                stm.setBoolean(4,result);
                stm.executeUpdate();          
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    
    public void updateQuizDetail(String quizID,String questionID,int selectedAnswer,boolean result) throws SQLException{
        Connection conn=null;
        PreparedStatement stm=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="UPDATE tblQuizDetail\n" +
                           "SET selectedAnswer=?,result=?\n" +
                           "WHERE quizID=? and questionID=?";
                stm=conn.prepareStatement(sql);
                stm.setInt(1,selectedAnswer);
                stm.setBoolean(2,result);
                stm.setString(3,quizID);
                stm.setString(4,questionID);
                stm.executeUpdate();          
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
    }
    
    public float getTotalMark(String quizID) throws SQLException{
        float totalMark=0;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        int totalQuestion=0;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select detailID,result\n" +
                           "From tblQuizDetail\n" +
                           "where quizID=?";
                stm=conn.prepareStatement(sql);
                stm.setString(1,quizID);
                rs=stm.executeQuery();
                while(rs.next()){
                    totalQuestion++;
                    if(rs.getInt("result")==1){
                        totalMark++;
                    }
                }
                totalMark=(totalMark/totalQuestion)*10;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return totalMark;
    }
    
    public List<String> getListAnswerSelectedAndQuestionID(String quizID) throws SQLException{
        List<String> result=null;
        Connection conn=null;
        PreparedStatement stm=null;
        ResultSet rs=null;
        try {
            conn=DBUtils.getConnection();
            if(conn!=null){
                String sql="select selectedAnswer,questionID\n" +
                           "From tblQuizDetail\n" +
                           "where quizID=?";
                stm=conn.prepareStatement(sql);
                stm.setString(1,quizID);
                result=new ArrayList<>();
                rs=stm.executeQuery();
                while(rs.next()){
                    String selectedAnswer=rs.getString("selectedAnswer");
                    String questionID=rs.getString("questionID");
                    result.add(selectedAnswer+"_"+questionID);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally{
            if(rs!=null)rs.close();
            if(stm!=null)stm.close();
            if(conn!=null)conn.close();
        }
        return result;
    }
    
    
    
    
}
