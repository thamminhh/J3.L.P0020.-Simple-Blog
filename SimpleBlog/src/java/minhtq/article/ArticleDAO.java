/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.article;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import minhtq.utils.DBHealper;

/**
 *
 * @author admin
 */
public class ArticleDAO {

    List<ArticleDTO> articleList;
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public List<ArticleDTO> getList() {
        return articleList;
    }

    private void closeDB() throws NamingException, SQLException {
        if (rs != null) {
            rs.close();
        }
        if (stm != null) {
            stm.close();
        }
        if (con != null) {
            con.close();
        }
    }

    public void getAllArticalSortByDate() throws NamingException, SQLException {
        try {
            con = DBHealper.makeConnection();
            if (con != null) {
                String sql = "SELECT articleID, title, shortDescription, author, date, status "
                        + "FROM tbl_Article "
                        + "WHERE status = 'Active' "
                        + "ORDER by date ";

                stm = con.prepareStatement(sql);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String articleID = rs.getString("articleID");
                    String title = rs.getString("title");
                    String shortDescription = rs.getString("shortDescription");
                    String author = rs.getString("author");
                    String status = rs.getString("status");
                    Date date = rs.getDate("date");
                    ArticleDTO dto = new ArticleDTO(articleID, title, shortDescription, author, date, status);
                    if (articleList == null) {
                        this.articleList = new ArrayList<>();
                    }
                    this.articleList.add(dto);
                }
            }
        } finally {
            closeDB();
        }
    }

    public ArticleDTO getArticleByID(String articleID) throws SQLException, NamingException {
        try {
            con = DBHealper.makeConnection();
            if (con != null) {
                String sql = "SELECT title, shortDescription, author, date, status, contents "
                        + "FROM tbl_Article "
                        + "WHERE articleID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, articleID);
                rs = stm.executeQuery();
                while (rs.next()) {
                    String title = rs.getString("title");
                    String shortDescription = rs.getString("shortDescription");
                    String author = rs.getString("author");
                    Date date = rs.getDate("date");
                    String status = rs.getString("status");
                    String contents = rs.getString("contents");
                    ArticleDTO dto = new ArticleDTO(articleID, title, shortDescription, author, date, status, contents);
                    return dto;
                }

            }
        } finally {
            closeDB();
        }
        return null;
    }

    public void getListArticleByTitle(String title) throws NamingException, SQLException {

        try {
            con = DBHealper.makeConnection();
            if (con != null) {
                String sql = "SELECT articleID, title, shortDescription, author, date, status "
                        + "FROM tbl_Article "
                        + "WHERE title LIKE ? "
                        + "AND status = 'Active' "
                        + "ORDER by date ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + title + "%");

                rs = stm.executeQuery();
                while (rs.next()) {
                    String articleID = rs.getString("articleID");
                    String titles = rs.getString("title");
                    String shortDescription = rs.getString("shortDescription");
                    String author = rs.getString("author");
                    String status = rs.getString("status");
                    Date date = rs.getDate("date");
                    ArticleDTO dto = new ArticleDTO(articleID, titles, shortDescription, author, date, status);
                    if (articleList == null) {
                        this.articleList = new ArrayList<>();
                    }
                    this.articleList.add(dto);
                }
            }
        } finally {
            closeDB();
        }
    }

    public boolean insertArticle(ArticleDTO dto) throws NamingException, SQLException {
        try {
            con = DBHealper.makeConnection();
            if (con != null) {
                String sql = "INSERT INTO tbl_Article(title, shortDescription, contents, date , status, author) "
                        + "VALUES  (?,?,?,?,?,?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getTitle());
                stm.setString(2, dto.getShortDescription());
                stm.setString(3, dto.getContents());
                stm.setDate(4, dto.getDate());
                stm.setString(5, dto.getStatus());
                stm.setString(6, dto.getAuthor());
                int row = stm.executeUpdate();
                if (row > 0) {
                    return true;
                }
            }
        } finally {
            closeDB();
        }
        return false;
    }

    public void getListArticleByTitleAndStatus(String title, String status) throws NamingException, SQLException {

        try {
            con = DBHealper.makeConnection();
            if (con != null) {
                String sql = "SELECT articleID, title, shortDescription, author, date, status "
                        + "FROM tbl_Article "
                        + "WHERE title LIKE ? "
                        + "AND status = ? "
                        + "ORDER by date ";
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + title + "%");
                stm.setString(2, status);

                rs = stm.executeQuery();
                while (rs.next()) {
                    String articleID = rs.getString("articleID");
                    String titles = rs.getString("title");
                    String shortDescription = rs.getString("shortDescription");
                    String author = rs.getString("author");
                    Date date = rs.getDate("date");
                    ArticleDTO dto = new ArticleDTO(articleID, titles, shortDescription, author, date, status);
                    if (articleList == null) {
                        this.articleList = new ArrayList<>();
                    }
                    this.articleList.add(dto);
                }
            }
        } finally {
            closeDB();
        }
    }

    public boolean changeStatusById(String articleID, String status) throws SQLException, NamingException{
        try{
            con = DBHealper.makeConnection();
            if(con != null){
                String sql = "UPDATE tbl_Article "
                        + "SET status = ? "
                        + "WHERE articleID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, status);
                stm.setString(2, articleID);
                int row = stm.executeUpdate();
                if(row > 0){
                    return true;
                }
            }
        }finally{
            closeDB();
        }
        return false;
    }
}
