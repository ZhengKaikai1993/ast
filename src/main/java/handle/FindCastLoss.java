package handle;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.eclipse.jdt.core.dom.CastExpression;
import parse.DefaultASTVisitor;

public class FindCastLoss
{

    public static final Map<String, List<String>> files = new TreeMap<>();

    public static void save(DefaultASTVisitor defaultASTVisitor, CastExpression node)
    {
        if (node.getType().toString().equals("int"))
        {
            List<String> casts = files.getOrDefault(defaultASTVisitor.currentSource, new ArrayList<>());
            casts.add(node.toString());
            files.put(defaultASTVisitor.currentSource, casts);
        }
    }

    public static void output()
    {
        try
        {
            FileOutputStream outputStream = new FileOutputStream("E:\\casts.txt");
            StringBuilder builder = new StringBuilder();
            for (String file : files.keySet())
            {
                for (String code : files.get(file))
                {
                    builder.append(file);
                    builder.append("----->");
                    builder.append(code);
                    builder.append("\n");
                }
            }
            outputStream.write(builder.toString().getBytes());
            outputStream.close();
        } catch (Exception e)
        {
            e.printStackTrace();

        }
    }
}
