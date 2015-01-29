
package pruebas.arduinoToJava;

//librerias para comunicacion Java-Arduino
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import panamahitek.Arduino.PanamaHitek_Arduino;


//Librerias para la salida en un textArea de un jFrame (y no por consola)
import java.io.PrintStream;

/*
 * Basado en: https://www.youtube.com/watch?v=3QgY5JjQxuI&feature=youtu.be
 * de Panama Hitek
 * Usa las librerias RXTX y panamahitek.Arduino (desarrollado por Panama Hitek)
 */


public class Ventana extends javax.swing.JFrame {

    //variables de la clase Ventana
    private PrintStream standardOut; //variable para hacer la salida en textArea
    PanamaHitek_Arduino arduino=new PanamaHitek_Arduino();
    SerialPortEventListener evento=new SerialPortEventListener() {

        @Override
        public void serialEvent(SerialPortEvent spe) {
            /*Este metodo abstracto que hay que implementar se ejecutra cada vez que arduino le envia datos
             * a java a traves del puerto serie
             */
            if(arduino.isMessageAvailable()==true){
                /*El mensaje de arduino a java se envia caracter por caracter y java los recibe en formato ascii
                * isMessageAvailable() pregunta si java ha terminado de recibir un mensaje (cadena de caracteres) de arduino
                */
                
                //OPCION 1
                System.out.println(arduino.printMessage()); //imprime el mensaje recibido completo y en una sola linea, como un String
                               
                try {
                    //OPCION 2. imprime el mensaje recibido completo, de a una linea, pero en formato ascii
                    //System.out.println(arduino.receiveData()); 
                    
                    //OPCION 3. imprime el mensaje recibido completo, de a una linea, pero parseando de ascii a char
                    //System.out.println((char)arduino.receiveData());
                } catch (Exception ex) {
                    Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
                
            }
            
             
        }
    };
    
    public Ventana() {  //constructor de la clase Ventana
        initComponents();
        
        //agregado por dario
        
        //Codigo para hacer la salida por textArea
        PrintStream printStream = new PrintStream(new CustomOutputStream(jTAresultados));
        // keeps reference of standard output stream
        standardOut = System.out;
        // re-assigns standard output stream and error output stream
	System.setOut(printStream);
	System.setErr(printStream);
        // -------------------------------------------------------------------------------------
        
        
        
        try { 
            arduino.arduinoRXTX("COM10", 9600, evento);
        } catch (Exception ex) {
            Logger.getLogger(Ventana.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTAresultados = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTAresultados.setColumns(20);
        jTAresultados.setRows(5);
        jScrollPane1.setViewportView(jTAresultados);

        jLabel1.setText("Mensaje recibido desde Arduino Uno:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /*
         * Set the Nimbus look and feel
         */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the
         * default look and feel. For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /*
         * Create and display the form
         */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTAresultados;
    // End of variables declaration//GEN-END:variables
}
