function alertaDelPremium() {
	let mensaje="Sentimos las molestias pero esta función no está activada en esta versión de la web, más adelante estará.";
	var alerta = document.getElementById("alertaPersonalizada");
	        document.getElementById("alertaMensaje").textContent = mensaje;
	        alerta.style.display = "flex";
}

function openFormularioModal(nombreCampo, tipoInput, valorActual) {
          var modal = document.getElementById("formularioModal");
          modal.style.display = "flex";

          var titulo = document.getElementById("tituloModal");
          titulo.textContent = "Modificar " + nombreCampo;

          var label = document.getElementById("labelModal");
          label.textContent = "Nuevo " + nombreCampo + ":";

          var input = document.getElementById("inputValorModal");
          input.type = tipoInput;

          if (tipoInput === "file") {
              input.value = "";
          } else {
              input.value = valorActual;
              input.setAttribute("data-valor-inicial", valorActual); // Guarda el valor inicial
          }
      }


      // Función para cerrar el formulario modal
      function closeFormularioModal() {
          var modal = document.getElementById("formularioModal");
          modal.style.display = "none";
      }

      // Cierra el modal si el usuario hace clic fuera de él
      window.onclick = function (event) {
          var modal = document.getElementById("formularioModal");
          if (event.target == modal) {
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
      document.getElementById("formularioDatosModal").addEventListener("submit", function (event) {
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


