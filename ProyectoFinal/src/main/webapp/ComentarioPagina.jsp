<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>AnimeXp</title>
<link rel="shortcut icon"
	href="<%=request.getContextPath()%>/imagenes/Gemini_Generated_Image_3majls3majls3maj.jpg"
	type="image/x-icon" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/estilo.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/estiloiniciosesion.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/estiloPerfil.css" />
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/comentario.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous" />
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css"
	rel="stylesheet" />
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
		no-repeat top left/cover;">
	<!-- Este header es para las pantallas-->
	<header class="header d-none d-md-block">
		<nav class="navegadorPrincipal">
			<div class="contenedorPrincipal">
				<div class="logo-contenedor">
					<a href="<%=request.getContextPath()%>/"><img
						class="imagenLogo"
						src="<%=request.getContextPath()%>/imagenes/Modified_Image_Pure_Black_Background.png"
						alt="Logo de la pagina web" width="200px" /> </a>
				</div>
				<!-- Columna de los botones (a la derecha) -->
				<div class="boton-contenedor">
					<a href="#"><button class="botonNavegador botonGrupo">GRUPOS</button></a>
					<a href="<%=request.getContextPath()%>/PaginaComentario"
						class="d-xl-block d-none"><button
							class="botonNavegador botonAnime">ANIMES</button></a> <a
						href="<%=request.getContextPath()%>/PaginaComentario"
						class="d-xl-block d-none"><button
							class="botonNavegador botonVideojuegos">VIDEOJUEGOS</button></a> <a
						href="<%=request.getContextPath()%>/PaginaComentario"
						class="d-xl-none d-md-block d-none"><button
							class="botonNavegador botonCategoria">COMENTARIOS</button></a>
					<!-- Aqui deberia de poner una condicion que si es admin coja una cosa y si no que haga la otra -->
					<c:if test="${Usuario == null}">
						<a href="<%=request.getContextPath()%>/InicioSesion"><button
								class="botonIconoIS">
								<i class="fas fa-user icono"></i>
							</button></a>
					</c:if>
					<c:if test="${Usuario != null}">
						<c:if test="${Usuario.rolUsu != 'user'}">
							<a href="<%=request.getContextPath()%>/PerfilUsuario"><button
									class=" botonNavegador ">ADMIN</button></a>
						</c:if>
						<c:if test="${Usuario.rolUsu == 'user'}">
							<a href="<%=request.getContextPath()%>/PerfilUsuario"><button
									class="botonNavegador ">PERFIL</button></a>
						</c:if>
					</c:if>
				</div>
			</div>
		</nav>
	</header>

	<!-- Este header es para la parte de moviles y tablest pequeñas-->
	<header class="header d-block d-md-none">
		<nav class="navegadorPrincipalS">
			<div class="contenedorPrincipal">
				<div class="logo-contenedor">
					<a href="<%=request.getContextPath()%>/"> <img
						class="imagenLogo2"
						src="<%=request.getContextPath()%>/imagenes/Modified_Image_Pure_Black_Background.png"
						alt="Logo de la pagina web" width="200px" />
					</a>
				</div>

				<!-- MENU-->
				<div class="boton-contenedor2">
					<button class="botonNavegador2 d-block d-md-none" id="menuToggle">
						<i class="fa-solid fa-bars"></i>
					</button>
					<div class="menu-desplegable" id="menuOpciones">
						<a href="#">GRUPOS</a> <a
							href="<%=request.getContextPath()%>/PaginaComentario">COMENTARIOS</a>
						<!--AQUI DEBERIA DE PONER LA CONDICION EN LA CUAL SI SE INICIA SESION PONDRA PERFIL USUARIO O PERFIL ADMINISTRADOR-->
						<c:if test="${Usuario == null}">
							<a href="<%=request.getContextPath()%>/InicioSesion">INICIAR
								SESION</a>
						</c:if>
						<c:if test="${Usuario != null}">
							<c:if test="${Usuario.rolUsu != 'user'}">
								<a href="<%=request.getContextPath()%>/PerfilUsuario">ADMIN</a>
							</c:if>
							<c:if test="${Usuario.rolUsu == 'user'}">
								<a href="<%=request.getContextPath()%>/PerfilUsuario">PERFIL</a>
							</c:if>
						</c:if>
					</div>
				</div>
			</div>
		</nav>
	</header>
	<c:set var="usuario" value="${sessionScope.Usuario}" />
	<main class="contenedorMainComentario">
		<div class="contenedorPrincipalComentario d-none d-lg-block">
			<div class="columnaPrincipalComentario">
				<div class="contenedorFiltro">
					<div class="filtrado">
						<h3>Categorías</h3>

						<div class="categorias">
							<button data-target="anime" class="active">Anime</button>
							<button data-target="videojuegos">Videojuegos</button>
							<button data-target="auxiliar">Auxiliar</button>
						</div>

						<div id="anime" class="subcategorias active">
							<h4>Subcategorías</h4>
							<ul>
								<li><a href="#">Shonen</a></li>
								<li><a href="#">Isekai</a></li>
								<li><a href="#">Shojo</a></li>
							</ul>
						</div>

						<div id="videojuegos" class="subcategorias">
							<h4>Subcategorías</h4>
							<ul>
								<li><a href="#">Shooters</a></li>
								<li><a href="#">Aventuras</a></li>
								<li><a href="#">Deportes</a></li>
							</ul>
						</div>

						<div id="auxiliar" class="subcategorias">
							<h4>Subcategorías</h4>
							<ul>
								<li><a href="#">Auxiliar</a></li>
							</ul>
						</div>
					</div>
					<div class="nuevoComentario">
						<button class="publicar" id="publicar">Publicar</button>
					</div>
				</div>

				
					<div class="contenedorComentario">
					<c:forEach var="comentario" items="${listadoComentarios}">
						<c:choose>
							<c:when test="${comentario.idUsuario == usuario.idUsu}">
								<div class="miContenido">
									<div class="autor">${comentario.aliasUsuarioComentario}</div>
									<div class="contenido">${comentario.comentarioTexto}</div>
									<div class="tematicaComentario">
										<div class="cat">${comentario.categoriaTipo}</div>
										<div class="subCat">${comentario.subCategoriaTipo}</div>
										<div class="subCat">${comentario.grupoComentario}</div>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="contenidoOtros">
									<div class="autor">${comentario.aliasUsuarioComentario}</div>
									<div class="contenido">${comentario.comentarioTexto}</div>
									<div class="tematicaComentario">
										<div class="cat">${comentario.categoriaTipo}</div>
										<div class="subCat">${comentario.subCategoriaTipo}</div>
										<div class="subCat">${comentario.grupoComentario}</div>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
				
			</div>
		</div>
		<div class="contenedorPrincipalComentario d-lg-none d-block">
			<div class="columnaPrincipalComentario2">
				<div class="contenedorFiltro2">
					<div class="filtroHorizontal">
						<div class="grupoSelect">
							<div class="selectorGrupo">
								<label for="categoriaFiltro">Categoría:</label> <select
									id="categoriaFiltro">
									<option value="anime">Anime</option>
									<option value="videojuegos">Videojuegos</option>
									<option value="auxiliar">Auxiliar</option>
								</select>
							</div>

							<div class="selectorGrupo">
								<label for="subcategoriaFiltro">Subcategoría:</label> <select
									id="subcategoriaFiltro">
									<!-- Se llenará dinámicamente -->
								</select>
							</div>
						</div>

						<button class="publicar2 d-none d-sm-block">Publicar</button>
					</div>
				</div>
					<div class="contenedorComentario2">
					<c:forEach var="comentario" items="${listadoComentarios}">
						<c:choose>
							<c:when test="${comentario.idUsuario == usuario.idUsu}">
								<div class="miContenido">
									<div class="autor">${comentario.aliasUsuarioComentario}</div>
									<div class="contenido">${comentario.comentarioTexto}</div>
									<div class="tematicaComentario">
										<div class="cat">${comentario.categoriaTipo}</div>
										<div class="subCat">${comentario.subCategoriaTipo}</div>
										<div class="subCat">${comentario.grupoComentario}</div>
									</div>
								</div>
							</c:when>
							<c:otherwise>
								<div class="contenidoOtros">
									<div class="autor">${comentario.aliasUsuarioComentario}</div>
									<div class="contenido">${comentario.comentarioTexto}</div>
									<div class="tematicaComentario">
										<div class="cat">${comentario.categoriaTipo}</div>
										<div class="subCat">${comentario.subCategoriaTipo}</div>
										<div class="subCat">${comentario.grupoComentario}</div>
									</div>
								</div>
							</c:otherwise>
						</c:choose>
					</c:forEach>
				</div>
				

				<button class="publicar3 d-sm-none d-block">Publicar</button>
			</div>
		</div>
	</main>
	<script src="<%=request.getContextPath()%>/js/javaScript.js"></script>
	<script src="<%=request.getContextPath()%>/js/javaScriptComentarios.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>