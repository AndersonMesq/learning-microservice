package com.amv.exchange_service.service;

import com.amv.exchange_service.environment.InstanceInformationService;
import com.amv.exchange_service.model.Exchange;
import com.amv.exchange_service.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ExchangeService {
    private final ExchangeRepository exchangeRepository;
    private final InstanceInformationService informationService;

    private Exchange findExchange(String from, String to) {
        return exchangeRepository.findByFromAndTo(from, to).orElseThrow();

    }

    public Exchange getExchangeValue(BigDecimal amont, String from, String to){
        Exchange exchange = findExchange(from, to);
        if (exchange == null) throw new RuntimeException("Currency Unsupported");
        BigDecimal conversionFactor = exchange.getConversionFactor();
        BigDecimal convertedValue = conversionFactor.multiply(amont);
        exchange.setConvertedValue(convertedValue);
        exchange.setEnvironment("PORT: " + informationService.retrieveServerPort());

        return exchange;
    }
}
