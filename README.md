
# PowerZone README


## Índice
1. [Descripción del Proyecto](#descripción-del-proyecto)
2. [Características Principales](#características-principales)
3. [Tecnologías Utilizadas](#tecnologías-utilizadas)
4. [Instalación y Configuración](#instalación-y-configuración)
5. [Uso de la Aplicación](#uso-de-la-aplicación)
6. [Próximas Actualizaciones](#próximas-actualizaciones)
7. [Contribuciones](#contribuciones)
   
## Descripción del Proyecto

**PowerZone** es una aplicación de Android diseñada con **Jetpack Compose** que tiene como objetivo ayudar a los usuarios a mejorar su experiencia en el gimnasio. La aplicación incluye funciones esenciales como inicio de sesión de usuario mediante Firebase, ajustes personalizados para mejorar la experiencia del usuario, y un apartado educativo para aprender técnicas adecuadas de ejercicios


---

## Características Principales

- **Inicio de Sesión Seguro**: Soporte para registro e inicio de sesión con Firebase.
- **Ajustes Personalizados**: Configuración guardada en la sesión de cada usuario. Se adaptará en el futuro con inteligencia artificial
- **Tutoriales de Ejercicios**: Aprende técnicas correctas organizadas por grupos musculares.
- **Progreso**: Registra tus sesiones de entrenamiento para llevar un seguimiento efectivo.
- **Modo Oscuro/Claro**: Adopta el tema del sistema del dispositivo automáticamente.
- **Soporte Multilingüe**: Disponible en español e inglés.

---

## Tecnologías Utilizadas

- **Kotlin**: Lenguaje de programación moderno, seguro y eficiente para Android.
- **Jetpack Compose**: Framework de UI declarativa que permite un desarrollo rápido y flexible.
- **Firebase Authentication**: Proporciona autenticación confiable y escalable.
- **Spring Boot + Kotlin**: Facilita la creación de APIs REST y microservicios.

---

## Instalación y Configuración

### Requisitos Previos:
- Android Studio ladybug o superior.
- Android SDK 21.
- Dependencias sincronizadas con Gradle.

### Pasos:

1. **Clona el repositorio**:
   ```bash
   git clone https://github.com/AGALMAD/PowerZone.git
   ```

2. **Configura Firebase**:
   - Accede a [Firebase Console](https://console.firebase.google.com) y crea un proyecto.
   - Descarga el archivo `google-services.json` y colócalo en la carpeta `app` de tu proyecto.
   - Configura Firebase Authentication activando el método de autenticación por correo electrónico y contraseña.

3. **Configura la API Mocky**:
   - Accede a [Mocky](https://designer.mocky.io/).
   - Crea un nuevo mock.
   - Añade tus datos en formato JSON en el HTTP Response Body.
   - Obtén la URL y úsala en tu servicio añadiendo el último tramo de esta.

4. **Compila y ejecuta**:
   - Abre el proyecto en Android Studio.
   - Sincroniza las dependencias y ejecuta la aplicación en un emulador o dispositivo físico.

---

## Uso de la Aplicación

1. **Inicio de sesión**: Regístrate o inicia sesión utilizando tu correo y contraseña para personalizar tus datos.
2. **Explora Tutoriales**: Accede al apartado `Aprender`, donde encontrarás tutoriales organizados por grupos musculares.
3. **Registra tus sesiones de entrenamiento**: Accede al apartado `Progreso`, donde podras llevar un registro del progreso diario.

---

## Próximas Actualizaciones

- API REST con Spring Boot para almacenamiento y autenticación propia.
- Implementación de una IA que genere rutinas de gimnasio personalizadas.
- Estadísticas del progreso del usuario.

---

## Contribuciones

¡Contribuciones son bienvenidas! Si deseas colaborar, por favor:

1. Haz un fork del repositorio.
2. Crea una rama para tu funcionalidad (`feature/nueva-funcion`).
3. Realiza un pull request con una descripción detallada de los cambios.

---


¡Gracias por usar PowerZone! 💪 Si tienes dudas o sugerencias, no dudes en abrir un issue en el repositorio. 🚀
