
package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    private Integer size;
    private Map<String, BaseSchema<String>> schemas;

    @Override
    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        this.size = size;
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        this.schemas = schemas;
        return this;
    }

    @Override
    protected boolean isValidValue(Map<?, ?> value) {
        if (size != null && value.size() != size) {
            return false;
        }

        if (schemas != null) {
            for (var entry : schemas.entrySet()) {
                Object fieldValue = value.get(entry.getKey());

                if (fieldValue != null && !(fieldValue instanceof String)) {
                    return false;
                }

                if (!entry.getValue().isValid((String) fieldValue)) {
                    return false;
                }
            }
        }

        return true;
    }
}
