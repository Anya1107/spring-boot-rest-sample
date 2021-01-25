package by.feedblog.api.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import java.awt.print.Book;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private int id;

    @NotBlank
    @Length(min = 3)
    private String username;

    @NotBlank
    @Length(min = 4, max = 8)
    private String password;

    @NotBlank
    @Length(min = 3)
    private String fullName;

    @NotNull
    @Positive
    private int age;

    @NotNull
    private Role role;

    private List<Bookmark> bookmarks;

    public User(String username, String password, String fullName, int age) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.age = age;
    }

    public User(String username, String password, String fullName, int age, Role role) {
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.age = age;
        this.role = role;
    }

    public User(int id, String username, String password, String fullName, int age) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.age = age;
    }
}
