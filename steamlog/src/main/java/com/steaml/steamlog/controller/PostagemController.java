package com.steaml.steamlog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.steaml.steamlog.model.Postagem;
import com.steaml.steamlog.persistencia.PostagemDAO;;


@RestController    
@RequestMapping(path = "/postagens/") 
public class PostagemController {

	private PostagemDAO poDAO;
	
	@PostMapping(value = "")
	public ResponseEntity<Postagem> cadastrar(@RequestBody Postagem postagem) {
		poDAO = new PostagemDAO();
		postagem = poDAO.inserirPostagem(postagem);
		return new ResponseEntity<Postagem>(postagem, HttpStatus.CREATED);
	}
	
	@PutMapping(value = "")
	public ResponseEntity<Void> editar(@RequestBody Postagem postagem) {
		poDAO = new PostagemDAO();
		poDAO.editarPostagem(postagem);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> excluir(@RequestParam long id) {
		poDAO = new PostagemDAO();
		poDAO.excluirPostagem(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	
	@GetMapping(value = "")
    public ResponseEntity<List<Postagem>> listaTodosUsuarios() {
		System.out.println("Testando");
		poDAO = new PostagemDAO();
		List<Postagem> listaPostagem = poDAO.buscarTodasPostagens();
		return new ResponseEntity<List<Postagem>>(listaPostagem, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Postagem> buscarPorId(@RequestParam long id) {
		poDAO = new PostagemDAO();
		Postagem postagem = poDAO.buscarPostagemPorId(id);
		if(postagem!=null) {
			return new ResponseEntity<Postagem>(postagem, HttpStatus.OK);
		}		
		return new ResponseEntity<Postagem>(HttpStatus.NOT_FOUND);
	}

	
	

}