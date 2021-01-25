package by.feedblog.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Like {
    private int id;
    private User user;
    private Post post;

    public Like(User user, Post post) {
        this.user = user;
        this.post = post;
    }

    public Like(User user) {
        this.user = user;
    }
}
