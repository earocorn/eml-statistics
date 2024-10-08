/*
 * Copyright (c) 2021, Lawrence Livermore National Security, LLC.  All rights reserved.  LLNL-CODE-822850
 * 
 * OFFICIAL USE ONLY – EXPORT CONTROLLED INFORMATION
 * 
 * This work was produced at the Lawrence Livermore National Laboratory (LLNL) under contract no.  DE-AC52-07NA27344 (Contract 44)
 * between the U.S. Department of Energy (DOE) and Lawrence Livermore National Security, LLC (LLNS) for the operation of LLNL.
 * See license for disclaimers, notice of U.S. Government Rights and license terms and conditions.
 */
package gov.llnl.utility;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 * Implementation of the Expandable. This should be used as a base class or a
 * member for attribute containing data structures.
 *
 * @see Expandable
 */
public class ExpandableObject implements Expandable, Cloneable, Serializable
{
  final TreeMap<String, Serializable> attributeMap = new TreeMap<>();

  public ExpandableObject()
  {
  }

  public ExpandableObject(Expandable ai)
  {
    if (ai == null)
      throw new NullPointerException("Cannot copy null object");
    attributeMap.putAll(ai.getAttributes());
  }

  @Override
  public <T extends Serializable> void setAttribute(String name, T value)
  {
    this.attributeMap.put(name, value);
  }

  @Override
  public Serializable getAttribute(String name)
  {
    return this.attributeMap.get(name);
  }

  @Override
  public Map<String, Serializable> getAttributes()
  {
    return this.attributeMap;
  }

}

//<editor-fold desc="attributes">
/*
  Code to copy and paste into a class that needs to implement AttributesInterface
  DO NOT REMOVE
  =============================================================================

  @Override
  public boolean hasAttribute(String name)
  {
    if (attributes == null)
      return false;
    return attributes.hasAttribute(name);
  }

  @Override
  public void removeAttribute(String name)
  {
    if (attributes != null)
      attributes.removeAttribute(name);
  }

  @Override
  public <T extends Serializable> void setAttribute(String name, T value)
  {
    if (attributes == null)
      attributes = new ExpandableObject();
    attributes.setAttribute(name, value);
  }

  @Override
  public Object getAttribute(String name)
  {
    if (attributes == null)
      return null;
    return attributes.getAttribute(name);
  }

  @Override
  public <T> T getAttribute(String name, Class<T> cls) throws InstantiationException
  {
    if (attributes == null)
      return null;
    return attributes.getAttribute(name, cls);
  }

  @Override
  public void copyAttributes(Expandable ai)
  {
    if (attributes == null)
      attributes = new ExpandableObject();
    attributes.copyAttributes(ai);
  }

  @Override
  public void clearAttributes()
  {
    attributes = null;
  }

  @Override
  public Collection<String> getAttributeKeys()
  {
    if (attributes == null)
      return new ArrayList<String>();
    return attributes.getAttributeKeys();
  }

 */
//</editor-fold>


/*
 * Copyright (c) 2021, Lawrence Livermore National Security, LLC.  All rights reserved.  LLNL-CODE-822850
 * 
 * OFFICIAL USE ONLY – EXPORT CONTROLLED INFORMATION
 * 
 * This work was produced at the Lawrence Livermore National Laboratory (LLNL) under contract no.  DE-AC52-07NA27344 (Contract 44)
 * between the U.S. Department of Energy (DOE) and Lawrence Livermore National Security, LLC (LLNS) for the operation of LLNL.
 * See license for disclaimers, notice of U.S. Government Rights and license terms and conditions.
 */