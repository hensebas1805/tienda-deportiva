# Tienda Deportiva - Sistema Completo (Swing + API + BD)

**Sistema de gestión de tienda deportiva** con interfaz gráfica Swing que consume una API REST desarrollada con Spring Boot.

---

## 📋 Contenido del Proyecto

Este proyecto incluye:
- ✅ **Backend REST API** (Spring Boot 3.2.5)
- ✅ **Frontend Swing** (Interfaz gráfica Java)
- ✅ **Base de Datos** (H2/MySQL)
- ✅ **CRUD Completo** (Categorías, Productos, Clientes, Ventas)

---

## 🚀 Instalación y Ejecución

### 1️⃣ Requisitos
- Java 17+
- Maven 3.6+
- IntelliJ IDEA (recomendado)

### 2️⃣ Descargar/Clonar
```bash
git clone https://github.com/hensebas1805/tienda-deportiva.git
cd tienda-deportiva
git checkout semana-3-ui-swing
```

### 3️⃣ Importar en IntelliJ
- **File → Open** → Selecciona la carpeta `tienda-deportiva-main`
- Maven descargará dependencias automáticamente

### 4️⃣ Compilar Proyecto
```bash
cd tienda-deportiva-main
mvn clean install
```

---

## 🎯 Ejecutar el Sistema

### **PASO 1: Iniciar Backend (API)**

**Opción A - Desde IntelliJ:**
1. Busca: `TiendaDeportivaApplication.java`
2. Click derecho → **Run 'TiendaDeportivaApplication'**
3. Espera a ver: `Started TiendaDeportivaApplication in X seconds`

**Opción B - Desde terminal:**
```bash
cd tienda-deportiva-main
mvn spring-boot:run
```

✅ **La API estará en:** `http://localhost:8080/api`

### **PASO 2: Iniciar Frontend (Swing)**

1. Busca: `MainFrame.java` (en `src/main/java/com/tienda/deportiva/ui/`)
2. Click derecho → **Run 'MainFrame.main()'**
3. ¡Aparece la ventana de la aplicación!

---

## 📊 Acceder a la API

Mientras el Backend está corriendo:

- **Swagger UI** (ver todos los endpoints): http://localhost:8080/api/swagger-ui.html
- **OpenAPI JSON**: http://localhost:8080/api/v3/api-docs
- **Consola H2** (base de datos): http://localhost:8080/api/h2-console

---

## 🎨 Interfaz Gráfica (Swing)

La aplicación Swing tiene **4 pestañas principales:**

### 📦 **Pestaña 1: Productos**
- ✅ Ver lista de productos
- ✅ Crear producto (Nombre, Precio, Stock, SKU, Descripción)
- ✅ Actualizar producto
- ✅ Eliminar producto
- ✅ Refrescar lista

### 👥 **Pestaña 2: Clientes**
- ✅ Ver lista de clientes
- ✅ Crear cliente (Nombre, Email, Teléfono, Dirección, Ciudad)
- ✅ Actualizar cliente
- ✅ Eliminar cliente
- ✅ Refrescar lista

### 🏷️ **Pestaña 3: Categorías**
- ✅ Ver lista de categorías
- ✅ Crear categoría (Nombre, Descripción)
- ✅ Refrescar lista

### 💳 **Pestaña 4: Ventas**
- ✅ Ver lista de ventas
- ✅ Crear nueva venta
- ✅ Completar venta
- ✅ Cancelar venta
- ✅ Refrescar lista

---

## 🔌 Endpoints API (REST)

### **Categorías**
```
GET    /api/categorias              - Listar todas
POST   /api/categorias              - Crear nueva
GET    /api/categorias/{id}         - Obtener por ID
PUT    /api/categorias/{id}         - Actualizar
DELETE /api/categorias/{id}         - Eliminar
GET    /api/categorias/buscar       - Buscar por nombre
```

### **Productos**
```
GET    /api/productos               - Listar todas
POST   /api/productos               - Crear nuevo
GET    /api/productos/{id}          - Obtener por ID
PUT    /api/productos/{id}          - Actualizar
DELETE /api/productos/{id}          - Eliminar
GET    /api/productos/buscar        - Buscar por nombre
```

### **Clientes**
```
GET    /api/clientes                - Listar todos
POST   /api/clientes                - Crear nuevo
GET    /api/clientes/{id}           - Obtener por ID
PUT    /api/clientes/{id}           - Actualizar
DELETE /api/clientes/{id}           - Eliminar
GET    /api/clientes/buscar         - Buscar por nombre
```

### **Ventas**
```
GET    /api/ventas                  - Listar todas
POST   /api/ventas                  - Crear nueva
GET    /api/ventas/{id}             - Obtener por ID
PUT    /api/ventas/{id}/completar   - Completar venta
PUT    /api/ventas/{id}/cancelar    - Cancelar venta
```

---

## 📁 Estructura del Proyecto

