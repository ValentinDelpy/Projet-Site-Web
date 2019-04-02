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

    </head>
    <body>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
            <a class="navbar-brand" href="#">Votre webmarket</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNav">
                <ul class="navbar-nav">
                    <li class="nav-item active">
                        <a class="nav-link" href="#">Profil <span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="#">Liste des produits</a>
                    </li>
                    <li class="nav-item">
                        <form action="LoginController" method="POST">
                            <input class="form-control" type='submit' name='action' value='DECONNEXION'>
                        </form>      
                    </li>
                </ul>
            </div>
        </nav>
        <div class="container-fluid">
            <div class="row">
                <div class="col">
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
                            <input type="text" class="form-control" disabled placeholder="Company" value="<fmt:formatNumber value = "${solde}" type = "currency"/>">            </div>
                        <div class="form-group mx-sm-3 mb-2">
                            <label for="inputPassword2" class="sr-only">Montant à ajouter</label>
                            <input type="number" class="form-control" id="argentAdd" placeholder="Montant à ajouter">
                        </div>
                        <button type="submit" class="btn btn-primary mb-2">Ajouter</button>
                    </form>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</html>
