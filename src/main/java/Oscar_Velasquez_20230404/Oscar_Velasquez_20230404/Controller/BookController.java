package Oscar_Velasquez_20230404.Oscar_Velasquez_20230404.Controller;


import Oscar_Velasquez_20230404.Oscar_Velasquez_20230404.Models.BookDTO;
import Oscar_Velasquez_20230404.Oscar_Velasquez_20230404.Service.BookService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

    @GetMapping("/getBookById/{id}")
    public BookDTO searchBookById(@PathVariable int id){
        return service.findBookById(id);
    }
    @PostMapping("/insertBook")
    public ResponseEntity<Map<String,Object>> insert (@Valid @RequestBody BookDTO dto, HttpServletRequest http){
        try{
            BookDTO answer = service.insertBook(dto);
            if(answer == null){
                return ResponseEntity.badRequest().body(Map.of("Error", "Error creando el usuario favor mandar datos validos"));
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("status", "created", "data", answer));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(Map.of("Error", "Error creando el usuarop "+e));
        }
    }
    @PutMapping("/updateBook/{id}")
    public ResponseEntity<?> updateBook(@PathVariable int id, @Valid @RequestBody BookDTO dto, BindingResult bindR){
        if(bindR.hasErrors()){
            return ResponseEntity.badRequest().body(Map.of("Error", bindR));
        }
        try{
            BookDTO answer = service.updateBook(dto, id);
            if(answer == null){
                return ResponseEntity.badRequest().body(Map.of("Error", "Datos nulos"));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("status", "success", "datos", dto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("Error", e));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,Object>> deleteBook(@PathVariable int id){
        try {
            if(!service.deleteBook(id)){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(Map.of("status", "usuario eliminado extosamente"));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(Map.of("status", "Error eliminando el usuario"+e));
        }
    }
}
