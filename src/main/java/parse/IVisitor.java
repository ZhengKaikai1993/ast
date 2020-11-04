package parse;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeMemberDeclaration;
import org.eclipse.jdt.core.dom.AnonymousClassDeclaration;
import org.eclipse.jdt.core.dom.ArrayAccess;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayInitializer;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.AssertStatement;
import org.eclipse.jdt.core.dom.Assignment;
import org.eclipse.jdt.core.dom.Block;
import org.eclipse.jdt.core.dom.BlockComment;
import org.eclipse.jdt.core.dom.BooleanLiteral;
import org.eclipse.jdt.core.dom.BreakStatement;
import org.eclipse.jdt.core.dom.CastExpression;
import org.eclipse.jdt.core.dom.CatchClause;
import org.eclipse.jdt.core.dom.CharacterLiteral;
import org.eclipse.jdt.core.dom.ClassInstanceCreation;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ConditionalExpression;
import org.eclipse.jdt.core.dom.ConstructorInvocation;
import org.eclipse.jdt.core.dom.ContinueStatement;
import org.eclipse.jdt.core.dom.CreationReference;
import org.eclipse.jdt.core.dom.Dimension;
import org.eclipse.jdt.core.dom.DoStatement;
import org.eclipse.jdt.core.dom.EmptyStatement;
import org.eclipse.jdt.core.dom.EnhancedForStatement;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.ExportsDirective;
import org.eclipse.jdt.core.dom.ExpressionMethodReference;
import org.eclipse.jdt.core.dom.ExpressionStatement;
import org.eclipse.jdt.core.dom.FieldAccess;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ForStatement;
import org.eclipse.jdt.core.dom.IfStatement;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.InstanceofExpression;
import org.eclipse.jdt.core.dom.IntersectionType;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.LabeledStatement;
import org.eclipse.jdt.core.dom.LambdaExpression;
import org.eclipse.jdt.core.dom.LineComment;
import org.eclipse.jdt.core.dom.MarkerAnnotation;
import org.eclipse.jdt.core.dom.MemberRef;
import org.eclipse.jdt.core.dom.MemberValuePair;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.MethodInvocation;
import org.eclipse.jdt.core.dom.MethodRef;
import org.eclipse.jdt.core.dom.MethodRefParameter;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.ModuleDeclaration;
import org.eclipse.jdt.core.dom.ModuleModifier;
import org.eclipse.jdt.core.dom.NameQualifiedType;
import org.eclipse.jdt.core.dom.NormalAnnotation;
import org.eclipse.jdt.core.dom.NullLiteral;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.OpensDirective;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.ParenthesizedExpression;
import org.eclipse.jdt.core.dom.PostfixExpression;
import org.eclipse.jdt.core.dom.PrefixExpression;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.ProvidesDirective;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.QualifiedType;
import org.eclipse.jdt.core.dom.RequiresDirective;
import org.eclipse.jdt.core.dom.ReturnStatement;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.SingleMemberAnnotation;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.SuperConstructorInvocation;
import org.eclipse.jdt.core.dom.SuperFieldAccess;
import org.eclipse.jdt.core.dom.SuperMethodInvocation;
import org.eclipse.jdt.core.dom.SuperMethodReference;
import org.eclipse.jdt.core.dom.SwitchCase;
import org.eclipse.jdt.core.dom.SwitchExpression;
import org.eclipse.jdt.core.dom.SwitchStatement;
import org.eclipse.jdt.core.dom.SynchronizedStatement;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.TextBlock;
import org.eclipse.jdt.core.dom.TextElement;
import org.eclipse.jdt.core.dom.ThisExpression;
import org.eclipse.jdt.core.dom.ThrowStatement;
import org.eclipse.jdt.core.dom.TryStatement;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclarationStatement;
import org.eclipse.jdt.core.dom.TypeLiteral;
import org.eclipse.jdt.core.dom.TypeMethodReference;
import org.eclipse.jdt.core.dom.TypeParameter;
import org.eclipse.jdt.core.dom.UnionType;
import org.eclipse.jdt.core.dom.UsesDirective;
import org.eclipse.jdt.core.dom.VariableDeclarationExpression;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;
import org.eclipse.jdt.core.dom.WhileStatement;
import org.eclipse.jdt.core.dom.WildcardType;
import org.eclipse.jdt.core.dom.YieldStatement;

public interface IVisitor
{

    default public void preVisit(ASTNode node)
    {
        // default implementation: do nothing
    }


    default public void postVisit(ASTNode node)
    {
        // default implementation: do nothing
    }


    default public boolean visit(AnnotationTypeDeclaration node)
    {
        return true;
    }


    default public boolean visit(AnnotationTypeMemberDeclaration node)
    {
        return true;
    }


    default public boolean visit(AnonymousClassDeclaration node)
    {
        return true;
    }


    default public boolean visit(ArrayAccess node)
    {
        return true;
    }


    default public boolean visit(ArrayCreation node)
    {
        return true;
    }


