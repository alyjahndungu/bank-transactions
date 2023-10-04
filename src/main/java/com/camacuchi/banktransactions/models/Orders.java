package com.camacuchi.banktransactions.models;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Orders implements Serializable {
    private long id;
    private Integer customerId;
    private Integer productId;
    private int productCount;
    private String orderType;
    private int amount;
}