package by.feedblog.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reaction {
    private int id;
    private String reaction;
    private User user;
    private Post post;


    public Reaction(String reaction, User user, Post post) {
        this.reaction = reaction;
        this.user = user;
        this.post = post;
    }

}
