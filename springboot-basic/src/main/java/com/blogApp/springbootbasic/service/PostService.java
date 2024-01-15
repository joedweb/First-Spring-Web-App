package com.blogApp.springbootbasic.service;

import com.blogApp.springbootbasic.domain.Post;
import com.blogApp.springbootbasic.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PostService implements PostRepository{

    private JdbcTemplate JdbcTemplate;

    public PostService(JdbcTemplate jdbcTemplate) {
        this.JdbcTemplate = jdbcTemplate;
    }

    @Override
    public void addPost(Post post) {
        String SQL = """
                
                INSERT INTO posts(TITLE, DESCRIPTION,BODY,POST_STATUS,CREATED_ON,UPDATED_ON)
                VALUES(?,?,?,?,?,?)
                """;
        JdbcTemplate.update(SQL,
                post.getTitle(),
                post.getDescription(),
                post.getBody(),
                post.getStatus(),
                post.getCreatedOn(),
                post.getUpdatedOn());
    }

    @Override
    public List<Post> getPosts() {
        String SQL = "SELECT * FROM POSTS";
        return JdbcTemplate.query(SQL,(rs, rowNum) -> {
            Post post = new Post();
            post.setId(rs.getInt("ID"));
            post.setTitle(rs.getString("TITLE"));
            post.setDescription(rs.getString("DESCRIPTION"));
            post.setBody(rs.getString("BODY"));

            return post;
        });
    }


    @Override
    public Post getPostById(Integer id) {
        String SQL = "SELECT * FROM POSTS WHERE ID=?";
        return JdbcTemplate.query(SQL,rs->{
                Post post = null;
                if(rs.next()) {
                    post = new Post();
                    post.setId(rs.getInt("ID"));
                    post.setTitle(rs.getString("TITLE"));
                    post.setDescription(rs.getString("DESCRIPTION"));
                    post.setBody(rs.getString("BODY"));
                }
                    return post;
                },id);
    }


    @Override
    public int removePostById(Integer id) {
    String SQL = "DELETE FROM POSTS WHERE ID=?";
        return JdbcTemplate.update(SQL,id);
    }


    @Override
    public void updatePostById(Integer id, Post post) {
        String SQL = "UPDATE POSTS SET TITLE=?, DESCRIPTION=?, BODY=? WHERE ID=?";
    JdbcTemplate.update(SQL,post.getTitle(),post.getDescription(),post.getBody(),id);
    }

}
