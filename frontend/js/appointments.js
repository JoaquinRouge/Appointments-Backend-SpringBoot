const user = sessionStorage.getItem("user");
const appointmentsSection = document.getElementById("myAppointments")

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

if (user) {
    try {
        const userData = JSON.parse(user); // Parseo el string a objeto
        const headerP = document.getElementById("headerP");

        if (headerP) {
            headerP.innerHTML += userData.username;
        }

        const id = userData.id; // Asegúrate de que userData tenga la propiedad id

        if (!id) {
            console.error("ID de usuario no encontrado");
        }

        // Llamar a la función para obtener las citas del usuario
        getUserAppointments(id);

    } catch (error) {
        console.error("Error al procesar los datos del usuario:", error);
    }
} else {
    console.log("No hay usuario en sessionStorage");
}

async function getUserAppointments(id) {
    try {
        const response = await fetch(`http://localhost:8080/appointments/user/${id}`);
        
        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const data = await response.json();
        
        const userData = JSON.parse(user);

        data.forEach(function (data) {
            appointmentsSection.innerHTML += 
                
                `
                
                <div class="appointment-card" id="appointment-card">
                    <div class="appointment-details">
                        <p><strong>Appointment id:</strong> <span id="appointment-id">${data.id}</span></p>
                        <p><strong>Date:</strong> <span id="appointment-date">${formatDate(data.date)}</span></p>
                        <p><strong>User Name:</strong> <span id="appointment-username">${userData.username}</span></p>
                    </div>
                </div>


                `
        })

        return data;
    } catch (error) {
        console.error("Error fetching user appointments:", error);
    }
}
