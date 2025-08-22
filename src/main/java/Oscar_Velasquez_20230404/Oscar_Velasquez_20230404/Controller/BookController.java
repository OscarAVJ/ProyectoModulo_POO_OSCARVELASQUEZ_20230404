package Oscar_Velasquez_20230404.Oscar_Velasquez_20230404.Controller;


import Oscar_Velasquez_20230404.Oscar_Velasquez_20230404.Models.BookDTO;
import Oscar_Velasquez_20230404.Oscar_Velasquez_20230404.Service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/apiBook")
public class BookController {
    @Autowired
    private BookService service;

    @GetMapping("/getAllBooks")
    public List<BookDTO> getAllBooks(){
        return service.getAllBooks();
    }
    @PostMapping("/insertBook")
    public ResponseEntity<?> insert (@Valid @RequestBody BookDTO dto, HttpServletRequest http){
        try{
            BookDTO answer = service.insertBook(dto);
            if(answer == null){
                return ResponseEntity.badRequest().body(Map.of("Error", "Error creando el usuario favor mandar datos validos"));
            }

            return ResponseEntity.ok(answer);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(Map.of("Error", "Error creando el usuarop "+e));
        }
    }
}
