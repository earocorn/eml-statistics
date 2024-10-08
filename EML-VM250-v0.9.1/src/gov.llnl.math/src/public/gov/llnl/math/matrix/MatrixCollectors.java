/*
 * Copyright (c) 2021, Lawrence Livermore National Security, LLC.  All rights reserved.  LLNL-CODE-822850
 * 
 * OFFICIAL USE ONLY – EXPORT CONTROLLED INFORMATION
 * 
 * This work was produced at the Lawrence Livermore National Laboratory (LLNL) under contract no.  DE-AC52-07NA27344 (Contract 44)
 * between the U.S. Department of Energy (DOE) and Lawrence Livermore National Security, LLC (LLNS) for the operation of LLNL.
 * See license for disclaimers, notice of U.S. Government Rights and license terms and conditions.
 */
package gov.llnl.math.matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

/**
 *
 * @author nelson85
 */
public class MatrixCollectors
{

  /** Collect a set of double arrays into a column matrix.
   * 
   * @return 
   */
  public static Collector<double[], ?, Matrix.ColumnAccess> asColumns()
  {
    return new ListCollector<double[], Matrix.ColumnAccess>()
    {
      @Override
      public Function<List<double[]>, Matrix.ColumnAccess> finisher()
      {
        return (p) -> MatrixFactory.newColumnMatrix(p);
      }
    };
  }

  public static Collector<double[], ?, Matrix.RowAccess> asRows()
  {
    return new ListCollector<double[], Matrix.RowAccess>()
    {
      @Override
      public Function<List<double[]>, Matrix.RowAccess> finisher()
      {
        return (p) -> MatrixFactory.newRowMatrix(p);
      }
    };
  }

  private static abstract class ListCollector<T1, T> implements Collector<T1, List<T1>, T>
  {
    @Override
    public Supplier<List<T1>> supplier()
    {
      return () -> new ArrayList<>();
    }

    @Override
    public BiConsumer<List<T1>, T1> accumulator()
    {
      return (p1, p2) -> p1.add(p2);
    }

    @Override
    public BinaryOperator<List<T1>> combiner()
    {
      return (p1, p2) ->
      {
        p1.addAll(p2);
        return p1;
      };
    }

    @Override
    public Set<Collector.Characteristics> characteristics()
    {
      return Collections.emptySet();
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