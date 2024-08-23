<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>JSP - Hello World</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      margin: 40px;
      background-color: #f4f4f4;
    }
    h1 {
      color: #333;
    }
    a {
      text-decoration: none;
      color: #007bff;
      padding: 10px 20px;
      border-radius: 5px;
      border: 1px solid #007bff;
      margin: 5px;
      display: inline-block;
    }
    a:hover {
      background-color: #007bff;
      color: #fff;
    }
  </style>
</head>
<body>

<h1><%= "Employee Computer Assignment App" %></h1>
<br>

<a href="AssigmentList.html">Employee Assignment List</a>
<a href="AssigningComputer.html">Assigning Computer/Reassigning Computer</a>

</body>
</html>
