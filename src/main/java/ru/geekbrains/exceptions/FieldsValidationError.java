package ru.geekbrains.exceptions;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class FieldsValidationError {
    private List<String> errorFieldsMessage;

    public FieldsValidationError(List<String> errorFieldsMessage) {
        this.errorFieldsMessage = errorFieldsMessage;
    }
}
