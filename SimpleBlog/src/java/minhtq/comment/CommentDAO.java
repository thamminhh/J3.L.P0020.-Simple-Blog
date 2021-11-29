/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.comment;

import java.sql.Connection;
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
public class CommentDAO {

    List<CommentDTO> cmtList;
    private Connection con;
    private PreparedStatement stm;
    private ResultSet rs;

    public List<CommentDTO> getList() {
        return cmtList;
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

    public void getAllCmtByArticleID(String articleID) throws NamingException, SQLException {
        try {
            con = DBHealper.makeConnection();
            
            if (con != null) {
                
                String sql = "Select cmtID, detail, email FROM dbo.Comment Where articleID = ?";
                stm = con.prepareStatement(sql);
                stm.setString(1, articleID);
                rs = stm.executeQuery();
                while(rs.next()){
                    String detail = rs.getString("detail");
                    int cmtID = rs.getInt("cmtID");
                    String email = rs.getString("email");
                    CommentDTO dto = new CommentDTO(cmtID, detail, email);
                    if (cmtList == null) {
                        this.cmtList = new ArrayList<>();
                    }
                    this.cmtList.add(dto); 
                }
            }
        } finally {
            closeDB();
        }

    }
    
    public boolean addCommentInArticle(CommentDTO dto) throws NamingException, SQLException{
        try {
            con = DBHealper.makeConnection();
            if(con != null){
                String sql = "INSERT INTO Comment(email, articleID, detail) VALUES (?,?,?) ";
                stm = con.prepareStatement(sql);
                stm.setString(1, dto.getEmail());
                stm.setString(2, dto.getArticleID());
                stm.setString(3, dto.getDetail());
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
