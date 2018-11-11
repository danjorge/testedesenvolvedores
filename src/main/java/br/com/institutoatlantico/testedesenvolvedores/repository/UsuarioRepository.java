package br.com.institutoatlantico.testedesenvolvedores.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.institutoatlantico.testedesenvolvedores.model.Usuario;

public interface UsuarioRepository extends PagingAndSortingRepository<Usuario, Long> {

}
