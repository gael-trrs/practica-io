import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.List;

public class GeneradorReporteIncidencias {

    public static void main(String[] args) {
        // Si no se le pasan argumentos al ejecutar, toma por defecto la ruta de incidencias
        String rutaEntrada = (args.length > 0) ? args[0] : "mesa-ayuda/entrada/incidencias.txt";

        Path archivoEntrada = Path.of(rutaEntrada);

        if (!Files.exists(archivoEntrada)) {
            System.err.println("El archivo no existe: " + archivoEntrada.toAbsolutePath());
            return;
        }

        try {
            System.out.println("Nombre: " + archivoEntrada.getFileName());
            System.out.println("Ruta absoluta: " + archivoEntrada.toAbsolutePath());
            System.out.println("Tamaño: " + Files.size(archivoEntrada) + " bytes");
            System.out.println("Última modificación: " + Files.getLastModifiedTime(archivoEntrada));
        } catch (IOException e) {
            System.err.println("Error al leer propiedades del archivo: " + e.getMessage());
        }

        Path directorioSalida = Path.of("mesa-ayuda", "salida", "reportes");
        Path pathReporte = directorioSalida.resolve("reporte-incidencias.txt");
        Path pathAltas = directorioSalida.resolve("incidencias-alta.txt");

        int total = 0, altas = 0, medias = 0, bajas = 0;
        List<String> descripcionesAltas = new ArrayList<>();

        try {
            Files.createDirectories(directorioSalida);

            try (
                    BufferedReader lector = Files.newBufferedReader(archivoEntrada);
                    PrintWriter reporteAltas = new PrintWriter(Files.newBufferedWriter(pathAltas))
            ) {
                String linea;

                while ((linea = lector.readLine()) != null) {
                    total++;
                    String[] partes = linea.split("\\|");

                    if (linea.endsWith("|ALTA")) {
                        altas++;
                        reporteAltas.println(linea);
                        descripcionesAltas.add(partes[0] + " - " + partes[1]);
                    } else if (linea.endsWith("|MEDIA")) {
                        medias++;
                    } else if (linea.endsWith("|BAJA")) {
                        bajas++;
                    }
                }
            }

            try (PrintWriter reporte = new PrintWriter(Files.newBufferedWriter(pathReporte))) {
                reporte.println("REPORTE DE INCIDENCIAS");
                reporte.println("======================");
                reporte.println("\nArchivo procesado: " + archivoEntrada.getFileName());
                reporte.println("\nTotal de incidencias: " + total);
                reporte.println("\nPrioridad ALTA: " + altas);
                reporte.println("Prioridad MEDIA: " + medias);
                reporte.println("Prioridad BAJA: " + bajas);
                reporte.println("\nINCIDENCIAS DE ALTA PRIORIDAD");
                reporte.println("--\n");
                for (String desc : descripcionesAltas) {
                    reporte.println(desc);
                }
            }

            System.out.println("\nReportes generados exitosamente en: " + directorioSalida.toAbsolutePath());

        } catch (IOException e) {
            System.err.println("Error de E/S: " + e.getMessage());
        }
    }
}