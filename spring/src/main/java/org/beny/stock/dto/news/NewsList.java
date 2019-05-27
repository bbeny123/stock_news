package org.beny.stock.dto.news;

import lombok.Data;
import org.beny.stock.model.News;

import java.time.LocalDateTime;

@Data
public class NewsList {

    private Long id;
    private String title;
    private String description;
    private String link;
    private LocalDateTime date;
    private String user;

    public static NewsList of(News news) {
        NewsList details = new NewsList();
        details.id = news.getId();
        details.title = news.getTitle();
        details.description = news.getDescription();
        details.link = news.getLink();
        details.date = news.getDate();
        details.user = news.getUser().getLogin();
        return details;
    }

}
