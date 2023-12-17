package com.blog.service;

import com.blog.excepiton.ResourceNotFoundException;
import com.blog.payload.PostDto;

import java.util.List;

public interface PostService {
    public PostDto createPost(PostDto postDto);
    public void deletePost(long id) throws ResourceNotFoundException;

    List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy);
}

