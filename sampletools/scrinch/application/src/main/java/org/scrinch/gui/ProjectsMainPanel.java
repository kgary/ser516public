/*
Scrinch is a stand-alone Swing application that helps managing your
projects based on Agile principles.
Copyright (C) 2007  Julien Piaser, Jerome Layat, Peter Fluckiger,
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

import java.awt.CardLayout;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import org.scrinch.model.Project;
import org.scrinch.model.ScrinchEnvToolkit;

public class ProjectsMainPanel extends javax.swing.JPanel {

    private final ScrinchEnvToolkit toolkit;

    public ProjectsMainPanel() {
        this(new ScrinchEnvToolkit());
    }

    public ProjectsMainPanel(ScrinchEnvToolkit toolkit) {
        initComponents();
        this.toolkit = toolkit;
        toolkit.addListener(new ScrinchEnvToolkit.Listener() {
            public void preferencesChanged() {
                resetProjectPanels();
            }
            public void dataLoaded() {
                resetProjectPanels();
            }

            public void dataChanged() {
            }
        });
        resetProjectPanels();
    }
    
    private boolean projectsLoaded = false;
    
    private synchronized void loadprojectPanels(boolean withOld) {
        if (!projectsLoaded) {
            List<Project> projectList;
            if (withOld) {
                projectList = toolkit.getProjectFactory().getList();
            } else {
                projectList = toolkit.getProjectFactory().findTopicals();
            }
            for (Project pb : projectList) {
                addProjectPanel(pb);
            }
            this.projectsLoaded = true;
        }
    }

    private List<ProjectPanel> projectPanelList = new ArrayList<ProjectPanel>();
    
    private void resetProjectPanels(){
        centerPanel.setVisible(false);
        releaseAllResources();
        loadprojectPanels(toolkit.isOldProjectsAndSprintsVisible());
        centerPanel.setVisible(true);
        ((CardLayout)this.centerPanel.getLayout()).first(this.centerPanel);
    }
    
    protected synchronized void releaseAllResources(){
        for(ProjectPanel projectPanel : projectPanelList){
            projectPanel.releaseAllResources();
        }
        centerPanel.removeAll();
        projectPanelList.clear();
        
        projectsLoaded = false;
    }
    
    private ProjectPanel addProjectPanel(final Project project) {
        final ProjectPanel panel = new ProjectPanel(this, toolkit);
        projectPanelList.add(panel);
        panel.setProject(project);
        centerPanel.add(panel, panel.getProject().getProjectName());
        updateProjectsCombo();
        return panel;
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        projectsTopPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        projectsCombo = new javax.swing.JComboBox();
        jLabel2 = new javax.swing.JLabel();
        addProject = new javax.swing.JButton();
        centerPanel = new javax.swing.JPanel();

        setLayout(new java.awt.BorderLayout());

        jLabel1.setText("Choose a project");
        projectsTopPanel.add(jLabel1);

        projectsCombo.setPreferredSize(new java.awt.Dimension(240, 27));
        projectsCombo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                projectsComboActionPerformed(evt);
            }
        });
        projectsTopPanel.add(projectsCombo);

        jLabel2.setText("or create one");
        projectsTopPanel.add(jLabel2);

        addProject.setIcon(new javax.swing.ImageIcon(getClass().getResource("/org/scrinch/gui/33-cabinet - create.png"))); // NOI18N
        addProject.setToolTipText("Click to create a new project");
        addProject.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addProjectActionPerformed(evt);
            }
        });
        projectsTopPanel.add(addProject);

        add(projectsTopPanel, java.awt.BorderLayout.NORTH);

        centerPanel.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentRemoved(java.awt.event.ContainerEvent evt) {
                centerPanelComponentRemoved(evt);
            }
        });
        centerPanel.setLayout(new java.awt.CardLayout());
        add(centerPanel, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

private void addProjectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addProjectActionPerformed
    Project project = toolkit.getProjectFactory().createProject();
    String projectName = "Project #" + centerPanel.getComponentCount();
    project.setProjectName(projectName);
    ProjectPanel panel = addProjectPanel(project);
    selectProject(projectName);
    if( !panel.isHeaderVisible() ){
        panel.toggleHeaderVisible();
    }
}//GEN-LAST:event_addProjectActionPerformed

private void projectsComboActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_projectsComboActionPerformed
    String name = (String)this.projectsCombo.getSelectedItem();
    CardLayout layout = (CardLayout)this.centerPanel.getLayout();
    layout.show(this.centerPanel, name);
}//GEN-LAST:event_projectsComboActionPerformed

private void centerPanelComponentRemoved(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_centerPanelComponentRemoved
    int index = this.projectsCombo.getSelectedIndex();
    if( index>0 ){
        index = index-1;
    }
    String previousProjectName = (String)this.projectsCombo.getItemAt(index);
    updateProjectsCombo();
    selectProject(previousProjectName);
}//GEN-LAST:event_centerPanelComponentRemoved


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addProject;
    private javax.swing.JPanel centerPanel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox projectsCombo;
    private javax.swing.JPanel projectsTopPanel;
    // End of variables declaration//GEN-END:variables

    private void updateProjectsCombo() {
        Vector<String> names = new Vector<String>();
        for(int i=0;i<this.centerPanel.getComponentCount();i++){
            ProjectPanel pp = (ProjectPanel)this.centerPanel.getComponent(i);
            names.add(pp.getProject().getProjectName());
        }
        this.projectsCombo.setModel(new DefaultComboBoxModel(names));
    }

    protected void updateProject(ProjectPanel panel) {
        this.centerPanel.remove(panel);
        centerPanel.add(panel, panel.getProject().getProjectName());
        updateProjectsCombo();
        this.projectsCombo.setSelectedItem(panel.getProject().getProjectName());
    }

    private void selectProject(String projectName) {
        this.projectsCombo.setSelectedItem(projectName);
    }

}
