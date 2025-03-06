Proyecto de gestión de turnos y usuarios con Spring Boot JPA e Hibernate.

Descripción: 

  Este es un proyecto backend desarrollado con Spring Boot que permite gestionar usuarios y turnos.
  Implementa un sistema CRUD completo para cada una de sus entidades y maneja la persistencia de datos con JPA e Hibernate MySQL.
  Ademas utiliza un sistema de encriptación de contraseña para cada usuario (BCrypt).

Tecnologías Utilizadas:
  
  - Java 23
  
  - Spring Boot (Spring MVC, Spring Data JPA)
  
  - Hibernate
  
  - MySQL
  
  - Maven

Clonar el repositorio:

  git clone https://github.com/JoaquinRouge/Appointments-Backend-SpringBoot.git

Configurar la base de datos en application.properties:
  
  spring.datasource.url=jdbc:mysql://localhost:3306/nombre_bd spring.datasource.username=tu_usuario spring.datasource.password=tu_contraseña spring.jpa.hibernate.ddl-auto=update

Construir y ejecutar la aplicación:

  mvn spring-boot:run

Uso: 

# API Endpoints

## Usuarios

### 📋 Obtener todos los usuarios

**GET /users**

**Descripción:**  
Obtiene la lista de todos los usuarios almacenados en la base de datos.

**Respuestas:**
- ✅ `200 OK` - Devuelve la lista de usuarios en formato JSON.
- ⚠️ `204 NO CONTENT` - No hay usuarios registrados en la base de datos.

---

### 🔍 Obtener un usuario por ID

**GET /users/{id}**

**Descripción:**  
Obtiene los datos de un usuario específico según su ID.

**Parámetros de ruta:**
- `id` *(Long)* - ID del usuario a buscar.

**Respuestas:**
- ✅ `200 OK` - Devuelve el usuario encontrado.
- ❌ `404 NOT FOUND` - Usuario no encontrado.

---

### 📝 Crear un nuevo usuario

**POST /users/create**

**Descripción:**  
Crea un nuevo usuario en la base de datos.

**Cuerpo de la solicitud (JSON):**
```json
{
  "name": "John Doe",
  "email": "johndoe@example.com",
  "password": "securepassword"
}
```

**Respuestas:**
- ✅ `201 CREATED` - Devuelve el usuario creado.
- ❌ `409 CONFLICT` - Error al crear el usuario (por ejemplo, el correo ya está en uso).

---

### ✏️ Actualizar un usuario existente

**PUT /users/update**

**Descripción:**  
Actualiza los datos de un usuario existente.

**Cuerpo de la solicitud (JSON):**
```json
{
  "id": 1,
  "name": "John Doe Updated",
  "email": "johndoe@example.com",
  "password": "newsecurepassword"
}
```

**Respuestas:**
- ✅ `201 CREATED` - Devuelve el usuario actualizado.
- ❌ `409 CONFLICT` - Error al actualizar el usuario (por ejemplo, el usuario no existe).

---

### 🗑️ Eliminar un usuario

**DELETE /users/delete/{id}**

**Descripción:**  
Elimina un usuario según su ID.

**Parámetros de ruta:**
- `id` *(Long)* - ID del usuario a eliminar.

**Respuestas:**
- ✅ `200 OK` - Usuario eliminado con éxito.
- ❌ `409 CONFLICT` - Error al eliminar el usuario (por ejemplo, el usuario no existe).

---

### 🔐 Inicio de sesión

**POST /users/login**

**Descripción:**  
Permite a un usuario autenticarse en el sistema.

**Cuerpo de la solicitud (JSON):**
```json
{
  "email": "johndoe@example.com",
  "password": "securepassword"
}
```

**Respuestas:**
- ✅ `200 OK` - Devuelve los datos del usuario autenticado.
- ❌ `401 UNAUTHORIZED` - Credenciales incorrectas.

---

## Citas (Appointments)

### 📋 Obtener todas las citas

**GET /appointments**

**Descripción:**  
Obtiene la lista de todas las citas registradas en la base de datos.

**Respuestas:**
- ✅ `200 OK` - Devuelve la lista de citas en formato JSON.
- ⚠️ `204 NO CONTENT` - No hay citas registradas.

---

### 🔍 Obtener una cita por ID

**GET /appointments/{id}**

**Descripción:**  
Recupera los detalles de una cita específica por su ID.

**Parámetros:**
- `id` *(Long)* - Identificador único de la cita.

**Respuestas:**
- ✅ `200 OK` - Devuelve la cita encontrada.
- ❌ `404 NOT FOUND` - No se encontró ninguna cita con el ID proporcionado.

---

### 📝 Crear una nueva cita

**POST /appointments/create**

**Descripción:**  
Registra una nueva cita en la base de datos.

**Cuerpo de la solicitud (JSON):**
```json
{
  "date": "2025-03-10T14:00:00",
  "userId": 1,
  "doctorId": 2,
  "status": "PENDING"
}
```

**Respuestas:**
- ✅ `201 CREATED` - Devuelve la cita creada.
- ❌ `409 CONFLICT` - Ocurrió un error al crear la cita (por ejemplo, conflicto de horarios).

---

### ✏️ Actualizar una cita existente

**PATCH /appointments/update**

**Descripción:**  
Modifica los detalles de una cita ya existente.

**Cuerpo de la solicitud (JSON):**
```json
{
  "id": 5,
  "date": "2025-03-11T16:00:00",
  "status": "CONFIRMED"
}
```

**Respuestas:**
- ✅ `201 CREATED` - Devuelve la cita actualizada.
- ❌ `409 CONFLICT` - Error al actualizar (por ejemplo, la cita no existe).

---

### 🗑️ Eliminar una cita

**DELETE /appointments/delete/{id}**

**Descripción:**  
Elimina una cita del sistema según su ID.

**Parámetros:**
- `id` *(Long)* - Identificador único de la cita.

**Respuestas:**
- ✅ `200 OK` - Cita eliminada exitosamente.
- ❌ `409 CONFLICT` - Error al eliminar la cita (por ejemplo, la cita no existe).

---

### 👤 Obtener citas de un usuario

**GET /appointments/user/{id}**

**Descripción:**  
Obtiene todas las citas asociadas a un usuario específico.

**Parámetros:**
- `id` *(Long)* - Identificador del usuario.

**Respuestas:**
- ✅ `200 OK` - Devuelve la lista de citas del usuario.
- ❌ `409 CONFLICT` - Error al obtener las citas (por ejemplo, el usuario no existe).

