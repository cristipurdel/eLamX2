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
package de.elamx.failureview3d.stressstateview;

import com.ardor3d.math.ColorRGBA;
import com.ardor3d.math.Vector3;
import com.ardor3d.math.type.ReadOnlyColorRGBA;
import com.ardor3d.renderer.IndexMode;
import com.ardor3d.renderer.queue.RenderBucketType;
import com.ardor3d.renderer.state.BlendState;
import com.ardor3d.renderer.state.BlendState.DestinationFunction;
import com.ardor3d.renderer.state.BlendState.SourceFunction;
import com.ardor3d.renderer.state.MaterialState;
import com.ardor3d.scenegraph.Line;
import com.ardor3d.scenegraph.Mesh;
import com.ardor3d.scenegraph.Point;
import com.ardor3d.scenegraph.hint.TransparencyType;
import com.ardor3d.util.geom.BufferUtils;
import de.elamx.clt.CLTRefreshListener;
import de.elamx.clt.CLT_LayerResult;
import de.elamx.clt.calculation.LayerResultContainer;
import de.elamx.core.RawDataExportService;
import de.elamx.core.SnapshotService;
import de.elamx.laminate.Layer;
import de.elamx.laminate.LayerMaterial;
import de.elamx.laminate.StressStrainState;
import de.elamx.laminate.failure.Criterion;
import de.view3d.View3D;
import de.view3d.View3DProperties;
import java.awt.Color;
import java.awt.font.TextAttribute;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.text.AttributedString;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.openide.util.Exceptions;
import org.openide.util.lookup.Lookups;
import org.openide.windows.TopComponent;

/**
 * Top component which displays something.
 */
@TopComponent.Description(
        preferredID = "StressStateViewTopComponent",
        iconBase = "de/elamx/failureview3d/resources/puck.png"
)
public final class StressStateViewTopComponent extends TopComponent implements PropertyChangeListener, CLTRefreshListener {

    private AttributedString captionX = new AttributedString("\u03C3\u2225");
    private AttributedString captionY = new AttributedString("\u03C3\u22A5");
    private AttributedString captionZ = new AttributedString("\u03C4 ");

    public final static Set<LayerResultContainer> uniqueLayerResultContainer = new HashSet<LayerResultContainer>();

    private final LayerResultContainer result;

    public StressStateViewTopComponent(LayerResultContainer result) {
        this.result = result;
        captionX.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB, 1, 2);
        captionY.addAttribute(TextAttribute.SUPERSCRIPT, TextAttribute.SUPERSCRIPT_SUB, 1, 2);
        initComponents();
        setName(result.getLayerResult().getLayer().getName() + " - " + result.getLayerResult().getLayer().getAngle());
        setToolTipText("Test");
        associateLookup(Lookups.fixed(result, new Snapshot(), new VTKExport()));
        result.addPropertyChangeListener(this);
        result.getLayerResult().getClt_layer().addCLTRefreshListener(this);
        updateView();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        view3D1 = new View3D(captionX, captionY, captionZ, true);

        setLayout(new java.awt.BorderLayout());
        add(view3D1, java.awt.BorderLayout.CENTER);
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private de.view3d.View3D view3D1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void componentClosed() {
        result.removePropertyChangeListener(this);
        CLT_LayerResult r = result.getLayerResult();
        if (r != null) {
            r.getClt_layer().removeCLTRefreshListener(this);
        }
        uniqueLayerResultContainer.remove(result);
    }

