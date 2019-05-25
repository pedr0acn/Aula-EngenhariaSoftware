package visao;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controle.ControleFilme;

public class ConfirmServlet extends HttpServlet{
	int idDelete;
	public void doGet(HttpServletRequest request, HttpServletResponse
		      response) throws
		      ServletException, IOException {
		String id = request.getParameter("id");
		PrintWriter out = response.getWriter();
		
		out.println("<HTML>");
		out.println("<HEAD>");
		out.println("<TITLE>Deseja apagar esse filme?</TITLE>");
		out.println("<TITLE>Tem certeza?</TITLE>");//alteração
		out.println("<style>body {background-image:url(\"https://images.alphacoders.com/100/thumb-1920-1005214.jpg\"); background-repeat: no-repeat; background-size: 100% 100%; border: 3;} #img {margin-left: center ;}</style>");
	    out.println("</HEAD>");
		out.println("<BODY>");
	    out.println("<CENTER>");
	    out.println("<BR> <H2 style= color:#FFFFFF> Confirmar Exclusão</H2>");
	    out.println("<BR><H3 style= color:#FFFFFF>Deseja excluir esse filme?</H3>");
	    out.println("<BR>");
	    out.println("<BR><FORM METHOD=POST>");
	    
	    out.println("<TD><A HREF=delete?id=" + id +">Apagar</A></TD>");
	    out.println("<input type=\"button\" onclick=\"javascript: location.href='read';\" value=\"Cancelar\" /></TD>"); 
	    out.println("</FORM>");
	    out.println("<BR>");
	    out.println("<BR>");
	    
		
		    }
	
	
}
