package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.DAOError;
import com.example.tfgfontanet.data.dao.DAOClientes;
import com.example.tfgfontanet.data.modelo.ClienteEntity;
import com.example.tfgfontanet.data.modelo.FavoritosEntity;
import com.example.tfgfontanet.domain.modelo.mapper.ClienteEntityMapper;
import com.example.tfgfontanet.domain.modelo.Cliente;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientesService {

    private final DAOClientes dao;
    private final ClienteEntityMapper clienteEntityMapper;
    private final PasswordEncoder passwordEncoder;

    public Either<DAOError, List<Cliente>> getAll() {
        return dao.getAll().map(clienteEntityList -> {
            List<Cliente> clientes = new ArrayList<>();
            for (ClienteEntity clienteEntity : clienteEntityList) {
                Cliente cliente = clienteEntityMapper.toCliente(clienteEntity);
                clientes.add(cliente);
            }
            return clientes;
        });
    }

    public Either<DAOError, Cliente> get(int id) {
        return dao.get(id).map(clienteEntityMapper::toCliente);
    }

    public Boolean registroCliente(Cliente cliente) {
        try {
            ClienteEntity clienteEntity = clienteEntityMapper.toClienteEntity(cliente);
            clienteEntity.getUsuario().setFechaEnvio(LocalDateTime.now());
            clienteEntity.getUsuario().setPassword(passwordEncoder.encode(clienteEntity.getUsuario().getPassword()));
            dao.add(clienteEntity);
            return true;
        } catch (CRUDException e) {
            return false;
        }
    }

    public Either<DAOError, Integer> update(Cliente cliente) {
        ClienteEntity clienteEntity = clienteEntityMapper.toClienteEntity(cliente);
        List<FavoritosEntity> favoritosEntities = new ArrayList<>();
        clienteEntity.setProfesionalesFavoritos(favoritosEntities);
        return dao.update(clienteEntity);
    }

    public Either<DAOError, Integer> delete(int id) {
        return dao.delete(id);
    }
}
