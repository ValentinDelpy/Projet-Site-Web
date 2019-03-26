/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Customer;
import model.DAO;
import model.DiscountCode;
import model.PurchaseOrder;

public class LoginController extends HttpServlet {
    
    
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
		throws ServletException, IOException, SQLException {
		// Quelle action a appelé cette servlet ?
		String action = request.getParameter("action");
		if (null != action) {
			switch (action) {
				case "login":
					checkLogin(request);
                                        
					break;
				case "DECONNEXION":
                                    
					doLogout(request);
					break;                                                                
                                
			}
		}

		// Est-ce que l'utilisateur est connecté ?
		// On cherche l'attribut userName dans la session
		String userName = findUserInSession(request);
		String jspView = null;
		if (null == userName) { 
// L'utilisateur n'est pas connecté
			// On choisit la page de login
			jspView = "login.jsp";

		} else if(!"admin".equals(userName)){ // L'utilisateur est connecté
			// On choisit la page d'affichage
			jspView = "WEB-INF/customer.jsp";
		}
                
                else if ("admin".equals(userName)){
                    jspView = "WEB-INF/admin.jsp";
                }
		// On va vers la page choisie
		request.getRequestDispatcher(jspView).forward(request, response);

	}

	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	private void checkLogin(HttpServletRequest request) {
            
                // on va créer un DAO pour pouvoir intéragir avec la bdd
                DAO dao = new DAO();
                
                
		// Les paramètres transmis dans la requête
		String loginParam = request.getParameter("loginParam");
		String passwordParam = request.getParameter("passwordParam");
                
                // on va d'abord vérifier si c'est un admin qui tente de se connecter
                if ((loginParam.equals("admin@admin") && (passwordParam.equals("admin")))) {
			// On a trouvé la combinaison login / password
			// On stocke l'information dans la session
			HttpSession session = request.getSession(true); // démarre la session
			session.setAttribute("userName", "admin");
		}
                
        }
        }