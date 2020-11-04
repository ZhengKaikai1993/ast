package parse;

import file.FileTool;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import xin.xihc.utils.common.FileUtil;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

public class ASTHandler
{
    public static Map <String, String> options = JavaCore.getOptions();

    static
    {
        options.put(JavaCore.COMPILER_COMPLIANCE, JavaCore.VERSION_13);
        options.put(JavaCore.COMPILER_CODEGEN_TARGET_PLATFORM,
                JavaCore.VERSION_13);
        options.put(JavaCore.COMPILER_SOURCE, JavaCore.VERSION_13);
    }

    public static CompilationUnit parseFile(File file)
    {
        try
        {
            String content = FileUtil.readFileToStr(file);
            return ASTHandler.parseContent(content);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    public static CompilationUnit parseContent(String content)
    {
        ASTVisitor visitor = new DefaultASTVisitor();
        ASTParser _parser = ASTParser.newParser(AST.JLS13);
        _parser.setKind(ASTParser.K_COMPILATION_UNIT);
        _parser.setCompilerOptions(options);
        _parser.setSource(content.toCharArray());
        _parser.setResolveBindings(true);
        _parser.setStatementsRecovery(true);
        _parser.setBindingsRecovery(true);
        CompilationUnit compilationUnit = (CompilationUnit) _parser.createAST(null);
        compilationUnit.accept(visitor);
        return compilationUnit;
    }

    public static DefaultFileASTRequester parseFiles(Set <String> sourceDirs, Set <String> classPathList)
    {
        //sourcePathEntries 文件夹，压缩包
        //sourceFiles 所有文件
        ArrayList <String> allFiles = new ArrayList <>();
        for (String dir : sourceDirs)
        {
            File dirFile = new File(dir);
            FileTool.getAllFiles(dirFile, file ->
            {
                try
                {
                    allFiles.add(file.getCanonicalPath());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
                return 0;
            }, new Function <File, Boolean>()
            {
                @Override
                public Boolean apply(File file)
                {
                    return file.getName().endsWith(".java");
                }
            });
        }
        String[] sourceFileArray = allFiles.toArray(new String[allFiles.size()]);
        String[] sourceDirArray = sourceDirs.toArray(new String[sourceDirs.size()]);
        String[] classPathArray = classPathList.toArray(new String[classPathList.size()]);
        ASTParser _parser = ASTParser.newParser(AST.JLS13);
        _parser.setKind(ASTParser.K_COMPILATION_UNIT);
        _parser.setCompilerOptions(options);
        _parser.setResolveBindings(true);
        //        _parser.setStatementsRecovery(true);
        //        _parser.setBindingsRecovery(true);
        _parser.setEnvironment(classPathArray, sourceDirArray, null, true);
        DefaultFileASTRequester fileACTRequester = new DefaultFileASTRequester();
        _parser.createASTs(sourceFileArray, null, new String[0], fileACTRequester, null);
        return fileACTRequester;

    }
}
