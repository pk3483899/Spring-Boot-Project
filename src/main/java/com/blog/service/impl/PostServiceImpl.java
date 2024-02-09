package com.blog.service.impl;

import com.blog.entity.Post;
import com.blog.excepiton.ResourceNotFoundException;
import com.blog.payload.PostDto;
import com.blog.repository.PostRepository;
import com.blog.service.PostService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
//@Autowired
    private PostRepository postRepo;

    public PostServiceImpl(PostRepository postRepo) {
        this.postRepo = postRepo;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
        Post post=new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        Post savedPost = postRepo.save(post);   //here the return type of post is saved post is post

//After saving the data into database

        PostDto dto=new PostDto();
        dto.setId(savedPost.getId());
        dto.setTitle(savedPost.getTitle());
        dto.setDescription(savedPost.getDescription());
        dto.setContent(savedPost.getContent());

//        dto.setMessage("Post is created and saved");

        return dto;
    }

    @Override
    public void deletePost(long id) throws ResourceNotFoundException {
        Optional<Post> byId = postRepo.findById(id);
        if(byId.isPresent()){
            postRepo.deleteById(id);
        }
        else {
            throw new ResourceNotFoundException("Post not found at id "+id);
        }

    }

    @Override
    public List<PostDto> getAllPosts(int pageNo, int pageSize, String sortBy) {
        PageRequest pageable = PageRequest.of(pageNo,pageSize,Sort.by(sortBy));
        Page<Post> pagePost = postRepo.findAll(pageable);
        List<Post> posts = pagePost.getContent();   // To cnvert page onto List
        List<PostDto> dtos = posts.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        return dtos;
    }


    PostDto mapToDto(Post post){
        PostDto dto=new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());

        return dto;
        
    }


}
