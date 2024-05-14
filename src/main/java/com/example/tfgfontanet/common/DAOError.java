package com.example.tfgfontanet.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;

@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
public class DAOError extends Throwable {
    private int nError;
    private String message;
    private LocalDate date;
}
