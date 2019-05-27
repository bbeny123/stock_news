package org.beny.stock.dto.comment;

import org.beny.stock.model.Comment;

import java.time.LocalDateTime;

public class CommentList {

    private Long id;
    private String content;
    private LocalDateTime date;
    private String user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public static CommentList of(Comment comment) {
        CommentList item = new CommentList();
        item.id = comment.getId();
        item.content = comment.getContent();
        item.date = comment.getDate();
        item.user = comment.getUser().getLogin();
        return item;
    }

}
