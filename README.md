
# PowerZone README

## Descripción del Proyecto

**PowerZone** es una aplicación de Android diseñada con **Jetpack Compose** que tiene como objetivo ayudar a los usuarios a mejorar su experiencia en el gimnasio. La aplicación incluye funciones esenciales como inicio de sesión de usuario mediante Firebase, ajustes personalizados para personalizar la experiencia del usuario, y un apartado educativo para aprender técnicas adecuadas de ejercicios


---

## Características Principales

### 1. **Inicio de Sesión con Firebase**
- Autenticación segura y rápida utilizando Firebase Authentication.
- Permite el registro e inicio de sesión mediante correo electrónico y contraseña.

### 2. **Ajustes Personalizados**
- Configuración ajustable por el usuario para personalizar la experiencia.
- Preparación para futura integración de inteligencia artificial que generarán rutinas de gimnasio adaptadas a las necesidades del usuario.

### 3. **Aprende Técnicas de Ejercicios**
- Un apartado dedicado para enseñar las técnicas correctas de los ejercicios más comunes.
- Integración con la API **Moki** para proporcionar el mejor contenido seleccionado.

### 4. **Temas**
- La aplicación obtiene el tema que el usuario tiene establecido en su dispositivo y lo establece en la aplicación

### 5. **Idiomas**
- La aplicación está disponible tanto en español como en ingles

---

## Tecnologías Utilizadas

- **Lenguaje de Programación:** Kotlin
- **Arquitectura:** Jetpack Compose
- **Backend:** Firebase Authentication
- **API:** Hosteada en mocky.io con datos propios

---

## Instalación y Configuración

### 1. Clona el repositorio
```bash
git clone https://github.com/AGALMAD/PowerZone.git
```

### 2. Configura Firebase
- Accede a [Firebase Console](https://console.firebase.google.com) y crea un proyecto.
- Descarga el archivo `google-services.json` y colócalo en la carpeta `app` de tu proyecto.
- Configura Firebase Authentication activando el método de autenticación por correo electrónico y contraseña.

### 3. Configura la API Moki
- Accede a https://designer.mocky.io/
- Añade HTTP Response Body tus datos en formato json
- Obten la url y en el servicio añade el ultimo tramo de esta

### 4. Compila y ejecuta la aplicación
Abre el proyecto en Android Studio, sincroniza las dependencias y ejecuta la aplicación en un emulador o dispositivo físico.

---

## Uso de la Aplicación

1. Para poder introducir datos personalizados para crear una rutina es necesario regístrarse o iniciar sesión utilizando tu correo y contraseña.
2. Para poder acceder a los tutoriales debes acceder al apartado `aprender` y este se encuentra dividido en los distintos grupos musculares, en ellos encontrarás múltiples tutoriales con el que aprenderás a ejecutar la técnica correctamente

---

## Próximas Actualizaciones

- API REST con Spring Boot con el que almacenaremos los datos de los ejercicios y autenticación propia
- Implementación de una IA que genere rutinas de gimnasio personalizadas.
- Integración de estadísticas y seguimiento de progreso del usuario.

---

## Contribuciones

¡Contribuciones son bienvenidas! Si deseas colaborar, por favor:

1. Haz un fork del repositorio.
2. Crea una rama para tu funcionalidad (`feature/nueva-funcion`).
3. Realiza un pull request con una descripción detallada de los cambios.

---

## Licencia

Este proyecto está licenciado bajo la **MIT License**. Consulta el archivo `LICENSE` para más detalles.

---

¡Gracias por usar PowerZone! 💪 Si tienes dudas o sugerencias, no dudes en abrir un issue en el repositorio. 🚀
