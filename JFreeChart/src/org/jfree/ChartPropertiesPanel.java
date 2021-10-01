/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.jfree;

import java.awt.Color;
import javax.swing.JColorChooser;
import org.openide.util.NbBundle;

final class ChartPropertiesPanel extends javax.swing.JPanel {

    private final ChartPropertiesOptionsPanelController controller;

    ChartPropertiesPanel(ChartPropertiesOptionsPanelController controller) {
        this.controller = controller;
        initComponents();
        backgroundColorLabel.setOpaque(true);
        backgroundColorLabel.setBackground((Color)eLamXChartTheme.getInstance().getPlotBackgroundPaint());
        domainGridColorLabel.setOpaque(true);
        domainGridColorLabel.setBackground((Color)eLamXChartTheme.getInstance().getDomainGridlinePaint());
        rangeGridColorLabel.setOpaque(true);
        rangeGridColorLabel.setBackground((Color)eLamXChartTheme.getInstance().getRangeGridlinePaint());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        domainGridColorLabel = new javax.swing.JLabel();
        rangeGridColorLabel = new javax.swing.JLabel();
        backgroundColorLabel = new javax.swing.JLabel();
        backgroundColorSelectButton = new javax.swing.JButton();
        domainGridColorSelectButton = new javax.swing.JButton();
        rangeGridColorSelectButton2 = new javax.swing.JButton();

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(ChartPropertiesPanel.class, "ChartPropertiesPanel.jLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(ChartPropertiesPanel.class, "ChartPropertiesPanel.jLabel2.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(ChartPropertiesPanel.class, "ChartPropertiesPanel.jLabel3.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(domainGridColorLabel, org.openide.util.NbBundle.getMessage(ChartPropertiesPanel.class, "ChartPropertiesPanel.domainGridColorLabel.text")); // NOI18N
        domainGridColorLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        domainGridColorLabel.setMaximumSize(new java.awt.Dimension(27, 22));
        domainGridColorLabel.setMinimumSize(new java.awt.Dimension(27, 22));
        domainGridColorLabel.setPreferredSize(new java.awt.Dimension(27, 22));

        org.openide.awt.Mnemonics.setLocalizedText(rangeGridColorLabel, org.openide.util.NbBundle.getMessage(ChartPropertiesPanel.class, "ChartPropertiesPanel.rangeGridColorLabel.text")); // NOI18N
        rangeGridColorLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        rangeGridColorLabel.setMaximumSize(new java.awt.Dimension(27, 22));
        rangeGridColorLabel.setMinimumSize(new java.awt.Dimension(27, 22));
        rangeGridColorLabel.setPreferredSize(new java.awt.Dimension(27, 22));

        org.openide.awt.Mnemonics.setLocalizedText(backgroundColorLabel, org.openide.util.NbBundle.getMessage(ChartPropertiesPanel.class, "ChartPropertiesPanel.backgroundColorLabel.text")); // NOI18N
        backgroundColorLabel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        backgroundColorLabel.setMaximumSize(new java.awt.Dimension(27, 22));
        backgroundColorLabel.setMinimumSize(new java.awt.Dimension(27, 22));
        backgroundColorLabel.setPreferredSize(new java.awt.Dimension(27, 22));

        org.openide.awt.Mnemonics.setLocalizedText(backgroundColorSelectButton, org.openide.util.NbBundle.getMessage(ChartPropertiesPanel.class, "ChartPropertiesPanel.backgroundColorSelectButton.text")); // NOI18N
        backgroundColorSelectButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        backgroundColorSelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backgroundColorSelectButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(domainGridColorSelectButton, org.openide.util.NbBundle.getMessage(ChartPropertiesPanel.class, "ChartPropertiesPanel.domainGridColorSelectButton.text")); // NOI18N
        domainGridColorSelectButton.setMargin(new java.awt.Insets(2, 2, 2, 2));
        domainGridColorSelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                domainGridColorSelectButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(rangeGridColorSelectButton2, org.openide.util.NbBundle.getMessage(ChartPropertiesPanel.class, "ChartPropertiesPanel.rangeGridColorSelectButton2.text")); // NOI18N
        rangeGridColorSelectButton2.setMargin(new java.awt.Insets(2, 2, 2, 2));
        rangeGridColorSelectButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rangeGridColorSelectButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backgroundColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(backgroundColorSelectButton))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rangeGridColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rangeGridColorSelectButton2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(domainGridColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(domainGridColorSelectButton)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(backgroundColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backgroundColorSelectButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(domainGridColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(domainGridColorSelectButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(rangeGridColorLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rangeGridColorSelectButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backgroundColorSelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backgroundColorSelectButtonActionPerformed
        Color c = JColorChooser.showDialog(this, NbBundle.getMessage(ChartPropertiesPanel.class, "BackgroundPaintChooser.caption"), backgroundColorLabel.getBackground());
        if (c != null) {
            backgroundColorLabel.setOpaque(true);
            backgroundColorLabel.setBackground(c);
            controller.changed();
        }
    }//GEN-LAST:event_backgroundColorSelectButtonActionPerformed

    private void domainGridColorSelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_domainGridColorSelectButtonActionPerformed
        Color c = JColorChooser.showDialog(this, NbBundle.getMessage(ChartPropertiesPanel.class, "DomainGridPaintChooser.caption"), domainGridColorLabel.getBackground());
        if (c != null) {
            domainGridColorLabel.setOpaque(true);
            domainGridColorLabel.setBackground(c);
            controller.changed();
        }
    }//GEN-LAST:event_domainGridColorSelectButtonActionPerformed

    private void rangeGridColorSelectButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rangeGridColorSelectButton2ActionPerformed
        Color c = JColorChooser.showDialog(this, NbBundle.getMessage(ChartPropertiesPanel.class, "RangeGridPaintChooser.caption"), rangeGridColorLabel.getBackground());
        if (c != null) {
            rangeGridColorLabel.setOpaque(true);
            rangeGridColorLabel.setBackground(c);
            controller.changed();
        }
    }//GEN-LAST:event_rangeGridColorSelectButton2ActionPerformed

    void load() {
        backgroundColorLabel.setOpaque(true);
        backgroundColorLabel.setBackground((Color)eLamXChartTheme.getInstance().getPlotBackgroundPaint());
        domainGridColorLabel.setOpaque(true);
        domainGridColorLabel.setBackground((Color)eLamXChartTheme.getInstance().getDomainGridlinePaint());
        rangeGridColorLabel.setOpaque(true);
        rangeGridColorLabel.setBackground((Color)eLamXChartTheme.getInstance().getRangeGridlinePaint());
    }

    void store() {
        eLamXChartTheme.getInstance().setPlotBackgroundPaint(backgroundColorLabel.getBackground());
        eLamXChartTheme.getInstance().setDomainGridlinePaint(domainGridColorLabel.getBackground());
        eLamXChartTheme.getInstance().setRangeGridlinePaint(rangeGridColorLabel.getBackground());
    }

    boolean valid() {
        // TODO check whether form is consistent and complete
        return true;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundColorLabel;
    private javax.swing.JButton backgroundColorSelectButton;
    private javax.swing.JLabel domainGridColorLabel;
    private javax.swing.JButton domainGridColorSelectButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel rangeGridColorLabel;
    private javax.swing.JButton rangeGridColorSelectButton2;
    // End of variables declaration//GEN-END:variables
}