
package hexlet.code.schemas;

public class NumberSchema extends BaseSchema<Integer> {

    private boolean positive;
    private Integer min;
    private Integer max;

    @Override
    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        positive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.min = min;
        this.max = max;
        return this;
    }

    @Override
    protected boolean isValidValue(Integer value) {
        if (positive && value <= 0) {
            return false;
        }

        if (min != null && (value < min || value > max)) {
            return false;
        }

        return true;
    }
}
