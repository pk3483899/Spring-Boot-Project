package com.blog.controller;

import com.blog.entity.Post;
import com.blog.excepiton.ResourceNotFoundException;
import com.blog.payload.PostDto;
import com.blog.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {
//@Autowired
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
//http://localhost:8080/api/posts
    @PostMapping
    public ResponseEntity<?> createPost(@Valid @RequestBody PostDto postDto, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PostDto dto = postService.createPost(postDto);
        return  new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    //http://localhost:8080/api/posts/id
    @DeleteMapping("/{id}")//path parameter
    public ResponseEntity<String> deletePost(@PathVariable long id) throws ResourceNotFoundException {
        postService.deletePost(id);
        return new ResponseEntity<>("Post is deleted", HttpStatus.OK);
    }
//http:localhost:8080/api/posts?pageNo&apgeSize
    @GetMapping
    public List<PostDto> getAllPosts(
            @RequestParam(name="pageNo", defaultValue ="0", required = false) int pageNo,
            @RequestParam(name="pageSize",defaultValue ="3",required = true) int pageSize,
            @RequestParam(name="sortBy",defaultValue = "id",required = false) String sortBy
){
       List<PostDto> postDtos = postService.getAllPosts(pageNo,pageSize,sortBy);
        return postDtos;
    }

}
