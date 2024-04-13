package Tasm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class ConstantPool implements Iterable<Value>
{
    private final ArrayList<Value> constantPool;
    private final HashMap<Value, Integer> constantPoolChecker;

    public ConstantPool()
    {
        this.constantPool = new ArrayList<>();
        this.constantPoolChecker = new HashMap<>();
    }

    public ArrayList<Value> getConstantPool()
    {
        return this.constantPool;
    }

    public int addIfAbsent(Value v)
    {
        Integer index = this.constantPoolChecker.get(v);
        if (index == null)
        {
            this.constantPool.add(v);
            index = this.constantPool.size() - 1;
            this.constantPoolChecker.put(v, index);
        }
        return index;
    }

    public Value get(int index)
    {
        return this.constantPool.get(index);
    }

    @Override
    public String toString()
    {
        return this.constantPool.toString();
    }

    @Override
    public Iterator<Value> iterator()
    {
        return this.constantPool.iterator();
    }
}
