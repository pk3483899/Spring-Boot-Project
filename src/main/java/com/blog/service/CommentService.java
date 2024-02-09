package com.blog.service;

import com.blog.entity.Comments;
import com.blog.payload.CommentDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

public interface CommentService {
    public CommentDto createComment(long postId, CommentDto commentDto);

    void deleteComment(long commentId);

    List<CommentDto> getCommentsByPostId(long postId);
    List<CommentDto> getAllCommetns();
}
