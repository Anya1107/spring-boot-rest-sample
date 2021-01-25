package by.feedblog.api.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    private long id;

    @NotBlank
    @Length(min = 3)
    private String name;

    public Tag(String name) {
        this.name = name;
    }
}
