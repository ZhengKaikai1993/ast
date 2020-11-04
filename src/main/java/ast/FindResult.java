package ast;

public interface FindResult
{

    void setFindValue(Object object, Object... para);

    boolean hasFind();

    void apply(Object replacedObject);

    Object getBelong();
}
