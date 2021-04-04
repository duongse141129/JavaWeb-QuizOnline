/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dtos;

/**
 *
 * @author minhv
 */
public class QuestionDTO {   
    private String questionID ;
    private String question_content ;
    private String createDate ;
    private String subjectID ;
    private boolean status;

    public QuestionDTO(String questionID, String question_content, String createDate, String subjectID, boolean status) {
        this.questionID = questionID;
        this.question_content = question_content;
        this.createDate = createDate;
        this.subjectID = subjectID;
        this.status = status;
    }

    public QuestionDTO() {
    }

    public String getQuestionID() {
        return questionID;
    }

    public void setQuestionID(String questionID) {
        this.questionID = questionID;
    }

    public String getQuestion_content() {
        return question_content;
    }

    public void setQuestion_content(String question_content) {
        this.question_content = question_content;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
