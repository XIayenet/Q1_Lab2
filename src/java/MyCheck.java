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
    ArrayList<Entry> entryList = new ArrayList<Entry>();
    if (session.getAttribute("entryList") != null) {
      entryList = (ArrayList<Entry>) session.getAttribute("entryList");
    }

    request.setAttribute("entryList", entryList);
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
    String errorMessage = "";
    String result = "";

    String txtStr = request.getParameter("txtStr").trim();
    String choice = request.getParameter("choice");

    ArrayList<Entry> entryList = new ArrayList<>();
    if (session.getAttribute("entryList") != null) {
      entryList = (ArrayList<Entry>) session.getAttribute("entryList");
    }

    if (checkInputExists(entryList, txtStr)) {
      errorMessage = "Already checked.";
    } else {

      switch (choice) {
        case "Check password" -> {
          if (txtStr.trim().isEmpty()) {
            errorMessage = "Password is invalid!";
          } else if (txtStr.length() > 10 && txtStr.matches(".*[A-Z].*") && txtStr.matches(".*[0-9].*") && txtStr.matches(".*[!@#$%^&*].*")) {
            result = "Strong";
            entryList.add(new Entry(txtStr, choice, result));
          } else if (txtStr.length() >= 6 && txtStr.length() <= 10 && txtStr.matches(".*[a-zA-Z].*") && txtStr.matches(".*[0-9].*")) {
            result = "Medium";
          } else if (txtStr.length() < 6) {
            result = "Weak";
          }
        }
        case "Count words" -> {
          int wordCount = 0;
          int sentenceCount = 0;
          if (!txtStr.isEmpty()) {
            // Count words
            wordCount = txtStr.split("\\s+").length;

            // Count sentences
            String[] sentences = txtStr.split("[.!?]+");
            for (String s : sentences) {
              if (!s.trim().isEmpty()) {
                sentenceCount++;
              }
            }
          }
          result = "Word count: " + wordCount + "<br>Sentence count: " + sentenceCount;
          entryList.add(new Entry(txtStr, choice, result));
        }
        case "Sensitive content Filtering" -> {
          String[] restrictedWords = {"badword1", "badword2", "badword3"};

          String filteredText = txtStr;
          for (String word : restrictedWords) {
            filteredText = filteredText.replaceAll("(?i)" + word, "***");
          }
          result = filteredText;
          entryList.add(new Entry(txtStr, choice, result));
          break;
        }
      }
    }
    session.setAttribute("entryList", entryList);
    request.setAttribute("result", result);
    request.setAttribute("errorMessage", errorMessage);
    request.getRequestDispatcher("mycheck.jsp").forward(request, response);

  }

  public boolean checkInputExists(ArrayList<Entry> entryList, String input) {
    for (Entry entry : entryList) {
      if (entry.getInput().equals(input)) {
        return true;
      }
    }
    return false;
  }

  @Override
  public String getServletInfo() {
    return "Short description";
  }// </editor-fold>

}
