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
public class SubjectDTO {
    private String subjectID ;
    private String subjectName ;
    private int numberQuestion ;
    private int timeQuiz ;
    private boolean status;

    public SubjectDTO() {
    }

    public SubjectDTO(String subjectID, String subjectName, int numberQuestion, int timeQuiz, boolean status) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.numberQuestion = numberQuestion;
        this.timeQuiz = timeQuiz;
        this.status = status;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(int numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public int getTimeQuiz() {
        return timeQuiz;
    }

    public void setTimeQuiz(int timeQuiz) {
        this.timeQuiz = timeQuiz;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    
    
    
}
