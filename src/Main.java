import java.util.InputMismatchException;
import java.util.Scanner;

public class Main{
    private static Scanner scanner = new Scanner(System.in);
    private static Caso caso;

    public static void main(String[] args){
        System.out.println("----Agencia de detectives");

        caso = crearCaso();
        int opcion = 0;
        while(opcion != 13){
            menu();
            try {
                opcion = leerEntero("seleccionar una opcion:");
                ejecutarOpcion(opcion);
            } catch (InputMismatchException e) {
                System.out.println("Erorr: debes de ingresar un numero entero");
                scanner.nextLine();
                opcion = 0;
            } catch(IllegalArgumentException e){
                System.out.println("Erorr" + e.getMessage());
            }finally {
                System.out.println("================================");
            }

        }
        scanner.close();
    }

    private static void menu(){
        System.out.println();
        System.out.println("Caso actual" + caso.getnombre() + "[" + caso.getcodigo() + "]");
        System.out.println("1. Nuevo caso");
        System.out.println("2. Registrar Ubicacion");
        System.out.println("3. Consultar ubicaciones");
        System.out.println("4. consultar una ubicacion");
        System.out.println("5. Modificar ubicacion");
        System.out.println("6. Descartar ubicacion");
        System.out.println("7. Registrar pista");
        System.out.println("8. Consultar pistas");
        System.out.println("9. Buscar pista");
        System.out.println("10. Modificar pista");
        System.out.println("11. Eliminar pista");
        System.out.println("12. Mostrar reporte de investigacion");
        System.out.println("13. salir");
    }

    private static void ejecutarOpcion(int opcion){
        switch(opcion){
            case 1:
                caso = crearCaso();
                System.out.print("Nuevo caso creadi");
                break;
            case 2:
                registrarUbicacion();
                break;
            case 3:
                System.out.print(caso.listarUbicaciones());
                break;
            case 4:
                consultarUbicacion();
            case 5:
                modificarUbicacion();
                break;
            case 6:
                descartarUbicacion();
                break;
            case 7:
                registrarPista();
                break;
            case 8:
                System.out.print(caso.listarPistas());
                break;
            case 9:
                buscarPista();
                break;
            case 10:
                modificarPista();
                break;
            case 11:
                eliminarPista();
                break;
            case 12:
                System.out.print(caso.generarReporte());
            case 13:
                System.out.println("Sistema cerrado");
                break;
            default:
                System.out.println("Opcion no valida");
        }
    }

    private static int leerEntero(String mensaje){
        System.out.print(mensaje);
        int numero = scanner.nextInt();
        scanner.nextLine();
        return numero;
    }

    private static String leerTexto(String mensaje){
        System.out.print(mensaje);
        return scanner.nextLine();
    }

    private static Caso crearCaso(){
        Caso nuevo = null;
        while(nuevo == null){
            try {
                String nombre = leerTexto("nombre del caso:");
                String codigo = leerTexto("Codigo de identificacion:");
                String detective = leerTexto("Nombre del detective:");
                nuevo = new Caso(nombre, codigo, detective);
            } catch (IllegalArgumentException e) {
                System.out.println("Error" + e.getMessage());
            }
        }
        return nuevo;
    }

    private static void registrarUbicacion(){
        int posicion = leerEntero("Posicion donde registrar:" + (Caso.MAX_UBICACIONES - 1));
        caso.verificarPosiciondisponible(posicion);
        String codigo = leerTexto("Codigo de la ubicacion:");
        String nombre = leerTexto("Nombre:");
        String direccion = leerTexto("Direccion:");
        int riesgo = leerEntero("Nivel de riesgo de 1 a 10:");
        String Estado = leerTexto("Estaso:");

        Ubicacion nueva = new Ubicacion(codigo, nombre, direccion, riesgo, Estado);
        caso.registrarUbicacion(posicion, nueva);
        System.out.println("Ubicacion registrada en la posicion:" + posicion);
    }

    private static void consultarUbicacion(){
        int posicion = leerEntero("posicion a consultar" + (Caso.MAX_UBICACIONES - 1));
        Ubicacion ubicacion = caso.obtenerUbicacion(posicion);

        if(ubicacion == null){
            System.out.println("La posicion" + posicion + "esta vacia");
        }
        else{
            System.out.println(ubicacion);
        }
    }

    private static void modificarUbicacion(){
        int posicion = leerEntero("Posicion de la ubicacion a moodificar:");
        int riesgo = leerEntero("Nuevo nivel de riesgo:");
        String Estado = leerTexto("Nuevo estado:");
        System.out.println("Ubicacion modificada");
    }

    private static void descartarUbicacion(){
        int posicion = leerEntero("Posicion de la ubicacion por eliminar:");
        caso.descartarUbicacion(posicion);
        System.out.println("Ubicacion descartada, Posicion:" + posicion + "quedo dsiponible");
    }

    private static void registrarPista(){
        String codigo = leerTexto("Codigo de la pista:");
        String descripcion = leerTexto("DEscripciom:");
        String tipo = leerTexto("Tipo de evidencia:");
        int importancia = leerEntero("Nivel de importancia:");
        int confiabilidad = leerEntero("Nivel de confibilidad:");

        Pista nueva = new Pista(codigo, descripcion, tipo, importancia, confiabilidad);
        caso.registrarPista(nueva);
        System.out.println("Pista a sido registrada de manera correcta");
    }

    private static void buscarPista(){
        String codigo = leerTexto("Codigo de la pista buscar:");
        Pista pista = caso.buscarPista(codigo);
        if(pista == null){
            System.out.println("No se encontro la pista con el codigo:" + codigo);
        }
        else{
            System.out.println(pista);
        }
    }

    private static void modificarPista(){
        String codigo = leerTexto("Codigo de la psita a modificar:");
        if(caso.buscarPista(codigo) == null){
            System.out.println("No se encontro una pista con el codigo:" + codigo);
        }

        String descripcion = leerTexto("Nueva descripcion:");
        String tipo = leerTexto("Nuevo tipo de evidencia:");
        int importancia = leerEntero("Nueva importancia:");
        int confiabilidad = leerEntero("Nueva confiabilidad:");

        caso.modificarPista(codigo, descripcion, tipo, importancia, confiabilidad);
        System.out.println("Pista modificada de manera correcta");
    }

    private static void eliminarPista(){
        String codigo = leerTexto("Codigo de la pista a eliminar:");
        caso.eliminarPista(codigo);
        System.out.println("Pista eliminada");
    }


}