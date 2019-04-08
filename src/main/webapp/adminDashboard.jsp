<%@page import="model.DAO" %> 
<!doctype html>
<html lang="en">

    <head>
        <title>Admin dashboard</title>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
        <!--     Fonts and icons     -->
        <link rel="stylesheet" type="text/css" href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700|Roboto+Slab:400,700|Material+Icons" />
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/latest/css/font-awesome.min.css">
        <!-- Material Kit CSS -->
        <link href="ressources/css/admin.css" rel="stylesheet" />
    </head>

    <body>
        <div class="wrapper ">
            <div class="sidebar" data-color="purple" data-background-color="white">
                <!--
                Tip 1: You can change the color of the sidebar using: data-color="purple | azure | green | orange | danger"

                Tip 2: you can also add an image using data-image tag
                -->
                <div class="logo">
                    <a class="simple-text logo-normal">
                        Admin global view
                    </a>
                </div>
                <div class="sidebar-wrapper">
                    <ul class="nav">
                        <li class="nav-item active  ">
                            <a class="nav-link" href="#0">
                                <i class="material-icons">dashboard</i>
                                <p>Dashboard</p>
                            </a>
                        <li class="nav-item ">
                            <a class="nav-link" href="./adminTables.jsp">
                                <i class="material-icons">content_paste</i>
                                <p>Table List</p>
                            </a>
                        </li>
                        <li class="nav-item ">
                            <a class="nav-link" href="./ajoutDiscount.jsp">
                                <i class="material-icons">eject</i>
                                <p>Disconnect</p>
                            </a>
                        </li>
                        <!-- your sidebar here -->
                    </ul>
                </div>
            </div>
            <div class="main-panel">
                <!-- Navbar -->
                <nav class="navbar navbar-expand-lg navbar-transparent navbar-absolute fixed-top ">
                    <div class="container-fluid">
                        <div class="navbar-wrapper">
                            <a class="navbar-brand" href="#pablo">Tables</a>
                        </div>
                        <button class="navbar-toggler" type="button" data-toggle="collapse" aria-controls="navigation-index" aria-expanded="false" aria-label="Toggle navigation">
                            <span class="sr-only">Toggle navigation</span>
                            <span class="navbar-toggler-icon icon-bar"></span>
                            <span class="navbar-toggler-icon icon-bar"></span>
                            <span class="navbar-toggler-icon icon-bar"></span>
                        </button>
                        <div class="collapse navbar-collapse justify-content-end">
                            <ul class="navbar-nav">
                                <li class="nav-item">
                                    <a class="nav-link" href="#pablo">
                                        <i class="material-icons">notifications</i> Notifications
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </nav>
                <!-- End Navbar -->
                <div class="content">
                    <div class="container-fluid">
                        <div class="content">
                            <div class="container-fluid">
                                <div class="row">
                                    <div class="col-lg-3 col-md-6 col-sm-6">
                                        <div class="card card-stats">
                                            <div class="card-header card-header-warning card-header-icon">
                                                <div class="card-icon">
                                                    <i class="material-icons">attach_money</i>
                                                </div>
                                                <p class="card-category">Bénéfice</p>
                                                <h3 class="card-title">                                                    
                                                    <% DAO dao = new DAO();
                                                        out.println(dao.benefice());%>
                                                  
                                                    <small>dollars</small>
                                                </h3>
                                                
                                            </div>
                                            <div class="card-footer">
                                                <div class="stats">
                                                    <i class="material-icons">local_offer</i>
                                                    <a>Chiffre d'affaire total</a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-3 col-md-6 col-sm-6">
                                        <div class="card card-stats">
                                            <div class="card-header card-header-success card-header-icon">
                                                <div class="card-icon">
                                                    <i class="material-icons">store</i>
                                                </div>
                                                <p class="card-category">Nombre de produits</p>
                                                <h3 class="card-title">
                                                    <% out.println(dao.nbProduits());%>
                                                </h3>
                                            </div>
                                            <div class="card-footer">
                                                <div class="stats">
                                                    <i class="material-icons">date_range</i>
                                                    <a> Un choix varié </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-lg-3 col-md-6 col-sm-6">
                                        <div class="card card-stats">
                                            <div class="card-header card-header-danger card-header-icon">
                                                <div class="card-icon">
                                                    <i class="material-icons">person</i>
                                                </div>
                                                <p class="card-category">Nombre clients</p>
                                                <h3 class="card-title">
                                                    <% out.println(dao.nbClients());%>
                                                </h3>
                                            </div>
                                            <div class="card-footer">
                                                <div class="stats">
                                                    <i class="material-icons">local_offer</i> Fidèles et assidus
                                                </div>
                                            </div> 
                                        </div>
                                    </div>
                                    <div class="col-lg-3 col-md-6 col-sm-6">
                                        <div class="card card-stats">
                                            <div class="card-header card-header-info card-header-icon">
                                                <div class="card-icon">
                                                    <i class="material-icons">local_shipping</i>
                                                </div>
                                                <p class="card-category">Nombre fournisseurs</p>
                                                <h3 class="card-title">
                                                    <% out.println(dao.nbManufacturer());%>
                                                </h3>
                                            </div>
                                            <div class="card-footer">
                                                <div class="stats">
                                                    <i class="material-icons">update</i> Fourni-fournissez.
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>


                <div class="content">
                    <div class="container-fluid">
                        <div class="row">
                            <div class="col-md-6">
                                <div class="card">

                                    <div class="header">
                                        <h4 class="title">Chiffre d'affaire par Produit</h4>
                                        <p class="category">Visualisez quelle produit a le plus grand chiffre d'affaire !</p>
                                        <form method='POST' action="AdminController">
                                            <input type="date" class="choixdate" name="date_debut">
                                            <input type="date" class="choixdate" name="date_fin">
                                            <input type="hidden" name="action" value="caByProduct">
                                            <input type="submit" value="Envoyer" name="action" value="caByProduct">
                                        </form>
                                    </div>
                                    <div class="content">
                                        <canvas id="myChart"></canvas>

                                        <div class="footer">

                                            <hr>
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="card">
                                    <div class="header">
                                        <h4 class="title">Chiffre d'affaire par Zone Géographique</h4>
                                        <p class="category">Quelle zone vend le plus ?</p>
                                    </div>
                                    <div class="content">
                                        <form method='POST' action="AdminController">
                                            <input type="date" class="choixdate" name="date_debut_geo">
                                            <input type="date" class="choixdate" name="date_fin_geo">
                                            <input type="hidden" name="action" value="caByGeo">
                                            <input type="submit" value="Envoyer">
                                        </form>
                                        <canvas id="chartGeo"></canvas>
                                        <div class="footer">
                                            <div class="legend">

                                            </div>
                                            <hr>
                                            <div class="stats">
                                                <i class="fa fa-history"></i> Les chiffres sont ici marquants.
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>



                        <div class="row">
                            <div class="col-md-6">
                                <div class="card ">
                                    <div class="header">
                                        <h4 class="title">Chiffre d'affaire par Client</h4>
                                        <p class="category">Qui est le plus gros acheteur compulsif ?</p>
                                        <form method='POST' action="AdminController">
                                            <input type="date" class="choixdate" name="date_debut_cli">
                                            <input type="date" class="choixdate" name="date_fin_cli">
                                            <input type="hidden" name="action" value="caByCli">
                                            <input type="submit" value="Envoyer">
                                        </form>
                                    </div>
                                    <div class="content">
                                        <canvas id="chartCli"></canvas>

                                        <div class="footer">

                                            <hr>
                                            
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <div class="col-md-6">
                                <div class="card ">
                                    <div class="header">
                                        <h4 class="title">Chiffre d'affaire par Catégorie d'article</h4>
                                        <p class="category">Quel produit plaît le plus à Jasmine Ghozali ?</p>
                                        <form method='POST' action="AdminController">
                                            <input type="date" class="choixdate" name="date_debut_cat">
                                            <input type="date" class="choixdate" name="date_fin_cat">
                                            <input type="hidden" name="action" value="caByProductCode">
                                            <input type="submit" value="Envoyer">
                                        </form>
                                    </div>
                                    <div class="content">
                                        <canvas id="chartCat"></canvas>

                                        <div class="footer">

                                            <hr>
                                           
                                        </div>
                                    </div>
                                </div>
                            </div>

                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <div class="card ">
                                    <div class="header">
                                        <h4 class="title">Chiffre d'affaire par ZIP (Code postal)</h4>
                                        <p class="category">Quel "Plan d'Amélioration du Découpage en Zone" possède le meilleur CA ?</p>
                                        <form method='POST' action="AdminController">
                                            <input type="date" class="choixdate" name="date_debut_zip">
                                            <input type="date" class="choixdate" name="date_fin_zip">
                                            <input type="hidden" name="action" value="caByZip">
                                            <input type="submit" value="Envoyer">
                                        </form>
                                    </div>
                                    <div class="content">
                                        <canvas id="chartZip"></canvas>

                                        <div class="footer">

                                            <hr>
                                          
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                        </div>
                    </div>
                </div>
                <footer class="footer">
                    <div class="container-fluid">
                        <nav class="float-left">
                            <ul>
                                <li>
                                    <a href='https://github.com/ValentinDelpy/Projet-Site-Web'>
                                        Curt Bryan - Delpy Valentin - Goncalves de Carvalho Amaury
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                </footer>
            </div>
        </div>
    </body>
    <script src="path/to/chartjs/dist/Chart.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.bundle.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.7.3/Chart.min.js"></script>
    <script>
        var ctx = document.getElementById('myChart').getContext('2d');
        var label = [];
        var titre = "CA par Produit en $ ${dateProcuct}"
        var ca = [];
        <c:forEach items="${productCA}" var="item" >
        label.push("${item.key}");
        </c:forEach>
        <c:forEach items="${productCA}" var="item" >
        ca.push(${item.value});
        </c:forEach>
        var randomColorGenerator = function () {
            return '#' + (Math.random().toString(16) + '0000000').slice(2, 8);
        };
        var chart = new Chart(ctx, {
            // The type of chart we want to create
            type: 'horizontalBar',

            // The data for our dataset
            data: {
                labels: label,
                datasets: [{
                        label: "CA par Produit en $, ${dateProduct}.",
                        backgroundColor: 'rgb(255, 99, 132)',
                        borderColor: 'rgb(255, 99, 132)',
                        data: ca,
                    }]
            },

            // Configuration options go here
            options: {}
        });
    </script>

    <script>
        var ctx = document.getElementById('chartGeo').getContext('2d');
        var label = [];
        var ca = [];
        <c:forEach items="${geoCA}" var="item" >
        label.push("${item.key}");
        </c:forEach>
        <c:forEach items="${geoCA}" var="item" >
        ca.push(${item.value});
        </c:forEach>
        var chart = new Chart(ctx, {
            // The type of chart we want to create
            type: 'bar',

            // The data for our dataset
            data: {
                labels: label,
                datasets: [{
                        label: "CA par zone Géographique en $, ${dateGeo}",
                        backgroundColor: 'rgb(255, 99, 132)',
                        borderColor: 'rgb(255, 99, 132)',
                        data: ca,
                    }]
            },

            // Configuration options go here
            options: {}
        });
    </script>

    <script>
        var ctx = document.getElementById('chartCli').getContext('2d');
        var label = [];
        var ca = [];
        <c:forEach items="${cliCA}" var="item" >
        label.push("${item.key}");
        </c:forEach>
        <c:forEach items="${cliCA}" var="item" >
        ca.push(${item.value});
        </c:forEach>
        var chart = new Chart(ctx, {
            // The type of chart we want to create
            type: 'bar',

            // The data for our dataset
            data: {
                labels: label,
                datasets: [{
                        label: "CA par Client en $, ${dateCli}",
                        backgroundColor: 'rgb(255, 99, 132)',
                        borderColor: 'rgb(255, 99, 132)',
                        data: ca,
                    }]
            },

            // Configuration options go here
            options: {}
        });
    </script>

    <script>
        var ctx = document.getElementById('chartCat').getContext('2d');
        var label = [];
        var ca = [];
        <c:forEach items="${productCodeCA}" var="item" >
        label.push("${item.key}");
        </c:forEach>
        <c:forEach items="${productCodeCA}" var="item" >
        ca.push(${item.value});
        </c:forEach>
        var chart = new Chart(ctx, {
            // The type of chart we want to create
            type: 'horizontalBar',

            // The data for our dataset
            data: {
                labels: label,
                datasets: [{
                        label: "CA par catégorie d'article en $, ${dateProductCode}",
                        backgroundColor: 'rgb(255, 99, 132)',
                        borderColor: 'rgb(255, 99, 132)',
                        data: ca,
                    }]
            },

            // Configuration options go here
            options: {}
        });
    </script>

    <script>
        var ctx = document.getElementById('chartZip').getContext('2d');
        var label = [];
        var ca = [];
        <c:forEach items="${zipCA}" var="item" >
        label.push("${item.key}");
        </c:forEach>
        <c:forEach items="${zipCA}" var="item" >
        ca.push(${item.value});
        </c:forEach>
        var chart = new Chart(ctx, {
            // The type of chart we want to create
            type: 'horizontalBar',

            // The data for our dataset
            data: {
                labels: label,
                datasets: [{
                           label: "CA par catégorie d'article en $, ${dateZip}",
                        backgroundColor: 'rgb(255, 99, 132)',
                        borderColor: 'rgb(255, 99, 132)',
                        data: ca,
                    }]
            },

            // Configuration options go here
            options: {}
        });
    </script>

</html>
