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
package de.elamx.clt.plateui.buckling.batchrun;

import de.elamx.clt.CLT_Laminate;
import de.elamx.clt.plate.BucklingResult;
import de.elamx.clt.plateui.buckling.BucklingModuleData;
import de.elamx.laminate.Laminat;
import de.elamx.utilities.Utilities;
import java.io.PrintStream;
import java.util.Locale;
import org.openide.util.lookup.ServiceProvider;

/**
 *
 * @author Andreas Hauffe
 */
@ServiceProvider(service=BucklingOutputWriterService.class, position=1)
public class BucklingOutputWriterServiceImpl implements BucklingOutputWriterService{
    
    @Override
    public void writeResults(PrintStream out, BucklingModuleData data, Laminat laminate, BucklingResult result){
        Locale lo = Locale.ENGLISH;      
        double [][] dmat;
        String dMatrixOption;
        if (data.getBucklingInput().isDtilde()) {
            dmat = data.getLaminat().getLookup().lookup(CLT_Laminate.class).getDtildeMatrix();
            dMatrixOption = "D-tilde matrix";
        } else if(!data.getBucklingInput().isWholeD()) {
            dmat = data.getLaminat().getLookup().lookup(CLT_Laminate.class).getDMatrixWithZeroD12D16();
            dMatrixOption = "D matrix with D_{16} = D_{26} = 0";
        } else {
            dmat = data.getLaminat().getLookup().lookup(CLT_Laminate.class).getDMatrix();
            dMatrixOption = "Original D matrix";
        }
        out.println("********************************************************************************");
        out.println(Utilities.centeredText("BUCKLING", 80));
        out.println(Utilities.centeredText(data.getName(), 80));
        out.println("********************************************************************************");
        out.println();
        out.println("Laminat: " + laminate.getName());
        out.println();
        out.println("D-matrix option: " + dMatrixOption);
        out.println("D-matrix used:");
        for (int ii=0; ii<3; ii++) {
        out.printf(lo,"  %10.1f    %10.1f    %10.1f%n"  , dmat[ii][0], dmat[ii][1], dmat[ii][2]);
        };
        out.println();
        out.println("critical load");
        double[] ncrit = result.getN_crit();
        out.printf(lo,"  nx_crit  = %17.10E%n"  , ncrit[0]);
        out.printf(lo,"  ny_crit  = %17.10E%n"  , ncrit[1]);
        out.printf(lo,"  nxy_crit = %17.10E%n"  , ncrit[2]);
        out.println();
        out.println("Eigenvalues 1 to 5");
        double[] eigenvalues = result.getEigenvalues_();
        out.printf(lo,"  Eigenv1  = %17.10E%n"  , eigenvalues[0]);
        out.printf(lo,"  Eigenv2  = %17.10E%n"  , eigenvalues[1]);
        out.printf(lo,"  Eigenv3  = %17.10E%n"  , eigenvalues[2]);
        out.printf(lo,"  Eigenv4  = %17.10E%n"  , eigenvalues[3]);
        out.printf(lo,"  Eigenv5  = %17.10E%n"  , eigenvalues[4]);
        out.println();
        out.println();
    }  
}
