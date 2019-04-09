<%-- 
    Document   : product
    Created on : 9 avr. 2019, 09:30:08
    Author     : Mark
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Customer"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <link rel="apple-touch-icon" sizes="76x76" href="../assets/img/apple-icon.png">
        <link rel="icon" type="image/png" href="../assets/img/favicon.png">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <title>
            Admin tables
        </title>
        <meta content='width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0, shrink-to-fit=no' name='viewport' />
        <!--     Fonts and icons     -->
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
        <!-- CSS Files -->
        <link href="../assets/css/material-dashboard.css?v=2.1.1" rel="stylesheet" />
        <link href="./ressources/css/admin.css" rel="stylesheet" />
        <title>Profil client : ${userName} </title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="ressources/css/other.css">
    </head>
    <body>
        <div class="wrapper ">

            <div id="sidebar">
                <div class="sidebar-header">
                    <h3>${userName}</h3>
                    <p class="description text-center"> Ici c'est presque gratuit <br>

                    </p>
                    <c:forEach var="item" items="${codes}">
                        <p class="description text-center"> Vous possedez un le code de réduction suivant : "${item.discountCode}"</p>
                        <p class="description text-center"> Il vous donne accès à  : ${item.rate}% de réduction sur votre commande</p>
                    </c:forEach>
                </div>

                <ul class="list-unstyled components">
                    <li class="active">
                        <a href=".\Customer.jsp">Profil</a>
                        <div><h4>${message}</h4></div>
                    </li>
                    <li>
                        <a href=".\product.jsp">Liste des produits</a>
                    </li>
                    <li>
                        <form class="logout" action="LoginController" method="POST">
                            <input class="btn btn-light" type='submit' name='action' value='DECONNEXION'>
                        </form>
                    </li>
                </ul>
            </div>
            <div class="col-md-12">
                <div class="card card-plain">
                    <div class="card-header card-header-primary">
                        <h4 class="card-title mt-0"> Liste des produits</h4>
                        <p class="card-category"> Here is a subtitle for this table</p>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-hover">
                                <thead class="">
                                <th>
                                    ID
                                </th>
                                <th>
                                    Name
                                </th>
                                <th>
                                    Price
                                </th>

                                </thead>
                                <tbody>
                                <c:forEach var="prod" items="${produit}">


                                    <tr>
                                        <td>${prod.productId}</td>
                                        <td>${prod.description}</td>
                                        <td>${prod.purchaseCost}</td>


                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
