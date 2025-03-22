/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;

/**
 *
 * @author Mmotkim
 */
public class MyCheck extends HttpServlet {

  /**
   * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
   * methods.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  protected void processRequest(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    response.setContentType("text/html;charset=UTF-8");
  }

  // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
  /**
   * Handles the HTTP <code>GET</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);
    HttpSession session = request.getSession();
    ArrayList<String> passList = new ArrayList<String>();
//    if (session.getAttribute("passList") != null) {
      passList = (ArrayList<String>) session.getAttribute("passList");
//    }

    session.setAttribute("passList", passList);
    request.getRequestDispatcher("mycheck.jsp").forward(request, response);

  }

  /**
   * Handles the HTTP <code>POST</code> method.
   *
   * @param request servlet request
   * @param response servlet response
   * @throws ServletException if a servlet-specific error occurs
   * @throws IOException if an I/O error occurs
   */
  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
          throws ServletException, IOException {
    processRequest(request, response);

    HttpSession session = request.getSession();

    String txtStr = request.getParameter("txtStr").trim();
    String choice = request.getParameter("choice");

    ArrayList<String> passList = new ArrayList<String>();
    if (session.getAttribute("passList") != null) {
      passList = (ArrayList<String>) session.getAttribute("passList");
    }

    String errorMessage = "";
    String result = "";

    switch (choice) {
      case "checkPassword":

        if (txtStr.trim().isEmpty()) {
          errorMessage = "Password is invalid!";
          request.setAttribute("errorMessage", errorMessage);
          request.getRequestDispatcher("mycheck.jsp").forward(request, response);

          break;
        } else
        
        if (passList.contains(txtStr)){
          errorMessage = "Already checked.";
          request.setAttribute("errorMessage", errorMessage);
          session.setAttribute("passList", passList);
          request.getRequestDispatcher("mycheck.jsp").forward(request, response);
          break;
        } else

        if (txtStr.length() > 10 && txtStr.matches(".*[A-Z].*") && txtStr.matches(".*[0-9].*") && txtStr.matches(".*[!@#$%^&*].*")) {
          result = "Strong";
          session.setAttribute("password", this);
          request.setAttribute("result", result);
          request.setAttribute("errorMessage", errorMessage);
          if (!passList.contains(txtStr)) {
            passList.add(txtStr);
          }
          session.setAttribute("passList", passList);
          request.getRequestDispatcher("mycheck.jsp").forward(request, response);
          break;
        } else if (txtStr.length() >= 6 && txtStr.length() <= 10 && txtStr.matches(".*[a-zA-Z].*") && txtStr.matches(".*[0-9].*")) {
          result = "Medium";
          request.setAttribute("result", result);
          request.setAttribute("errorMessage", errorMessage);
          request.getRequestDispatcher("mycheck.jsp").forward(request, response);
          break;
        } else if (txtStr.length() < 6) {
          result = "Weak";
          request.setAttribute("result", result);
          request.setAttribute("errorMessage", errorMessage);
          request.getRequestDispatcher("mycheck.jsp").forward(request, response);
          break;
        }
      case "countWord":

        break;
      case "contentFilter":

        break;

      default:
        request.setAttribute("result", result);
        request.setAttribute("errorMessage", errorMessage);
        throw new AssertionError();
    }

  }

  /**
   * Returns a short description of the servlet.
   *
   * @return a String containing servlet description
   */
  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
