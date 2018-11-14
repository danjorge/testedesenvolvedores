package br.com.institutoatlantico.business;

import java.util.List;
import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import br.com.institutoatlantico.repository.UsuarioRepository;
import br.com.institutoatlantico.security.Usuario;

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
			Usuario user = (Usuario) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			if(StringUtils.hasText(usuario.getPassword()) && usuario.getPassword() != usr.getPassword() && !user.hasRole("ADMIN")) {
				throw new Exception("usuario não tem permissão para atualizar senha");
			}
		}
		
		if(StringUtils.hasText(usuario.getPassword())) {
			BCryptPasswordEncoder enconder = new BCryptPasswordEncoder();
			usuario.setPassword(enconder.encode(usuario.getPassword()));
		}
		return repository.save(usuario);
	}
	
	public void delete(Long id) {
		Usuario usuario = findById(id);
		repository.delete(usuario);
	}

}
