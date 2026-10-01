import java.util.ArrayList;


public class Caso{

    public static final int MAX_UBICACIONES = 5;
    private String nombre;
    private String codigo;
    private String detective;
    private Ubicacion[] Ubicaciones;
    private ArrayList<Pista> pistas;

    public Caso(String nombre, String codigo, String detective){
        validarTexto(nombre, "El nombre del caso");
        validarTexto(codigo, "El codigo del caso");
        validarTexto(detective, "El nombre del detective");

        this.nombre = nombre.trim();
        this.codigo = codigo.trim();
        this.detective = detective.trim();
        this.Ubicaciones = new Ubicacion[MAX_UBICACIONES];
        this.pistas = new ArrayList<Pista>();
    }

    private static void validarTexto(String texto, String campo){
        if(texto == null || texto.trim().isEmpty()){
            throw new IllegalArgumentException(campo + "no puede estar vacio");
        }
    }

    public String getnombre(){
        return nombre;
    }

    public String getcodigo(){
        return codigo;
    }

    public String getdetective(){
        return detective;
    }

    private void validarLimites(int posicion){
        if(posicion < 0 || posicion >= Ubicaciones.length){
            throw new IllegalArgumentException("la posicion" + posicion + "no es valida" + (Ubicaciones.length - 1));
        }
    }

    public void verificarPosiciondisponible(int posicion){
        validarLimites(posicion);
        if (Ubicaciones[posicion] != null) {
            throw new IllegalArgumentException("la posicion" + posicion + "ya esta usada");
        }
    }

    public void registrarUbicacion(int posicion, Ubicacion ubicacion){
        verificarPosiciondisponible(posicion);
        if(ubicacion == null){
            throw new IllegalArgumentException("la ubicacion no puede ser null");
        }
        Ubicaciones[posicion] = ubicacion;
    }

    public Ubicacion obtenerUbicacion(int posicion){
        validarLimites(posicion);
        return Ubicaciones[posicion];
    }

    public String listarUbicaciones(){
        String texto = "";
        for(int i = 0; i < Ubicaciones.length; i++){
            if(Ubicaciones[i] != null){
                texto += "(Posicion" + i + ")" + Ubicaciones[i];
            }
        }
        if(texto.isEmpty()){
            texto = "no hay ubicaciones registradas";
        }
        return texto;
    }

    public void modificarUbicacion(int posicion, int nuevoniveldeRiesgo, String nuevoEstado){
        validarLimites(posicion);
        if(Ubicaciones[posicion] == null){
            throw new IllegalArgumentException("la posicion" + posicion + "esta vacia, no se puede modificar nada");
        }
        Ubicaciones[posicion].modificar(nuevoniveldeRiesgo, nuevoEstado);
    }

    public void descartarUbicacion(int posicion){
        validarLimites(posicion);
        if(Ubicaciones[posicion] == null){
            throw new IllegalArgumentException("la posicion" + posicion + "esta vacia");
        }
        Ubicaciones[posicion] = null;
    }

    public int contarUbicaciones(){
        int cantidad = 0;
        for(int i = 0; i < Ubicaciones.length; i++){
            if(Ubicaciones[i] != null){
                cantidad++;
            }
        }
        return cantidad;
    }

    public int contadordeEspaciosDisponibles(){
        return Ubicaciones.length - contarUbicaciones();
    }

    public Ubicacion UbicacionConMayorRiesgo(){
        Ubicacion mayor = null;
        for(int i = 0; i < Ubicaciones.length; i++){
            if(Ubicaciones[i] != null){
                if(mayor == null || Ubicaciones[i].getniveldeRiesgo() > mayor.getniveldeRiesgo()){
                    mayor = Ubicaciones[i];
                }
            }
        }
        return mayor;
    }

    public void registrarPista(Pista pista){
        if(pista == null){
            throw new IllegalArgumentException("la pista no puede ser null");
        }
        if(buscarPista(pista.getcodigo()) != null){
            throw new IllegalArgumentException("Ya existe una pista con el codigo:" + pista.getcodigo());
        }
        pistas.add(pista);
    }

    public Pista buscarPista(String codigo){
        for(int i = 0; i < pistas.size(); i++){
            Pista actual = pistas.get(i);
            if(actual.getcodigo().equalsIgnoreCase(codigo.trim())){
                return actual;
            }
        }
        return null;
    }

    public String listarPistas(){
        if(pistas.isEmpty()){
            return "no hay una pista registrada";
        }
        String texto = "";
        for(int i = 0; i < pistas.size(); i++){
            texto += (i + 1) + "." + pistas.get(i);
        }
        return texto;
    }

    public void modificarPista(String codigo, String descripcion, String tipoEvidencia, int importancia, int confiabilidad){
        Pista pista = buscarPista(codigo);
        if(pista == null){
            throw new IllegalArgumentException("No existe una pista con el codigo:" + codigo);
        }
        pista.modificar(descripcion, tipoEvidencia, importancia, confiabilidad);
    }

    public void eliminarPista(String codigo){
        for(int i = 0; i < pistas.size(); i++){
            if(pistas.get(i).getcodigo().equalsIgnoreCase(codigo.trim())){
                pistas.remove(i);
                return;
            }
        }
        throw new IllegalArgumentException("No existe una pistas con el codigo" + codigo);
    }

    public int contarPistas(){
        return pistas.size();
    }

    public Pista pistademayorImportancia(){
        if(pistas.isEmpty()){
            return null;
        }
        Pista mayor = pistas.get(0);
        for(int i = 1; i < pistas.size(); i++){
            if(pistas.get(i).getimportancia() > mayor.getimportancia()){
                mayor = pistas.get(i);
            }
        }
        return mayor;
    }

    public Pista pistaConMayorConfiabilidad(){
        if(pistas.isEmpty()){
            return null;
        }
        Pista mayor = pistas.get(0);
        for(int i = 1; i < pistas.size(); i++){
            if(pistas.get(i).getConfibilidad() > mayor.getConfibilidad()){
                mayor = pistas.get(i);
            }
        }
        return mayor;
    }

    public double promedioDeImportancia(){
        if(pistas.isEmpty()){
            return 0;
        }
        int suma = 0;
        for(int i = 0; i < pistas.size(); i++){
            suma += pistas.get(i).getimportancia();
        }
        return (double) suma / pistas.size();
    }

    public String generarReporte(){
        String r = "------Reporte De Investigacion------";
        r += "caso:" + nombre + "(" + codigo + ")";
        r += "Detective:" + detective;

        r += "Ubicaciones registradas" + contarUbicaciones();
        r += "Espacios disponibles:" + contadordeEspaciosDisponibles();
        Ubicacion riesgosa = UbicacionConMayorRiesgo();
        if(riesgosa == null){
            r += "ubicaciones con un alto riesgo: No hay";
        }
        else{
            r += "ubicaciones con un alto riesgo:" + riesgosa.getnombre() + "[Riesgo" + riesgosa.getniveldeRiesgo();
        }

        r += "Pistas registradas:" + contarPistas();
        if(pistas.isEmpty()){
            r += "No hay pistas y por esto mismo no se puede calcular la importancia, confiabilidas ni promedio";
        }
        else{
            Pista confiabilidad = pistaConMayorConfiabilidad();
            Pista importancia = pistademayorImportancia();
            r += "Pista con mayor importancia:" + importancia.getcodigo() +"(" + confiabilidad.getConfibilidad();
            r += "Promedio de importancia:" + String.format("%.2f",promedioDeImportancia());
        }
        return r;
    }


}