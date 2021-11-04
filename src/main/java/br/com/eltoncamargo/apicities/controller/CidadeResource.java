package br.com.eltoncamargo.apicities.controller;

import br.com.eltoncamargo.apicities.model.Cidade;
import br.com.eltoncamargo.apicities.repositories.CidadeRepository;
import br.com.eltoncamargo.apicities.repositories.EstadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cidades")
public class CidadeResource {

    @Autowired
    private CidadeRepository repository;

    @GetMapping
    public Page<Cidade> getAll(Pageable page){
        return repository.findAll(page);
    }

    @GetMapping("/by-estado/{idEstado}")
    public List<Cidade> getByIdEstado(@PathVariable Long idEstado){

        List<Cidade> listaCidades = repository.findByIdEstado(idEstado);;

        return listaCidades;

    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable Long id){


        Optional<Cidade> optional = repository.findById(id);

        if(optional.isPresent()){
            return ResponseEntity.ok().body(optional.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    }


}
