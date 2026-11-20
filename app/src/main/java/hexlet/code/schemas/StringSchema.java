
package hexlet.code.schemas;

public class StringSchema extends BaseSchema<String> {

    private Integer minLength;
    private String contains;

    @Override
    public StringSchema required() {
        super.required();
        return this;
    }

    public StringSchema minLength(int length) {
        minLength = length;
        return this;
    }

    public StringSchema contains(String substring) {
        contains = substring;
        return this;
    }

    @Override
    protected boolean isValidValue(String value) {
        if (value.isEmpty()) {
            return !isRequired();
        }

        if (minLength != null && value.length() < minLength) {
            return false;
        }

        if (contains != null && !value.contains(contains)) {
            return false;
        }

        return true;
    }
}
