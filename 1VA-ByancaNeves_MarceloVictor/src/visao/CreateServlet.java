package visao;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;
import persistencia.Conexao;

import modelo.Filme;
import controle.ControleFilme;
import persistencia.DAOFilme;

public class CreateServlet extends HttpServlet {
	String TituloFilme,GeneroFilme,ProdutoraFilme;
	int minutos,ano;
	
	
	public void doGet(HttpServletRequest request, HttpServletResponse
			response)throws ServletException, IOException{
		
		sendPageHeader(response);
		sendIncludeForm(request, response);
		sendPageFooter(response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse
			response)throws ServletException, IOException {
		
		sendPageHeader(response);
		createRecord(request, response);
	    sendPageFooter(response);
	}
	
	private void sendPageHeader(HttpServletResponse response)
			throws ServletException, IOException{
		
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<HTML>");
	    out.println("<HEAD>");
	    out.println("<TITLE>Biblioteca de Filmes</TITLE>");
	    out.println("<style>body {background-image:url(\"https://images.alphacoders.com/100/thumb-1920-1005214.jpg\"); background-repeat: no-repeat; background-size: 100% 100%; border: 3;} #img {margin-left: center ;}</style>");
	    out.println("</HEAD>");
	    out.println("<BODY>");
	    out.println("<CENTER>");
	}
	
	private void sendPageFooter(HttpServletResponse response)
			throws ServletException, IOException{
		
		PrintWriter out = response.getWriter();
		out.println("</CENTER>");
		out.println("</BODY>");
		out.println("</HTML>");
	}
	
	private void sendIncludeForm(HttpServletRequest request,
			HttpServletResponse response)throws IOException {
		String id = request.getParameter("id");
		PrintWriter out = response.getWriter();
		out.println("<BR><H2 style= color:#FFFFFF>Formulário de Cadastro</H2>");
		out.println("<BR>");
		out.println("<BR><H3 style= color:#FFFFFF>Preencha as informações do filme.</H3>");
		out.println("<BR>");
		
		out.println("<BR><FORM METHOD=POST>");
        out.println("<TABLE>");
        out.println("<TR>");
        
        out.println("<TD>Titulo</TD>");
        out.print("<TD><INPUT TYPE=TEXT Name=titulo>");
        out.println("</TR>");
        out.println("<TR>");
        
        out.println("<TD>Genero:</TD> <TD><select name=\"genero\">\r\n" + 
        		"			<option value=\"Ação\">Ação</option>\r\n" + 
        		"			<option value=\"Aventura\">Aventura</option>\r\n" + 
        		"			<option value=\"Terror\">Terror</option>\r\n" + 
        		"			<option value=\"Comédia\">Comédia</option>\r\n" + 
        		"			<option value=\"Fantasia\">Fantasia</option>\r\n" + 

        		"		</select> <br> <br> \r\n</" + 
        		"");
        out.println("</TR>");
        out.println("<TR>");
        
        out.println("<TD>Duração (Minutos)</TD>");
        out.print("<TD><INPUT TYPE=TEXT Name=duracao>");
        out.println("</TR>");
        out.println("<TR>");
        
        out.println("<TD>Lançamento</TD>");
        out.print("<TD><INPUT TYPE=TEXT Name=lancamento>");
        out.println("</TR>");
        out.println("<TR>");
        
        out.println("<TD>Produtora</TD>");
        out.print("<TD><INPUT TYPE=TEXT Name=produtora");		       
        out.println("></TD>");
        out.println("</TR>");
        out.println("<TR>");
        out.println("<TD><INPUT TYPE=SUBMIT><TD>");
        out.println("<input type=\"button\" onclick=\"javascript: location.href='read';\" value=\"Voltar\" /></TD>"); 
        out.println("</TR>");
        out.println("</TABLE>");
        out.println("</FORM>");
		
	}
	
	void createRecord(HttpServletRequest request, HttpServletResponse
			response) throws IOException{
		String titulo = request.getParameter("titulo");
		String id = request.getParameter("id");
		String genero = request.getParameter("genero");
		String duracao = request.getParameter("duracao");
		String lancamento = request.getParameter("lancamento");
		String produtora = request.getParameter("produtora");
		PrintWriter out = response.getWriter();
		out.println("<BR><H2 style= color:#FFFFFF>Filme inserido com sucesso </H2");
		out.println("<BR>");
		
		Filme f1 = new Filme(titulo,
				genero,
				minutos=Integer.parseInt(duracao),
				ano=Integer.parseInt(lancamento),
				produtora);
		
		ControleFilme ctrlSalvar = new ControleFilme();
		ctrlSalvar.salvarFilme(f1);
		
		
		out.println("<input type=\"button\" onclick=\"javascript: location.href='read';\" value=\"Menu Principal\" /></TD>"); 
        
		
		
		
	}

}
