package com.amv.book_service.service;

import com.amv.book_service.dto.ExchangeResponse;
import com.amv.book_service.environment.InstanceInformationService;
import com.amv.book_service.model.Book;
import com.amv.book_service.proxy.ExchangeProxy;
import com.amv.book_service.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;
    private final InstanceInformationService informationService;
    private final ExchangeProxy proxy;

    private Book findBook(Long id){
        return repository.findById(id).orElseThrow();
    }

    public Book findBookAndCurrency(Long id, String currency){
        Book book = findBook(id);
        String port = informationService.retrieveServerPort();
        ExchangeResponse exchangeResponse = proxy.getExchange(book.getPrice(), "USD", currency);

        book.setEnvironment("BOOK PORT: " + port + " EXCHANGE PORT: " + exchangeResponse.getEnvironment());
        book.setPrice(exchangeResponse.getConvertedValue());
        book.setCurrency(currency);
        return book;
    }
}
