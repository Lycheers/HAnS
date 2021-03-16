package se.ch.HAnS.featureModel;

import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import se.ch.HAnS.featureModel.parser.FeatureModelParser;
import se.ch.HAnS.featureModel.psi.FeatureModelFile;
import se.ch.HAnS.featureModel.psi.FeatureModelTypes;
import org.jetbrains.annotations.NotNull;

public class FeatureModelParserDefinition implements ParserDefinition {

    public static final TokenSet WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE);
    public static final IFileElementType FILE = new IFileElementType(FeatureModelLanguage.INSTANCE);

    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new FeatureModelLexerAdapter();
    }

    @Override
    public PsiParser createParser(Project project) {
        return new FeatureModelParser();
    }

    // &begin[FeatureModel::File]
    @Override
    public IFileElementType getFileNodeType() {
        return FILE;
    }
    // &end[FeatureModel::File]

    // &begin[FeatureModel::Language]
    @Override
    public @NotNull TokenSet getWhitespaceTokens() {
        return WHITE_SPACES;
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return TokenSet.EMPTY;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode node) {
        return FeatureModelTypes.Factory.createElement(node);
    }
    // &end[FeatureModel::Language]

    // &begin[FeatureModel::File]
    @Override
    public PsiFile createFile(FileViewProvider viewProvider) {
        return new FeatureModelFile(viewProvider);
    }
    // &end[FeatureModel::File]

    @Override
    public SpaceRequirements spaceExistenceTypeBetweenTokens(ASTNode left, ASTNode right) {
        return SpaceRequirements.MAY; // &line[FeatureModel::Language]
    }
}
