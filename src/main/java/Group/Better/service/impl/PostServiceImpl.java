package Group.Better.service.impl;

import Group.Better.entity.Post;
import Group.Better.repository.PostRepository;
import Group.Better.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<Post> getAllPosts() {
        List<Post> posts = postRepository.findAll();
        posts.sort(Comparator.comparing(Post::getId).reversed());
        return posts;
    }

    @Override
    public Post getById(Long id) {
        return postRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(Post post){
        postRepository.save(post);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}