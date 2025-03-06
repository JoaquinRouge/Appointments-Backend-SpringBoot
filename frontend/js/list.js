document.addEventListener("DOMContentLoaded", () => {
    const appointmentsList = document.getElementById("appointments-list");

    // Función para obtener todas las citas
    async function fetchAppointments() {
        try {
            const response = await fetch("http://localhost:8080/appointments");
            if (!response.ok) {
                throw new Error(`Error HTTP: ${response.status}`);
            }
            const appointments = await response.json();
            renderAppointments(appointments);
        } catch (error) {
            console.error("Error al cargar las citas:", error);
            appointmentsList.innerHTML = "<p>Error al cargar citas.</p>";
        }
    }

    // Función para obtener el username por userId
    async function getUserName(userId) {
        try {
            const response = await fetch(`http://localhost:8080/users/${userId}`);
            if (!response.ok) {
                throw new Error(`Error HTTP: ${response.status}`);
            }
            const user = await response.json();
            return user.username;
        } catch (error) {
            console.error(`Error al obtener el usuario ${userId}:`, error);
            return "Desconocido";
        }
    }

    // Renderizar citas en el DOM
    async function renderAppointments(appointments) {
        appointmentsList.innerHTML = "";

        for (const appointment of appointments) {
            const card = document.createElement("div");
            card.classList.add("card");
            card.innerHTML = `
                <p><strong>Id:</strong> ${appointment.id}</p>
                <p><strong>Date:</strong> ${appointment.date}</p>
                <p><strong>User:</strong> <span class="loading">Cargando...</span></p>
            `;
            appointmentsList.appendChild(card);

            // Obtener y actualizar el username
            const username = await getUserName(appointment.userId);
            card.querySelector(".loading").textContent = username;
        }
    }

    // Cargar las citas al iniciar
    fetchAppointments();
});
