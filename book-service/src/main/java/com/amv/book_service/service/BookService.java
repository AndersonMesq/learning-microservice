package com.amv.book_service.service;

import com.amv.book_service.controller.BookController;
import com.amv.book_service.dto.ExchangeResponse;
import com.amv.book_service.environment.InstanceInformationService;
import com.amv.book_service.model.Book;
import com.amv.book_service.proxy.ExchangeProxy;
import com.amv.book_service.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final Logger logger = LoggerFactory.getLogger(BookController.class);
    private final BookRepository repository;
    private final InstanceInformationService informationService;
    private final ExchangeProxy proxy;

    private Book findBook(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Book findBookAndCurrency(Long id, String currency) {
        String port = informationService.retrieveServerPort();
        String host = informationService.retrieveInstanceInfo();
        Book book = findBook(id);
        logger.info("Calculating the converted price of the book from {} USD to {}.", book.getPrice(), currency);
        ExchangeResponse exchangeResponse = proxy.getExchange(book.getPrice(), "USD", currency);

        book.setEnvironment("BOOK HOST"+ host + "PORT: " + port + "VERSION: kube-v1" + " EXCHANGE HOST: " + exchangeResponse.getEnvironment());
        book.setPrice(exchangeResponse.getConvertedValue());
        book.setCurrency(currency);
        return book;
    }
}
