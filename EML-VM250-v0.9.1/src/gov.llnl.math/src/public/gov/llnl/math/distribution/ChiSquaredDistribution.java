/*
 * Copyright (c) 2021, Lawrence Livermore National Security, LLC.  All rights reserved.  LLNL-CODE-822850
 * 
 * OFFICIAL USE ONLY – EXPORT CONTROLLED INFORMATION
 * 
 * This work was produced at the Lawrence Livermore National Laboratory (LLNL) under contract no.  DE-AC52-07NA27344 (Contract 44)
 * between the U.S. Department of Energy (DOE) and Lawrence Livermore National Security, LLC (LLNS) for the operation of LLNL.
 * See license for disclaimers, notice of U.S. Government Rights and license terms and conditions.
 */
package gov.llnl.math.distribution;

import gov.llnl.math.SpecialFunctions;
import gov.llnl.utility.UUIDUtilities;

/**
 *
 * @author nelson85
 */
public class ChiSquaredDistribution implements Distribution
{
  private static final long serialVersionUID = UUIDUtilities.createLong("ChiSquaredDistribution");
  double k; // degree of freedom

  public ChiSquaredDistribution(double d)
  {
    k = d;
  }

  public String toString()
  {
    return "ChiSquared Distribution df=" + k;
  }

  @Override
  public double pdf(double x)
  {
    if (x < 0)
      return 0;
    return Math.exp(
            -Math.log(2) * k / 2
            - SpecialFunctions.gammaln(k / 2)
            + (k / 2 - 1) * Math.log(x)
            - x / 2);
  }

  @Override
  public double cdf(double x)
  {
    return SpecialFunctions.gammaP(k / 2, x / 2);
  }

  @Override
  public double ccdf(double x)
  {
    return SpecialFunctions.gammaQ(k / 2, x / 2);
  }

  @Override
  public double logccdf(double x)
  {
    return Math.log(ccdf(x));
  }

  @Override
  public double cdfinv(double x)
  {
    throw new UnsupportedOperationException("Not supported yet.");
  }

//  static public void main(String[] args)
//  {
//    ChiSquaredDistribution chi2 = new ChiSquaredDistribution(4);
//    System.out.println(chi2.pdf(0.5));
//    System.out.println(chi2.pdf(1));
//    System.out.println(chi2.pdf(2));
//    
//    System.out.println(chi2.cdf(0.5));
//    System.out.println(chi2.cdf(1));
//    System.out.println(chi2.cdf(2));
//  }
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