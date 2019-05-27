package org.beny.stock.dto.comment;

import lombok.Data;
import org.beny.stock.model.Comment;

import java.time.LocalDateTime;

@Data
public class CommentList {

    private Long id;
    private String content;
    private LocalDateTime date;
    private String user;

    public static CommentList of(Comment comment) {
        CommentList item = new CommentList();
        item.id = comment.getId();
        item.content = comment.getContent();
        item.date = comment.getDate();
        item.user = comment.getUser().getLogin();
        return item;
    }

}
