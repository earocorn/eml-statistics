/*
 * Copyright (c) 2021, Lawrence Livermore National Security, LLC.  All rights reserved.  LLNL-CODE-822850
 * 
 * OFFICIAL USE ONLY – EXPORT CONTROLLED INFORMATION
 * 
 * This work was produced at the Lawrence Livermore National Laboratory (LLNL) under contract no.  DE-AC52-07NA27344 (Contract 44)
 * between the U.S. Department of Energy (DOE) and Lawrence Livermore National Security, LLC (LLNS) for the operation of LLNL.
 * See license for disclaimers, notice of U.S. Government Rights and license terms and conditions.
 */
package gov.llnl.math;

import java.util.ArrayList;

/**
 *
 * @author nelson85
 */
public class MathExceptions
{
  /**
   * This is the base class for all exceptions thrown by math library.
   */
  static public class MathException extends RuntimeException
  {
    ArrayList<Object> context = null;

    public MathException()
    {
    }

    public MathException(String s)
    {
      super(s);
    }

  }

  /**
   * This exception is thrown when the requested interpolation point is outside
   * of the function domain.
   */
  static public class DomainException extends MathException
  {
    public DomainException(String s)
    {
      super(s);
    }

  }

  /**
   * This exception is thrown when the requested interpolation point is outside
   * of the function range. This is used when computing inverse functions.
   */
  static public class RangeException extends MathException
  {
    public RangeException(String s)
    {
      super(s);
    }

  }

  /**
   * This exception is thrown when the size of the operands are incompatible.
   */
  static public class WriteAccessException extends MathException
  {
    public WriteAccessException(String s)
    {
      super(s);
    }

    public WriteAccessException()
    {
    }

  }

  /**
   * This exception is thrown whenever a matrix operation would change the size
   * of a matrix, but the matrix is fixed.
   */
  static public class ResizeException extends MathException
  {
    public ResizeException(String s)
    {
      super(s);
    }

    public ResizeException()
    {
    }

  }

  /**
   * This exception is thrown when the size of the operands are incompatible.
   */
  static public class SizeException extends MathException
  {
    public SizeException(String s)
    {
      super(s);
    }

    public SizeException()
    {
    }

  }

  /**
   * This exception is thrown if the operation could not be completed because
   * the algorithm failed to converge.
   */
  static public class ConvergenceException extends MathException
  {
    public ConvergenceException(String s)
    {
      super(s);
    }
  }

  /**
   * This exception is thrown if a matrix division is singular.
   */
  static public class SingularException extends MathException
  {
    public SingularException(String s)
    {
      super(s);
    }
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