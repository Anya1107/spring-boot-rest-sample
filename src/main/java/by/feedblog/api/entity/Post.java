package by.feedblog.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "posts")
public class Post implements Comparable<Post> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank
    @Length(min = 3, max = 10)
    private String title;

    @NotBlank
    @Length(min = 4, max = 100)
    private String description;

    @OneToOne
    private Category category;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Tag> tag;

    @OneToOne
    private User user;

//    @OneToOne
//    private Comment moderComment;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Like> likes;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Dislike> dislikes;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<Reaction> reactions;

    private boolean isChecked = false;

    private Date date;

    @Override
    public int compareTo(Post o) {
        return this.getDate().compareTo(o.getDate());
    }
}
