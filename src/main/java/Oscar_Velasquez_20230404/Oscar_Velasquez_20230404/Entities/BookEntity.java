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
    /***
     * Aca definimos nuestra entidad
     * Definimos la secuencia que incrementara nuestro id
     * y posteriormente las columnas con su respectivo getter y setter
     */

    @Id
    @SequenceGenerator(name = "seq_libro", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_libro")
    @Column(name ="IDLIBRO")
    private int id;

    ///Columna titulo en la db
    @Column(name = "TITULO")
    private String title;

    //Columna isbn en la db
    @Column(name = "ISBN")
    private String isbn;

    //Columna añopublicacion en la db
    @Column(name = "AÑO_PUBLICACION")
    private int yearPublished;

    //Columna genero en la db
    @Column(name = "GENERO")
    private String gender;

    //Columna idAutor en la db
    @Column(name = "IDAUTOR")
    private int idAutor;

}
