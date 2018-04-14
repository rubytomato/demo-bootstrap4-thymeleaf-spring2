package com.example.demo.model;

import lombok.Builder;
import lombok.Value;

import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@Value
public class ItemDto implements Serializable {

    private Long id;
    private String name;
    private Integer price;
    private LocalDateTime createAt;

    public static ItemDto of(Long id, String name, Integer price, LocalDateTime createAt) {
        return ItemDto.builder()
                .id(id)
                .name(name)
                .price(price)
                .createAt(createAt)
                .build();
    }
}
