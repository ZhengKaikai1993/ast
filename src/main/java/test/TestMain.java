package test;

import file.FileTool;
import handle.FindCastLoss;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TreeMap;
import java.util.TreeSet;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.text.edits.TextEdit;
import parse.ASTHandler;
import parse.DefaultASTVisitor;
import parse.DefaultFileASTRequester;
import parse.MethodMappingInfo;

public class TestMain
{

    public static void main(String[] args) throws Exception
    {
        TreeMap<String, MethodMappingInfo> methodMap = new TreeMap<>();
//        methodMap.put("org.eclipse.jetty.util.ajax.JSON.parse",new MethodMappingInfo("gameplay.gamerule.debugs.JSON.parse"));
        TreeSet<String> sourceDir = new TreeSet<>();
        sourceDir.add(new File("F:\\work\\gameserver\\").getCanonicalPath());
        //        sourceDir.add(new File("file/src/main/java").getCanonicalPath());
        //        sourceDir.add(new File("src/main/java").getCanonicalPath());
        TreeSet<String> classPathFile = new TreeSet<>();
        String libDir = "E:\\work\\gameserver\\libs\\";
//        String libDir = "libs";
        FileTool.getAllFiles(new File(libDir), file ->
            {
                try
                {
                    classPathFile.add(file.getCanonicalPath());
                } catch (IOException e)
                {
                    e.printStackTrace();
                }
                return 0;
            }, file ->
                file.getName().endsWith(".jar")
        );
        DefaultFileASTRequester requester = ASTHandler.parseFiles(sourceDir, classPathFile);
        DefaultASTVisitor visitor = new DefaultASTVisitor(methodMap, requester);
        for (String source : requester.getUnits().keySet())
        {
            CompilationUnit unit = requester.getUnits().get(source);
            unit.accept(visitor);
            AST ast = unit.getAST();
            //        ImportDeclaration importDeclaration = ast.newImportDeclaration();
            //        importDeclaration.setName(ASTUtil.getQualifiedName(ast, "java.lang.String"));
            //        unit.imports().add(importDeclaration);
            final ASTRewrite rewriter = ASTRewrite.create(ast);
            //获取当前文档源
            //        final Document document = new Document();
            //计算您对编译单元的编辑
            final TextEdit edits = rewriter.rewriteAST();
            //将编辑应用于文档
            IDocument document = new Document(unit.toString());
            edits.apply(document);
            //获取新的源
            if (visitor.changeFlag.containsKey(source))
            {
                System.out.println(source);
                String newSource = document.get();
                FileOutputStream outputStream = new FileOutputStream(source);
                outputStream.write(newSource.getBytes());
                outputStream.close();
            }

        }
        FindCastLoss.output();
    }
}
