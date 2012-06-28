package ventanas;

import ajechess.colores.Color.Colores;
import ajechess.juegos.Juego;
import ajechess.piezas.tipo.Alfil;
import ajechess.piezas.tipo.Caballo;
import ajechess.piezas.tipo.Dama;
import ajechess.piezas.tipo.Torre;
import ajechess.piezas.tipo.coronadores.Coronador;
import javax.swing.ImageIcon;

public class Coronacion extends javax.swing.JFrame {

    private Coronador coronador;
    private Juego juego;

    public Coronador getCoronador() {
        return coronador;
    }

    public void setCoronador(Coronador coronador) {
        this.coronador = coronador;
    }

    public Juego getJuego() {
        return juego;
    }

    public void setJuego(Juego juego) {
        this.juego = juego;
    }

    public Coronacion(Coronador cor,Juego juego) {
        initComponents();
        setJuego(juego);
        Colores color=getJuego().getJugadorActual().getColor();
        Dama d=new Dama(color,getJuego());
        Torre t=new Torre(color,getJuego());
        Alfil a=new Alfil(color,getJuego());
        Caballo c=new Caballo(color,getJuego());
        ImageIcon icon;
        icon=new ImageIcon(VentanaPrincipal.class.getResource(d.getImagenPath()));
        dama.setIcon(icon);
        icon=new ImageIcon(VentanaPrincipal.class.getResource(t.getImagenPath()));
        torre.setIcon(icon);
        icon=new ImageIcon(VentanaPrincipal.class.getResource(a.getImagenPath()));
        alfil.setIcon(icon);
        icon=new ImageIcon(VentanaPrincipal.class.getResource(c.getImagenPath()));
        caballo.setIcon(icon);
        coronador=cor;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dama = new javax.swing.JButton();
        torre = new javax.swing.JButton();
        alfil = new javax.swing.JButton();
        caballo = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Coronacion");

        jPanel1.setBackground(new java.awt.Color(250, 235, 200));

        dama.setBackground(new java.awt.Color(200, 175, 150));
        dama.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                damaActionPerformed(evt);
            }
        });

        torre.setBackground(new java.awt.Color(150, 125, 100));
        torre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                torreActionPerformed(evt);
            }
        });

        alfil.setBackground(new java.awt.Color(150, 125, 100));
        alfil.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alfilActionPerformed(evt);
            }
        });

        caballo.setBackground(new java.awt.Color(200, 175, 150));
        caballo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                caballoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(dama, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(torre, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(alfil, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(caballo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(torre, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(dama, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(alfil, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(caballo, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void damaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_damaActionPerformed
        getCoronador().setRespuesta(Coronador.Respuestas.DAMA);
        this.dispose();
}//GEN-LAST:event_damaActionPerformed

    private void torreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_torreActionPerformed
        getCoronador().setRespuesta(Coronador.Respuestas.TORRE);
        this.dispose();
    }//GEN-LAST:event_torreActionPerformed

    private void alfilActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alfilActionPerformed
        getCoronador().setRespuesta(Coronador.Respuestas.ALFIL);
        this.dispose();
    }//GEN-LAST:event_alfilActionPerformed

    private void caballoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_caballoActionPerformed
        getCoronador().setRespuesta(Coronador.Respuestas.CABALLO);
        this.dispose();
    }//GEN-LAST:event_caballoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton alfil;
    private javax.swing.JButton caballo;
    private javax.swing.JButton dama;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton torre;
    // End of variables declaration//GEN-END:variables
}