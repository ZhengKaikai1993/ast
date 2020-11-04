package parse;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FileASTRequestor;
import org.eclipse.jdt.core.dom.IBinding;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class DefaultFileASTRequester extends FileASTRequestor
{
    private Map <String, CompilationUnit> units = new TreeMap <>();
    private Map <CompilationUnit, String> re_units = new HashMap <>();
    private Map <String, IBinding> bindingKeys = new TreeMap <>();

    @Override
    public void acceptAST(String sourceFilePath, CompilationUnit ast)
    {
        super.acceptAST(sourceFilePath, ast);
        units.put(sourceFilePath, ast);
        re_units.put(ast, sourceFilePath);
    }

    @Override
    public void acceptBinding(String bindingKey, IBinding binding)
    {
        super.acceptBinding(bindingKey, binding);
        bindingKeys.put(bindingKey, binding);
    }

    public Map <String, CompilationUnit> getUnits()
    {
        return units;
    }

    public Map <String, IBinding> getBindingKeys()
    {
        return bindingKeys;
    }

    public Map <CompilationUnit, String> getRe_units()
    {
        return re_units;
    }
}
