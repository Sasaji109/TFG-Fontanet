package com.example.tfgfontanet.ui.errores;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CustomError {
    private String message;
    private LocalDateTime fecha;
}
