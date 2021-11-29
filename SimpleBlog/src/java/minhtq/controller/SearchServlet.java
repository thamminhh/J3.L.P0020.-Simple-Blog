/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
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
import minhtq.users.UsersDTO;

/**
 *
 * @author admin
 */
@WebServlet(name = "SearchServlet", urlPatterns = {"/SearchServlet"})
public class SearchServlet extends HttpServlet {

    private final String HOME_PAGE = "homePage";
    private final String MEMBER_PAGE = "memberPage";
    private final String ADMIN_PAGE = "adminPage";

    private static final org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(SearchServlet.class);

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

        String url = (String) siteMap.get(HOME_PAGE);

        String searchValue = request.getParameter("txtSearchValue");
        String statusOption = request.getParameter("statusOption");

        try {
            HttpSession session = request.getSession();
            UsersDTO account = (UsersDTO) session.getAttribute("USER");

            if (account == null) {
                url = (String) siteMap.get(HOME_PAGE);
                ArticleDAO dao = new ArticleDAO();
                dao.getListArticleByTitle(searchValue);
                List<ArticleDTO> result = dao.getList();
                request.setAttribute("RESULT", result);
                LOGGER.info("End-User search Title");
            } if(account != null) {
                if (account.isRole() == true) {
                    url = (String) siteMap.get(ADMIN_PAGE);
                    ArticleDAO dao = new ArticleDAO();
                    dao.getListArticleByTitleAndStatus(searchValue, statusOption);
                    List<ArticleDTO> result = dao.getList();
                    request.setAttribute("RESULT", result);
                    LOGGER.info("Admin search");
                } else {
                    url = (String) siteMap.get(MEMBER_PAGE);
                    ArticleDAO dao = new ArticleDAO();
                    dao.getListArticleByTitle(searchValue);
                    List<ArticleDTO> result = dao.getList();
                    request.setAttribute("RESULT", result);
                    LOGGER.info("Member search Title");  
                }
            }

        } catch (NamingException ex) {
            LOGGER.error(ex);
        } catch (SQLException ex) {
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
