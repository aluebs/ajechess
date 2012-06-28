package ajechess.colores;

import java.util.Random;

/**
 * Clase abstracta que contiene los metodos necesarios para
 * administrar los colores de las piezas.
 */
public abstract class Color {

    public enum Colores {BLANCO, NEGRO};

    /**
     * Devuelve el color opuesto al ingresado.
     * @param color color ingresado.
     * @return color opuesto
     */
    public static Colores getOtroColor(Colores color) {
        Colores otroColor = color;
        if (color == Colores.BLANCO) {
            otroColor = Colores.NEGRO;
        } else if (color == Colores.NEGRO) {
            otroColor = Colores.BLANCO;
        }
        return otroColor;
    }

    /**
     * Obtiene la ruta a la carpeta con os iconos de las piezas de cada color.
     * @param color de las piezas, cuyos icono se desean levantar.
     * @return ruta a la carpeta donde se encuentran los iconos del color especificado.
     */
    public static String getPath(Colores color) {
        String path = "";
        if (color == Colores.BLANCO) {
            path ="/ajechess/iconos/blanco/";
        } else if (color == Colores.NEGRO) {
            path ="/ajechess/iconos/negro/";
        }
        return path;
    }

    /**
     * Delvuelve un color aleatorio.
     * @return Color aleatorio.
     */
    public static Colores GetRandomColor(){
        Colores color=Colores.BLANCO;
        Random r=new Random();
        if(r.nextInt(2)==0){
            color=Colores.BLANCO;
        }else{
            color=Colores.NEGRO;
        }
        return color;
    }
}
