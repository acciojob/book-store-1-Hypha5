package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("books")
public class BookController {

    private List<Book> bookList;
    //HashMap<Integer, Book> books = new HashMap<>();
    private int id;

    public List<Book> getBookList() {
        return bookList;
    }


    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookController(){
        this.bookList = new ArrayList<Book>();
        this.id = 1;
    }

    // post request /create-book
    // pass book as request body
    @PostMapping("/create-book")
    public ResponseEntity<Book> createBook(@RequestBody Book book){
        // Your code goes here.
        book.setId(id);
        bookList.add(book);
        id++;
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }
    @GetMapping("/get-book-by-id/{id}")
    // get request /get-book-by-id/{id}
    // pass id as path variable
    public ResponseEntity<Book> getBookById(@PathVariable("id")int id){
       for(Book b : bookList)
       {
           if(b.getId() == id){
               return new ResponseEntity<>(b,HttpStatus.OK);
           }
       }
       return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    // delete request /delete-book-by-id/{id}
    // pass id as path variable
    // deleteBookById()
   @GetMapping("/get-all-books")
//    // get request /get-all-books
    public ResponseEntity<List<Book>> getAllBooks()
    {

        return  new ResponseEntity<>(bookList, HttpStatus.OK);
    }
  //  @DeleteMapping("delete-all-books")
    public void deleteAllBooks()
    {
       // books.clear();
        bookList = new ArrayList<>();
        id = 1;
    }
    // delete request /delete-all-books
    // deleteAllBooks()
    @DeleteMapping("/delete-book-by-id/{id}")
    public void deleteByid(@PathVariable("id")int id)
    {
       for(int i=0;i<bookList.size();i++)
       {
           if(bookList.get(i).getId() == id){
               bookList.remove(i);
           }
       }
    }
    @GetMapping("/get-books-by-author")
    public ResponseEntity<Book> getBooksByAuthor(@RequestParam String author){
        for(Book b : bookList)
        {
            if(b.getAuthor().equals(author)){
                return new ResponseEntity<>(b,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    // get request /get-books-by-author
    // pass author name as request param
    // getBooksByAuthor()
    @GetMapping("/get-books-by-genre")
    public ResponseEntity<Book> getBooksByGenre(@RequestParam String genre)
    {
        for(Book b : bookList)
        {
            if(b.getGenre().equals(genre)){
                return new ResponseEntity<>(b,HttpStatus.OK);
            }
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    // get request /get-books-by-genre
    // pass genre name as request param
    // getBooksByGenre()
}
