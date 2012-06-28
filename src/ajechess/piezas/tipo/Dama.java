package ajechess.piezas.tipo;

import ajechess.colores.Color.Colores;
import ajechess.juegos.Juego;
import ajechess.movimientos.Movimiento;
import ajechess.piezas.Pieza;
import java.util.ArrayList;

public class Dama extends Pieza{
    public Dama(Colores color,Juego juego){
        super(color,false,false,"dama.svg",juego);
        ArrayList<Movimiento> m=new ArrayList<Movimiento>();
        m.add(new Movimiento(1,0));
        m.add(new Movimiento(2,0));
        m.add(new Movimiento(3,0));
        m.add(new Movimiento(4,0));
        m.add(new Movimiento(5,0));
        m.add(new Movimiento(6,0));
        m.add(new Movimiento(7,0));
        m.add(new Movimiento(-1,0));
        m.add(new Movimiento(-2,0));
        m.add(new Movimiento(-3,0));
        m.add(new Movimiento(-4,0));
        m.add(new Movimiento(-5,0));
        m.add(new Movimiento(-6,0));
        m.add(new Movimiento(-7,0));
        m.add(new Movimiento(0,1));
        m.add(new Movimiento(0,2));
        m.add(new Movimiento(0,3));
        m.add(new Movimiento(0,4));
        m.add(new Movimiento(0,5));
        m.add(new Movimiento(0,6));
        m.add(new Movimiento(0,7));
        m.add(new Movimiento(0,-1));
        m.add(new Movimiento(0,-2));
        m.add(new Movimiento(0,-3));
        m.add(new Movimiento(0,-4));
        m.add(new Movimiento(0,-5));
        m.add(new Movimiento(0,-6));
        m.add(new Movimiento(0,-7));
        m.add(new Movimiento(1,1));
        m.add(new Movimiento(2,2));
        m.add(new Movimiento(3,3));
        m.add(new Movimiento(4,4));
        m.add(new Movimiento(5,5));
        m.add(new Movimiento(6,6));
        m.add(new Movimiento(7,7));
        m.add(new Movimiento(-1,1));
        m.add(new Movimiento(-2,2));
        m.add(new Movimiento(-3,3));
        m.add(new Movimiento(-4,4));
        m.add(new Movimiento(-5,5));
        m.add(new Movimiento(-6,6));
        m.add(new Movimiento(-7,7));
        m.add(new Movimiento(1,-1));
        m.add(new Movimiento(2,-2));
        m.add(new Movimiento(3,-3));
        m.add(new Movimiento(4,-4));
        m.add(new Movimiento(5,-5));
        m.add(new Movimiento(6,-6));
        m.add(new Movimiento(7,-7));
        m.add(new Movimiento(-1,-1));
        m.add(new Movimiento(-2,-2));
        m.add(new Movimiento(-3,-3));
        m.add(new Movimiento(-4,-4));
        m.add(new Movimiento(-5,-5));
        m.add(new Movimiento(-6,-6));
        m.add(new Movimiento(-7,-7));
        setMovimientos(m);
        setComidas(m);
    }
}