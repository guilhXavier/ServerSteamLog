package com.steaml.steamlog.model;

import java.util.ArrayList;
import java.sql.Date;

public class Postagem {
	
	private long idPostagem;
	private String tituloPostagem;
	private String descPostagem;
	private Date dataPostagem;
	private String refFotoPostagem;
	private ArrayList<Comentarios>listComentarios;
	private ArrayList<Post>listPost;
	
	public Postagem() {}

	public Postagem(long idPostagem,String tituloPostagem, String descPostagem, Date dataPostagem, String refFotoPostagem,
			ArrayList<Comentarios> listComentarios, ArrayList<Post> listPost) {
		super();
		this.tituloPostagem = tituloPostagem;
		this.idPostagem = idPostagem;
		this.descPostagem = descPostagem;
		this.dataPostagem = dataPostagem;
		this.refFotoPostagem = refFotoPostagem;
		this.listComentarios = listComentarios;
		this.listPost = listPost;
	}
	
	

	public String getTituloPostagem() {
		return tituloPostagem;
	}

	public void setTituloPostagem(String tituloPostagem) {
		this.tituloPostagem = tituloPostagem;
	}

	public long getIdPostagem() {
		return idPostagem;
	}

	public void setIdPostagem(long idPostagem) {
		this.idPostagem = idPostagem;
	}

	public String getDescPostagem() {
		return descPostagem;
	}

	public void setDescPostagem(String descPostagem) {
		this.descPostagem = descPostagem;
	}

	public Date getDataPostagem() {
		return dataPostagem;
	}

	public void setDataPostagem(Date dataPostagem) {
		this.dataPostagem = dataPostagem;
	}

	public String getRefFotoPostagem() { return refFotoPostagem; }

	public void setRefFotoPostagem(String refFotoPostagem) { this.refFotoPostagem = refFotoPostagem; }

	public ArrayList<Comentarios> getListComentarios() {
		return listComentarios;
	}

	public void setListComentarios(ArrayList<Comentarios> listComentarios) {
		this.listComentarios = listComentarios;
	}

	public ArrayList<Post> getListPost() {
		return listPost;
	}

	public void setListPost(ArrayList<Post> listPost) {
		this.listPost = listPost;
	}
	
	

}

