/*
 * Copyright (c) 2021, Lawrence Livermore National Security, LLC.  All rights reserved.  LLNL-CODE-822850
 * 
 * OFFICIAL USE ONLY – EXPORT CONTROLLED INFORMATION
 * 
 * This work was produced at the Lawrence Livermore National Laboratory (LLNL) under contract no.  DE-AC52-07NA27344 (Contract 44)
 * between the U.S. Department of Energy (DOE) and Lawrence Livermore National Security, LLC (LLNS) for the operation of LLNL.
 * See license for disclaimers, notice of U.S. Government Rights and license terms and conditions.
 */
package gov.llnl.ernie.rtk;

import gov.llnl.math.DoubleArray;
import gov.llnl.utility.annotation.Matlab;
import gov.llnl.utility.xml.bind.ReaderInfo;
import java.util.List;

/**
 *
 * @author nelson85
 * @param <T>
 */
@ReaderInfo(SpectraListReader.class)
public interface SpectraList<T extends SpectrumBase> extends List<T>
{
  Class<T> getSpectrumClass();

  @Matlab
  default Object[] getAttributes(String name)
  {
    Object[] out = new Object[size()];
    int i = 0;
    for (T s : this)
    {
      out[i++] = s.getAttribute(name);
    }
    return out;
  }

  @Matlab
  default double[] getAttributesDouble(String name)
  {
    double[] out = new double[size()];
    int i = 0;
    for (T s : this)
    {
      out[i++] = (double) s.getAttribute(name);
    }
    return out;
  }

  @Matlab
  default String[] getTitles()
  {
    String[] out = new String[size()];
    int i = 0;
    for (T s : this)
    {
      out[i++] = s.getTitle();
    }
    return out;
  }

  default double[] getMean()
  {
    double[] out = null;
    double lt = 0;
    for (T s : this)
    {
      if (out == null)
        out = s.toDoubles();
      else
        DoubleArray.addAssign(out, s.toDoubles());
      lt += s.getLiveTime();
    }
    if (lt > 0)
      DoubleArray.divideAssign(out, lt);
    return out;
  }

  default double[] getSum()
  {
    double[] out = null;
    for (T s : this)
    {
      if (out == null)
        out = s.toDoubles();
      else
        DoubleArray.addAssign(out, s.toDoubles());
    }
    return out;
  }
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