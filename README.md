# Ejercicio Resuelve - Cristian Mendivelso

Encontrar la cantidad de facturas entre 2 fechas

Pre-requisitos: 
  - Tener Instalado y configurado Java
  - Tener Git (Opcional)

En una terminal de linux se descarga el repositorio con el siguiente comando (En caso de no tener git puede descargar el proyecto como zip https://github.com/CristianMendivelso/Res-Ejercicio/archive/master.zip)
```sh
$ git clone https://github.com/CristianMendivelso/Res-Ejercicio
```
Una vez descargado se debe acceder a la carpeta que contiene el proyecto 
```sh
$ cd Res-Ejercicio/
```
Se debe cambiar a la rama actual

```sh
$ git checkout tipo2Respuesta
```

Allí se debe compilar el proyecto con el comando:
```sh
$ mvn compile
```
Una vez compilado se puede ejecutar con el siguiente comando, por favor tener en cuenta las siguientes observaciones acerca de los parámetros
  - url: Url del servicio del cual se va a hacer uso (en este caso http://34.209.24.195/facturas)
  - id: id del cliente sobre el cual se va a consultar
  - startDate: Fecha Inicial del periodo a consultar en formato YYYY-MM-DD
  - finishDate: Fecha Final del periodo a consultar en formato YYYY-MM-DD
  
```sh
$  mvn exec:java -Dexec.mainClass=tn.esprit.resuelve.ejercicio.QuantityOfInvoices -Dexec.args="'url' 'id' 'startDate' 'finishDate'"
```
Por ejemplo podría ejecutar la siguiente consulta
```sh
$  mvn exec:java -Dexec.mainClass=tn.esprit.resuelve.ejercicio.QuantityOfInvoices -Dexec.args="'http://34.209.24.195/facturas' '4e25ce61-e6e2-457a-89f7-116404990967' '2017-01-01' '2017-03-30'"

```
