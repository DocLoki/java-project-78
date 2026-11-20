
package hexlet.code.schemas;

public abstract class BaseSchema<T> {

    private boolean required;

    public BaseSchema<T> required() {
        required = true;
        return this;
    }

    protected boolean isRequired() {
        return required;
    }

    public boolean isValid(T value) {
        if (value == null) {
            return !required;
        }

        return isValidValue(value);
    }

    protected abstract boolean isValidValue(T value);
}