    @Override
    protected void componentDeactivated() {
        super.componentDeactivated(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        view3D1.setActive(false);
    }

    @Override
    protected void componentActivated() {
        super.componentActivated(); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        view3D1.setActive(true);
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
        if (result.getLayerResult() == null) {
            this.close();
        } else {
            updateView();
        }
        if (evt.getPropertyName().equals(LayerResultContainer.PROP_LAYERRESULT)) {
            Object oldVal = evt.getOldValue();
            if (oldVal != null) {
                ((CLT_LayerResult) oldVal).getClt_layer().removeCLTRefreshListener(this);
            }
        }
    }

    private void updateView() {
        int pointSize = 7;

        Layer l = result.getLayerResult().getLayer();
        LayerMaterial m = l.getMaterial();
        Criterion c = l.getCriterion();
        Mesh shape = c.getAsMesh(m, View3DProperties.getDefault().getNetQuality());
        setCriterionAppearance(shape, c.getColor());
        ArrayList<Mesh> list = new ArrayList<>(3);
        list.add(shape);
        StressStrainState sss;
        if (result.getLayerResult().getRr_lower().getMinimalReserveFactor() > result.getLayerResult().getRr_upper().getMinimalReserveFactor()) {
            sss = result.getLayerResult().getSss_upper();
        } else {
            sss = result.getLayerResult().getSss_lower();
        }
        double[] stresses = sss.getStress();
        list.add(createPointAt(new Vector3((float) stresses[0], (float) stresses[1], (float) stresses[2]), pointSize));
        list.add(createSigmaVector(new Vector3((float) stresses[0], (float) stresses[1], (float) stresses[2]), pointSize));

        double maxValue = m.getRParTen();
        maxValue = Math.max(maxValue, m.getRParCom());
        maxValue = Math.max(maxValue, m.getRNorTen());
        maxValue = Math.max(maxValue, m.getRNorCom());
        maxValue = Math.max(maxValue, m.getRShear());
        view3D1.setShape3D(list, 1.0 / maxValue);
    }

    public static void setCriterionAppearance(Mesh mesh, Color color) {

        mesh.setSolidColor(new ColorRGBA(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, 0.8f));
        // Add a material state
        final MaterialState ms = new MaterialState();
        // Pull diffuse color for front from mesh color
        ms.setColorMaterial(MaterialState.ColorMaterial.Diffuse);
        ms.setColorMaterialFace(MaterialState.MaterialFace.Front);
        // Set shininess for front and back
        ms.setShininess(MaterialState.MaterialFace.Front, 100);
        mesh.setRenderState(ms);

        BlendState blend = new BlendState();
        blend.setBlendEnabled(true);
        mesh.setRenderState(blend);

        mesh.getSceneHints().setRenderBucketType(RenderBucketType.Transparent);
        mesh.getSceneHints().setTransparencyType(TransparencyType.TwoPass);
    }

    @Override
    public void refreshed() {
        updateView();
    }

    private Mesh createPointAt(Vector3 pos, int size) {

        int nNumPoints = 1;

        final FloatBuffer pointData = BufferUtils.createFloatBuffer(nNumPoints * 3);
        for (int i = 0; i < nNumPoints; i++) {
            pointData.put(pos.getXf()); // x 
            pointData.put(pos.getYf()); // y 
            pointData.put(pos.getZf()); // z 
        }
        
        Vector3[] normals = new Vector3[]{new Vector3(0.0f, 0.0f, 0.0f)};

        final Point pointsA = new Point("points", pointData, BufferUtils.createFloatBuffer(normals), null, null);

        pointsA.setDefaultColor(ColorRGBA.RED);
        pointsA.setSolidColor(ColorRGBA.RED);
        pointsA.setAntialiased(true);
        pointsA.setPointSize(size);

        final BlendState bState = new BlendState();
        bState.setBlendEnabled(true);
        pointsA.setRenderState(bState);

        // Add a material state
        final MaterialState ms = new MaterialState();
        // Pull diffuse color for front from mesh color
        ms.setColorMaterial(MaterialState.ColorMaterial.AmbientAndDiffuse);
        ms.setColorMaterialFace(MaterialState.MaterialFace.FrontAndBack);
        pointsA.setRenderState(ms);

        return pointsA;
    }

    private Mesh createSigmaVector(Vector3 pos, int size) {
        // 2 Punkte im LineArray (Startpunkt und Endpunkt)
        int nNumPoints = 2;
        //float lineSize = size/3.0f;                   // Liniestärke relativ zur Punktgröße einstellen

        Vector3[] linePts = new Vector3[nNumPoints];
        linePts[0] = new Vector3(0.0f, 0.0f, 0.0f);   // der Urpsrung
        linePts[1] = pos;                              // übergebener Punkt

        Vector3[] normals = new Vector3[2];
        normals[0] = new Vector3(0.0, 0.0, 0.0);
        normals[1] = new Vector3(0.0, 0.0, 0.0);

        final Line line = new Line("lines", linePts, normals, new ReadOnlyColorRGBA[]{ColorRGBA.RED, ColorRGBA.RED}, null);
        // The type of line we are making is a LineStrip. You can experiment and try making this Lines, or a Line Loop. 
        line.getMeshData().setIndexMode(IndexMode.LineStrip);
        // Update the model bound of our line to fit the data we've provided. 
        line.updateModelBound();
        // Finally let us make this antialiased... see also BlendState below. 
        line.setAntialiased(true);
        line.setLineWidth(size);

        // Antialiased lines work by adding small pixels to the line with alpha blending values. 
        // To make use of this, you need to add a blend state that blends the source color 
        // with the destination color using the source alpha. 
        final BlendState blend = new BlendState();
        blend.setBlendEnabled(true);
        // use source color * source alpha + (1-source color) * destination color. 
        // (Note: for an interesting effect, switch them so it is OneMinusSourceAlpha/SourceAlpha. 
        // This will show you the pixels that are being added to your line in antialiasing.) 
        blend.setSourceFunction(SourceFunction.SourceAlpha);
        blend.setDestinationFunction(DestinationFunction.OneMinusSourceAlpha);
        line.setRenderState(blend);

        // Add a material state
        final MaterialState ms = new MaterialState();
        // Pull diffuse color for front from mesh color
        ms.setColorMaterial(MaterialState.ColorMaterial.AmbientAndDiffuse);
        ms.setColorMaterialFace(MaterialState.MaterialFace.FrontAndBack);
        line.setRenderState(ms);

        return line;
    }

    private class VTKExport implements RawDataExportService {

        @Override
        public void export(FileWriter fw) {
            try {
                view3D1.exportQuadArrays(fw);
            } catch (IOException ex) {
                Exceptions.printStackTrace(ex);
            }
        }

        @Override
        public String getFileExtension() {
            return "vtk";
        }
    }

    private class Snapshot implements SnapshotService {

        @Override
        public void saveSnapshot(File file) {
            view3D1.saveScreenshot(file, "png");
        }
    }
}
