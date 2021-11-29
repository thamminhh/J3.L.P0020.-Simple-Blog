/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.controller;

import java.io.IOException;
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
import minhtq.article.ArticleDAO;
import minhtq.article.ArticleDTO;
import minhtq.article.ArticleNewErr;
import minhtq.users.UsersDTO;

/**
 *
 * @author admin
 */
@WebServlet(name = "PostArticleServlet", urlPatterns = {"/PostArticleServlet"})
public class PostArticleServlet extends HttpServlet {

    private static final String LOAD_ARTICLE_SERVLET = "loadArticleAction";
    private static final String POST_ARTICLE_PAGE = "postArticlePage";

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

        String url = (String) siteMap.get(POST_ARTICLE_PAGE);

        String title = request.getParameter("txtTitle");
        String shortDescription = request.getParameter("txtShortDescription");
        String content = request.getParameter("txtContent");
        String status = "New";
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        ArticleNewErr errors = new ArticleNewErr();
        
        HttpSession session = request.getSession();
        UsersDTO account = (UsersDTO) session.getAttribute("USER");

        String author = account.getEmail();
        System.out.println(author);

        try {
            boolean error = false;
            if (title.length() == 0) {
                error = true;
                errors.setTitleLengErr("Title can't blank");
            }
            if (shortDescription.length() == 0) {
                error = true;
                errors.setShortDescriptionLengErr("Short Description can't blank");
            }
            if (content.length() == 0) {
                error = true;
                errors.setContentLengErr("Content can't blank");
            }
            if (error) {
                request.setAttribute("CREATEERR", errors);
            } else {
                ArticleDTO dto = new ArticleDTO(title, shortDescription, author, date, content, status);
                ArticleDAO dao = new ArticleDAO();
                boolean result = dao.insertArticle(dto);
                if (result) {
                    url = (String) siteMap.get(LOAD_ARTICLE_SERVLET);
                    LOGGER.info(account.getEmail() + " Create new Cake");
                } else {
                    url = (String) siteMap.get(POST_ARTICLE_PAGE);
                }
            }

        } catch (NamingException ex) {
            Logger.getLogger(PostArticleServlet.class.getName()).log(Level.SEVERE, null, ex);
            LOGGER.error(ex);
        } catch (SQLException ex) {
            Logger.getLogger(PostArticleServlet.class.getName()).log(Level.SEVERE, null, ex);
            LOGGER.error(ex);
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
