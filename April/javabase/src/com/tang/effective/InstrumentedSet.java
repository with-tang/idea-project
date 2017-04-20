package com.tang.effective;

import java.util.Collection;
import java.util.Set;

/**
 * Created by tang on 2017/4/5.
 */
public class InstrumentedSet<E> extends ForwardingSet<E>  {
    private int addCount=0;
    public InstrumentedSet(Set<E> s)
    {
        super(s);
    }
    public boolean add(E e)
    {
        addCount++;
        return super.add(e);
    }
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size();
        return super.addAll(c);
    }
    public int getAddCount()
    {
        return addCount;
    }

}
