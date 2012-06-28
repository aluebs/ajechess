package ajechess.piezas.tipo;

import ajechess.colores.Color.Colores;
import ajechess.juegos.Juego;
import ajechess.movimientos.Movimiento;
import ajechess.piezas.Pieza;
import java.util.ArrayList;

public class Alfil extends Pieza{
    public Alfil(Colores color,Juego juego){
        super(color,false,false,"alfil.svg",juego);
        ArrayList<Movimiento> m=new ArrayList<Movimiento>();
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