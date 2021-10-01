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
package de.elamx.welcome;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JComponent;

/**
 *
 * @author Andreas Hauffe
 */
public class MainPanel extends javax.swing.JPanel {
    
    private final JComponent[] tabs;
    private final TabButton[] buttons;
    private final boolean[] tabAdded;
    private int selTabIndex = -1;

    /**
     * Creates new form FirstStepsPanel
     */
    public MainPanel(JComponent ... tabs) {
        
        this.tabs = tabs;
        
        tabAdded = new boolean[tabs.length];
        Arrays.fill(tabAdded, false);
        
        ActionListener al = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TabButton btn = (TabButton) e.getSource();
                switchTab( btn.getTabIndex() );
                WelcomeOptions.getDefault().setLastActiveTab( btn.getTabIndex() );
            }
        };
        
        buttons = new TabButton[tabs.length];
        for( int i=0; i<buttons.length; i++ ) {
            buttons[i] = new TabButton(tabs[i].getName().toUpperCase(), i);
            buttons[i].addActionListener(al);
        }
        
        initComponents();
        
        for (TabButton b : buttons) {
            tabButtonPanel.add(b);
        }
        
        int activeTabIndex = WelcomeOptions.getDefault().getLastActiveTab();
        if( WelcomeOptions.getDefault().isSecondStart() && activeTabIndex < 0 ) {
            activeTabIndex = 1;
            WelcomeOptions.getDefault().setLastActiveTab( 1 );
        }
        activeTabIndex = Math.max(0, activeTabIndex);
        activeTabIndex = Math.min(activeTabIndex, tabs.length-1);
        buttons[activeTabIndex].setSelected(true);
        switchTab( activeTabIndex );
    }
    
    private void switchTab( int tabIndex ) {
        if( !tabAdded[tabIndex] ) {
            contentPanel.add( tabs[tabIndex], new GridBagConstraints(tabIndex, 0, 1, 1, 1.0, 1.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0) ); //NOI18N
            tabAdded[tabIndex] = true;
        }
        if( selTabIndex >= 0 ) {
            buttons[selTabIndex].setSelected(false);
        }
        JComponent compToShow = tabs[tabIndex];
        JComponent compToHide = selTabIndex >= 0 ? tabs[selTabIndex] : null;
        selTabIndex = tabIndex;
        buttons[selTabIndex].setSelected(true);

        if( null != compToHide )
            compToHide.setVisible( false );
        
        compToShow.setVisible( true );
        compToShow.requestFocusInWindow();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        headerPanel = new javax.swing.JPanel();
        logo1 = new Logo("http://tu-dresden.de");
        logo2 = new Logo("http://tu-dresden.de/mw/ilr/lft");
        jPanel2 = new javax.swing.JPanel();
        facultyLabel = new javax.swing.JLabel();
        instituteLabel = new javax.swing.JLabel();
        tabButtonPanel = new javax.swing.JPanel();
        contentPanel = new javax.swing.JPanel();

        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(204, 204, 204), 10));

        headerPanel.setBackground(new java.awt.Color(11, 42, 81));
        headerPanel.setPreferredSize(new java.awt.Dimension(82, 72));

        logo1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/elamx/welcome/resources/tud_logo_weiss.png"))); // NOI18N

        logo2.setForeground(new java.awt.Color(255, 255, 255));
        logo2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/de/elamx/welcome/resources/ILRLogo_weiss.png"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(logo2, org.openide.util.NbBundle.getMessage(MainPanel.class, "MainPanel.logo2.text")); // NOI18N
        logo2.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N

        javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(headerPanel);
        headerPanel.setLayout(headerPanelLayout);
        headerPanelLayout.setHorizontalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(logo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        headerPanelLayout.setVerticalGroup(
            headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(headerPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(headerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(logo2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(logo1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(78, 100, 126));
        jPanel2.setPreferredSize(new java.awt.Dimension(0, 24));

        facultyLabel.setFont(new java.awt.Font("Arial", 1, 12)); // NOI18N
        facultyLabel.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(facultyLabel, org.openide.util.NbBundle.getMessage(MainPanel.class, "MainPanel.facultyLabel.text")); // NOI18N
        facultyLabel.setToolTipText("");

        instituteLabel.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N
        instituteLabel.setForeground(new java.awt.Color(255, 255, 255));
        org.openide.awt.Mnemonics.setLocalizedText(instituteLabel, org.openide.util.NbBundle.getMessage(MainPanel.class, "MainPanel.instituteLabel.text")); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(67, 67, 67)
                .addComponent(facultyLabel)
                .addGap(18, 18, 18)
                .addComponent(instituteLabel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(facultyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addComponent(instituteLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        tabButtonPanel.setBackground(new java.awt.Color(164, 174, 184));
        tabButtonPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 5, 1, 1));
        tabButtonPanel.setPreferredSize(new java.awt.Dimension(0, 32));
        tabButtonPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 0, 5));

        contentPanel.setBackground(new java.awt.Color(255, 255, 255));
        contentPanel.setLayout(new java.awt.GridBagLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(headerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 477, Short.MAX_VALUE)
            .addComponent(tabButtonPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(headerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tabButtonPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(contentPanel, javax.swing.GroupLayout.DEFAULT_SIZE, 63, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel contentPanel;
    private javax.swing.JLabel facultyLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel instituteLabel;
    private javax.swing.JPanel jPanel2;
    private de.elamx.welcome.Logo logo1;
    private de.elamx.welcome.Logo logo2;
    private javax.swing.JPanel tabButtonPanel;
    // End of variables declaration//GEN-END:variables
}