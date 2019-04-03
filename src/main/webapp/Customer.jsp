<%-- 
    Document   : Customer
    Created on : 20 mars 2019, 11:49:55
    Author     : Mark
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Profil client : ${userName}</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" type="text/css" href="ressources/css/other.css">
    </head>
    <body>
        <div class="wrapper">
            <!-- Sidebar -->
            <div id="sidebar">
                <div class="sidebar-header">
                    <h3>${userName}</h3>
                </div>

                <ul class="list-unstyled components">
                    <li class="active">
                        <a href="#">Profil</a>
                    </li>
                    <li>
                        <a href="#">Liste des produits</a>
                    </li>
                    <li>
                        <form class="logout" action="LoginController" method="POST">
                            <input class="btn btn-light" type='submit' name='action' value='DECONNEXION'>
                        </form>
                    </li>
                </ul>
            </div>
            <div class="container-fluid">
                <div class="row">
                    <div class="col-12 col-md-8">
                        <div class="card">
                            <div class="header">
                                <h4 class="title">Effectuer une commande : </h4>
                            </div>
                            <div class="content">
                                <form method='POST' action="customerController">
                                    <div class="row">
                                        <div class="col-md-5">
                                            <div class="form-group">
                                                <label>Produit</label>
                                                <br>
                                                <select name="produit" class="select-custom selectpicker">
                                                    <c:forEach var="item" items="${listeProduits}">
                                                        <option value="${item}">${item}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="col-md-3">
                                            <div class="form-group">
                                                <label>Quantité</label>
                                                <input type="text" class="form-control" placeholder="Quantite" value="" name="quantite">
                                                <input type="hidden" name="action" value="ADD_COMMANDE">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group ">
                                                <button type="submit" class="btn btn-info btn-fill pull-right">Ajouter</button>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="clearfix"></div>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="col">
                        <div class="card" style="size: 20%">
                            <form class="form-inline">
                                <div class="form-group mb-2">
                                    <input type="text" class="form-control" disabled placeholder="Company" value="<fmt:formatNumber value = ${solde} >" type = "currency"/>            </div>
                                <div class="form-group mx-sm-3 mb-2">
                                    <label for="inputPassword2" class="sr-only">Montant à ajouter</label>
                                    <input type="number" class="form-control" id="argentAdd" placeholder="Montant à ajouter">
                                </div>
                                <button type="submit" class="btn btn-primary mb-2">Ajouter</button>
                            </form>
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
</html>
