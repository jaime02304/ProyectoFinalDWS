//PARTE ALERTA
function alertaDelPremium() {
	let mensaje = "Sentimos las molestias pero esta función no está activada en esta versión de la web, más adelante estará.";
	var alerta = document.getElementById("alertaPersonalizada");
	document.getElementById("alertaMensaje").textContent = mensaje;
	alerta.style.display = "flex";
}

// Función para mostrar alertas personalizadas
function mostrarAlertaPersonalizada(mensaje) {
	var alerta = document.getElementById("alertaPersonalizada");
	document.getElementById("alertaMensaje").textContent = mensaje;
	alerta.style.display = "flex";
}

function cerrarAlertaPersonalizada() {
	document.getElementById("alertaPersonalizada").style.display = "none";
	location.reload();
}

/*---------------------------------------------------------------------------------------------------------------------------------------------------------------- */
/*---------------------------------------------------------------------------------------------------------------------------------------------------------------- */

//PARTE MODIFICACION USUARIO
// Función que maneja el envío del formulario de forma asíncrona
function enviarFormulario(event) {
	event.preventDefault(); // Evita el envío tradicional del formulario (recarga de página)

	// Obtención de los datos del formulario
	var aliasInput = document.getElementById("aliasInput");
	var nombreCompletoInput = document.getElementById("nombreCompletoInput");
	var movilInput = document.getElementById("movilInput");
	var fotoInput = document.getElementById("fotoInput");

	if (!aliasInput || !nombreCompletoInput || !movilInput || !fotoInput) {
		console.error("Uno o más elementos del formulario no se encontraron.");
		return;
	}

	// Recoger los valores actuales y originales del formulario
	var aliasOriginal = aliasInput.defaultValue;
	var nombreOriginal = nombreCompletoInput.defaultValue;
	var movilOriginal = movilInput.defaultValue;

	var aliasNuevo = aliasInput.value;
	var nombreNuevo = nombreCompletoInput.value;
	var movilNuevo = movilInput.value;
	var fotoNuevo = fotoInput.files.length > 0 ? fotoInput.files[0] : null;

	// Verificamos si hubo cambios en los valores del formulario o si se subió una nueva foto
	if (aliasNuevo !== aliasOriginal || nombreNuevo !== nombreOriginal || movilNuevo !== movilOriginal || fotoNuevo) {
		// Crear un objeto FormData para enviar los datos del formulario
		var formData = new FormData();
		formData.append("aliasUsu", aliasNuevo);
		formData.append("nombreCompletoUsu", nombreNuevo);
		formData.append("movilUsu", movilNuevo);
		if (fotoNuevo) {
			formData.append("foto", fotoNuevo);
		}

		// Obtener el contexto de la aplicación para construir la URL correcta
		var contextPath = window.location.pathname.split('/')[1]; // Ejemplo: "miapp" si la URL es /miapp/ModificarUsuario

		// Realizar la solicitud POST con fetch
		fetch("/" + contextPath + "/ModificarUsuario", {
			method: "POST",
			body: formData
		})
			.then(function(response) {
				if (response.ok) {
					mostrarAlertaPersonalizada("Los datos se han guardado correctamente. Dale a aceptar y se verá los datos modificados");
				} else {
					mostrarAlertaPersonalizada("Error al guardar los datos. Inténtelo nuevamente.");
				}
			})
			.catch(function(error) {
				console.error("Error en la solicitud:", error);
				mostrarAlertaPersonalizada("Ocurrió un error inesperado.");
			});
	}

	// Cerrar el modal (si corresponde)
	closeFormularioModal();
}

// Función para abrir el formulario modal
function openFormularioModal() {
	document.getElementById("formularioModal").style.display = "flex";
}

// Función para cerrar el formulario modal
function closeFormularioModal() {
	document.getElementById("formularioModal").style.display = "none";
}

// Evento que escucha cuando el DOM está cargado
document.addEventListener("DOMContentLoaded", function() {
	var formulario = document.getElementById("formularioDatosModal");
	if (formulario) {
		formulario.addEventListener("submit", enviarFormulario); // Asociamos el evento submit al formulario
	} else {
		console.error("No se encontró el formulario.");
	}
});

