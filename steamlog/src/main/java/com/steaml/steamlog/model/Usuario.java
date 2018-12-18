package com.steaml.steamlog.model;

import java.util.ArrayList;

public class Usuario {  //Comentario para commit
	private long idUsuario;
	private String email;
	private String nickname;
	private String senha;
	private long steamid;
	private String refFotoPerfil;
	private int numJogos;
	private ArrayList<Post>listPost;

	
	
	public Usuario() {
		
	}
	
	public Usuario(long idUsuario, String email, String nickname, String senha, int steamid, String refFotoPerfil, int numJogos, ArrayList<Post> listPost) {
		super();
		this.idUsuario = idUsuario;
		this.email = email;
		this.nickname = nickname;
		this.senha = senha;
		this.steamid = steamid;
		this.refFotoPerfil = refFotoPerfil;
		this.numJogos = numJogos;
		this.listPost = listPost;
	}

	
	public long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public long getSteamid() {
		return steamid;
	}

	public void setSteamid(long steamid) {
		this.steamid = steamid;
	}

	public String getRefFotoPerfil() {
		return refFotoPerfil;
	}

	public void setRefFotoPerfil(String refFotoPerfil) {
		this.refFotoPerfil = refFotoPerfil;
	}

	public int getNumJogos() {
		return numJogos;
	}

	public void setNumJogos(int numJogos) {
		this.numJogos = numJogos;
	}

	public ArrayList<Post> getListPost() {
		return listPost;
	}

	public void setListPost(ArrayList<Post> listPost) {
		this.listPost = listPost;
	}


	

}
