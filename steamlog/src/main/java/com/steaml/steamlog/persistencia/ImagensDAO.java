package com.steaml.steamlog.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.steaml.steamlog.model.Imagens;

public class ImagensDAO {

	private ConexaoMysql conexao;

	public ImagensDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "BDGustavo", "projetointegrador");
	}

	// INSERT INTO Imagens VALUES(null, 1, 'endereco');
	public Imagens inserirImagem(Imagens imagem) {
		// ABRIR A CONEX�O COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERA��O QUE DESEJA-SE REALIZAR
		//email,nickname,senha,steamid,foto_perfil,num_jogos,num_conquistas,id_imagem
		String sqlInsert = "INSERT INTO Imagens VALUES(null, ?, ?);";
		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
			// SQL � SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGA��ES PELOS VALORES QUE EST�O NO OBJETO
			// USU�RIO
			statement.setShort(1,imagem.getTipoImagem());
			statement.setString(2, imagem.getArquivoImagem());
			// EXECUTAR A INSTRU��O NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				// PEGA O ID
				imagem.setIdImagem(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEX�O COM O BANCO
			this.conexao.fecharConexao();
		}
		return imagem;
	}
	
	
	// DELETE FROM Imagens WHERE id_imagens=?;
		public void excluirImagem(long id) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlDelete = "DELETE FROM Imagens WHERE id_imagem=?;";
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
	
		// SELECT * FROM Imagens;
		public List<Imagens> buscarTodasImagens() {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlSelect = "SELECT * FROM Imagens;";
			PreparedStatement statement;
			Imagens imagens = null;
			List<Imagens> listaImagens = new ArrayList<Imagens>();
			try {
				statement = this.conexao.getConexao().prepareStatement(sqlSelect);
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()) {
					// Converter um objeto ResultSet em um objeto Imagens
					imagens = new Imagens();
					imagens.setIdImagem(rs.getLong("id_imagem"));
					imagens.setTipoImagem(rs.getShort("tipo_imagem"));
					imagens.setArquivoImagem(rs.getString("arquivo_imagem"));
					listaImagens.add(imagens);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return listaImagens;
		}
	
		// SELECT * FROM Imagens WHERE id_imagem=?;
		public Imagens buscarImagemPorId(long id) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlInsert = "SELECT * FROM Imagens WHERE id_imagem=?;";
			PreparedStatement statement;
			Imagens imagens = null;
			try {
				statement = this.conexao.getConexao().prepareStatement(sqlInsert);
				ResultSet rs = statement.executeQuery();
				statement.setLong(1, id);
				if(rs.next()) {
					// Converter um objeto ResultSet em um objeto Usuario
					imagens = new Imagens();
					imagens.setIdImagem(rs.getLong("id_imagem"));;
					imagens.setTipoImagem(rs.getShort("tipo_imagem"));
					imagens.setArquivoImagem(rs.getString("arquivo_imagem"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return imagens;
		}	

		

}
