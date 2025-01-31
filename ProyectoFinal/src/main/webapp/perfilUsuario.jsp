<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Animexp</title>

<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/imagenes/Gemini_Generated_Image_3majls3majls3maj.jpg"
	type="image/x-icon" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/estilo.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/estiloiniciosesion.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/estiloPerfil.css" />
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
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.2/css/all.min.css" />
</head>

<body
	style="	background:
		url('<%=request.getContextPath()%>/imagenes/artbreeder-image-2024-12-14T13_41_59.055Z.jpeg')
		no-repeat top left/cover;">
	<!-- Header completo -->
	<header>
		<div class="d-none d-md-block">
			<a href="<%=request.getContextPath()%>/"><button
					class="botonNavegador botonVolvermd">
					<i class="fas fa-arrow-left"></i> Volver
				</button></a>
		</div>
		<div class="d-block d-md-none">
			<a href="<%=request.getContextPath()%>/"><button
					class="botonNavegador botonVolversm">
					<i class="fas fa-arrow-left"></i> Volver
				</button></a>
		</div>
	</header>

	<main class="contenedorMainPerfil">
		<!--PArte de PC-->
		<div class="container contenedorPrincipalPerfil d-none d-lg-block">
			<div class="row columnaPrincipal">
				<div class="col contenedorInfoPerfil">
					<div class="encabezado">
						<!--Si esta sin imagen un imput para insertar una imagen-->
						<div class="divImagenPerfil">
							<img
								src="imagenes/artbreeder-image-2024-12-14T13_53_57.670Z.jpeg"
								alt="Foto Perfil del usuario" class="imagenUsuPerfil" />
						</div>
						<div class="divAliasPerfil">
							<h1 class="aliasPerfil">ALIAS</h1>
						</div>
					</div>
					<div class="ContenedorDatosPerfil">
						<h3 class="DatosPerfil">Nombre Completo:</h3>
						<h3 class="DatosPerfilInfo">
							Jaime Prieto Rubio
							<button class="botonModificador">
								<i class="fa-solid fa-pen"></i>
							</button>
						</h3>
					</div>
					<div class="ContenedorDatosPerfil">
						<h3 class="DatosPerfil">Teléfono Móvil:</h3>
						<h3 class="DatosPerfilInfo">
							674583437
							<button class="botonModificador">
								<i class="fa-solid fa-pen"></i>
							</button>
						</h3>
					</div>
					<div class="ContenedorDatosPerfil">
						<h3 class="DatosPerfil">Correo:</h3>
						<h3 class="DatosPerfilInfo">
							Jaime.p.r023@gmail.com
							<button class="botonModificador">
								<i class="fa-solid fa-pen"></i>
							</button>
						</h3>
					</div>
					<div class="ContenedorDatosPerfil">
						<h3 class="DatosPerfil">Tipo de Usuario:</h3>
						<h3 class="DatosPerfilInfo">Usuario Basico</h3>
					</div>
					<div class="ContenedorDatosPerfil">
						<button class="hacersePremiumBoton botonNavegador">Cerrar</button>
						<button class="ValidarUsuario botonNavegador">Validar</button>
						<button class="hacersePremiumBoton botonNavegador">Premium</button>
					</div>
				</div>
				<div class="col">
					<div class="row contenedorDosPerfil">
						<div class="col contenedorInfoPerfilDerecho contenedorGruposB">
							<!--Aqui en la parte admin se debera poner los usuario y grupos que se deban de poder modificar o eliminar-->
							<div class="trozoGrupo">
								<div class="NombreGrupoP">Nombre</div>
								<div class="categoriaGrupoP">Categoria</div>
								<div class="tematicaGrupoP d-xl-block d-none">Tematica</div>
								<div>
									<a href="#" class="verGrupoP">Ver</a>
								</div>
							</div>
						</div>
					</div>
					<div class="col contenedorInfoPerfilDerechoB">
						<div class="contenidoMensaje">
							<div class="contenidoMensaje2">¡Bienvenid@ a AnimeXP! 🌟 El
								espacio donde el anime, los videojuegos y la pasión de los fans
								se encuentran. 🎮✨ Descubre contenidos épicos, conecta con una
								comunidad increíble y vive tu afición como nunca antes. 🧡
								¡Explora, juega y siéntete como en casa! 🚀</div>
						</div>
						<div class="tiposMensaje">
							<button>Categoría</button>
							<button>Temática</button>
						</div>
					</div>
				</div>
			</div>
		</div>
		</div>

		<!--PArte de movil mini tablet-->
		<div class="container contenedorPrincipalPerfil d-block d-lg-none">
			<div class="row columnaPrincipal">
				<div class="col contenedorInfoPerfil">
					<div class="encabezado">
						<!--Si esta sin imagen un imput para insertar una imagen-->
						<div class="divImagenPerfil">
							<img
								src="imagenes/artbreeder-image-2024-12-14T13_53_57.670Z.jpeg"
								alt="Foto Perfil del usuario" class="imagenUsuPerfil" />
						</div>
						<div class="divAliasPerfil">
							<h1 class="aliasPerfil">ALIAS</h1>
						</div>
					</div>
					<div class="ContenedorDatosPerfil">
						<h3 class="DatosPerfil">Nombre Completo:</h3>
						<h3 class="DatosPerfilInfo">
							Jaime Prieto Rubio
							<button class="botonModificador">
								<i class="fa-solid fa-pen"></i>
							</button>
						</h3>
					</div>
					<div class="ContenedorDatosPerfil">
						<h3 class="DatosPerfil">Teléfono Móvil:</h3>
						<h3 class="DatosPerfilInfo">
							674583437
							<button class="botonModificador">
								<i class="fa-solid fa-pen"></i>
							</button>
						</h3>
					</div>
					<div class="ContenedorDatosPerfil">
						<h3 class="DatosPerfil">Correo:</h3>
						<h3 class="DatosPerfilInfo">
							Jaime.p.r023@gmail.com
							<button class="botonModificador">
								<i class="fa-solid fa-pen"></i>
							</button>
						</h3>
					</div>
					<div class="ContenedorDatosPerfil">
						<h3 class="DatosPerfil">Tipo de Usuario:</h3>
						<h3 class="DatosPerfilInfo">Usuario Basico</h3>
					</div>
					<div class="ContenedorDatosPerfil">
						<button class="ValidarUsuario botonNavegador">Validar</button>
						<button class="hacersePremiumBoton botonNavegador">Premium</button>
					</div>
				</div>
				<div class="row columnaPrincipalB">
					<div class="col contenedorInfoPerfilDerecho contenedorGruposB">
						<!--Aqui en la parte admin se debera poner los usuario y grupos que se deban de poder modificar o eliminar-->
						<div class="trozoGrupo">
							<div class="NombreGrupoP">Nombre</div>
							<div class="categoriaGrupoP">Categoria</div>
							<div class="tematicaGrupoP d-xl-block d-none">Tematica</div>
							<div>
								<a href="#" class="verGrupoP">Ver</a>
							</div>
						</div>
					</div>
					<div class="col contenedorInfoPerfilDerechoB">
						<div class="contenidoMensaje">
							<div class="contenidoMensaje2">¡Bienvenid@ a AnimeXP! 🌟 El
								espacio donde el anime, los videojuegos y la pasión de los fans
								se encuentran. 🎮✨ Descubre contenidos épicos, conecta con una
								comunidad increíble y vive tu afición como nunca antes. 🧡
								¡Explora, juega y siéntete como en casa! 🚀</div>
						</div>
						<div class="tiposMensaje">
							<button>Categoría</button>
							<button>Temática</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</main>
</body>
</html>
