/*
 * Copyright (c) 2021, Lawrence Livermore National Security, LLC.  All rights reserved.  LLNL-CODE-822850
 * 
 * OFFICIAL USE ONLY – EXPORT CONTROLLED INFORMATION
 * 
 * This work was produced at the Lawrence Livermore National Laboratory (LLNL) under contract no.  DE-AC52-07NA27344 (Contract 44)
 * between the U.S. Department of Energy (DOE) and Lawrence Livermore National Security, LLC (LLNS) for the operation of LLNL.
 * See license for disclaimers, notice of U.S. Government Rights and license terms and conditions.
 */
package gov.llnl.math.internal;

import gov.llnl.math.Interpolator;
import gov.llnl.math.Interpolator.Method;
import gov.llnl.math.Interpolator.Methods;
import java.util.function.DoubleUnaryOperator;

/**
 * Factory for creating an Interpolator for 1d data.
 *
 * The default method is linear if not specified. The default end behavior is to
 * use the inner interpolation method. Lower and upper end behaviors can be
 * defined independently.
 *
 * @author nelson85
 */
public class InterpolatorBuilder implements gov.llnl.math.Interpolator.Builder
{
  private Method lowerMethod;
  private Method innerMethod = InterpolatorBuilder::linearMethod;
  private Method upperMethod;

  /**
   * Create a new builder with the specified parameters.
   *
   * @return a new Interpolator.
   */
  @Override
  public Interpolator create()
  {
    Method inner = this.innerMethod;
    Method lower = this.lowerMethod;
    Method upper = this.upperMethod;
    if (lower == null)
      lower = inner;
    if (upper == null)
      upper = inner;

    // FIXME support speed optimizations such as requests to process ordered queries.
    return new InterpolatorImpl(lower, inner, upper);
  }

//<editor-fold desc="end point">
  @Override
  public InterpolatorBuilder lower(Method end)
  {
    this.lowerMethod = end;
    return this;
  }

  @Override
  public InterpolatorBuilder upper(Method end)
  {
    this.upperMethod = end;
    return this;
  }

  @Override
  public InterpolatorBuilder clip()
  {
    this.lowerMethod = Methods.Nearest.get();
    this.upperMethod = this.lowerMethod;
    return this;
  }

//</editor-fold>
//<editor-fold desc="method">
  /**
   * Specify an interpolation method.
   *
   * @param method
   * @return
   */
  @Override
  public InterpolatorBuilder method(Interpolator.Method method)
  {
    this.innerMethod = method;
    return this;
  }

  /**
   * Use a kernel density estimate weighting the nearest points.
   *
   * Typical density functions for this method are Gaussian or
   * lambda/(x*x+lambda).
   *
   * @param density is the specified density function.
   * @param nearest is the number of points around the query to consider.
   * @return
   */
  @Override
  public InterpolatorBuilder weighted(DoubleUnaryOperator density, int nearest)
  {
    this.innerMethod = new Interpolator.Method()
    {
      @Override
      public double evaluate(double[] x, double[] y, double xq, int n)
      {
        int p0 = n - (nearest / 2) + 1;
        if (p0 < 0)
        {
          p0 = 0;
        }

        int p1 = p0 + nearest;
        if (p1 > x.length)
        {
          p0 -= p1 - x.length;
          p1 = x.length;
        }

        double v = 0;
        double w = 0;
        for (int j = p0; j < p1; ++j)
        {
          double wi = density.applyAsDouble(xq - x[j]);

          w += wi;
          v += wi * y[j];
        }
        return v / w;
      }
    };
    return this;
  }
//</editor-fold>
//<editor-fold desc="internal" defaultstate="collapsed">

  public static double nearestMethod(double[] x, double[] y, double xq, int n)
  {
    return y[n];
  }

  public static double linearMethod(double[] x, double[] y, double xq, int n)
  {
    if (x[n] > xq && n > 0)
      n--;
    if (n >= x.length - 1)
      n = x.length - 2;
    double f = (xq - x[n]) / (x[n + 1] - x[n]);
    return y[n] * (1 - f) + f * y[n + 1];
  }

  public static double zeroMethod(double[] x, double[] y, double xq, int n)
  {
    return 0;
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