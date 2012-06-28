package ventanas;

import ajechess.juegos.Juego;
import java.awt.Color;

public class Configuracion extends javax.swing.JFrame {

    public Configuracion() {
        initComponents();
        textNombre1.requestFocus();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        textNombre2 = new javax.swing.JTextField();
        labelNombre2 = new javax.swing.JLabel();
        textNombre1 = new javax.swing.JTextField();
        labelNombre1 = new javax.swing.JLabel();
        labelTiempo = new javax.swing.JLabel();
        textTiempo = new javax.swing.JTextField();
        buttonJugar = new javax.swing.JButton();
        checkRed = new javax.swing.JCheckBox();
        checkServidor = new javax.swing.JCheckBox();
        checkMultijugador = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Nueva Partida");
        setBackground(new java.awt.Color(153, 153, 153));
        setResizable(false);
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(250, 235, 200));

        textNombre2.setBackground(new java.awt.Color(250, 235, 200));
        textNombre2.setForeground(new java.awt.Color(100, 75, 50));
        textNombre2.setEnabled(false);

        labelNombre2.setFont(new java.awt.Font("Verdana", 1, 12));
        labelNombre2.setForeground(new java.awt.Color(200, 175, 150));
        labelNombre2.setText("Jugador 2");

        textNombre1.setBackground(new java.awt.Color(250, 235, 200));
        textNombre1.setForeground(new java.awt.Color(100, 75, 50));

        labelNombre1.setFont(new java.awt.Font("Verdana", 1, 12));
        labelNombre1.setForeground(new java.awt.Color(100, 75, 50));
        labelNombre1.setText("Jugador 1");

        labelTiempo.setFont(new java.awt.Font("Verdana", 1, 12));
        labelTiempo.setForeground(new java.awt.Color(100, 75, 50));
        labelTiempo.setText("Minutos");

