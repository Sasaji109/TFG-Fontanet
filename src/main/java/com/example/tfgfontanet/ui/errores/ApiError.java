package com.example.tfgfontanet.ui.errores;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@ToString
public class ApiError {
    private String mensaje;
}
