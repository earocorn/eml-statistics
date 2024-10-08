/*
 * Copyright (c) 2021, Lawrence Livermore National Security, LLC.  All rights reserved.  LLNL-CODE-822850
 * 
 * OFFICIAL USE ONLY – EXPORT CONTROLLED INFORMATION
 * 
 * This work was produced at the Lawrence Livermore National Laboratory (LLNL) under contract no.  DE-AC52-07NA27344 (Contract 44)
 * between the U.S. Department of Energy (DOE) and Lawrence Livermore National Security, LLC (LLNS) for the operation of LLNL.
 * See license for disclaimers, notice of U.S. Government Rights and license terms and conditions.
 */
package gov.llnl.math.internal.matrix;

import gov.llnl.math.DoubleArray;
import gov.llnl.math.matrix.Matrix;
import gov.llnl.math.matrix.MatrixColumnArray;
import gov.llnl.math.matrix.MatrixIterators;
import static gov.llnl.math.matrix.MatrixOps.determineVectorAccess1;
import gov.llnl.math.matrix.MatrixRowArray;

/**
 *
 * @author nelson85
 */
public class VectorOps
{
  public static MatrixRowArray evaluateEachColumn(Matrix matrix, VectorOperator vo)
  {
    MatrixRowArray out=new MatrixRowArray(1, matrix.columns());
    if (determineVectorAccess1(matrix))
      return operateDim1(out, MatrixIterators.newColumnReadIterator(matrix), vo);
    else
      return operateDim2(out, MatrixIterators.newRowReadIterator(matrix), vo);
  }

  public static MatrixColumnArray evaluateEachRow(Matrix matrix, VectorOperator vo)
  {
    MatrixColumnArray out=new MatrixColumnArray(matrix.rows(), 1);
    if (determineVectorAccess1(matrix))
      return operateDim2(out, MatrixIterators.newColumnReadIterator(matrix), vo);
    else
      return operateDim1(out, MatrixIterators.newRowReadIterator(matrix), vo);
  }

  private static <T extends MatrixArrayBase> T operateDim1(T result, MatrixIterators.VectorIterator iter, VectorOperator vo)
  {
    double[] out = result.toArray();
    while (iter.hasNext())
    {
      out[iter.index()] = vo.evaluate(iter.access(), iter.begin(), iter.end());
    }
    return result;
  }

  private static <T extends MatrixArrayBase> T operateDim2(T result, MatrixIterators.VectorIterator iter, VectorOperator vo)
  {
    double[] out = result.toArray();
    while (iter.hasNext())
    {
      vo.evaluate(out, iter.access(), iter.begin(), iter.end());
    }
    return result;
  }

  /**
   * Operate on a matrix as a collection of vectors.
   */
  public interface VectorOperator
  {
    /**
     * Operate as a matrix in the primary direction.
     *
     * @param in
     * @param begin
     * @param end
     * @return the result of operating on this vector.
     */
    double evaluate(double[] in, int begin, int end);

    /**
     * Operate as a matrix in the transpose direction.
     *
     * @param out
     * @param in
     * @param begin
     * @param end
     */
    void evaluate(double[] out, double[] in, int begin, int end);
  }

  final static public class Sum implements VectorOperator
  {
    @Override
    public double evaluate(double[] in, int begin, int end)
    {
      return DoubleArray.sumRange(in, begin, end);
    }

    @Override
    public void evaluate(double[] out, double[] in, int begin, int end)
    {
      DoubleArray.addAssign(out, 0, in, begin, end - begin);
    }
  }

  final static public class SumAbsolute implements VectorOperator
  {
    @Override
    public double evaluate(double[] in, int begin, int end)
    {
      double out = 0;
      for (; begin < end; ++begin)
        out += Math.abs(in[begin]);
      return out;
    }

    @Override
    public void evaluate(double[] out, double[] in, int begin, int end)
    {
      for (int i = 0; begin < end; ++begin, ++i)
        out[i] += Math.abs(in[begin]);
    }
  }

  final static public class SumSquare implements VectorOperator
  {
    @Override
    public double evaluate(double[] in, int begin, int end)
    {
      return DoubleArray.sumSqrRange(in, begin, end);
    }

    @Override
    public void evaluate(double[] out, double[] in, int begin, int end)
    {
      for (int i = 0; begin < end; ++begin, ++i)
        out[i] += in[begin] * in[begin];
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