/*---------------------------------------------------------------------------------------------------------------------------------------------------------------- */
/*---------------------------------------------------------------------------------------------------------------------------------------------------------------- */

//PARTE DE ELIMINAR
function openEliminacionModal(id, filtradorNombre, esUsuario) {
	// Cacheamos los elementos del DOM
	const modal = document.getElementById("formularioEliminacionModal");
	const idInput = document.getElementById("idElementoAEliminar");
	const nombreInput = document.getElementById("elementoAEliminar");
	const esUsuarioV = document.getElementById("esUsuarioElementoAEliminar");
	const modalTitulo = document.getElementById("modalTitulo");

	// Abrimos el modal y asignamos los valores correspondientes
	modal.style.display = "flex";
	idInput.value = id;
	nombreInput.value = filtradorNombre;
	esUsuarioV.value = esUsuario;
	modalTitulo.innerText = `Eliminar a: ${filtradorNombre}`;
}

function closeEliminacionModal() {
	document.getElementById("formularioEliminacionModal").style.display = "none";
}

function enviarEliminacion(event) {
	event.preventDefault(); // Evita la recarga de la página

	// Obtenemos los valores de los inputs una sola vez
	const nombre = document.getElementById("elementoAEliminar").value;
	const id = document.getElementById("idElementoAEliminar").value;
	const esUsuario = document.getElementById("esUsuarioElementoAEliminar").value;

	if (!nombre) {
		console.error("No se encontró el nombre o identificador del usuario a eliminar.");
		return;
	}

	// Creamos el FormData e incluimos únicamente los campos deseados
	const formData = new FormData();
	formData.append("elementoEliminar", nombre);
	formData.append("idElementoEliminar", id);
	formData.append("esUsuarioEliminar", esUsuario)

	// Obtenemos el contexto de la aplicación (asumiendo que está en la URL)
	const contextPath = window.location.pathname.split('/')[1];

	// Realizamos la solicitud POST usando fetch con promesas
	fetch(`/${contextPath}/EliminarElementosComoAdmin`, {
		method: "POST",
		body: formData
	})
		.then(function(response) {
			closeEliminacionModal();
			if (response.ok) {
				mostrarAlertaPersonalizada("El elemento ha sido eliminado correctamente.");
			} else {
				mostrarAlertaPersonalizada("Error al eliminar el elemento. Inténtelo nuevamente.");
			}
		})
		.catch(function(error) {
			console.error("Error en la solicitud:", error);
			closeEliminacionModal();
			mostrarAlertaPersonalizada("Ocurrió un error inesperado.");
		});
}


/*---------------------------------------------------------------------------------------------------------------------------------------------------------------- */
/*---------------------------------------------------------------------------------------------------------------------------------------------------------------- */

// Abrir modal de modificación de usuario
function openModificacionModal(id, nombreCompleto, alias, correo, movil, esPremium, rol, esVerificadoEntidad) {
	// Cacheamos el modal del DOM
	const modal = document.getElementById("formularioModificacionModal");
	modal.style.display = "flex";

	// Asignamos los valores correspondientes a cada campo del formulario
	document.getElementById("idUsu").value = id;
	document.getElementById("nombreCompletoUsu").value = nombreCompleto;
	document.getElementById("aliasUsu").value = alias;
	document.getElementById("correoElectronicoUsu").value = correo;
	document.getElementById("movilUsu").value = movil;
	// Nota: No es posible pre-cargar el campo file (fotoUsu) por razones de seguridad
	document.getElementById("esPremium").checked = esPremium;
	document.getElementById("rolUsu").value = rol;
	document.getElementById("esVerificadoEntidad").checked = esVerificadoEntidad;

	// Actualizamos el título del modal (opcional)
	document.getElementById("modalTituloMod").innerText = `Modificar usuario: ${alias}`;
}

// Cerrar modal de modificación de usuario
function closeModificacionModal() {
	document.getElementById("formularioModificacionModal").style.display = "none";
}

