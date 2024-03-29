package com.blog.repository;

import com.blog.entity.Comments;
import com.blog.payload.CommentDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comments,Long> {
    List<Comments> findByPostId(long postId);
}

