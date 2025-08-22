package Oscar_Velasquez_20230404.Oscar_Velasquez_20230404.Models;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@ToString @EqualsAndHashCode
@Getter @Setter
public class BookDTO {

    private int id;

    @NotBlank
    private String title;

    @NotBlank
    private String isbn;

    @Positive
    private int yearPublished;

    @NotBlank
    private String gender;

    @NotNull
    private int idAutor;
}
