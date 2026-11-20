
package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NumberSchemaTest {

    private final Validator validator = new Validator();

    @Test
    void testDefaultSchema() {
        var schema = validator.number();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(-5));
    }

    @Test
    void testRequired() {
        var schema = validator.number().required();

        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(10));
    }

    @Test
    void testPositive() {
        var schema = validator.number().positive();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(-5));
    }

    @Test
    void testRange() {
        var schema = validator.number().range(5, 10);

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(4));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testCombinedRules() {
        var schema = validator.number()
                .required()
                .positive()
                .range(5, 10);

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(-5));
        assertFalse(schema.isValid(0));
        assertFalse(schema.isValid(4));
        assertTrue(schema.isValid(5));
        assertTrue(schema.isValid(10));
        assertFalse(schema.isValid(11));
    }

    @Test
    void testReplacingRange() {
        var schema = validator.number()
                .range(5, 10)
                .range(6, 9);

        assertFalse(schema.isValid(5));
        assertTrue(schema.isValid(6));
        assertTrue(schema.isValid(9));
        assertFalse(schema.isValid(10));
    }
}
