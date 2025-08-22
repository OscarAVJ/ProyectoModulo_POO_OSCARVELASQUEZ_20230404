package Oscar_Velasquez_20230404.Oscar_Velasquez_20230404.Repositories;

import Oscar_Velasquez_20230404.Oscar_Velasquez_20230404.Entities.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Book;

/***
 * Aca accedemos a JPA REPOSITORY PARA PODER ACCEDER A TODOS LOS METODOS QUE NOS FACILITA
 */
public interface BookRepository extends JpaRepository<BookEntity, Integer> {
}
