package com.blogApp.springbootbasic.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Post {
    private Integer id;

        @NotNull                //validation checking to avoid empty inputs
        @Size(min = 3, max=50, message = "The title should be between 3 and 50 characters")
    private String title;

        @NotNull                //validation checking to avoid empty inputs
        @Size(min = 3, max=500, message = "The description should be between 3 and 50 characters")
    private String description;

        @NotNull                //validation checking to avoid empty inputs
        @Size(min = 3, max=1000, message = "The description should be between 3 and 50 characters")
    private String body;

    private PostStatus status;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<Comment> comments;


}
