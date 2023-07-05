package Group.Better.repository;

import Group.Better.entity.Post;
import Group.Better.entity.User;
import org.apache.ibatis.annotations.*;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface PostRepository extends Repository<Post, Long> {

    @Query(value = "insert into posts (title, content, user_id) values (#{title}, #{content}, #{user_id})", nativeQuery = true)
    void insert(@Param("title") String title, @Param("content") String content, @Param("user_id") long user_id);

    @Query("select p from Post p")
    List<Post> findAll();

    Post findById(long id);

    @Query(value = "UPDATE posts SET title = #{title}, content = #{content} WHERE id = #{id}", nativeQuery = true)
    void update(@Param("id") long id, @Param("title") String title, @Param("content") String content);

    @Query(value = "delete from posts where id = #{id}", nativeQuery = true)
    void deleteById(@Param("id")long id);

}
