package handle;

import ast.ASTUtil;
import ast.FindResult;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.IMethodBinding;
import org.eclipse.jdt.core.dom.ITypeBinding;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.SimpleName;
import parse.DefaultASTVisitor;
import parse.ImportChange;
import parse.ImportChangeType;
import parse.MethodMappingInfo;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ReplaceStaticMethod
{
    public static void replaceMethodToAnotherMethod(MethodInvocation node, DefaultASTVisitor visitor)
    {
        IMethodBinding methodBinding = node.resolveMethodBinding();
        if (methodBinding != null)
        {
            ITypeBinding declaringClass = methodBinding.getDeclaringClass();
            String className = declaringClass.getQualifiedName();
            String qualifiedMethodName = className + "." + methodBinding.getName();
            processClassMethod(node, qualifiedMethodName, methodBinding, visitor);
        }
    }

    public static void processClassMethod(MethodInvocation node, String qualifiedMethodName, IMethodBinding methodBinding, DefaultASTVisitor visitor)
    {
        if (visitor.methodMap.containsKey(qualifiedMethodName))
        {
            MethodMappingInfo mappingInfo = visitor.methodMap.get(qualifiedMethodName);
            FindResult findResult = ASTUtil.findObjectInOtherObject(node, node.getParent());
            if (findResult.hasFind())
            {
                //
                DefaultASTVisitor.logger.info("replace source method {} to {}", qualifiedMethodName, mappingInfo.getTargetMethodName());
                Object belongObject = findResult.getBelong();
                if (belongObject instanceof ASTNode)
                {
                    if (Modifier.isStatic(methodBinding.getModifiers()))
                    {
                        processStaticMethodInvocationNode(node, mappingInfo, findResult, (ASTNode) belongObject, visitor);

                    }
                    else
                    {
                        //某个对象的方法调用无法转换为另一个对象的方法调用，因为此时没有另外一个类型的对象
                        //                        processStaticMethodInvocationNode(node, mappingInfo, findResult, (ASTNode) belongObject);
                    }
                }
            }
            else
            {
                DefaultASTVisitor.logger.info("handle source method {} : not find", qualifiedMethodName);

            }
        }
    }

    public static void processStaticMethodInvocationNode(MethodInvocation node, MethodMappingInfo mappingInfo, FindResult findResult, ASTNode belongObject, DefaultASTVisitor visitor)
    {
        MethodInvocation newInvocation = ASTUtil.getObject(belongObject.getAST(), MethodInvocation.class);
        SimpleName simpleMethodName = ASTUtil.getObject(node.getAST(), SimpleName.class);
        SimpleName simpleClassName = ASTUtil.getObject(node.getAST(), SimpleName.class);
        String qualifiedName = mappingInfo.getTargetMethodName();
        int index = qualifiedName.lastIndexOf(".");
        String methodName = qualifiedName.substring(index + 1);
        String qualifier = qualifiedName.substring(0, index);
        String className = qualifier.substring(qualifier.lastIndexOf(".") + 1);
        visitor.importChanges.put(qualifier, new ImportChange(ImportChangeType.Add, qualifier));
        simpleMethodName.setIdentifier(methodName);
        simpleClassName.setIdentifier(className);
        newInvocation.setName(simpleMethodName);
        newInvocation.setExpression(simpleClassName);
        List arguments = newInvocation.arguments();
        if (mappingInfo.getParaMap().size() == 0)
        {
            //参数原样搬过去
            for (int i = 0; i < node.arguments().size(); i++)
            {
                Object object = node.arguments().get(i);
                if (object instanceof ASTNode)
                {
                    ASTNode paraNode = (ASTNode) object;
                    Object clonedNode = ASTUtil.getClonedASTNode(paraNode, node.getAST());
                    if (clonedNode != null)
                    {
                        arguments.add(i, clonedNode);
                    }
                }
            }

        }
        else
        {
            Iterator <Map.Entry <Integer, Integer>> it = mappingInfo.getParaMap().entrySet().iterator();
            while (it.hasNext())
            {
                Map.Entry <Integer, Integer> pair = it.next();
                int sourceParaIndex = pair.getKey();
                int targetParaIndex = pair.getValue();
                if (node.arguments().get(sourceParaIndex) instanceof ASTNode)
                {
                    ASTNode paraNode = (ASTNode) node.arguments().get(sourceParaIndex);
                    Object clonedNode = ASTUtil.getClonedASTNode(paraNode, node.getAST());
                    if (clonedNode != null)
                    {
                        arguments.add(targetParaIndex, clonedNode);
                    }
                }
            }
        }
        findResult.apply(newInvocation);
    }
}
