package banqueWeb.Controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.ejb.EJB;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import metier.entities.Compte;
import metier.session.ICompteLocal;
import metier.session.ICompteRemote;

/**
 * Servlet implementation class CompteController
 */
@WebServlet("/CompteController")
public class CompteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	
    public CompteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/views/CompteView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String action = request.getParameter("action");
		Properties jndiProperties= new Properties();
		ICompteRemote stub = null;
		jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory"); 
		  jndiProperties.put(Context.PROVIDER_URL,"http-remoting://localhost:8080");
		  Context context = null;
		try {
			context = new InitialContext(jndiProperties);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  try {
			 stub = (ICompteRemote) context.lookup("ejb:BanqueEAR/EjbBanque/CompteEjb!metier.session.ICompteRemote");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   if(action.equals("Ajouter"))
   {
	   stub.addCompte(new Compte(request.getParameter("proprietaire"),request.getParameter("categorie").charAt(0),0.0));
	   
   }
   else if(action.equals("Consulter"))
   {
	  if(!request.getParameter("code").equals(""))
	   { List<Compte> l = new ArrayList<Compte>();
	   Compte compte =stub.ConsulterCompte(Long.parseLong(request.getParameter("code")));
	   l.add(compte);
	   request.setAttribute("Comptes", l);
	   }
	  else {
		  request.setAttribute("errCode", "veuillez entrer le numero de compte");
	  }
   }
   else 
   {
	   request.setAttribute("Comptes",  stub.ConsulterComptes());

   }
    request.getRequestDispatcher("/views/CompteView.jsp").forward(request, response);
	}

}
