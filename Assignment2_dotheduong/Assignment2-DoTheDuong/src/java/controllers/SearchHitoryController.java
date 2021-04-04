/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import daos.QuizDAO;
import dtos.UserDTO;

/**
 *
 * @author minhv
 */
public class SearchHitoryController extends HttpServlet {

    private final static String SUCCESS = "history.jsp";

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
            HttpSession session = request.getSession();
            UserDTO loginUser = (UserDTO) session.getAttribute("LOGIN_USER");
            String subjectID = request.getParameter("cmbSubject");
            
            QuizDAO quizDAO = new QuizDAO();
            int count = quizDAO.getCountQuizOfStudent(loginUser.getEmail(),subjectID);
            int totalPage=0;
            if (count % 5 != 0) {
                totalPage = (count / 5) + 1;
            } else {
                totalPage = (count / 5);
            }
            request.setAttribute("TOTAL_PAGE", totalPage);
            String currentPage = request.getParameter("txtCurrentPage");
            int page = 1;
            if (currentPage != null) {
                page = Integer.parseInt(currentPage);
            }
            
            if (loginUser != null && loginUser.getRoleID().equals("US")) {
                if (subjectID != null) {
                    List<String> history = quizDAO.getHistory(loginUser.getEmail(), subjectID,page);
                    if (history != null && history.size()>0) {
                        url = SUCCESS;
                        request.setAttribute("LIST_HISTORY", history);
                    } else {
                        request.setAttribute("MESSAGE", "Not found");
                    }
                }
            }
        } catch (Exception e) {
            log("Error at SearchHistoryServlet " + e.toString());
        } finally {
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
