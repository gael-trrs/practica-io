---

# Solución y Entregables de la Práctica


### Parte I: Actividad 1 (CopiarBytes)
1. **¿Por qué `read()` devuelve un `int`?**  
   Porque lee un byte a la vez (valores de 0 a 255) y necesita un rango de datos que permita devolver el valor `-1` para señalar la llegada al final del archivo.
2. **¿Qué significa `-1`?**  
   Indica la condición de *End of File* (EOF), es decir, que ya no existen más bytes por leer en el flujo de entrada.
3. **¿Qué representa la variable `dato`?**  
   Representa el valor numérico entero correspondiente al byte individual recuperado en la lectura actual.
4. **¿Podría utilizarse este mecanismo para copiar una imagen?**  
   Sí, los flujos basados en bytes (`InputStream`/`OutputStream`) son la herramienta adecuada para procesar archivos binarios como imágenes o audio.

### Parte II: Actividad 2 (CopiarCaracteres)
* **Pregunta de decisión:** ¿Qué utilizaría para procesar un archivo `.txt`? ¿Y para copiar un archivo `.jpg`?  
  Para un archivo `.txt` utilizaría flujos de caracteres (`FileReader`/`FileWriter` o `BufferedReader`), ya que gestionan la codificación de texto. Para un `.jpg` utilizaría flujos de bytes (`FileInputStream`/`FileOutputStream`).

### Parte VIII: Archivos temporales
* **¿En qué situaciones podría ser conveniente utilizar un archivo temporal en lugar de un archivo permanente?**  
  Cuando se realiza el procesamiento de datos intermedios, copias de respaldo durante ejecuciones activas o almacenamiento de archivos en caché que no se requiere conservar tras finalizar la ejecución.

### Parte IX: Miniactividad (Patrones glob)
1. Seleccionar todos los archivos Java: `*.java`
2. Seleccionar todos los archivos CSV: `*.csv`
3. Seleccionar `reporte1.txt` a `reporte9.txt`: `reporte[1-9].txt`
4. Seleccionar archivos que comiencen con `log`: `log*`

---

## Preguntas de Reflexión (Parte 20)

1. **¿Qué es un *stream*?**  
   Es un flujo secuencial continuo de datos que permite la transferencia de información entre la aplicación Java y una fuente o destino de E/S.
2. **¿Cuál es la diferencia entre un *input stream* y un *output stream*?**  
   Un *input stream* lee datos desde una fuente hacia el programa, mientras que un *output stream* escribe datos desde el programa hacia un destino externo.
3. **¿Cuál es la diferencia entre flujos de bytes y caracteres?**  
   Los flujos de bytes manejan información en bloques de 8 bits (datos binarios), mientras que los flujos de caracteres manejan datos representados mediante codificación de texto (Unicode).
4. **¿Cuándo utilizaría `FileInputStream` en lugar de `FileReader`?**  
   `FileInputStream` se utiliza al trabajar con archivos binarios (imágenes, ejecutables, archivos comprimidos), mientras que `FileReader` se utiliza para archivos de texto plano.
5. **¿Qué ventaja proporciona `BufferedReader`?**  
   Ofrece mayor eficiencia de lectura al almacenar en búfer los datos del disco y proporciona el método `readLine()` para procesar líneas completas de texto de manera sencilla.
6. **¿Qué devuelve `readLine()` cuando termina el archivo?**  
   Devuelve `null`.
7. **¿Por qué los flujos con búfer pueden mejorar el desempeño?**  
   Porque reducen la cantidad de llamadas directas y lecturas de disco al sistema operativo, acumulando los datos en un bloque de memoria temporal.
8. **¿Qué diferencia existe entre una ruta absoluta y una relativa?**  
   Una ruta absoluta detalla la ubicación completa desde la raíz del sistema de archivos, mientras que la relativa se evalúa tomando como referencia el directorio de trabajo actual.
9. **¿Qué representa un objeto `Path`?**  
   Es una representación de la ubicación de un archivo o directorio dentro de la API `java.nio.file`.
10. **¿Cuál es la responsabilidad de la clase `Files`?**  
    Proporciona operaciones estáticas sobre archivos y directorios a través de objetos `Path` (creación, copia, eliminación, consulta de propiedades, etc.).
11. **¿Qué hace `Files.exists()`?**  
    Verifica si el archivo o directorio especificado por la ruta existe actualmente en el sistema de archivos.
12. **¿Para qué se utiliza `resolve()`?**  
    Para combinar o unir dos rutas, anexando una subruta a un directorio base.
13. **¿Qué ventaja proporciona `Files.createDirectories()`?**  
    Crea la carpeta especificada en la ruta e incluye la creación automática de cualquier directorio padre faltante en la estructura.
14. **¿Qué es una expresión *glob*?**  
    Es un patrón sintáctico utilizado para realizar búsquedas o filtrados de archivos basados en caracteres comodín (como `*` o `?`).
15. **¿Qué relación existe entre un `Channel` y un `Buffer` en NIO?**  
    Los canales representan la conexión hacia las fuentes o destinos de E/S, mientras que los *buffers* actúan como los contenedores en memoria donde el canal lee o escribe la información.
16. **¿Qué mecanismo utilizaría para procesar un archivo de texto línea por línea?**  
    `BufferedReader` mediante su método `readLine()`, o `Files.readAllLines()` / `Files.lines()`.
17. **¿Qué mecanismo utilizaría para copiar una imagen?**  
    `FileInputStream` y `FileOutputStream` o `Files.copy()`.
18. **¿Qué mecanismos de E/S fueron necesarios en el sistema de incidencias?**  
    Se emplearon `Path` y la clase `Files` para la validación y gestión de rutas/directorios, `BufferedReader` para la lectura por líneas, y `PrintWriter` acoplado con `BufferedWriter` para la escritura formateada del reporte.
