package ajechess.piezas.tipo;

import ajechess.colores.Color.Colores;
import ajechess.jugadas.Jugada;
import ajechess.juegos.Juego;
import ajechess.movimientos.Movimiento;
import ajechess.piezas.Pieza;
import ajechess.posicion.Posicion;
import java.util.ArrayList;

public class Rey extends Pieza{
    private boolean movido;
    private Jugada enrroque;

    public boolean isMovido() {
        return movido;
    }

    public void setMovido(boolean movido) {
        this.movido = movido;
    }

    public Jugada getEnrroque() {
        return enrroque;
    }

    public void setEnrroque(Jugada enrroque) {
        this.enrroque = enrroque;
    }

    public Rey(Colores color,Juego juego){
        super(color,false,true,"rey.svg",juego);
        ArrayList<Movimiento> m=new ArrayList<Movimiento>();
        m.add(new Movimiento(1,0));
        m.add(new Movimiento(-1,0));
        m.add(new Movimiento(0,1));
        m.add(new Movimiento(0,-1));
        m.add(new Movimiento(1,1));
        m.add(new Movimiento(-1,1));
        m.add(new Movimiento(1,-1));
        m.add(new Movimiento(-1,-1));
        setMovimientos(m);
        setComidas(m);
        setMovido(false);
        setEnrroque(null);
    }

    @Override
    public boolean validarPosible(Jugada jugada){
        boolean valida=super.validarPosible(jugada);
        setEnrroque(null);
        if(!valida){
            if(!isMovido()){
                int paso=0;
                Pieza t=null;
                if((jugada.getMovimiento().getY()==-2)&&(jugada.getMovimiento().getX()==0)){
                    paso=-1;
                    t=getJuego().getTablero().getCelda(new Posicion(jugada.getPosicion().getX(),0,getJuego())).getPieza();
                }
                if((jugada.getMovimiento().getY()==2)&&(jugada.getMovimiento().getX()==0)){
                    paso=1;
                    t=getJuego().getTablero().getCelda(new Posicion(jugada.getPosicion().getX(),7,getJuego())).getPieza();
                }
                if((t!=null)&&Torre.class.isInstance(t)){
                    Torre torre=(Torre)t;
                    if(!torre.isMovido()){
                        valida=true;
                        int i;
                        for(i=jugada.getPosicion().getY()+paso;(i<7)&&(i>0);i+=paso){
                            Posicion p=new Posicion(jugada.getPosicion().getX(),i,getJuego());
                            if(getJuego().getTablero().getCelda(p).getPieza()!=null){
                                valida=false;
                            }
                            if((i<=6)&&(i>=2)&&getJuego().getTablero().getCelda(p).esAtacada(getColor(),p)){
                                valida=false;
                            }
                        }
                        if(valida){
                            setEnrroque(new Jugada(new Posicion(jugada.getPosicion().getX(),i,getJuego()),new Movimiento(0,jugada.getPosicion().getY()+paso-i),getJuego()));
                        }
                    }
                }
            }
        }
        return valida;
    }

    @Override
    public void mover(Jugada jugada){
        setMovido(true);
        if(getEnrroque()!=null){
            getJuego().getTablero().moverTemp(getEnrroque());
        }
    }
}