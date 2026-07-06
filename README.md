# Taller 4 POO - Coleccion Pokemon TCG

App en Java con Swing para administrar una coleccion de cartas del Pokemon TCG (el juego de cartas). La aplicacion carga las cartas desde un archivo de texto, las muestra en una ventana con dos pestañas y permite agregar, editar y borrar cartas, guardando todo de vuelta en el mismo archivo.

## Integrantes

- Vicente Guerra - 21.855.415-6 - nemura0
- Luis Molina - 21.564.225-9 - mixolydiann

## Que hace

La idea es tener una especie de "album" digital de cartas. La aplicacion tiene dos pestañas:

- **Administracion**: permite agregar cartas nuevas, editar las que ya existen y eliminar las que no. Cada cambio se guarda automaticamente en el archivo `Sobres.txt`.
- **Ver Coleccion**: muestra todas las cartas en una lista, que se puede ordenar por rareza, nombre o poder. Al hacer clic en una carta se abre una ventana mas grande con su imagen y sus datos.

Hay cuatro tipos de cartas, cada una con sus propios datos:

- **Pokemon**: tiene daño y cantidad de energias.
- **Item**: tiene un bonus.
- **Supporter**: tiene efectos por turno.
- **Energy**: tiene un elemento (Fuego, Agua, etc).

## Como ejecutarlo

El proyecto esta hecho como proyecto de Eclipse y usa Java 21, asi que es bastante simple.

### Desde Eclipse

Se importa el proyecto y se ejecuta la clase `App.java`.

### Desde la terminal/cmd

Importante: debe ejecutarse **parado en la carpeta raiz del proyecto**, porque el programa busca el archivo `Sobres.txt` y las imagenes de la carpeta `img/` usando rutas relativas. Si se corre desde otra carpeta no encuentra los archivos.

```bash
# compilar todo dentro de la carpeta bin
javac -d bin $(find src -name '*.java')

# ejecutar
java -cp bin logica.App
```

## Como estan organizadas las cartas (Sobres.txt)

Todas las cartas se guardan en el archivo `Sobres.txt`, una carta por linea. Los datos van separados por punto y coma (`;`). El formato cambia un poco segun el tipo de carta:

```
Pokemon:    Nombre;Rareza;Pokemon;Daño;CantidadEnergia    ->  Pikachu;1;Pokemon;60;1
Item:       Nombre;Rareza;Item;Bonus                      ->  Potion;1;Item;10
Supporter:  Nombre;Rareza;Supporter;EfectosPorTurno       ->  Hop;1;Supporter;2
Energy:     Nombre;Rareza;Energy;Elemento                 ->  Fire Energy;1;Energy;Fire
```

Si el archivo tiene una linea con un tipo que no existe, el programa simplemente la ignora y sigue.

## Imagenes

Las imagenes de las cartas estan en la carpeta `img/`. El programa las busca por el nombre exacto de la carta (por ejemplo `Pikachu.png`). Si una carta no tiene imagen no pasa nada: se dibuja un recuadro gris que dice "sin imagen" para que la interfaz no se rompa.

## Como esta armado el codigo

El proyecto esta dividido en paquetes segun lo que hace cada parte:

- **`dominio/`**: contiene las cartas. `Carta` es la clase abstracta que tiene lo comun (nombre, rareza, tipo) y de ella heredan `Pokemon`, `Item`, `Supporter` y `Energy`.
- **`patrones/`**: contiene los patrones de diseño que pide el taller:
  - **Factory** (`CartaFactory`): toma una linea de texto y crea la carta del tipo correcto.
  - **Visitor** (`CartaVisitor`): calcula el "poder" de cada carta segun su tipo.
  - **Strategy** (`OrdenPorNombre`, `OrdenPorRareza`, `OrdenPorPoder`): cada forma de ordenar la coleccion es una clase aparte.
- **`logica/`**: `SistemaImpl` es el nucleo de la aplicacion (es un Singleton). Mantiene la lista de cartas en memoria y se encarga de cargar, guardar, agregar, editar, borrar y ordenar.
- **`gui/`**: toda la parte visual con Swing (las ventanas, pestañas y la vista ampliada de las cartas).

## Detalle sobre el calculo del poder

Cada tipo de carta calcula su poder distinto (esto lo hace el Visitor):

- **Pokemon**: `(daño / cantidad de energias) * 100` (division entera). Si tiene 0 energias, el poder es 0 para que no falle.
- **Item**: `bonus * 20`
- **Supporter**: `efectos por turno * 50`
- **Energy**: siempre vale 1.

## Notas

- Todo el codigo y los comentarios estan en español.
- El archivo `Sobres.txt` es la unica "base de datos": no se usa ninguna base de datos real, todo se guarda en ese texto.
