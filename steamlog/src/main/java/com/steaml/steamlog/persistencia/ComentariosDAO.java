package com.steaml.steamlog.persistencia;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.steaml.steamlog.model.Comentarios;

public class ComentariosDAO {
	private ConexaoMysql conexao;

	public ComentariosDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", " ", "projetointegrador");
	}

	// INSERT INTO comentarios VALUES(null,corpo_comentario,data_comentario);
	public Comentarios inserirComentarios(Comentarios comentarios) {
		// ABRIR A CONEXAO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERACAO QUE DESEJA-SE REALIZAR
		String sqlInsert = "INSERT INTO Comentarios VALUES(null, ?, ?, null);";
		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
			// SQL A SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGACOES PELOS VALORES QUE ESTAO NO OBJETO
			// USUARIO
			statement.setString(1, comentarios.getCorpoComentario());
			statement.setDate(2,  comentarios.getDataComentario());
			// EXECUTAR A INSTRUCAO NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				// PEGA O ID
				comentarios.setIdComentario(rs.getLong(1));;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEXAO COM O BANCO
			this.conexao.fecharConexao();
		}
		return comentarios;
	}
	
	
	// DELETE FROM comentarios WHERE id_comentarios=?;
		public void excluirComentario(long id) {
			// ABRIR A CONEX�O COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERA��O QUE DESEJA-SE REALIZAR
			String sqlDelete = "DELETE FROM comentarios WHERE id_comentario=?;";
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
			// SQL � SER EXECUTADO
			try {
				PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlDelete);
				statement.setLong(1, id);

				statement.executeUpdate();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
		}
	
		// SELECT * FROM Comentarios;
		public List<Comentarios> buscarTodosComentarios() {
			// ABRIR A CONEX�O COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERA��O QUE DESEJA-SE REALIZAR
			String sqlSelect = "SELECT * FROM comentarios;";
			PreparedStatement statement;
			Comentarios comentarios = null;
			List<Comentarios> listaComentarios = new ArrayList<Comentarios>();
			try {
				statement = this.conexao.getConexao().prepareStatement(sqlSelect);
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()) {
					// Converter um objeto ResultSet em um objeto Comentarios
					comentarios = new Comentarios();
					comentarios.setIdComentario(rs.getLong("id_comentario"));
					comentarios.setCorpoComentario(rs.getString("corpo_comentario"));
					comentarios.setDataComentario(rs.getDate("data_comentario"));
					listaComentarios.add(comentarios);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return listaComentarios;
		}
	
		// SELECT * FROM comentarios WHERE id_comentarios=?;
		public Comentarios  buscarComentariosporID(long id) {
			// ABRIR A CONEX�O COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERA��O QUE DESEJA-SE REALIZAR
			String sqlInsert = "SELECT * FROM comentarios WHERE id_comentario=?;";
			PreparedStatement statement;
			Comentarios comentarios = null;
			try {
				statement = this.conexao.getConexao().prepareStatement(sqlInsert);
				statement.setLong(1, id);
				ResultSet rs = statement.executeQuery();
				if(rs.next()) {
					// Converter um objeto ResultSet em um objeto Comentarios
					comentarios = new Comentarios();
					comentarios.setIdComentario(rs.getLong("id_comentario"));;
					comentarios.setCorpoComentario(rs.getString("corpo_comentario"));
					comentarios.setDataComentario(rs.getDate("data_comentario"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return comentarios;
		}	
		
}

