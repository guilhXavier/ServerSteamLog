package com.steaml.steamlog.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.steaml.steamlog.model.Post;
import com.steaml.steamlog.model.Postagem;
import com.steaml.steamlog.persistencia.PostDAO;

@RestController    
@RequestMapping(path = "/post/") 
public class PostController {
	
private PostDAO poDAO;
	
	@PostMapping(value = "")
	public ResponseEntity<Post> cadastrar(@RequestBody Post post) {
		poDAO = new PostDAO();
		post = poDAO.inserirPost(post);
		return new ResponseEntity<Post>(post, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<Post> buscarUsuarioPost(@RequestParam long id){
		poDAO = new PostDAO();
		Post post;
		System.out.println("Teste");
		post = poDAO.buscarPost(id);
		return new ResponseEntity<Post>(post, HttpStatus.CREATED);
	}
	
}
