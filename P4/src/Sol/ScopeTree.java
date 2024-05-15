package Sol;

import org.antlr.v4.runtime.tree.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScopeTree implements Tree
{
    public record Variable(Type scopedType, int index, boolean global, Variable next){
        public int refCount(){
            int result = 0;
            Variable head = this.next;
            while(head != null){
                head = head.next;
                result++;
            }
            return result;
        }
    }
    private final HashMap<String, Variable> variables;
    private ScopeTree parent;
    private final List<ScopeTree> children;
    private int variableIndex;
    private int offset;

    public ScopeTree()
    {
        this.children = new ArrayList<>();
        this.variables = new HashMap<>();
    }

    public ScopeTree(ScopeTree parent)
    {
        this();
        /*
        Because global memory is stored with GALLOC and GSTORE rather than LALLOC indices get weird,
        Meaning that for any child we need to check if the parent is root, if so, offset from that nodes variables
         */
        this.parent = parent;
        this.offset = this.parent == null ? 0 : this.parent.offset;

        if (this.parent == null)
            return;

        if (this.parent.parent != null)
            this.variableIndex = this.parent.variableIndex;
    }

    public void offset(int offset)
    {
        this.offset += offset;
    }

    public void addChild(ScopeTree child)
    {
        this.children.add(child);
    }

    public Variable getVariableLocal(String identifier)
    {
        return this.variables.get(identifier);
    }

    private static Variable getVariableRecursive(ScopeTree tree, String identifier)
    {
        if (tree == null)
           return null;

        Variable var = tree.getVariableLocal(identifier);
        return var != null ? var : getVariableRecursive(tree.parent, identifier);
    }

    public Variable getVariable(String identifier)
    {
        return getVariableRecursive(this, identifier);
    }

    public void putVariable(String identifier, Type type)
    {
        boolean isRoot = this.parent == null;
        this.variables.put(identifier, new Variable(type, (this.variableIndex++ + this.offset), isRoot,null));
    }

    public void putVariable(String identifier, Type type, Variable ref)
    {
        boolean isRoot = this.parent == null;
        this.variables.put(identifier, new Variable(type, (this.variableIndex++ + this.offset), isRoot,ref));
    }

    public boolean containsVariableLocal(String identifier)
    {
        return this.getVariableLocal(identifier) != null;
    }

    public boolean containsVariable(String identifier)
    {
        return this.getVariable(identifier) != null;
    }

    public ScopeTree getRightmostChild()
    {
        return this.children.get(this.children.size() - 1);
    }

    public int getVariableCount()
    {
        return this.variables.size();
    }

    @Override
    public Tree getParent()
    {
        return this.parent;
    }

    @Override
    public Object getPayload()
    {
        return null;
    }

    @Override
    public Tree getChild(int i)
    {
        return this.children.get(i);
    }

    @Override
    public int getChildCount()
    {
        return this.children.size();
    }

    @Override
    public String toStringTree()
    {
        return "";
    }
}
