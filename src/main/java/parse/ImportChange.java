package parse;

public class ImportChange
{
    ImportChangeType type;
    String qualifiedName;

    public ImportChange(ImportChangeType type, String qualifiedName)
    {
        this.type = type;
        this.qualifiedName = qualifiedName;
    }
}
