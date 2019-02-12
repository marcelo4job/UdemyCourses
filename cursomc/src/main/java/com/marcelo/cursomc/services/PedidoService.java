package com.marcelo.cursomc.services;

import com.marcelo.cursomc.domain.Categoria;
import com.marcelo.cursomc.domain.Pedido;
import com.marcelo.cursomc.repositories.PedidoRepository;
import com.marcelo.cursomc.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository categoriaRepository;

    public Pedido find(Integer id) {
        Optional<Pedido> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "O objeto não encontrado! Id: " + id + ", Tipo:" + Pedido.class.getName()));
    }

}
