
package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    private Integer size;

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        this.size = size;
        return this;
    }

    @Override
    protected boolean isValidValue(Map<?, ?> value) {
        if (size != null && value.size() != size) {
            return false;
        }

        return true;
    }
}
