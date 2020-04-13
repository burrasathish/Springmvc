package com.mountblue.Blogproject.controller;

import com.mountblue.Blogproject.entity.Post;
import com.mountblue.Blogproject.entity.Tag;
import com.mountblue.Blogproject.repository.TagRepository;
import com.mountblue.Blogproject.service.PostService;
import com.mountblue.Blogproject.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@Controller
public class TagController {

    @Autowired
    private TagService tagService;
    @Autowired
    private PostService postService;
    @Autowired
    private TagRepository tagRepository;

    public List<Tag> tagList(Post post, Tag tags) {
        return tagService.postTags(post, tags);
    }


    @RequestMapping("/tag/{tagName}")
    //public String filterTag(@PathVariable  String tagName, @RequestParam(defaultValue = "0") int page, Model model){
    public String filterTag(@PathVariable String tagName, @RequestParam(defaultValue = "0") int page, Model model){
            Page<Post> post=postService.tagData(tagName,page);
       model.addAttribute("pagePost",post);
       model.addAttribute("currentPage", page);
       model.addAttribute("tagList",tagRepository.findAll());
        return "PostList";
    }



}
