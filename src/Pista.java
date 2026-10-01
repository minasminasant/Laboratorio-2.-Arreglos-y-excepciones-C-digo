public class Pista{
    private String codigo;
    private String descripcion;
    private String tipodeEvidencia;
    private int importancia;
    private int confiabilidad;

    public Pista(String codigo, String descripcion, String tipodeEvidencia, int importancia, int confiabilidad){
        validarTexto(codigo, "El codigo");
        validarTexto(descripcion, "La descripcion");
        validarTexto(tipodeEvidencia, "EL tipo de evidencia");
        validarimportancia(importancia);
        validarconfibilidad(confiabilidad);

        this.codigo = codigo.trim();
        this.descripcion = descripcion.trim();
        this.tipodeEvidencia = tipodeEvidencia.trim();
        this.importancia = importancia;
        this.confiabilidad = confiabilidad;
    }

    private static void validarTexto(String texto, String campo){
        if(texto == null || texto.trim().isEmpty()){
            throw new IllegalArgumentException(campo + "no puede estar vacio");
        }
    }

    private static void validarimportancia(int importancia){
        if(importancia < 1 || importancia > 10){
            throw new IllegalArgumentException("El nivel de importancia de llegar a estar entre 1 y 10");
        }
    }

    private static void validarconfibilidad(int confiabilidad){
        if(confiabilidad < 0 || confiabilidad > 100){
            throw new IllegalArgumentException("el nivel de confibilidad debe llegar a estar entre 0 y 100");
        }
    }

    public String getcodigo(){
        return codigo;
    }

    public String getdescripcion(){
        return descripcion;
    }

    public String gettipodeEvidencia(){
        return tipodeEvidencia;
    }

    public int getimportancia(){
        return importancia;
    }

    public int getConfibilidad(){
        return confiabilidad;
    }

    public void modificar(String descripcion, String tipodeEvidencia, int importancia, int confiabilidad){
        validarTexto(descripcion, "La descripcion");
        validarTexto(tipodeEvidencia, "El tipo de evidencia");
        validarimportancia(importancia);
        validarconfibilidad(confiabilidad);

        this.descripcion = descripcion.trim();
        this.tipodeEvidencia = tipodeEvidencia.trim();
        this.importancia = importancia;
        this.confiabilidad = confiabilidad;
    }

    @Override
    public String toString(){
        return "codigo:" + codigo + "| descripcion:" + descripcion + "| tipo:" + tipodeEvidencia + "|importancia:" + importancia + "| confiabilidad:" + confiabilidad;
    }
}