package com.mountblue.Blogproject.repository;

import com.mountblue.Blogproject.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("SELECT e FROM  Post e WHERE e.is_published=?1 and e.author=?2")
    Page<Post> findPostData(Boolean value,String id, Pageable pageable);


    @Query("SELECT e FROM  Post e WHERE e.is_published=?1")
    Page<Post> findGlobalData(Boolean value , Pageable pageable);


//    @Query(value = "SELECT c FROM Post c WHERE c.title LIKE '%' || :keyword || '%'"
//            + " OR c.content LIKE '%' || :keyword || '%'"
//            + " OR c.exceprt LIKE '%' || :keyword || '%'")

    @Query("select  p from Post p  where  p.title like ?1 " +
            "or p.author like ?1 or p.excerpt like ?1")
    Page<Post> search(String name, Pageable pageable);


   @Query("select p from Post p join p.tags t where  p.is_published=?1 and t.tagName=?2")
    Page<Post> findByTag(boolean value,String TagName,Pageable pageable);

    @Query("select p from Post p where p.is_published= ?1")
   Page<Post> findBySorting(boolean value, Pageable pageable);


}
