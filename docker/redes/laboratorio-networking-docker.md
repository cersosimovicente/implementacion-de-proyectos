# Laboratorio de Networking Avanzado en Docker

## Objetivo
Aprender a configurar una topología de red avanzada en Docker, incluyendo el uso de contenedores como routers.

## Topología de red
![topologia de red](imgs/TopologíadeRedenDocker.drawio.png)


## Instrucciones

1. Crear redes personalizadas:
    ```bash
    docker network create red1
    docker network create red2
    ```

2. Iniciar los contenedores:
    ```bash
    docker run -d --name servidor1 --network red1 nginx:latest
    docker run -it --name cliente1 --network red1 curlimages/curl
    docker run -d --name servidor2 --network red2 nginx:latest
    docker run -it --name cliente2 --network red2 curlimages/curl
    docker build -t router .
    docker run -d --name router --network red1 --privileged router
    docker network connect red2 router
    ```

3. Configurar el router:
    ```bash
    docker exec -it router sh
    echo 1 > /proc/sys/net/ipv4/ip_forward
    iptables -t nat -A POSTROUTING -o eth1 -j MASQUERADE
    iptables -A FORWARD -i eth1 -o eth0 -m state --state RELATED,ESTABLISHED -j ACCEPT
    iptables -A FORWARD -i eth0 -o eth1 -j ACCEPT
    ```

4. Verificar la conectividad y resolución de nombres:
    ```bash
    docker exec -it cliente1 curl servidor1
    docker exec -it cliente1 curl <IP-del-servidor2>
    docker exec -it cliente2 curl servidor2
    docker exec -it cliente2 curl <IP-del-servidor1>
    ```

## Estructura del Repositorio

- `Dockerfile`: Archivo de configuración para el router
- `README.md`: Instrucciones detalladas

