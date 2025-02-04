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
		<c:set var="usuario" value="${sessionScope.Usuario}" />
		<!--PArte de PC-->
		<div class="container contenedorPrincipalPerfil d-none d-lg-block">
			<div class="row columnaPrincipal">
				<div class="col contenedorInfoPerfil">
					<div class="encabezado">
						<!--Si esta sin imagen un imput para insertar una imagen-->
						<div class="divImagenPerfil">

							<c:choose>
								<c:when test="${not empty usuario.fotoUsu}">
									<img src="<c:out value='${usuario.fotoUsu}' />"
										alt="Foto Perfil del usuario" class="imagenUsuPerfil" />
									<button class="botonModificador" type="submit">
										<i class="fa-solid fa-pen"></i>
									</button>
								</c:when>
								<c:otherwise>
									<!-- Mostrar un formulario si no hay imagen -->
									<form action="" method="post" enctype="multipart/form-data"
										class="formularioFoto">
										<input type="file" name="fotoUsu" id="fotoUsu" required
											class="fotoUsu" /> <label for="fotoUsu" class="btn-file">Seleccionar
											archivo</label>
										<button type="submit" class=" botonSubir botonNavegador">Subir</button>
									</form>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="divAliasPerfil">
							<h1 class="aliasPerfil">
								<c:out value="${usuario.aliasUsu}" default="ALIAS" />
							</h1>
						</div>
						<button type="submit" class="botonModificador">
							<i class="fa-solid fa-pen"></i>
						</button>
					</div>
					<div class="ContenedorCompleto">
						<div class="ContenedorDatosPerfil">
							<h3 class="DatosPerfil">Nombre Completo:</h3>
							<h3 class="DatosPerfilInfo">
								<c:out value="${usuario.nombreCompletoUsu}" default="" />
								<button class="botonModificador" type="submit">
									<i class="fa-solid fa-pen"></i>
								</button>
							</h3>
						</div>
						<div class="ContenedorDatosPerfil">
							<h3 class="DatosPerfil">Teléfono Móvil:</h3>
							<h3 class="DatosPerfilInfo">
								<c:out value="${usuario.movilUsu}" default="" />
								<button class="botonModificador">
									<i class="fa-solid fa-pen"></i>
								</button>
							</h3>
						</div>
						<div class="ContenedorDatosPerfil">
							<h3 class="DatosPerfil">Correo:</h3>
							<h3 class="DatosPerfilInfo">
								<c:out value="${usuario.correoElectronicoUsu}" default="" />
							</h3>
						</div>
						<div class="ContenedorDatosPerfil">
							<h3 class="DatosPerfil">Tipo de Usuario:</h3>
							<h3 class="DatosPerfilInfo">
								<c:choose>
									<c:when test="${usuario.esPremium}">
                Usuario Premium
            </c:when>
									<c:otherwise>
                Usuario Básico
            </c:otherwise>
								</c:choose>
							</h3>
						</div>
						<div class="ContenedorDatosPerfil">
							<a href="<%=request.getContextPath()%>/CerrarSesion"><button
									class="hacersePremiumBoton botonNavegador">Cerrar</button></a>
							<c:if test="${usuario.esVerificadoEntidad==false}">
								<button class="ValidarUsuario botonNavegador">Validar</button>
							</c:if>
							<button class="hacersePremiumBoton botonNavegador"
								onclick="alertaDelPremium()">Premium</button>
						</div>
					</div>

				</div>
				<div class="col">
					<div class="row contenedorDosPerfil">
						<div class="col contenedorInfoPerfilDerecho contenedorGruposB">
							<!--Aqui en la parte admin se debera poner los usuario y grupos que se deban de poder modificar o eliminar-->
							<c:if test="${usuario.rolUsu == 'user'}">
								<c:choose>
									<c:when test="${empty listadoGruposUsuario}">
										<div class="mensajeGrupo">
											<c:out value="No se encontraron grupos disponibles." />
										</div>
										<div class="tiposMensaje">
											<button>Crear Grupo</button>
										</div>
									</c:when>
									<c:otherwise>
										<c:forEach var="grupo" items="${listadoGruposUsuario}">
											<div class="trozoGrupo">
												<div class="NombreGrupo">
													<c:out value="${grupo.nombreGrupo}" />
												</div>
												<div class="categoriaGrupo">
													<c:out value="${grupo.categoriaNombre}" />
												</div>
												<div class="tematicaGrupo">
													<c:out value="${grupo.subCategoriaNombre}" />
												</div>
												<div>
													<a href="#" class="verGrupo">Ver</a>
												</div>
											</div>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</c:if>
							<c:if test="${usuario.rolUsu == 'admin'}">
							</c:if>

						</div>
					</div>
					<div class="col contenedorInfoPerfilDerechoB">
						<div class="contenidoMensaje">
							<div class="contenidoMensaje2">
								<c:choose>
									<c:when
										test="${not empty comentario and not empty comentario.comentarioTexto}">
                    ${comentario.comentarioTexto}
                </c:when>
									<c:otherwise>
                    No se encontraron comentarios para este usuario.
                </c:otherwise>
								</c:choose>
							</div>
						</div>
						<c:choose>
							<c:when
								test="${not empty comentario and not empty comentario.comentarioTexto}">
								<div class="tiposMensaje">
									<button>${comentario.categoriaTipo}</button>
									<button>${comentario.subCategoriaTipo}</button>
								</div>
							</c:when>
							<c:otherwise>
								<div class="tiposMensaje">
									<button>Crear</button>
								</div>
							</c:otherwise>
						</c:choose>
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

							<c:choose>
								<c:when test="${not empty usuario.fotoUsu}">
									<img src="<c:out value='${usuario.fotoUsu}' />"
										alt="Foto Perfil del usuario" class="imagenUsuPerfil" />
								</c:when>
								<c:otherwise>
									<!-- Mostrar un formulario si no hay imagen -->
									<form action="" method="post" enctype="multipart/form-data"
										class="formularioFoto">
										<input type="file" name="fotoUsu" id="fotoUsu" required
											class="fotoUsu" /> <label for="fotoUsu" class="btn-file">Seleccionar
											archivo</label>
										<button type="submit" class=" botonSubir botonNavegador">Subir</button>
									</form>
								</c:otherwise>
							</c:choose>
						</div>
						<div class="divAliasPerfil">
							<h1 class="aliasPerfil">
								<c:out value="${usuario.aliasUsu}" default="ALIAS" />
							</h1>
						</div>
						<button type="submit" class="botonModificador">
							<i class="fa-solid fa-pen"></i>
						</button>
					</div>
					<div class="ContenedorCompleto">
						<div class="ContenedorDatosPerfilB">
							<h3 class="DatosPerfil2">Nombre Completo:</h3>
							<h3 class="DatosPerfilInfo2">
								<c:out value="${usuario.nombreCompletoUsu}" default="" />
								<button class="botonModificador">
									<i class="fa-solid fa-pen"></i>
								</button>
							</h3>
						</div>
						<div class="ContenedorDatosPerfilB">
							<h3 class="DatosPerfil2">Teléfono Móvil:</h3>
							<h3 class="DatosPerfilInfo2">
								<c:out value="${usuario.movilUsu}" default="" />
								<button class="botonModificador">
									<i class="fa-solid fa-pen"></i>
								</button>
							</h3>
						</div>
						<div class="ContenedorDatosPerfilB">
							<h3 class="DatosPerfil2">Correo:</h3>
							<h3 class="DatosPerfilInfo2">
								<c:out value="${usuario.correoElectronicoUsu}" default="" />
							</h3>
						</div>
						<div class="ContenedorDatosPerfilB">
							<h3 class="DatosPerfil2">Tipo de Usuario:</h3>
							<h3 class="DatosPerfilInfo2">
								<c:choose>
									<c:when test="${usuario.esPremium}">
                Usuario Premium
            </c:when>
									<c:otherwise>
                Usuario Básico
            </c:otherwise>
								</c:choose>
							</h3>
						</div>
						<div class="ContenedorDatosPerfil2">
							<div class="divBoton">
								<c:if test="${usuario.esVerificadoEntidad==false}">
									<button class="ValidarUsuario2 botonNavegador">Validar</button>
								</c:if>
							</div>
							<div class="divBoton">
								<button class="hacersePremiumBoton2 botonNavegador"
									onclick="alertaDelPremium()">Premium</button>
							</div>
							<div class="divBoton">
								<a href="<%=request.getContextPath()%>/CerrarSesion"><button
										class="hacersePremiumBoton2 botonNavegador">Cerrar</button></a>
							</div>
						</div>
					</div>
				</div>
				<div class="row columnaPrincipalB">
					<div class="col contenedorInfoPerfilDerecho contenedorGruposB">
						<!--Aqui en la parte admin se debera poner los usuario y grupos que se deban de poder modificar o eliminar-->
						<c:if test="${usuario.rolUsu == 'user'}">
							<c:choose>
								<c:when test="${empty listadoGruposUsuario}">
									<div class="mensajeGrupo">
										<c:out value="No se encontraron grupos disponibles." />
									</div>
									<div class="tiposMensaje">
										<button>Crear Grupo</button>
									</div>
								</c:when>
								<c:otherwise>
									<c:forEach var="grupo" items="${listadoGruposUsuario}">
										<div class="trozoGrupo">
											<div class="NombreGrupo">
												<c:out value="${grupo.nombreGrupo}" />
											</div>
											<div class="categoriaGrupo">
												<c:out value="${grupo.categoriaNombre}" />
											</div>
											<div class="tematicaGrupo">
												<c:out value="${grupo.subCategoriaNombre}" />
											</div>
											<div>
												<a href="#" class="verGrupo">Ver</a>
											</div>
										</div>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</c:if>
						<c:if test="${usuario.rolUsu == 'admin'}">
						</c:if>
					</div>
					<div class="col contenedorInfoPerfilDerechoB">
						<div class="contenidoMensaje">
							<div class="contenidoMensaje2">
								<c:choose>
									<c:when
										test="${not empty comentario and not empty comentario.comentarioTexto}">
                    ${comentario.comentarioTexto}
                </c:when>
									<c:otherwise>
                    No se encontraron comentarios para este usuario.
                </c:otherwise>
								</c:choose>
							</div>
						</div>
						<c:choose>
							<c:when
								test="${not empty comentario and not empty comentario.comentarioTexto}">
								<div class="tiposMensaje">
									<button>${comentario.categoriaTipo}</button>
									<button>${comentario.subCategoriaTipo}</button>
								</div>
							</c:when>
							<c:otherwise>
								<div class="tiposMensaje">
									<button>Crear</button>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>

		<!-- Modal para editar usuario -->
		<%-- 		<div class="modal fade" id="editarDatosModal" tabindex="-1"
			aria-labelledby="editarDatosModalLabel" aria-hidden="true">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<h5 class="modal-title" id="editarDatosModalLabel">Editar
							Información</h5>
						<button type="button" class="btn-close" data-bs-dismiss="modal"
							aria-label="Close"></button>
					</div>
					<div class="modal-body">
						<form id="formEditarDatos" method="post"
							action="${pageContext.request.contextPath}/actualizarDatos">
							<input type="hidden" id="campoEditar" name="campoEditar">
							<!-- Nombre del campo -->
							<div class="mb-3">
								<label id="labelCampo" class="form-label"></label> <input
									type="text" class="form-control" id="valorEditar"
									name="valorEditar" required>
							</div>
							<button type="submit" class="btn btn-primary">Guardar
								Cambios</button>
						</form>
					</div>
				</div>
			</div>
		</div> --%>
	</main>
	<script src="<%=request.getContextPath()%>/js/javaScriptPerfil.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>
