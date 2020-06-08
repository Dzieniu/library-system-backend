package com.github.dzieniu.libsysbe.service;

import com.github.dzieniu.libsysbe.repository.BookRepository;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

public class BookServiceTest{

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookService bookService;

    @Test
    public void testNothing(){

    }
}