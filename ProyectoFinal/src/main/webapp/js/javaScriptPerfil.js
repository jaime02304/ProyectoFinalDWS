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
function openEliminacionModal(id, filtradorNombre) {
  // Cacheamos los elementos del DOM
  const modal = document.getElementById("formularioEliminacionModal");
  const idInput = document.getElementById("idElementoAEliminar");
  const nombreInput = document.getElementById("elementoAEliminar");
  const modalTitulo = document.getElementById("modalTitulo");

  // Abrimos el modal y asignamos los valores correspondientes
  modal.style.display = "flex";
  idInput.value = id;
  nombreInput.value = filtradorNombre;
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

  if (!nombre) {
    console.error("No se encontró el nombre o identificador del usuario a eliminar.");
    return;
  }

  // Creamos el FormData e incluimos únicamente los campos deseados
  const formData = new FormData();
  formData.append("elementoEliminar", nombre);
  formData.append("idElementoEliminar", id);

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


