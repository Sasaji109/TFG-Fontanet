package com.example.tfgfontanet.domain.mapper;

import com.example.tfgfontanet.data.modelo.UsuarioEntity;
import com.example.tfgfontanet.domain.modelo.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioEntityMapper {
    UsuarioEntity toUsuarioEntity(Usuario usuario);
    Usuario toUsuario(UsuarioEntity usuarioEntity);
}
