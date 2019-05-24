package visao;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.swing.ImageIcon;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import controle.ControleFilme;
import modelo.Filme;
import util.Retorno;

public class ReadServlet extends HttpServlet {
	int i;
	Image imagem;
	private String keyword = "";
	
	public void doGet(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException{
		sendPageHeader(response);
		sendSearchForm(response);
		sendPageFooter(response);	
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse 
			response) throws ServletException, IOException {
		keyword = request.getParameter("keyword");
		sendPageHeader(response);
	    sendSearchForm(response);
	    try {
			sendSearchResult(response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    sendPageFooter(response);
	}
	
	void sendSearchResult(HttpServletResponse 
			response)throws IOException, SQLException {
		PrintWriter out = response.getWriter();
		
		
		
		ControleFilme ctrlListar = new ControleFilme();
		Retorno<List<Filme>> listaFilme = ctrlListar.listaFilme();
		List<Filme> listaFilmePrint = new ArrayList<>();
		listaFilmePrint = listaFilme.getDado();
		if(listaFilmePrint.isEmpty()) {
			out.println("Nenhum filme encontrado");
		}else {
			out.println("<TABLE border=1 cellspacing=0 cellpadding=5 bgcolor=\"#FFFFFF\">");
	        out.println("<TR>");
	        out.println("<TH>Titulo</TH>");
	        out.println("<TH>Genero</TH>");
	        out.println("<TH>Duração</TH>");
	        out.println("<TH>Lançamento</TH>");
	        out.println("<TH>Produtora</TH>");
	        out.println("<TH></TH>");
	        out.println("<TH></TH>");
	        out.println("</TR>");
			for(Filme f1 : listaFilmePrint ) {
				Integer id = f1.getId();
				String tituloUp = f1.getTitulo();
				Integer duracaoUP = f1.getDuracao();
				out.println("<TR>");
				out.println("<TD>" + f1.getTitulo() + "</TD>");
				out.println("<TD>		"+ f1.getGenero() + "</TD>");
				out.println("<TD>				"+ f1.getDuracao() + "</TD>");
				out.println("<TD>						"+ f1.getLancamento() + "</TD>");
				out.println("<TD>								"+ f1.getProdutora() + "</TD>");
				out.println("<TD><A HREF=confirm?id=" + id +
			            ">Apagar</A></TD>");
				out.println("<TD><A HREF=update?id=" + id +
			            ">Atualizar</A></TD>");
			}
		}
			
		}
		
		
	
	
	private void sendPageHeader(HttpServletResponse response) 
			throws ServletException, IOException{
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String file = "https://i.imgur.com/wrHPesB.jpg";
		out.println("<HTML>");
	    out.println("<HEAD>");
	    out.println("<TITLE>Apresentando registros selecionados</TITLE>");
	    out.println("<style>body {background-image:url(\"https://images.alphacoders.com/100/thumb-1920-1005214.jpg\"); background-repeat: no-repeat; background-size: 100% 100%; border: 3;} #img {margin-left: center ;}</style>");
	    out.println("</HEAD>");
	    
	    out.println("<CENTER>");
		
	}
	
	private void sendPageFooter(HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
	    out.println("</CENTER>");
	    out.println("</BODY>");
	    out.println("</HTML>");
	}
	
	private void sendSearchForm(HttpServletResponse response) 
			throws IOException{
		PrintWriter out = response.getWriter();
	    out.println("<BR> <H2 style= color:#FFFFFF> Formulário de Busca</H2>");
	    out.println("<BR><H3 style= color:#FFFFFF>Ao clicar em pesquisar será mostrado todos os filmes cadastrados</H3>");
	    out.println("<BR>");
	    out.println("<BR><FORM METHOD=POST>");
	    out.println("<INPUT TYPE=SUBMIT VALUE='PESQUISAR'>");
	    out.println("<input type=\"button\" onclick=\"javascript: location.href='create';\" value=\"CADASTRAR\" /></TD>"); 
	    
	   // out.println("<A  HREF=create>Cadastrar</A></TD>");
	    
	    out.println("</FORM>");
	    out.println("<BR>");
	    out.println("<BR>");
		
	}
	
}
