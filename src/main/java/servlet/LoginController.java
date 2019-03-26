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
            jspView = "ajoutDiscount.jsp";

        } else if (!"admin".equals(userName)) { // L'utilisateur est connecté
            // On choisit la page d'affichage
            jspView = "Customer.jsp";
        } else if ("admin".equals(userName)) {
            jspView = "admin.jsp";
        }
        // On va vers la page choisie
        request.getRequestDispatcher(jspView).forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            System.out.println("bug");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            System.out.println("bug");;
        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private void checkLogin(HttpServletRequest request) throws SQLException {

        // on va créer un DAO pour pouvoir intéragir avec la bdd
        DAO dao = new DAO();

        // Les paramètres transmis dans la requête
        String loginParam = request.getParameter("uname");
        String passwordParam = request.getParameter("psw");

        // on va d'abord vérifier si c'est un admin qui tente de se connecter
        if ((loginParam.equals("admin@admin") && (passwordParam.equals("admin")))) {
            // On a trouvé la combinaison login / password
            // On stocke l'information dans la session
            HttpSession session = request.getSession(true); // démarre la session
            session.setAttribute("userName", "admin");
        } else if (!"".equals(loginParam) && !"".equals(passwordParam)) {
            Customer c = dao.selectCustomerByMail(loginParam);
            // Le login/password défini dans les propiétés du customer
            String login = c.getEmail();
            String password = c.getPassword();
            String userName = c.getName();
            String phone = c.getPhone();
            String address = c.getAddressline1();

            // si le mail et le mdp correspondent alors on peut se connecter
            if ((login.equals(loginParam) && (password.equals(passwordParam)))) {
                // On a trouvé la combinaison login / password
                // On stocke l'information dans la session
                HttpSession session = request.getSession(true); // démarre la session
                session.setAttribute("userName", userName);
                session.setAttribute("userEmail", login);
                session.setAttribute("userPassword", password);
                session.setAttribute("userAddress", address);
                session.setAttribute("userPhone", phone);
                //session.setAttribute("commandes", dao.customerCommandes(c));  
                //ArrayList<myProducts> des = dao.allProducts();
                //request.setAttribute("listeProduits", des);
                //Double solde = dao.soldeClient(Integer.parseInt(password));
                //session.setAttribute("solde", solde);
                //session.setAttribute("codes", viewCodes(request));
            } else if (login.equals("nodata")) {
                request.setAttribute("errorMessage", "Login/Password incorrect");
            } else if ("".equals(loginParam) || "".equals(passwordParam)) { // On positionne un message d'erreur pour l'afficher dans la JSP
                request.setAttribute("errorMessage", "Login/Password incorrect");
            } else {
                request.setAttribute("errorMessage", "Login/Password incorrect");
            }

        }
    }

    private void doLogout(HttpServletRequest request) {
        // On termine la session
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
    }

    private String findUserInSession(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        return (session == null) ? null : (String) session.getAttribute("userName");
    }

}
