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
import dtos.QuestionDTO;
import dtos.SubjectDTO;
import utils.DBUtils;

/**
 *
 * @author minhv
 */
public class QuestionDAO {
    
    public int getNoOfAdminSearchResult(String question_content, String status, String subjectID) throws SQLException {
        int result = 0;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "";
                if (status.equals("")) {
                    sql = "SELECT Count(Q.questionID) as NoOfQuestion\n"
                            + "from tblQuestions Q,tblSubjects S\n"
                            + "where question_content like ? and Q.subjectID=S.subjectID and Q.subjectID like ?";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, "%" + question_content + "%");
                    stm.setString(2, "%" + subjectID + "%");
                    rs = stm.executeQuery();
                    if (rs.next()) {
                        result = rs.getInt("NoOfQuestion");
                    }
                } else {
                    sql = "SELECT Count(Q.questionID) as NoOfQuestion\n"
                            + "from tblQuestions Q,tblSubjects S\n"
                            + "where question_content like ? and Q.status =? and Q.subjectID=S.subjectID and Q.subjectID like ?";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, "%" + question_content + "%");
                    stm.setBoolean(2, Boolean.parseBoolean(status));
                    stm.setString(3, "%" + subjectID + "%");
                    rs = stm.executeQuery();
                    if (rs.next()) {
                        result = rs.getInt("NoOfQuestion");
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
    
    public List<QuestionDTO> getListQuestion(String question_content, String status, String subjectID, int currentPage) throws SQLException {
        List<QuestionDTO> result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        int offset = (currentPage - 1) * 20;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                if (status.equals("")) {
                    String sql = "SELECT Q.questionID,question_content,createDate,Q.subjectID,Q.status\n"
                            + "from tblQuestions Q,tblSubjects S\n"
                            + "where question_content like ? and Q.subjectID=S.subjectID and Q.subjectID like ? order by S.subjectName,question_content\n"
                            + "offset ? rows fetch next 20\n"
                            + "rows only";
                    stm = conn.prepareStatement(sql);
                    stm.setString(1, "%" + question_content + "%");
                    stm.setString(2, "%" + subjectID + "%");
                    stm.setInt(3, offset);
                    rs = stm.executeQuery();
                    while (rs.next()) {
                        String questionID = rs.getString("questionID");
                        String question_content1 = rs.getString("question_content");
                        String createDate = rs.getString("createDate");
                        String subjectID1 = rs.getString("subjectID");
                        boolean status1 = rs.getBoolean("status");
                        if (result == null) {
                            result = new ArrayList<>();
                        }
                        result.add(new QuestionDTO(questionID, question_content1, createDate, subjectID1, status1));
                    }
                } else {
                    String sql = "SELECT Q.questionID,question_content,createDate,Q.subjectID,Q.status\n"
                            + "from tblQuestions Q,tblSubjects S\n"
                            + "where question_content like ? and Q.status =? and Q.subjectID=S.subjectID and Q.subjectID like ? order by S.subjectName,question_content\n"
                            + "offset ? rows fetch next 20\n"
                            + "rows only";
                    stm = conn.prepareStatement(sql);

                    stm.setString(1, "%" + question_content + "%");
                    stm.setBoolean(2, Boolean.parseBoolean(status));
                    stm.setString(3, "%" + subjectID + "%");
                    stm.setInt(4, offset);
                    rs = stm.executeQuery();
                    while (rs.next()) {
                        String questionID = rs.getString("questionID");
                        String question_content1 = rs.getString("question_content");
                        String createDate = rs.getString("createDate");
                        String subjectID1 = rs.getString("subjectID");
                        boolean status1 = rs.getBoolean("status");
                        if (result == null) {
                            result = new ArrayList<>();
                        }
                        result.add(new QuestionDTO(questionID, question_content1, createDate, subjectID1, status1));
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

    public QuestionDTO getQuestion(String questionID) throws SQLException {
        QuestionDTO result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT Q.questionID,question_content,createDate,Q.subjectID,Q.status\n"
                        + "from tblQuestions Q\n"
                        + "where  Q.questionID=?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, questionID);
                rs = stm.executeQuery();
                if (rs.next()) {
                    String question_content1 = rs.getString("question_content");
                    String createDate = rs.getString("createDate");
                    String subjectID = rs.getString("subjectID");
                    boolean status1 = rs.getBoolean("status");
                    result = new QuestionDTO(questionID, question_content1, createDate, subjectID, status1);
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

    public void update(QuestionDTO questionDTO) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblQuestions\n"
                        + "SET question_content=?,subjectID=?,status=?\n"
                        + "WHERE questionID = ?";
                stm = conn.prepareStatement(sql);

                stm.setString(1, questionDTO.getQuestion_content());
                stm.setString(2, questionDTO.getSubjectID());
                stm.setBoolean(3, questionDTO.isStatus());
                stm.setString(4, questionDTO.getQuestionID());

                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public void delete(String questionID) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "UPDATE tblQuestions\n"
                        + "SET status=0\n"
                        + "WHERE questionID = ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, questionID);
                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public boolean checkID(String questionID) throws SQLException {
        boolean result = false;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT questionID\n"
                        + "from tblQuestions \n"
                        + "where questionID= ?";
                stm = conn.prepareStatement(sql);
                stm.setString(1, questionID);

                rs = stm.executeQuery();
                if (rs.next()) {
                    result = true;
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

    public void create(QuestionDTO question) throws SQLException {
        Connection conn = null;
        PreparedStatement stm = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "INSERT INTO tblQuestions\n"
                        + "values(?,?,?,?,1)";
                stm = conn.prepareStatement(sql);
                stm.setString(1, question.getQuestionID());
                stm.setString(2, question.getQuestion_content());
                stm.setString(3, question.getCreateDate());
                stm.setString(4, question.getSubjectID());
                stm.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    public List<QuestionDTO> getListQuestionRandom(SubjectDTO subject) throws SQLException {
        List<QuestionDTO> result = null;
        Connection conn = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        try {
            conn = DBUtils.getConnection();
            if (conn != null) {
                String sql = "SELECT TOP " + subject.getNumberQuestion() + " questionID,question_content ,createDate,Q.subjectID,Q.status\n"
                        + "FROM tblQuestions Q, tblSubjects S\n"
                        + "where Q.subjectID=S.subjectID and Q.status='1' and Q.subjectID=?\n"
                        + "ORDER BY NEWID()";
                stm = conn.prepareStatement(sql);
                stm.setString(1, subject.getSubjectID());
                rs = stm.executeQuery();
                while (rs.next()) {
                    String questionID = rs.getString("questionID");
                    String question_content1 = rs.getString("question_content");
                    String createDate = rs.getString("createDate");
                    String subjectID = rs.getString("subjectID");
                    boolean status1 = rs.getBoolean("status");
                    if (result == null) {
                        result = new ArrayList<>();
                    }
                    result.add(new QuestionDTO(questionID, question_content1, createDate, subjectID, status1));
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
