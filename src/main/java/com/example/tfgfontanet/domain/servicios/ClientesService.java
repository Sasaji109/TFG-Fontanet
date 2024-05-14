package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOClientes;
import com.example.tfgfontanet.data.modelo.ClienteEntity;
import com.example.tfgfontanet.domain.modelo.mapper.ClienteEntityMapper;
import com.example.tfgfontanet.domain.modelo.Cliente;
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

    public Either<DAOError, List<ClienteEntity>> getAll() {
        return dao.getAll();
    }

    public Either<DAOError, ClienteEntity> get(int id) {
        return dao.get(id);
    }

    public Boolean registroCliente(Cliente cliente) {
        try {
            ClienteEntity clienteEntity = clienteEntityMapper.toClienteEntity(cliente);
            clienteEntity.getUsuario().setFechaEnvio(LocalDateTime.now());
            clienteEntity.getUsuario().setPassword(passwordEncoder.encode(clienteEntity.getUsuario().getPassword()));
            dao.add(clienteEntity);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public Either<DAOError, Integer> update(ClienteEntity cliente) {
        return dao.update(cliente);
    }

    public Either<DAOError, Integer> delete(int id) {
        return dao.delete(id);
    }
}
