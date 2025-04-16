const buttons = document.querySelectorAll(".categorias button");
const subcategorias = document.querySelectorAll(".subcategorias");

buttons.forEach((button) => {
	button.addEventListener("click", () => {
		// Quitar clase activa de todos los botones y secciones
		buttons.forEach((btn) => btn.classList.remove("active"));
		subcategorias.forEach((sub) => sub.classList.remove("active"));

		// Activar el botón y la sección correspondiente
		button.classList.add("active");
		const target = button.getAttribute("data-target");
		document.getElementById(target).classList.add("active");
	});
});

const categoriaFiltro = document.getElementById("categoriaFiltro");
const subcategoriaFiltro = document.getElementById("subcategoriaFiltro");

const subcategoriasPorCategoria = {
	anime: ["Shonen", "Isekai", "Shojo"],
	videojuegos: ["Shooters", "Aventuras", "Deportes"],
	auxiliar:["auxiliar"],
};

function actualizarSubcategoriasFiltro() {
	const categoriaSeleccionada = categoriaFiltro.value;
	const subcategorias = subcategoriasPorCategoria[categoriaSeleccionada];

	// Limpiar subcategorías anteriores
	subcategoriaFiltro.innerHTML = "";

	// Añadir nuevas opciones
	subcategorias.forEach((sub) => {
		const opcion = document.createElement("option");
		opcion.value = sub.toLowerCase();
		opcion.textContent = sub;
		subcategoriaFiltro.appendChild(opcion);
	});
}

// Inicializar y actualizar cuando se cambie la categoría
categoriaFiltro.addEventListener("change", actualizarSubcategoriasFiltro);
actualizarSubcategoriasFiltro();