package visao;

import javax.servlet.*;
import javax.servlet.http.*;

import controle.ControleFilme;
import modelo.Filme;
import util.Retorno;

import java.io.*;
import java.util.*;
import java.sql.*;



public class UpdadeServlet extends HttpServlet{
	int ano, minutos,idInclude;
	
	public void doGet(HttpServletRequest request, HttpServletResponse
		      response) throws ServletException, IOException {

		      sendPageHeader(response);
		      sendUpdateForm(request, response);
		      sendPageFooter(response);
		    }
	
	public void doPost(HttpServletRequest request, HttpServletResponse
		      response) throws ServletException, IOException {

		      sendPageHeader(response);
		      updateRecord(request, response);
		      sendPageFooter(response);
		    }
	
	 private void sendPageHeader(HttpServletResponse response)
		      throws ServletException, IOException {

		      response.setContentType("text/html");
		      PrintWriter out = response.getWriter();
		      out.println("<HTML>");
		      out.println("<HEAD>");
		      out.println("<TITLE>Atualizando Registro</TITLE>");
			  out.println("<style>body {background-image:url(\"https://images.alphacoders.com/100/thumb-1920-1005214.jpg\"); background-repeat: no-repeat; background-size: 100% 100%; border: 3;} #img {margin-left: center ;}</style>");
		      out.println("</HEAD>");
		      out.println("<BODY>");
		      out.println("<CENTER>");
		    }
	 
	 private void sendPageFooter(HttpServletResponse response)
		      throws ServletException, IOException {

		      PrintWriter out = response.getWriter();
		      out.println("</CENTER>");
		      out.println("</BODY>");
		      out.println("</HTML>");
		    }
	 
	 private void sendUpdateForm(HttpServletRequest request,
		      HttpServletResponse response) throws IOException {
		 
		  String id = request.getParameter("id");
		  int idUpdate = Integer.parseInt(id);
		  
		  ControleFilme ctrlUp = new ControleFilme();
		  Retorno<List<Filme>> filme1 = ctrlUp.listaUpdate(idUpdate);
		  List<Filme> listaUpdate = filme1.getDado();
		  
		  Filme f1 = (Filme)listaUpdate.get(0);
		  String tituloUp = f1.getTitulo();
		  int duracaoUp = f1.getDuracao();
		  int anoUp = f1.getLancamento();
		  String produtoraUp = f1.getProdutora();
		  
		 PrintWriter out = response.getWriter();
		 out.println("<BR><H2 style= color:#FFFFFF>Formulário de Atualização</H2>");
	     out.println("<BR><H3 style= color:#FFFFFF>Por favor, altere os dados do filme</H3>");
	     out.println("<BR>");
	     //out.println("<A HREF=read>Página de Busca</A>");
	     out.println("<BR>");
	     
	     
	     out.println("<BR><FORM METHOD=POST>");
	        out.println("<TABLE>");
	        out.println("<TR>");
	        
	        out.println("<TD>Titulo</TD>");
	        out.print("<TD><INPUT TYPE=TEXT Name=titulo value=\""+tituloUp+"\">"); //value = "+tituloUp+"
	        out.println("</TR>");
	        out.println("<TR>");
	        
	        
	        //out.println("<TD>Genero</TD>");
	       // out.print("<TD><INPUT TYPE=TEXT Name=genero>");
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
	        
	        out.println("<TD>Duração</TD>");
	        out.print("<TD><INPUT TYPE=TEXT Name=duracao value=\""+duracaoUp+"\">");
	        out.println("</TR>");
	        out.println("<TR>");
	        
	        out.println("<TD>Lançamento</TD>");
	        out.print("<TD><INPUT TYPE=TEXT Name=lancamento value=\""+anoUp+"\">");
	        out.println("</TR>");
	        out.println("<TR>");
	        
	        out.println("<TD>Produtora</TD>");
	        out.print("<TD><INPUT TYPE=TEXT Name=produtora value=\""+produtoraUp+"\">");		       
	        out.println("</TD>");
	        out.println("</TR>");
	        out.println("<TR>");
	        //out.println("<TD><INPUT TYPE=RESET></TD>");
	        out.println("<TD><INPUT TYPE=SUBMIT>");
	        out.println("<input type=\"button\" onclick=\"javascript:window.history.go(-1);\" value=\"Voltar\" /></TD>"); 
	        
	        out.println("</TR>");
	        out.println("</TABLE>");
	        out.println("</FORM>");
	      
	 }
	 
	 void updateRecord(HttpServletRequest request, HttpServletResponse
		      response) throws IOException {

		 String titulo = request.getParameter("titulo");
			String id = request.getParameter("id");
			String genero = request.getParameter("genero");
			String duracao = request.getParameter("duracao");
			String lancamento = request.getParameter("lancamento");
			String produtora = request.getParameter("produtora");
			PrintWriter out = response.getWriter();
			
			Filme f1 = new Filme(idInclude = Integer.parseInt(id),
					titulo,
					genero,
					minutos=Integer.parseInt(duracao),
					ano=Integer.parseInt(lancamento),
					produtora);
			
			ControleFilme ctrlAlterar = new ControleFilme();
			ctrlAlterar.alterarFilme(f1);
			out.println("<BR> <H1 style= color:#FFFFFF> Filme Alterado com Sucesso</H1>");
			
			out.println("<input type=\"button\" onclick=\"javascript: location.href='read';\" value=\"MENU PRINCIPAL\" /></TD>"); 
	        
		    }

}
