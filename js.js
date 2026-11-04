
let carrito = [];

const botones = document.querySelectorAll(".agregar-carrito");
const listaCarrito = document.getElementById("lista-carrito");
const totalCarrito = document.getElementById("total-carrito");
const botonVaciar = document.getElementById("vaciar-carrito");

function mostrarCarrito() {
    listaCarrito.innerHTML = "";
    let total = 0;

    if (carrito.length === 0) {
        listaCarrito.innerHTML = "<p>Tu carrito está vacío.</p>";
    }

    carrito.forEach((producto, indice) => {
        total += producto.precio;

        const elemento = document.createElement("div");
        elemento.innerHTML = `
            <p>
                ${producto.nombre} - $${producto.precio.toLocaleString("es-CO")}
                <button onclick="eliminarProducto(${indice})">
                    Quitar
                </button>
            </p>
        `;

        listaCarrito.appendChild(elemento);
    });

    totalCarrito.textContent =
        "Total: $" + total.toLocaleString("es-CO");
}

function eliminarProducto(indice) {
    carrito.splice(indice, 1);
    mostrarCarrito();
}

botones.forEach((boton) => {
    boton.addEventListener("click", () => {
        const producto = boton.closest(".producto");
        const nombre = producto.querySelector("h3").textContent;
        const precioTexto = producto.querySelector(".precio").textContent;
        const precio = Number(
            precioTexto.replace("$", "").replace(/\./g, "")
        );

        carrito.push({
            nombre: nombre,
            precio: precio
        });

        mostrarCarrito();
    });
});

botonVaciar.addEventListener("click", () => {
    carrito = [];
    mostrarCarrito();
});

mostrarCarrito();


function filtrarCategoria(categoriaSeleccionada) {
    const productos = document.querySelectorAll(".producto");

    productos.forEach((producto) => {
        if (producto.dataset.categoria === categoriaSeleccionada) {
            producto.style.display = "block";
        } else {
            producto.style.display = "none";
        }
    });

    document.getElementById("productos").scrollIntoView({
        behavior: "smooth"
    });
}
