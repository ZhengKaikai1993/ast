package parse;

import java.util.Map;
import java.util.TreeMap;

public class MethodMappingInfo
{

    private String targetMethodName = "MISSING";
    private Map <Integer, Integer> paraMap = new TreeMap <>();

    public MethodMappingInfo(String name)
    {
        this.targetMethodName = name;
    }

    public String getTargetMethodName()
    {
        return targetMethodName;
    }

    public Map <Integer, Integer> getParaMap()
    {
        return paraMap;
    }

}
