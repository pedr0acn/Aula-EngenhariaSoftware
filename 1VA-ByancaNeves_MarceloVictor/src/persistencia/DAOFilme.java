package persistencia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import modelo.Filme;
import modelo.Modelo;
import util.Retorno;

public class DAOFilme {
	
	public DAOFilme() {
		try {
		      Class.forName("org.postgresql.Driver");
		      System.out.println("JDBC driver carregado.");
		    }
		    catch (ClassNotFoundException e) {
		      System.out.println(e.toString());
		    }
		  }
	
	public Retorno<?> inserir(Filme filme){
		Retorno<?> retValidos = filme.camposObrigatoriosPreenchidos();
		if(retValidos.isSucesso()==false) {return retValidos;}
		
		String sql = "insert into FILMES(TITULO,GENERO,DURACAO,LANCAMENTO,PRODUTORA)"
				+"values(?,?,?,?,?)";
		
		Retorno<PreparedStatement> retSQL = obterSQLPreparada(sql);
		if(retSQL.isSucesso()==false) {return retSQL;}
		
		Retorno<?> ret2 = preencherSQLPreparada(retSQL.getDado(),filme);
		if(ret2.isSucesso()==false) {return ret2;}
		
		return executarSQLPreparadaAlteracao(retSQL.getDado());
	}
	
	public Retorno<List<Filme>> listar(){
		Retorno<List<Filme>> retornoLista = new Retorno<List<Filme>>(true,"OK");
		
		String sql = "select ID,TITULO,GENERO,DURACAO,LANCAMENTO,PRODUTORA from FILMES";
		
		Retorno<PreparedStatement> retSQL = obterSQLPreparada(sql);
		if(retSQL.isSucesso()==false) {return new Retorno<List<Filme>>(retSQL);}
		
		Retorno<ResultSet> retResultado = executarSQLPreparadaConsulta(retSQL.getDado());
		if(retResultado.isSucesso()==false) {return new Retorno<List<Filme>>(retResultado);}
		
		ResultSet rs = retResultado.getDado();
		List<Filme> lista = new ArrayList<>();
		try {
			while(rs.next()) {
				Filme f = new Filme();
				f.setId(rs.getInt("id"));
				f.setTitulo(rs.getString("titulo"));
				f.setGenero(rs.getString("genero"));
				f.setDuracao(rs.getInt("duracao"));
				f.setLancamento(rs.getInt("lancamento"));
				f.setProdutora(rs.getString("produtora"));
				lista.add(f);
			}
		}catch(SQLException e) {
			retornoLista.setSucesso(false);
			retornoLista.setMensagem(e.getMessage());
		}
		retornoLista.setDado(lista);
		return retornoLista;
	}
	
	private Retorno<Integer> executarSQLPreparadaAlteracao(PreparedStatement pst){
		Retorno<Integer> ret = new Retorno<>(true,"Alteração Executada com Sucesso");
		try {
			int linhasAlteracao = pst.executeUpdate();
			pst.close();
			ret.setDado(linhasAlteracao);
		}catch	(SQLException e) {
			ret = tratarErroSQLPreparadaAlteracao(e);
		}
		return ret;
	}
	private Retorno<ResultSet> executarSQLPreparadaConsulta(PreparedStatement pst){
		Retorno<ResultSet> ret = new Retorno<>(true,"Consulta Executada com Sucesso");
		try {
			ResultSet rs = pst.executeQuery();
			ret.setDado(rs);
		}catch(SQLException e) {
			throw new RuntimeException(e.getMessage());
		}
		return ret;
	}
	private Retorno<Integer> tratarErroSQLPreparadaAlteracao(SQLException e){
		e.printStackTrace();
		Retorno<Integer> ret = new Retorno<>(false,"Erro desconhecido ao executar alteracao: "+e.getMessage());
		ret.setDado(0);
		if(e.getMessage().contains("Filme_nome_unico")) {
			ret.setMensagem("Já existe um filme com esse nome");
		}
		return ret;
	}
	private Retorno preencherSQLPreparada(PreparedStatement pst, Filme filme) {
		Retorno<?> ret = new Retorno<>(true,"OK");
		try {
			pst.setString(1,filme.getTitulo());
			pst.setString(2,filme.getGenero());
			pst.setInt(3,filme.getDuracao());
			pst.setInt(4,filme.getLancamento());
			pst.setString(5,filme.getProdutora());
		}catch (SQLException e) {
			ret.setSucesso(false);
			ret.setMensagem("Erro desconhecido 2: "+e.getMessage());
		}
		return ret;
	}
	private Retorno<PreparedStatement> obterSQLPreparada(String sql){
		Retorno<PreparedStatement> ret = new Retorno<>(true,"OK");
		PreparedStatement pst = null;
		try {
			pst = Conexao.obterConexao().obterSQLPreparada(sql);
			ret.setDado(pst);
		}catch(SQLException e) {
			ret.setSucesso(false);
			if(e.getCause().getClass().getSimpleName().equals("ConnectException")) {
				ret.setMensagem("Erro de Conexão com Banco");
			}else {
				ret.setMensagem("Erro desconhecido 3: "+e.getMessage());
			}
		}
		return ret;
	}
	
