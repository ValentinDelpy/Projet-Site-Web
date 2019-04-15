<%-- 
    Document   : Customer
    Created on : 20 mars 2019, 11:49:55
    Author     : Mark
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profil client : ${userName} </title>

        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="ressources/css/other.css">
    </head>
    <body>
        <div class="wrapper">
            <!-- Sidebar -->
            <div id="sidebar">
                <div class="sidebar-header">

                    <h3 class="text-center">${userName}</h3>
                    <hr/>
                    <div class="borderingit">
                        <p class="description text-center"> Ici c'est presque gratuit <br>

                        </p>
                        <c:forEach var="item" items="${codes}">
                            <p class="description text-center"> Votre code de réduction : "${item.discountCode}"</p>
                            <p class="description text-center"> Il vous donne accès à  : ${item.rate}% de réduction sur votre commande</p>
                        </c:forEach>
                    </div>
                </div>
                <hr/>
                <ul class="list-unstyled components">
                    <li class="active">
                        <a href="#" class="text-center">Profil</a>

                    </li>
                    <li>
                        <a href=".\product.jsp" class="text-center">Liste des produits</a>
                    </li>
                    <li>
                        <div class="centrer">
                            <form class="logout" action="LoginController" method="POST">
                                <input class="btn btn-light submit" display='block' type='submit' name='action' value='DECONNEXION'>
                            </form>
                        </div>
                    </li>
                </ul>
                <div><h4>${message}</h4></div>
                <hr/>
            </div>
            <div class="container corps">
                <div class="row">
                    <div class="col col-xl-6">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Effectuer une commande : </h4>
                            </div>
                            <form class="form-inline col-4" method='POST' action="CustomerController">
                                <div class="form-group">
                                        <label>Produit </label>
                                        <select name="produit" class="select-custom selectpicker">
                                            <c:forEach var="item" items="${listeProduits}">
                                                <option value="${item}">${item}</option>
                                            </c:forEach>
                                        </select>
                                </div>
                                <div class="form-group">
                                    <label>Quantité</label>
                                    <input type="text" class="form-control" placeholder="Quantite" value="" name="quantite">
                                    <input type="hidden" name="action" value="ADD_COMMANDE">
                                    <button type="submit" class="btn btn-primary mb-2 pop-up-button">Ajouter</button>
                                </div>
                                <div class="clearfix"></div>
                            </form>
                        </div>
                    </div>
                    <div class="col-12 col-xl-6">
                        <div class="card" style="size: 20%">
                            <div class="header">
                                <h4 class="title">Votre solde : </h4>
                            </div>
                            <form class="form-inline" method='POST' action="CustomerController">
                                <div class="form-group mb-2">
                                    <fmt:setLocale value = "en_US"/>
                                    <input type="text" class="form-control" disabled placeholder="Company" value='<fmt:formatNumber value = "${solde}" type = "currency" />'>            </div>

                                <div class="form-group mx-sm-3 mb-2">
                                    <label for="inputPassword2" class="sr-only">Montant à ajouter</label>
                                    <input type="text" class="form-control" id="argentAdd" placeholder="montant" name="montant">
                                    <input type="hidden" name="action" value="DO_VIREMENT">
                                </div>
                                <button type="submit" class="btn btn-primary mb-2">Ajouter</button>
                            </form>
                        </div>
                    </div>
                </div>

                <div class="row">
                    <div class="col-md-12">
                        <div class="card" >
                            <div class="header" id="headcommands">
                                <h4 class="title">Liste de vos commandes</h4>
                                <p class="category">Modifier ou supprimer une commande n'a jamais été aussi simple</p>
                            </div>
                            <div class="content table-responsive table-full-width">
                                <table class="table table-hover table-striped table-sm">
                                    <thead>
                                    <th>ID</th>
                                    <th>Numéro de commande</th>
                                    <th>Quantité</th>
                                    <th>Modifier</th>
                                    <th>Prix Total</th>
                                    <th>Type de produit</th>
                                    <th>Date</th>
                                    <th>Effacer</th>
                                    </thead>
                                    <tbody>
                                        <c:forEach var="comm" items="${commandes}">
                                            <tr>
                                        <form method='POST' action="CustomerController">
                                            <td >
                                                ${comm.CUSTOMER_ID}
                                            </td>
                                            <td>
                                                <input hidden name="purchaseToEdit" id="${comm.ORDER_NUM}" type="text" class="validate" value="${comm.ORDER_NUM}">
                                                <p name="purchaseToEdit" value="${comm.ORDER_NUM}">${comm.ORDER_NUM}</p>
                                            </td>
                                            <td >
                                                <input name="quantityToEdit" id="${comm.QUANTITY}" type="text" class="validate" value ="${comm.QUANTITY}">
                                                <input type="hidden" name="action" value="EDIT_COMMANDE">
                                            </td>

                                            <td>
                                                <button type="submit" class="btn btn-primary mb-2">Edit <i class="fa fa-pencil"></i></button>

                                            </td>
                                        </form>
                                        <td >
                                            <fmt:setLocale value = "en_US"/>
                                            <fmt:formatNumber value = "${comm.COST}" type = "currency"/>

                                        </td>
                                        <td >
                                            ${comm.DESCRIPTION}
                                        </td>
                                        <td >
                                            ${comm.SHIPPING_DATE}
                                        </td>

                                        <form method='POST' action="CustomerController">
                                            <td>
                                                <input hidden name="purchaseToDelete" id="${comm.ORDER_NUM}" type="text" class="validate" value="${comm.ORDER_NUM}">
                                                <input type="hidden" name="action" value="DELETE_COMMANDE">
                                                <button type="submit" class="btn btn-primary mb-2" name="action" value="DELETE_COMMANDE">Delete <i class="fa fa-trash"></i></button>
                                                <span class="popuptext" id="myPopup">Commande supprimée.</span>

                                                </a>
                                            </td>
                                        </form>
                                        </tr>
                                    </c:forEach> 
                                    </tbody>
                                </table>

                            </div>
                        </div>
                    </div>
                </div>
                <footer class="footer">
                    <div class="container-fluid">
                        <p class="copyright pull-right">
                            &copy;<a href="#"> Promotion 2021</a>, made by CDGDC Corporation.
                        </p>
                    </div>
                </footer>
            </div>
        </div>

    </body>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>

    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    <script type="text/javascript">
        $(document).ready(function () {

            demo.initChartist();

            $.notify({
                icon: 'pe-7s-gift',
                message: "Bienvenue sur votre super Web Market ${userName}!"

            }, {
                type: 'info',
                timer: 4000
            });

        });


    </script>
</html>
