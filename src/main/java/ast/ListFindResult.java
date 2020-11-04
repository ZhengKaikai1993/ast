package ast;

import java.util.List;

public class ListFindResult implements FindResult
{
    //是某个List里面的对象,返回索引和集合本身
    private List list;
    private int index;
    private Object belong;

    @Override
    public void setFindValue(Object object, Object... para)
    {
        this.index = (int) para[0];
        this.belong = para[1];
        this.list = (List) object;
    }

    @Override
    public boolean hasFind()
    {
        return list != null;
    }

    @Override
    public void apply(Object replacedObject)
    {
        list.remove(index);
        list.add(index, replacedObject);

    }

    @Override
    public Object getBelong()
    {
        return belong;
    }
}
