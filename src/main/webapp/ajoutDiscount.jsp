<!DOCTYPE html>

<%@page contentType="text/html" pageEncoding="UTF-8" session="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
	<title>Webmarket : connexion</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<!--<meta name="viewport" content="width=device-width, initial-scale=1">-->
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="ressources/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="ressources/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="ressources/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="ressources/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="ressources/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="ressources/vendor/select2/select2.min.css">
<!--===============================================================================================-->
        <link rel="stylesheet" type="text/css" href="ressources/css/main.css">
	<!-- <link rel="stylesheet" type="text/css" href="ressources/css/util.css"> -->

<!--===============================================================================================-->
</head>
<body>
    <section class="connexion container  z-depth-4">
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100">
				<div class="login100-pic js-tilt" data-tilt>
					<img src="ressources/images/img-01.png" alt="IMG">
				</div>

				<form class="col s12 login-form" action="<c:url value="LoginController" />" method="POST">
					<span class="login100-form-title">
						Espace de connexion
					</span>

					<div class="wrap-input100 validate-input" data-validate = "Précisez un email valide : ex@abc.xyz">
						<input class="input100" type="text"  placeholder="Adresse mail" name="uname" required>
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-envelope" aria-hidden="true"></i>
						</span>
					</div>

					<div class="wrap-input100 validate-input" data-validate = "Un mot de passe est requis.">
						<input class="input100" type="password"  placeholder="Mot de passe"  name="psw" required>
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock" aria-hidden="true"></i>
						</span>
					</div>
					
					<div class="container-login100-form-btn">
						<button class="login100-form-btn" type="submit" name="action" value="login">
							Se connecter
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	

	
<!--===============================================================================================-->	
	<script src="ressources/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="ressources/vendor/bootstrap/js/popper.js"></script>
	<script src="ressources//bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="ressources/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="ressources/vendor/tilt/tilt.jquery.min.js"></script>
	<script >
		$('.js-tilt').tilt({
			scale: 1.1
		})
	</script>
<!--===============================================================================================-->
</section>
</body>
</html>