    default public boolean visit(ArrayInitializer node)
    {
        return true;
    }


    default public boolean visit(ArrayType node)
    {
        return true;
    }


    default public boolean visit(AssertStatement node)
    {
        return true;
    }


    default public boolean visit(Assignment node)
    {
        return true;
    }


    default public boolean visit(Block node)
    {
        return true;
    }


    default public boolean visit(BlockComment node)
    {
        return true;
    }


    default public boolean visit(BooleanLiteral node)
    {
        return true;
    }


    default public boolean visit(BreakStatement node)
    {
        return true;
    }


    default public boolean visit(CastExpression node)
    {
        return true;
    }


    default public boolean visit(CatchClause node)
    {
        return true;
    }


    default public boolean visit(CharacterLiteral node)
    {
        return true;
    }


    default public boolean visit(ClassInstanceCreation node)
    {
        return true;
    }


    default public boolean visit(CompilationUnit node)
    {
        return true;
    }


    default public boolean visit(ConditionalExpression node)
    {
        return true;
    }


    default public boolean visit(ConstructorInvocation node)
    {
        return true;
    }


    default public boolean visit(ContinueStatement node)
    {
        return true;
    }


    default public boolean visit(CreationReference node)
    {
        return true;
    }


    default public boolean visit(Dimension node)
    {
        return true;
    }


    default public boolean visit(DoStatement node)
    {
        return true;
    }


    default public boolean visit(EmptyStatement node)
    {
        return true;
    }


    default public boolean visit(EnhancedForStatement node)
    {
        return true;
    }


    default public boolean visit(EnumConstantDeclaration node)
    {
        return true;
    }


    default public boolean visit(EnumDeclaration node)
    {
        return true;
    }


    default public boolean visit(ExportsDirective node)
    {
        return true;
    }


    default public boolean visit(ExpressionMethodReference node)
    {
        return true;
    }


    default public boolean visit(ExpressionStatement node)
    {
        return true;
    }


    default public boolean visit(FieldAccess node)
    {
        return true;
    }


    default public boolean visit(FieldDeclaration node)
    {
        return true;
    }


    default public boolean visit(ForStatement node)
    {
        return true;
    }


    default public boolean visit(IfStatement node)
    {
        return true;
    }


    default public boolean visit(ImportDeclaration node)
    {
        return true;
    }


    default public boolean visit(InfixExpression node)
    {
        return true;
    }


    default public boolean visit(Initializer node)
    {
        return true;
    }


    default public boolean visit(InstanceofExpression node)
    {
        return true;
    }


    default public boolean visit(IntersectionType node)
    {
        return true;
    }


    default public boolean visit(Javadoc node)
    {
        // visit tag elements inside doc comments only if requested
        return true;
    }


    default public boolean visit(LabeledStatement node)
    {
        return true;
    }


    default public boolean visit(LambdaExpression node)
    {
        return true;
    }


    default public boolean visit(LineComment node)
    {
        return true;
    }


    default public boolean visit(MarkerAnnotation node)
    {
        return true;
    }


    default public boolean visit(MemberRef node)
    {
        return true;
    }


    default public boolean visit(MemberValuePair node)
    {
        return true;
    }


    default public boolean visit(MethodRef node)
    {
        return true;
    }


    default public boolean visit(MethodRefParameter node)
    {
        return true;
    }


    default public boolean visit(MethodDeclaration node)
    {
        return true;
    }


    default public boolean visit(MethodInvocation node)
    {
        return true;
    }


    default public boolean visit(Modifier node)
    {
        return true;
    }


    default public boolean visit(ModuleDeclaration node)
    {
        return true;
    }


    default public boolean visit(ModuleModifier node)
    {
        return true;
    }


    default public boolean visit(NameQualifiedType node)
    {
        return true;
    }


    default public boolean visit(NormalAnnotation node)
    {
        return true;
    }


    default public boolean visit(NullLiteral node)
    {
        return true;
    }


    default public boolean visit(NumberLiteral node)
    {
        return true;
    }


    default public boolean visit(OpensDirective node)
    {
        return true;
    }


    default public boolean visit(PackageDeclaration node)
    {
        return true;
    }


    default public boolean visit(ParameterizedType node)
    {
        return true;
    }


    default public boolean visit(ParenthesizedExpression node)
    {
        return true;
    }


    default public boolean visit(PostfixExpression node)
    {
        return true;
    }


    default public boolean visit(PrefixExpression node)
    {
        return true;
    }


    default public boolean visit(ProvidesDirective node)
    {
        return true;
    }


    default public boolean visit(PrimitiveType node)
    {
        return true;
    }


    default public boolean visit(QualifiedName node)
    {
        return true;
    }


    default public boolean visit(QualifiedType node)
    {
        return true;
    }


    default public boolean visit(RequiresDirective node)
    {
        return true;
    }


    default public boolean visit(ReturnStatement node)
    {
        return true;
    }


    default public boolean visit(SimpleName node)
    {
        return true;
    }


    default public boolean visit(SimpleType node)
    {
        return true;
    }


