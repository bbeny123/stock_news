package org.beny.stock.dto.news;

import org.beny.stock.model.News;
import org.beny.stock.security.UserContext;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

public class NewsRequest {

    @NotEmpty
    @Length(max = 60)
    private String title;

    @NotEmpty
    @Length(max = 300)
    private String description;

    @NotEmpty
    @Length(max = 1000)
    private String link;

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

    public News getNews(UserContext ctx) {
        News news = new News();
        news.setTitle(this.title);
        news.setDescription(this.description);
        news.setLink(this.link);
        news.setUser(ctx.getPrincipal());
        return news;
    }

    public News updateNews(News news) {
        news.setTitle(this.title);
        news.setDescription(this.description);
        news.setLink(this.link);
        return news;
    }

}
