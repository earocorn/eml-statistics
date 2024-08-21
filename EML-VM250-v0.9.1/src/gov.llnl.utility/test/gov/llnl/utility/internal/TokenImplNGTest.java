/*
 * Copyright (c) 2021, Lawrence Livermore National Security, LLC.  All rights reserved.  LLNL-CODE-822850
 * 
 * OFFICIAL USE ONLY – EXPORT CONTROLLED INFORMATION
 * 
 * This work was produced at the Lawrence Livermore National Laboratory (LLNL) under contract no.  DE-AC52-07NA27344 (Contract 44)
 * between the U.S. Department of Energy (DOE) and Lawrence Livermore National Security, LLC (LLNS) for the operation of LLNL.
 * See license for disclaimers, notice of U.S. Government Rights and license terms and conditions.
 */
package gov.llnl.utility.internal;

import java.util.regex.Pattern;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 *
 * @author pham21
 */
public class TokenImplNGTest
{

  public TokenImplNGTest()
  {
  }

  /**
   * Test of id method, of class TokenImpl.
   */
  @Test
  public void testId()
  {
    System.out.println("id");
    TokenImpl instance = new TokenImpl();
    assertEquals(instance.id(), 0);
    instance.index = 1;
    assertEquals(instance.id(), 1);
  }

  /**
   * Test of start method, of class TokenImpl.
   */
  @Test(expectedExceptions =
  {
    IllegalStateException.class
  })
  public void testStart_0args()
  {
    System.out.println("start");
    String str = "Hello, World!";
    TokenImpl instance = new TokenImpl();
    Pattern p = Pattern.compile("\\S+");

    instance.matcher = p.matcher(str);

    instance.matcher.region(0, str.length());
    instance.matcher.lookingAt();
    assertEquals(instance.start(), 0);
    instance.offset = 1;
    assertEquals(instance.start(), 1);
    instance.matcher.find();
    assertEquals(instance.start(), 8);

    // Test IllegalStateException
    instance.matcher = p.matcher(str);
    instance.start();
  }

  /**
   * Test of end method, of class TokenImpl.
   */
  @Test(expectedExceptions =
  {
    IllegalStateException.class
  })
  public void testEnd_0args()
  {
    System.out.println("end");
    String str = "Hello, World!";
    TokenImpl instance = new TokenImpl();
    Pattern p = Pattern.compile("\\S+");

    instance.matcher = p.matcher(str);

    instance.matcher.region(0, str.length());
    instance.matcher.lookingAt();
    assertEquals(instance.end(), 6);
    instance.offset = 1;
    assertEquals(instance.end(), 7);
    instance.matcher.find();
    assertEquals(instance.end(), 14);

    // Test IllegalStateException
    instance.matcher = p.matcher(str);
    instance.end();
  }

  /**
   * Test of groupCount method, of class TokenImpl.
   */
  @Test
  public void testGroupCount()
  {
    System.out.println("groupCount");
    String str = "Hello Hello, World!";
    TokenImpl instance = new TokenImpl();
    Pattern p = Pattern.compile("(Hello)|(World)");
    instance.matcher = p.matcher(str);
    assertEquals(instance.groupCount(), 2);
  }

  /**
   * Test of start method, of class TokenImpl.
   */
  @Test(expectedExceptions =
  {
    IllegalStateException.class,
    IndexOutOfBoundsException.class
  })
  public void testStart_int()
  {
    System.out.println("start");
    String str = "Hello Hello, World! Why hello";
    TokenImpl instance = new TokenImpl();
    Pattern p = Pattern.compile("(Hello)|(World)|(Why)");
    instance.matcher = p.matcher(str);

    // First Hello
    instance.matcher.find();
    assertEquals(instance.start(0), 0);
    assertEquals(instance.start(1), 0);
    assertEquals(instance.start(2), -1);
    // Second Hello
    instance.matcher.find();
    assertEquals(instance.start(0), 6);
    assertEquals(instance.start(1), 6);
    assertEquals(instance.start(2), -1);
    // First World
    instance.matcher.find();
    assertEquals(instance.start(0), 13);
    assertEquals(instance.start(1), -1);
    assertEquals(instance.start(2), 13);
    // First Why
    instance.matcher.find();
    assertEquals(instance.start(0), 20);
    assertEquals(instance.start(1), -1);
    assertEquals(instance.start(2), -1);

    // Test IllegalStateException
    instance.matcher = p.matcher(str);
    try
    {
      instance.start(0);
    }
    catch (IllegalStateException ex)
    {
      // Expected exception
    }

    // Test IndexoutOutOfBound
    // Let the test capture it
    instance.start(4);
  }

