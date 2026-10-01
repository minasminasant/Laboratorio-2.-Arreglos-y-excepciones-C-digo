public class Ubicacion{
    private String codigo;
    private String nombre;
    private String direccion;
    private int niveldeRiesgo;
    private String Estado;

    public Ubicacion(String codigo, String nombre, String direccion, int niveldeRiesgo, String Estado){
        validarTexto(codigo, "El codigo");
        validarTexto(nombre, "El nombre");
        validarTexto(direccion, "La direccion");
        validarniveldeRiesgo(niveldeRiesgo);
        validarTexto(Estado, "El estado");

        this.nombre = nombre;
        this.codigo = codigo;
        this.direccion = direccion.trim();
        this.niveldeRiesgo = niveldeRiesgo;
        this.Estado = Estado.trim();
    }

    private static void validarTexto(String texto, String campo){
        if( texto == null || texto.trim().isEmpty()){
            throw new IllegalArgumentException(campo + "no puede esatr vacio");
        }
    }

    private static void validarniveldeRiesgo(int nivel){
        if(nivel < 1 || nivel > 10){
            throw new IllegalArgumentException("El nivel de riesgo debe de estar en el rango de 1 y 10");
        }
    }

    public String getcodigo(){
        return codigo;
    }

    public String getnombre(){
        return nombre;
    }

    public String getdireccion(){
        return direccion;
    }

    public int getniveldeRiesgo(){
        return niveldeRiesgo;
    }

    public String getEstado(){
        return Estado;
    }

    public void setniveldeRiesgo(int niveldeRiesgo){
        validarniveldeRiesgo(niveldeRiesgo);
        this.niveldeRiesgo = niveldeRiesgo;
    }

    public void setEstado(String Estado){
        validarTexto(Estado, "El estado");
        this.Estado = Estado.trim();
    }

    public void modificar(int nuevoniveldeRiesgo, String nuevoEstado){
        validarniveldeRiesgo(nuevoniveldeRiesgo);
        validarTexto(nuevoEstado, "El estado");
        this.niveldeRiesgo = nuevoniveldeRiesgo;
        this.Estado = nuevoEstado.trim();
    }

    @Override
    public String toString(){
        return "Codigo:" + codigo +"| Nombre:" + nombre + "| Direccion:" + direccion + "| Riesgo:" + niveldeRiesgo + "|Estado:" + Estado;
    }
}