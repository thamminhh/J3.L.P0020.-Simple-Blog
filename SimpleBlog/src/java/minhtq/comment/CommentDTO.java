/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.comment;

/**
 *
 * @author admin
 */
public class CommentDTO {
    private int cmtID;
    private String detail;
    private String email;
    private String articleID;

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public CommentDTO() {
    }

    public CommentDTO(int cmtID, String detail, String email) {
        this.cmtID = cmtID;
        this.detail = detail;
        this.email = email;
    }

    public CommentDTO(int cmtID, String detail, String email, String articleID) {
        this.cmtID = cmtID;
        this.detail = detail;
        this.email = email;
        this.articleID = articleID;
    }

    public CommentDTO(String detail, String email, String articleID) {
        this.detail = detail;
        this.email = email;
        this.articleID = articleID;
    }
    
    

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    

    public int getCmtID() {
        return cmtID;
    }

    public void setCmtID(int cmtID) {
        this.cmtID = cmtID;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    
    
    
}
