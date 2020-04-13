package com.mountblue.Blogproject.controller;

import com.mountblue.Blogproject.entity.*;
import com.mountblue.Blogproject.repository.TagRepository;
import com.mountblue.Blogproject.service.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.util.List;
import org.slf4j.Logger;


@Controller
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private RoleService roleService;


    @Autowired
    private TagService tagService;

    private Logger logger= LoggerFactory.getLogger(UserController.class);

    @GetMapping("postForm")
        public String addPost(Model model,  Authentication authentication) {
            String name=authentication.getName();
       if(name.isEmpty()){
           logger.warn("no name found");
       }
        logger.info("Example log from {}", UserController.class.getSimpleName());
            UserData  listUser=userService.findUser(name);
        model.addAttribute("listUsers", listUser);
        model.addAttribute("posts", new Post());
        model.addAttribute("tags", new Tag());
        return "addpost";
    }


    @GetMapping("home")
    public String homepage(Model model, @RequestParam(defaultValue = "0") int page,Authentication authentication) {

        String name=authentication.getName();
        UserData  listUser=userService.findUser(name);
        Long id=listUser.getId();
        String userId=Long.toString(id);
        Page<Post> pagePost = postService.ViewPost(page, userId);
        List<Post> AllPost = postService.postList();
        List<CommenterDetails> allComments = commentService.CommenterList();
        model.addAttribute("AllPost", AllPost);
        model.addAttribute("listUsers", listUser);
        model.addAttribute("pagePost", pagePost);
        model.addAttribute("currentPage", page);
        model.addAttribute("commenterList",allComments);
        model.addAttribute("CommenterDetails", new CommenterDetails());
        model.addAttribute("authorName",listUser.getUsername());
        return "DisplayPost";
    }


    @GetMapping("SignUp")
    public String showRegistrationForm( Model model) {
        UserData userInfo = new UserData();
        model.addAttribute("user", userInfo);
        return "Signup";
    }
//    WebRequest request,



    @GetMapping("loginForm")
    public String loginForm(Model model) {
        model.addAttribute("users", new UserData());
        return "login";
    }


    @GetMapping("Global")
    public String homepage(Model model, @RequestParam(defaultValue = "0") int page) {
        List<UserData> listUser = userService.listAll();
        List<Tag> tagList = tagService.listAll();
        Page<Post> pagePost = postService.ViewGlobal(page);
        model.addAttribute("listUsers", listUser);
        model.addAttribute("pagePost", pagePost);
        model.addAttribute("currentPage", page);
        model.addAttribute("tagList", tagList);
        return "PostList";
    }


    @GetMapping("showAdmin")
    public String adminShow(Model model){
        List<UserData> lisUser=userService.listAll();
        model.addAttribute("listUsers",lisUser);
        return "UserList";
    }


    @GetMapping("editAdmin/{id}")
    public String DisplayUpdate(@PathVariable("id") Long id, Model model) {
        if(id==null){
            logger.warn("empty id");
        }

        UserData userData = userService.findById(id);
        model.addAttribute("userData", userData);
        List<Role>  roleList= roleService.listAll();
        model.addAttribute("roleList", roleList);
        return "UpdateUser";
    }


    @PostMapping("updateUsers/{id}")
    public String storePost(@PathVariable("id") Long id, UserData userData, Model model) {
//        System.out.println(id+"ffffffff");
//        System.out.println(userData.getRole().getName()+"jhjbjb");
//        System.out.println(roleService.getById(id).getName()+"nnmnmnmn");
//        userData.getRole().setName(roleService.getById(id).getName())
//        userData.setRole( roleService.getName("ROLE_USER"));
        userService.saveUsers(userData);
        return "redirect:homePage";
    }









    @RequestMapping("findHome")
    public String viewHomePage(Model model) {
        List<UserData> listUser = userService.listAll();
        model.addAttribute("listUsers", listUser);
        return "index";
    }

    @RequestMapping("/login")
    public String loginPage(Model model){
        model.addAttribute("users", new UserData());
        return "login";
    }

    @RequestMapping("/logout")
    public String loginOut(){
        return "login";
    }


    @GetMapping("usersignin")
    public String showPostForm(Model model) {
        model.addAttribute("users", new UserData());
        return "Register";
    }



    @PostMapping("userLogin")
    public  String userLogin(@ModelAttribute("users") UserData user, Model model){
        System.out.println(user.getEmail());
        return "indexx";
    }


}