// Enviar datos de modificación
function enviarModificacion(event) {
	event.preventDefault(); // Evita la recarga de la página

	// Obtenemos los valores de los inputs
	const id = document.getElementById("idUsu").value;
	const nombreCompleto = document.getElementById("nombreCompletoUsu").value;
	const alias = document.getElementById("aliasUsu").value;
	const correo = document.getElementById("correoElectronicoUsu").value;
	const movil = document.getElementById("movilUsu").value;
	const fotoFile = document.getElementById("fotoUsu").files[0]; // Archivo de foto (si se seleccionó)
	const esPremium = document.getElementById("esPremium").checked;
	const rol = document.getElementById("rolUsu").value;
	const esVerificadoEntidad = document.getElementById("esVerificadoEntidad").checked;

	// Creamos el FormData e incluimos los campos deseados
	const formData = new FormData();
	formData.append("idUsu", id);
	formData.append("nombreCompletoUsu", nombreCompleto);
	formData.append("aliasUsu", alias);
	formData.append("correoElectronicoUsu", correo);
	formData.append("movilUsu", movil);
	if (fotoFile) {
		formData.append("fotoUsu", fotoFile);
	}
	formData.append("esPremium", esPremium);
	formData.append("rolUsu", rol);
	formData.append("esVerificadoEntidad", esVerificadoEntidad);

	// Obtenemos el contexto de la aplicación (asumiendo que está en la URL)
	const contextPath = window.location.pathname.split('/')[1];

	// Realizamos la solicitud POST usando fetch
	fetch(`/${contextPath}/ModificarUsuarioComoAdmin`, {
		method: "POST",
		body: formData
	})
		.then(function(response) {
			closeModificacionModal();
			if (response.ok) {
				mostrarAlertaPersonalizada("El usuario ha sido modificado correctamente.");
			} else {
				mostrarAlertaPersonalizada("Error al modificar el usuario. Inténtelo nuevamente.");
			}
		})
		.catch(function(error) {
			console.error("Error en la solicitud:", error);
			closeModificacionModal();
			mostrarAlertaPersonalizada("Ocurrió un error inesperado.");
		});
}

/*---------------------------------------------------------------------------------------------------------------------------------------------------------------- */
/*---------------------------------------------------------------------------------------------------------------------------------------------------------------- */
//Abrir modal para la modificacion de grupos
function openModificacionGrupoModal(id, nombreGrupo, categoria, subcategoria) {
	// Cacheamos el modal del DOM y lo mostramos
	const modal = document.getElementById("formularioModificacionGrupoModal");
	modal.style.display = "flex";

	// Asignamos los valores a los inputs
	document.getElementById("idGrupo").value = id;
	document.getElementById("nombreGrupo").value = nombreGrupo;
	document.getElementById("categoriaGrupo").value = categoria;

	// Actualizar subcategorías según la categoría seleccionada
	actualizarSubcategorias();
	document.getElementById("subCategoriaGrupo").value = subcategoria;

	// Actualizamos el título del modal (opcional)
	document.getElementById("modalTituloGrupo").innerText = `Modificar Grupo: ${nombreGrupo}`;
}

// Función para cerrar el modal
function closeModificacionGrupoModal() {
	document.getElementById("formularioModificacionGrupoModal").style.display = "none";
}

// Función para actualizar dinámicamente las subcategorías
function actualizarSubcategorias() {
	const categoria = document.getElementById("categoriaGrupo").value;
	const subcategoriaSelect = document.getElementById("subCategoriaGrupo");

	// Limpiar opciones previas
	subcategoriaSelect.innerHTML = "";

	// Subcategorías según la categoría seleccionada
	const subcategoriasPorCategoria = {
		anime: [
			{ value: "isekai", text: "Isekai - Mundos paralelos o alternativos" },
			{ value: "shonen", text: "Shonen - Para público juvenil masculino" },
			{ value: "shojo", text: "Shojo - Para público juvenil femenino" }
		],
		videojuegos: [
			{ value: "shooters", text: "Shooters - Disparos en primera o tercera persona" },
			{ value: "aventuras", text: "Aventuras - Exploración y narrativas" },
			{ value: "deportes", text: "Deportes - Juegos deportivos" }
		]
	};

	// Agregar las opciones de subcategoría según la categoría seleccionada
	subcategoriasPorCategoria[categoria].forEach(sub => {
		const option = document.createElement("option");
		option.value = sub.value;
		option.textContent = sub.text;
		if (sub.value === document.getElementById("subCategoriaGrupo").value) {
			option.selected = true;
		}

		subcategoriaSelect.appendChild(option);
	});
}

