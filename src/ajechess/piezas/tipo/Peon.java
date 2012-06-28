package ajechess.piezas.tipo;

import ajechess.colores.Color.Colores;
import ajechess.jugadas.Jugada;
import ajechess.jugadores.Jugador;
import ajechess.jugadores.tipo.Humano;
import ajechess.juegos.Juego;
import ajechess.movimientos.Movimiento;
import ajechess.piezas.Pieza;
import ajechess.piezas.tipo.coronadores.Coronador;
import ajechess.posicion.Posicion;
import java.util.ArrayList;

public class Peon extends Pieza {

    private Posicion comerAlPaso;
    private Pieza fachada;
    private String facha;
    private static Coronador coronador;

    public Posicion getComerAlPaso() {
        return comerAlPaso;
    }

    public void setComerAlPaso(Posicion comerAlPaso) {
        this.comerAlPaso = comerAlPaso;
    }

    public Pieza getFachada() {
        return fachada;
    }

    public void setFachada(Pieza fachada) {
        this.fachada = fachada;
    }

    public String getFacha() {
        return facha;
    }

    public void setFacha(String facha) {
        this.facha = facha;
    }

    public static Coronador getCoronador() {
        return coronador;
    }

    public static void setCoronador(Coronador aCoronador) {
        coronador = aCoronador;
    }

    public Peon(Colores color,Juego juego){
        super(color,false,false,"peon.svg",juego);
        ArrayList<Movimiento> m=new ArrayList<Movimiento>();
        if(getColor()==getJuego().getJugador().getColor()){
            m.add(new Movimiento(1,0));
            m.add(new Movimiento(2,0));
        }else{
            m.add(new Movimiento(-1,0));
            m.add(new Movimiento(-2,0));
        }
        setMovimientos(m);
        m=new ArrayList<Movimiento>();
        if(getColor()==getJuego().getJugador().getColor()){
            m.add(new Movimiento(1,1));
            m.add(new Movimiento(1,-1));
        }else{
            m.add(new Movimiento(-1,1));
            m.add(new Movimiento(-1,-1));
        }
        setComidas(m);
        setComerAlPaso(null);
        setFachada(null);
        setFacha("");
        setCoronador(new Coronador(getJuego()));
    }

    @Override
    public boolean validarPosible(Jugada jugada) {
        boolean valida=false;
        if(getFachada()==null){
            valida=super.validarPosible(jugada);
            setComerAlPaso(null);
            int fila;
            int direccion;
            Jugador otroJugador;
            if(getColor()==getJuego().getJugador().getColor()){
                fila=4;
                direccion=1;
                otroJugador=getJuego().getOponente();
            }else{
                fila=3;
                direccion=-1;
                otroJugador=getJuego().getJugador();
            }
            if(jugada.getPosicion().getX()==fila){
                if((jugada.getMovimiento().getX()==direccion)&&((jugada.getMovimiento().getY()==1)||(jugada.getMovimiento().getY()==-1))){
                    Pieza p=getJuego().getTablero().getCelda(new Posicion(jugada.getPosicion().getX(),jugada.getPosicion().getY()+jugada.getMovimiento().getY(),getJuego())).getPieza();
                    if((p instanceof Peon)&&(p.getColor()!=getColor())){
                        Jugada j=otroJugador.getUltimaJugada();
                        if((j.getPosicion().getX()==(fila+2*direccion))&&(j.getPosicion().getY()==(jugada.getPosicion().getY()+jugada.getMovimiento().getY()))&&(j.getMovimiento().getX()==(-2*direccion))&&(j.getMovimiento().getY()==0)){
                                setComerAlPaso(new Posicion(jugada.getPosicion().getX(),jugada.getPosicion().getY()+jugada.getMovimiento().getY(),getJuego()));
                                valida=true;
                        }
                    }
                }
            }
        }else{
            valida=getFachada().validarPosible(jugada);
        }
        return valida;
    }

    @Override
    public void mover(Jugada jugada){
        if(getFachada()==null){
            if(getMovimientos().size()>1){
                getMovimientos().remove(1);
            }
            if(getComerAlPaso()!=null){
                Pieza temp=getJuego().getTablero().getCelda(getComerAlPaso()).getPieza();
                if(temp!=null){
                    if(temp.getColor()==getJuego().getJugador().getColor()){
                        getJuego().getJugador().addComido(temp);
                    }else{
                        getJuego().getOponente().addComido(temp);
                    }
                }
                getJuego().getTablero().getCelda(getComerAlPaso()).setPieza(null);
            }
            if(getFacha().equals("Dama")){
                setFachada(new Dama(getColor(),getJuego()));
            }
            if(getFacha().equals("Torre")){
                setFachada(new Torre(getColor(),getJuego()));
            }
            if(getFacha().equals("Alfil")){
                setFachada(new Alfil(getColor(),getJuego()));
            }
            if(getFacha().equals("Caballo")){
                setFachada(new Caballo(getColor(),getJuego()));
            }
            if(((getColor()==getJuego().getJugador().getColor())&&(jugada.getPosicionFinal().getX()==7)&&(Humano.class.isInstance(getJuego().getJugador())))||((getColor()==getJuego().getOponente().getColor())&&(jugada.getPosicionFinal().getX()==0)&&(Humano.class.isInstance(getJuego().getOponente())))){
                Pieza p=getCoronador().obtenerPieza(getColor());
                setFachada(p);
                if(Dama.class.isInstance(p)){
                    setFacha("Dama");
                }
                if(Torre.class.isInstance(p)){
                    setFacha("Torre");
                }
                if(Alfil.class.isInstance(p)){
                    setFacha("Alfil");
                }
                if(Caballo.class.isInstance(p)){
                    setFacha("Caballo");
                }
            }
        }else{
            getFachada().mover(jugada);
        }
    }

    @Override
    public ArrayList<Movimiento> getMovimientos() {
        ArrayList<Movimiento> movimientos=null;
        if(getFachada()==null){
            movimientos=super.getMovimientos();
        }else{
            movimientos=getFachada().getMovimientos();
        }
        return movimientos;
    }

    @Override
    public ArrayList<Movimiento> getComidas() {
        ArrayList<Movimiento> comidas=null;
        if(getFachada()==null){
            comidas=super.getComidas();
        }else{
            comidas=getFachada().getComidas();
        }
        return comidas;
    }

    @Override
    public String getImagenPath() {
        String path;
        if(getFachada()==null){
            path=super.getImagenPath();
        }else{
            path=getFachada().getImagenPath();
        }
        return path;
    }
}