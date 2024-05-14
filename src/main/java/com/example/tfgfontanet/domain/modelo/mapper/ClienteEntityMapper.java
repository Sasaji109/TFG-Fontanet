package com.example.tfgfontanet.domain.modelo.mapper;

import com.example.tfgfontanet.data.modelo.ClienteEntity;
import com.example.tfgfontanet.domain.modelo.Cliente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ClienteEntityMapper {
    ClienteEntity toClienteEntity(Cliente cliente);
    Cliente toCliente(ClienteEntity clienteEntity);
}
