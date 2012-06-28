package ajechess.piezas;

import ajechess.colores.Color.Colores;
import ajechess.colores.Color;
import ajechess.jugadas.Jugada;
import ajechess.juegos.Juego;
import ajechess.movimientos.Movimiento;
import ajechess.posicion.Posicion;
import ajechess.tableros.celdas.Celda;
import java.util.ArrayList;

public abstract class Pieza {

    private Colores color;
    private ArrayList<Movimiento> movimientos;
    private ArrayList<Movimiento> comidas;
    private boolean salta;
    private boolean jaqueable;
    private String imagen;
    private Juego juego;

    public Colores getColor() {
        return color;
    }

    private void setColor(Colores color) {
        this.color = color;
    }

    public ArrayList<Movimiento> getMovimientos() {
        return movimientos;
    }

    protected void setMovimientos(ArrayList<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public ArrayList<Movimiento> getComidas() {
        return comidas;
    }

    protected void setComidas(ArrayList<Movimiento> comidas) {
        this.comidas = comidas;
    }

    private boolean isSalta() {
        return salta;
    }

    private void setSalta(boolean salta) {
        this.salta = salta;
    }

    public boolean isJaqueable() {
        return jaqueable;
    }

    private void setJaqueable(boolean jaqueable) {
        this.jaqueable = jaqueable;
    }

    private String getImagen() {
        return imagen;
    }

    private void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Pieza(Colores color, boolean salta, boolean jaqueable, String imagen,Juego juego) {
        setColor(color);
        setSalta(salta);
        setJaqueable(jaqueable);
        setImagen(imagen);
        setJuego(juego);
    }

    public boolean validar(Jugada jugada) {
        boolean valida = validarPosible(jugada);
        if(valida){
            Pieza temp = getJuego().getTablero().moverTemp(jugada);
            if (getJuego().getTablero().esJaque(getColor())) {
                valida=false;
            }
            getJuego().getTablero().moverTempInv(jugada.jugadaInversa(), temp);
        }
        return valida;
    }

    public boolean validarPosible(Jugada jugada) {
        boolean valida = false;
        Posicion posicionFinal = jugada.getPosicionFinal();
        Celda celda = getJuego().getTablero().getCelda(posicionFinal);
        if ((movimientos.contains(jugada.getMovimiento()) && (getJuego().getTablero().getCelda(posicionFinal).getPieza() == null)) || (comidas.contains(jugada.getMovimiento()) && (celda.getPieza()!=null)&&(celda.getPieza().getColor() != getColor()))) {
            if (isSalta()) {
                valida = true;
            } else {
                Movimiento paso = jugada.getMovimiento().getPaso();
                valida = true;
                for (Posicion tempPos = new Posicion(jugada.getPosicion().getX() + paso.getX(), jugada.getPosicion().getY() + paso.getY(),getJuego()); !tempPos.equals(posicionFinal); tempPos = tempPos.mover(paso)) {
                    if (getJuego().getTablero().getCelda(tempPos).getPieza() != null) {
                        valida = false;
                    }
                }
            }

        } else {
            valida = false;
        }
        return valida;
    }

    public String getImagenPath() {
        return Color.getPath(getColor()) + getImagen();
    }

    public void mover(Jugada jugada) {
    }
}