// Enviar datos del formulario de modificación del grupo
function enviarModificacionGrupo(event) {
	event.preventDefault(); // Evita la recarga de la página

	// Obtener los valores de los inputs
	const idGrupo = document.getElementById("idGrupo").value;
	const nombreGrupo = document.getElementById("nombreGrupo").value;
	const categoriaGrupo = document.getElementById("categoriaGrupo").value;
	const subCategoriaGrupo = document.getElementById("subCategoriaGrupo").value;
	console.log(subCategoriaGrupo);

	// Crear objeto FormData para enviar los datos
	const formData = new FormData();
	formData.append("idGrupo", idGrupo);
	formData.append("nombreGrupo", nombreGrupo);
	formData.append("categoriaNombre", categoriaGrupo);
	formData.append("subCategoriaNombre", subCategoriaGrupo);

	// Obtener el contexto de la aplicación
	const contextPath = window.location.pathname.split('/')[1];

	// Realizar la solicitud POST usando fetch
	fetch(`/${contextPath}/ModificarGrupoComoAdmin`, {
		method: "POST",
		body: formData
	})
		.then(function(response) {
			closeModificacionGrupoModal();
			if (response.ok) {
				mostrarAlertaPersonalizada("El grupo ha sido modificado correctamente.");
			} else {
				mostrarAlertaPersonalizada("Error al modificar el grupo. Inténtelo nuevamente.");
			}
		})
		.catch(function(error) {
			console.error("Error en la solicitud:", error);
			closeModificacionGrupoModal();
			mostrarAlertaPersonalizada("Ocurrió un error inesperado.");
		});
}

/*---------------------------------------------------------------------------------------------------------------------------------------------------------------- */
/*---------------------------------------------------------------------------------------------------------------------------------------------------------------- */
//abrir modal para la creacion de usuarios
function openCreacionUsuarioModal() {
	document.getElementById("formularioCreacionUsuarioModal").style.display = "flex";
}

function closeCreacionUsuarioModal() {
	document.getElementById("formularioCreacionUsuarioModal").style.display = "none";
}

function enviarCreacionUsuario(event) {
	event.preventDefault(); // Evita recargar la página

	// Obtener los valores del formulario
	const nombreCompleto = document.getElementById("nombreCompletoNuevo").value;
	const alias = document.getElementById("aliasNuevo").value;
	const correo = document.getElementById("correoNuevo").value;
	const movil = document.getElementById("movilNuevo").value;
	const fotoFile = document.getElementById("fotoNuevo").files[0];
	const esPremium = document.getElementById("esPremiumNuevo").checked;
	const esVerificado = document.getElementById("esVerificadoNuevo").checked;
	const rol = document.getElementById("rolNuevo").value;

	// Crear FormData para enviar los datos
	const formData = new FormData();
	formData.append("nombreCompletoUsu", nombreCompleto);
	formData.append("aliasUsu", alias);
	formData.append("correoElectronicoUsu", correo);
	formData.append("movilUsu", movil);
	if (fotoFile) {
		formData.append("fotoUsu", fotoFile);
	}
	formData.append("esPremium", esPremium);
	formData.append("esVerificadoEntidad", esVerificado);
	formData.append("rolUsu", rol);

	// Obtener el contexto de la aplicación (si es necesario)
	const contextPath = window.location.pathname.split('/')[1];

	// Realizar la solicitud POST
	fetch(`/${contextPath}/CrearUsuarioComoAdmin`, {
		method: "POST",
		body: formData
	})
		.then(response => {
			closeCreacionUsuarioModal();
			if (response.ok) {
				mostrarAlertaPersonalizada("El usuario ha sido creado correctamente.");
			} else {
				mostrarAlertaPersonalizada("Error al crear el usuario. Inténtelo nuevamente.");
			}
		})
		.catch(error => {
			console.error("Error en la solicitud:", error);
			closeCreacionUsuarioModal();
			mostrarAlertaPersonalizada("Ocurrió un error inesperado.");
		});
}

