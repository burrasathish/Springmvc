package com.mountblue.Blogproject.repository;

import com.mountblue.Blogproject.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    public Tag findByTagName(String tagName);

    @Query("select t from Tag t join t.post p where p.id=?1")
    public List<Tag> findByid(Long id);



}
