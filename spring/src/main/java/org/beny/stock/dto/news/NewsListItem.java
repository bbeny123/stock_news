package org.beny.stock.dto.news;

import org.beny.stock.model.News;

import java.time.LocalDateTime;

public class NewsListItem {

    private Long id;
    private String title;
    private String description;
    private String link;
    private LocalDateTime date;
    private String user;
    private Long userId;

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

    public static NewsListItem of(News news) {
        NewsListItem item = new NewsListItem();
        item.id = news.getId();
        item.title = news.getTitle();
        item.description = news.getDescription();
        item.link = news.getLink();
        item.date = news.getDate();
        item.user = news.getUser().getLogin();
        item.userId = news.getUser().getId();
        return item;
    }

}
