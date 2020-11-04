package parse;

import ast.ASTUtil;
import handle.FindCastLoss;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.MemberRef;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.MethodRefParameter;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DefaultASTVisitor extends ASTVisitor implements IVisitor
{

    public static final Logger logger = LoggerFactory.getLogger(DefaultASTVisitor.class);
    public Block lastBlock = null;

    public TreeMap<String, MethodMappingInfo> methodMap = new TreeMap<>();
    public Map<String, ImportChange> importChanges = new TreeMap<>();
    public DefaultFileASTRequester requester = null;
    public Map<String, Boolean> changeFlag = new TreeMap<>();
    public String currentSource = null;

    public DefaultASTVisitor(TreeMap<String, MethodMappingInfo> methodMap)
    {
        this.methodMap.putAll(methodMap);
    }

    public DefaultASTVisitor()
    {
    }

    public DefaultASTVisitor(TreeMap<String, MethodMappingInfo> methodMap, DefaultFileASTRequester requester)
    {
        this.requester = requester;
        this.methodMap.putAll(methodMap);
    }

    public boolean visit(Block node)
    {
        lastBlock = node;
        return true;
    }


    public boolean visit(CastExpression node)
    {
        FindCastLoss.save(this, node);
        return true;
    }

    //
    public boolean visit(VariableDeclarationStatement node)
    {
        return true;
    }

    public boolean visit(MethodDeclaration node)
    {
        return true;
    }

    public boolean visit(MethodInvocation node)
    {
        return true;
    }

    public boolean visit(MemberRef node)
    {
        return true;
    }

    public boolean visit(MethodRefParameter node)
    {
        // default implementation: do nothing
        return true;
    }

    @Override
    public boolean visit(CompilationUnit compilationUnit)
    {
        currentSource = requester.getRe_units().get(compilationUnit);
        importChanges.clear();
        return true;
    }

    public void setCurrentModified()
    {
        changeFlag.put(currentSource, true);
    }

    @Override
    public void endVisit(CompilationUnit compilationUnit)
    {
        AST ast = compilationUnit.getAST();
        for (ImportChange importChange : importChanges.values())
        {
            if (importChange.type == ImportChangeType.Add)
            {
                ImportDeclaration importDeclaration = ast.newImportDeclaration();
                importDeclaration.setName(ASTUtil.getQualifiedName(ast, importChange.qualifiedName));
                compilationUnit.imports().add(importDeclaration);
            }

        }
        //        Iterator it = compilationUnit.imports().iterator();
        //        while (it.hasNext())
        //        {
        //            Object object = it.next();
        //            if (object instanceof ImportDeclaration)
        //            {
        //                ImportDeclaration declaration = (ImportDeclaration) object;
        //                if (!importChanges.containsKey(declaration.getName().getFullyQualifiedName()) && declaration.resolveBinding() == null)
        //                {
        //                    it.remove();
        //                }
        //            }
        //        }
    }

}
