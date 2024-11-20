# Voluntariado App

## Descripción
Esta aplicación móvil conecta a voluntarios con oportunidades de servicio comunitario. Los usuarios pueden buscar actividades basadas en sus intereses y ubicación, registrarse en ellas y visualizar próximas oportunidades mediante un widget.

### Funcionalidades principales:
1. **Búsqueda de oportunidades**: Sistema de búsqueda y filtrado para encontrar actividades de voluntariado.
2. **Registro en actividades**: Los usuarios pueden registrarse en oportunidades de su interés.
3. **Widget en pantalla de inicio**: Muestra las próximas oportunidades de voluntariado más cercanas.
4. **Sugerencias basadas en ubicación**: Filtra y ordena actividades según la proximidad del usuario.
5. **Gestión de perfil**: Edición de datos personales como habilidades, intereses y ubicación.

---

## Estructura del Proyecto

### 1. **Clases principales**
- **`MainActivity`**:
  - Administra los fragmentos de la aplicación y la navegación mediante un `BottomNavigationView`.

- **`FragmentHome`**:
  - Pantalla principal de bienvenida, diseñada para ser visualmente atractiva.

- **`FragmentProfile`**:
  - Permite al usuario editar y guardar su información personal (nombre, ubicación, habilidades e intereses).

- **`FragmentSearch`**:
  - Contiene el sistema de búsqueda y filtrado de oportunidades.
  - Utiliza sensores de ubicación para ordenar actividades según proximidad.

- **`VolunteerOpportunity`**:
  - Clase modelo que representa las oportunidades de voluntariado, incluyendo detalles como nombre, descripción, horario y habilidades requeridas.

- **`VolunteerAdapter`**:
  - Adaptador para mostrar las oportunidades en un `RecyclerView`.

- **`VolunteerWidget`**:
  - Proporciona un widget para la pantalla de inicio que muestra las tres oportunidades más relevantes.

---

## Relación entre Clases
1. **`MainActivity`**:
   - Coordina los fragmentos principales (`FragmentHome`, `FragmentProfile`, `FragmentSearch`).
   
2. **`FragmentSearch`**:
   - Gestiona las oportunidades mediante la clase modelo `VolunteerOpportunity`.
   - Usa `VolunteerAdapter` para mostrar las actividades en una lista.

3. **`VolunteerWidget`**:
   - Utiliza una lista de `VolunteerOpportunity` para mostrar las próximas actividades en un widget.

4. **`FragmentProfile`**:
   - Permite guardar y cargar datos de usuario usando `SharedPreferences`.

---

## Cómo funciona la aplicación

### Inicio
- El usuario inicia la aplicación y se encuentra en la pantalla de inicio (`FragmentHome`), que contiene un diseño limpio y minimalista.

### Gestión de perfil
- En el perfil (`FragmentProfile`), el usuario puede guardar sus datos personales. Estos datos son utilizados para destacar habilidades coincidentes con las actividades disponibles.

### Búsqueda de actividades
- En el buscador (`FragmentSearch`), se listan oportunidades ordenadas por cercanía a la ubicación del usuario.
- El sistema de búsqueda permite filtrar actividades según palabras clave como el nombre, la ciudad o el país.

### Registro
- Al seleccionar una actividad, el usuario puede registrarse en ella. Esto genera un mensaje en pantalla y en los registros del sistema (`Logcat`).

### Widget
- El widget muestra las tres actividades más cercanas al usuario directamente en la pantalla de inicio.

---

## Cómo cumple la solución con el enunciado

1. **Promoción del voluntariado**:
   - La interfaz incentiva la participación activa mediante un diseño amigable y sugerencias basadas en intereses y ubicación.

2. **Sistema de búsqueda y filtrado**:
   - Los usuarios pueden buscar oportunidades de forma eficiente usando palabras clave o filtros automáticos.

3. **Uso de sensores de ubicación**:
   - La ubicación del dispositivo se utiliza para priorizar actividades cercanas.

4. **Widget**:
   - El widget hace que las oportunidades más relevantes sean visibles desde la pantalla de inicio.

