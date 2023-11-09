package com.example;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Component
@Data
public class Counter {
    @EqualsAndHashCode.Exclude
    private Integer counter;
    private String name;

    public void inc(){
        if (counter != Integer.MAX_VALUE)
            counter++;
    }

}
