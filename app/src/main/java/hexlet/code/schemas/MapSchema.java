
package hexlet.code.schemas;

import java.util.Map;

public class MapSchema extends BaseSchema<Map<?, ?>> {

    @Override
    public MapSchema required() {
        required = true;
        return this;
    }

    public MapSchema sizeof(int size) {
        addCheck("sizeof", value -> value.size() == size);
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
        addCheck("shape", value -> {
            for (var entry : schemas.entrySet()) {
                Object fieldValue = value.get(entry.getKey());

                if (fieldValue != null && !(fieldValue instanceof String)) {
                    return false;
                }

                if (!entry.getValue().isValid((String) fieldValue)) {
                    return false;
                }
            }

            return true;
        });

        return this;
    }
}
