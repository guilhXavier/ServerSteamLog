package com.steaml.steamlog.controller;
import java.util.List;


import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.steaml.steamlog.model.Imagens;
import com.steaml.steamlog.persistencia.ImagensDAO;


@Controller    
@RequestMapping(path="/imagens/") 
public class ImagensController {
	
	private ImagensDAO iDAO;
	
	@RequestMapping(value = "", method = RequestMethod.POST)
	public ResponseEntity<Imagens> inserirImagem(@RequestBody Imagens imagem) {
		iDAO = new ImagensDAO();
		imagem = iDAO.inserirImagem(imagem);
		return new ResponseEntity<Imagens>(imagem, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.POST)
	public ResponseEntity<Void> excluirImagem(@RequestParam long id) {
		iDAO = new ImagensDAO();
		iDAO.excluirImagem(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@RequestMapping(value = "", method = RequestMethod.GET)
    public ResponseEntity<List<Imagens>> listaTodasImagens() {
		iDAO = new ImagensDAO();
		List<Imagens> listaImagens = iDAO.buscarTodasImagens();		
		return new ResponseEntity<List<Imagens>>(listaImagens, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public ResponseEntity<Imagens> buscarPorId(@RequestParam long id) {
		iDAO = new ImagensDAO();
		Imagens imagens = iDAO.buscarImagemPorId(id);
		if(imagens!=null) {
			return new ResponseEntity<Imagens>(imagens, HttpStatus.FOUND);
		}		
		return new ResponseEntity<Imagens>(HttpStatus.NOT_FOUND);
	}
		
	
}
