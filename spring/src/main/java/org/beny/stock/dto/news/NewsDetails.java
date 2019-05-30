package org.beny.stock.dto.news;

import org.beny.stock.dto.comment.CommentListItem;
import org.beny.stock.model.News;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static java.util.Comparator.comparingLong;
import static java.util.stream.Collectors.toList;

public class NewsDetails {

    private Long id;
    private String title;
    private String description;
    private String link;
    private LocalDateTime date;
    private String user;
    private Long userId;
    private List<CommentListItem> comments;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
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

    public List<CommentListItem> getComments() {
        if (comments == null) {
            comments = new ArrayList<>();
        }
        return comments;
    }

    public void setComments(List<CommentListItem> comments) {
        this.comments = comments;
    }

    public static NewsDetails of(News news) {
        NewsDetails details = new NewsDetails();
        details.id = news.getId();
        details.title = news.getTitle();
        details.description = news.getDescription();
        details.link = news.getLink();
        details.date = news.getDate();
        details.user = news.getUser().getLogin();
        details.userId = news.getUser().getId();
        details.comments = news.getComments().stream()
                .map(CommentListItem::of)
                .sorted(comparingLong(CommentListItem::getId))
                .collect(toList());
        return details;
    }

}
