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

    private void init() {
        posts.add(Post.builder()
                .title("Man must explore, and this is exploration at its greatest")
                .subTitle("Problems look mighty small from 150 miles up")
                .author("Start Bootstrap")
                .published(new Date()).build());

        posts.add(Post.builder()
                .title("I believe every human has a finite number of heartbeats. I don't intend to waste any of mine.")
                .author("Start Bootstrap")
                .published(new Date()).build());

        posts.add(Post.builder()
                .title("Science has not yet mastered prophecy")
                .subTitle("We predict too much for the next year and yet far too little for the next ten.")
                .author("Start Bootstrap")
                .published(new Date()).build());

        posts.add(Post.builder()
                .title("Failure is not an option")
                .subTitle("Many say exploration is part of our destiny, but it’s actually our duty to future generations.")
                .author("Start Bootstrap")
                .published(new Date()).build());
    }
}
