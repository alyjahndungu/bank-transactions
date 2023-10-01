package com.camacuchi.banktransactions.controller;


import com.camacuchi.banktransactions.models.BankBalance;
import com.camacuchi.banktransactions.service.BankBalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BankBalanceController {

    private final BankBalanceService bankBalanceService;

    @GetMapping(value = "/{bankBalanceId}", produces = "application/json")
    public ResponseEntity<BankBalance> getBankBalance(@PathVariable("bankBalanceId") Long bankBalanceId){
        var bankbalance = bankBalanceService.getBankBalance(bankBalanceId);
        return ResponseEntity.ok(bankbalance);
    }

}
