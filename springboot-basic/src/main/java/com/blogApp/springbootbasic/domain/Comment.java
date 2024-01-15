package com.blogApp.springbootbasic.domain;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Comment {
    private Integer id;

    private String title;
    private String authorName;
    private String body;
    private LocalDateTime updatedOn;
}
