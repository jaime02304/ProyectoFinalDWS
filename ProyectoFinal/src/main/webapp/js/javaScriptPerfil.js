function alertaDelPremium() {
	let mensaje = "Sentimos las molestias pero esta función no está activada en esta versión de la web, más adelante estará.";
	var alerta = document.getElementById("alertaPersonalizada");
	document.getElementById("alertaMensaje").textContent = mensaje;
	alerta.style.display = "flex";
}

function openFormularioModal() {
	document.getElementById("formularioModal").style.display = "flex";
}

function closeFormularioModal() {
	document.getElementById("formularioModal").style.display = "none";
}

window.onclick = function(event) {
	var modal = document.getElementById("formularioModal");
	if (event.target === modal) {
		modal.style.display = "none";
	}
};
// Funciones para el Alert Personalizado
function mostrarAlertaPersonalizada(mensaje) {
	var alerta = document.getElementById("alertaPersonalizada");
	document.getElementById("alertaMensaje").textContent = mensaje;
	alerta.style.display = "flex";
}

function cerrarAlertaPersonalizada() {
	var alerta = document.getElementById("alertaPersonalizada");
	alerta.style.display = "none";
}

// Evento de envío del formulario: simula funcionalidad correcta, muestra el alert personalizado y cierra el modal
document.getElementById("formularioDatosModal").addEventListener("submit", function(event) {
	event.preventDefault(); // Evita el envío real del formulario

	var input = document.getElementById("inputValorModal");
	var nuevoValor = input.value; // Nuevo valor ingresado
	var valorInicial = input.getAttribute("data-valor-inicial"); // Valor original almacenado

	// Verificamos si el usuario realmente cambió el valor antes de mostrar la alerta
	if (nuevoValor !== valorInicial) {
		mostrarAlertaPersonalizada("Los datos se han guardado correctamente.");
	}

	closeFormularioModal(); // Cierra el modal
});


