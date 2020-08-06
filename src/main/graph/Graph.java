package main.graph;

import java.util.Iterator;
import java.util.Set;

public interface Graph<E> extends Iterable<E> {
  public int size();

  public int isEmpty();

  public boolean hasEdge(E u, E v);

  public boolean addEdge(E u, E v, E weight);

  public E getWeight(E u, E v);

  public void removeEdge(E u, E v);

  public Set<E> outNeighbors(E v);

  public Iterator<E> iterator();

  public String toString();
}
