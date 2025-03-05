document.getElementById("loginForm").addEventListener("submit", async (e) => {
    e.preventDefault();

    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    try {
        const response = await fetch("http://localhost:8080/users/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify({ 
                email: email, 
                password: password
            }),
        });

        if (!response.ok) {
            const errorMessage = await response.text(); // Captura el mensaje de error
            throw new Error(errorMessage);
        }

        const userData = await response.json();

        if (!userData || !userData.role || !userData.id) {
            throw new Error("Respuesta del servidor inválida");
        }

        sessionStorage.setItem("user", JSON.stringify(userData));

        // Redirigir según el rol
        window.location.href = userData.role === "USER" ? "user.html" : "admin.html";

    } catch (error) {
        alert(error.message);
    }
});

// Mostrar el ID del usuario si está logueado
