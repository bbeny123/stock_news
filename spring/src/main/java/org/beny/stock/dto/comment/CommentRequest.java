package org.beny.stock.dto.comment;

import lombok.Data;
import org.beny.stock.model.Comment;
import org.beny.stock.security.UserContext;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CommentRequest {

    @NotEmpty
    @Length(max = 600)
    private String content;

    @NotNull
    private Long newsId;

    public Comment getComment(UserContext ctx) {
        Comment comment = new Comment();
        comment.setContent(content);
        comment.setUser(ctx.getPrincipal());
        return comment;
    }

    public Comment updateComment(Comment comment) {
        comment.setContent(this.content);
        return comment;
    }

}
