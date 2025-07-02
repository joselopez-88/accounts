# 📦 Crear Imagen y Contenedor con Docker

Este documento describe los pasos necesarios para construir una imagen Docker 
a partir de un `Dockerfile` y ejecutar un contenedor en modo *detached* (`-d`),
exponiendo el puerto `8080`.

---

## 📁 Prerrequisitos

- Tener Docker instalado y funcionando en tu máquina.
- Tener un proyecto con un `Dockerfile` válido en la raíz.
- Tener packaging en jar

---

## 🛠️ Paso 1: Crear el `Dockerfile` valido en la raiz del proyecto

--
## 🔨 Paso 2: Construir la imagen Docker
docker build . -t albertoesteva88/accounts:s4

Esta instruccion incluye el usuario, el nombre
de la imagen a crear y el tag de la version
util al usarlo con docker hub para subir la imagen
posteriormente

--
## 🚀 Paso 3: Crear y ejecutar el contenedor
docker run -d -p 8080:8080 albertoesteva88/accounts:s4

Al ejecutar esta instruccion si se cambia el puerto crea un 
contenedor nuevo con un nombre diferente generado automaticamente

si se agrega el tag name se puede poner un nombre personalizado

docker run -d -p 8080:8080 --name accounts albertoesteva88/accounts:s4

