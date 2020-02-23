
package codigo;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author xp
 */
public class VentanaPokedex extends javax.swing.JFrame {

    BufferedImage buffer1 = null;
    Image imagen1 = null;
    int contador = 1;
    
    Statement estado;
    ResultSet resultadoConsulta;
    Connection conexion;
    
    //estructura para guardar todo el contenido de la base de datos
    //de golpe
    HashMap<String, Pokemon> listaPokemons = new HashMap();
    
    
    @Override
    public void paint(Graphics g){
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) imagenPokemon.getGraphics();
        g2.drawImage(buffer1, 0, 0,
                imagenPokemon.getWidth(),
                imagenPokemon.getHeight(), null);
    }
    
    /**
     * Creates new form VentanaPokedex
     */
    public VentanaPokedex() {
        initComponents();
        try {
            imagen1 = ImageIO.read(getClass()
                    .getResource("/imagenes/black-white.png"));
        } catch (IOException ex) {
        }
        
        buffer1 = (BufferedImage) imagenPokemon.createImage(
                imagenPokemon.getWidth(),
                imagenPokemon.getHeight());
        
        
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager
                    .getConnection("jdbc:mysql://127.0.0.1/newpokemon",
                            "root",
                            "");
            estado = conexion.createStatement();
            resultadoConsulta = estado.executeQuery("Select * from pokemon");
            //recorremos el array del resultado uno a uno
            //para ir cargándolo en el Hashmap
            
            while (resultadoConsulta.next()){
                Pokemon p = new Pokemon();
                p.nombre = resultadoConsulta.getString("nombre");
                p.especie = resultadoConsulta.getString("especie");
                p.movimiento1 = resultadoConsulta.getString("movimiento1");
                p.peso = resultadoConsulta.getString("peso");
                p.preEvolucion = resultadoConsulta.getInt("preEvolucion");
                p.posEvolucion = resultadoConsulta.getInt("posEvolucion");
                
                //añado el pokemon recien creado al Hashmap
                listaPokemons.put(resultadoConsulta.getString("id"), p);
            }
            
            
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println(e.getMessage());
            System.out.println("hay un error");
        }
        
        dibujaElPokemonQueEstaEnLaPosicion(0);
        
        try {
            resultadoConsulta = estado.executeQuery("select * from pokemon where id= 1");
            if (resultadoConsulta.next()){
                nombre.setText("Nombre: " + resultadoConsulta.getString(2));
                num.setText("Nº en la pokedex: " + resultadoConsulta.getString(1));
                altura.setText("Mide " + resultadoConsulta.getString(3) + " m");
                peso.setText("Pesa " + resultadoConsulta.getString(4) + "Kg");
                tipo.setText("Tipo: " + resultadoConsulta.getString(7) + (!"".equals(resultadoConsulta.getString(8)) ? (" / " + resultadoConsulta.getString(8)) : ""));
                habitat.setText("Habitat: " + resultadoConsulta.getString(6));
                especie.setText("Especie: " + resultadoConsulta.getString(5));
            }else{
                nombre.setText("Este pokemon no figura en la pokedex");
            }
        } catch (SQLException ex) {
                Logger.getLogger(VentanaPokedex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void dibujaElPokemonQueEstaEnLaPosicion(int posicion){
        int fila = posicion / 31;
        int columna = posicion % 31;
        Graphics2D g2 = (Graphics2D) buffer1.getGraphics();
        g2.setColor(Color.decode("#FBFBFB"));
        g2.fillRect(0, 0, //pinta el fondo del jpanel negro
                imagenPokemon.getWidth(),
                imagenPokemon.getHeight()); 
        g2.drawImage(imagen1,
                0,  //posicion X inicial dentro del jpanel 
                0,  // posicion Y inicial dentro del jpanel
                imagenPokemon.getWidth(), //ancho del jpanel
                imagenPokemon.getHeight(), //alto del jpanel
                columna*96, //posicion inicial X dentro de la imagen de todos los pokemon
                fila*96, //posicion inicial Y dentro de la imagen de todos los pokemon
                columna*96 + 96, //posicion final X
                fila*96 + 96, //posicion final Y
                null  //si no lo pones no va
                );
        repaint();
        
    }
    
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        izq = new javax.swing.JButton();
        der = new javax.swing.JButton();
        nombre = new javax.swing.JLabel();
        num = new javax.swing.JLabel();
        altura = new javax.swing.JLabel();
        peso = new javax.swing.JLabel();
        tipo = new javax.swing.JLabel();
        habitat = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        especie = new javax.swing.JLabel();
        imagenPokemon = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        izq.setText("<");
        izq.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izqActionPerformed(evt);
            }
        });

        der.setText(">");
        der.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                derActionPerformed(evt);
            }
        });

        nombre.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        num.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        altura.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        peso.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        tipo.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        habitat.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/Pokedex.png"))); // NOI18N

        especie.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        javax.swing.GroupLayout imagenPokemonLayout = new javax.swing.GroupLayout(imagenPokemon);
        imagenPokemon.setLayout(imagenPokemonLayout);
        imagenPokemonLayout.setHorizontalGroup(
            imagenPokemonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 177, Short.MAX_VALUE)
        );
        imagenPokemonLayout.setVerticalGroup(
            imagenPokemonLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 164, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(127, 127, 127)
                .addComponent(izq, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(der, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(imagenPokemon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 198, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(altura, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(peso, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(habitat, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(especie, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(jLabel2)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(195, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(num, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(altura, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(peso, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(habitat, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(especie, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(imagenPokemon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 167, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(der)
                            .addComponent(izq))
                        .addGap(91, 91, 91))))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

<<<<<<< HEAD
    private void izqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izqActionPerformed
        dibujaElPokemonQueEstaEnLaPosicion(contador);//porque en el png los pokemon empiezan en el 0
        Pokemon p = listaPokemons.get(String.valueOf(contador+1));  //y en la BBDD en el 1
        if (p != null){
            nombrePokemon.setText(p.nombre);
        }
        else{
            nombrePokemon.setText("NO HAY DATOS");
        }
        contador --;
        if (contador <=0){
            contador = 0;
        }

    }//GEN-LAST:event_izqActionPerformed

    private void derActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_derActionPerformed
=======
    private void derActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_derActionPerformed
        contador ++;
        if (contador >= 150){
            contador = 150;
        }
        dibujaElPokemonQueEstaEnLaPosicion(contador);
        try {
            resultadoConsulta = estado.executeQuery("select * from pokemon where id=" + (contador + 1));
            if (resultadoConsulta.next()){
                nombre.setText("Nombre: " + resultadoConsulta.getString(2));
                num.setText("Nº en la pokedex: " + resultadoConsulta.getString(1));
                altura.setText("Mide " + resultadoConsulta.getString(3) + " m");
                peso.setText("Pesa " + resultadoConsulta.getString(4) + "Kg");
                tipo.setText("Tipo: " + resultadoConsulta.getString(7) + (!"".equals(resultadoConsulta.getString(8)) ? (" / " + resultadoConsulta.getString(8)) : ""));
                habitat.setText("Habitat: " + resultadoConsulta.getString(6));
                especie.setText("Especie: " + resultadoConsulta.getString(5));
            }else{
                nombre.setText("Este pokemon no figura en la pokedex");
            }
        } catch (SQLException ex) {
                Logger.getLogger(VentanaPokedex.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_derActionPerformed
>>>>>>> master

    private void izqActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izqActionPerformed
        contador --;
        if (contador < 0){
            contador = 0;
        }
        dibujaElPokemonQueEstaEnLaPosicion(contador);
<<<<<<< HEAD
        Pokemon p = listaPokemons.get(String.valueOf(contador+1));
        if (p != null){
            nombrePokemon.setText(p.nombre);
        }
        else{
            nombrePokemon.setText("NO HAY DATOS");
=======
        try {
            resultadoConsulta = estado.executeQuery("select * from pokemon where id=" + (contador + 1));
            if (resultadoConsulta.next()){
                nombre.setText("Nombre: " + resultadoConsulta.getString(2));
                num.setText("Nº en la pokedex: " + resultadoConsulta.getString(1));
                altura.setText("Mide " + resultadoConsulta.getString(3) + " m");
                peso.setText("Pesa " + resultadoConsulta.getString(4) + "Kg");
                tipo.setText("Tipo: " + resultadoConsulta.getString(7) + (!"".equals(resultadoConsulta.getString(8)) ? (" / " + resultadoConsulta.getString(8)) : ""));
                habitat.setText("Habitat: " + resultadoConsulta.getString(6));
                especie.setText("Especie: " + resultadoConsulta.getString(5));
            }
            else{
                nombre.setText("Este pokemon no figura en la pokedex");
            }
        } catch (SQLException ex) {
            Logger.getLogger(VentanaPokedex.class.getName()).log(Level.SEVERE, null, ex);
>>>>>>> master
        }
    }//GEN-LAST:event_izqActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPokedex.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaPokedex().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel altura;
    private javax.swing.JButton der;
    private javax.swing.JLabel especie;
    private javax.swing.JLabel habitat;
    private javax.swing.JPanel imagenPokemon;
    private javax.swing.JButton izq;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel nombre;
    private javax.swing.JLabel num;
    private javax.swing.JLabel peso;
    private javax.swing.JPanel pre1;
    private javax.swing.JLabel tipo;
    // End of variables declaration//GEN-END:variables
}
