package Oscar_Velasquez_20230404.Oscar_Velasquez_20230404.Service;


import Oscar_Velasquez_20230404.Oscar_Velasquez_20230404.Entities.BookEntity;
import Oscar_Velasquez_20230404.Oscar_Velasquez_20230404.Models.BookDTO;
import Oscar_Velasquez_20230404.Oscar_Velasquez_20230404.Repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BookService {
    ///Aca nosotros le damos acceso a que podamos usar nuestro repositorio
    @Autowired
    private BookRepository repo;

    /***
     * Aca hacemos nuestra solicitud a la base de datos, la cual nos devuelve una lista de libros y nosotros la convertimos a DTO y a una lista
     * @return
     */
    public List<BookDTO> getAllBooks(){
        List<BookEntity> book = repo.findAll();
        return book.stream().map(this::BookToDTO).collect(Collectors.toList());
    }

    /***
     * Aca hacemos una peticion a la base para buscar por id,
     * @param id
     * @return
     */
    public BookDTO findBookById(int id) {
        BookEntity book = repo.getReferenceById(id);
        return BookToDTO(book);
    }

    /***
     * Aca hacemos la peticion para poder insertar un libro, primeramente pasamos los datos que nos den a entity para pasarlos a la base de datos y posteriormente lo guardamos
     * ya lo que retornamos es esa data como DTO
     * @param dto
     * @return
     */
    public BookDTO insertBook(BookDTO dto){
        if(dto == null){
            throw new IllegalArgumentException("Invalid data to create a book");
        }
        BookEntity entity = BookToEntity(dto);
        BookEntity saved = repo.save(entity);
         return BookToDTO(saved);
    }

    /***
     * Aca actualizamos nuestro libro, primero lo buscamos y si no retornamos un null
     * posteriormente accedemos a los datos de ese libro y le ponemos los valores que tenemos en el dto
     * @param dto
     * @param id
     * @return
     */
    public  BookDTO updateBook(BookDTO dto, int id){
        BookEntity ent = repo.getReferenceById(id);
        ent.setTitle(dto.getTitle());
        ent.setIsbn(dto.getIsbn());
        ent.setYearPublished(dto.getYearPublished());
        ent.setGender(dto.getGender());
        ent.setIdAutor(dto.getIdAutor());
        BookEntity updated = repo.save(ent);
        return BookToDTO(updated);
    }

    /***
     * Aca eliminamos un libro hacemos un findById y pues si esto es diferente de nulo retornamos un true
     * caso contrario retornamos un false y si ya es una excepcion un EmptyData......
     * @param id
     * @return
     */
    public boolean deleteBook(int id){
        try{
            BookEntity ent = repo.findById(id).orElseThrow(null);
            if (ent != null){
                repo.deleteById(id);
                return true;
            }else {
                return false;
            }
        }catch (EmptyResultDataAccessException e){
            throw new EmptyResultDataAccessException("No hay un libro con el id", id);
        }
    }

    /***
     * Aca contros convertimos la entidad a DTO cuando sea necesaria
     * @param entity
     * @return
     */
    public BookDTO BookToDTO(BookEntity entity){
        BookDTO dto = new BookDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setIsbn(entity.getIsbn());
        dto.setGender(entity.getGender());
        dto.setYearPublished(entity.getYearPublished());
        dto.setIdAutor(entity.getIdAutor());
        return dto;
    }

    /***
     * Aca nosotros retornamos nuestra entidad y le pedimos un dto para este proceso
     * @param dto
     * @return
     */
    public BookEntity BookToEntity(BookDTO dto){
        BookEntity entity = new BookEntity();
        entity.setTitle(dto.getTitle());
        entity.setIsbn(dto.getIsbn());
        entity.setGender(dto.getGender());
        entity.setYearPublished(dto.getYearPublished());
        entity.setIdAutor(dto.getIdAutor());
        return entity;
    }


}
