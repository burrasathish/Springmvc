package com.mountblue.Blogproject.service;


import com.mountblue.Blogproject.entity.CommenterDetails;
import com.mountblue.Blogproject.entity.Post;
import com.mountblue.Blogproject.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    CommentRepository commentRepository;


    public void saveComment(CommenterDetails cd) {
        commentRepository.save(cd);
    }


    public List<CommenterDetails> CommenterList() {
        return commentRepository.findAll();
    }


    public CommenterDetails getCommentData(Long id) {
        CommenterDetails post = commentRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("invalid id: " + id));
        return post;
    }




}
