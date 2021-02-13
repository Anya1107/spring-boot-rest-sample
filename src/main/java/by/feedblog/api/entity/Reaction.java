package by.feedblog.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reactions")
public class Reaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String reaction;

    @ManyToOne
    private User user;

    public Reaction(String reaction) {
        this.reaction = reaction;
    }

    public Reaction(String reaction, User user) {
        this.reaction = reaction;
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reaction reaction1 = (Reaction) o;
        return Objects.equals(reaction, reaction1.reaction) &&
                Objects.equals(user, reaction1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reaction, user);
    }
}
