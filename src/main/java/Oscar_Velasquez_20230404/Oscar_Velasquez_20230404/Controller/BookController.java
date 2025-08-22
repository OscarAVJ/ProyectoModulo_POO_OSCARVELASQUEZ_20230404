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

    /***
     * Metodo para buscar todos los libros
     *
     * @return List<BookDTO></>
     */
    @GetMapping("/getAllBooks")
    public List<BookDTO> getAllBooks(){
        return service.getAllBooks();
    }

    /***
     * Metodo para buscar un libro por id, llamamos a nuestro servicio para poder hacer la peticion a la base de datos
     * @param id
     * @return BookDTO
     */
    @GetMapping("/getBookById/{id}")
    public BookDTO searchBookById(@PathVariable int id){
        return service.findBookById(id);
    }

    /***
     * Metpdp para insertar un libro, llamamos a nuestro service con su metodo de insert
     * en caso de que esta respuesta sea nula mandamos una bad request
     * si todo esta bien mandamos una respuesta de que fue creado y si se a va a la excepcion
     * @param dto
     * @param http
     * @return ResponseEntity<Map<String,Object>>
     */
    @PostMapping("/insertBook")
    public ResponseEntity<Map<String,Object>> insert (@Valid @RequestBody BookDTO dto, HttpServletRequest http){
        try{
            BookDTO answer = service.insertBook(dto);
            if(answer == null){
                return ResponseEntity.badRequest().body(Map.of("Error", "Error creando el usuario favor mandar datos validos"));
            }

            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("status", "created", "data", answer));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(Map.of("Error", "Error creando el usuario ", "detail", e.getMessage()));
        }
    }

    /***
     * Metodo para actualizar un libro, si nuestro BindingResoult da error retornamos una badRequest
     * y luego intentamos actualizar llamando a nuestro servicio
     * si nuestra respuesta esta nula mandamos un badRequest
     * si ya todo esta bien mandamos una respuesta ok junto con su respectivo mensaje
     * @param id
     * @param dto
     * @param bindR
     * @return
     */
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
            return ResponseEntity.ok().body(Map.of("status", "success", "datos", dto));
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Map.of("Error", e.getMessage()));
        }
    }


    /***
     * Aca pues eliminamos un libro, hacemos la peticion al service y si esta retorna un false pues entonces mandamos un error de notFound
     * si todo esta bien pues mandamos un mensaje de exito
     * y si se va al catch pues un server error
     * @param id
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Map<String,Object>> deleteBook(@PathVariable int id){
        try {
            if(!service.deleteBook(id)){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(Map.of("status", "success", "message", "Libro eliminado exitosamente"));
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(Map.of("Error", "Error eliminando el libro"+ e.getMessage()));
        }
    }
}
