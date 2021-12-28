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
package de.elamx.clt.view3d;

import com.ardor3d.scenegraph.Node;
import de.elamx.clt.CLT_Input;
import de.elamx.core.GlobalProperties;
import de.elamx.core.RawDataExportService;
import de.elamx.core.SnapshotService;
import de.view3d.View3D;
import de.view3d.View3DProperties;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import javax.swing.JSpinner;
import javax.swing.JToolBar;
import javax.swing.SpinnerNumberModel;
import org.netbeans.api.progress.ProgressHandle;
import org.openide.util.Exceptions;
import org.openide.util.ImageUtilities;
import org.openide.util.NbBundle;
import org.openide.util.NbPreferences;
import org.openide.util.RequestProcessor;
import org.openide.util.lookup.Lookups;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@TopComponent.Description(
        preferredID = "CLT3DViewTopComponent"
//iconBase="SET/PATH/TO/ICON/HERE", 
)
public final class CLT3DViewTopComponent extends TopComponent implements PropertyChangeListener {

    private final CLT_Input dataHolder;
    private final View3D.AdditionalGeometryButton bcButton;

    public CLT3DViewTopComponent(CLT_Input dataHolder) {
        initComponents();
        setName(NbBundle.getMessage(CLT3DViewTopComponent.class, "CLT_CLT3DViewTopComponent"));
        setToolTipText(NbBundle.getMessage(CLT3DViewTopComponent.class, "HINT_CLT_CLT3DViewTopComponent"));
        this.dataHolder = dataHolder;
        dataHolder.addPropertyChangeListener(this);
        View3DProperties.getDefault().addPropertyChangeListener(this);
        associateLookup(Lookups.fixed(new Snapshot(), new VTKExport()));

        JToolBar bar = new JToolBar();

        bcButton = new View3D.AdditionalGeometryButton(view3D);
        bcButton.setMargin(new java.awt.Insets(0, 0, 0, 0));
        bcButton.setIcon(ImageUtilities.loadImageIcon("de/elamx/clt/view3d/resources/force.png", false));
        bcButton.setToolTipText(NbBundle.getMessage(CLT3DViewTopComponent.class, "forceButton.tip"));
        bcButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                NbPreferences.forModule(CLT3DViewTopComponent.class).putBoolean("CLT3DViewTopComponent.bcButton.selected", bcButton.isSelected());
            }
        });
        bcButton.setSelected(NbPreferences.forModule(CLT3DViewTopComponent.class).getBoolean("CLT3DViewTopComponent.bcButton.selected", true));

        bar.add(bcButton);
        
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(jSpinner1);
        DecimalFormat format = editor.getFormat();
        format.setDecimalFormatSymbols(new DecimalFormatSymbols(GlobalProperties.getDefault().getActualLocale()));
        jSpinner1.setEditor(editor);
        jSpinner1.addChangeListener(new javax.swing.event.ChangeListener() {
            @Override
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                updateView3D();
                NbPreferences.forModule(CLT3DViewTopComponent.class).putDouble("CLT3DViewTopComponent.scaleSpinner.value", ((Number) jSpinner1.getValue()).doubleValue());
            }
        });

        view3D.addAdditionalButtonBar(bar);
        updateView3D();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        view3D = new de.view3d.View3D();

        setLayout(new java.awt.BorderLayout());

        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(CLT3DViewTopComponent.class, "CLT3DViewTopComponent.jLabel1.text")); // NOI18N
        jPanel1.add(jLabel1);

        jSpinner1.setModel(new DoubleSpinnerModel(NbPreferences.forModule(CLT3DViewTopComponent.class).getDouble("CLT3DViewTopComponent.scaleSpinner.value", 1.0), 0.00000000001d, 100000000.0d));
        jSpinner1.setMinimumSize(new java.awt.Dimension(50, 20));
        jSpinner1.setPreferredSize(new java.awt.Dimension(50, 20));
        jPanel1.add(jSpinner1);

        add(jPanel1, java.awt.BorderLayout.PAGE_START);
        add(view3D, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSpinner jSpinner1;
    private de.view3d.View3D view3D;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentClosed() {
        dataHolder.removePropertyChangeListener(this);
        View3DProperties.getDefault().removePropertyChangeListener(this);
    }

    @Override
    protected void componentDeactivated() {
        super.componentDeactivated(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        view3D.setActive(false);
    }

    @Override
    protected void componentActivated() {
        super.componentActivated(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        view3D.setActive(true);
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    void readProperties(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_NEVER;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        updateView3D();
    }

    private void updateView3D() {
        final ProgressHandle ph = ProgressHandle.createSystemHandle("Test", null);
        RequestProcessor.getDefault().post(new Runnable() {
            @Override
            public void run() {
                ph.start();
                final Plate plate = new Plate(dataHolder.getStrains(), dataHolder.getLoad());
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        Node group = new Node();
                        for (Node g : plate.getUndeformedWithBC()) {
                            group.attachChild(g);
                        }
                        view3D.setShape3D(plate.getShapes(true,((Number) jSpinner1.getValue()).doubleValue()), 1.0);
                        bcButton.setGeo(group);
                        ph.finish();
                    }
                });
            }
        });
    }

    private class Snapshot implements SnapshotService {

        @Override
        public void saveSnapshot(File file) {
            view3D.saveScreenshot(file, "png");
        }
    }

    private class VTKExport implements RawDataExportService {

        @Override
        public void export(FileWriter fw) {
            try {
                view3D.exportQuadArrays(fw);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        @Override
        public String getFileExtension() {
            return "vtk";
        }
    }

    class SpinnerLayout extends BorderLayout {

        @Override
        public void addLayoutComponent(Component comp, Object constraints) {
            if ("Editor".equals(constraints)) {
                constraints = "Center";
            } else if ("Next".equals(constraints)) {
                constraints = "North";
            } else if ("Previous".equals(constraints)) {
                constraints = "South";
            }
            super.addLayoutComponent(comp, constraints);
        }
    }
    
    class DoubleSpinnerModel extends SpinnerNumberModel{
        
        double value;
        double minimum;
        double maximum;
        double stepSize;
        
        public DoubleSpinnerModel(double value, double minimum, double maximum) {
            this.value = value;
            this.minimum = minimum;
            this.maximum = maximum;
        }

        @Override
        public Object getValue() {
            return value;
        }

        @Override
        public void setValue(Object value) {
            double newVal = ((Double)value);
            if (newVal != this.value) {
                this.value = newVal;
                fireStateChanged();
            }
        }

        @Override
        public Object getNextValue() {
            stepSize = 0.1*Math.pow(10,(int)Math.log10(value));
            
            double val = value + stepSize;
            
            if (val > maximum){
                return null;
            }else{
                return val;
            }
        }

        @Override
        public Object getPreviousValue() {
            stepSize = 0.1*Math.pow(10,(int)Math.log10(value));
            
            double val = value - stepSize;
            
            if (val < minimum){
                return null;
            }else{
                return val;
            }
        }
        
    }
}
