package com.fdmgroup.service;

@Service
public class postService {
	
	@Autowired
    private PostRepository postRepository;

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int postId) {
        return postRepository.findById(postId).orElse(null);
    }

    public Post createPost(Post post) {
        return postRepository.save(post);
    }

    public Post updatePost(int postId, Post postDetails) {
        Post post = postRepository.findById(postId).orElse(null);
        if (post != null) {
            post.setTitle(postDetails.getTitle());
            post.setContent(postDetails.getContent());
            return postRepository.save(post);
        }
        return null;
    }

    public boolean deletePost(int postId) {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isPresent()) {
            postRepository.delete(post.get());
            return true;
        }
        return false;
    }

}
