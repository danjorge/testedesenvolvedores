package br.com.institutoatlantico.endpoint;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.institutoatlantico.DTO.EmailRequestDTO;
import br.com.institutoatlantico.repository.UsuarioRepository;
import br.com.institutoatlantico.security.Usuario;

@RestController
@RequestMapping(value = "/email")
public class EmailEndpoint {
	
	@Autowired
	private RabbitTemplate rabbitTemplate;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
    
    @PostMapping("")
    public ResponseEntity<Object> enviarEmail(@RequestBody EmailRequestDTO email) {
    	
    	try {
    		Usuario usuario = usuarioRepository.findById(email.getUsuario().getId()).get();
    		email.setUsuario(usuario);
    		rabbitTemplate.convertAndSend("email-exchange", "email", email);
			
    		return ResponseEntity.ok("Mensagem enviada");
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Erro ao enviar mensagem");
		}
    	
    }

}
