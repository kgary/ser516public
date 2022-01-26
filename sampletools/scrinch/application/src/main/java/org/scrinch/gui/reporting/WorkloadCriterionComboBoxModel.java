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

package org.scrinch.gui.reporting;

import java.util.List;
import java.util.Vector;
import javax.swing.ComboBoxModel;
import javax.swing.event.ListDataListener;

public class WorkloadCriterionComboBoxModel implements ComboBoxModel {

    private WorkloadDistributionPanel.Criterion[] criterionList;
    private WorkloadDistributionPanel.Criterion selectedItem;
    private List<ListDataListener> listDataListenerList = new Vector();

    public WorkloadCriterionComboBoxModel() {
        this(false);
    }

    public WorkloadCriterionComboBoxModel(boolean forProject) {
        if(forProject){
            criterionList = WorkloadDistributionPanel.Criterion.getCriteriaForProject();
        } else {
            criterionList = WorkloadDistributionPanel.Criterion.values();
        }
    }

    public void setSelectedItem(Object object) {
        try {
            this.selectedItem = (WorkloadDistributionPanel.Criterion)object;
        } catch(Exception e){
            this.selectedItem = null;
        }
    }

    public Object getElementAt(int i) {
        return criterionList[i];
    }

    public void removeListDataListener(ListDataListener listDataListener) {
        listDataListenerList.remove(listDataListener);
    }

    public void addListDataListener(ListDataListener listDataListener) {
        listDataListenerList.add(listDataListener);
    }

    public int getSize() {
        return this.criterionList.length;
    }

    public Object getSelectedItem() {
        return selectedItem;
    }
}