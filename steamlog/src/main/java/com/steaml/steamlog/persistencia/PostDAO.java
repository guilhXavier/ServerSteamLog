package com.steaml.steamlog.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.steaml.steamlog.model.Post;
import com.steaml.steamlog.model.Usuario;


public class PostDAO {
	
	private ConexaoMysql conexao; //comentario pra commit

	public PostDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "BDGustavo", "projetointegrador");
	}
	
	public Post inserirPost(Post post) {
		// ABRIR A CONEX�O COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERA��O QUE DESEJA-SE REALIZAR
		String sqlInsert = "INSERT INTO Post VALUES(null, ?, ?);";
		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
			// SQL � SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGA��ES PELOS VALORES QUE EST�O NO OBJETO
			// USU�RIO
			statement.setLong(1, post.getUsuario().getIdUsuario());
			statement.setLong(2, post.getPostagem().getIdPostagem());
			// EXECUTAR A INSTRU��O NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				// PEGA O ID
				post.setIdUsuarioPostagem((rs.getLong(1)));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEX�O COM O BANCO
			this.conexao.fecharConexao();
		}
		return post;
	};
	
	public Post buscarPost(long id){
		
		this.conexao.abrirConexao();
		
		String sql = "SELECT * FROM (post) INNER JOIN usuario ON usuario.id_usuario = post.id_usuario WHERE post.id_postagem= ?;";
		Usuario usuario = null;
		Post post = null;
		try {
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sql);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if(rs.next()){
				
				usuario = new Usuario();
				post = new Post();
				usuario.setIdUsuario(rs.getLong("id_usuario"));
				usuario.setNickname(rs.getString("nickname"));
				post.setUsuario(usuario);
				post.setPostagem(null);
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return post;
	}
}
