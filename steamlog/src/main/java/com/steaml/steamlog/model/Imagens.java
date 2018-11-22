package com.steaml.steamlog.model;

import java.util.ArrayList;

public class Imagens {
	
	private long idImagem;
	private short tipoImagem;
	private String arquivoImagem;
	private ArrayList<Usuario>listusuario;
	private ArrayList<ImagemPostagem>listImagemPostagem;
	
	public Imagens() {}
	
	public Imagens(long idImagem, short tipo_imagem, String arquivo_imagem, ArrayList<ImagemPostagem> listImagemPostagem, ArrayList<Usuario> listusuario) {
		super();
		this.idImagem = idImagem;
		this.tipoImagem = tipo_imagem;
		this.arquivoImagem = arquivo_imagem;
		this.listusuario = listusuario;
		this.listImagemPostagem = listImagemPostagem;
	}
	
	public long getIdImagem() {
		return idImagem;
	}

	public void setIdImagem(long idImagem) {
		this.idImagem = idImagem;
	}

	public short getTipoImagem() {
		return this.tipoImagem ;
	}

	public void setTipoImagem(short tipoImagem) {
		this.tipoImagem = tipoImagem;
	}

	public String getArquivoImagem() {
		return this.arquivoImagem;
	}

	public void setArquivoImagem(String arquivoImagem) {
		this.arquivoImagem = arquivoImagem;
	}

	public ArrayList<Usuario> getListusuario() {
		return listusuario;
	}

	public void setListusuario(ArrayList<Usuario> listusuario) {
		this.listusuario = listusuario;
	}

	public ArrayList<ImagemPostagem> getListImagemPostagem() {
		return listImagemPostagem;
	}

	public void setListImagemPostagem(ArrayList<ImagemPostagem> listImagemPostagem) {
		this.listImagemPostagem = listImagemPostagem;
	}
	
}