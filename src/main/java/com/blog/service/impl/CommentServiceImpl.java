package com.blog.service.impl;

import com.blog.entity.Comments;
import com.blog.entity.Post;
import com.blog.excepiton.ResourceNotFoundException;
import com.blog.payload.CommentDto;
import com.blog.repository.CommentRepository;
import com.blog.repository.PostRepository;
import com.blog.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.nio.file.ReadOnlyFileSystemException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private PostRepository postRepository;

    private CommentRepository commentRepository;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post not found at at id:" + postId)
        );
        Comments comments=new Comments();
        comments.setName(commentDto.getName());
        comments.setEmail(commentDto.getEmail());
        comments.setBody(commentDto.getBody());
        comments.setPost(post);//setting the comment for this post

        Comments savedComment = commentRepository.save(comments);
        CommentDto dto=new CommentDto();
        dto.setId(savedComment.getId());
        dto.setName(savedComment.getName());
        dto.setBody(savedComment.getBody());
        dto.setEmail(savedComment.getEmail());
        return dto;
    }

    @Override
    public void deleteComment(long commentId) {
        Comments comments = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment not found at id: " + commentId)
        );
        commentRepository.deleteById(commentId);
    }

    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        List<Comments> comments = commentRepository.findByPostId(postId);
        List<CommentDto> commeentDtos = comments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return commeentDtos;

    }

    @Override
    public List<CommentDto> getAllCommetns() {
        List<Comments> allComments = commentRepository.findAll();
        List<CommentDto> dto = allComments.stream().map(c -> mapToDto(c)).collect(Collectors.toList());
        return dto;
    }


    CommentDto mapToDto(Comments comments){
        CommentDto dto=new CommentDto();
        dto.setId(comments.getId());
        dto.setName(comments.getName());
        dto.setEmail(comments.getEmail());
        dto.setBody(comments.getBody());
        return dto;
    }

}
