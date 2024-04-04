package ru.tinkoff.education.features;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionBody {

    private String message;
    private String code;
}