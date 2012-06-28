package ajechess.jugadores.tipo;

import ajechess.colores.Color.Colores;
import ajechess.cuentasRegresivas.CuentaRegresiva;
import ajechess.jugadas.Jugada;
import ajechess.jugadores.Jugador;
import ajechess.juegos.Juego;
import ajechess.movimientos.Movimiento;
import ajechess.piezas.Pieza;
import ajechess.piezas.tipo.Peon;
import ajechess.posicion.Posicion;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class HumanoEnRed extends Jugador{

    private final static int PORT=4000;
    
    private ServerSocket server;
    private Socket client;
    private DataOutputStream out;
    private DataInputStream in;
    private boolean servidor;

    public ServerSocket getServer() {
        return server;
    }

    public void setServer(ServerSocket server) {
        this.server = server;
    }

    public Socket getClient() {
        return client;
    }

    public void setClient(Socket client) {
        this.client = client;
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void setOut(DataOutputStream out) {
        this.out = out;
    }

    public DataInputStream getIn() {
        return in;
    }

    public void setIn(DataInputStream in) {
        this.in = in;
    }

    public boolean isServidor() {
        return servidor;
    }

    public void setServidor(boolean servidor) {
        this.servidor = servidor;
    }

    public HumanoEnRed(Colores color,String nombre,int minutos,Juego juego){
        super(color,"",0,juego);
        setServidor(true);
        String sc="";
        if(color==Colores.BLANCO){
            sc="Negro";
        }else{
            sc="Blanco";
        }
        try{
            setServer(new ServerSocket(PORT));
            setClient(getServer().accept());
            setOut(new DataOutputStream(getClient().getOutputStream()));
            setIn(new DataInputStream(getClient().getInputStream()));
            getOut().writeUTF(sc);
            getOut().flush();
            getOut().writeUTF(nombre);
            getOut().flush();
            setNombre(getIn().readUTF());
            getOut().writeInt(minutos);
            getOut().flush();
        }catch(Exception ex){
        }
        getCuenta().setMinutosMax(minutos);
        getCuenta().reiniciar();
    }

    public HumanoEnRed(String nombre,String ip,Juego juego){
        super(Colores.BLANCO,"",0,juego);
        setServidor(false);
        String sc="";
        int minutos=0;
        try {
            setClient(new Socket(ip,PORT));
            setOut(new DataOutputStream(getClient().getOutputStream()));
            setIn(new DataInputStream(getClient().getInputStream()));
            sc=getIn().readUTF();
            setNombre(getIn().readUTF());
            getOut().writeUTF(nombre);
            getOut().flush();
            minutos=getIn().readInt();
        } catch (Exception ex) {
        }
        if(sc.equals("Blanco")){
            setColor(Colores.BLANCO);
            getJuego().getJugador().setColor(Colores.NEGRO);
        }else{
            setColor(Colores.NEGRO);
            getJuego().getJugador().setColor(Colores.BLANCO);
        }
        getCuenta().setMinutosMax(minutos);
        getCuenta().reiniciar();
        getJuego().getJugador().getCuenta().setMinutosMax(minutos);
        getJuego().getJugador().getCuenta().reiniciar();
    }

    @Override
    public Jugada jugar(){
        Jugada jugada=null;
        Posicion pos=null;
        Movimiento mov=null;
        Pieza p=null;
        try{
            pos=new Posicion(getIn().readInt(), getIn().readInt(),getJuego());
            mov=new Movimiento(getIn().readInt(), getIn().readInt());
            p=getJuego().getTablero().getCelda(pos).getPieza();
            if(Peon.class.isInstance(p)){
                ((Peon)p).setFacha(getIn().readUTF());
            }
            getCuenta().setTiempo(getIn().readInt(),getIn().readInt(),getIn().readInt());
        }catch(Exception ex){
        }
        jugada=new Jugada(pos,mov,getJuego());
        System.out.println(jugada.toString());
        if(!getCuenta().isTermino()){
            getJuego().getTablero().mover(jugada);
            getJugadas().add(jugada);
        }
        return jugada;
    }

    @Override
    public void avisar(Jugada jugada,CuentaRegresiva cuenta){
        try{
            getOut().writeInt(7-jugada.getPosicion().getX());
            getOut().flush();
            getOut().writeInt(7-jugada.getPosicion().getY());
            getOut().flush();
            getOut().writeInt(-jugada.getMovimiento().getX());
            getOut().flush();
            getOut().writeInt(-jugada.getMovimiento().getY());
            getOut().flush();
            if(Peon.class.isInstance(jugada.getPieza())){
                getOut().writeUTF(((Peon)jugada.getPieza()).getFacha());
                getOut().flush();
            }
            getOut().writeInt(cuenta.getMinutos());
            getOut().flush();
            getOut().writeInt(cuenta.getSegundos());
            getOut().flush();
            getOut().writeInt(cuenta.getDecimas());
            getOut().flush();
        }catch(Exception ex){
        }
    }

    @Override
    public void cerrar(){
        try{
            getOut().close();
        }catch(Exception ex){
        }
        try{
            getIn().close();
        }catch(Exception ex){
        }
        try{
            getClient().close();
        }catch(Exception ex){
        }
        try{
            getServer().close();
        }catch(Exception ex){
        }
    }

    @Override
    public boolean volverAJugar(boolean deseo){
        boolean jugar=true;
        try{
            if(isServidor()){
                getOut().writeBoolean(deseo);
                getOut().flush();
                jugar=getIn().readBoolean();
            }else{
                jugar=getIn().readBoolean();
                getOut().writeBoolean(deseo);
                getOut().flush();
            }
        }catch(Exception ex){
        }
        return deseo&&jugar;
    }
}