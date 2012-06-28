package ajechess.jugadores.tipo;

import ajechess.colores.Color.Colores;
import ajechess.jugadas.Jugada;
import ajechess.jugadores.Jugador;
import ajechess.juegos.Juego;
import ajechess.tableros.Tablero;

public class Humano extends Jugador{
    private boolean noJugo;

    public boolean isNoJugo() {
        return noJugo;
    }

    public void setNoJugo(boolean noJugo) {
        this.noJugo = noJugo;
    }

    public Humano(Colores color, String nombre, int tiempo,Juego juego){
        super(color,nombre,tiempo,juego);
        setNoJugo(true);
    }

    @Override
    public Jugada jugar(){
        Tablero t=getJuego().getTablero();
        for(boolean valida=false;!valida&&!getCuenta().isTermino();valida=t.mover(t.getUltimaJugada())){
            while(isNoJugo()&&!getCuenta().isTermino()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }
            }
            setNoJugo(true);
        }
        if(!getCuenta().isTermino()){
            getJugadas().add(t.getUltimaJugada());
        }
        return getUltimaJugada();
    }

    @Override
    public void reiniciar(){
        super.reiniciar();
        setNoJugo(true);
    }
}
