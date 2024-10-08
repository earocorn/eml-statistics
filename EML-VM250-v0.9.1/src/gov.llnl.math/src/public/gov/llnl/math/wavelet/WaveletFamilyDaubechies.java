/*
 * Copyright (c) 2021, Lawrence Livermore National Security, LLC.  All rights reserved.  LLNL-CODE-822850
 * 
 * OFFICIAL USE ONLY – EXPORT CONTROLLED INFORMATION
 * 
 * This work was produced at the Lawrence Livermore National Laboratory (LLNL) under contract no.  DE-AC52-07NA27344 (Contract 44)
 * between the U.S. Department of Energy (DOE) and Lawrence Livermore National Security, LLC (LLNS) for the operation of LLNL.
 * See license for disclaimers, notice of U.S. Government Rights and license terms and conditions.
 */

package gov.llnl.math.wavelet;

import gov.llnl.math.DoubleArray;

public class WaveletFamilyDaubechies implements WaveletFamily
{

  @Override
  public double[] get(int i) throws WaveletNotFoundException
  {
    if (i == 0)
      throw new WaveletNotFoundException("Daubechies length must be positive.");
    if ((i & 1) == 1)
      throw new WaveletNotFoundException("Daubechies length must be even.");
    if (i / 2 > COEF.length)
      throw new WaveletNotFoundException("Daubechies length exceeded.");
    return COEF[i / 2 - 1].clone();
  }

  @Override
  public String[] getAvailableWavelets()
  {
    return AVAILABLE;
  }

//<editor-fold desc="internal" defaultstate="collapsed">
  static final double[][] COEF = new double[12][];
  static final String[] AVAILABLE = new String[]
  {
    "daub4", "daub6", "daub8", "daub10", "daub12", "daub14",
    "daub16", "daub18", "daub20", "daub22", "daub24"
  };

  static
  {
    COEF[0] = new double[]
    {
      0.50000000000000, 0.50000000000000
    };
    COEF[1] = new double[]
    {
      0.34150635094622, 0.59150635094587, 0.15849364905378, -0.09150635094587
    };
    COEF[2] = new double[]
    {
      0.23523360389270, 0.57055845791731, 0.32518250026371, -0.09546720778426,
      -0.06041610415535, 0.02490874986589
    };
    COEF[3] = new double[]
    {
      0.16290171402562, 0.50547285754565, 0.44610006912319, -0.01978751311791,
      -0.13225358368437, 0.02180815023739, 0.02325180053556, -0.00749349466513
    };
    COEF[4] = new double[]
    {
      0.11320949129173, 0.42697177135271, 0.51216347213016, 0.09788348067375,
      -0.17132835769133, -0.02280056594205, 0.05485132932108, -0.00441340005433,
      -0.00889593505093, 0.00235871396920
    };
    COEF[5] = new double[]
    {

      0.07887121600143, 0.34975190703757, 0.53113187994121, 0.22291566146505,
      -0.15999329944587, -0.09175903203003, 0.06894404648720, 0.01946160485396,
      -0.02233187416548, 0.00039162557603, 0.00337803118151, -0.00076176690258,
    };
    COEF[6] = new double[]
    {
      0.05504971537285, 0.28039564181304, 0.51557424581833, 0.33218624110566,
      -0.10175691123173, -0.15841750564054, 0.05042323250485, 0.05700172257986,
      -0.02689122629486, -0.01171997078235, 0.00887489618962, 0.00030375749776,
      -0.00127395235906, 0.00025011342658
    };
    COEF[7] = new double[]
    {
      0.03847781105406, 0.22123362357624, 0.47774307521438, 0.41390826621166,
      -0.01119286766665, -0.20082931639111, 0.00033409704628, 0.09103817842345,
      -0.01228195052300, -0.03117510332533, 0.00988607964808, 0.00618442240954,
      -0.00344385962813, -0.00027700227421, 0.00047761485533, -0.00008306863060
    };
    COEF[8] = new double[]
    {
      0.02692517479416, 0.17241715192471, 0.42767453217028, 0.46477285717278,
      0.09418477475112, -0.20737588089628, -0.06847677451090, 0.10503417113714,
      0.02172633772990, -0.04782363205882, 0.00017744640673, 0.01581208292614,
      -0.00333981011324, -0.00302748028715, 0.00130648364018, 0.00016290733601,
      -0.00017816487955, 0.00002782275679
    };
    COEF[9] = new double[]
    {
      0.01885857879640, 0.13306109139687, 0.37278753574266, 0.48681405536610,
      0.19881887088440, -0.17666810089647, -0.13855493935993, 0.09006372426666,
      0.06580149355070, -0.05048328559801, -0.02082962404385, 0.02348490704841,
      0.00255021848393, -0.00758950116768, 0.00098666268244, 0.00140884329496,
      -0.00048497391996, -0.00008235450295, 0.00006617718320, -0.00000937920789
    };
    COEF[10] = new double[]
    {
      0.01321886471666, 0.1018707676010, 0.3181271742304, 0.4848537683132, 0.2913027988903,
      -0.1147459261776, -0.1939104913955, 0.04669986906777, 0.1059330899182, -0.03286629145225,
      -0.04697931589875, 0.02215725478298, 0.01473674479914, -0.01086456905449, -0.002362343964096,
      0.003484917545119, -0.0002182081030939, -0.0006314627963034, 0.0001761774389539, 0.00003849423888144,
      -0.00002449063218491, 3.177931817946e-6
    };
    COEF[11] = new double[]
    {
      0.009271766518224, 0.07747505450120, 0.2668303750255, 0.4647096733230, 0.3647868272188,
      -0.03165284709804, -0.2235719287137, -0.01681447405735, 0.1290318596729, 0.003789788060976,
      -0.06818780604444, 0.007671493573868, 0.02937765454983, -0.008639889614158, -0.009079834573749,
      0.004745746461046, 0.001590005428333, -0.001541141788352, 4.62810454280e-6, 0.0002748192162496,
      -0.00006258185578400, -0.00001714136139124, 9.034669557220e-6, -1.081217009051e-6
    };
    for (int i = 0; i < 12; ++i)
      DoubleArray.multiplyAssign(COEF[i], Math.sqrt(2));
  }
//</editor-fold>
}


/*
 * Copyright (c) 2021, Lawrence Livermore National Security, LLC.  All rights reserved.  LLNL-CODE-822850
 * 
 * OFFICIAL USE ONLY – EXPORT CONTROLLED INFORMATION
 * 
 * This work was produced at the Lawrence Livermore National Laboratory (LLNL) under contract no.  DE-AC52-07NA27344 (Contract 44)
 * between the U.S. Department of Energy (DOE) and Lawrence Livermore National Security, LLC (LLNS) for the operation of LLNL.
 * See license for disclaimers, notice of U.S. Government Rights and license terms and conditions.
 */