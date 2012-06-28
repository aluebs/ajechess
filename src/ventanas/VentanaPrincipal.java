package ventanas;

import ajechess.jugadas.Jugada;
import ajechess.jugadores.Jugador;
import ajechess.juegos.Juego;
import ajechess.piezas.Pieza;
import ajechess.piezas.tipo.Alfil;
import ajechess.piezas.tipo.Caballo;
import ajechess.piezas.tipo.Dama;
import ajechess.piezas.tipo.Peon;
import ajechess.piezas.tipo.Torre;
import ajechess.piezas.tipo.coronadores.Coronador;
import ajechess.posicion.Posicion;
import ajechess.tableros.Tablero;
import java.awt.Color;
import java.awt.Container;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import ajechess.cuentasRegresivas.CuentaRegresiva;
import ajechess.jugadores.tipo.Humano;

public class VentanaPrincipal extends javax.swing.JFrame implements Observer {

    private Juego juego;
    private ArrayList<ArrayList<JButton>> botones = new ArrayList<ArrayList<JButton>>();
    private DefaultListModel modelJugador;
    private DefaultListModel modelOponente;

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public ArrayList<ArrayList<JButton>> getBotones() {
        return botones;
    }

    public void setBotones(ArrayList<ArrayList<JButton>> botones) {
        this.botones = botones;
    }

    public DefaultListModel getModelJugador() {
        return modelJugador;
    }

    public void setModelJugador(DefaultListModel modelJugador) {
        this.modelJugador = modelJugador;
    }

    public DefaultListModel getModelOponente() {
        return modelOponente;
    }

    public void setModelOponente(DefaultListModel modelOponente) {
        this.modelOponente = modelOponente;
    }

    public VentanaPrincipal(Juego juego) {
        initComponents();
        setJuego(juego);
        llenarBotones();
        nombreBlanca.setText(getJuego().getJugador().getNombre());
        nombreNegra.setText(getJuego().getOponente().getNombre());
        modelJugador = new DefaultListModel();
        modelOponente = new DefaultListModel();
        listaJugador.setModel(modelJugador);
        listaOponente.setModel(modelOponente);
        setObservados(getJuego());
    }

    private void llenarBotones() {
        ArrayList<JButton> fila = new ArrayList<JButton>();
        fila.add(celda00);
        fila.add(celda01);
        fila.add(celda02);
        fila.add(celda03);
        fila.add(celda04);
        fila.add(celda05);
        fila.add(celda06);
        fila.add(celda07);
        getBotones().add(fila);

        fila = new ArrayList<JButton>();
        fila.add(celda10);
        fila.add(celda11);
        fila.add(celda12);
        fila.add(celda13);
        fila.add(celda14);
        fila.add(celda15);
        fila.add(celda16);
        fila.add(celda17);
        getBotones().add(fila);

        fila = new ArrayList<JButton>();
        fila.add(celda20);
        fila.add(celda21);
        fila.add(celda22);
        fila.add(celda23);
        fila.add(celda24);
        fila.add(celda25);
        fila.add(celda26);
        fila.add(celda27);
        getBotones().add(fila);

        fila = new ArrayList<JButton>();
        fila.add(celda30);
        fila.add(celda31);
        fila.add(celda32);
        fila.add(celda33);
        fila.add(celda34);
        fila.add(celda35);
        fila.add(celda36);
        fila.add(celda37);
        getBotones().add(fila);

        fila = new ArrayList<JButton>();
        fila.add(celda40);
        fila.add(celda41);
        fila.add(celda42);
        fila.add(celda43);
        fila.add(celda44);
        fila.add(celda45);
        fila.add(celda46);
        fila.add(celda47);
        getBotones().add(fila);

        fila = new ArrayList<JButton>();
        fila.add(celda50);
        fila.add(celda51);
        fila.add(celda52);
        fila.add(celda53);
        fila.add(celda54);
        fila.add(celda55);
        fila.add(celda56);
        fila.add(celda57);
        getBotones().add(fila);

        fila = new ArrayList<JButton>();
        fila.add(celda60);
        fila.add(celda61);
        fila.add(celda62);
        fila.add(celda63);
        fila.add(celda64);
        fila.add(celda65);
        fila.add(celda66);
        fila.add(celda67);
        getBotones().add(fila);

        fila = new ArrayList<JButton>();
        fila.add(celda70);
        fila.add(celda71);
        fila.add(celda72);
        fila.add(celda73);
        fila.add(celda74);
        fila.add(celda75);
        fila.add(celda76);
        fila.add(celda77);
        getBotones().add(fila);
    }

    public void setObservados(Juego m) {
        m.addObserver(this);
        m.getJugador().getCuenta().addObserver(this);
        m.getOponente().getCuenta().addObserver(this);
        m.getTablero().addObserver(this);
        Peon.getCoronador().addObserver(this);
        refrescarTablero();
    }

