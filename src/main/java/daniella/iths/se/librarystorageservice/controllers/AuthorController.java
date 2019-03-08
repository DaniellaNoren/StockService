//package daniella.iths.se.librarystorageservice.controllers;
//
//import daniella.iths.se.librarystorageservice.resources.Author;
//import daniella.iths.se.librarystorageservice.resources.ListOfObject;
//import daniella.iths.se.librarystorageservice.storage.AuthorService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/authors")
//public class AuthorController {
//
//    @Autowired
//    AuthorService authorService;
//
//    @GetMapping()
//    public ListOfObject<Author> getAll(){
//        return authorService.getAll();
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<?> getOneAuthor(@PathVariable long id){
//        if(authorService.getAuthorById(id).isPresent())
//            return new ResponseEntity(authorService.getAuthorById(id).get(), HttpStatus.OK);
//        return new ResponseEntity("no no no author found", HttpStatus.NOT_FOUND);
//    }
//
//    @DeleteMapping("/delete")
//    public void deleteAll(){
//        authorService.deleteAll();
//    }
//
//    @PostMapping()
//    public void addAuthor(@RequestBody Author author){
//        System.out.println(author.getFirstName());
//        authorService.addAuthor(author);
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<?> updateAuthorName(@RequestBody Author author, @PathVariable long id){
//        if(authorService.getAuthorById(id).isPresent()) {
//            authorService.updateAuthor(id, author);
//            return new ResponseEntity(authorService.getAuthorById(id).get(), HttpStatus.OK);
//        }
//        return new ResponseEntity("where the author at???? not here!!", HttpStatus.NOT_FOUND);
//    }
//}
