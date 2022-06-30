package ru.otus.hw;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public record Banknote(Long count, DenominationType type) {
}
