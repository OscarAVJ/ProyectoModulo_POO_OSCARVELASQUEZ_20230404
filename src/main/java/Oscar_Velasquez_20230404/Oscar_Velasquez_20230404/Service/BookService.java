package Oscar_Velasquez_20230404.Oscar_Velasquez_20230404.Service;


import Oscar_Velasquez_20230404.Oscar_Velasquez_20230404.Entities.BookEntity;
import Oscar_Velasquez_20230404.Oscar_Velasquez_20230404.Models.BookDTO;
import Oscar_Velasquez_20230404.Oscar_Velasquez_20230404.Repositories.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        BookEntity ent = repo.findById(id).orElseThrow(->new IllegalArgumentException("No user found"));
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
}
