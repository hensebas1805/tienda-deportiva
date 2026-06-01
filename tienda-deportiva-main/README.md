# Tienda Deportiva API

Backend completo para gestión de tienda deportiva desarrollado con **Spring Boot 3.2.5**, **Java 17** y **MySQL/H2**.

## ✅ Características

- **CRUD Completo** para 4 entidades (Categorías, Productos, Clientes, Ventas)
- **Validaciones** con Bean Validation (@Valid, @NotNull, @NotBlank, etc.)
- **Manejo Centralizado de Errores** con @ControllerAdvice
- **Reglas de Negocio** (validación de stock, totales, estados)
- **Búsquedas Simples** con query params
- **Documentación con Swagger/OpenAPI**
- **Persistencia con JPA/Hibernate**

## 📋 Requisitos

- Java 17+
- Maven 3.6+
- IntelliJ IDEA (recomendado)

## 🚀 Instalación y Ejecución

### 1. Clonar/Descargar el proyecto
```bash
git clone https://github.com/HenSebas/tienda-deportiva.git
cd tienda-deportiva
```

### 2. Importar en IntelliJ IDEA
- File → Open → Selecciona la carpeta del proyecto
- Maven descargará las dependencias automáticamente

### 3. Compilar
```bash
mvn clean install
```

### 4. Ejecutar

**Opción A - Desde IntelliJ:**
- Click derecho en `TiendaDeportivaApplication.java` → Run

**Opción B - Desde terminal:**
```bash
mvn spring-boot:run
```

## 📚 Acceder a la API

Una vez que la aplicación está corriendo:

- **Swagger UI**: http://localhost:8080/api/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api/v3/api-docs
- **Consola H2**: http://localhost:8080/api/h2-console

## 📊 Base de Datos

**Por defecto**: H2 (en memoria)

### Credenciales H2:
- Usuario: `sa`
- Contraseña: (dejar vacío)
- JDBC URL: `jdbc:h2:mem:tiendadeportiva`

### Cambiar a MySQL:
Modifica `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tienda_deportiva
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=tuContraseña
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
```

## 📁 Estructura del Proyecto

```
src/main/java/com/tienda/deportiva/
├── model/              # Entidades JPA
├── repository/         # Interfaces Repository
├── service/            # Lógica de negocio
├── controller/         # REST Controllers
├── exception/          # Manejo de excepciones
├── dto/               # Data Transfer Objects
├── config/            # Configuraciones
└── TiendaDeportivaApplication.java
```

## 🔌 Endpoints Principales

### Categorías
```
GET    /api/categorias           - Obtener todas
GET    /api/categorias/{id}      - Obtener por ID
GET    /api/categorias/buscar?nombre=... - Buscar
POST   /api/categorias           - Crear
PUT    /api/categorias/{id}      - Actualizar
DELETE /api/categorias/{id}      - Eliminar
```

### Productos
```
GET    /api/productos            - Obtener todas
GET    /api/productos/{id}       - Obtener por ID
GET    /api/productos/buscar?nombre=...    - Buscar
GET    /api/productos/categoria/{id}       - Por categoría
GET    /api/productos/stock-bajo?minimo=10 - Stock bajo
POST   /api/productos            - Crear
PUT    /api/productos/{id}       - Actualizar
DELETE /api/productos/{id}       - Eliminar
```

### Clientes
```
GET    /api/clientes             - Obtener todas
GET    /api/clientes/{id}        - Obtener por ID
GET    /api/clientes/buscar?nombre=... - Buscar
GET    /api/clientes/email/{email}      - Por email
POST   /api/clientes             - Crear
PUT    /api/clientes/{id}        - Actualizar
DELETE /api/clientes/{id}        - Eliminar
```

### Ventas
```
GET    /api/ventas               - Obtener todas
GET    /api/ventas/{id}          - Obtener por ID
GET    /api/ventas/cliente/{id}  - Por cliente
GET    /api/ventas/estado?estado=PENDIENTE - Por estado
GET    /api/ventas/fecha-rango?inicio=...&fin=... - Por fecha
POST   /api/ventas               - Crear
PUT    /api/ventas/{id}/completar - Completar
PUT    /api/ventas/{id}/cancelar - Cancelar
GET    /api/ventas/reportes/ingresos-totales   - Reportes
GET    /api/ventas/reportes/promedio-ventas    - Reportes
```

## ✅ Validaciones

### Categoría
- `nombre`: Obligatorio, único

### Producto
- `nombre`: Obligatorio
- `precio`: Obligatorio, >= 0
- `stock`: Obligatorio, >= 0
- `categoria`: Obligatoria

### Cliente
- `nombre`: Obligatorio
- `email`: Obligatorio, válido, único
- `telefono`: Obligatorio, 10 dígitos
- `direccion`: Obligatoria

### Venta
- `cliente`: Obligatorio
- `detalles`: Al menos 1 producto

## 📋 Reglas de Negocio

1. No se puede vender más de lo disponible en stock
2. Al completar una venta, se reduce automáticamente el stock
3. Solo se pueden cancelar ventas pendientes
4. No se pueden eliminar categorías con productos
5. No se pueden eliminar clientes con ventas

## 🛠️ Tecnologías

- Spring Boot 3.2.5
- Spring Data JPA
- Hibernate ORM
- H2/MySQL
- Lombok
- Springdoc OpenAPI (Swagger)
- Maven

## 📝 Ejemplo de Uso

**Crear una venta:**
```json
POST /api/ventas

{
  "clienteId": 1,
  "detalles": [
    {
      "productoId": 1,
      "cantidad": 2
    },
    {
      "productoId": 5,
      "cantidad": 1
    }
  ]
}
```

## 📧 Soporte

Para reportar problemas, abre un issue en el repositorio.

---

**Hecho con ❤️ por HenSebas**