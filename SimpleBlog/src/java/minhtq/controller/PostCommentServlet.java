/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import minhtq.comment.CommentDAO;
import minhtq.comment.CommentDTO;
import minhtq.users.UsersDTO;

/**
 *
 * @author admin
 */
@WebServlet(name = "PostCommentServlet", urlPatterns = {"/PostCommentServlet"})
public class PostCommentServlet extends HttpServlet {

    private static final String VIEW_ARTICLE_DETAIL_SERVLET = "viewDetailAction";
    private static final String LOGIN_PAGE = "loginPage";

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(LoginServlet.class);

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
        ServletContext context = request.getServletContext();
        Properties siteMap = (Properties) context.getAttribute("SITE_MAP");

        String url = (String) siteMap.get(VIEW_ARTICLE_DETAIL_SERVLET);
        
        String articleID = request.getParameter("txtArticleID");
        String detail = request.getParameter("txtCmt");

        try {
            HttpSession session = request.getSession();
            UsersDTO account = (UsersDTO) session.getAttribute("USER");
            if(account == null){
                url = (String) siteMap.get(LOGIN_PAGE);
            }else{
                String email = account.getEmail();
                CommentDAO dao = new CommentDAO();
                CommentDTO dto = new CommentDTO(detail, email, articleID);
                boolean result = dao.addCommentInArticle(dto);
                if(result){
                    url = (String) siteMap.get(VIEW_ARTICLE_DETAIL_SERVLET);
                }
            }

        } catch (NamingException ex) {
            Logger.getLogger(PostCommentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(PostCommentServlet.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
