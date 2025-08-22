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
    @Autowired
    private BookRepository repo;


    public List<BookDTO> getAllBooks(){
        List<BookEntity> book = repo.findAll();
        return book.stream().map(this::BookToDTO).collect(Collectors.toList());
    }

    public BookDTO insertBook(BookDTO dto){
        if(dto == null){
            throw new IllegalArgumentException("Invalid data to create a book");
        }
        BookEntity entity = BookToEntity(dto);
        BookEntity saved = repo.save(entity);
         return BookToDTO(saved);
    }

    public  BookDTO updateBook(BookDTO dto, int id){
        BookEntity ent = repo.findById(id).orElseThrow(null );
        ent.setTitle(dto.getTitle());
        ent.setIsbn(dto.getIsbn());
        ent.setYearPublished(dto.getYearPublished());
        ent.setGender(dto.getGender());
        ent.setIdAutor(dto.getIdAutor());
        BookEntity updated = repo.save(ent);
        return BookToDTO(updated);
    }
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
            throw new EmptyResultDataAccessException("No hay un usuario con el id", id);
        }
    }
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
    public BookEntity BookToEntity(BookDTO dto){
        BookEntity entity = new BookEntity();
        entity.setTitle(dto.getTitle());
        entity.setIsbn(dto.getIsbn());
        entity.setGender(dto.getGender());
        entity.setYearPublished(dto.getYearPublished());
        entity.setIdAutor(dto.getIdAutor());
        return entity;
    }

    public BookDTO findBookById(int id) {
        BookEntity book = repo.getReferenceById(id);
        return BookToDTO(book);
    }
}
