package br.com.institutoatlantico.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.institutoatlantico.repository.UsuarioRepository;
import br.com.institutoatlantico.security.Usuario;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository repo;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = repo.findByLogin(login);
		if (usuario == null) {
			throw new UsernameNotFoundException(login);
		}
		return new Usuario(usuario.getId(), usuario.getLogin(), usuario.getPassword(), usuario.getNome(),
				usuario.getEmail(), usuario.getRoles());
	}
}