package com.camacuchi.banktransactions.models;


import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class TransactionTotal {
    private int count;
    private int productCount;
    private long amount;

}
