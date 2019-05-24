package visao;
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.sql.*;

import controle.ControleFilme;

public class DeleteServlet extends HttpServlet {
	int idDelete;
	public void doGet(HttpServletRequest request, HttpServletResponse
		      response) throws
		      ServletException, IOException {
		String id = request.getParameter("id");
		idDelete = Integer.parseInt(id);
		ControleFilme ctrlAlterar = new ControleFilme();
		ctrlAlterar.deletarFilme(idDelete);
		

		      response.setContentType("text/html");
		      PrintWriter out = response.getWriter();
		      out.println("<HTML>");
		      out.println("<HEAD>");
		      out.println("<TITLE>Apagando Um Registro</TITLE>");
		      out.println("</HEAD>");
		      out.println("<style>body {background-image:url(\"https://images.alphacoders.com/100/thumb-1920-1005214.jpg\"); background-repeat: no-repeat; background-size: 100% 100%; border: 3;} #img {margin-left: center ;}</style>");
			    
		      out.println("<CENTER>");
		      
		      //out.println("<P>Filme Apagado com sucesso</P>");
		      out.println("<BR> <H1 style= color:#FFFFFF> Apagado com sucesso</H1>");

		      out.println("<input type=\"button\" onclick=\"javascript: location.href='read';\" value=\"MENU PRINCIPAL\" /></TD>"); 
		    }
	
	

}
