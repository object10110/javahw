package org.itstep.db;

import lombok.*;

import java.util.Date;

// https://projectlombok.org/
// https://habr.com/ru/post/438870/
@Data
@Builder
public class Post {
    private int id;
    private String title;
    private String author;
    private String subTitle;
    private String content;
    private Date published;
}
