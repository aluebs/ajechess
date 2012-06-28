package ajechess.piezas.tipo;

import ajechess.colores.Color.Colores;
import ajechess.juegos.Juego;
import ajechess.movimientos.Movimiento;
import ajechess.piezas.Pieza;
import java.util.ArrayList;

public class Caballo extends Pieza{
    public Caballo(Colores color,Juego juego){
        super(color,true,false,"caballo.svg",juego);
        ArrayList<Movimiento> m=new ArrayList<Movimiento>();
        m.add(new Movimiento(2,1));
        m.add(new Movimiento(1,2));
        m.add(new Movimiento(-1,2));
        m.add(new Movimiento(-2,1));
        m.add(new Movimiento(-2,-1));
        m.add(new Movimiento(-1,-2));
        m.add(new Movimiento(1,-2));
        m.add(new Movimiento(2,-1));
        setMovimientos(m);
        setComidas(m);
    }
}