
const appointmentsDiv = document.getElementById("appointments")

function formatDate(dateString) {
    // Separar la fecha y la hora
    let [datePart, timePart] = dateString.split('-').reduce((acc, val, index) => {
        if (index < 3) {
            acc[0].push(val);
        } else {
            acc[1].push(val);
        }
        return acc;
    }, [[], []]);
    
    // Unir los valores formateados
    return `${datePart.join('/')} ${timePart.join(':')}`;
}

async function getAppointments() {
    try {
        const response = await fetch("http://localhost:8080/appointments");
        if (!response.ok) {
            throw new Error("Error fetching appointments");
        }
        const appointments = await response.json();

        console.log(appointments)

        // Limpiar el contenedor antes de agregar nuevos turnos
        appointmentsDiv.innerHTML = "";

        appointments.forEach(function (appointment) {
            let estado;
            let text;
            let date = formatDate(appointment.date);
        
            if (!appointment.reserved) {
                estado = "disponible";
                text = "available";
            } else {
                text = "reserved";
                estado = "reservado";
            }
        
            appointmentsDiv.innerHTML += `
                <div class="turno-card">
                    <div class="fecha">${date}</div>
                    <div class="estado ${estado}">${text}</div>
                    <button 
                        class="btn-reservar" 
                        ${estado === "reservado" ? "disabled" : ""}
                        data-id="${appointment.id}"
                        data-date="${appointment.date}"
                        data-reserved="${appointment.reserved}"
                    >
                        Reserve
                    </button>
                </div>
            `;
        });

        // Agregar eventos a los botones después de crear las tarjetas
        const reserveBtns = document.querySelectorAll(".btn-reservar");
        reserveBtns.forEach(function (btn) {
            btn.addEventListener("click", function () {
                
                const userSS = sessionStorage.getItem("user");
                if (!userSS) {
                    alert("No hay usuario en sesión");
                    return;
                }

                const user = JSON.parse(userSS); // Convertimos el string en objeto
                
                // Construimos el objeto con toda la info
                const appointment = {
                    "id": btn.getAttribute("data-id"),
                    "date": btn.getAttribute("data-date"),
                    "reserved": true,
                    "user": {
                        "id": user.id, 
                    }
                };

                updateAppointment(appointment);
            });
        });

    } catch (error) {
        console.error("Error:", error);
    }
}

async function updateAppointment(appointment) {
    try {
        const response = await fetch("http://localhost:8080/appointments/update", {
            method: "PUT",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(appointment) // Enviamos el objeto completo
        });

        if (!response.ok) {
            throw new Error(`Error en la actualización: ${response.status}`);
        }

        alert("Turno actualizado con éxito");
        location.reload()
    } catch (error) {
        console.error("Error al actualizar el turno:", error);
        alert("Error al actualizar el turno");
    }
}



getAppointments()