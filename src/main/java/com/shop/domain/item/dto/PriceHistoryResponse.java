package com.shop.domain.item.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PriceHistoryResponse {
    private Long price;
    private String modifiedAt;

    private Long itemId;

    private Object date;

    public static List<PriceHistoryResponse> setAllDateInOrder(
            List<PriceHistoryResponse> priceHistoryResponses, LocalDate startDate) {
        for (PriceHistoryResponse priceHistoryResponse : priceHistoryResponses) {
            if (priceHistoryResponse != null) {
                priceHistoryResponse.setDate(startDate);
            }
            startDate = startDate.plusDays(1);
        }

        return priceHistoryResponses;
    }
}
