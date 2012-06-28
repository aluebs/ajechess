package ajechess.posicion;

import ajechess.colores.Color.Colores;
import ajechess.juegos.Juego;
import ajechess.movimientos.Movimiento;
import ajechess.tableros.Tablero;

public class Posicion {
    private int x;
    private int y;
    private Juego juego;

    public int getX() {
        return x;
    }

    private void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    private void setY(int y) {
        this.y = y;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Posicion(int x,int y,Juego juego){
        setX(x);
        setY(y);
        setJuego(juego);
    }

    public Posicion(String posicion){
        char[] c=posicion.toCharArray();
        if(getJuego().getJugador().getColor()==Colores.BLANCO){
            setX((int)c[1]-(int)'1');
            setY((int)c[0]-(int)'a');
        }else{
            setX((int)'1'-(int)c[1]+7);
            setY((int)'a'-(int)c[0]+7);
        }
    }

    @Override
    public boolean equals(Object o){
        Posicion p=(Posicion)o;
        return (p.getX()==getX())&&(p.getY()==getY());
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + this.x;
        hash = 73 * hash + this.y;
        return hash;
    }

    public boolean validar(){
        return (getX()>=0)&&(getX()<Tablero.maxX)&&(getY()>=0)&&(getY()<Tablero.maxY);
    }

    public Posicion mover(Movimiento movimiento){
        return new Posicion(getX()+movimiento.getX(),getY()+movimiento.getY(),getJuego());
    }

    @Override
    public String toString(){
        char[] c=new char[2];
        if(getJuego().getJugador().getColor()==Colores.BLANCO){
            c[0]=(char)(((int)'a')+getY());
            c[1]=(char)(((int)'1')+getX());
        }else{
            c[0]=(char)(((int)'a')+7-getY());
            c[1]=(char)(((int)'1')+7-getX());
        }
        return String.valueOf(c);
    }
}
