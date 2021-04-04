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
import dtos.SubjectDTO;
import utils.DBUtils;

/**
 *
 * @author minhv
 */
public class SubjectDAO {
    public SubjectDTO getSubjectFromQuestionID(String questionID) throws SQLException {
        SubjectDTO result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT S.subjectID,subjectName,numberQuestion,timeQuiz,S.status \n" +
                             "FROM tblSubjects S,tblQuestions Q\n" +
                             "where S.subjectID=Q.subjectID and Q.questionID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, questionID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String subjectID =rs.getString("subjectID");
                    String subjectName=rs.getString("subjectName");
                    int numberQuestion=rs.getInt("numberQuestion");
                    int timeQuiz=rs.getInt("timeQuiz");
                    boolean status=rs.getBoolean("status");
                    result= new SubjectDTO(subjectID, subjectName, numberQuestion, timeQuiz, status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
    public List<SubjectDTO> getAllSubject() throws SQLException {
        List<SubjectDTO> result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT subjectID,subjectName,numberQuestion,timeQuiz,status\n" +
                             "from tblSubjects";
                stm = conn.prepareStatement(sql);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String subjectID =rs.getString("subjectID");
                    String subjectName=rs.getString("subjectName");
                    int numberQuestion=rs.getInt("numberQuestion");
                    int timeQuiz=rs.getInt("timeQuiz");
                    boolean status=rs.getBoolean("status");
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    if (status==true) {
                        result.add(new SubjectDTO(subjectID, subjectName, numberQuestion, timeQuiz, status));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }
    
    public SubjectDTO getSubjectFromSubjectID(String subjectID) throws SQLException {
        SubjectDTO result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT S.subjectID,subjectName,numberQuestion,timeQuiz,S.status \n" +
                             "FROM tblSubjects S\n" +
                             "where S.subjectID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, subjectID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String subjectName=rs.getString("subjectName");
                    int numberQuestion=rs.getInt("numberQuestion");
                    int timeQuiz=rs.getInt("timeQuiz");
                    boolean status=rs.getBoolean("status");
                    result= new SubjectDTO(subjectID, subjectName, numberQuestion, timeQuiz, status);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return result;
    }

    
}
