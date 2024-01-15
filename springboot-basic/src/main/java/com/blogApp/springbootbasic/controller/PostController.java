package com.blogApp.springbootbasic.controller;

import com.blogApp.springbootbasic.domain.Post;
import com.blogApp.springbootbasic.service.PostService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;


@Controller
@RequestMapping("v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //MVC
    @GetMapping         //when reading(displaying)
    public String postPage(Model model) {

        model.addAttribute("posts", postService.getPosts());
        return "post";
    }


    @GetMapping("/add")                          //when writing(creating)
    public String addPost(Model model) {
        model.addAttribute("post", new Post());
        return "addPost";
    }

    @PostMapping
    public String addPost(@ModelAttribute("post") @Valid Post post, Errors errors) {             //user gave us a post by the upper ^^ method
       if(errors.hasErrors()){      //error checking        //^^@Valid to check the validation
           return "addPost";        //will return the empty addpost page to try again
       }
        post.setCreatedOn(LocalDateTime.now());
        postService.addPost(post);
        return "redirect:/v1/posts";
    }


    @GetMapping("/edit/{id}")
    public String editPost(@PathVariable Integer id, Model model){
        Post post = postService.getPostById(id);
        model.addAttribute("post",post);
        return "editPost";
    }

    @PostMapping("/edit/{id}")                  //Not a reading method so POSTMapping
    public String editPost(@PathVariable Integer id,@ModelAttribute("post") @Valid Post post, Errors errors){
        if(errors.hasErrors()) {
            return "editPost";
        }
        postService.updatePostById(id,post);
        return "redirect:/v1/posts";
    }

    @PostMapping("/delete/{id}")
    public String deletePost(@PathVariable Integer id){
         postService.removePostById(id);
         return "redirect:/v1/posts";           //back to main page. SO READS. therefore GetMapping needed
    }

}
