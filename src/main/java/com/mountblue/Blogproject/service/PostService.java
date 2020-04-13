package com.mountblue.Blogproject.service;

import com.mountblue.Blogproject.controller.CommentController;
import com.mountblue.Blogproject.controller.TagController;
import com.mountblue.Blogproject.entity.Post;
import com.mountblue.Blogproject.entity.Tag;
import com.mountblue.Blogproject.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional

public class PostService {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private TagController tagController;

    @Autowired
    private CommentController commentController;

    public void PostAddService(Post post, Tag TagData) {

        List<Tag> tagList = tagController.tagList(post, TagData);;
        post.setTags(tagList);
//        tagController.tagList(post,TagData);
        postRepo.save(post);
    }


    public Page<Post> ViewPost(int page, String authorId) {
        System.out.println(authorId);
        return postRepo.findPostData(true, authorId, PageRequest.of(page, 3, Sort.by("updated_at").descending()));
    }

    public Page<Post> ViewGlobal(int page) {
        return postRepo.findGlobalData(true, PageRequest.of(page, 3, Sort.by("updated_at").descending()));
    }



    public List<Post> postList() {
        return postRepo.findAll();
    }

    public Post getPostData(Long id) {
        Post post = postRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("invalid id: " + id));
        return post;
    }

    public void storePost(Post post) {
        postRepo.save(post);
    }


    public void deletePost(Long id) {
        postRepo.deleteById(id);
    }


    public Post get(Long id) {
        return postRepo.findById(id).get();
    }

//
//    public List<Post>search(String keyword){
//        return postRepo.searchh(keyword);
//    }


    public Page<Post> sortingFields(int page) {
        return postRepo.findBySorting(true, PageRequest.of(page, 5, Sort.by("published_at").ascending()));
    }




    public Page<Post> tagData(String TagName,int page) {

        return postRepo.findByTag(true, TagName, PageRequest.of(page, 5, Sort.by("published_at").descending()));
    }
}
