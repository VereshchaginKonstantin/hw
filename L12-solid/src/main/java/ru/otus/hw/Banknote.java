package ru.otus.hw;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Banknote {
    private final Long count;
    private final DenominationType type;
}
