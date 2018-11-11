package br.com.institutoatlantico.testedesenvolvedores.business;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.institutoatlantico.testedesenvolvedores.model.Usuario;
import br.com.institutoatlantico.testedesenvolvedores.repository.UsuarioRepository;

@Service
public class UsuarioBusiness {
	
	@Autowired
	private UsuarioRepository repository;
	
	public List<Usuario> findAll() {
		return (List<Usuario>) repository.findAll();
	}
	
	public Usuario findById(Long id) {
		Optional<Usuario> usuario = repository.findById(id);
		if(!usuario.isPresent()) {
			throw new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName(),
					"Objeto não encontrado! Id: " + id + ", Tipo: " + Usuario.class.getName());
		}
		return usuario.get();
	}
	
	public Usuario save(Usuario usuario) throws Exception {
		if (usuario.getId() != null) {
			Usuario usr = findById(usuario.getId());
			if(usr.getPassword() != usuario.getPassword() && !usuario.isAdmin()) {
				throw new Exception("usuario não tem permissão para atualizar senha");
			}
		}
		return repository.save(usuario);
	}
	
	public void delete(Long id) {
		Usuario usuario = findById(id);
		repository.delete(usuario);
	}

}
