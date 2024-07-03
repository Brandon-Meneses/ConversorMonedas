# Convertidor de Monedas

Este proyecto es un convertidor de monedas basado en Java que utiliza la API de ExchangeRate-API para obtener las tasas de cambio y realizar conversiones entre diferentes monedas. El programa permite al usuario ingresar un monto y seleccionar las monedas de origen y destino para realizar la conversión.

## Tabla de Contenidos

- [Descripción](#descripción)
- [Requisitos](#requisitos)
- [Instalación](#instalación)
- [Uso](#uso)
- [Código de Ejemplo](#código-de-ejemplo)
- [Contribuir](#contribuir)
- [Licencia](#licencia)

## Descripción

Este proyecto es parte del programa ONE y es un desafío técnico propuesto por la empresa para evaluar habilidades en Java, manipulación de JSON, y consumo de APIs. El objetivo es crear un convertidor de monedas que interactúe con el usuario a través de la consola y realice conversiones entre diferentes monedas utilizando datos de una API externa.

## Requisitos

- JDK 11 o superior
- Maven o Gradle (para la gestión de dependencias)
- Conexión a Internet (para acceder a la API)

## Instalación

1. **Clonar el repositorio**:

   ```bash
   git clone https://github.com/tu-usuario/convertidor-monedas.git
   cd convertidor-monedas
   
2. **Obtener la clave de la API**:
Regístrate en ExchangeRate-API y obtén tu clave de API. Reemplaza YOUR_API_KEY en el código con tu clave de API.

3. **Configurar dependencias**:

Maven
Añade la siguiente dependencia a tu archivo pom.xml:
<dependency>
    <groupId>com.google.code.gson</groupId>
    <artifactId>gson</artifactId>
    <version>2.8.6</version>
</dependency>
