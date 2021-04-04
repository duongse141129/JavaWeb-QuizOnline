/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import daos.AnswerDAO;
import daos.QuestionDAO;
import daos.SubjectDAO;
import dtos.AnswerDTO;
import dtos.QuestionDTO;
import dtos.SubjectDTO;

/**
 *
 * @author minhv
 */
public class SearchController extends HttpServlet {
    private final static String SUCCESS="management.jsp";

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = SUCCESS;
        try {
            String question_content = request.getParameter("txtQuestionContent");
            String status = request.getParameter("cmbStatus");
            String subjectID = request.getParameter("cmbSubject");
            String currentPage = request.getParameter("txtCurrentPage");
            
            if (currentPage==null) {
                currentPage = "1";
            }
            
            SubjectDAO subjectDao = new SubjectDAO();
            QuestionDAO questionDao=new QuestionDAO();
            AnswerDAO answerDao=new AnswerDAO();
            int count=questionDao.getNoOfAdminSearchResult(question_content, status, subjectID);
            
            int totalPage=0;
            if(count % 20!=0){
                totalPage=count/20 +1;
            }else{
                totalPage=count/20;
            }
            request.setAttribute("TOTAL_PAGE", totalPage);
            List<QuestionDTO> listQuestion = questionDao.getListQuestion(question_content,status, subjectID,Integer.parseInt(currentPage));
            if (listQuestion != null) {
                
                request.setAttribute("LIST_QUESTION", listQuestion);
                           
                List<AnswerDTO> listAnswer = new ArrayList<>();
                List<SubjectDTO> listSubject = new ArrayList<>();
                for (QuestionDTO questionDTO : listQuestion) {
                    SubjectDTO subjecttemp=subjectDao.getSubjectFromQuestionID(questionDTO.getQuestionID()); 
                    boolean checkDuplicateSubject=true;
                    for (SubjectDTO subjectDTO : listSubject) {
                        if(subjectDTO.getSubjectID().equals(subjecttemp.getSubjectID())){
                            checkDuplicateSubject=false;
                        }
                    }
                    if(checkDuplicateSubject==true && subjecttemp!=null){
                        listSubject.add(subjecttemp);
                    }                   
                    List<AnswerDTO> listtemp = answerDao.getAnswersFromQuestionID(questionDTO.getQuestionID());
                    for (AnswerDTO answerDTO : listtemp) {
                        listAnswer.add(answerDTO);
                    }
                    
                }
                request.setAttribute("LIST_ANSWER", listAnswer);               
                request.setAttribute("LIST_SUBJECT", listSubject);
                url = SUCCESS;
            }else{
                request.setAttribute("MESSAGE", "Not found");
            }    
        } catch (Exception e) {
            log("Error at SearchServlet "+e.toString());
        }
        finally{
            request.getRequestDispatcher(url).forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
