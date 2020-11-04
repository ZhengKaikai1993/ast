package handle;

import ast.ASTUtil;
import ast.FindResult;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.SimpleName;
import parse.DefaultASTVisitor;
import parse.ImportChange;
import parse.ImportChangeType;

public class ReplaceEquals
{

    public static void replaceEquals(InfixExpression node, DefaultASTVisitor visitor)
    {
        if (node.getOperator().toString().equals("==") || node.getOperator().toString().equals("!="))
        {
            Expression leftExpression = node.getLeftOperand();
            Expression rightExpression = node.getRightOperand();
            if (leftExpression.resolveTypeBinding() != null && rightExpression.resolveTypeBinding() != null)
            {
                String leftClassName = leftExpression.resolveTypeBinding().getQualifiedName();
                String rightClassName = rightExpression.resolveTypeBinding().getQualifiedName();
                if (leftClassName.equals("java.lang.String") && rightClassName.equals("java.lang.String"))
                {
                    FindResult findResult = ASTUtil.findObjectInOtherObject(node, node.getParent());
                    if (findResult.hasFind())
                    {
                        System.out.println("process  ：" + node.toString());
                        if (node.getOperator().toString().equals("=="))
                        {
                            ASTNode belongObject = (ASTNode) findResult.getBelong();
                            MethodInvocation newInvocation = ASTUtil.getObject(belongObject.getAST(), MethodInvocation.class);
                            SimpleName simpleMethodName = ASTUtil.getObject(node.getAST(), SimpleName.class);
                            SimpleName simpleClassName = ASTUtil.getObject(node.getAST(), SimpleName.class);
                            simpleMethodName.setIdentifier("equals");
                            simpleClassName.setIdentifier("Objects");
                            newInvocation.setName(simpleMethodName);
                            newInvocation.setExpression(simpleClassName);
                            Object leftCloned = ASTUtil.getClonedASTNode(leftExpression, belongObject.getAST());
                            Object rightCloned = ASTUtil.getClonedASTNode(rightExpression, belongObject.getAST());
                            newInvocation.arguments().add(leftCloned);
                            newInvocation.arguments().add(rightCloned);
                            findResult.apply(newInvocation);
                        }
                        else
                        {
                            ASTNode belongObject = (ASTNode) findResult.getBelong();
                            MethodInvocation newInvocation = ASTUtil.getObject(belongObject.getAST(), MethodInvocation.class);
                            SimpleName simpleMethodName = ASTUtil.getObject(node.getAST(), SimpleName.class);
                            SimpleName simpleClassName = ASTUtil.getObject(node.getAST(), SimpleName.class);
                            simpleMethodName.setIdentifier("equals");
                            simpleClassName.setIdentifier("Objects");
                            newInvocation.setName(simpleMethodName);
                            newInvocation.setExpression(simpleClassName);
                            Object leftCloned = ASTUtil.getClonedASTNode(leftExpression, belongObject.getAST());
                            Object rightCloned = ASTUtil.getClonedASTNode(rightExpression, belongObject.getAST());
                            newInvocation.arguments().add(leftCloned);
                            newInvocation.arguments().add(rightCloned);
                            PrefixExpression prefixExpression = ASTUtil.getObject(belongObject.getAST(), PrefixExpression.class);
                            prefixExpression.setOperator(PrefixExpression.Operator.NOT);
                            prefixExpression.setOperand(newInvocation);
                            findResult.apply(prefixExpression);

                        }
                    }
                    String qualifiedName = "java.util.Objects";
                    visitor.importChanges.put(qualifiedName, new ImportChange(ImportChangeType.Add, qualifiedName));
                    visitor.setCurrentModified();

                }

            }
        }
    }
}
