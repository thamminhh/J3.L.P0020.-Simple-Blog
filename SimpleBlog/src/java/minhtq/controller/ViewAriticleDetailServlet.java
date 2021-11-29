/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.controller;

import java.io.IOException;
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
import minhtq.comment.CommentDAO;
import minhtq.comment.CommentDTO;
import minhtq.users.UsersDTO;

/**
 *
 * @author admin
 */
@WebServlet(name = "ViewAriticleDetailServlet", urlPatterns = {"/ViewAriticleDetailServlet"})
public class ViewAriticleDetailServlet extends HttpServlet {
    
    private final String VIEW_DETAIL_PAGE = "viewDetailPage";

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

        String url = (String) siteMap.get(VIEW_DETAIL_PAGE);
        
        String articleID = request.getParameter("txtArticleID").trim();
        
        try  {
            ArticleDAO dao = new ArticleDAO();
            ArticleDTO dto = new ArticleDTO();

            dto = dao.getArticleByID(articleID);
            
            request.setAttribute("ARTICLE", dto);
            
            CommentDAO cmtDAO = new CommentDAO();
            cmtDAO.getAllCmtByArticleID(articleID);
            List<CommentDTO> cmtDTO = cmtDAO.getList();
            request.setAttribute("COMMENT", cmtDTO);
            
           
        } catch (SQLException ex) {
            Logger.getLogger(ViewAriticleDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(ViewAriticleDetailServlet.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
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
