<%--
  Created by IntelliJ IDEA.
  User: mohithreddy
  Date: 4/13/24
  Time: 6:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventory</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 40px;
            background-color: #f4f4f4;
        }
        label {
            display: inline-block;
            width: 150px;
            margin-bottom: 10px;
        }
        input[type="text"] {
            width: 300px;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 16px;
        }
        select#inventory {
            width: 500px;
            padding: 10px;
            border-radius: 5px;
            border: 1px solid #ccc;
            font-size: 16px;
            font-family: Arial, sans-serif;
        }
        select#inventory option {
            padding: 10px;
        }
        input[type="submit"] {
            padding: 12px 24px;
            border-radius: 5px;
            border: none;
            background-color: #007bff;
            color: #fff;
            cursor: pointer;
            margin-top: 10px;
            font-size: 16px;
        }
        input[type="submit"]:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>

<form action="listForm" method="GET">

    <fieldset id="info">

        <label for="id">Employee ID:</label>
        <input id="id" type="text" size="20" name="eid" value="${eid}"><br><br>

        <label for="name">Employee Name:</label>
        <input id="name" type="text" size="20" name="ename" value="${ename}"><br><br>

        <label for="inventory">Employee Assignment:</label><br>
        <select id="inventory" name="inventory" size="8" disabled="true">
            <c:forEach items="${inventory}" var="corder">
                <option>${corder}</option>
            </c:forEach>
        </select><br><br>

        <input type="submit" value="Get Employee">
        <input type="submit" formaction="index.jsp" value="Main Menu">

    </fieldset>

</form>

</body>
</html>
