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
package de.elamx.export.Nastran;

import de.elamx.export.Export;
import de.elamx.export.ExportOptions;
import de.elamx.export.ExportOptionsPanel;
import de.elamx.laminate.Laminat;
import org.openide.util.NbPreferences;

/**
 *
 * @author Andreas Hauffe
 */
public class NastranExportOptionsPanel extends ExportOptionsPanel {

    private Laminat laminate;
    
    /**
     * Creates new form NastranExportOptionsPanel
     */
    @SuppressWarnings("this-escape")
    public NastranExportOptionsPanel() {
        initComponents();
        hygrothermalCheckBox.setSelected(NbPreferences.forModule(NastranExportOptionsPanel.class).getBoolean("NastranExportOptionsPanel.hygrothermnal", false));
        strengthCheckBox.setSelected(NbPreferences.forModule(NastranExportOptionsPanel.class).getBoolean("NastranExportOptionsPanel.strength", false));
        smallRadioButton.setSelected(NbPreferences.forModule(NastranExportOptionsPanel.class).getBoolean("NastranExportOptionsPanel.small", true));
        largeRadioButton.setSelected(NbPreferences.forModule(NastranExportOptionsPanel.class).getBoolean("NastranExportOptionsPanel.large", false));
        freeRadioButton.setSelected(NbPreferences.forModule(NastranExportOptionsPanel.class).getBoolean("NastranExportOptionsPanel.free", false));
    }
    
    public NastranExportOptionsPanel(Laminat laminate) {
        this();
        this.laminate = laminate;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        smallRadioButton = new javax.swing.JRadioButton();
        largeRadioButton = new javax.swing.JRadioButton();
        freeRadioButton = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        hygrothermalCheckBox = new javax.swing.JCheckBox();
        strengthCheckBox = new javax.swing.JCheckBox();

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(NastranExportOptionsPanel.class, "formatPanel.title"))); // NOI18N

        buttonGroup1.add(smallRadioButton);
        smallRadioButton.setSelected(true);
        org.openide.awt.Mnemonics.setLocalizedText(smallRadioButton, org.openide.util.NbBundle.getMessage(NastranExportOptionsPanel.class, "NastranExportOptionsPanel.smallRadioButton.text")); // NOI18N

        buttonGroup1.add(largeRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(largeRadioButton, org.openide.util.NbBundle.getMessage(NastranExportOptionsPanel.class, "NastranExportOptionsPanel.largeRadioButton.text")); // NOI18N

        buttonGroup1.add(freeRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(freeRadioButton, org.openide.util.NbBundle.getMessage(NastranExportOptionsPanel.class, "NastranExportOptionsPanel.freeRadioButton.text")); // NOI18N
        freeRadioButton.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(smallRadioButton)
                    .addComponent(largeRadioButton)
                    .addComponent(freeRadioButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(smallRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(largeRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(freeRadioButton)
                .addContainerGap(10, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(NastranExportOptionsPanel.class, "additionalPanel.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(hygrothermalCheckBox, org.openide.util.NbBundle.getMessage(NastranExportOptionsPanel.class, "NastranExportOptionsPanel.hygrothermalCheckBox.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(strengthCheckBox, org.openide.util.NbBundle.getMessage(NastranExportOptionsPanel.class, "NastranExportOptionsPanel.strengthCheckBox.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hygrothermalCheckBox)
                    .addComponent(strengthCheckBox))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(hygrothermalCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(strengthCheckBox)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JRadioButton freeRadioButton;
    private javax.swing.JCheckBox hygrothermalCheckBox;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JRadioButton largeRadioButton;
    private javax.swing.JRadioButton smallRadioButton;
    private javax.swing.JCheckBox strengthCheckBox;
    // End of variables declaration//GEN-END:variables

    @Override
    public Export<? extends ExportOptions> getExport() {
        NastranExport export = new NastranExport(laminate);
        NastranExportOptions options = export.getOptions();
        options.setHygrothermal(hygrothermalCheckBox.isSelected());
        options.setStrength(strengthCheckBox.isSelected());
        
        int format = NastranCardCreator.SMALL_FORMAT;
        if (largeRadioButton.isSelected()){
            format = NastranCardCreator.LARGE_FORMAT;
        }else if (freeRadioButton.isSelected()){
            format = NastranCardCreator.FREE_FORMAT;
        }
        options.setFormatType(format);
        NbPreferences.forModule(NastranExportOptionsPanel.class).putBoolean("NastranExportOptionsPanel.hygrothermnal", hygrothermalCheckBox.isSelected());
        NbPreferences.forModule(NastranExportOptionsPanel.class).putBoolean("NastranExportOptionsPanel.strength", strengthCheckBox.isSelected());
        NbPreferences.forModule(NastranExportOptionsPanel.class).putBoolean("NastranExportOptionsPanel.small", smallRadioButton.isSelected());
        NbPreferences.forModule(NastranExportOptionsPanel.class).putBoolean("NastranExportOptionsPanel.large", largeRadioButton.isSelected());
        NbPreferences.forModule(NastranExportOptionsPanel.class).putBoolean("NastranExportOptionsPanel.free", freeRadioButton.isSelected());
        return export;
    }
}
