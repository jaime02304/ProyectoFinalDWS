<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page isELIgnored="false"%>
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
										alt="Foto Perfil del usuario" class="imagenUsuPerfil">
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
						<button type="submit" class="botonModificador"
							onclick="openFormularioModal()">
							<i class="fa-solid fa-pen"></i>
						</button>
					</div>
					<div class="ContenedorCompleto">
						<div class="ContenedorDatosPerfil">
							<h3 class="DatosPerfil">Nombre Completo:</h3>
							<h3 class="DatosPerfilInfo">
								<c:out value="${usuario.nombreCompletoUsu}" default="" />

							</h3>
						</div>
						<div class="ContenedorDatosPerfil">
							<h3 class="DatosPerfil">Teléfono Móvil:</h3>
							<h3 class="DatosPerfilInfo">
								<c:out value="${usuario.movilUsu}" default="" />
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
				<div class="col" style="height: 100%;">
					<div class="row contenedorDosPerfil">
						<div class="col contenedorInfoPerfilDerecho contenedorGruposB">
							<h3>Grupos</h3>
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
											<div class="trozoGrupo" style="height: auto;">
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
							<c:if
								test="${usuario.rolUsu == 'admin' or usuario.rolUsu == 'sadmin'}">
								<c:choose>
									<c:when test="${empty listadoGruposAdmin}">
										<div class="mensajeGrupo">
											<c:out value="No se encontraron grupos disponibles." />
										</div>
										<div class="tiposMensaje">
											<button>Crear Grupo</button>
										</div>
									</c:when>
									<c:otherwise>
										<c:forEach var="grupo" items="${listadoGruposAdmin}">
											<div class="trozoGrupo" style="height: auto;">
												<div class="NombreGrupo">
													<c:out value="${grupo.nombreGrupo}" />
												</div>
												<div>
													<a class="verGrupo2"
														onclick="openModificacionGrupoModal('${grupo.idGrupo}', '${grupo.nombreGrupo}', '${grupo.categoriaNombre}', '${grupo.subCategoriaNombre}')">Modificar</a>
												</div>
												<div>
													<a class="verGrupo2"
														onclick="openEliminacionModal('${grupo.idGrupo}', '${grupo.nombreGrupo}', ${false})">Borrar</a>
												</div>
											</div>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</c:if>
						</div>


						<c:if test="${ usuario.rolUsu == 'user'}">
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
						</c:if>
						<c:if test="${usuario.rolUsu == 'admin'}">
							<div class="col contenedorInfoPerfilDerecho contenedorGruposB">
								<h3>Usuarios</h3>
								<c:choose>
									<c:when test="${empty listadoUsuariosAdmin}">
										<div class="mensajeGrupo">
											<c:out value="No se encontraron grupos disponibles." />
										</div>
										<div class="tiposMensaje">
											<button>Crear Usuario</button>
										</div>
									</c:when>
									<c:otherwise>
										<c:forEach var="usuario" items="${listadoUsuariosAdmin}">
											<div class="trozoGrupo" style="height: auto;">
												<div class="NombreGrupo">
													<c:out value="${usuario.aliasUsu}" />
												</div>
												<div>
													<a class="verGrupo2"
														onclick="openModificacionModal('${usuario.idUsu}', '${usuario.nombreCompletoUsu}', '${usuario.aliasUsu}', '${usuario.correoElectronicoUsu}', '${usuario.movilUsu}', ${usuario.esPremium}, '${usuario.rolUsu}', ${usuario.esVerificadoEntidad})">Modificar</a>
												</div>
												<div>
													<a class="verGrupo2"
														onclick="openEliminacionModal('${usuario.idUsu}', '${usuario.aliasUsu}' , ${true})">Borrar</a>
												</div>
											</div>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div>
						</c:if>

						<c:if test="${usuario.rolUsu == 'sadmin'}">
							<div class="col contenedorInfoPerfilDerecho contenedorGruposB">
								<h3>Usuarios</h3>
								<c:choose>
									<c:when test="${empty listadoUsuariosSAdmin}">
										<div class="mensajeGrupo">
											<c:out value="No se encontraron grupos disponibles." />
										</div>
										<div class="tiposMensaje">
											<button>Crear Usuario</button>
										</div>
									</c:when>
									<c:otherwise>
										<c:forEach var="usuario" items="${listadoUsuariosSAdmin}">
											<div class="trozoGrupo" style="height: auto;">
												<div class="NombreGrupo">
													<c:out value="${usuario.aliasUsu}" />
												</div>
												<div>
													<a class="verGrupo2"
														onclick="openModificacionModal('${usuario.idUsu}', '${usuario.nombreCompletoUsu}', '${usuario.aliasUsu}', '${usuario.correoElectronicoUsu}', '${usuario.movilUsu}', ${usuario.esPremium}, '${usuario.rolUsu}', ${usuario.esVerificadoEntidad})">Modificar</a>
												</div>
												<div>
													<a class="verGrupo2"
														onclick="openEliminacionModal('${usuario.idUsu}', '${usuario.aliasUsu}' , ${true})">Borrar</a>
												</div>
											</div>
										</c:forEach>
									</c:otherwise>
								</c:choose>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>

		<!--PArte de movil mini tablet-->
		<c:set var="usuario" value="${sessionScope.Usuario}" />
		<div class="container contenedorPrincipalPerfil d-block d-lg-none">
			<div class="row columnaPrincipal">
				<div class="col contenedorInfoPerfil">
					<div class="encabezado">
						<!--Si esta sin imagen un imput para insertar una imagen-->
						<div class="divImagenPerfil">

							<c:choose>
								<c:when test="${not empty usuario.fotoUsu}">
									<img src="<c:out value='${usuario.fotoUsu}' />"
										alt="Foto Perfil del usuario" class="imagenUsuPerfil">
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
						<button type="submit" class="botonModificador"
							onclick="openFormularioModal()">
							<i class="fa-solid fa-pen"></i>
						</button>
					</div>
					<div class="ContenedorCompleto">
						<div class="ContenedorDatosPerfilB">
							<h3 class="DatosPerfil2">Nombre Completo:</h3>
							<h3 class="DatosPerfilInfo2">
								<c:out value="${usuario.nombreCompletoUsu}" default="" />
							</h3>
						</div>
						<div class="ContenedorDatosPerfilB">
							<h3 class="DatosPerfil2">Teléfono Móvil:</h3>
							<h3 class="DatosPerfilInfo2">
								<c:out value="${usuario.movilUsu}" default="" />
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
								<a href="<%=request.getContextPath()%>/CerrarSesion"><button
										class="hacersePremiumBoton2 botonNavegador">Cerrar</button></a>
							</div>
							<div class="divBoton">
								<button class="hacersePremiumBoton2 botonNavegador"
									onclick="alertaDelPremium()">Premium</button>
							</div>

							<div class="divBoton">
								<c:if test="${usuario.esVerificadoEntidad==false}">
									<button class="ValidarUsuario2 botonNavegador">Validar</button>
								</c:if>
							</div>
						</div>
					</div>
				</div>
				<div class="row columnaPrincipalB">
					<div class="col contenedorInfoPerfilDerecho contenedorGruposB">
						<h3>Grupos</h3>
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
										<div class="trozoGrupo" style="height: auto;">
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
						<c:if
							test="${usuario.rolUsu == 'admin' or usuario.rolUsu == 'sadmin'}">
							<c:choose>
								<c:when test="${empty listadoGruposAdmin}">
									<div class="mensajeGrupo">
										<c:out value="No se encontraron grupos disponibles." />
									</div>
									<div class="tiposMensaje">
										<button>Crear Grupo</button>
									</div>
								</c:when>
								<c:otherwise>
									<c:forEach var="grupo" items="${listadoGruposAdmin}">
										<div class="trozoGrupo" style="height: auto;">
											<div class="NombreGrupo">
												<c:out value="${grupo.nombreGrupo}" />
											</div>
											<div>
												<a class="verGrupo2"
													onclick="openModificacionGrupoModal('${grupo.idGrupo}', '${grupo.nombreGrupo}', '${grupo.categoriaNombre}', '${grupo.subCategoriaNombre}')">Modificar</a>
											</div>
											<div>
												<a class="verGrupo2"
													onclick="openEliminacionModal('${grupo.idGrupo}', '${grupo.nombreGrupo}', ${false})">Borrar</a>
											</div>
										</div>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</c:if>
					</div>
					<c:if test="${ usuario.rolUsu == 'user'}">
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
					</c:if>
					<c:if test="${usuario.rolUsu == 'admin'}">
						<div class="col contenedorInfoPerfilDerecho contenedorGruposB">
							<h3>Usuarios</h3>
							<c:choose>
								<c:when test="${empty listadoUsuariosAdmin}">
									<div class="mensajeGrupo">
										<c:out value="No se encontraron grupos disponibles." />
									</div>
									<div class="tiposMensaje">
										<button>Crear Usuario</button>
									</div>
								</c:when>
								<c:otherwise>
									<c:forEach var="usuario" items="${listadoUsuariosAdmin}">
										<div class="trozoGrupo" style="height: auto;">
											<div class="NombreGrupo">
												<c:out value="${usuario.aliasUsu}" />
											</div>
											<div>
												<a class="verGrupo2"
													onclick="openModificacionModal('${usuario.idUsu}', '${usuario.nombreCompletoUsu}', '${usuario.aliasUsu}', '${usuario.correoElectronicoUsu}', '${usuario.movilUsu}', ${usuario.esPremium}, '${usuario.rolUsu}', ${usuario.esVerificadoEntidad})">Modificar</a>
											</div>
											<div>
												<a class="verGrupo2"
													onclick="openEliminacionModal('${usuario.idUsu}', '${usuario.aliasUsu}', ${true})">Borrar</a>
											</div>
										</div>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>
					</c:if>

					<c:if test="${usuario.rolUsu == 'sadmin'}">
						<div class="col contenedorInfoPerfilDerecho contenedorGruposB">
							<h3>USuarios</h3>
							<c:choose>
								<c:when test="${empty listadoUsuariosSAdmin}">
									<div class="mensajeGrupo">
										<c:out value="No se encontraron grupos disponibles." />
									</div>
									<div class="tiposMensaje">
										<button>Crear Usuario</button>
									</div>
								</c:when>
								<c:otherwise>
									<c:forEach var="usuario" items="${listadoUsuariosSAdmin}">
										<div class="trozoGrupo" style="height: auto;">
											<div class="NombreGrupo">
												<c:out value="${usuario.aliasUsu}" />
											</div>
											<div>
												<a class="verGrupo2"
													onclick="openModificacionModal('${usuario.idUsu}', '${usuario.nombreCompletoUsu}', '${usuario.aliasUsu}', '${usuario.correoElectronicoUsu}', '${usuario.movilUsu}', ${usuario.esPremium}, '${usuario.rolUsu}', ${usuario.esVerificadoEntidad})">Modificar</a>
											</div>
											<div>
												<a class="verGrupo2"
													onclick="openEliminacionModal('${usuario.idUsu}', '${usuario.aliasUsu}', ${true})">Borrar</a>
											</div>
										</div>
									</c:forEach>
								</c:otherwise>
							</c:choose>
						</div>
					</c:if>
				</div>
			</div>
		</div>

		<!-- MODAL PARA LA ELIMINACION DE ELEMENTOS -->
		<div id="formularioEliminacionModal" class="modal">
			<div class="contenidoModal2">
				<span class="close" onclick="closeEliminacionModal()">&times;</span>
				<h2 id="modalTitulo" style="word-wrap: break-word; width: 90%;">Eliminar
				</h2>
				<form id="formularioDatosModal" enctype="multipart/form-data"
					onsubmit="enviarEliminacion(event)">
					<input type="hidden" id="elementoAEliminar" name="modal" /> <input
						type="hidden" id="idElementoAEliminar" name="modal" /> <input
						type="hidden" id="esUsuarioElementoAEliminar" name="modal" />
					<div>
						<button type="submit" class="manga-button">Si</button>
					</div>
				</form>
			</div>
		</div>

		<!-- MODAL PARA LA MODIFICACIÓN DE USUARIO -->
		<div id="formularioModificacionModal" class="modal">
			<div class="contenidoModal2">
				<span class="close" onclick="closeModificacionModal()">&times;</span>
				<h2 id="modalTituloMod" style="word-wrap: break-word; width: 90%;">Modificar
					Usuario</h2>
				<form id="formularioModificacionDatosModal"
					enctype="multipart/form-data" onsubmit="enviarModificacion(event)">
					<!-- Campo oculto para el id del usuario -->
					<input type="hidden" id="idUsu" name="idUsu" />

					<div>
						<label for="nombreCompletoUsu">Nombre Completo</label> <input
							type="text" id="nombreCompletoUsu" name="nombreCompletoUsu"
							placeholder="Nombre completo" required />
					</div>
					<div>
						<label for="aliasUsu">Alias</label> <input type="text"
							id="aliasUsu" name="aliasUsu" placeholder="Alias" required />
					</div>
					<div>
						<input type="hidden" id="correoElectronicoUsu"
							name="correoElectronicoUsu" placeholder="correo@ejemplo.com"
							required />
					</div>
					<div>
						<label for="movilUsu">Móvil</label> <input type="number"
							id="movilUsu" name="movilUsu" placeholder="Número de móvil"
							required />
					</div>
					<div>
						<label for="fotoUsu">Foto</label> <input type="file" id="fotoUsu"
							name="fotoUsu" accept="image/*" />
					</div>
					<div
						style="display: flex; justify-content: center; align-items: center;">
						<div
							style="display: flex; justify-content: center; align-items: center; margin: 10px; flex-direction: column;">
							<label for="esPremium">Cuenta Premium</label> <input
								type="checkbox" id="esPremium" name="esPremium" />
						</div>
						<div
							style="display: flex; justify-content: center; align-items: center; margin: 10px; flex-direction: column;">
							<label for="esVerificadoEntidad">Verificada</label> <input
								type="checkbox" id="esVerificadoEntidad"
								name="esVerificadoEntidad" />
						</div>
					</div>
					<div
						style="display: flex; justify-content: center; align-items: center;">
						<label for="rolUsu">Rol de Usuario</label> <select id="rolUsu"
							name="rolUsu">
							<option value="user">User</option>
							<option value="admin">Admin</option>
							<!-- Puedes agregar más opciones según tus necesidades -->
						</select>
					</div>

					<div>
						<button type="submit" class="manga-button">Enviar</button>
					</div>
				</form>
			</div>
		</div>

		<!-- MODAL PARA LA MODIFICACIÓN DE GRUPOS -->
		<div id="formularioModificacionGrupoModal" class="modal">
			<div class="contenidoModal2">
				<span class="close" onclick="closeModificacionGrupoModal()">&times;</span>
				<h2 id="modalTituloGrupo" style="word-wrap: break-word; width: 90%;">Modificar
					Grupo</h2>
				<form id="formularioModificacionGrupo"
					onsubmit="enviarModificacionGrupo(event)">
					<input type="hidden" id="idGrupo" name="idGrupo" />

					<div>
						<label for="nombreGrupo">Nombre del Grupo</label> <input
							type="text" id="nombreGrupo" name="nombreGrupo"
							placeholder="Nombre del grupo" required />
					</div>

					<div>
						<label for="categoriaGrupo">Categoría</label> <select
							id="categoriaGrupo" name="categoriaGrupo"
							onchange="actualizarSubcategorias()">
							<option value="anime">Anime</option>
							<option value="videojuegos">Videojuegos</option>
						</select>
					</div>

					<div>
						<label for="subCategoriaGrupo">Subcategoría</label> <select
							id="subCategoriaGrupo" name="subCategoriaGrupo">
							<!-- Las opciones se llenarán dinámicamente con JavaScript -->
						</select>
					</div>

					<div>
						<button type="submit" class="manga-button">Guardar</button>
					</div>
				</form>
			</div>
		</div>

		<!-- MODAL PARA EDITAR EL PERFIL PERSONAL -->
		<c:set var="usuarioAModificar" value="${sessionScope.Usuario}" />
		<div id="formularioModal" class="modal">
			<div class="contenidoModal">
				<span class="close" onclick="closeFormularioModal()">&times;</span>
				<h2>Modificar Usuario</h2>
				<form id="formularioDatosModal" enctype="multipart/form-data"
					onsubmit="enviarFormulario(event)">
					<!-- Campos visibles -->
					<div>
						<label for="aliasInput">Alias:</label> <input type="text"
							id="aliasInput" name="aliasUsu"
							value="${usuarioAModificar.aliasUsu}" />
					</div>
					<div>
						<label for="nombreCompletoInput">Nombre Completo:</label> <input
							type="text" id="nombreCompletoInput" name="nombreCompletoUsu"
							value="${usuarioAModificar.nombreCompletoUsu}" />
					</div>
					<div>
						<label for="movilInput">Teléfono Móvil:</label> <input type="tel"
							id="movilInput" name="movilUsu"
							value="${usuarioAModificar.movilUsu}" />
					</div>
					<div>
						<label for="fotoInput">Fotografía:</label> <input type="file"
							id="fotoInput" name="fotoUsu" name="fotoUsu"
							value="${usuarioAModificar.fotoUsu}" />
					</div>
					<button type="submit" class="manga-button">Guardar</button>
				</form>
			</div>
		</div>

		<!-- ALERTA PERSONALIZADA -->
		<div id="alertaPersonalizada" class="alerta-personalizada">
			<div class="alerta-contenido">
				<p id="alertaMensaje">Mensaje de alerta</p>
				<button onclick="cerrarAlertaPersonalizada()">Aceptar</button>
			</div>
		</div>
	</main>
	<script src="<%=request.getContextPath()%>/js/javaScriptPerfil.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>