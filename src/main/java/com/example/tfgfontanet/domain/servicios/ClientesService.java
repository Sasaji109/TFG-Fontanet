package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.ErrorC;
import com.example.tfgfontanet.common.utiles.Constantes;
import com.example.tfgfontanet.data.dao.DAOClientes;
import com.example.tfgfontanet.data.modelo.ClienteEntity;
import com.example.tfgfontanet.domain.mapper.ClienteEntityMapper;
import com.example.tfgfontanet.domain.modelo.Cliente;
import com.example.tfgfontanet.ui.errores.CustomError;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientesService {

    private final DAOClientes dao;
    private final ClienteEntityMapper clienteEntityMapper;
    private final PasswordEncoder passwordEncoder;

    public Either<ErrorC, List<ClienteEntity>> getAll() {
        return dao.getAll();
    }

    public Either<ErrorC, ClienteEntity> get(int id) {
        return dao.get(id);
    }

    public Either<CustomError, Integer> registroCliente(Cliente cliente) {
        Either<CustomError, Integer> either;

        try {
            ClienteEntity clienteEntity = clienteEntityMapper.toClienteEntity(cliente);
            clienteEntity.getUsuario().setFechaEnvio(LocalDateTime.now());
            clienteEntity.getUsuario().setPassword(passwordEncoder.encode(clienteEntity.getUsuario().getPassword()));
            dao.add(clienteEntity);
            either = Either.right(1);
        } catch (Exception e) {
            either = Either.left(new CustomError(Constantes.ERROR_REGISTRO, LocalDateTime.now()));
        }

        return either;
    }

    public Either<ErrorC, Integer> update(ClienteEntity cliente) {
        return dao.update(cliente);
    }

    public Either<ErrorC, Integer> delete(int id) {
        return dao.delete(id);
    }
}
