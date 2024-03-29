/*
 Scrinch is a stand-alone Swing application that helps managing your
 projects based on Agile principles.
 Copyright (C) 2007  Julien Piaser, Jerome Layat, Peter Fluekiger,
 Christian Lebaudy, Jean-Marc Borer

 Scrinch is free software: you can redistribute it and/or modify
 it under the terms of the GNU General Public License as published by
 the Free Software Foundation, either version 3 of the License, or
 (at your option) any later version.

 Scrinch is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU General Public License for more details.

 You should have received a copy of the GNU General Public License
 along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.scrinch.gui;

import java.util.Date;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.model.Target;
import org.scrinch.model.ScrinchException;
import org.scrinch.model.TargetFactory;

public class TargetPanel extends javax.swing.JPanel {

    public interface Listener{
        void removed();
        void changed();
    }

    private Target target;

    private Listener listener;
    private final ScrinchEnvToolkit toolkit;


    public TargetPanel(ScrinchEnvToolkit toolkit) {
        initComponents();
        this.toolkit = toolkit;
    }

    public Target getTarget() {
        return target;
    }

    public void setTarget(Target target, Listener listener) {
        this.target = target;
        resetFields();
        this.listener = listener;
    }

    private void resetFields(){
        cbActive.setSelected(target.isActive());
        labelTextField.setText(target.getLabel());
        descriptionTextField.setText(target.getDescription());
        deadlineDatePanel.setText(ScrinchGuiToolkit.getDefaultDayFormat().format(target.getDeadline()));

        labelTextField.setEnabled(target.isActive());
        descriptionTextField.setEnabled(target.isActive());
        deadlineDatePanel.setEnabled(target.isActive());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        jPanel3 = new javax.swing.JPanel();
        cbActive = new javax.swing.JCheckBox();
        labelTextField = new javax.swing.JTextField();
        descriptionTextField = new javax.swing.JTextField();
        deadlineDatePanel = new org.scrinch.gui.DatePanel();
        btRemove = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(2147483647, 24));
        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));

        jPanel3.setLayout(new java.awt.GridBagLayout());

        cbActive.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbActive.setMargin(new java.awt.Insets(0, 0, 0, 0));
        cbActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbActiveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel3.add(cbActive, gridBagConstraints);

        labelTextField.setPreferredSize(new java.awt.Dimension(50, 23));
        labelTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                labelTextFieldActionPerformed(evt);
            }
        });
        labelTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                labelTextFieldFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 0.5;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel3.add(labelTextField, gridBagConstraints);

        descriptionTextField.setPreferredSize(new java.awt.Dimension(300, 23));
        descriptionTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descriptionTextFieldActionPerformed(evt);
            }
        });
        descriptionTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                descriptionTextFieldFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel3.add(descriptionTextField, gridBagConstraints);

        deadlineDatePanel.setMaximumSize(new java.awt.Dimension(130, 22));
        deadlineDatePanel.setMinimumSize(new java.awt.Dimension(130, 22));
        deadlineDatePanel.setPreferredSize(new java.awt.Dimension(130, 22));
        deadlineDatePanel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deadlineDatePanelActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel3.add(deadlineDatePanel, gridBagConstraints);

        btRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/edit-delete.png"))); // NOI18N
        btRemove.setToolTipText("Remove this member");
        btRemove.setMaximumSize(new java.awt.Dimension(25, 25));
        btRemove.setMinimumSize(new java.awt.Dimension(25, 25));
        btRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.BOTH;
        gridBagConstraints.insets = new java.awt.Insets(0, 2, 0, 0);
        jPanel3.add(btRemove, gridBagConstraints);

        add(jPanel3);
    }// </editor-fold>//GEN-END:initComponents

    private void deadlineDatePanelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deadlineDatePanelActionPerformed
        try{
            Date deadLine = ScrinchGuiToolkit.getDefaultDayFormat().parse(deadlineDatePanel.getText());
            if( deadLine!=null && target!=null && !deadLine.equals(target.getDeadline()) ){
                target.setDeadline(deadLine);
                if( this.listener!=null ){
                    this.listener.changed();
                }
            }
        }catch(Exception e){
            ScrinchEnvToolkit.logger.log(Level.SEVERE, "Deadline value is wrong",e);
        }
}//GEN-LAST:event_deadlineDatePanelActionPerformed

    private void removeTarget(){
        try{
            toolkit.getTargetFactory().dispose(this.target);
            this.target = null;
            this.listener.removed();
        } catch(ScrinchException e) {
             JOptionPane.showMessageDialog(this,
                  e.getMessage(),
                  "Error removing a target",
                  JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void btRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveActionPerformed
        if(JOptionPane.OK_OPTION ==
            JOptionPane.showConfirmDialog(this,
                "Do you really want to remove "
                + this.target.getLabel() + " ?",
                "Are you sure?",
                JOptionPane.OK_CANCEL_OPTION)){
            removeTarget();
        }

    }//GEN-LAST:event_btRemoveActionPerformed

    private void cbActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbActiveActionPerformed
        this.target.setActive(cbActive.isSelected());
        resetFields();
        if( this.listener!=null ){
            this.listener.changed();
        }
    }//GEN-LAST:event_cbActiveActionPerformed

    private void descriptionTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descriptionTextFieldActionPerformed
        target.setDescription(descriptionTextField.getText());
    }//GEN-LAST:event_descriptionTextFieldActionPerformed

    private void labelTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_labelTextFieldActionPerformed
        if( !labelTextField.getText().equals(target.getLabel()) ){
            target.setLabel(labelTextField.getText());
            if( this.listener!=null ){
                this.listener.changed();
            }
        }
    }//GEN-LAST:event_labelTextFieldActionPerformed

    private void labelTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_labelTextFieldFocusLost
        labelTextFieldActionPerformed(null);
    }//GEN-LAST:event_labelTextFieldFocusLost

    private void descriptionTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_descriptionTextFieldFocusLost
        descriptionTextFieldActionPerformed(null);
    }//GEN-LAST:event_descriptionTextFieldFocusLost

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btRemove;
    private javax.swing.JCheckBox cbActive;
    private org.scrinch.gui.DatePanel deadlineDatePanel;
    private javax.swing.JTextField descriptionTextField;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField labelTextField;
    // End of variables declaration//GEN-END:variables
}