  /**
   * Test of end method, of class TokenImpl.
   */
  @Test(expectedExceptions =
  {
    IllegalStateException.class,
    IndexOutOfBoundsException.class
  })
  public void testEnd_int()
  {
    System.out.println("end");
    String str = "Hello Hello, World! Why hello";
    TokenImpl instance = new TokenImpl();
    Pattern p = Pattern.compile("(Hello)|(World)|(Why)");
    instance.matcher = p.matcher(str);

    // First Hello
    instance.matcher.find();
    assertEquals(instance.end(0), 5);
    assertEquals(instance.end(1), 5);
    assertEquals(instance.end(2), -1);
    // Second Hello
    instance.matcher.find();
    assertEquals(instance.end(0), 11);
    assertEquals(instance.end(1), 11);
    assertEquals(instance.end(2), -1);
    // First World
    instance.matcher.find();
    assertEquals(instance.end(0), 18);
    assertEquals(instance.end(1), -1);
    assertEquals(instance.end(2), 18);
    // First Why
    instance.matcher.find();
    assertEquals(instance.end(0), 23);
    assertEquals(instance.end(1), -1);
    assertEquals(instance.end(2), -1);

    // Test IllegalStateException
    instance.matcher = p.matcher(str);
    try
    {
      instance.end(0);
    }
    catch (IllegalStateException ex)
    {
      // Expected exception
    }

    // Test IndexoutOutOfBound
    // Let the test capture it
    instance.end(4);
  }

  /**
   * Test of group method, of class TokenImpl.
   */
  @Test(expectedExceptions =
  {
    IllegalStateException.class
  })
  public void testGroup_0args()
  {
    System.out.println("group");
    String str = "Hello Hello, World! Why hello";
    TokenImpl instance = new TokenImpl();
    Pattern p = Pattern.compile("(Hello)|(World)|(Why)");
    instance.matcher = p.matcher(str);

    // First Hello
    instance.matcher.find();
    assertEquals(instance.group(), "Hello");
    // Second Hello
    instance.matcher.find();
    assertEquals(instance.group(), "Hello");
    // First World
    instance.matcher.find();
    assertEquals(instance.group(), "World");
    // First Why
    instance.matcher.find();
    assertEquals(instance.group(), "Why");

    // Test IllegalStateException
    instance.matcher = p.matcher(str);
    instance.group();
  }

  /**
   * Test of group method, of class TokenImpl.
   */
  @Test(expectedExceptions =
  {
    IllegalStateException.class,
    IndexOutOfBoundsException.class
  })
  public void testGroup_int()
  {
    System.out.println("group");
    String str = "Hello Hello, World! Why hello";
    TokenImpl instance = new TokenImpl();
    Pattern p = Pattern.compile("(Hello)|(World)|(Why)");
    instance.matcher = p.matcher(str);

    // First Hello
    instance.matcher.find();
    assertEquals(instance.group(0), "Hello");
    assertEquals(instance.group(1), "Hello");
    assertEquals(instance.group(2), null);
    // Second Hello
    instance.matcher.find();
    assertEquals(instance.group(0), "Hello");
    assertEquals(instance.group(1), "Hello");
    assertEquals(instance.group(2), null);
    // First World
    instance.matcher.find();
    assertEquals(instance.group(0), "World");
    assertEquals(instance.group(1), null);
    assertEquals(instance.group(2), "World");
    // First Why
    instance.matcher.find();
    assertEquals(instance.group(0), "Why");
    assertEquals(instance.group(1), null);
    assertEquals(instance.group(2), null);
    
    // Test IllegalStateException
    instance.matcher = p.matcher(str);
    try
    {
      instance.group(0);
    }
    catch (IllegalStateException ex)
    {
      // Expected exception
    }
    
    // Test IndexoutOutOfBound
    // Let the test capture it
    instance.matcher.find();
    instance.group(4);
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