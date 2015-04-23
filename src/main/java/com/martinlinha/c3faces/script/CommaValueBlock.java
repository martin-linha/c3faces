package com.martinlinha.c3faces.script;

/**
 * Through this class can be built script blocks of value type separated by comma, name and body are quoted. OUTPUT EXAMPLE:
 *
 * 'NAME', 'BODY'
 *
 * @author Martin Linha
 */
public class CommaValueBlock extends ValueBlock {

    /**
     * Constructs new comma value block with specified NAME and BODY. Both can be quoted. Example of output:
     *
     * 'NAME', 'BODY'
     *
     * @param name Name of array block
     * @param body Body of array block
     * @param bodyQuoted True if body should be quoted
     * @param nameQuoted True if name should be quoted
     */
    public CommaValueBlock(String name, String body, boolean bodyQuoted, boolean nameQuoted) {
        super(name, body, bodyQuoted, nameQuoted);
    }

    @Override
    public boolean isBodyQuoted() {
        return true;
    }

    @Override
    public boolean isNameQuoted() {
        return true;
    }

    @Override
    public String getSeparator() {
        return ", ";
    }
}
