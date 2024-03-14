/*
 *  This program developed in Java is based on the netbeans platform and is used
 *  to design and to analyse composite structures by means of analytical and 
 *  numerical methods.
 * 
 *  Further information can be found here:
 *  http://www.elamx.de
 *    
 *  Copyright (C) 2021 Technische Universität Dresden - Andreas Hauffe
 * 
 *  This file is part of eLamX².
 *
 *  eLamX² is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  eLamX² is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with eLamX².  If not, see <http://www.gnu.org/licenses/>.
 */
package de.elamx.clt.plateui.buckling;

import de.elamx.clt.CLT_Laminate;
import de.elamx.clt.plate.Buckling;
import de.elamx.clt.plate.BucklingInput;
import de.elamx.clt.plate.BucklingResult;
import de.elamx.clt.plateui.stiffenerui.StiffenerPanel;
import de.view3d.View3D;
import java.awt.EventQueue;
import org.netbeans.api.progress.ProgressHandle;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.explorer.ExplorerManager;
import org.openide.util.NbBundle;
import org.openide.util.RequestProcessor;

/**
 *
 * @author Andreas Hauffe
 */
public class ControlPanel extends javax.swing.JPanel {

    private final BucklingModuleData data;
    private final View3D view3D;

    public ControlPanel() {
        this.data = null;
        this.view3D = null;
        initComponents();
    }

    /**
     * Creates new form ControlPanel
     */
    public ControlPanel(BucklingModuleData data, View3D view3D) {
        this.data = data;
        this.view3D = view3D;
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        inputPanel = new InputPanel(data);
        stiffenerPanel1 = new StiffenerPanel(data != null ? data.getBucklingInput() : null);
        jButton1 = new javax.swing.JButton();
        resultPanel1 = new ResultPanel(data, view3D);
        infoPanel1 = new de.elamx.clt.plateui.buckling.InfoPanel(data);

        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(ControlPanel.class, "ControlPanel.inputPanel.TabConstraints.tabTitle"), inputPanel); // NOI18N
        jTabbedPane1.addTab(org.openide.util.NbBundle.getMessage(ControlPanel.class, "ControlPanel.stiffenerPanel1.TabConstraints.tabTitle"), stiffenerPanel1); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jButton1, org.openide.util.NbBundle.getMessage(ControlPanel.class, "ControlPanel.jButton1.text")); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addComponent(resultPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(infoPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infoPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        jButton1.setEnabled(false);
        final CLT_Laminate laminat = data.getLaminat().getLookup().lookup(CLT_Laminate.class);
        final ProgressHandle ph = ProgressHandle.createSystemHandle(NbBundle.getMessage(ControlPanel.class, "Task.bucklingcalc"), null);
        if (checkInput(data.getBucklingInput(), laminat)) {
            RequestProcessor.getDefault().post(new Runnable() {
                @Override
                public void run() {
                    ph.start();
                    final BucklingResult result = Buckling.calc(laminat, data.getBucklingInput());
                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            data.setResult(result);
                            jButton1.setEnabled(true);
                            ph.finish();
                        }
                    });
                }
            });
        } else {
            jButton1.setEnabled(true);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.elamx.clt.plateui.buckling.InfoPanel infoPanel1;
    private de.elamx.clt.plateui.buckling.InputPanel inputPanel;
    private javax.swing.JButton jButton1;
    private javax.swing.JTabbedPane jTabbedPane1;
    private de.elamx.clt.plateui.buckling.ResultPanel resultPanel1;
    private de.elamx.clt.plateui.stiffenerui.StiffenerPanel stiffenerPanel1;
    // End of variables declaration//GEN-END:variables
    public void cleanup() {
        resultPanel1.cleanup();
    }

    public ExplorerManager getLocalExplorerManager() {
        return stiffenerPanel1.getExplorerManager();
    }

    private boolean checkInput(BucklingInput input, CLT_Laminate laminat) {
        if (laminat.getCLTLayers().length == 0) {
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(NbBundle.getMessage(ControlPanel.class, "Warning.nolaminate"), NotifyDescriptor.ERROR_MESSAGE));
            return false;
        }
        if (input.getBcx() == 3 && input.getBcy() == 3){
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(NbBundle.getMessage(ControlPanel.class, "Warning.ffff-boundaryconditions"), NotifyDescriptor.ERROR_MESSAGE));
            return false;
        }
        if (!laminat.isSymmetric()) {
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(NbBundle.getMessage(ControlPanel.class, "Warning.unsymmetriclaminate"), NotifyDescriptor.WARNING_MESSAGE));
        }
        int m = input.getM();
        double ratio = input.getLength() / input.getWidth();
        if (ratio < 1 / (double) m || m < ratio) {
            DialogDisplayer.getDefault().notify(new NotifyDescriptor.Message(NbBundle.getMessage(ControlPanel.class, "Warning.aspectratio"), NotifyDescriptor.WARNING_MESSAGE));
        }
        return true;
    }
}
