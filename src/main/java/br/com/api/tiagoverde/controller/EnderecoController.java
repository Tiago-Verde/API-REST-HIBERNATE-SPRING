package br.com.api.tiagoverde.controller;

import br.com.api.tiagoverde.exception.NotFoundException;
import br.com.api.tiagoverde.model.Endereco;
import br.com.api.tiagoverde.repository.EnderecoRepository;
import br.com.api.tiagoverde.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/api")
public class EnderecoController {
    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuario/{usuarioId}/enderecos")
    public List getEnderecoByUsuarioId(@PathVariable Long usuarioId) {
        if(!usuarioRepository.existsById(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possivel encontrar a informação solicitada");
        }
        return enderecoRepository.findByUsuarioId(usuarioId);
    }


    @PostMapping("/usuario/{usuarioId}/enderecos")
    public ResponseEntity<Endereco> addEndereco(@PathVariable Long usuarioId,
                                                @RequestBody Endereco endereco) {
        return usuarioRepository.findById(usuarioId)
                .map(usuario -> {
                    endereco.setUsuario(usuario);
                    enderecoRepository.save(endereco);
                    return new ResponseEntity<>(endereco, HttpStatus.CREATED);
                }).orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "Usuário não existe na base, endereço não será cadastrado"));
    }

}