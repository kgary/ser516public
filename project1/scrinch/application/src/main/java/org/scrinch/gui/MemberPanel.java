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

import java.util.Collection;
import javax.swing.JOptionPane;
import org.scrinch.model.Member;
import org.scrinch.model.MemberFactory;
import org.scrinch.model.Item;
import org.scrinch.model.ItemToolkit;
import org.scrinch.model.ScrinchEnvToolkit;

public class MemberPanel extends javax.swing.JPanel {

    public interface Listener{
        void removed();
        void changed();
    }

    private Member member;

    private Listener listener;

    private final ScrinchEnvToolkit toolkit;

    public MemberPanel(ScrinchEnvToolkit toolkit) {
        initComponents();
        this.toolkit = toolkit;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member, Listener listener) {
        this.member = member;
        resetFields();
        this.listener = listener;
    }

    private void resetFields(){
        cbActive.setSelected(member.isActive());
        nickNameTextField.setText(member.getNickname());
        fullNameTextField.setText(member.getFullName());
        mobilePhoneTextField.setText(member.getMobilePhone());
        internalPhoneTextField.setText(member.getInternalPhone());

        nickNameTextField.setEnabled(member.isActive());
        fullNameTextField.setEnabled(member.isActive());
        mobilePhoneTextField.setEnabled(member.isActive());
        internalPhoneTextField.setEnabled(member.isActive());
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        cbActive = new javax.swing.JCheckBox();
        fullNameTextField = new javax.swing.JTextField();
        nickNameTextField = new javax.swing.JTextField();
        internalPhoneTextField = new javax.swing.JTextField();
        mobilePhoneTextField = new javax.swing.JTextField();
        btRemove = new javax.swing.JButton();

        setMaximumSize(new java.awt.Dimension(2147483647, 24));
        setLayout(new java.awt.GridBagLayout());

        cbActive.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 0, 0));
        cbActive.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbActiveActionPerformed(evt);
            }
        });
        add(cbActive, new java.awt.GridBagConstraints());

        fullNameTextField.setPreferredSize(new java.awt.Dimension(100, 23));
        fullNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                fullNameTextFieldActionPerformed(evt);
            }
        });
        fullNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                fullNameTextFieldFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 2;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(fullNameTextField, gridBagConstraints);

        nickNameTextField.setPreferredSize(new java.awt.Dimension(100, 23));
        nickNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nickNameTextFieldActionPerformed(evt);
            }
        });
        nickNameTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                nickNameTextFieldFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(nickNameTextField, gridBagConstraints);

        internalPhoneTextField.setPreferredSize(new java.awt.Dimension(100, 23));
        internalPhoneTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                internalPhoneTextFieldActionPerformed(evt);
            }
        });
        internalPhoneTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                internalPhoneTextFieldFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 3;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(internalPhoneTextField, gridBagConstraints);

        mobilePhoneTextField.setPreferredSize(new java.awt.Dimension(100, 23));
        mobilePhoneTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mobilePhoneTextFieldActionPerformed(evt);
            }
        });
        mobilePhoneTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                mobilePhoneTextFieldFocusLost(evt);
            }
        });
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 4;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        gridBagConstraints.weightx = 1.0;
        gridBagConstraints.insets = new java.awt.Insets(2, 2, 2, 2);
        add(mobilePhoneTextField, gridBagConstraints);

        btRemove.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/edit-delete.png"))); // NOI18N
        btRemove.setToolTipText("Remove this member");
        btRemove.setMaximumSize(new java.awt.Dimension(25, 25));
        btRemove.setMinimumSize(new java.awt.Dimension(25, 25));
        btRemove.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRemoveActionPerformed(evt);
            }
        });
        add(btRemove, new java.awt.GridBagConstraints());
    }// </editor-fold>//GEN-END:initComponents

    private void removeMember(){
        toolkit.getMemberFactory().dispose(this.member);
        this.member = null;
        if( this.listener!=null ){
            this.listener.removed();
        }
    }

    private void btRemoveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRemoveActionPerformed
        String nick = this.member.getNickname();
        if( nick!=null ){
            Collection<Item> itemsWithVisas = ItemToolkit.findItemsWithVisaFrom(toolkit.getItemFactory().getItemList(), nick);
            if(itemsWithVisas!=null){
                String cantRemoveTitle = "Items with " + this.member.getNickname() + " Visas found";
                String cantRemoveTextBeginning = "You can't remove this member\n";
                if(this.member.isActive()){
                    if(JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(this,
                                                                              cantRemoveTextBeginning
                                                                              +"Do you want to set " + this.member.getNickname() + " inactive?",
                                                                              cantRemoveTitle,
                                                                              JOptionPane.OK_CANCEL_OPTION)){
                        this.member.setActive(false);
                        resetFields();
                    }
                }else{
                        JOptionPane.showMessageDialog(this,
                                                      cantRemoveTextBeginning,
                                                      cantRemoveTitle,
                                                      JOptionPane.INFORMATION_MESSAGE);
                }
            }else{
                if(JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(this,
                                                                          "Do you really want to remove "
                                                                          + this.member.getNickname() + " ?",
                                                                          "Are you sure?",
                                                                          JOptionPane.OK_CANCEL_OPTION)){
                    removeMember();
                }
            }
        }else{
            removeMember();
        }
    }//GEN-LAST:event_btRemoveActionPerformed

    private void cbActiveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbActiveActionPerformed
        this.member.setActive(cbActive.isSelected());
        resetFields();
        if( this.listener!=null ){
            this.listener.changed();
        }
    }//GEN-LAST:event_cbActiveActionPerformed

    private void fullNameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_fullNameTextFieldFocusLost
        fullNameTextFieldActionPerformed(null);
    }//GEN-LAST:event_fullNameTextFieldFocusLost

    private void fullNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_fullNameTextFieldActionPerformed
        member.setFullName(fullNameTextField.getText());
    }//GEN-LAST:event_fullNameTextFieldActionPerformed

    private void mobilePhoneTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_mobilePhoneTextFieldFocusLost
        mobilePhoneTextFieldActionPerformed(null);
    }//GEN-LAST:event_mobilePhoneTextFieldFocusLost

    private void mobilePhoneTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mobilePhoneTextFieldActionPerformed
        member.setMobilePhone(mobilePhoneTextField.getText());
    }//GEN-LAST:event_mobilePhoneTextFieldActionPerformed

    private void internalPhoneTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_internalPhoneTextFieldFocusLost
        internalPhoneTextFieldActionPerformed(null);
    }//GEN-LAST:event_internalPhoneTextFieldFocusLost

    private void internalPhoneTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_internalPhoneTextFieldActionPerformed
        member.setInternalPhone(internalPhoneTextField.getText());
    }//GEN-LAST:event_internalPhoneTextFieldActionPerformed

    private void nickNameTextFieldFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nickNameTextFieldFocusLost
        nickNameTextFieldActionPerformed(null);
    }//GEN-LAST:event_nickNameTextFieldFocusLost

    private void nickNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nickNameTextFieldActionPerformed
        if( !nickNameTextField.getText().equals(member.getNickname()) ){
            member.setNickname(nickNameTextField.getText());
            if( this.listener!=null ){
                this.listener.changed();
            }
        }
    }//GEN-LAST:event_nickNameTextFieldActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btRemove;
    private javax.swing.JCheckBox cbActive;
    private javax.swing.JTextField fullNameTextField;
    private javax.swing.JTextField internalPhoneTextField;
    private javax.swing.JTextField mobilePhoneTextField;
    private javax.swing.JTextField nickNameTextField;
    // End of variables declaration//GEN-END:variables

}
