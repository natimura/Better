package Group.Better.repository;

import Group.Better.entity.PostEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface PostRepository {

    @Insert("insert into posts (title, content) values (#{title}, #{content})")
    void insert(@Param("title") String title, @Param("content") String content);

    @Select("select * from posts")
    List<PostEntity> findAll();

    @Select("select * from posts where id = #{id}")
    PostEntity findById(long id);

    @Update("UPDATE posts SET title = #{title}, content = #{content} WHERE id = #{id}")
    void update(@Param("id") long id, @Param("title") String title, @Param("content") String content);

    @Delete("delete from posts where id = #{id}")
    void deleteById(@Param("id")long id);


}
