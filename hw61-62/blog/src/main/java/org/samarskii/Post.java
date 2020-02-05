package org.samarskii;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class Post {
    private int id;
    private String title;
    private String pathToImg;
    private String shortText;
    private String text;
    private Date published;
    private String author;
}
