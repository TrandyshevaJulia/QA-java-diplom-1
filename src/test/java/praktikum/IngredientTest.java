package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.junit.runners.Parameterized.Parameter;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class IngredientTest {

    @Parameter(0)
    public IngredientType type;

    @Parameter(1)
    public String name;

    @Parameter(2)
    public float price;

    @Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][] {
                { IngredientType.SAUCE, "hot sauce", 100.0f },
                { IngredientType.SAUCE, "sour cream", 200.0f },
                { IngredientType.SAUCE, "chili sauce", 300.0f },
                { IngredientType.FILLING, "cutlet", 100.0f },
                { IngredientType.FILLING, "dinosaur", 200.0f },
                { IngredientType.FILLING, "sausage", 300.0f }
        });
    }

    @Test
    public void testIngredientCreation() {
        Ingredient ingredient = new Ingredient(type, name, price);

        assertEquals(type, ingredient.getType());
        assertEquals(name, ingredient.getName());
        assertEquals(price, ingredient.getPrice(), 0.0f);
    }

    @Test
    public void testGetPrice() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(price, ingredient.getPrice(), 0.0f);
    }

    @Test
    public void testGetName() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(name, ingredient.getName());
    }

    @Test
    public void testGetType() {
        Ingredient ingredient = new Ingredient(type, name, price);
        assertEquals(type, ingredient.getType());
    }
}