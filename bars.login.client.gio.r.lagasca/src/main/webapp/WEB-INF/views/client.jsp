<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="com.accenture.tcf.bars.login.client.bars.login.client.domain.User" %>
<% User user = (User)request.getAttribute("user"); %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
  <head>
    <title>Title</title>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" 
    href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" 
    integrity="sha384-UHRtZLI+pbxtHCWp1t77Bi1L4ZtiqrqD80Kn4Z8NTSRyMA2Fd33n5dQ8lWUE00s/" 
    crossorigin="anonymous">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" 
    href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" 
    integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" 
    crossorigin="anonymous"> 
    </head>
  <body>
    <nav class="navbar navbar-expand-xl navbar-light bg-light shadow mb-5">
        <a class="navbar-brand" href="#">Bars Client</a>
        <div class="collapse navbar-collapse" id="collapsibleNavId">
            <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                <li class="nav-item">
                    <a class="nav-link active">Account List</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/add">Add Account</a>
                </li>
            </ul>
            <div class="form-inline my-2 my-lg-0">
                <ul class="nav navbar-nav">
                    <li class="dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="dropdownId" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${sessionScope.username}</a>
                        <ul class="dropdown-menu">
                            <li><a href="/logout">Logout</a></li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    
            
    <div class="container">
        <div class="row justify-content-center mt-5">
            <div class="card col-8 shadow p-3 mb-5 bg-white rounded">
                <table class="table table-hover table-bordered">
                    <thead>
                        <tr>
                        <th scope="col">ID#</th>
                        <th scope="col">Username</th>
                        <th scope="col">Password</th>
                        <th scope="col" colspan="2">Role</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="account" items="${list}">
                        <tr>
                            <th scope="row">${account.id}</th>
                            <td>${account.username}</td>
                            <td>${account.password}</td>
                            <td>${account.role}</td>
                            <td class="d-flex justify-content-end">
                                <a href="/edit/${account.id}"  class="btn btn-outline-success mr-2">
                                    <i class="fas fa-pencil-alt"></i> Edit
                                </a>
                                <form action="/delete" method="POST">
                                	<input type="hidden" name="id" value="${account.id}">
                                	<button type="submit" class="btn btn-outline-danger">
                                    	<i class="fas fa-trash"></i> Delete
                                	</button>
                                </form>
                            </td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy" crossorigin="anonymous"></script>
  </body>
</html>