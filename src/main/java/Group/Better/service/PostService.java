package Group.Better.service;

import Group.Better.entity.Post;

import java.util.List;

public interface PostService {

    List<Post> getAllPosts();

    Post getById(Long id);

    void save(Post post);

    void delete(Long id);
}
