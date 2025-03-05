document.addEventListener("DOMContentLoaded", () => {
    const appointmentsList = document.getElementById("appointments-list");
    const modal = document.getElementById("confirmation-modal");
    const confirmBtn = document.getElementById("confirm-delete");
    const cancelBtn = document.getElementById("cancel-delete");

    let selectedAppointmentId = null;

    // Función para cargar las citas
    function fetchAppointments() {
        fetch("http://localhost:8080/appointments")
            .then(response => response.json())
            .then(data => {
                appointmentsList.innerHTML = "";
                data.forEach(appointment => {
                    const card = document.createElement("div");
                    card.classList.add("card");
                    card.innerHTML = `
                        <span>Id: ${appointment.id} -  Date: ${appointment.date} - User id: ${appointment.userId}</span>
                        <button class="delete-btn" data-id="${appointment.id}">
                            <i class="bi bi-trash-fill"></i>
                        </button>
                    `;
                    appointmentsList.appendChild(card);
                });

                document.querySelectorAll(".delete-btn").forEach(btn => {
                    btn.addEventListener("click", (e) => {
                        selectedAppointmentId = e.target.closest("button").getAttribute("data-id");
                        modal.style.display = "flex";
                    });
                });
            })
            .catch(error => console.error("Error al cargar citas:", error));
    }

    // Confirmar eliminación
    confirmBtn.addEventListener("click", () => {
        if (selectedAppointmentId) {
            fetch(`http://localhost:8080/appointments/delete/${selectedAppointmentId}`, {
                method: "DELETE"
            })
            .then(response => {
                if (response.ok) {
                    modal.style.display = "none";
                    fetchAppointments();
                } else {
                    alert("Error al eliminar la cita");
                }
            })
            .catch(error => console.error("Error:", error));
        }
    });

    // Cancelar eliminación
    cancelBtn.addEventListener("click", () => {
        modal.style.display = "none";
        selectedAppointmentId = null;
    });

    fetchAppointments();
});
