/*
 * Copyright (c) 2021, Lawrence Livermore National Security, LLC.  All rights reserved.  LLNL-CODE-822850
 * 
 * OFFICIAL USE ONLY – EXPORT CONTROLLED INFORMATION
 * 
 * This work was produced at the Lawrence Livermore National Laboratory (LLNL) under contract no.  DE-AC52-07NA27344 (Contract 44)
 * between the U.S. Department of Energy (DOE) and Lawrence Livermore National Security, LLC (LLNS) for the operation of LLNL.
 * See license for disclaimers, notice of U.S. Government Rights and license terms and conditions.
 */
package gov.llnl.math.graph.primatives;

/**
 *
 * @author nelson85
 */
public abstract class Digraph<NodeType extends DigraphNode, EdgeType extends EdgeBase> extends GraphBase<NodeType, EdgeType>
{
  public Digraph()
  {
  }

  @Override
  public void dispose()
  {
  }

  @Override
  public EdgeType link(NodeType parent, NodeType child)
  {
    if (parent == null || child == null)
      return null;
    EdgeType e = this.allocateEdge();
    parent.appendChild(e);
    child.appendParent(e);
    return e;
  }
};


/*
 * Copyright (c) 2021, Lawrence Livermore National Security, LLC.  All rights reserved.  LLNL-CODE-822850
 * 
 * OFFICIAL USE ONLY – EXPORT CONTROLLED INFORMATION
 * 
 * This work was produced at the Lawrence Livermore National Laboratory (LLNL) under contract no.  DE-AC52-07NA27344 (Contract 44)
 * between the U.S. Department of Energy (DOE) and Lawrence Livermore National Security, LLC (LLNS) for the operation of LLNL.
 * See license for disclaimers, notice of U.S. Government Rights and license terms and conditions.
 */