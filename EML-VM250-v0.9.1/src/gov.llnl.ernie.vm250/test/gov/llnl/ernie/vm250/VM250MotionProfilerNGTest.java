/*
 * Copyright (c) 2021, Lawrence Livermore National Security, LLC.  All rights reserved.  LLNL-CODE-822850
 * 
 * OFFICIAL USE ONLY – EXPORT CONTROLLED INFORMATION
 * 
 * This work was produced at the Lawrence Livermore National Laboratory (LLNL) under contract no.  DE-AC52-07NA27344 (Contract 44)
 * between the U.S. Department of Energy (DOE) and Lawrence Livermore National Security, LLC (LLNS) for the operation of LLNL.
 * See license for disclaimers, notice of U.S. Government Rights and license terms and conditions.
 */

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gov.llnl.ernie.vm250;

import gov.llnl.ernie.data.Record;
import gov.llnl.ernie.vm250.data.VM250VehicleMotion;
import static org.testng.Assert.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 *
 * @author pham21
 */
public class VM250MotionProfilerNGTest
{
  
  public VM250MotionProfilerNGTest()
  {
  }

  @BeforeClass
  public static void setUpClass() throws Exception
  {
  }

  @AfterClass
  public static void tearDownClass() throws Exception
  {
  }

  @BeforeMethod
  public void setUpMethod() throws Exception
  {
  }

  @AfterMethod
  public void tearDownMethod() throws Exception
  {
  }

  /**
   * Test of initialize method, of class VM250MotionProfiler.
   */
  @Test
  public void testInitialize() throws Exception
  {
    System.out.println("initialize");
    VM250MotionProfiler instance = new VM250MotionProfiler();
    instance.initialize(null);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of compute method, of class VM250MotionProfiler.
   */
  @Test
  public void testCompute() throws Exception
  {
    System.out.println("compute");
    Record record = null;
    VM250MotionProfiler instance = new VM250MotionProfiler();
    instance.compute(record);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
  }

  /**
   * Test of extract method, of class VM250MotionProfiler.
   */
  @Test
  public void testExtract()
  {
    System.out.println("extract");
    Record record = null;
    VM250MotionProfiler instance = new VM250MotionProfiler();
    VM250VehicleMotion expResult = null;
    VM250VehicleMotion result = instance.extract(record);
    assertEquals(result, expResult);
    // TODO review the generated test code and remove the default call to fail.
    fail("The test case is a prototype.");
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