	// Alterar
	
	public Retorno<?> alterar(Filme filme){
		Retorno<?> retValidos = filme.camposObrigatoriosPreenchidos();
		if(retValidos.isSucesso()==false) {return retValidos;}
		int id = filme.getId();
		System.out.println(id);
		String titulo1 = filme.getTitulo();
		String genero1 = filme.getGenero();
		int duracao1 = filme.getDuracao();
		int ano1 = filme.getLancamento();
		String produtora1 = filme.getProdutora();
		String sql = "UPDATE FILMES " + 
		"SET TITULO='"+titulo1+
		"',GENERO='"+genero1+
		"',DURACAO='"+duracao1+
		"',LANCAMENTO='"+ano1+
		"',PRODUTORA='"+produtora1+
		"' WHERE ID="+ id;
		System.out.println(sql);
		
		Retorno<PreparedStatement> retSQL = obterSQLPreparada(sql);
		if(retSQL.isSucesso()==false) {return retSQL;}
		
		/*Retorno<?> ret2 = preencherSQLPreparada(retSQL.getDado(),filme);
		if(ret2.isSucesso()==false) {return ret2;}*/
		
		return executarSQLPreparadaAlteracao(retSQL.getDado());
	}
	
	public Retorno<?> deletar(int id){
		String sql = "DELETE FROM FILMES WHERE ID="+id;
		System.out.println(sql);
		
		Retorno<PreparedStatement> retSQL = obterSQLPreparada(sql);
		if(retSQL.isSucesso()==false) {return retSQL;}
		
		/*Retorno<?> ret2 = preencherSQLPreparada(retSQL.getDado(),filme);
		if(ret2.isSucesso()==false) {return ret2;}*/
		
		return executarSQLPreparadaAlteracao(retSQL.getDado());
	}
	
	// Exibir na tela update
	public Retorno<?> exibirUpdate(int id){
Retorno<List<Filme>> retornoLista = new Retorno<List<Filme>>(true,"OK");
		
		String sql = "select ID,TITULO,GENERO,DURACAO,LANCAMENTO,PRODUTORA from FILMES where ID="+id;
		
		Retorno<PreparedStatement> retSQL = obterSQLPreparada(sql);
		if(retSQL.isSucesso()==false) {return new Retorno<List<Filme>>(retSQL);}
		
		Retorno<ResultSet> retResultado = executarSQLPreparadaConsulta(retSQL.getDado());
		if(retResultado.isSucesso()==false) {return new Retorno<List<Filme>>(retResultado);}
		
		ResultSet rs = retResultado.getDado();
		List<Filme> lista = new ArrayList<>();
		try {
			while(rs.next()) {
				Filme f = new Filme();
				f.setId(rs.getInt("id"));
				f.setTitulo(rs.getString("titulo"));
				f.setGenero(rs.getString("genero"));
				f.setDuracao(rs.getInt("duracao"));
				f.setLancamento(rs.getInt("lancamento"));
				f.setProdutora(rs.getString("produtora"));
				lista.add(f);
			}
		}catch(SQLException e) {
			retornoLista.setSucesso(false);
			retornoLista.setMensagem(e.getMessage());
		}
		retornoLista.setDado(lista);
		return retornoLista;
		
	}
	
	
}
	