    public void refrescarTablero() {
        for (int i = 0; i < Tablero.maxX; i++) {
            for (int j = 0; j < Tablero.maxY; j++) {
                Pieza p = getJuego().getTablero().getCelda(new Posicion(i, j,getJuego())).getPieza();
                if (p != null) {
                    String ruta = p.getImagenPath();
                    ImageIcon icon = new ImageIcon(VentanaPrincipal.class.getResource(ruta));
                    getBotones().get(i).get(j).setIcon(icon);
                } else {
                    getBotones().get(i).get(j).setIcon(null);
                }
                coloresFondo(new Posicion(i, j,getJuego()));
            }
        }
        Jugador j = getJuego().getJugador();
        Pieza p = new Dama(j.getColor(),getJuego());
        ImageIcon icon = new ImageIcon(VentanaPrincipal.class.getResource(p.getImagenPath()));
        icon.setImage(icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
        switch (j.getDamasComidas()) {
            case 0:
                label1Dama.setIcon(null);
                break;
            case 1:
                label1Dama.setIcon(icon);
                break;
        }
        p = new Torre(j.getColor(),getJuego());
        icon = new ImageIcon(VentanaPrincipal.class.getResource(p.getImagenPath()));
        icon.setImage(icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
        switch (j.getTorresComidas()) {
            case 0:
                label1Torre1.setIcon(null);
                label1Torre2.setIcon(null);
                break;
            case 1:
                label1Torre1.setIcon(icon);
                label1Torre2.setIcon(null);
                break;
            case 2:
                label1Torre1.setIcon(icon);
                label1Torre2.setIcon(icon);
                break;
        }
        p = new Alfil(j.getColor(),getJuego());
        icon = new ImageIcon(VentanaPrincipal.class.getResource(p.getImagenPath()));
        icon.setImage(icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
        switch (j.getAlfilesComidos()) {
            case 0:
                label1Alfil1.setIcon(null);
                label1Alfil2.setIcon(null);
                break;
            case 1:
                label1Alfil1.setIcon(icon);
                label1Alfil2.setIcon(null);
                break;
            case 2:
                label1Alfil1.setIcon(icon);
                label1Alfil2.setIcon(icon);
                break;
        }
        p = new Caballo(j.getColor(),getJuego());
        icon = new ImageIcon(VentanaPrincipal.class.getResource(p.getImagenPath()));
        icon.setImage(icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
        switch (j.getCaballosComidos()) {
            case 0:
                label1Caballo1.setIcon(null);
                label1Caballo2.setIcon(null);
                break;
            case 1:
                label1Caballo1.setIcon(icon);
                label1Caballo2.setIcon(null);
                break;
            case 2:
                label1Caballo1.setIcon(icon);
                label1Caballo2.setIcon(icon);
                break;
        }
        p = new Peon(j.getColor(),getJuego());
        icon = new ImageIcon(VentanaPrincipal.class.getResource(p.getImagenPath()));
        icon.setImage(icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
        switch (j.getPeonesComidos()) {
            case 0:
                label1Peon1.setIcon(null);
                label1Peon2.setIcon(null);
                label1Peon3.setIcon(null);
                label1Peon4.setIcon(null);
                label1Peon5.setIcon(null);
                label1Peon6.setIcon(null);
                label1Peon7.setIcon(null);
                label1Peon8.setIcon(null);
                break;
            case 1:
                label1Peon1.setIcon(icon);
                label1Peon2.setIcon(null);
                label1Peon3.setIcon(null);
                label1Peon4.setIcon(null);
                label1Peon5.setIcon(null);
                label1Peon6.setIcon(null);
                label1Peon7.setIcon(null);
                label1Peon8.setIcon(null);
                break;
            case 2:
                label1Peon1.setIcon(icon);
                label1Peon2.setIcon(icon);
                label1Peon3.setIcon(null);
                label1Peon4.setIcon(null);
                label1Peon5.setIcon(null);
                label1Peon6.setIcon(null);
                label1Peon7.setIcon(null);
                label1Peon8.setIcon(null);
                break;
            case 3:
                label1Peon1.setIcon(icon);
                label1Peon2.setIcon(icon);
                label1Peon3.setIcon(icon);
                label1Peon4.setIcon(null);
                label1Peon5.setIcon(null);
                label1Peon6.setIcon(null);
                label1Peon7.setIcon(null);
                label1Peon8.setIcon(null);
                break;
            case 4:
                label1Peon1.setIcon(icon);
                label1Peon2.setIcon(icon);
                label1Peon3.setIcon(icon);
                label1Peon4.setIcon(icon);
                label1Peon5.setIcon(null);
                label1Peon6.setIcon(null);
                label1Peon7.setIcon(null);
                label1Peon8.setIcon(null);
                break;
            case 5:
                label1Peon1.setIcon(icon);
                label1Peon2.setIcon(icon);
                label1Peon3.setIcon(icon);
                label1Peon4.setIcon(icon);
                label1Peon5.setIcon(icon);
                label1Peon6.setIcon(null);
                label1Peon7.setIcon(null);
                label1Peon8.setIcon(null);
                break;
            case 6:
                label1Peon1.setIcon(icon);
                label1Peon2.setIcon(icon);
                label1Peon3.setIcon(icon);
                label1Peon4.setIcon(icon);
                label1Peon5.setIcon(icon);
                label1Peon6.setIcon(icon);
                label1Peon7.setIcon(null);
                label1Peon8.setIcon(null);
                break;
            case 7:
                label1Peon1.setIcon(icon);
                label1Peon2.setIcon(icon);
                label1Peon3.setIcon(icon);
                label1Peon4.setIcon(icon);
                label1Peon5.setIcon(icon);
                label1Peon6.setIcon(icon);
                label1Peon7.setIcon(icon);
                label1Peon8.setIcon(null);
                break;
            case 8:
                label1Peon1.setIcon(icon);
                label1Peon2.setIcon(icon);
                label1Peon3.setIcon(icon);
                label1Peon4.setIcon(icon);
                label1Peon5.setIcon(icon);
                label1Peon6.setIcon(icon);
                label1Peon7.setIcon(icon);
                label1Peon8.setIcon(icon);
                break;
        }
        label1Puntos.setText(String.valueOf(9 * j.getDamasComidas() + 5 * j.getTorresComidas() + 3 * (j.getAlfilesComidos() + j.getCaballosComidos()) + j.getPeonesComidos()));
        j = getJuego().getOponente();
        p = new Dama(j.getColor(),getJuego());
        icon = new ImageIcon(VentanaPrincipal.class.getResource(p.getImagenPath()));
        icon.setImage(icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
        switch (j.getDamasComidas()) {
            case 0:
                label2Dama.setIcon(null);
                break;
            case 1:
                label2Dama.setIcon(icon);
                break;
        }
        p = new Torre(j.getColor(),getJuego());
        icon = new ImageIcon(VentanaPrincipal.class.getResource(p.getImagenPath()));
        icon.setImage(icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
        switch (j.getTorresComidas()) {
            case 0:
                label2Torre1.setIcon(null);
                label2Torre2.setIcon(null);
                break;
            case 1:
                label2Torre1.setIcon(icon);
                label2Torre2.setIcon(null);
                break;
            case 2:
                label2Torre1.setIcon(icon);
                label2Torre2.setIcon(icon);
                break;
        }
        p = new Alfil(j.getColor(),getJuego());
        icon = new ImageIcon(VentanaPrincipal.class.getResource(p.getImagenPath()));
        icon.setImage(icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
        switch (j.getAlfilesComidos()) {
            case 0:
                label2Alfil1.setIcon(null);
                label2Alfil2.setIcon(null);
                break;
            case 1:
                label2Alfil1.setIcon(icon);
                label2Alfil2.setIcon(null);
                break;
            case 2:
                label2Alfil1.setIcon(icon);
                label2Alfil2.setIcon(icon);
                break;
        }
        p = new Caballo(j.getColor(),getJuego());
        icon = new ImageIcon(VentanaPrincipal.class.getResource(p.getImagenPath()));
        icon.setImage(icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
        switch (j.getCaballosComidos()) {
            case 0:
                label2Caballo1.setIcon(null);
                label2Caballo2.setIcon(null);
                break;
            case 1:
                label2Caballo1.setIcon(icon);
                label2Caballo2.setIcon(null);
                break;
            case 2:
                label2Caballo1.setIcon(icon);
                label2Caballo2.setIcon(icon);
                break;
        }
        p = new Peon(j.getColor(),getJuego());
        icon = new ImageIcon(VentanaPrincipal.class.getResource(p.getImagenPath()));
        icon.setImage(icon.getImage().getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH));
        switch (j.getPeonesComidos()) {
            case 0:
                label2Peon1.setIcon(null);
                label2Peon2.setIcon(null);
                label2Peon3.setIcon(null);
                label2Peon4.setIcon(null);
                label2Peon5.setIcon(null);
                label2Peon6.setIcon(null);
                label2Peon7.setIcon(null);
                label2Peon8.setIcon(null);
                break;
            case 1:
                label2Peon1.setIcon(icon);
                label2Peon2.setIcon(null);
                label2Peon3.setIcon(null);
                label2Peon4.setIcon(null);
                label2Peon5.setIcon(null);
                label2Peon6.setIcon(null);
                label2Peon7.setIcon(null);
                label2Peon8.setIcon(null);
                break;
            case 2:
                label2Peon1.setIcon(icon);
                label2Peon2.setIcon(icon);
                label2Peon3.setIcon(null);
                label2Peon4.setIcon(null);
                label2Peon5.setIcon(null);
                label2Peon6.setIcon(null);
                label2Peon7.setIcon(null);
                label2Peon8.setIcon(null);
                break;
            case 3:
                label2Peon1.setIcon(icon);
                label2Peon2.setIcon(icon);
                label2Peon3.setIcon(icon);
                label2Peon4.setIcon(null);
                label2Peon5.setIcon(null);
                label2Peon6.setIcon(null);
                label2Peon7.setIcon(null);
                label2Peon8.setIcon(null);
                break;
            case 4:
                label2Peon1.setIcon(icon);
                label2Peon2.setIcon(icon);
                label2Peon3.setIcon(icon);
                label2Peon4.setIcon(icon);
                label2Peon5.setIcon(null);
                label2Peon6.setIcon(null);
                label2Peon7.setIcon(null);
                label2Peon8.setIcon(null);
                break;
            case 5:
                label2Peon1.setIcon(icon);
                label2Peon2.setIcon(icon);
                label2Peon3.setIcon(icon);
                label2Peon4.setIcon(icon);
                label2Peon5.setIcon(icon);
                label2Peon6.setIcon(null);
                label2Peon7.setIcon(null);
                label2Peon8.setIcon(null);
                break;
            case 6:
                label2Peon1.setIcon(icon);
                label2Peon2.setIcon(icon);
                label2Peon3.setIcon(icon);
                label2Peon4.setIcon(icon);
                label2Peon5.setIcon(icon);
                label2Peon6.setIcon(icon);
                label2Peon7.setIcon(null);
                label2Peon8.setIcon(null);
                break;
            case 7:
                label2Peon1.setIcon(icon);
                label2Peon2.setIcon(icon);
                label2Peon3.setIcon(icon);
                label2Peon4.setIcon(icon);
                label2Peon5.setIcon(icon);
                label2Peon6.setIcon(icon);
                label2Peon7.setIcon(icon);
                label2Peon8.setIcon(null);
                break;
            case 8:
                label2Peon1.setIcon(icon);
                label2Peon2.setIcon(icon);
                label2Peon3.setIcon(icon);
                label2Peon4.setIcon(icon);
                label2Peon5.setIcon(icon);
                label2Peon6.setIcon(icon);
                label2Peon7.setIcon(icon);
                label2Peon8.setIcon(icon);
                break;
        }
        label2Puntos.setText(String.valueOf(9 * j.getDamasComidas() + 5 * j.getTorresComidas() + 3 * (j.getAlfilesComidos() + j.getCaballosComidos()) + j.getPeonesComidos()));
        Jugada jug=getJuego().getTablero().getUltimaJugadaValida();
        if(jug.getPosicion()!=null){
            getBotones().get(jug.getPosicion().getX()).get(jug.getPosicion().getY()).setBackground(new Color(150,200,250));
            if(jug.getMovimiento()!=null){
                getBotones().get(jug.getPosicionFinal().getX()).get(jug.getPosicionFinal().getY()).setBackground(new Color(150,200,250));
            }
        }
    }

    private void coloresFondo(Posicion p) {
        if ((p.getX() + p.getY()) % 2 == 0) {
            getBotones().get(p.getX()).get(p.getY()).setBackground(new Color(150, 125, 100));
        } else {
            getBotones().get(p.getX()).get(p.getY()).setBackground(new Color(200, 175, 150));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        celda71 = new javax.swing.JButton();
        celda70 = new javax.swing.JButton();
        celda72 = new javax.swing.JButton();
        celda73 = new javax.swing.JButton();
        celda76 = new javax.swing.JButton();
        celda77 = new javax.swing.JButton();
        celda74 = new javax.swing.JButton();
        celda75 = new javax.swing.JButton();
        celda65 = new javax.swing.JButton();
        celda66 = new javax.swing.JButton();
        celda63 = new javax.swing.JButton();
        celda64 = new javax.swing.JButton();
        celda62 = new javax.swing.JButton();
        celda61 = new javax.swing.JButton();
        celda67 = new javax.swing.JButton();
        celda60 = new javax.swing.JButton();
        celda55 = new javax.swing.JButton();
        celda56 = new javax.swing.JButton();
        celda57 = new javax.swing.JButton();
        celda51 = new javax.swing.JButton();
        celda52 = new javax.swing.JButton();
        celda53 = new javax.swing.JButton();
        celda54 = new javax.swing.JButton();
        celda50 = new javax.swing.JButton();
        celda40 = new javax.swing.JButton();
        celda41 = new javax.swing.JButton();
        celda46 = new javax.swing.JButton();
        celda47 = new javax.swing.JButton();
        celda44 = new javax.swing.JButton();
        celda45 = new javax.swing.JButton();
        celda43 = new javax.swing.JButton();
        celda42 = new javax.swing.JButton();
        celda32 = new javax.swing.JButton();
        celda33 = new javax.swing.JButton();
        celda30 = new javax.swing.JButton();
        celda31 = new javax.swing.JButton();
        celda37 = new javax.swing.JButton();
        celda34 = new javax.swing.JButton();
        celda36 = new javax.swing.JButton();
        celda35 = new javax.swing.JButton();
        celda26 = new javax.swing.JButton();
        celda27 = new javax.swing.JButton();
        celda20 = new javax.swing.JButton();
        celda21 = new javax.swing.JButton();
        celda22 = new javax.swing.JButton();
        celda23 = new javax.swing.JButton();
        celda24 = new javax.swing.JButton();
        celda25 = new javax.swing.JButton();
        celda12 = new javax.swing.JButton();
        celda14 = new javax.swing.JButton();
        celda10 = new javax.swing.JButton();
        celda11 = new javax.swing.JButton();
        celda16 = new javax.swing.JButton();
        celda15 = new javax.swing.JButton();
        celda13 = new javax.swing.JButton();
        celda17 = new javax.swing.JButton();
        celda06 = new javax.swing.JButton();
        celda05 = new javax.swing.JButton();
        celda07 = new javax.swing.JButton();
        celda01 = new javax.swing.JButton();
        celda02 = new javax.swing.JButton();
        celda03 = new javax.swing.JButton();
        celda04 = new javax.swing.JButton();
        celda00 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        nombreNegra = new javax.swing.JLabel();
        reloj1 = new javax.swing.JTextField();
        reloj2 = new javax.swing.JTextField();
        nombreBlanca = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listaJugador = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaOponente = new javax.swing.JList();
        label2Dama = new javax.swing.JLabel();
        label2Peon1 = new javax.swing.JLabel();
        label2Torre1 = new javax.swing.JLabel();
        label2Peon2 = new javax.swing.JLabel();
        label2Peon4 = new javax.swing.JLabel();
        label2Alfil1 = new javax.swing.JLabel();
        label2Torre2 = new javax.swing.JLabel();
        label2Peon3 = new javax.swing.JLabel();
        label2Caballo1 = new javax.swing.JLabel();
        label2Alfil2 = new javax.swing.JLabel();
        label2Peon5 = new javax.swing.JLabel();
        label2Peon6 = new javax.swing.JLabel();
        label2Peon7 = new javax.swing.JLabel();
        label2Peon8 = new javax.swing.JLabel();
        label2Caballo2 = new javax.swing.JLabel();
        label1Dama = new javax.swing.JLabel();
        label1Torre1 = new javax.swing.JLabel();
        label1Peon1 = new javax.swing.JLabel();
        label1Peon2 = new javax.swing.JLabel();
        label1Peon3 = new javax.swing.JLabel();
        label1Torre2 = new javax.swing.JLabel();
        label1Alfil1 = new javax.swing.JLabel();
        label1Peon4 = new javax.swing.JLabel();
        label1Peon5 = new javax.swing.JLabel();
        label1Alfil2 = new javax.swing.JLabel();
        label1Caballo1 = new javax.swing.JLabel();
        label1Peon6 = new javax.swing.JLabel();
        label1Peon7 = new javax.swing.JLabel();
        label1Caballo2 = new javax.swing.JLabel();
        label1Peon8 = new javax.swing.JLabel();
        label2Puntos = new javax.swing.JLabel();
        label1Puntos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Ajedrez");
        setBackground(new java.awt.Color(225, 200, 175));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(100, 75, 50));
        jPanel1.setPreferredSize(new java.awt.Dimension(550, 550));

        celda71.setBackground(new java.awt.Color(150, 125, 100));
        celda71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda71ActionPerformed(evt);
            }
        });

        celda70.setBackground(new java.awt.Color(200, 175, 150));
        celda70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda70ActionPerformed(evt);
            }
        });

        celda72.setBackground(new java.awt.Color(200, 175, 150));
        celda72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda72ActionPerformed(evt);
            }
        });

