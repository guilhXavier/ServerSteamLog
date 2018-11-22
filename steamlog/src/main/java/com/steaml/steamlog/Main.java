package com.steaml.steamlog;

import java.sql.Date;

import com.steaml.steamlog.model.*;
import com.steaml.steamlog.persistencia.*;

public class Main {
	
	public static void main(String[]args) {
		
//		PostagemDAO pDAO = new PostagemDAO();
//		Postagem postagem = new Postagem();
//		String s = "1999-3-9";
//		Date data = Date.valueOf(s);
//		postagem.setDescPostagem("john johnes");
//		postagem.setDataPostagem(data);
//		pDAO.inserirPostagem(postagem);
		
//		ImagensDAO iDAO = new ImagensDAO();
//		Imagens imagens = new Imagens();
//		
//		imagens.setTipoImagem((short)2);
//		imagens.setArquivoImagem("busato");
//		iDAO.inserirImagem(imagens);
		
		UsuarioDAO uDAO = new UsuarioDAO();
		Usuario usuario = new Usuario();
		
//		usuario.setEmail("sadasda");
//		usuario.setNickname("sdasdasae");
//		usuario.setSenha("12345");
//		usuario.setSteamid(31231231);
//		usuario.setFotoPerfil("FOTO");
//		uDAO.cadastrar(usuario);
		
		
		ComentariosDAO cDAO = new ComentariosDAO();
		Comentarios comentarios = new Comentarios();
		
		comentarios.setCorpoComentario("comentario comentado");
		comentarios.setDataComentario(Date.valueOf("2018-03-09"));
		cDAO.inserirComentarios(comentarios);
		
		System.out.println("sera que comitou");
		
		
	}
	
}
