package Group.Better.repository;

import Group.Better.entity.Post;
import Group.Better.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostRepository {

    @Insert("insert into posts (title, content, user_id) values (#{title}, #{content}, #{user_id})")
    void insert(@Param("title") String title, @Param("content") String content, @Param("user_id") long user_id);

    @Select("select * from posts")
    List<Post> findAll();

    @Select("select * from posts where id = #{id}")
    Post findById(long id);

    @Update("UPDATE posts SET title = #{title}, content = #{content} WHERE id = #{id}")
    void update(@Param("id") long id, @Param("title") String title, @Param("content") String content);

    @Delete("delete from posts where id = #{id}")
    void deleteById(@Param("id")long id);


}