        celda73.setBackground(new java.awt.Color(150, 125, 100));
        celda73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda73ActionPerformed(evt);
            }
        });

        celda76.setBackground(new java.awt.Color(200, 175, 150));
        celda76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda76ActionPerformed(evt);
            }
        });

        celda77.setBackground(new java.awt.Color(150, 125, 100));
        celda77.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda77ActionPerformed(evt);
            }
        });

        celda74.setBackground(new java.awt.Color(200, 175, 150));
        celda74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda74ActionPerformed(evt);
            }
        });

        celda75.setBackground(new java.awt.Color(150, 125, 100));
        celda75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda75ActionPerformed(evt);
            }
        });

        celda65.setBackground(new java.awt.Color(200, 175, 150));
        celda65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda65ActionPerformed(evt);
            }
        });

        celda66.setBackground(new java.awt.Color(150, 125, 100));
        celda66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda66ActionPerformed(evt);
            }
        });

        celda63.setBackground(new java.awt.Color(200, 175, 150));
        celda63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda63ActionPerformed(evt);
            }
        });

        celda64.setBackground(new java.awt.Color(150, 125, 100));
        celda64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda64ActionPerformed(evt);
            }
        });

        celda62.setBackground(new java.awt.Color(150, 125, 100));
        celda62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda62ActionPerformed(evt);
            }
        });

        celda61.setBackground(new java.awt.Color(200, 175, 150));
        celda61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda61ActionPerformed(evt);
            }
        });

        celda67.setBackground(new java.awt.Color(200, 175, 150));
        celda67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda67ActionPerformed(evt);
            }
        });

        celda60.setBackground(new java.awt.Color(150, 125, 100));
        celda60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda60ActionPerformed(evt);
            }
        });

        celda55.setBackground(new java.awt.Color(150, 125, 100));
        celda55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda55ActionPerformed(evt);
            }
        });

        celda56.setBackground(new java.awt.Color(200, 175, 150));
        celda56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda56ActionPerformed(evt);
            }
        });

        celda57.setBackground(new java.awt.Color(150, 125, 100));
        celda57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda57ActionPerformed(evt);
            }
        });

        celda51.setBackground(new java.awt.Color(150, 125, 100));
        celda51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda51ActionPerformed(evt);
            }
        });

        celda52.setBackground(new java.awt.Color(200, 175, 150));
        celda52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda52ActionPerformed(evt);
            }
        });

        celda53.setBackground(new java.awt.Color(150, 125, 100));
        celda53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda53ActionPerformed(evt);
            }
        });

        celda54.setBackground(new java.awt.Color(200, 175, 150));
        celda54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda54ActionPerformed(evt);
            }
        });

        celda50.setBackground(new java.awt.Color(200, 175, 150));
        celda50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda50ActionPerformed(evt);
            }
        });

        celda40.setBackground(new java.awt.Color(150, 125, 100));
        celda40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda40ActionPerformed(evt);
            }
        });

        celda41.setBackground(new java.awt.Color(200, 175, 150));
        celda41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda41ActionPerformed(evt);
            }
        });

        celda46.setBackground(new java.awt.Color(150, 125, 100));
        celda46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda46ActionPerformed(evt);
            }
        });

        celda47.setBackground(new java.awt.Color(200, 175, 150));
        celda47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda47ActionPerformed(evt);
            }
        });

        celda44.setBackground(new java.awt.Color(150, 125, 100));
        celda44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda44ActionPerformed(evt);
            }
        });

        celda45.setBackground(new java.awt.Color(200, 175, 150));
        celda45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda45ActionPerformed(evt);
            }
        });

        celda43.setBackground(new java.awt.Color(200, 175, 150));
        celda43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda43ActionPerformed(evt);
            }
        });

        celda42.setBackground(new java.awt.Color(150, 125, 100));
        celda42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda42ActionPerformed(evt);
            }
        });

        celda32.setBackground(new java.awt.Color(200, 175, 150));
        celda32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda32ActionPerformed(evt);
            }
        });

        celda33.setBackground(new java.awt.Color(150, 125, 100));
        celda33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda33ActionPerformed(evt);
            }
        });

        celda30.setBackground(new java.awt.Color(200, 175, 150));
        celda30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda30ActionPerformed(evt);
            }
        });

        celda31.setBackground(new java.awt.Color(150, 125, 100));
        celda31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda31ActionPerformed(evt);
            }
        });

        celda37.setBackground(new java.awt.Color(150, 125, 100));
        celda37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda37ActionPerformed(evt);
            }
        });

        celda34.setBackground(new java.awt.Color(200, 175, 150));
        celda34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda34ActionPerformed(evt);
            }
        });

        celda36.setBackground(new java.awt.Color(200, 175, 150));
        celda36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda36ActionPerformed(evt);
            }
        });

        celda35.setBackground(new java.awt.Color(150, 125, 100));
        celda35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda35ActionPerformed(evt);
            }
        });

        celda26.setBackground(new java.awt.Color(150, 125, 100));
        celda26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda26ActionPerformed(evt);
            }
        });

        celda27.setBackground(new java.awt.Color(200, 175, 150));
        celda27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda27ActionPerformed(evt);
            }
        });

        celda20.setBackground(new java.awt.Color(150, 125, 100));
        celda20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda20ActionPerformed(evt);
            }
        });

        celda21.setBackground(new java.awt.Color(200, 175, 150));
        celda21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda21ActionPerformed(evt);
            }
        });

        celda22.setBackground(new java.awt.Color(150, 125, 100));
        celda22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda22ActionPerformed(evt);
            }
        });

        celda23.setBackground(new java.awt.Color(200, 175, 150));
        celda23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda23ActionPerformed(evt);
            }
        });

        celda24.setBackground(new java.awt.Color(150, 125, 100));
        celda24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda24ActionPerformed(evt);
            }
        });

        celda25.setBackground(new java.awt.Color(200, 175, 150));
        celda25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda25ActionPerformed(evt);
            }
        });

        celda12.setBackground(new java.awt.Color(200, 175, 150));
        celda12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda12ActionPerformed(evt);
            }
        });

        celda14.setBackground(new java.awt.Color(200, 175, 150));
        celda14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda14ActionPerformed(evt);
            }
        });

        celda10.setBackground(new java.awt.Color(200, 175, 150));
        celda10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda10ActionPerformed(evt);
            }
        });

        celda11.setBackground(new java.awt.Color(150, 125, 100));
        celda11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda11ActionPerformed(evt);
            }
        });

        celda16.setBackground(new java.awt.Color(200, 175, 150));
        celda16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda16ActionPerformed(evt);
            }
        });

        celda15.setBackground(new java.awt.Color(150, 125, 100));
        celda15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda15ActionPerformed(evt);
            }
        });

        celda13.setBackground(new java.awt.Color(150, 125, 100));
        celda13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda13ActionPerformed(evt);
            }
        });

        celda17.setBackground(new java.awt.Color(150, 125, 100));
        celda17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda17ActionPerformed(evt);
            }
        });

        celda06.setBackground(new java.awt.Color(150, 125, 100));
        celda06.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda06ActionPerformed(evt);
            }
        });

        celda05.setBackground(new java.awt.Color(200, 175, 150));
        celda05.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda05ActionPerformed(evt);
            }
        });

        celda07.setBackground(new java.awt.Color(200, 175, 150));
        celda07.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda07ActionPerformed(evt);
            }
        });

        celda01.setBackground(new java.awt.Color(200, 175, 150));
        celda01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda01ActionPerformed(evt);
            }
        });

        celda02.setBackground(new java.awt.Color(150, 125, 100));
        celda02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda02ActionPerformed(evt);
            }
        });

        celda03.setBackground(new java.awt.Color(200, 175, 150));
        celda03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda03ActionPerformed(evt);
            }
        });

        celda04.setBackground(new java.awt.Color(150, 125, 100));
        celda04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda04ActionPerformed(evt);
            }
        });

        celda00.setBackground(new java.awt.Color(150, 125, 100));
        celda00.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                celda00ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(celda60, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(celda61, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(celda62, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(celda63, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(celda64, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(celda65, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(celda66, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(celda67, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(celda70, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(celda71, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(celda72, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(celda73, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(celda74, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(celda75, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(celda76, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(celda77, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(celda50, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda51, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda52, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda53, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda54, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda55, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda56, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda57, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(celda40, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda41, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda42, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda43, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda44, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda45, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda46, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda47, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(celda30, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda31, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda32, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda33, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda34, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda35, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda36, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda37, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(celda20, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda21, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda23, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda24, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda25, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda26, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda27, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(celda10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(celda00, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda01, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda02, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda03, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda04, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda05, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda06, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(celda07, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(18, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(celda70, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda71, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda72, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda73, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda74, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda75, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda76, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda77, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(celda60, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda61, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda62, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda63, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda64, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda65, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda66, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda67, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(celda50, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda51, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda52, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda53, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda54, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda55, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda56, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda57, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(celda40, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda41, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda42, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda43, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda44, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda45, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda46, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda47, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(celda30, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda32, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda33, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda34, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda35, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda36, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda37, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(celda20, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda21, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda22, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda23, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda26, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(celda10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda12, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda13, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda14, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(celda00, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda01, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda02, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda03, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda04, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda05, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda06, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(celda07, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(250, 235, 200));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        nombreNegra.setFont(new java.awt.Font("Verdana", 1, 14));
        nombreNegra.setForeground(new java.awt.Color(100, 75, 50));
        nombreNegra.setText("Jugador 2");

        reloj1.setBackground(new java.awt.Color(250, 235, 200));
        reloj1.setEditable(false);
        reloj1.setFont(new java.awt.Font("Verdana", 1, 14));
        reloj1.setForeground(new java.awt.Color(100, 75, 50));
        reloj1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        reloj2.setBackground(new java.awt.Color(250, 235, 200));
        reloj2.setEditable(false);
        reloj2.setFont(new java.awt.Font("Verdana", 1, 14));
        reloj2.setForeground(new java.awt.Color(100, 75, 50));
        reloj2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        nombreBlanca.setFont(new java.awt.Font("Verdana", 1, 14));
        nombreBlanca.setForeground(new java.awt.Color(100, 75, 50));
        nombreBlanca.setText("Jugador 1");

        listaJugador.setBackground(new java.awt.Color(250, 235, 200));
        listaJugador.setForeground(new java.awt.Color(100, 75, 50));
        jScrollPane1.setViewportView(listaJugador);

        listaOponente.setBackground(new java.awt.Color(250, 235, 200));
        listaOponente.setForeground(new java.awt.Color(100, 75, 50));
        jScrollPane2.setViewportView(listaOponente);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(nombreBlanca)
                                .addGap(30, 30, 30)
                                .addComponent(reloj1, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(nombreNegra)
                                .addGap(30, 30, 30)
                                .addComponent(reloj2, javax.swing.GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(52, 52, 52))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1Dama, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1Peon1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1Torre1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1Peon2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1Torre2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1Peon3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1Alfil1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1Peon4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1Alfil2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1Peon5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1Caballo1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1Peon6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label1Caballo2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1Peon7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label1Peon8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label1Puntos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label2Dama, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2Peon1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label2Torre1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2Peon2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label2Torre2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2Peon3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label2Alfil1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2Peon4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label2Alfil2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2Peon5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label2Caballo1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2Peon6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(label2Caballo2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2Peon7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(label2Peon8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(label2Puntos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reloj2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreNegra))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label2Dama, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label2Peon1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label2Torre1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label2Peon2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label2Torre2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label2Peon3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label2Alfil1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label2Peon4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label2Alfil2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label2Peon5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label2Caballo1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label2Peon6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label2Caballo2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label2Peon7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label2Puntos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label2Peon8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label1Dama, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label1Peon1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label1Torre1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label1Peon2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label1Torre2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label1Peon3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label1Alfil1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label1Peon4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label1Alfil2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label1Peon5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label1Caballo1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label1Peon6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label1Caballo2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label1Peon7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(label1Puntos, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(label1Peon8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(reloj1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nombreBlanca))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 544, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void celda00ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda00ActionPerformed
        seApreto(0, 0);
    }//GEN-LAST:event_celda00ActionPerformed

    private void celda02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda02ActionPerformed
        seApreto(0, 2);
    }//GEN-LAST:event_celda02ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
    }//GEN-LAST:event_formWindowClosed

    private void celda01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda01ActionPerformed
        seApreto(0, 1);
    }//GEN-LAST:event_celda01ActionPerformed

    private void celda03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda03ActionPerformed
        seApreto(0, 3);
    }//GEN-LAST:event_celda03ActionPerformed

    private void celda04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda04ActionPerformed
        seApreto(0, 4);
    }//GEN-LAST:event_celda04ActionPerformed

    private void celda05ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda05ActionPerformed
        seApreto(0, 5);
    }//GEN-LAST:event_celda05ActionPerformed

    private void celda06ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda06ActionPerformed
        seApreto(0, 6);
    }//GEN-LAST:event_celda06ActionPerformed

    private void celda07ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda07ActionPerformed
        seApreto(0, 7);
    }//GEN-LAST:event_celda07ActionPerformed

    private void celda10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda10ActionPerformed
        seApreto(1, 0);
    }//GEN-LAST:event_celda10ActionPerformed

    private void celda11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda11ActionPerformed
        seApreto(1, 1);
    }//GEN-LAST:event_celda11ActionPerformed

    private void celda12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda12ActionPerformed
        seApreto(1, 2);
    }//GEN-LAST:event_celda12ActionPerformed

    private void celda13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda13ActionPerformed
        seApreto(1, 3);
    }//GEN-LAST:event_celda13ActionPerformed

    private void celda14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda14ActionPerformed
        seApreto(1, 4);
    }//GEN-LAST:event_celda14ActionPerformed

    private void celda15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda15ActionPerformed
        seApreto(1, 5);
    }//GEN-LAST:event_celda15ActionPerformed

    private void celda16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda16ActionPerformed
        seApreto(1, 6);
    }//GEN-LAST:event_celda16ActionPerformed

    private void celda17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda17ActionPerformed
        seApreto(1, 7);
    }//GEN-LAST:event_celda17ActionPerformed

    private void celda20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda20ActionPerformed
        seApreto(2, 0);
    }//GEN-LAST:event_celda20ActionPerformed

    private void celda21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda21ActionPerformed
        seApreto(2, 1);
    }//GEN-LAST:event_celda21ActionPerformed

    private void celda22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda22ActionPerformed
        seApreto(2, 2);
    }//GEN-LAST:event_celda22ActionPerformed

    private void celda23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda23ActionPerformed
        seApreto(2, 3);
    }//GEN-LAST:event_celda23ActionPerformed

    private void celda24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda24ActionPerformed
        seApreto(2, 4);
    }//GEN-LAST:event_celda24ActionPerformed

    private void celda25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda25ActionPerformed
        seApreto(2, 5);
    }//GEN-LAST:event_celda25ActionPerformed

    private void celda26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda26ActionPerformed
        seApreto(2, 6);
    }//GEN-LAST:event_celda26ActionPerformed

    private void celda27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda27ActionPerformed
        seApreto(2, 7);
    }//GEN-LAST:event_celda27ActionPerformed

    private void celda30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda30ActionPerformed
        seApreto(3, 0);
    }//GEN-LAST:event_celda30ActionPerformed

    private void celda31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda31ActionPerformed
        seApreto(3, 1);
    }//GEN-LAST:event_celda31ActionPerformed

    private void celda32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda32ActionPerformed
        seApreto(3, 2);
    }//GEN-LAST:event_celda32ActionPerformed

    private void celda33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda33ActionPerformed
        seApreto(3, 3);
    }//GEN-LAST:event_celda33ActionPerformed

    private void celda34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda34ActionPerformed
        seApreto(3, 4);
    }//GEN-LAST:event_celda34ActionPerformed

    private void celda35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda35ActionPerformed
        seApreto(3, 5);
    }//GEN-LAST:event_celda35ActionPerformed

    private void celda36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda36ActionPerformed
        seApreto(3, 6);
    }//GEN-LAST:event_celda36ActionPerformed

    private void celda37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda37ActionPerformed
        seApreto(3, 7);
    }//GEN-LAST:event_celda37ActionPerformed

    private void celda40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda40ActionPerformed
        seApreto(4, 0);
    }//GEN-LAST:event_celda40ActionPerformed

    private void celda41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda41ActionPerformed
        seApreto(4, 1);
    }//GEN-LAST:event_celda41ActionPerformed

    private void celda42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda42ActionPerformed
        seApreto(4, 2);
    }//GEN-LAST:event_celda42ActionPerformed

    private void celda43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda43ActionPerformed
        seApreto(4, 3);
    }//GEN-LAST:event_celda43ActionPerformed

    private void celda44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda44ActionPerformed
        seApreto(4, 4);
    }//GEN-LAST:event_celda44ActionPerformed

    private void celda45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda45ActionPerformed
        seApreto(4, 5);
    }//GEN-LAST:event_celda45ActionPerformed

    private void celda46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda46ActionPerformed
        seApreto(4, 6);
    }//GEN-LAST:event_celda46ActionPerformed

    private void celda47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda47ActionPerformed
        seApreto(4, 7);
    }//GEN-LAST:event_celda47ActionPerformed

    private void celda50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda50ActionPerformed
        seApreto(5, 0);
    }//GEN-LAST:event_celda50ActionPerformed

    private void celda51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda51ActionPerformed
        seApreto(5, 1);
    }//GEN-LAST:event_celda51ActionPerformed

    private void celda52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda52ActionPerformed
        seApreto(5, 2);
    }//GEN-LAST:event_celda52ActionPerformed

    private void celda53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda53ActionPerformed
        seApreto(5, 3);
    }//GEN-LAST:event_celda53ActionPerformed

    private void celda54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda54ActionPerformed
        seApreto(5, 4);
    }//GEN-LAST:event_celda54ActionPerformed

    private void celda55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda55ActionPerformed
        seApreto(5, 5);
    }//GEN-LAST:event_celda55ActionPerformed

    private void celda56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda56ActionPerformed
        seApreto(5, 6);
    }//GEN-LAST:event_celda56ActionPerformed

    private void celda57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda57ActionPerformed
        seApreto(5, 7);
    }//GEN-LAST:event_celda57ActionPerformed

    private void celda60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda60ActionPerformed
        seApreto(6, 0);
    }//GEN-LAST:event_celda60ActionPerformed

    private void celda61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda61ActionPerformed
        seApreto(6, 1);
    }//GEN-LAST:event_celda61ActionPerformed

    private void celda62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda62ActionPerformed
        seApreto(6, 2);
    }//GEN-LAST:event_celda62ActionPerformed

    private void celda63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda63ActionPerformed
        seApreto(6, 3);
    }//GEN-LAST:event_celda63ActionPerformed

    private void celda64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda64ActionPerformed
        seApreto(6, 4);
    }//GEN-LAST:event_celda64ActionPerformed

    private void celda65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda65ActionPerformed
        seApreto(6, 5);
    }//GEN-LAST:event_celda65ActionPerformed

    private void celda66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda66ActionPerformed
        seApreto(6, 6);
    }//GEN-LAST:event_celda66ActionPerformed

    private void celda67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda67ActionPerformed
        seApreto(6, 7);
    }//GEN-LAST:event_celda67ActionPerformed

    private void celda70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda70ActionPerformed
        seApreto(7, 0);
    }//GEN-LAST:event_celda70ActionPerformed

    private void celda71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda71ActionPerformed
        seApreto(7, 1);
    }//GEN-LAST:event_celda71ActionPerformed

    private void celda72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda72ActionPerformed
        seApreto(7, 2);
    }//GEN-LAST:event_celda72ActionPerformed

    private void celda73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda73ActionPerformed
        seApreto(7, 3);
    }//GEN-LAST:event_celda73ActionPerformed

    private void celda74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda74ActionPerformed
        seApreto(7, 4);
    }//GEN-LAST:event_celda74ActionPerformed

    private void celda75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda75ActionPerformed
        seApreto(7, 5);
    }//GEN-LAST:event_celda75ActionPerformed

    private void celda76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda76ActionPerformed
        seApreto(7, 6);
    }//GEN-LAST:event_celda76ActionPerformed

    private void celda77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_celda77ActionPerformed
        seApreto(7, 7);
    }//GEN-LAST:event_celda77ActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        this.dispose();
    }//GEN-LAST:event_formWindowClosing

    private void seApreto(int x, int y) {
        if (((getJuego().getEstado() == Juego.Estados.MITURNO) || (getJuego().getEstado() == Juego.Estados.SUTURNO)) && Humano.class.isInstance(getJuego().getJugadorActual())) {
            getBotones().get(x).get(y).setBackground(Color.yellow);
            getJuego().getTablero().seApreto(x,y);
        }
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton celda00;
    private javax.swing.JButton celda01;
    private javax.swing.JButton celda02;
    private javax.swing.JButton celda03;
    private javax.swing.JButton celda04;
    private javax.swing.JButton celda05;
    private javax.swing.JButton celda06;
    private javax.swing.JButton celda07;
    private javax.swing.JButton celda10;
    private javax.swing.JButton celda11;
    private javax.swing.JButton celda12;
    private javax.swing.JButton celda13;
    private javax.swing.JButton celda14;
    private javax.swing.JButton celda15;
    private javax.swing.JButton celda16;
    private javax.swing.JButton celda17;
    private javax.swing.JButton celda20;
    private javax.swing.JButton celda21;
    private javax.swing.JButton celda22;
    private javax.swing.JButton celda23;
    private javax.swing.JButton celda24;
    private javax.swing.JButton celda25;
    private javax.swing.JButton celda26;
    private javax.swing.JButton celda27;
    private javax.swing.JButton celda30;
    private javax.swing.JButton celda31;
    private javax.swing.JButton celda32;
    private javax.swing.JButton celda33;
    private javax.swing.JButton celda34;
    private javax.swing.JButton celda35;
    private javax.swing.JButton celda36;
    private javax.swing.JButton celda37;
    private javax.swing.JButton celda40;
    private javax.swing.JButton celda41;
    private javax.swing.JButton celda42;
    private javax.swing.JButton celda43;
    private javax.swing.JButton celda44;
    private javax.swing.JButton celda45;
    private javax.swing.JButton celda46;
    private javax.swing.JButton celda47;
    private javax.swing.JButton celda50;
    private javax.swing.JButton celda51;
    private javax.swing.JButton celda52;
    private javax.swing.JButton celda53;
    private javax.swing.JButton celda54;
    private javax.swing.JButton celda55;
    private javax.swing.JButton celda56;
    private javax.swing.JButton celda57;
    private javax.swing.JButton celda60;
    private javax.swing.JButton celda61;
    private javax.swing.JButton celda62;
    private javax.swing.JButton celda63;
    private javax.swing.JButton celda64;
    private javax.swing.JButton celda65;
    private javax.swing.JButton celda66;
    private javax.swing.JButton celda67;
    private javax.swing.JButton celda70;
    private javax.swing.JButton celda71;
    private javax.swing.JButton celda72;
    private javax.swing.JButton celda73;
    private javax.swing.JButton celda74;
    private javax.swing.JButton celda75;
    private javax.swing.JButton celda76;
    private javax.swing.JButton celda77;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel label1Alfil1;
    private javax.swing.JLabel label1Alfil2;
    private javax.swing.JLabel label1Caballo1;
    private javax.swing.JLabel label1Caballo2;
    private javax.swing.JLabel label1Dama;
    private javax.swing.JLabel label1Peon1;
    private javax.swing.JLabel label1Peon2;
    private javax.swing.JLabel label1Peon3;
    private javax.swing.JLabel label1Peon4;
    private javax.swing.JLabel label1Peon5;
    private javax.swing.JLabel label1Peon6;
    private javax.swing.JLabel label1Peon7;
    private javax.swing.JLabel label1Peon8;
    private javax.swing.JLabel label1Puntos;
    private javax.swing.JLabel label1Torre1;
    private javax.swing.JLabel label1Torre2;
    private javax.swing.JLabel label2Alfil1;
    private javax.swing.JLabel label2Alfil2;
    private javax.swing.JLabel label2Caballo1;
    private javax.swing.JLabel label2Caballo2;
    private javax.swing.JLabel label2Dama;
    private javax.swing.JLabel label2Peon1;
    private javax.swing.JLabel label2Peon2;
    private javax.swing.JLabel label2Peon3;
    private javax.swing.JLabel label2Peon4;
    private javax.swing.JLabel label2Peon5;
    private javax.swing.JLabel label2Peon6;
    private javax.swing.JLabel label2Peon7;
    private javax.swing.JLabel label2Peon8;
    private javax.swing.JLabel label2Puntos;
    private javax.swing.JLabel label2Torre1;
    private javax.swing.JLabel label2Torre2;
    private javax.swing.JList listaJugador;
    private javax.swing.JList listaOponente;
    private javax.swing.JLabel nombreBlanca;
    private javax.swing.JLabel nombreNegra;
    private javax.swing.JTextField reloj1;
    private javax.swing.JTextField reloj2;
    // End of variables declaration//GEN-END:variables

    public void update(Observable o, Object arg) {
        if (CuentaRegresiva.class.isInstance(o)) {
            if (getJuego().getEstado() == Juego.Estados.MITURNO) {
                reloj1.setForeground(Color.red);
                reloj2.setForeground(Color.black);
            } else if (getJuego().getEstado() == Juego.Estados.SUTURNO) {
                reloj2.setForeground(Color.red);
                reloj1.setForeground(Color.black);
            } else {
                reloj2.setForeground(Color.black);
                reloj1.setForeground(Color.black);
            }

            reloj1.setText(getJuego().getJugador().getCuenta().getHora());
            reloj2.setText(getJuego().getOponente().getCuenta().getHora());
        }
        if (Tablero.class.isInstance(o)) {
            refrescarTablero();
        }
        if (Juego.class.isInstance(o)) {
            ArrayList<Jugada> j = getJuego().getJugador().getJugadas();
            for (int i = getModelJugador().size(); i < j.size(); i++) {
                getModelJugador().addElement(j.get(i).toString());
            }
            j = getJuego().getOponente().getJugadas();
            for (int i = getModelOponente().size(); i < j.size(); i++) {
                getModelOponente().addElement(j.get(i).toString());
            }
            if (getJuego().getEstado() == Juego.Estados.FINAL) {
                Object rta;
                if (getJuego().getGanador() == null) {
                    rta = ventanaPregunta("Termino en tablas! ¿Desea jugar otro partido?", "Fin del Partido");
                } else {
                    rta = ventanaPregunta("El ganador es " + getJuego().getGanador().getNombre() + "! ¿Desea jugar otro partido?", "Fin del Partido");
                }
                if (rta.equals("Si")) {
                    getJuego().setRespuesta(Juego.Respuestas.SI);
                } else {
                    getJuego().setRespuesta(Juego.Respuestas.NO);
                }
            }
            if (getJuego().getEstado() == Juego.Estados.INICIO) {
                getModelJugador().clear();
                getModelOponente().clear();
            }
            if (getJuego().getEstado() == Juego.Estados.SALIR) {
                this.dispose();
            }
        }
        if (Coronador.class.isInstance(o)) {
            Coronacion v = new Coronacion((Coronador) o,getJuego());
            v.setVisible(true);
        }
    }

    private Object ventanaPregunta(String texto, String titulo) {
        Object[] o={"Si","No"};
        JOptionPane pane = new JOptionPane(texto, JOptionPane.QUESTION_MESSAGE, JOptionPane.YES_NO_OPTION,null,o);
        pane.setBackground(new Color(250, 235, 200));
        pane.setForeground(new Color(100, 75, 50));
        for (int i = 0; i < pane.getComponentCount(); i++) {
            pane.getComponent(i).setBackground(new Color(250, 235, 200));
            pane.getComponent(i).setForeground(new Color(100, 75, 50));
            if (pane.getComponent(i) instanceof Container) {
                Container c = (Container) pane.getComponent(i);
                for (int j = 0; j < c.getComponentCount(); j++) {
                    c.getComponent(j).setBackground(new Color(250, 235, 200));
                    c.getComponent(j).setForeground(new Color(100, 75, 50));
                    if (c.getComponent(j) instanceof Container) {
                        Container co = (Container) c.getComponent(j);
                        for (int k = 0; k < co.getComponentCount(); k++) {
                            co.getComponent(k).setBackground(new Color(250, 235, 200));
                            co.getComponent(k).setForeground(new Color(100, 75, 50));
                            if (co.getComponent(k) instanceof Container) {
                                Container con = (Container) co.getComponent(k);
                                for (int l = 0; l < con.getComponentCount(); l++) {
                                    con.getComponent(l).setBackground(new Color(250, 235, 200));
                                    con.getComponent(l).setForeground(new Color(100, 75, 50));
                                }
                            }
                        }
                    }
                }
            }
        }
        JDialog dialog = pane.createDialog(this, titulo);
        dialog.setVisible(true);
        return pane.getValue();
    }
}
