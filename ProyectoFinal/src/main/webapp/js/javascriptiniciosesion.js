const signUpButton = document.getElementById('signUp');
const signInButton = document.getElementById('signIn');
const container = document.getElementById('container');

signUpButton.addEventListener('click', () => {
	container.classList.add("right-panel-active");
});

signInButton.addEventListener('click', () => {
	container.classList.remove("right-panel-active");
});

function flipForm() {
	const container = document.getElementById('formContainer');
	container.classList.toggle('flip');
}


document.getElementById("formRegistro2").addEventListener("submit", function(event) {
	var contrasenia = document.getElementById("contraseniaUsu2").value;
	var repetirContrasenia = document.getElementById("repetirContrasenia2").value;

	if (contrasenia !== repetirContrasenia) {
		// Si las contraseñas no coinciden, evitamos el envío del formulario
		event.preventDefault();
		mostrarAlertaPersonalizada("Las contraseñas no coinciden. Por favor, revisa e intenta nuevamente.");
	}
});

document.getElementById("formRegistro").addEventListener("submit", function(event) {
	var contrasenia = document.getElementById("contraseniaUsu").value;
	var repetirContrasenia = document.getElementById("repetirContrasenia").value;

	if (contrasenia !== repetirContrasenia) {
		// Si las contraseñas no coinciden, evitamos el envío del formulario
		event.preventDefault();
		mostrarAlertaPersonalizada("Las contraseñas no coinciden. Por favor, revisa e intenta nuevamente.");
	}
});


function mostrarAlertaPersonalizada(mensaje) {
	var alerta = document.getElementById("alertaPersonalizada");
	document.getElementById("alertaMensaje").textContent = mensaje;
	alerta.style.display = "flex";
}

function cerrarAlertaPersonalizada() {
	document.getElementById("alertaPersonalizada").style.display = "none";
}