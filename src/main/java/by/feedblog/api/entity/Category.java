package by.feedblog.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    private int id;

    @NotBlank
    @Length(min = 3)
    private String title;

    public Category(String title) {
        this.title = title;
    }
}
