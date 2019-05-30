package org.beny.stock.dto.comment;

import org.beny.stock.model.Comment;

import java.time.LocalDateTime;

public class CommentListItem {

    private Long id;
    private String content;
    private LocalDateTime date;
    private String user;
    private Long userId;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public static CommentListItem of(Comment comment) {
        CommentListItem item = new CommentListItem();
        item.id = comment.getId();
        item.content = comment.getContent();
        item.date = comment.getDate();
        item.user = comment.getUser().getLogin();
        item.userId = comment.getUser().getId();
        return item;
    }

}
