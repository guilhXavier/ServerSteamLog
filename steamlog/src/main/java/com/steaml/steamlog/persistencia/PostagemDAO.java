package com.steaml.steamlog.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.steaml.steamlog.model.*;

public class PostagemDAO {

	private ConexaoMysql conexao;

	public PostagemDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "", "projetointegrador");
	}

	// INSERT INTO Postagem VALUES(null, "postagem", data);
	public Postagem inserirPostagem(Postagem postagem) {
		// ABRIR A CONEX�O COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERA��O QUE DESEJA-SE REALIZAR
		String sqlInsert = "INSERT INTO Postagem VALUES(null, ?, ?);";
		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
			// SQL � SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert,
					Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGA��ES PELOS VALORES QUE EST�O NO OBJETO
			// USU�RIO
			statement.setString(1, postagem.getDescPostagem());
			statement.setDate(2, postagem.getDataPostagem());
			// EXECUTAR A INSTRU��O NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if (rs.next()) {
				// PEGA O ID
				postagem.setIdPostagem(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEX�O COM O BANCO
			this.conexao.fecharConexao();
		}
		return postagem;
	};

	public void editarPostagem(Postagem postagem) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlUpdate = "UPDATE Postagem SET descricao_postagem = ? WHERE id_postagem=?;";

		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
			// SQL À SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			// SUBSTITUIR AS INTERROGAÇÕES PELOS VALORES QUE ESTÃO NO OBJETO
			// USUÁRIO
			statement.setString(1, postagem.getDescPostagem());
			statement.setLong(2, postagem.getIdPostagem());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}

	// DELETE FROM Postagem WHERE IdPostagemagem=?;
	public void excluirPostagem(long id) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlDelete = "DELETE FROM Postagem WHERE id_postagem=?;";
		// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
		// SQL À SER EXECUTADO
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

	// SELECT * FROM Postagem;
	public List<Postagem> buscarTodasPostagens() {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlSelect = "SELECT * FROM Postagem;";
		PreparedStatement statement;
		Postagem postagem = null;
		List<Postagem> listaPostagens = new ArrayList<Postagem>();
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlSelect);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				// Converter um objeto ResultSet em um objeto Postagem
				postagem = new Postagem();
				postagem.setIdPostagem(rs.getLong("id_postagemagem"));
				postagem.setDescPostagem(rs.getString("descricao_postagem"));
				postagem.setDataPostagem(rs.getDate("arquivo_imagem"));
				listaPostagens.add(postagem);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return listaPostagens;
	}

	// SELECT * FROM Postagem WHERE IdPostagemagem=?;
	public Postagem buscarPostagemPorId(long id) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlInsert = "SELECT * FROM Postagem WHERE id_postagem=?;";
		PreparedStatement statement;
		Postagem postagem = null;
		try {
			statement = this.conexao.getConexao().prepareStatement(sqlInsert);
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				// Converter um objeto ResultSet em um objeto Usuario
				postagem = new Postagem();
				postagem.setIdPostagem(rs.getLong("id_postagem"));
				postagem.setDescPostagem(rs.getString("descricao_postagem"));
				postagem.setDataPostagem(rs.getDate("data_postagemagem"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
		return postagem;
	}

}