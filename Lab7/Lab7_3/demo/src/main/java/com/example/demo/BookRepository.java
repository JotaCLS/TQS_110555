package com.example.demo;


import java.util.List;

import org.springframework.stereotype.Repository;



@Repository
public interface BookRepository {
    
    List<Book> findAll();

    Book findById(Long id);

    Book save(Book book);

    void deleteById(Long id);
    
}
