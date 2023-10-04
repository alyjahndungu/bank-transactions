package com.camacuchi.banktransactions.models;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class SuperHero  {

       private List<String> cities = List.of(  "Tokyo", "Paris", "London", "Beijing", "New York", "Moscow", "Cairo", "Sydney", "Rome", "Delhi", "Nairobi", "Arusha", "Kinsasha", "Mombasa", "Cape Town");
}
