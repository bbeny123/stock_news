package org.beny.stock.controller;

import org.beny.stock.dto.comment.CommentListItem;
import org.beny.stock.dto.comment.CommentRequest;
import org.beny.stock.repository.CommentRepository;
import org.beny.stock.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/rest")
public class CommentREST extends BaseREST {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("/comment")
    public ResponseEntity<?> createComment(@Valid @RequestBody CommentRequest request) throws RuntimeException {
        return ok(CommentListItem.of(commentService.create(request.getComment(getContext()), request.getNewsId())));
    }

    @PatchMapping("/comment/{id}")
    public ResponseEntity<?> updateComment(@PathVariable("id") Long id, @Valid @RequestBody CommentRequest request) throws RuntimeException {
        return ok(CommentListItem.of(commentService.update(getContext(), request.updateComment(commentRepository.findOneById(id)))));
    }

    @DeleteMapping("/comment/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") Long id) throws RuntimeException {
        commentService.remove(getContext(), id);
        return ok();
    }

}
