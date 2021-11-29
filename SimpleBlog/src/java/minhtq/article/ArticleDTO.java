/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package minhtq.article;

import java.sql.Date;

/**
 *
 * @author admin
 */
public class ArticleDTO {
    
    String articleID;
    String title;
    String shortDescription;
    String author;
    Date date;
    String contents;
    String status;

    public ArticleDTO() {
    }

    public ArticleDTO(String articleID, String title, String shortDescription, String author, Date date, String status, String contents) {
        this.articleID = articleID;
        this.title = title;
        this.shortDescription = shortDescription;
        this.author = author;
        this.date = date;
        this.contents = contents;
        this.status = status;
    }

    public ArticleDTO(String articleID, String title, String shortDescription, String author, Date date, String status) {
        this.articleID = articleID;
        this.title = title;
        this.shortDescription = shortDescription;
        this.author = author;
        this.date = date;
        this.status = status;
    }

    public ArticleDTO(String title, String shortDescription, String author, Date date, String contents, String status) {
        this.title = title;
        this.shortDescription = shortDescription;
        this.author = author;
        this.date = date;
        this.contents = contents;
        this.status = status;
    }
    

    
    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    
    
    
    
}
