package br.com.api.tiagoverde.controller;

import java.util.List;
import java.util.Optional;
import br.com.api.tiagoverde.exception.NotFoundException;
import br.com.api.tiagoverde.model.Usuario;
import br.com.api.tiagoverde.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/usuarios")
    public List getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @GetMapping("/usuarios/{id}")
    public Optional<Usuario> getUsuarioByID(@PathVariable Long id) {
        try{
            return usuarioRepository.findById(id);
        }
        catch(NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "400");
        }
    }

    @PostMapping("/usuarios")
    public ResponseEntity<Usuario> createUsuario(@RequestBody Usuario usuario) {
        try {
            Usuario user = usuarioRepository.save(usuario);
            return new ResponseEntity<>(user, HttpStatus.CREATED);
        }
        catch (NotFoundException exception){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "400");
        }
    }

}