package com.amv.exchange_service.service;

import com.amv.exchange_service.controller.ExchangeController;
import com.amv.exchange_service.environment.InstanceInformationService;
import com.amv.exchange_service.model.Exchange;
import com.amv.exchange_service.repository.ExchangeRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class ExchangeService {
    private final Logger logger = LoggerFactory.getLogger(ExchangeController.class);
    private final ExchangeRepository exchangeRepository;
    private final InstanceInformationService informationService;

    private Exchange findExchange(String from, String to) {
        return exchangeRepository.findByFromAndTo(from, to).orElseThrow();
    }

    public Exchange getExchangeValue(BigDecimal amount, String from, String to){
        logger.info("getExchange is called with -> {}, {} and {}", amount, from, to);
        Exchange exchange = findExchange(from, to);
        if (exchange == null) throw new RuntimeException("Currency Unsupported");

        String port = informationService.retrieveServerPort();
        String host = informationService.retrieveInstanceInfo();

        BigDecimal conversionFactor = exchange.getConversionFactor();
        BigDecimal convertedValue = conversionFactor.multiply(amount);
        exchange.setConvertedValue(convertedValue);
        exchange.setEnvironment(host + " VERSION: kube-v2 PORT " + port);

        return exchange;
    }
}
