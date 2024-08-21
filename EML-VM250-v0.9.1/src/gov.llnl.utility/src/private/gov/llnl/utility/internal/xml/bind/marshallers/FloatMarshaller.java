/*
 * Copyright (c) 2021, Lawrence Livermore National Security, LLC.  All rights reserved.  LLNL-CODE-822850
 * 
 * OFFICIAL USE ONLY – EXPORT CONTROLLED INFORMATION
 * 
 * This work was produced at the Lawrence Livermore National Laboratory (LLNL) under contract no.  DE-AC52-07NA27344 (Contract 44)
 * between the U.S. Department of Energy (DOE) and Lawrence Livermore National Security, LLC (LLNS) for the operation of LLNL.
 * See license for disclaimers, notice of U.S. Government Rights and license terms and conditions.
 */
package gov.llnl.utility.internal.xml.bind.marshallers;

import gov.llnl.utility.xml.bind.ObjectWriter;
import gov.llnl.utility.xml.bind.WriterContext;

/**
 *
 * @author nelson85
 */
class FloatMarshaller implements WriterContext.Marshaller<Float>
{
  @Override
  public String marshall(Float o, WriterContext.MarshallerOptions options)
  {
    if (options == null)
      return o.toString();
    String format = options.get(ObjectWriter.DOUBLE_FORMAT, String.class, null);
    if (format == null && o == 0)
      return "0";
    if (format == null)
      return String.format("%.8e", o).replaceFirst("\\.?0+e", "e");
    return String.format(format, o);
  }

  @Override
  public Class<Float> getObjectClass()
  {
    return Float.class;
  }

  @Override
  public boolean hasProperty(String part)
  {
    return ObjectWriter.DOUBLE_FORMAT.equals(part);
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