        textTiempo.setBackground(new java.awt.Color(250, 235, 200));
        textTiempo.setForeground(new java.awt.Color(100, 75, 50));
        textTiempo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textTiempoActionPerformed(evt);
            }
        });

        buttonJugar.setBackground(new java.awt.Color(250, 235, 200));
        buttonJugar.setFont(new java.awt.Font("Verdana", 1, 12));
        buttonJugar.setForeground(new java.awt.Color(100, 75, 50));
        buttonJugar.setText("JUGAR");
        buttonJugar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonJugarActionPerformed(evt);
            }
        });

        checkRed.setBackground(new java.awt.Color(250, 235, 200));
        checkRed.setFont(new java.awt.Font("Tahoma", 0, 12));
        checkRed.setForeground(new java.awt.Color(100, 75, 50));
        checkRed.setText("Jugar en Red");
        checkRed.setEnabled(false);
        checkRed.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkRedActionPerformed(evt);
            }
        });

        checkServidor.setBackground(new java.awt.Color(250, 235, 200));
        checkServidor.setFont(new java.awt.Font("Tahoma", 0, 12));
        checkServidor.setForeground(new java.awt.Color(100, 75, 50));
        checkServidor.setText("Servidor");
        checkServidor.setEnabled(false);
        checkServidor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkServidorActionPerformed(evt);
            }
        });

        checkMultijugador.setBackground(new java.awt.Color(250, 235, 200));
        checkMultijugador.setForeground(new java.awt.Color(100, 75, 50));
        checkMultijugador.setText("Multijugador");
        checkMultijugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkMultijugadorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(checkServidor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkRed)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(checkMultijugador))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelNombre1)
                            .addComponent(labelNombre2)
                            .addComponent(labelTiempo))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textTiempo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(textNombre2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(textNombre1, javax.swing.GroupLayout.DEFAULT_SIZE, 157, Short.MAX_VALUE)
                            .addComponent(buttonJugar))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(checkServidor)
                    .addComponent(checkRed)
                    .addComponent(checkMultijugador))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNombre1)
                    .addComponent(textNombre1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textNombre2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelNombre2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textTiempo, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelTiempo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonJugar)
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

    private void textTiempoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textTiempoActionPerformed

    }//GEN-LAST:event_textTiempoActionPerformed

    private void buttonJugarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonJugarActionPerformed
        if(!checkMultijugador.isSelected()){
            try{
                int min=Integer.parseInt(textTiempo.getText());
                if(min>0){
                    Juego m = new Juego(textNombre1.getText(),min,0);
                    Thread t = new Thread(m);
                    t.start();

                    VentanaPrincipal v = new VentanaPrincipal(m);
                    v.setVisible(true);
                }else{
                    textTiempo.setText("");
                    textTiempo.requestFocus();
                }
            }catch(Exception ex){
                textTiempo.setText("");
                textTiempo.requestFocus();
            }
        }else{
            if(!checkRed.isSelected()){
                try{
                    int min=Integer.parseInt(textTiempo.getText());
                    if(min>0){
                        Juego m = new Juego(textNombre1.getText(),textNombre2.getText(),min);
                        Thread t = new Thread(m);
                        t.start();

                        VentanaPrincipal v = new VentanaPrincipal(m);
                        v.setVisible(true);
                    }else{
                        textTiempo.setText("");
                        textTiempo.requestFocus();
                    }
                }catch(Exception ex){
                    textTiempo.setText("");
                    textTiempo.requestFocus();
                }
            }else if(!checkServidor.isSelected()){
                Juego m=new Juego(textNombre1.getText(), textTiempo.getText());
                Thread t = new Thread(m);
                t.start();

                VentanaPrincipal v = new VentanaPrincipal(m);
                v.setVisible(true);
            }else{
                try{
                    int min=Integer.parseInt(textTiempo.getText());
                    if(min>0){
                        Juego m = new Juego(textNombre1.getText(),min);
                        Thread t = new Thread(m);
                        t.start();

                        VentanaPrincipal v = new VentanaPrincipal(m);
                        v.setVisible(true);
                    }else{
                        textTiempo.setText("");
                        textTiempo.requestFocus();
                    }
                }catch(Exception ex){
                    textTiempo.setText("");
                    textTiempo.requestFocus();
                }
            }
        }
    }//GEN-LAST:event_buttonJugarActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
    }//GEN-LAST:event_formKeyPressed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
    }//GEN-LAST:event_formKeyTyped

    private void checkRedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkRedActionPerformed
        if(checkRed.isSelected()){
            checkServidor.setEnabled(true);
            labelNombre2.setForeground(new Color(200,175,150));
            labelTiempo.setText("IP");
            textNombre2.setText(null);
            textNombre2.setEnabled(false);
            textTiempo.setText(null);
        }else{
            checkServidor.setEnabled(false);
            checkServidor.setSelected(false);
            labelNombre2.setForeground(new Color(100,75,50));
            labelTiempo.setText("Minutos");
            textNombre2.setText(null);
            textNombre2.setEnabled(true);
            textTiempo.setText(null);
        }
        textNombre1.requestFocus();
    }//GEN-LAST:event_checkRedActionPerformed

    private void checkServidorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkServidorActionPerformed
        if(checkServidor.isSelected()){
            labelTiempo.setText("Minutos");
            textTiempo.setText(null);
        }else{
            labelNombre2.setForeground(new Color(100,75,50));
            labelTiempo.setText("IP");
            textNombre2.setText(null);
            textNombre2.setEnabled(false);
            textTiempo.setText(null);
        }
        textNombre1.requestFocus();
    }//GEN-LAST:event_checkServidorActionPerformed

    private void checkMultijugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkMultijugadorActionPerformed
        if(checkMultijugador.isSelected()){
            checkRed.setEnabled(true);
            labelNombre2.setForeground(new Color(100,75,50));
            textNombre2.setText(null);
            textNombre2.setEnabled(true);
        }else{
            checkRed.setEnabled(false);
            checkRed.setSelected(false);
            checkServidor.setEnabled(false);
            checkServidor.setSelected(false);
            labelNombre2.setForeground(new Color(200,175,150));
            labelTiempo.setText("Minutos");
            textNombre2.setText(null);
            textNombre2.setEnabled(false);
            textTiempo.setText(null);
        }
        textNombre1.requestFocus();
    }//GEN-LAST:event_checkMultijugadorActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonJugar;
    private javax.swing.JCheckBox checkMultijugador;
    private javax.swing.JCheckBox checkRed;
    private javax.swing.JCheckBox checkServidor;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelNombre1;
    private javax.swing.JLabel labelNombre2;
    private javax.swing.JLabel labelTiempo;
    private javax.swing.JTextField textNombre1;
    private javax.swing.JTextField textNombre2;
    private javax.swing.JTextField textTiempo;
    // End of variables declaration//GEN-END:variables
}