```
tienda-deportiva-main/
├── src/main/java/com/tienda/deportiva/
│   ├── TiendaDeportivaApplication.java     # Punto de entrada Backend
│   ├── controller/                          # REST Controllers
│   ├── service/                             # Lógica de negocio
│   ├── repository/                          # Acceso a datos
│   ├── model/                               # Entidades JPA
│   ├── dto/                                 # Data Transfer Objects
│   ├── exception/                           # Manejo de errores
│   ├── config/                              # Configuraciones
│   └── ui/                                  # INTERFAZ SWING
│       ├── MainFrame.java                   # Ventana principal
│       ├── ProductoPanel.java               # Gestión productos
│       ├── ClientePanel.java                # Gestión clientes
│       ├── CategoriaPanel.java              # Gestión categorías
│       ├── VentaPanel.java                  # Gestión ventas
│       └── ApiClient.java                   # Cliente HTTP REST
│
├── src/main/resources/
│   └── application.properties               # Configuración
│
├── pom.xml                                  # Dependencias Maven
└── README.md                                # Este archivo
```

---

## ⚙️ Configuración Base de Datos

### **H2 (por defecto - en memoria)**
```properties
spring.datasource.url=jdbc:h2:mem:tiendadeportiva
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

### **MySQL (opcional)**
Edita `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tienda_deportiva
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=tuContraseña
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=create-drop
```

---

## ✅ Validaciones y Reglas de Negocio

### **Categoría**
- Nombre: Obligatorio, único
- Descripción: Opcional

### **Producto**
- Nombre: Obligatorio
- Precio: Obligatorio, >= 0
- Stock: Obligatorio, >= 0
- SKU: Único
- Categoría: Obligatoria

### **Cliente**
- Nombre: Obligatorio
- Email: Obligatorio, único, válido
- Teléfono: Obligatorio
- Dirección: Obligatoria
- Ciudad: Opcional

### **Venta**
- Cliente: Obligatorio
- Detalles: Al menos 1 producto
- Cantidad: >= 1 y <= stock disponible
- Estado: PENDIENTE → COMPLETADA/CANCELADA

### **Reglas**
✅ No se puede vender más de lo que hay en stock
✅ Al completar, se reduce automáticamente el stock
✅ Solo se cancelan ventas PENDIENTES
✅ No se eliminan categorías con productos
✅ No se eliminan clientes con ventas

---

## 🛠️ Tecnologías Utilizadas

| Componente | Tecnología |
|-----------|-----------|
| **Backend** | Spring Boot 3.2.5 |
| **Frontend** | Java Swing |
| **ORM** | Spring Data JPA / Hibernate |
| **Base de Datos** | H2 / MySQL |
| **HTTP Client** | Java 11+ HttpClient |
| **JSON** | Gson 2.10.1 |
| **Build Tool** | Maven |
| **Java** | 17+ |

---

## 📝 Ejemplo de Uso Completo

### **Flujo de ejemplo:**

1. **Crear Categoría**
   - Pestaña "Categorías"
   - Ingresa: Nombre = "Ropa", Descripción = "Prendas deportivas"
   - Click "Crear"

2. **Crear Producto**
   - Pestaña "Productos"
   - Ingresa: Nombre = "Camiseta", Precio = 29.99, Stock = 50, SKU = "CAMI001"
   - Click "Crear"

3. **Crear Cliente**
   - Pestaña "Clientes"
   - Ingresa: Nombre = "Juan Pérez", Email = "juan@email.com", Teléfono = "1234567890"
   - Click "Crear"

4. **Crear Venta**
   - Pestaña "Ventas"
   - Selecciona cliente y producto
   - Ingresa cantidad
   - Click "Crear Venta"

5. **Completar Venta**
   - Selecciona la venta de la lista
   - Click "Completar"
   - ✅ Stock se actualiza automáticamente

---

## 🐛 Solución de Problemas

### **"Connection refused" en la UI**
- ✅ Verifica que el Backend esté corriendo (http://localhost:8080/api/v3/api-docs)
- ✅ Asegúrate de ejecutar `TiendaDeportivaApplication` primero

### **"Port 8080 already in use"**
```bash
# Cambiar puerto en application.properties
server.port=8081
```

### **Maven no descarga dependencias**
```bash
mvn clean dependency:resolve
```

### **Errores de validación en la UI**
- ✅ Todos los campos marcados son obligatorios
- ✅ El email debe ser válido
- ✅ El teléfono debe tener 10 dígitos
- ✅ Precio y Stock deben ser números positivos

---

## 📚 Documentación API Interactiva

Mientras el Backend esté corriendo, visita:
**http://localhost:8080/api/swagger-ui.html**

Ahí puedes:
- ✅ Ver todos los endpoints
- ✅ Probar los endpoints directamente
- ✅ Ver ejemplos de request/response

---

## 👨‍💻 Autor

**Desarrollado por:** HenSebas  
**Fecha:** Semana 3 - 2026

---

## 📄 Licencia

Este proyecto es de código abierto y está disponible bajo la licencia Apache 2.0.

---

## 📞 Soporte

Para reportar problemas o sugerencias:
- Abre un **Issue** en GitHub
- Email: marbenzk18@gmail.com

---

**¡Listo para usar! 🎉**
