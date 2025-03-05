document.getElementById("appointmentForm").addEventListener("submit", function(event) {
    event.preventDefault();

    const dateInput = document.getElementById("date").value;
    if (!dateInput) {
        alert("Por favor, elige una fecha y hora.");
        return;
    }

    const dateObj = new Date(dateInput);
    const formattedDate = `${String(dateObj.getDate()).padStart(2, '0')}-${String(dateObj.getMonth() + 1).padStart(2, '0')}-${dateObj.getFullYear()}-${String(dateObj.getHours()).padStart(2, '0')}-${String(dateObj.getMinutes()).padStart(2, '0')}`;

    fetch("http://localhost:8080/appointments/create", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ date: formattedDate })
    })
    .then(response => response.json())
    .then(data => alert("Turno registrado correctamente."))
    .catch(error => alert("Error al registrar el turno."));
});