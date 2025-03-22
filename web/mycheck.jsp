<%-- 
    Document   : mycheck.jsp
    Created on : 20 Mar 2025, 2:24:58 pm
    Author     : admin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>JSP Page</title>
    <style>
      table, th, td{
        border: 1px solid black;
      } 
        

    </style>
  </head>
  <body>
    <div class="container">
      <form action="myCheck" method="post">


        <label for="inputString">Input string (txtStr)</label>
        <input type="text" name="txtStr" id="inputString" placeholder="Enter your string">

        <label for="options">Choose an option:</label>
        <select id="options" name="choice">
          <option value="checkPassword">Check password</option>
          <option value="countWords">Count words</option>
          <option value="sensitiveContent">Sensitive content Filtering</option>
        </select>
        <div>
          <span style="color: red"> ${errorMessage == null ? "" : errorMessage} </span>
          ${result == null ? "" : result}

        </div>

        <button type="submit">CHECK</button>
      </form>

    </div>
          
          
          
          
    <table>
      <tr>
        <th>Result</th>
      </tr>
      <c:forEach var="pass" items="${passList}"> 
        <tr>
          <td>
            ${pass}
          </td>
        </tr>
      </c:forEach>

    </table>
  </body>
</html>
