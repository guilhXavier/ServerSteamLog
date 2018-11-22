package com.steaml.steamlog.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.steaml.steamlog.model.*;
import com.steaml.steamlog.persistencia.*;


@RestController    
@RequestMapping(path = "/comentarios/") 
public class ComentariosController {

	private ComentariosDAO cDAO;
	
	@PostMapping(value = "")
	public ResponseEntity<Comentarios> cadastrar(@RequestBody Comentarios comentarios) {
		cDAO = new ComentariosDAO();
		comentarios = cDAO.inserirComentarios(comentarios);
		return new ResponseEntity<Comentarios>(comentarios, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> excluir(@RequestParam long id) {
		cDAO = new ComentariosDAO();
		cDAO.excluirComentario(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}	
	
	
	@GetMapping(value = "")
    public ResponseEntity<List<Comentarios>> listaTodosUsuarios() {
		System.out.println("Testando");
		cDAO = new ComentariosDAO();
		List<Comentarios> listaComentarios = cDAO.buscarTodosComentarios();
		return new ResponseEntity<List<Comentarios>>(listaComentarios, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Comentarios> buscarPorId(@RequestParam long id) {
		cDAO = new ComentariosDAO();
		Comentarios comentarios = cDAO.buscarComentariosporID(id);
		if(comentarios!=null) {
			return new ResponseEntity<Comentarios>(comentarios, HttpStatus.FOUND);
		}		
		return new ResponseEntity<Comentarios>(HttpStatus.NOT_FOUND);
	}

	
	

}