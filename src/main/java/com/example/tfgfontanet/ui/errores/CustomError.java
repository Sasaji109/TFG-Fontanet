package com.example.tfgfontanet.ui.errores;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class CustomError extends Throwable {
    private int nError;
    private String message;
    private LocalDate date;
}
