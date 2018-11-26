package com.steaml.steamlog.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.steaml.steamlog.model.Usuario;
import com.steaml.steamlog.persistencia.UsuarioDAO;

@RestController    
@RequestMapping(path="/usuario/") 
public class UsuarioController {

	private UsuarioDAO uDAO;
	
	@PostMapping(value = "")
	public ResponseEntity<Usuario> cadastrar(@RequestBody Usuario usuario) {
		uDAO = new UsuarioDAO();
		System.out.println("NOME:"+usuario.getNickname());
		usuario = uDAO.cadastrar(usuario);
		return new ResponseEntity<Usuario>(usuario, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "")
	public ResponseEntity<Void> editar(@RequestBody Usuario usuario) {
		uDAO = new UsuarioDAO();
		System.out.println(usuario.getNumJogos());
		uDAO.editar(usuario);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> excluir(@RequestParam long id) {
		uDAO = new UsuarioDAO();
		uDAO.excluir(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	@GetMapping(value = "")
    public ResponseEntity<List<Usuario>> listaTodosUsuarios() {
		uDAO = new UsuarioDAO();
		System.out.println("0");
		List<Usuario> listaUsuarios = uDAO.buscarTodos();		
		return new ResponseEntity<List<Usuario>>(listaUsuarios, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Usuario> buscarPorId(@RequestParam long id) {
		uDAO = new UsuarioDAO();
		System.out.println(id);
		Usuario usuario = uDAO.buscarPorId(id);
		if(usuario!=null) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}		
		return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "{nickname}/{senha}")
	public ResponseEntity<Usuario> buscarPorLoginESenha(@RequestParam String nickname, @RequestParam String senha) {
		uDAO = new UsuarioDAO();
		System.out.println(nickname);
		Usuario usuario = uDAO.buscarPorNicknameESenha(nickname, senha);
		System.out.println(usuario.getNumJogos());
		if(usuario!=null) {
			return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		}		
		return new ResponseEntity<Usuario>(HttpStatus.NOT_FOUND);
	}
	
	@GetMapping(value = "ranking")
	public ResponseEntity<List<Usuario>>buscarRanking(){
		uDAO = new UsuarioDAO();
		System.out.println("Foi");
		ArrayList<Usuario>listRanking = uDAO.RankingJogos();
		return new ResponseEntity<List<Usuario>>(listRanking, HttpStatus.OK);  //Cometario para commit
	}
	
	

}