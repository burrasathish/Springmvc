package com.mountblue.Blogproject.service;

import com.mountblue.Blogproject.entity.Post;
import com.mountblue.Blogproject.entity.Tag;
import com.mountblue.Blogproject.repository.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static java.util.Objects.isNull;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TagService {

    @Autowired
    private TagRepository tagRepository;


    public List<Tag> postTags(Post post, Tag tags) {
        List<Tag> tagList = new ArrayList<>();
        String[] arrayList = tags.getTagName().split(",");
        Tag found = null;
        try {
            for (String taglist : arrayList) {
                found = tagRepository.findByTagName(taglist); //change

                if (isNull(found)) {
                    found = new Tag();
                    List<Post> posts = new ArrayList<>();
                    posts.add(post);
                    found.setTagName(taglist);
                    found.setPost(posts);
                } else {
                    found.getPost().add(post);
                }
                tagRepository.save(found);
                tagList.add(found);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return tagList;
    }

    public List<Tag> listAll() {

        return tagRepository.findAll();
    }


   
}
