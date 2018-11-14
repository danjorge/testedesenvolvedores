package br.com.institutoatlantico.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.institutoatlantico.business.UsuarioBusiness;
import br.com.institutoatlantico.security.Usuario;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioEndpoint {
	
	@Autowired
	private UsuarioBusiness business;
	
	@GetMapping("")
	public ResponseEntity<List<Usuario>> listar() {
		try {
			return ResponseEntity.ok(business.findAll());
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping("")
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) {
		try {
			return ResponseEntity.ok(business.save(usuario));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PutMapping("")
	public ResponseEntity<Usuario> atualizar(@RequestBody Usuario usuario) {
		try {
			return ResponseEntity.ok(business.save(usuario));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deletar(@RequestParam("id") Long id) {
		try {
			business.delete(id);
			return ResponseEntity.ok("Deletado com sucesso");			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().build();
		}
	}

}
