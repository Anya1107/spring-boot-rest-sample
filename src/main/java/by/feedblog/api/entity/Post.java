package by.feedblog.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseDataSource;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post implements Comparable<Post> {
    private int id;

    @NotBlank
    @Length(min = 3)
    private String title;

    @NotBlank
    @Length(min = 4)
    private String description;

    private Category category;

    private Tag tag;

    private User user;
    private Comment moderComment;
    private List<Comment> comments;
    private List<Like> likes;
    private List<Dislike> dislikes;
    private List<Reaction> reactions;
    private boolean isChecked = false;
    private Date date;

    public Post(String title, String description, Category category, Tag tag, User user, Date date) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.tag = tag;
        this.user = user;
        this.date = date;
    }

    public Post(String title, String description, Category category, Tag tag, User user, Comment moderComment, List<Comment> comments, boolean isChecked, Date date) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.tag = tag;
        this.user = user;
        this.moderComment = moderComment;
        this.comments = comments;
        this.isChecked = isChecked;
        this.date = date;
    }

    public Post(String title, String description, Category category, Tag tag, User user, List<Comment> comments, boolean isChecked) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.tag = tag;
        this.user = user;
        this.isChecked = isChecked;
    }

    public Post(String title, String description, Category category, Tag tag, User user, List<Comment> comments, boolean isChecked, List<Like> likes, List<Dislike> dislikes) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.tag = tag;
        this.user = user;
        this.isChecked = isChecked;
    }

    public Post(int id, String title, String description, Category category, Tag tag, User user, boolean isChecked, Date date) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.tag = tag;
        this.user = user;
        this.isChecked = isChecked;
        this.date = date;
    }

    public Post(String title, String description,Category category, Tag tag, User user, boolean isChecked, Date date) {
        this.title = title;
        this.description = description;
        this.category = category;
        this.tag = tag;
        this.user = user;
        this.isChecked = isChecked;
        this.date = date;
    }

    @Override
    public int compareTo(Post o) {
        return this.getDate().compareTo(o.getDate());
    }
}
