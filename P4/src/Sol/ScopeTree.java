package Sol;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.Tree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ScopeTree implements Tree {
    private HashMap<String,Class<?>> variables;
    private ScopeTree parent;
    private List<ScopeTree> children;

    public ScopeTree(){
        this.children = new ArrayList<>();
        this.variables = new HashMap<>();
    }
    public ScopeTree(ScopeTree parent){
        this();
        this.parent = parent;
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

    public boolean containsVariableLocal(String identifier){
        return this.getVariableLocal(identifier) != null;
    }

    public boolean containsVariable(String identifier){
        return this.getVariable(identifier) != null;
    }
    public ScopeTree getRightmostChild(){
        return this.children.get(this.children.size()-1);
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
