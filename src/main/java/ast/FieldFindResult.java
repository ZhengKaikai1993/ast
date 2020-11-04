package ast;

import java.lang.reflect.Field;

public class FieldFindResult implements FindResult
{

    //直接是某个Object的Field
    private Field field;
    private Object object;

    @Override
    public void setFindValue(Object object, Object... para)
    {
        this.field = (Field) para[0];
        this.object = object;
    }

    @Override
    public boolean hasFind()
    {
        return field != null;
    }

    @Override
    public void apply(Object replacedObject)
    {
        try
        {
            field.setAccessible(true);
            if (field.getType().isAssignableFrom(replacedObject.getClass()))
            {
                field.set(object, replacedObject);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @Override
    public Object getBelong()
    {
        return object;
    }

}
