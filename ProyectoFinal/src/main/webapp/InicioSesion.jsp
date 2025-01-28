<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>AnimeXp</title>
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/imagenes/Gemini_Generated_Image_3majls3majls3maj.jpg"
	type="image/x-icon" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/estilo.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/estiloiniciosesion.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous" />
<link
	href="https://fonts.googleapis.com/css2?family=Bangers&display=swap"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap"
	rel="stylesheet" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
	rel="stylesheet" />
</head>
<body
	style="	background:
		url('<%=request.getContextPath()%>/imagenes/artbreeder-image-2024-12-14T13_41_59.055Z.jpeg')
		no-repeat top left/cover">
	<!-- Header completo -->
	<header>
		<div class="d-none d-md-block">
			<a href="<%=request.getContextPath()%>/LandinPage.jsp"><button
					class="botonNavegador botonVolvermd">
					<i class="fas fa-arrow-left"></i> Volver
				</button></a>
		</div>
		<div class="d-block d-md-none">
			<a href="<%=request.getContextPath()%>/LandinPage.jsp"><button
					class="botonNavegador botonVolversm">
					<i class="fas fa-arrow-left"></i> Volver
				</button></a>
		</div>
	</header>
	<!-- Main completo -->
	<main class="contenedorMain">
		<div class="d-none d-md-block zonaCompleta">
			<div class="container" id="container">
				<div class="formularioContenedor sign-up-container">
					<form action="${pageContext.request.contextPath}/Registro"
						method="post">
						<h2 class="registroTitulo">Registrarse</h2>
						<input type="text" placeholder="Nombre Completo"
							name="nombreCompletoUsu"
							value="${nuevoUsuario.nombreCompletoUsu}" required /> <input
							type="text" placeholder="Alias" name="aliasUsu"
							value="${nuevoUsuario.aliasUsu}" required /> <input type="email"
							placeholder="Correo electrónico" name="correoElectronicoUsu"
							value="${nuevoUsuario.correoElectronicoUsu}" required /> <input
							type="password" placeholder="Contraseña" name="contraseniaUsu"
							value="${nuevoUsuario.contraseniaUsu}" required /> <input
							type="password" placeholder="Repetir contraseña" />
						<div class="debajoDeLosInputs">
							<div class="social-contenedor2">
								<a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
							</div>
							<button type="submit" class="botonNavegador botonRegistro">
								Registrarse</button>
						</div>
					</form>
				</div>

				<div class="formularioContenedor sign-in-container">
					<form action="#">
						<h2 class="inicioSesionTitulo">Inicio sesion</h2>
						<input type="email" placeholder="Correo electrónico" /> <input
							type="password" placeholder="Contraseña" />
						<div class="debajoDeLosInputs">
							<div class="social-contenedor">
								<a href="#" class="social"><i class="fab fa-google"></i></a>
							</div>
							<a href="#">¿Has olvidado tu contraseña?</a>
						</div>
						<button class="botonNavegador botonIS1">Iniciar Sesion</button>
					</form>
				</div>

				<div class="overlay-container">
					<div class="overlay">
						<div class="overlay-panel overlay-left">
							<h2 class="titulo">Bienvenido</h2>
							<p>Inicia sesión con tu cuenta</p>
							<button class="botonNavegador botonIS1" id="signIn">Iniciar
								sesion</button>
						</div>
						<div class="overlay-panel overlay-right">
							<h2 class="titulo">¿Eres nuevo?</h2>
							<p>Unete a esta gran familia</p>
							<button class="botonNavegador" id="signUp">Registrarse</button>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="d-block d-md-none contenedorIsMV">
			<div class="form-container capacidadMinima" id="formContainer">
				<div class="form-inner">
					<!-- Formulario Frontal -->
					<form action="#" class="form formsm">
						<h2 class="inicioSesionTitulo1">Inicio sesion</h2>
						<input type="email"
							placeholder="C o r r e o  e l e c t r ó n i c o" /> <input
							type="password" placeholder="C o n t r a s e ñ a" />
						<div class="debajoDeLosInputs1">
							<div class="social-contenedor">
								<a href="#" class="social1"><i class="fab fa-google"></i></a>
							</div>
							<a href="#" class="olvidoContra">¿Has olvidado tu contraseña?</a>
						</div>
						<button class="botonNavegador botonIS1">Iniciar Sesion</button>
						<button type="button" onclick="flipForm()"
							class="botonFlip botonNavegador">Registrase</button>
					</form>
					<!-- Formulario Trasero -->
					<form action="${pageContext.request.contextPath}/Registro"
						method="post" class="form1 form-back formsm">
						<h2 class="registroTitulo1">Registrarse</h2>
						<input type="text" placeholder="N o m b r e  c o m p l e t o"
							name="nombreCompletoUsu"
							value="${nuevoUsuario.nombreCompletoUsu}" required /> <input
							type="text" placeholder="A l i a s" name="aliasUsu"
							value="${nuevoUsuario.aliasUsu}" required /> <input type="email"
							placeholder="C o r r e o  e l e c t r ó n i c o"
							name="correoElectronicoUsu"
							value="${nuevoUsuario.correoElectronicoUsu}" required /> <input
							type="password" placeholder="C o n t r a s e ñ a"
							name="contraseniaUsu" value="${nuevoUsuario.contraseniaUsu}"
							required /> <input type="password"
							placeholder="R e p e t i r  c o n t r a s e ñ a" />
						<div class="social-contenedor3">
							<a href="#" class="social"><i class="fab fa-google-plus-g"></i></a>
						</div>
						<div class="debajoDeLosInputs2">
							<button class="botonNavegador botonRegistro">
								Registrarse</button>
							<button type="button" onclick="flipForm()"
								class="botonFlip botonNavegador botonIS1">inicio Sesion</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</main>
	<script
		src="<%=request.getContextPath()%>/js/javascriptiniciosesion.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>
