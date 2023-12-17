package com.blog.payload;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {

    private long id;

    @NotEmpty
    @Size(min=2,message = "Title should be at least 2 characters")
    private String title;

    @NotEmpty
    @Size(min=4, message = "Description should be at least 4 characters")
    private String description;

    @NotEmpty
    @Size(min=4, message = "Description should be at least 4 characters")
    private String content;

    @NotEmpty
    @Size(min=4, message = "Description should be at least 4 characters")
    private String message;
}
