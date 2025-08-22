package Oscar_Velasquez_20230404.Oscar_Velasquez_20230404.Entities;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "libros")
@Getter @Setter @ToString
@EqualsAndHashCode
public class BookEntity {


    @Id
    @SequenceGenerator(name = "seq_libro")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_libro")
    @Column(name ="IDLIBRO")
    private int id;

    @Column(name = "titulo")
    private String title;

    @Column(name = "ISBN")
    private String isbn;

    @Column(name = "AÑO_PUBLICACION")
    private int yearPublished;

    @Column(name = "GENERO")
    private String gender;

    @Column(name = "IDAUTOR")
    private int idAutor;

}
