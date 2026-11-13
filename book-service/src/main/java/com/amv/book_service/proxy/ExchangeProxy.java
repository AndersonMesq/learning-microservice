package com.amv.book_service.proxy;

import com.amv.book_service.dto.ExchangeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.math.BigDecimal;

//@FeignClient(name = "exchange-service")
@FeignClient(name = "exchange-service", url = "${EXCHANGE_SERVICE_SERVICE_HOST:http://host.docker.internal}:8000")
public interface ExchangeProxy {
    @GetMapping(value = "/exchange-service/{amount}/{from}/{to}")
    public ExchangeResponse getExchange(
            @PathVariable("amount") BigDecimal amount,
            @PathVariable("from") String from,
            @PathVariable("to") String to);
}
