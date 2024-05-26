package com.example.tfgfontanet.domain.servicios;

import com.example.tfgfontanet.common.Constantes;
import com.example.tfgfontanet.ui.errores.CustomError;
import com.example.tfgfontanet.data.dao.DAOClientes;
import com.example.tfgfontanet.data.dao.DAOFavoritos;
import com.example.tfgfontanet.data.dao.DAOUsuario;
import com.example.tfgfontanet.data.modelo.FavoritosEntity;
import com.example.tfgfontanet.data.modelo.UsuarioEntity;
import com.example.tfgfontanet.domain.modelo.Cliente;
import com.example.tfgfontanet.domain.modelo.Favorito;
import com.example.tfgfontanet.domain.modelo.mapper.ClienteEntityMapper;
import com.example.tfgfontanet.domain.modelo.mapper.FavoritoEntityMapper;
import com.example.tfgfontanet.ui.errores.excepciones.CRUDException;
import com.example.tfgfontanet.ui.errores.excepciones.NotFoundException;
import io.vavr.control.Either;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class FavoritosService {

    private final DAOFavoritos daoFavoritos;
    private final DAOUsuario daoUsuario;
    private final DAOClientes daoClientes;
    private final ClienteEntityMapper clienteEntityMapper;
    private final FavoritoEntityMapper favoritoEntityMapper;

    public Either<CustomError, List<Favorito>> getAllByCliente() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UsuarioEntity usuario = daoUsuario.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException(Constantes.USUARIO_NOT_FOUND));
        Cliente cliente = daoClientes.getByUserId(usuario.getUserId()).map(clienteEntityMapper::toCliente).getOrElseThrow(() -> new NotFoundException(Constantes.CLIENTE_NOT_FOUND));

        return daoFavoritos.getAllByCliente(cliente.getClienteId()).map(favoritoEntityList -> {
            List<Favorito> favoritos = new ArrayList<>();
            for (FavoritosEntity favoritoEntity : favoritoEntityList) {
                Favorito favorito = favoritoEntityMapper.toFavorito(favoritoEntity);
                favoritos.add(favorito);
            }
            return favoritos;
        });
    }

    public Boolean addFavorito(int profesionalId) {
        try {
            String name = SecurityContextHolder.getContext().getAuthentication().getName();
            UsuarioEntity usuario = daoUsuario.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException(Constantes.USUARIO_NOT_FOUND));
            Cliente cliente = daoClientes.getByUserId(usuario.getUserId()).map(clienteEntityMapper::toCliente).getOrElseThrow(() -> new NotFoundException(Constantes.CLIENTE_NOT_FOUND));
            daoFavoritos.addFavorito(cliente.getClienteId(), profesionalId);
            return true;
        } catch (CRUDException e) {
            return false;
        }
    }

    public Either<CustomError, Integer> deleteFavorito(int profesionalId) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        UsuarioEntity usuario = daoUsuario.findByUsername(name).orElseThrow(() -> new UsernameNotFoundException(Constantes.USUARIO_NOT_FOUND));
        Cliente cliente = daoClientes.getByUserId(usuario.getUserId()).map(clienteEntityMapper::toCliente).getOrElseThrow(() -> new NotFoundException(Constantes.CLIENTE_NOT_FOUND));
        return daoFavoritos.deleteFavorito(cliente.getClienteId(), profesionalId);
    }
}
