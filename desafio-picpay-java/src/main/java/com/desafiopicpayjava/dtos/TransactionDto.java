package com.desafiopicpayjava.dtos;

import java.math.BigDecimal;

public record TransactionDto(

    Long senderId,

    Long receiverId,

    BigDecimal value
) { }