/*---------------------------------------------------------------------------------------------------------------------------------------------------------------- */
/*---------------------------------------------------------------------------------------------------------------------------------------------------------------- */
//abrir modal para la creacion de grupos
// Función para abrir el modal de creación de grupo
function openCreacionGrupoModal() {
	const modal = document.getElementById("formularioCreacionGrupoModal");
	modal.style.display = "flex";

	// Establecer un valor por defecto en la categoría y actualizar subcategorías
	document.getElementById("categoriaGrupoNuevo").value = "anime";
	actualizarSubcategoriasCreacion();
}

// Función para cerrar el modal de creación de grupo
function closeCreacionGrupoModal() {
	document.getElementById("formularioCreacionGrupoModal").style.display = "none";
}

// Función para actualizar dinámicamente las subcategorías en el modal de creación
function actualizarSubcategoriasCreacion() {
	const categoria = document.getElementById("categoriaGrupoNuevo").value;
	const subCategoriaSelect = document.getElementById("subCategoriaGrupoNuevo");

	// Limpiar opciones previas
	subCategoriaSelect.innerHTML = "";

	// Definir las subcategorías disponibles para cada categoría
	const subcategoriasPorCategoria = {
		anime: [
			{ value: "isekai", text: "Isekai - Mundos paralelos o alternativos" },
			{ value: "shonen", text: "Shonen - Para público juvenil masculino" },
			{ value: "shojo", text: "Shojo - Para público juvenil femenino" }
		],
		videojuegos: [
			{ value: "shooters", text: "Shooters - Disparos en primera o tercera persona" },
			{ value: "aventuras", text: "Aventuras - Exploración y narrativas" },
			{ value: "deportes", text: "Deportes - Juegos deportivos" }
		]
	};

	// Agregar las opciones al select de subcategoría según la categoría seleccionada
	subcategoriasPorCategoria[categoria].forEach(sub => {
		const option = document.createElement("option");
		option.value = sub.value;
		option.textContent = sub.text;
		subCategoriaSelect.appendChild(option);
	});
}

// Función para enviar los datos del formulario de creación del grupo
function enviarCreacionGrupo(event) {
	event.preventDefault(); // Evitar la recarga de la página

	// Obtener los valores de los inputs
	const creador = document.getElementById("creadorGrupo").value;
	const nombreGrupo = document.getElementById("nombreGrupoNuevo").value;
	const categoriaGrupo = document.getElementById("categoriaGrupoNuevo").value;
	const subCategoriaGrupo = document.getElementById("subCategoriaGrupoNuevo").value;
	const aliasCreador = document.getElementById("aliasCreadorNuevo").value;

	// Crear FormData para enviar los datos
	const formData = new FormData();
	formData.append("idCreador", creador);
	formData.append("nombreGrupo", nombreGrupo);
	formData.append("categoriaNombre", categoriaGrupo);
	formData.append("subCategoriaNombre", subCategoriaGrupo);
	formData.append("aliasCreadorUString", aliasCreador);

	// Obtener el contexto de la aplicación (si es necesario)
	const contextPath = window.location.pathname.split('/')[1];

	// Realizar la solicitud POST usando fetch
	fetch(`/${contextPath}/CrearGrupoComoAdmin`, {
		method: "POST",
		body: formData
	})
		.then(function(response) {
			closeCreacionGrupoModal();
			if (response.ok) {
				mostrarAlertaPersonalizada("El grupo ha sido creado correctamente.");
			} else {
				mostrarAlertaPersonalizada("Error al crear el grupo. Inténtelo nuevamente.");
			}
		})
		.catch(function(error) {
			console.error("Error en la solicitud:", error);
			closeCreacionGrupoModal();
			mostrarAlertaPersonalizada("Ocurrió un error inesperado.");
		});
}




