package ast;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.SimpleName;
import reflect.ReflectionUtil;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

public class ASTUtil
{
    public static Name getQualifiedName(AST ast, String name)
    {
        String ss[] = name.split("\\.");
        if (ss.length == 1)
        {
            SimpleName simpleName = (SimpleName) getObject(ast, SimpleName.class);
            simpleName.setIdentifier(name);
            return simpleName;
        }
        else
        {
            QualifiedName qualifiedName = (QualifiedName) getObject(ast, QualifiedName.class);
            SimpleName simpleName = (SimpleName) getObject(ast, SimpleName.class);
            simpleName.setIdentifier(ss[ss.length - 1]);
            int index = name.lastIndexOf(".");
            String prfix = name.substring(0, index);
            qualifiedName.setQualifier(getQualifiedName(ast, prfix));
            qualifiedName.setName(simpleName);
            return qualifiedName;
        }
    }

    public static <T> T getObject(AST ast, Class <T> tClass)
    {
        return ReflectionUtil.newObject(tClass, new Class[]{AST.class}, new Object[]{ast});
    }

    /**
     * 从parent的所有Field中搜索
     *
     * @param one
     * @param other
     * @return
     */
    public static FindResult findObjectInOtherObject(Object one, Object other)
    {
        FieldFindResult fieldFindResult = new FieldFindResult();
        try
        {
            ArrayList <Field> fields = ReflectionUtil.getAllFields(other.getClass());
            for (Field field : fields)
            {
                field.setAccessible(true);
                //不处理静态对象
                if (Modifier.isStatic(field.getModifiers()))
                {
                    continue;
                }
                Object fieldValue = field.get(other);
                if (!List.class.isAssignableFrom(field.getType()))
                {
                    if (field.getType().isAssignableFrom(one.getClass()))
                    {
                        if (fieldValue == one)
                        {
                            fieldFindResult.setFindValue(other, field);
                            return fieldFindResult;
                        }
                    }
                }
                else
                {
                    List collection = (List) fieldValue;
                    for (Object item : collection)
                    {
                        //元素本身
                        if (item == one)
                        {
                            ListFindResult listFindResult = new ListFindResult();
                            listFindResult.setFindValue(collection, collection.indexOf(item), other);
                            return listFindResult;
                        }
                        //在集合里面的对象内部查找
                        FindResult interFindResult = findObjectInOtherObject(one, item);
                        if (interFindResult.hasFind())
                        {
                            return interFindResult;
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
            return fieldFindResult;
        }
        return fieldFindResult;
    }

    public static Object getClonedASTNode(ASTNode paraNode, AST ast)
    {
        Method cloneMethod = getCloneMethod(paraNode.getClass());
        cloneMethod.setAccessible(true);
        Object clonedNode = null;
        try
        {
            clonedNode = cloneMethod.invoke(paraNode, ast);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return clonedNode;
    }

    public static Method getCloneMethod(Class <? extends ASTNode> clazz)
    {
        return ReflectionUtil.findMethod(clazz, "clone", AST.class);
    }
}
