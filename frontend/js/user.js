
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

async function getAppointments(){
    try {
        const response = await fetch("http://localhost:8080/appointments");
        if (!response.ok) {
            throw new Error("Error fetching appointments");
        }
        const appointments = await response.json();

        console.log(appointments)

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
                    <button class="btn-reservar">Reserve</button>
                </div>
            `;
        
            if (estado == "reservado") {
                const btn = document.querySelectorAll(".btn-reservar");
                btn[btn.length - 1].disabled = true; 
            }
        });
        
    } catch (error) {
        console.error("Error:", error);
        }
}

getAppointments()