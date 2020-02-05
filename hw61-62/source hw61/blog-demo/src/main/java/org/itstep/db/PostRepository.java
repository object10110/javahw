package org.itstep.db;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class PostRepository {
    List<Post> posts;

    private static PostRepository instance = new PostRepository();

    private PostRepository() {
        posts = new CopyOnWriteArrayList<>();
        init();
    }

    public static PostRepository getInstance() {
        return instance;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void save(Post post) {
        posts.add(post);
    }

    private void init() {
        posts.add(Post.builder()
                .title("Первый пост")
                .subTitle("Problems look mighty small from 150 miles up")
                .author("Start Bootstrap")
                .published(new Date()).build());

        posts.add(Post.builder()
                .title("Второй пост")
                .author("Start Bootstrap")
                .published(new Date()).build());

        posts.add(Post.builder()
                .title("Третий пост")
                .subTitle("We predict too much for the next year and yet far too little for the next ten.")
                .author("Start Bootstrap")
                .published(new Date()).build());

        posts.add(Post.builder()
                .title("Четвертый пост")
                .subTitle("Many say exploration is part of our destiny, but it’s actually our duty to future generations.")
                .author("Start Bootstrap")
                .published(new Date()).build());
    }
}
