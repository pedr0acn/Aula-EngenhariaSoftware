package controle;

import java.util.List;

import modelo.Filme;
import persistencia.DAOFilme;
import util.Retorno;

public class ControleFilme {
	public Retorno salvarFilme(Filme filme) {
		DAOFilme daofilme = new DAOFilme();
		Retorno retorno = daofilme.inserir(filme);
		System.out.println(retorno.getMensagem());
		return retorno;
	}
	
	public Retorno alterarFilme(Filme filme) {
		DAOFilme daofilme = new DAOFilme();
		Retorno retorno = daofilme.alterar(filme);
		System.out.println(retorno.getMensagem());
		return retorno;
	}
	public Retorno deletarFilme(int id) {
		DAOFilme daofilme = new DAOFilme();
		Retorno retorno = daofilme.deletar(id);
		System.out.println(retorno.getMensagem());
		return retorno;
	}
	public Retorno<List<Filme>> listaFilme(){
		DAOFilme daolista = new DAOFilme();
		Retorno<List<Filme>> retorno = daolista.listar();
		System.out.println(retorno.getMensagem());
		return retorno;
	}
	
	public Retorno<List<Filme>> listaUpdate(int id){
		DAOFilme daolistaup = new DAOFilme();
		@SuppressWarnings("unchecked")
		Retorno<List<Filme>> retorno =  (Retorno<List<Filme>>) daolistaup.exibirUpdate(id);
		System.out.println(retorno.getMensagem());
		return retorno;
	}
	
	/*public Retorno editarFilme() {
		
	}*/
	
}
