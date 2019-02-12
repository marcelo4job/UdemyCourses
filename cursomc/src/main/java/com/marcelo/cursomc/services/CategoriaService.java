package com.marcelo.cursomc.services;

import com.marcelo.cursomc.domain.Categoria;
import com.marcelo.cursomc.domain.Cliente;
import com.marcelo.cursomc.repositories.CategoriaRepository;
import com.marcelo.cursomc.services.exception.ObjectNotFoundException;
import com.marcelo.cursomc.dto.CategoriaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class  CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria find(Integer id) {
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException(
                "O objeto não encontrado! Id: " + id + ", Tipo:" + Categoria.class.getName()));
    }

    public Categoria insert(Categoria obj){
        obj.setId(null);
        return categoriaRepository.save(obj);
    }

    public Categoria update(Categoria obj){
        Categoria newObj = find(obj.getId());
        updateData(newObj, obj);
        return categoriaRepository.save(newObj);
    }

    public void delete(Integer id) {
        find(id);
        try {
            categoriaRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e){

            throw new DataIntegrityViolationException("Não é possivel excluir uma categoria que possui produtos");

        }
    }

public List<Categoria> findAll(){

        return categoriaRepository.findAll();
        }

public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        return categoriaRepository.findAll(pageRequest);
        }

public Categoria fromDTO(CategoriaDTO objeto){
        return new Categoria(objeto.getId(), objeto.getNome());
        }

    private void updateData(Categoria newObj, Categoria obj){
        newObj.setNome(obj.getNome() );

    }

        }
