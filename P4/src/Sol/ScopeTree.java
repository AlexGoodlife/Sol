package Sol;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScopeTree implements Tree {
    private HashMap<String,Class<?>> variables;
    private final ScopeTree parent;
    private List<ScopeTree> children;

    public ScopeTree(ScopeTree parent){
        this.parent = parent;
        this.children = new ArrayList<>();
        this.variables = new HashMap<>();
    }

    public void addChild(ScopeTree child){
        this.children.add(child);
    }

    public Class<?> getVariableLocal(String identifier){
        return this.variables.get(identifier);
    }
    private static Class<?> getVariableRecursive(ScopeTree tree, String identifier){
        if(tree == null){
           return null;
        }
        Class<?> type =  tree.getVariableLocal(identifier);
        return type != null ? type : getVariableRecursive(tree.parent,identifier);
    }

    public Class<?> getVariable(String identifier){
        return getVariableRecursive(this,identifier);
    }
    public Class<?> putVariable(String identifier, Class<?> type){
        return this.variables.put(identifier,type);
    }
    @Override
    public Tree getParent() {
        return this.parent;
    }

    @Override
    public Object getPayload() {
        return null;
    }

    @Override
    public Tree getChild(int i) {
        return this.children.get(i);
    }

    @Override
    public int getChildCount() {
        return this.children.size();
    }

    @Override
    public String toStringTree() {
        return "";
    }
}
