package com.example.tfgfontanet.domain.modelo.mapper;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.data.modelo.ClienteEntity;
import com.example.tfgfontanet.domain.modelo.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = Constantes.SPRING)
public interface ClienteEntityMapper {
    ClienteEntity toClienteEntity(Cliente cliente);
    Cliente toCliente(ClienteEntity clienteEntity);
}
