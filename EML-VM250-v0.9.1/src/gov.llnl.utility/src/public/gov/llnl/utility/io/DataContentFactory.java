/*
 * Copyright (c) 2021, Lawrence Livermore National Security, LLC.  All rights reserved.  LLNL-CODE-822850
 * 
 * OFFICIAL USE ONLY – EXPORT CONTROLLED INFORMATION
 * 
 * This work was produced at the Lawrence Livermore National Laboratory (LLNL) under contract no.  DE-AC52-07NA27344 (Contract 44)
 * between the U.S. Department of Energy (DOE) and Lawrence Livermore National Security, LLC (LLNS) for the operation of LLNL.
 * See license for disclaimers, notice of U.S. Government Rights and license terms and conditions.
 */
package gov.llnl.utility.io;

import gov.llnl.utility.internal.io.DataContentFactoryImpl;
import gov.llnl.utility.xml.bind.DocumentReader;
import java.io.IOException;
import java.net.URL;

/**
 *
 * @author nelson85
 */
public abstract class DataContentFactory
{
  protected DataContentFactory()
  {
  }

  static public DataContentFactory newInstance(URL url) throws IOException, ReaderException
  {
    DocumentReader<DataContentFactoryImpl> reader = DocumentReader.create(DataContentFactoryImpl.class);
    return reader.loadURL(url);
  }

  abstract public <T> DataFileReader<T> getDataFileReader(String uri, Class<T> cls);

  abstract public <T> DataStreamReader<T> getDataStreamReader(String uri, Class<T> cls);

  abstract public <T> DataFileWriter<T> getDataFileWriter(String uri, Class<T> cls);

  abstract public <T> DataStreamWriter<T> getDataStreamWriter(String uri, Class<T> cls);
}

/*
<dataContentFactory>
  <handler type="gov.llnl.rtk.data.Bins" ns="urn:sandia:gadras" >
    <dataFileReader name="gov.llnl.rtk.gadras.data.BinGadrasInputFile"/>
    <dataFileWriter name="gov.llnl.rtk.gadras.data.BinGadrasOutputFile"/>
  </handler>

</dataContentFactory>
 */


/*
 * Copyright (c) 2021, Lawrence Livermore National Security, LLC.  All rights reserved.  LLNL-CODE-822850
 * 
 * OFFICIAL USE ONLY – EXPORT CONTROLLED INFORMATION
 * 
 * This work was produced at the Lawrence Livermore National Laboratory (LLNL) under contract no.  DE-AC52-07NA27344 (Contract 44)
 * between the U.S. Department of Energy (DOE) and Lawrence Livermore National Security, LLC (LLNS) for the operation of LLNL.
 * See license for disclaimers, notice of U.S. Government Rights and license terms and conditions.
 */