package com.example.tfgfontanet.data.modelo;

import lombok.*;
import org.bson.types.ObjectId;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class ProfesionalMongo {
    private ObjectId _id;
    private Integer profesionalId;
    List<ValoracionMongo> valoraciones;
}
