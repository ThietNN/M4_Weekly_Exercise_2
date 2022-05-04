package thietnn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import thietnn.model.Book;
import thietnn.repository.IBookRepository;
import thietnn.service.IBookService;
import thietnn.service.ICategoryService;

import java.util.Optional;

@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private IBookService bookService;

    @Autowired
    private ICategoryService categoryService;

    @GetMapping("/home")
    public ModelAndView showBookList(){
        ModelAndView modelAndView = new ModelAndView("book/home");
        modelAndView.addObject("bookList",bookService.findAll());
        modelAndView.addObject("categoryList",categoryService.findAll());
        return modelAndView;
    }

    @GetMapping
    public ResponseEntity<Iterable<Book>> showAllBook(){
        return new ResponseEntity<>(bookService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Book> saveBook(@RequestBody Book book){
        return new ResponseEntity<>(bookService.save(book), HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<Book> deleteBook(@PathVariable Long id){
        Optional<Book> optionalBook = bookService.findById(id);
        if (!optionalBook.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        bookService.remove(id);
        return new ResponseEntity<>(optionalBook.get(),HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Book> editBook(@PathVariable Long id, @RequestBody Book book){
        Optional<Book> optionalBook = bookService.findById(id);
        if (!optionalBook.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        long idFound = optionalBook.get().getId();
        book.setId(idFound);
        return new ResponseEntity<>(bookService.save(book),HttpStatus.NO_CONTENT);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Book> findBook(@PathVariable Long id){
        Optional<Book> optionalBook = bookService.findById(id);
        if (!optionalBook.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(optionalBook.get(),HttpStatus.OK);
    }

}
