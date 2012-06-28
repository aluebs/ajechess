package ajechess.cuentasRegresivas;

import java.util.Observable;

/**
 * Clase que administra las cuentas regresivas de los jugadores. Extiende
 * Observable, para ser actualizada en la ventana. Implementa Runnable ya que
 * cada cuenta regresiva corre en un hilo aparte.
 */
public class CuentaRegresiva extends Observable implements Runnable {

    private int minutosMax;
    private int minutos;
    private int segundos;
    private int decimas;
    private String hora;
    private boolean encendido;
    private boolean termino;

    /**
     * @return Cantidad de minutos con los que comienza la partida.
     */
    private synchronized int getMinutosMax() {
        return minutosMax;
    }

    /**
     * Setea minutosMax.
     * @param minutosMax cantidad de minutos con los que comienza la partida.
     */
    public synchronized void setMinutosMax(int minutosMax) {
        this.minutosMax = minutosMax;
    }

    /**
     * @return El string que representa el tiempo en la cuenta regresiva.
     */
    public synchronized String getHora() {
        return hora;
    }

    /**
     * Setea hora.
     * @param hora string que representa el tiempo en la cuenta regresiva.
     */
    public synchronized void setHora(String hora) {
        this.hora = hora;
    }

    /**
     * @return Minutos que actualmente tiene la cuenta regresiva.
     */
    public synchronized int getMinutos() {
        return minutos;
    }

    /**
     * Setea minutos.
     * @param minutos que actualmente tiene la cuenta regresiva.
     */
    public synchronized void setMinutos(int minutos) {
        this.minutos = minutos;
    }

    /**
     * @return Segundos que actualmente tiene la cuenta regresiva.
     */
    public synchronized int getSegundos() {
        return segundos;
    }

    /**
     * Setea segundos.
     * @param segundos que actualmente tiene la cuenta regresiva.
     */
    public synchronized void setSegundos(int segundos) {
        this.segundos = segundos;
    }

    /**
     * @return Decimas que actualmente tiene la cuenta regresiva.
     */
    public synchronized int getDecimas() {
        return decimas;
    }

    /**
     * Setea decimas.
     * @param decimas que actualmente tiene la cuenta regresiva.
     */
    public synchronized void setDecimas(int decimas) {
        this.decimas = decimas;
    }

    /**
     * @return Encendido, un flag que avisa si la cuenta regresiva esta corriendo.
     */
    public synchronized boolean isEncendido() {
        return encendido;
    }

    /**
     * Setea encendido.
     * @param encendido un flag que avisa si la cuenta regresiva esta corriendo.
     */
    public synchronized void setEncendido(boolean encendido) {
        this.encendido = encendido;
    }

    /**
     * @return Termino, un flag que avisa si la cuenta regresiva llego a cero.
     */
    public synchronized boolean isTermino() {
        return termino;
    }

    /**
     * Setea termino.
     * @param termino un flag que avisa cuando la cuenta regresiva llego a cero.
     */
    public synchronized void setTermino(boolean termino) {
        this.termino = termino;
    }

    /**
     * Setea los minutos, segundos y decimas que actualmente tiene la cuenta regresiva.
     * @param minutos que actualmente tiene la cuenta regresiva.
     * @param segundos que actualmente tiene la cuenta regresiva.
     * @param decimas que actualmente tiene la cuenta regresiva.
     */
    public synchronized void setTiempo(int minutos,int segundos,int decimas){
        setMinutos(minutos);
        setSegundos(segundos);
        setDecimas(decimas);
        actualizarHora();
        if((minutos==0)&&(segundos==0)&&(decimas==0)){
            setTermino(true);
        }
        setChanged();
        notifyObservers();
    }

    /**
     * Constructor de una cuenta regresiva.
     * @param minutos con los que comienza la partiad.
     */
    public CuentaRegresiva(int minutos) {
        setMinutosMax(minutos);
        reiniciar();
    }

    /**
     * Comienza a correr la cuenta regresiva y a decrementar el tiempo.
     */
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
            }
            while(isEncendido()){
                actualizarHora();
                setChanged();
                notifyObservers();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ex) {
                }
                setDecimas(getDecimas()-1);
                if(getDecimas()<0){
                    setDecimas(9);
                    setSegundos(getSegundos()-1);
                    if (getSegundos()<0) {
                        setSegundos(59);
                        setMinutos(getMinutos()-1);
                        if((getMinutos()==0)&&(getSegundos()==0)&&(getDecimas()==0)){
                            setTiempo(0,0,0);
                            setEncendido(false);
                        }
                    }
                }
            }
        }
    }

    /**
     * Reinicia la cuenta regresiva a sus valore por defecto.
     */
    public synchronized void reiniciar(){
        setEncendido(false);
        setTermino(false);
        setTiempo(getMinutosMax(),0,0);
    }

    /**
     * Actualiza el string que representa el tiempo en la cuenta regresiva.
     */
    private void actualizarHora(){
        String s=(getSegundos()<10)?("0"+String.valueOf(getSegundos())):(String.valueOf(getSegundos()));
        setHora(getMinutos() + ":" + s);
    }
}
