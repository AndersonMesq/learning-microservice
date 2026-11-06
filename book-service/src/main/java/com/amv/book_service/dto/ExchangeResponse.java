package com.amv.book_service.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ExchangeResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private long id;

    private String from;

    private String to;

    private BigDecimal conversionFactor;

    private BigDecimal convertedValue;

    private String environment;
}