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
                                                    <i class="material-icons">euro_symbol</i>
                                                </div>
                                                <p class="card-category">Bénéfice</p>
                                                <h3 class="card-title">                                                    
                                                    <% DAO dao = new DAO();
                                                        out.println(dao.benefice());%>
                                                  
                                                    <small>euros</small>
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
                            <div class="container">
                                <div class="row">
                                    <span class="row">
                                        <div class="text-center">
                                            <canvas id="graphique" width="1000" height="350"></canvas>
                                        </div>
                                    </span>
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
    <script>
        var chartdata = {
            labels: ["2016-02-29 11:02:06", "2016-02-29 12:01:54", "2016-02-29 13:02:09", "2016-02-29 14:02:10", "2016-02-29 15:02:08", "2016-02-29 16:02:10", "2016-02-29 17:02:09"],
            datasets: [{
                    fillColor: "rgba(7,75,234,0.5)",
                    strokeColor: "#074bea",
                    pointColor: "#fff",
                    pointStrokeColor: "#074bea",
                    data: [0, 862, 1899, 2430, 2768, 2958, 3124]
                },
                {
                    fillColor: "rgba(23,234,7,0.5)",
                    strokeColor: "#17ea07",
                    pointColor: "#fff",
                    pointStrokeColor: "#17ea07",
                    data: [0, 64, 128, 256, 512, 1024, 2048]
                },
                {
                    fillColor: "rgba(231,26,13,0.75)",
                    strokeColor: "#e71a0d",
                    pointColor: "#fff",
                    pointStrokeColor: "#e71a0d",
                    data: [0, 4200, 3000, 2000, 200, 500, 5]
                }
            ]
        }
        var chart = document.getElementById('graphique').getContext('2d');
        new Chart(chart).Line(chartdata);
    </script>

</html>
