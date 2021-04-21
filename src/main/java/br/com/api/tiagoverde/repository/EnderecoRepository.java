package br.com.api.tiagoverde.repository;

import br.com.api.tiagoverde.model.Endereco;;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Long>
{
    List findByUsuarioId(Long usuarioId);
}