    default public boolean visit(SingleMemberAnnotation node)
    {
        return true;
    }


    default public boolean visit(SingleVariableDeclaration node)
    {
        return true;
    }


    default public boolean visit(StringLiteral node)
    {
        return true;
    }


    default public boolean visit(SuperConstructorInvocation node)
    {
        return true;
    }


    default public boolean visit(SuperFieldAccess node)
    {
        return true;
    }


    default public boolean visit(SuperMethodInvocation node)
    {
        return true;
    }


    default public boolean visit(SuperMethodReference node)
    {
        return true;
    }


    default public boolean visit(SwitchCase node)
    {
        return true;
    }


    default public boolean visit(SwitchExpression node)
    {
        return true;
    }


    default public boolean visit(SwitchStatement node)
    {
        return true;
    }


    default public boolean visit(SynchronizedStatement node)
    {
        return true;
    }


    default public boolean visit(TagElement node)
    {
        return true;
    }


    default public boolean visit(TextBlock node)
    {
        return true;
    }


    default public boolean visit(TextElement node)
    {
        return true;
    }


    default public boolean visit(ThisExpression node)
    {
        return true;
    }


    default public boolean visit(ThrowStatement node)
    {
        return true;
    }


    default public boolean visit(TryStatement node)
    {
        return true;
    }


    default public boolean visit(TypeDeclaration node)
    {
        return true;
    }


    default public boolean visit(TypeDeclarationStatement node)
    {
        return true;
    }


    default public boolean visit(TypeLiteral node)
    {
        return true;
    }


    default public boolean visit(TypeMethodReference node)
    {
        return true;
    }


    default public boolean visit(TypeParameter node)
    {
        return true;
    }


    default public boolean visit(UnionType node)
    {
        return true;
    }


    default public boolean visit(UsesDirective node)
    {
        return true;
    }


    default public boolean visit(VariableDeclarationExpression node)
    {
        return true;
    }


    default public boolean visit(VariableDeclarationStatement node)
    {
        return true;
    }


    default public boolean visit(VariableDeclarationFragment node)
    {
        return true;
    }


    default public boolean visit(WhileStatement node)
    {
        return true;
    }


    default public boolean visit(WildcardType node)
    {
        return true;
    }


    default public boolean visit(YieldStatement node)
    {
        return true;
    }


    default public void endVisit(AnnotationTypeDeclaration node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(AnnotationTypeMemberDeclaration node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(AnonymousClassDeclaration node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ArrayAccess node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ArrayCreation node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ArrayInitializer node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ArrayType node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(AssertStatement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(Assignment node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(Block node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(BlockComment node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(BooleanLiteral node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(BreakStatement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(CastExpression node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(CatchClause node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(CharacterLiteral node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ClassInstanceCreation node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(CompilationUnit node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ConditionalExpression node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ConstructorInvocation node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ContinueStatement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(CreationReference node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(DoStatement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(EmptyStatement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(EnhancedForStatement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(EnumConstantDeclaration node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(EnumDeclaration node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ExportsDirective node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ExpressionMethodReference node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ExpressionStatement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(Dimension node)
    {
        // do nothing by default
    }


    default public void endVisit(FieldAccess node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(FieldDeclaration node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ForStatement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(IfStatement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ImportDeclaration node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(InfixExpression node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(InstanceofExpression node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(Initializer node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(Javadoc node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(LabeledStatement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(LambdaExpression node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(LineComment node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(MarkerAnnotation node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(MemberRef node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(MemberValuePair node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(MethodRef node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(MethodRefParameter node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(MethodDeclaration node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(MethodInvocation node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(Modifier node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ModuleDeclaration node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ModuleModifier node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(NameQualifiedType node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(NormalAnnotation node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(NullLiteral node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(NumberLiteral node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(OpensDirective node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(PackageDeclaration node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ParameterizedType node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ParenthesizedExpression node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(PostfixExpression node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(PrefixExpression node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(PrimitiveType node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ProvidesDirective node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(QualifiedName node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(QualifiedType node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(RequiresDirective node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ReturnStatement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(SimpleName node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(SimpleType node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(SingleMemberAnnotation node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(SingleVariableDeclaration node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(StringLiteral node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(SuperConstructorInvocation node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(SuperFieldAccess node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(SuperMethodInvocation node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(SuperMethodReference node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(SwitchCase node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(SwitchExpression node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(SwitchStatement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(SynchronizedStatement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(TagElement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(TextBlock node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(TextElement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ThisExpression node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(ThrowStatement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(TryStatement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(TypeDeclaration node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(TypeDeclarationStatement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(TypeLiteral node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(TypeMethodReference node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(TypeParameter node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(UnionType node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(UsesDirective node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(IntersectionType node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(VariableDeclarationExpression node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(VariableDeclarationStatement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(VariableDeclarationFragment node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(WhileStatement node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(WildcardType node)
    {
        // default implementation: do nothing
    }


    default public void endVisit(YieldStatement node)
    {
        // default implementation: do nothing
    }
}
