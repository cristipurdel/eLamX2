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
package de.elamx.hdf5.output;

import ch.systemsx.cisd.hdf5.IHDF5Writer;
import de.elamx.clt.CLT_Laminate;
import de.elamx.core.HDF5OutputWriterService;
import de.elamx.laminate.Laminat;
import de.elamx.laminate.Layer;
import de.elamx.laminate.Material;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.io.FileUtils;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Florian Dexl
 */
@ServiceProvider(service = HDF5OutputWriterService.class, position = 1000)
public class HDF5OutputWriterServiceImpl implements HDF5OutputWriterService {

    @Override
    public void writeHeader(IHDF5Writer hdf5writer, File inputFile) {
        hdf5writer.object().createGroup("materials");
        hdf5writer.object().createGroup("laminates");

        if (inputFile != null) {
            byte[] data = null;
            try {
                hdf5writer.string().write("input file", FileUtils.readFileToString(inputFile));
                data = FileUtils.readFileToByteArray(inputFile);
            } catch (IOException ex) {
                Logger.getLogger(HDF5OutputWriterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            byte[] hash = null;
            try {
                hash = MessageDigest.getInstance("MD5").digest(data);
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(HDF5OutputWriterServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            String checksum = new BigInteger(1, hash).toString(16);
            hdf5writer.string().setAttr("input file", "md5 checksum", checksum);
            hdf5writer.string().setAttr("input file", "filename", inputFile.getName());
            hdf5writer.string().setAttr("input file", "absolute path", inputFile.getAbsolutePath());
        }
    }

    @Override
    public void writeLaminateInformation(IHDF5Writer hdf5writer, Laminat laminate) {

        CLT_Laminate clt_laminate = laminate.getLookup().lookup(CLT_Laminate.class);
        if (clt_laminate == null) {
            clt_laminate = new CLT_Laminate(laminate);
        }

        String groupName = "laminates/".concat(laminate.getName());
        hdf5writer.object().createGroup(groupName);

        hdf5writer.bool().setAttr("/".concat(groupName), "symmetric", laminate.isSymmetric());
        hdf5writer.int32().setAttr("/".concat(groupName), "total number of layers", laminate.getNumberofLayers());
        hdf5writer.float64().setAttr("/".concat(groupName), "total thickness", clt_laminate.getTges());

        hdf5writer.float64().createMatrix("/".concat(groupName).concat("/ABD-Matrix"), 6, 6);
        hdf5writer.float64().writeMatrix("/".concat(groupName).concat("/ABD-Matrix"), clt_laminate.getABDMatrix());

        hdf5writer.float64().createMatrix("/".concat(groupName).concat("/abd-Matrix"), 6, 6);
        hdf5writer.float64().writeMatrix("/".concat(groupName).concat("/abd-Matrix"), clt_laminate.getInvABDMatrix());

        hdf5writer.object().createGroup("/".concat(groupName).concat("/effective stiffness"));
        hdf5writer.object().createGroup("/".concat(groupName).concat("/effective stiffness/with poisson effect"));
        hdf5writer.object().createGroup("/".concat(groupName).concat("/effective stiffness/with poisson effect/membrane"));

        hdf5writer.float64().write("/".concat(groupName).concat("/effective stiffness/with poisson effect/membrane/Exx"), clt_laminate.getExSimple());
        hdf5writer.float64().write("/".concat(groupName).concat("/effective stiffness/with poisson effect/membrane/Eyy"), clt_laminate.getEySimple());
        hdf5writer.float64().write("/".concat(groupName).concat("/effective stiffness/with poisson effect/membrane/Gxy"), clt_laminate.getGSimple());
        hdf5writer.float64().write("/".concat(groupName).concat("/effective stiffness/with poisson effect/membrane/vxy"), clt_laminate.getNuxySimple());
        hdf5writer.float64().write("/".concat(groupName).concat("/effective stiffness/with poisson effect/membrane/vyx"), clt_laminate.getNuyxSimple());

        hdf5writer.object().createGroup("/".concat(groupName).concat("/effective stiffness/with poisson effect/flexural"));

        hdf5writer.float64().write("/".concat(groupName).concat("/effective stiffness/with poisson effect/flexural/Exx"), clt_laminate.getExBendSimple());
        hdf5writer.float64().write("/".concat(groupName).concat("/effective stiffness/with poisson effect/flexural/Eyy"), clt_laminate.getEyBendSimple());
        hdf5writer.float64().write("/".concat(groupName).concat("/effective stiffness/with poisson effect/flexural/Gxy"), clt_laminate.getGBendSimple());
        hdf5writer.float64().write("/".concat(groupName).concat("/effective stiffness/with poisson effect/flexural/vxy"), clt_laminate.getNuxyBendSimple());
        hdf5writer.float64().write("/".concat(groupName).concat("/effective stiffness/with poisson effect/flexural/vyx"), clt_laminate.getNuyxBendSimple());

        hdf5writer.object().createGroup("/".concat(groupName).concat("/effective stiffness/without poisson effect"));
        hdf5writer.object().createGroup("/".concat(groupName).concat("/effective stiffness/without poisson effect/membrane"));

        hdf5writer.float64().write("/".concat(groupName).concat("/effective stiffness/without poisson effect/membrane/Exx"), clt_laminate.getExFixed());
        hdf5writer.float64().write("/".concat(groupName).concat("/effective stiffness/without poisson effect/membrane/Eyy"), clt_laminate.getEyFixed());
        hdf5writer.float64().write("/".concat(groupName).concat("/effective stiffness/without poisson effect/membrane/Gxy"), clt_laminate.getGFixed());

        hdf5writer.object().createGroup("/".concat(groupName).concat("/effective stiffness/without poisson effect/flexural"));

        hdf5writer.float64().write("/".concat(groupName).concat("/effective stiffness/without poisson effect/flexural/Exx"), clt_laminate.getExBendFixed());
        hdf5writer.float64().write("/".concat(groupName).concat("/effective stiffness/without poisson effect/flexural/Eyy"), clt_laminate.getEyBendFixed());
        hdf5writer.float64().write("/".concat(groupName).concat("/effective stiffness/without poisson effect/flexural/Gxy"), clt_laminate.getGBendFixed());

        hdf5writer.object().createGroup("/".concat(groupName).concat("/layers"));
        ArrayList<Layer> layers = laminate.getLayers();
        int layerNum = layers.size();
        Layer l;
        String layerGroupName;
        for (int ii = 0; ii < layerNum; ii++) {
            l = layers.get(ii);
            layerGroupName = "layer " + (ii+1);
            layerGroupName = "/".concat(groupName).concat("/layers/").concat(layerGroupName);
            hdf5writer.object().createGroup(layerGroupName);
            hdf5writer.int32().write(layerGroupName.concat("/number"), l.getNumber());
            hdf5writer.string().write(layerGroupName.concat("/name"), l.getName());
            hdf5writer.float64().write(layerGroupName.concat("/thickness"), l.getThickness());
            hdf5writer.float64().write(layerGroupName.concat("/angle"), l.getAngle());
            hdf5writer.string().write(layerGroupName.concat("/material"), l.getMaterial().getName());
            hdf5writer.string().write(layerGroupName.concat("/criterion"), l.getCriterion().getDisplayName());
        }
    }

    @Override
    public void writeMaterialInformation(IHDF5Writer hdf5writer, Material material) {
        String groupName = "materials/".concat(material.getName());
        hdf5writer.object().createGroup(groupName);

        hdf5writer.float64().write("/".concat(groupName).concat("/E11"), material.getEpar());
        hdf5writer.float64().write("/".concat(groupName).concat("/E22"), material.getEnor());
        hdf5writer.float64().write("/".concat(groupName).concat("/v12"), material.getNue12());
        hdf5writer.float64().write("/".concat(groupName).concat("/v21"), material.getNue21());
        hdf5writer.float64().write("/".concat(groupName).concat("/G12"), material.getG());
        hdf5writer.float64().write("/".concat(groupName).concat("/rho"), material.getRho());
        hdf5writer.float64().write("/".concat(groupName).concat("/a11"), material.getAlphaTPar());
        hdf5writer.float64().write("/".concat(groupName).concat("/a22"), material.getAlphaTNor());
        hdf5writer.float64().write("/".concat(groupName).concat("/b11"), material.getBetaPar());
        hdf5writer.float64().write("/".concat(groupName).concat("/b22"), material.getBetaNor());
        hdf5writer.float64().write("/".concat(groupName).concat("/S11T"), material.getRParTen());
        hdf5writer.float64().write("/".concat(groupName).concat("/S22T"), material.getRNorTen());
        hdf5writer.float64().write("/".concat(groupName).concat("/S11C"), material.getRParCom());
        hdf5writer.float64().write("/".concat(groupName).concat("/S11C"), material.getRNorCom());
        hdf5writer.float64().write("/".concat(groupName).concat("/S12"), material.getRShear());
        for (String key : material.getAdditionalValueKeySet()) {
            hdf5writer.float64().write("/".concat(groupName).concat("/").concat(material.getAdditionalValueDisplayName(key)), material.getAdditionalValue(key));
        }
    }
}
