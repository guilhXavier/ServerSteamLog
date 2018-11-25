package com.steaml.steamlog.persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.steaml.steamlog.model.Imagens;
import com.steaml.steamlog.model.Usuario;

public class UsuarioDAO {

	private ConexaoMysql conexao;

	public UsuarioDAO() {
		super();
		this.conexao = new ConexaoMysql("localhost", "root", "BDGustavo", "projetointegrador");
	}

	// INSERT INTO usuario VALUES(null, 'Rodrigo', 'remor', '123');
	public Usuario cadastrar(Usuario usuario) {
		// ABRIR A CONEX�O COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERA��O QUE DESEJA-SE REALIZAR
		//email,nickname,senha,steamid,foto_perfil,num_jogos,num_conquistas,id_imagem
		String sqlInsert = "INSERT INTO Usuario VALUES(null, ?, ?, ?, ?, null, null, null, ?);";
		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
			// SQL � SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlInsert, Statement.RETURN_GENERATED_KEYS);
			// SUBSTITUIR AS INTERROGA��ES PELOS VALORES QUE EST�O NO OBJETO
			// USU�RIO
			statement.setString(1, usuario.getEmail());
			statement.setString(2, usuario.getNickname());
			statement.setString(3, usuario.getSenha());
			statement.setLong(4, usuario.getSteamid());
			statement.setLong(5,usuario.getImagens().getIdImagem());
			// EXECUTAR A INSTRU��O NO BANCO
			statement.executeUpdate();
			ResultSet rs = statement.getGeneratedKeys();
			if(rs.next()) {
				// PEGA O ID
				usuario.setIdUsuario(rs.getLong(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// FECHAR A CONEX�O COM O BANCO
			this.conexao.fecharConexao();
		}
		return usuario;
	}
	
	// UPDATE usuario SET nome='Rodrigo', login='remor222', senha='1' WHERE id_usuario=1;
	public void editar(Usuario usuario) {
		// ABRIR A CONEXÃO COM O BANCO
		this.conexao.abrirConexao();
		// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
		String sqlUpdate = "UPDATE usuario SET email=?, nickname=?, senha=?, Steamid=?, foto_Perfil=null, num_jogos=? , num_conquistas=null WHERE id_usuario=?;";

		try {
			// DECLARA E INICIALIZA UM STATEMENT, OBJETO USADO PARA PREPARAR O
			// SQL À SER EXECUTADO
			PreparedStatement statement = this.conexao.getConexao().prepareStatement(sqlUpdate);
			// SUBSTITUIR AS INTERROGAÇÕES PELOS VALORES QUE ESTÃO NO OBJETO
			// USUÁRIO
			statement.setString(1, usuario.getEmail());
			statement.setString(2, usuario.getNickname());
			statement.setString(3, usuario.getSenha());
			statement.setLong(4, usuario.getSteamid());
			statement.setInt(5, usuario.getNumJogos());
			statement.setLong(6, usuario.getIdUsuario());
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.conexao.fecharConexao();
		}
	}
	
	// DELETE FROM usuario WHERE id_usuario=3;
		public void excluir(long id) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlDelete = "DELETE FROM Usuario WHERE id_usuario=?;";
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
	
		// SELECT * FROM usuario;
		public List<Usuario> buscarTodos() {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlSelect = "SELECT * FROM Usuario;";
			PreparedStatement statement;
			Usuario usuario = null;
			List<Usuario> listaUsuarios = new ArrayList<Usuario>();
			try {
				statement = this.conexao.getConexao().prepareStatement(sqlSelect);
				ResultSet rs = statement.executeQuery();
				
				while(rs.next()) {
					// Converter um objeto ResultSet em um objeto Usuario
					usuario = new Usuario();
					usuario.setIdUsuario(rs.getLong("id_usuario"));
					usuario.setEmail(rs.getString("email"));
					usuario.setNickname(rs.getString("nickname"));
					usuario.setSteamid(rs.getLong("steamid"));
					usuario.setSenha(rs.getString("senha"));
					listaUsuarios.add(usuario);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return listaUsuarios;
		}
	
		// SELECT * FROM usuario WHERE id_usuario=2;
		public Usuario buscarPorId(long id) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlInsert = "SELECT * FROM Usuario WHERE id_usuario=?;";
			PreparedStatement statement;
			Usuario usuario = null;
			try {
				statement = this.conexao.getConexao().prepareStatement(sqlInsert);
				statement.setLong(1, id);
				ResultSet rs = statement.executeQuery();
				if(rs.next()) {
					// Converter um objeto ResultSet em um objeto Usuario
					usuario = new Usuario();
					usuario.setIdUsuario(rs.getLong("id_usuario"));
					usuario.setEmail(rs.getString("email"));
					usuario.setNickname(rs.getString("nickname"));
					usuario.setSenha(rs.getString("senha"));
					usuario.setSteamid(rs.getLong("steamid"));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return usuario;
		}	

		// SELECT * FROM usuario WHERE login=? AND senha=?;
		public Usuario buscarPorNicknameESenha(String nickname, String senha) {
			// ABRIR A CONEXÃO COM O BANCO
			this.conexao.abrirConexao();
			// SQL COM A OPERAÇÃO QUE DESEJA-SE REALIZAR
			String sqlInsert = "SELECT * FROM Usuario INNER JOIN Imagens ON Usuario.id_imagem = Imagens.id_imagem WHERE nickname=? AND senha=?;";
			PreparedStatement statement;
			Usuario usuario = null;
			Imagens imagens = null;
			try {
				statement = this.conexao.getConexao().prepareStatement(sqlInsert);
				statement.setString(1, nickname);
				statement.setString(2, senha);
				ResultSet rs = statement.executeQuery();
				if(rs.next()) {
					// Converter um objeto ResultSet em um objeto Usuario
					usuario = new Usuario();
					imagens = new Imagens();
					usuario.setIdUsuario(rs.getLong("id_usuario"));
					usuario.setEmail(rs.getString("email"));
					usuario.setNickname(rs.getString("nickname"));
					usuario.setSenha(rs.getString("senha"));
					usuario.setSteamid(rs.getLong("steamid"));
					imagens.setIdImagem(rs.getLong("id_imagem"));
					imagens.setArquivoImagem(rs.getString("arquivo_imagem"));
					imagens.setTipoImagem(rs.getShort("tipo_imagem"));
					usuario.setImagens(imagens);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			return usuario;
		}
		
		public ArrayList<Usuario>RankingJogos(){
			
			this.conexao.abrirConexao();
			String sql = "SELECT * FROM usuario ORDER BY num_jogos DESC";
			PreparedStatement statement;
			Usuario usuario = null;
			ArrayList<Usuario> listaRanking = new ArrayList<Usuario>();
			try {
				statement = this.conexao.getConexao().prepareStatement(sql);
				ResultSet rs = statement.executeQuery();
				while(rs.next()){
					usuario = new Usuario();
					usuario.setIdUsuario(rs.getLong("id_usuario"));
					usuario.setEmail(rs.getString("email"));
					usuario.setNickname(rs.getString("nickname"));
					usuario.setSenha(rs.getString("senha"));
					usuario.setSteamid(rs.getLong("steamid"));
					usuario.setNumJogos(rs.getShort("num_jogos"));
					listaRanking.add(usuario);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				this.conexao.fecharConexao();
			}
			
			return listaRanking;
			
		}

}