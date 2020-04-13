package com.mountblue.Blogproject.controller;


import com.mountblue.Blogproject.entity.CommenterDetails;
import com.mountblue.Blogproject.entity.Post;
import com.mountblue.Blogproject.entity.Tag;
import com.mountblue.Blogproject.entity.UserData;
import com.mountblue.Blogproject.repository.CommentRepository;
import com.mountblue.Blogproject.repository.PostRepository;
import com.mountblue.Blogproject.repository.TagRepository;
import com.mountblue.Blogproject.service.CommentService;
import com.mountblue.Blogproject.service.PostService;
import com.mountblue.Blogproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;


@Controller

public class PostController {

    @Autowired
    private PostRepository postRepo;

    @Autowired
    private TagRepository tagRepository;

    @Autowired
    private PostService postServices;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @PostMapping("savePosts")
    public String addPosts(@ModelAttribute("posts") Post post, Model model, @ModelAttribute("tags") Tag tags
    ,Authentication authentication) {
        String name=authentication.getName();
        UserData  listUser=userService.findUser(name);
        postServices.PostAddService(post, tags);
        return "homePage";
    }



    @GetMapping("showPostData/{id}")
    public String viewPost(@PathVariable("id") Long id, Model model) {
        Post post = postServices.getPostData(id);
        model.addAttribute("postData", post);
        List<UserData> listUser = userService.listAll();
        model.addAttribute("listUsers", listUser);
        model.addAttribute("user", new UserData());
        model.addAttribute("posts", new Post());
        model.addAttribute("CommenterDetails", new CommenterDetails());
        model.addAttribute("commentor",commentRepository.findByPost(post));
        model.addAttribute("tagList", tagRepository.findByid(id));
        return "ReadPost";
    }



    @RequestMapping(value = "/postComment/{id}")
    public String addComment(@PathVariable Long id, Model model, @RequestParam(name = "id") Long userId,
                             @RequestParam(name = "comment") String comment) {
        CommenterDetails Obj = new CommenterDetails();
        UserData userList = userService.findById(userId);
        Obj.setComment(comment);
        Obj.setEmail(userList.getEmail());
        Obj.setName(userList.getUsername());
        Obj.setPost(postServices.get(id));
        commentService.saveComment(Obj);
        return "DisplayPost";
    }



    @GetMapping("editPost/{id}")
    public String DisplayUpdate(@PathVariable("id") Long id, Model model) {
        Post post = postServices.getPostData(id);
        model.addAttribute("post", post);
        return "updatePost";
    }


    @PostMapping("update-posts")
    public String storePost(Post posts, Model model) {
        postServices.storePost(posts);
        return "homePage";
    }


    @GetMapping("deletePost/{id}")
    public String deletePost(@PathVariable("id") Long id, Model model) {
        postServices.deletePost(id);
        return "homePage";
    }




    @PostMapping("/search")
    public ModelAndView searchPosts ( String searchFields) {
        Page<Post> posts =  postRepo.search(searchFields, PageRequest.of(0,3, Sort.by("published_at").descending()));
        ModelAndView mav = new ModelAndView("PostList");
        mav.addObject("pagePost",posts);
        mav.addObject("currentPage", 0);
        mav.addObject("search-name", searchFields);
        return mav;
    }


    @GetMapping("sorting")
    public  String Sorting(Model model, @RequestParam(defaultValue = "0") int page) {
        Page<Post> postSort = postServices.sortingFields(page);
        model.addAttribute("pagePost",postSort);
        model.addAttribute("currentPage", page);
        model.addAttribute("tags",tagRepository.findAll());
        return "PostList";
    }

}
