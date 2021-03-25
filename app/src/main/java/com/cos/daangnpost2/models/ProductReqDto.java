package com.cos.daangnpost2.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProductReqDto {

    private String title;
    private String content;
    private int price;
    private String img;
    private String category;
}
