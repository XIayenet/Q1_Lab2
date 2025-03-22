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
          <option value="Check password">Check password</option>
          <option value="Count words">Count words</option>
          <option value="Sensitive content Filtering">Sensitive content Filtering</option>
        </select>
        <div>
          <span style="color: red"> ${errorMessage == null ? "" : errorMessage} </span>
          <c:if test="${not empty result}">
            <p>${result}</p>
          </c:if>

        </div>

        <button type="submit">CHECK</button>
      </form>

    </div>




    <table>
      <tr>
        <th>Input string</th>
        <th>Option selected</th>
        <th>Result</th>
      </tr>
      <c:forEach var="entry" items="${entryList}"> 
        <tr>
          <td>
            ${entry.input}
          </td>
          <td>
            ${entry.option}
          </td>
          <td>
            ${entry.result.replace("\\n", "<br>")}
          </td>
        </tr>
      </c:forEach>

    </table>
  </body>
</html>
