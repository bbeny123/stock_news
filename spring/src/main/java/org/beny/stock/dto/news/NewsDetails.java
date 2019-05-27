package org.beny.stock.dto.news;

import lombok.Data;
import org.beny.stock.dto.comment.CommentList;
import org.beny.stock.model.News;

import java.time.LocalDateTime;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Data
public class NewsDetails {

    private Long id;
    private String title;
    private String description;
    private String link;
    private LocalDateTime date;
    private String user;
    private Set<CommentList> comments;
    
    public static NewsDetails of(News news) {
        NewsDetails details = new NewsDetails();
        details.id = news.getId();
        details.title = news.getTitle();
        details.description = news.getDescription();
        details.link = news.getLink();
        details.date = news.getDate();
        details.user = news.getUser().getLogin();
        details.comments = news.getComments().stream().map(CommentList::of).collect(toSet());
        return details;
    }

}
