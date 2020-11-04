package reflect;

import test.AnimationConst;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;

public class ReflectionUtil
{
    public static <T> T newObject(Class <T> tClass, Class[] argTypeClass, Object[] args)
    {
        try
        {
            Constructor <T> constructor2 = tClass.getDeclaredConstructor(argTypeClass);
            if (constructor2 != null)
            {
                constructor2.setAccessible(true);
                return constructor2.newInstance(args);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static ArrayList <Field> getAllFields(Class clazz)
    {
        ArrayList <Field> fields = new ArrayList <>();
        for (Class current = clazz; current != Object.class; current = current.getSuperclass())
        {
            try
            {
                Collections.addAll(fields, current.getDeclaredFields());
            }
            catch (Exception e)
            {
            }
        }
        return fields;
    }

    public static Method findMethod(Class clazz, String methodName, Class... argumentTypes)
    {
        for (Class current = clazz; current != Object.class; current = current.getSuperclass())
        {
            try
            {
                Method method = current.getDeclaredMethod(methodName,argumentTypes);
                if (method != null  )
                {
                    return method;
                }
            }
            catch (Exception e)
            {
            }
        }
        return null;
    }

    private static boolean paraTypesMatch(Class <?>[] parameterTypes, Class[] argumentTypes)
    {
        if (parameterTypes != null && argumentTypes == null)
        {
            return false;
        }
        if (parameterTypes == null && argumentTypes != null)
        {
            return false;
        }
        if (parameterTypes == null && argumentTypes == null)
        {
            return true;
        }
        if (parameterTypes.length != argumentTypes.length)
        {
            for (int i = 0; i < parameterTypes.length; i++)
            {
                if (parameterTypes[i] != argumentTypes[i])
                {
                    return false;
                }
            }
        }
        return true;
    }
    public static AnimationConst fun1()
    {
        return null;
    }
}