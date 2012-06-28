package ajechess.jugadores.tipo;

import ajechess.colores.Color.Colores;
import ajechess.cuentasRegresivas.CuentaRegresiva;
import ajechess.jugadas.Jugada;
import ajechess.jugadores.Jugador;
import ajechess.juegos.Juego;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Computadora extends Jugador{

    private Process motor;
    private Reader in;
    private Writer out;
    private BufferedReader bin;
    private BufferedWriter bout;
    private ArrayList<Jugada> jugadasTotales;

    public Process getMotor() {
        return motor;
    }

    public void setMotor(Process motor) {
        this.motor = motor;
    }

    public Reader getIn() {
        return in;
    }

    public void setIn(Reader in) {
        this.in = in;
    }

    public Writer getOut() {
        return out;
    }

    public void setOut(Writer out) {
        this.out = out;
    }

    public BufferedReader getBin() {
        return bin;
    }

    public void setBin(BufferedReader bin) {
        this.bin = bin;
    }

    public BufferedWriter getBout() {
        return bout;
    }

    public void setBout(BufferedWriter bout) {
        this.bout = bout;
    }

    public ArrayList<Jugada> getJugadasTotales() {
        return jugadasTotales;
    }

    public void setJugadasTotales(ArrayList<Jugada> jugadasTotales) {
        this.jugadasTotales = jugadasTotales;
    }

    public Computadora(Colores color,int minutos,Juego juego){
        super(color,"Computadora",minutos,juego);
        setJugadasTotales(new ArrayList<Jugada>());
        try {
            setMotor(Runtime.getRuntime().exec("java -cp carballo.jar -Xmx512M com.alonsoruibal.chess.uci.Uci"));
            setIn(new InputStreamReader(getMotor().getInputStream()));
            setBin(new BufferedReader(getIn()));
            setOut(new OutputStreamWriter(getMotor().getOutputStream()));
            setBout(new BufferedWriter(getOut()));
            getBout().write("isready");
            getBout().newLine();
            getBout().flush();
            getBin().readLine();
        } catch (Exception ex) {
        }
    }

    @Override
    public Jugada jugar() {
        String s=null;
        StringTokenizer st=null;
        try{
            getBout().write("go");
            getBout().newLine();
            getBout().flush();
            s=getBin().readLine();
            st=new StringTokenizer(s);
            while(!st.nextToken().equals("bestmove")){
                s=getBin().readLine();
                st=new StringTokenizer(s);
            }
        }catch(Exception ex){
        }
        Jugada jugada=new Jugada(st.nextToken(),getJuego());
        getJugadasTotales().add(jugada);
        if(!getCuenta().isTermino()){
            getJuego().getTablero().mover(jugada);
            getJugadas().add(jugada);
        }
        return jugada;
    }

    @Override
    public void reiniciar(){
        super.reiniciar();
        getJugadasTotales().clear();
        try{
            getBout().write("ucinewgame");
            getBout().newLine();
            getBout().flush();
        }catch(Exception ex){
        }
    }

    @Override
    public void avisar(Jugada jugada,CuentaRegresiva cuenta){
        getJugadasTotales().add(jugada);
        try{
            getBout().write("position startpos moves "+getStringJugadasTotales());
            getBout().newLine();
            getBout().flush();
        }catch(Exception ex){
        }
    }

    @Override
    public void cerrar(){
        try{
            getBin().close();
        }catch(Exception ex){
        }
        try{
            getIn().close();
        }catch(Exception ex){
        }
        try{
            getBout().close();
        }catch(Exception ex){
        }
        try{
            getOut().close();
        }catch(Exception ex){
        }
        try{
            getMotor().destroy();
        }catch(Exception ex){
        }
    }

    private String getStringJugadasTotales(){
        String s=new String();
        for(int i=0;i<getJugadasTotales().size();i++){
            s+=(getJugadasTotales().get(i)+" ");
        }
        return s;
    }
}