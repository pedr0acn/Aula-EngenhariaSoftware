package modelo;

import java.util.ArrayList;
import java.util.List;


public class Filme extends Modelo{
	private Integer id;
	private String titulo;
	private String genero;
	private Integer duracao;
	private Integer lancamento;
	private String produtora;
	
	public Filme(Integer id, String titulo, String genero, Integer duracao, Integer lancamento, String produtora) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.genero = genero;
		this.duracao = duracao;
		this.lancamento = lancamento;
		this.produtora = produtora;
	}
	
	public Filme(String titulo, String genero, Integer duracao, Integer lancamento, String produtora) {
		super();
		this.titulo = titulo;
		this.genero = genero;
		this.duracao = duracao;
		this.lancamento = lancamento;
		this.produtora = produtora;
	}
	
	public Filme() {}
	@Override
	public String toString() {
		return "id ="+ id +"titulo ="+ titulo +"genero ="+ genero +"duracao ="+ duracao +"lancamento ="+ lancamento +"produtora ="+ produtora;
	}
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Integer getDuracao() {
		return duracao;
	}
	public void setDuracao(Integer duracao) {
		this.duracao = duracao;
	}
	public Integer getLancamento() {
		return lancamento;
	}
	public void setLancamento(Integer lancamento) {
		this.lancamento = lancamento;
	}
	public String getProdutora() {
		return produtora;
	}
	public void setProdutora(String produtora) {
		this.produtora = produtora;
	}
	public List<String> getCamposObrigatorios(){
		List<String> lista = new ArrayList<>();
		lista.add("titulo");
		lista.add("genero");
		lista.add("duracao");
		lista.add("lancamento");
		lista.add("produtora");
		return lista;
	}
	
	public Object getCamposValor(String campoNome) {
		if(campoNome.equalsIgnoreCase("titulo")) {
			return this.getTitulo();
		}else if(campoNome.equalsIgnoreCase("genero")) {
			return this.getGenero();
		}else if(campoNome.equalsIgnoreCase("duracao")) {
			return this.getDuracao();
		}else if(campoNome.equalsIgnoreCase("lancamento")) {
			return this.getLancamento();
		}else if(campoNome.equalsIgnoreCase("produtora")) {
			return this.getProdutora();
		}
		throw new RuntimeException("Campo"+campoNome+" não existe na tabela");
	}
	

	
	
}
