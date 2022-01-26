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

import org.scrinch.gui.model.SprintComboBoxModel;
import java.util.List;
import org.scrinch.model.Item;
import org.scrinch.model.ScrinchEnvToolkit;
import org.scrinch.model.ScrinchException;
import org.scrinch.model.Sprint;

public class SprintChooserDialog extends javax.swing.JDialog {

    private ItemTablePanel tablePanel;
    private List<Item> selectedItems;
    
    public SprintChooserDialog(java.awt.Frame parent, ItemTablePanel panel, ScrinchEnvToolkit toolkit) {
        super(parent, true);
        initComponents();
        sprintComboBox.setModel(new SprintComboBoxModel(toolkit));
        ScrinchGuiToolkit.centerFrame(this);
        this.tablePanel = panel;
        this.selectedItems = panel.getSelectedItems();
        this.lbItems.setText(idList(this.selectedItems));
    }

    private String idList(List<Item> itemList){
        StringBuffer sb = new StringBuffer();
        for(Item item : itemList){
            sb.append('#').append(item.getKey()).append(", ");
        }
        if(itemList.size()>0){
            sb.delete(sb.length()-2, sb.length()-1);
        }
        return sb.toString();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        lbToSprint = new javax.swing.JLabel();
        sprintComboBox = new javax.swing.JComboBox();
        validateButton = new javax.swing.JButton();
        lbAffect = new javax.swing.JLabel();
        lbItems = new javax.swing.JLabel();
        cancelButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridBagLayout());

        lbToSprint.setText("To sprint :");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(lbToSprint, gridBagConstraints);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(sprintComboBox, gridBagConstraints);

        validateButton.setText("Ok");
        validateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                validateButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(validateButton, gridBagConstraints);

        lbAffect.setText(" Affect: ");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        getContentPane().add(lbAffect, gridBagConstraints);

        lbItems.setText("##NO ITEM##");
        getContentPane().add(lbItems, new java.awt.GridBagConstraints());

        cancelButton.setText("Cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.insets = new java.awt.Insets(5, 5, 5, 5);
        getContentPane().add(cancelButton, gridBagConstraints);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        this.dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void validateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_validateButtonActionPerformed
        affectSelectedItemsToSprint();
    }//GEN-LAST:event_validateButtonActionPerformed

    private void affectSelectedItemsToSprint(){
        try{
            Sprint selectedSprint = (Sprint)sprintComboBox.getSelectedItem();
            if( selectedSprint!=null ){
                selectedSprint.addItems(this.selectedItems);

                // next, if items were moved from a sprint in the future, items are removed from it
                // NB : it doesn't make any sence to DELAY a task in future sprints, as they are not yet
                // fully set
                if(tablePanel instanceof SprintPanel){
                    Sprint sprintWhereSelectedItemsComeFrom = (Sprint)((SprintPanel)tablePanel).getItemSet();
                    if(!sprintWhereSelectedItemsComeFrom.isRunning() && sprintWhereSelectedItemsComeFrom.isTopical()){
                        for(Item itemThatHasBeenMoved : this.selectedItems){
                            sprintWhereSelectedItemsComeFrom.removeItem(itemThatHasBeenMoved, true);
                        }
                    }
                }
            }else{
                return;
            }
        } catch (ScrinchException se){
        }
        tablePanel.unselectAll();
        tablePanel.refreshTable();
        this.dispose();
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel lbAffect;
    private javax.swing.JLabel lbItems;
    private javax.swing.JLabel lbToSprint;
    private javax.swing.JComboBox sprintComboBox;
    private javax.swing.JButton validateButton;
    // End of variables declaration//GEN-END:variables

}