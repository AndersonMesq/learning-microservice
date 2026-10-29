package com.amv.exchange_service.controller;

import com.amv.exchange_service.model.Exchange;
import com.amv.exchange_service.service.ExchangeService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;

import java.awt.*;
import java.math.BigDecimal;

@Tag(name = "Exchange Endpoint")
@RestController
@RequestMapping("exchange-service")
@RequiredArgsConstructor
public class ExchangeController {
    private final ExchangeService exchangeService;

    //http://localhost:8000/exchange-service/5/USD/BRL
    @GetMapping(value = "/{amount}/{from}/{to}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Exchange getExchange(@PathVariable("amount") BigDecimal amount, @PathVariable("from") String from, @PathVariable("to") String to) {
        return exchangeService.getExchangeValue(amount, from, to);
    }
}
