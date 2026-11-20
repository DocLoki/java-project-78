package hexlet.code.schemas;

import hexlet.code.Validator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StringSchemaTest {

    private final Validator validator = new Validator();

    @Test
    void testDefaultSchema() {
        var schema = validator.string();

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid("Hexlet"));
    }

    @Test
    void testRequired() {
        var schema = validator.string().required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("Hexlet"));
    }

    @Test
    void testMinLength() {
        var schema = validator.string().minLength(5);

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertFalse(schema.isValid("Java"));
        assertTrue(schema.isValid("Hexlet"));
    }

    @Test
    void testContains() {
        var schema = validator.string().contains("hex");

        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertFalse(schema.isValid("Java"));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void testCombinedRules() {
        var schema = validator.string()
                .required()
                .minLength(5)
                .contains("hex");

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid("hex"));
        assertFalse(schema.isValid("JavaScript"));
        assertTrue(schema.isValid("hexlet"));
    }

    @Test
    void testReplacingRules() {
        var schema = validator.string()
                .minLength(10)
                .minLength(4);

        assertTrue(schema.isValid("Hexlet"));

        schema.contains("what").contains("fox");

        assertTrue(schema.isValid("the fox"));
        assertFalse(schema.isValid("what"));
    }
}
