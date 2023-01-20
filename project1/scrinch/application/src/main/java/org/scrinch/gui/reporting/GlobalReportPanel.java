/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * GlobalReportPanel.java
 *
 * Created on 8 août 2011, 13:33:30
 */

package org.scrinch.gui.reporting;

import com.lowagie.text.pdf.PdfContentByte;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JDialog;
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.pdf.PDFExport;

/**
 *
 * @author christian
 */
public class GlobalReportPanel extends javax.swing.JPanel {

    private final ScrinchEnvToolkit toolkit;

    public GlobalReportPanel() {
        this(new ScrinchEnvToolkit());
    }

    public GlobalReportPanel(ScrinchEnvToolkit toolkit) {
        this.toolkit = toolkit;
        initComponents();
        Dimension size = new Dimension(1024,800);
        this.setPreferredSize(size);
        this.setSize(size);
        toolkit.addListener(new ScrinchEnvToolkit.Listener() {
            public void preferencesChanged() {
            }
            public void dataLoaded() {
                resetReport();
            }

            public void dataChanged() {
            }
        });
        resetReport();
    }

    private void resetReport(){
        workloadDistributionPanel.initComponent(WorkloadDistributionPanel.Criterion.BY_PROJECT, null);
        workloadDistributionPanel.setAggregator(workloadDistributionPanel.WORK_TO_BE_DONE_AGGREGATOR);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        workloadEvolutionPanel = new WorkloadEvolutionPanel(toolkit);
        workloadDistributionPanel = new WorkloadDistributionPanel(toolkit);
        optionsPanel = new javax.swing.JPanel();
        btExport = new javax.swing.JButton();

        setOpaque(false);
        setLayout(new java.awt.BorderLayout());

        workloadEvolutionPanel.setOpaque(false);
        add(workloadEvolutionPanel, java.awt.BorderLayout.NORTH);

        workloadDistributionPanel.setOpaque(false);
        add(workloadDistributionPanel, java.awt.BorderLayout.CENTER);

        optionsPanel.setOpaque(false);

        btExport.setText("Export to PDF");
        btExport.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btExportActionPerformed(evt);
            }
        });
        optionsPanel.add(btExport);

        add(optionsPanel, java.awt.BorderLayout.SOUTH);
    }// </editor-fold>//GEN-END:initComponents

    private void btExportActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btExportActionPerformed
        String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        PDFExport.exportToPDF("global_report_"+date+".pdf", this, new PDFExport() {
            public void draw(PdfContentByte cb, Graphics2D g2, Rectangle2D rectangle){
                int width = (int)rectangle.getWidth();
                int height = (int)rectangle.getHeight();

                Rectangle2D workEvoR2d = new Rectangle2D.Double(0, 0, width, height/2-1);
                Rectangle2D workDistriR2d = new Rectangle2D.Double(0, height/2+1, width, height/2-1);

                workloadEvolutionPanel.draw(g2, workEvoR2d);
                System.err.println("-------------------------------------------------------------");
                workloadDistributionPanel.draw(g2, workDistriR2d);
            }
        });
}//GEN-LAST:event_btExportActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btExport;
    private javax.swing.JPanel optionsPanel;
    private org.scrinch.gui.reporting.WorkloadDistributionPanel workloadDistributionPanel;
    private org.scrinch.gui.reporting.WorkloadEvolutionPanel workloadEvolutionPanel;
    // End of variables declaration//GEN-END:variables

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JDialog dialog = new JDialog(new javax.swing.JFrame(), true);
                GlobalReportPanel panel = new GlobalReportPanel(new ScrinchEnvToolkit());
                dialog.getContentPane().setLayout(new BorderLayout());
                dialog.getContentPane().add(panel);
                dialog.pack();
                dialog.setVisible(true);
            }
        });
    }

}
