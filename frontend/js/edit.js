document.addEventListener("DOMContentLoaded", function() {
    const apiUrl = 'http://localhost:8080/appointments';
    const appointmentsList = document.getElementById('appointments-list');
    const modal = document.getElementById('editModal');
    const closeModalBtn = document.querySelector('.close-btn');
    const editForm = document.getElementById('edit-form');
    let currentAppointmentId = null;
    let appointments = []; // Variable para almacenar los appointments

    // Función para cargar los appointments desde el servidor
    async function loadAppointments() {
        const response = await fetch(apiUrl);
        appointments = await response.json(); // Guardamos los datos de los appointments en la variable

        appointmentsList.innerHTML = '';  // Limpiar lista
        appointments.forEach(appointment => {
            const card = document.createElement('div');
            card.classList.add('card');
            card.innerHTML = `
                <h3>Appointment ID: ${appointment.id}</h3>
                <p>Fecha: ${appointment.date}</p>
                <p>Reservado: ${appointment.reserved ? 'Sí' : 'No'}</p>
                <div class="edit-btn" data-id="${appointment.id}">&#9998; Editar</div>
            `;
            appointmentsList.appendChild(card);
        });

        // Añadir event listener a los botones de editar
        document.querySelectorAll('.edit-btn').forEach(button => {
            button.addEventListener('click', openEditModal);
        });
    }

    // Función para abrir el modal de edición
    function openEditModal(event) {
        currentAppointmentId = event.target.getAttribute('data-id');
        const appointment = findAppointmentById(currentAppointmentId); // Buscar en la variable appointments
        document.getElementById('date').value = appointment.date;  // Cargar la fecha en el formulario
        modal.style.display = 'flex';
    }

    // Función para encontrar un appointment por su ID
    function findAppointmentById(id) {
        return appointments.find(app => app.id === parseInt(id)); // Buscar en la variable appointments
    }

    // Función para cerrar el modal
    closeModalBtn.addEventListener('click', () => {
        modal.style.display = 'none';
    });

    // Función para enviar los cambios al servidor
    editForm.addEventListener('submit', async function(event) {
        event.preventDefault();

        const updatedDate = document.getElementById('date').value;
        const updatedAppointment = {
            id: currentAppointmentId,
            date: updatedDate,
            reserved: true,  // Mantener la reserva igual
            user: { id: 1 }  // Mantener el user igual
        };

        // Enviar el PATCH a la API
        const response = await fetch(`${apiUrl}/update`, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(updatedAppointment)
        });

        if (response.ok) {
            loadAppointments();  // Recargar la lista de appointments
            modal.style.display = 'none';  // Cerrar el modal
        } else {
            alert('Hubo un error al actualizar el appointment');
        }
    });

    // Cargar appointments al inicio
    loadAppointments();
});
