/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * form.java
 *
 * Created on 20/11/2012, 03:14:32 PM
 */
package info_system_windows;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTextArea;
import org.hyperic.sigar.CpuInfo;
import org.hyperic.sigar.CpuPerc;
import org.hyperic.sigar.Mem;
import org.hyperic.sigar.OperatingSystem;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.hyperic.sigar.Swap;

/**
 *
 * @author Ab
 */
public class Informacion extends javax.swing.JFrame {

    /** Creates new form form */
    public Informacion() {
        try {
            initComponents();
            imprimirInfoCPU();
            imprimirInfoRAM();
            informaciongeneralwindows();
            imprimirtiempo();
        } catch (SigarException ex) {
            Logger.getLogger(Informacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private Sigar sigar = new Sigar();
    
    private void informaciongeneralwindows(){
    OperatingSystem sys = OperatingSystem.getInstance();
    jTextAreaInfoGeneral.append("Descripcion del SO:\t" + sys.getDescription()+"\n");
    jTextAreaInfoGeneral.append("Nombre del SO:\t" + sys.getName()+"\n");
    jTextAreaInfoGeneral.append("Arquitectura del SO:\t" + sys.getArch()+"\n");
    jTextAreaInfoGeneral.append("Version del SO:\t\t" + sys.getVersion()+"\n");
    jTextAreaInfoGeneral.append("Fabricante:\t\t" + sys.getVendor()+"\n");
    jTextAreaInfoGeneral.append("Version SO:\t\t" + sys.getVendorVersion()+"\n\n");
    
    }
    
    public void imprimirtiempo() throws SigarException {
        double uptime = sigar.getUptime().getUptime();
        String resultado = "";
        int dias = (int) uptime / (60 * 60 * 24);
        int minutos, horas;
        if (dias != 0)
            resultado += dias + " " + ((dias > 1) ? "dias" : "dia") + ", ";
        minutos = (int) uptime / 60;
        horas = minutos / 60;
        horas %= 24;
        minutos %= 60;
        if (horas != 0)
            resultado += horas + ":" + (minutos < 10 ? "0" + minutos : minutos)+ " horas";
        else
            resultado += minutos + " min";
        jTextAreaInfoGeneral.append("Encendido durante:\t" + resultado);
    } 
 
    public void imprimirInfoCPU() {
        sigar = new Sigar();
        CpuInfo[] infos = null;
        CpuPerc[] cpus = null;
        try {
            infos = sigar.getCpuInfoList();
            cpus = sigar.getCpuPercList();
        } catch (SigarException e) {
         e.printStackTrace();
    }
 
        CpuInfo info = infos[0];
        //long tamañoCache = info.getCacheSize();
        jTextAreaInfoCPU.append("Fabricante:\t\t" + info.getVendor()+"\n");
        jTextAreaInfoCPU.append("Modelo\t\t" + info.getModel()+"\n");
        jTextAreaInfoCPU.append("Mhz\t\t" + info.getMhz()+"\n");
        jTextAreaInfoCPU.append("Total CPUs\t\t" + info.getTotalCores()+ "  "+ info.getModel()+"\n");
        jTextAreaInfoCPU.append("CPUs fisiscas\t\t" + info.getTotalSockets()+"\n");
        jTextAreaInfoCPU.append("Nucleos por CPU\t" + info.getCoresPerSocket()+ "\n");
       /* if ((info.getTotalCores() != info.getTotalSockets())
                || (info.getCoresPerSocket() > info.getTotalCores())) {
            jTextAreaInfoCPU.append("CPUs físicas\t\t" + info.getTotalSockets());
            jTextAreaInfoCPU.append("Nucleos por CPU\t\t" + info.getCoresPerSocket());
        }*/
 
        /*if (tamanioCache != Sigar.FIELD_NOTIMPL)
            jTextAreaInfoCPU.append("Tamanio cache\t\t" + tamanioCache +"\n");
        jTextAreaInfoCPU.append(""+"\n");*/
 
        for (int i = 0; i < cpus.length; i++)         
            jTextAreaInfoCPU.append("Consumo de CPU " + "  " + i + "\t"+ CpuPerc.format(cpus[i].getUser())+ "\n");
        try {
          jTextAreaInfoCPU.append("Consumo total del CPU:\t"+ CpuPerc.format(sigar.getCpuPerc().getUser()));  
       } catch (SigarException ex) {
            Logger.getLogger(Informacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void imprimirInfoRAM() throws SigarException {
        Mem memoria = sigar.getMem();
        Swap intercambio = sigar.getSwap();
        
        jTextAreaInfoRAM.append("Cantidad de memoria RAM:\t"+ memoria.getRam() + " MB"+"\n");
        //jTextAreaInfoRAM.append("Total:\t\t "+enBytes(memoria.getTotal())+" MB"+"\n");
        jTextAreaInfoRAM.append("Usada:\t\t"+enBytes(memoria.getUsed())+" MB"+"\n");
        jTextAreaInfoRAM.append("Disponible:\t\t "+enBytes(memoria.getFree())+" MB"+"\n");
        jTextAreaInfoRAM.append("Memoria SWAP total:\t"+enBytes(intercambio.getTotal())+" MB"+"\n");
        jTextAreaInfoRAM.append("Memoria SWAP usada:\t"+enBytes(intercambio.getUsed())+" MB"+"\n");
        jTextAreaInfoRAM.append("Memoria SWAP libre:\t"+enBytes(intercambio.getFree())+" MB"+"\n");
    }
    private Long enBytes(long valor) {
        return new Long((valor / 1024)/1024);
    }

    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextAreaInfoGeneral = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaInfoCPU = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextAreaInfoRAM = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jTextAreaInfoGeneral.setColumns(20);
        jTextAreaInfoGeneral.setRows(5);
        jScrollPane1.setViewportView(jTextAreaInfoGeneral);

        jLabel1.setFont(new java.awt.Font("Arial", 3, 24)); // NOI18N
        jLabel1.setText("Informacón General:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                    .addComponent(jLabel1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(77, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Información General", jPanel1);

        jTextAreaInfoCPU.setColumns(20);
        jTextAreaInfoCPU.setRows(5);
        jScrollPane2.setViewportView(jTextAreaInfoCPU);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Información del CPU", jPanel2);

        jTextAreaInfoRAM.setColumns(20);
        jTextAreaInfoRAM.setRows(5);
        jScrollPane3.setViewportView(jTextAreaInfoRAM);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 619, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Información de Memoria RAM", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 450, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
 
}//GEN-LAST:event_formWindowActivated

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Informacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Informacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Informacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Informacion.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                new Informacion().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextAreaInfoCPU;
    private javax.swing.JTextArea jTextAreaInfoGeneral;
    private javax.swing.JTextArea jTextAreaInfoRAM;
    // End of variables declaration//GEN-END:variables
}
