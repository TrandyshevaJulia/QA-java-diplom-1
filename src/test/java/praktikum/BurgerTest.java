package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BurgerTest {

    private Burger burger;
    private String bunName;
    private float bunPrice;
    private String ingredient1Name;
    private String ingredient1Type;
    private float ingredient1Price;
    private String ingredient2Name;
    private String ingredient2Type;
    private float ingredient2Price;
    private String expectedReceipt;

    public BurgerTest(String bunName, float bunPrice, String ingredient1Type, String ingredient1Name, float ingredient1Price, String ingredient2Type, String ingredient2Name, float ingredient2Price, String expectedReceipt) {
        this.bunName = bunName;
        this.bunPrice = bunPrice;
        this.ingredient1Type = ingredient1Type;
        this.ingredient1Name = ingredient1Name;
        this.ingredient1Price = ingredient1Price;
        this.ingredient2Type = ingredient2Type;
        this.ingredient2Name = ingredient2Name;
        this.ingredient2Price = ingredient2Price;
        this.expectedReceipt = expectedReceipt.replace(",", ".");
    }

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"black bun", 100, "sauce", "hot sauce", 100, null, null, 0, generateExpectedReceipt("black bun", "sauce", "hot sauce", null, null, 300.000000f)},
                {"black bun", 100, "filling", "cutlet", 100, null, null, 0, generateExpectedReceipt("black bun", "filling", "cutlet", null, null, 300.000000f)},
                {"white bun", 200, "sauce", "sour cream", 200, null, null, 0, generateExpectedReceipt("white bun", "sauce", "sour cream", null, null, 600.000000f)},
                {"white bun", 200, "filling", "dinosaur", 200, null, null, 0, generateExpectedReceipt("white bun", "filling", "dinosaur", null, null, 600.000000f)},
                {"red bun", 300, "sauce", "chili sauce", 300, null, null, 0, generateExpectedReceipt("red bun", "sauce", "chili sauce", null, null, 900.000000f)},
                {"red bun", 300, "filling", "sausage", 300, null, null, 0, generateExpectedReceipt("red bun", "filling", "sausage", null, null, 900.000000f)},
                {"black bun", 100, "sauce", "hot sauce", 100, "filling", "cutlet", 100, generateExpectedReceipt("black bun", "sauce", "hot sauce", "filling", "cutlet", 400.000000f)},
                {"black bun", 100, "sauce", "hot sauce", 100, "sauce", "sour cream", 200, generateExpectedReceipt("black bun", "sauce", "hot sauce", "sauce", "sour cream", 500.000000f)},
                {"white bun", 200, "sauce", "sour cream", 200, "filling", "dinosaur", 200, generateExpectedReceipt("white bun", "sauce", "sour cream", "filling", "dinosaur", 800.000000f)},
                {"red bun", 300, "sauce", "chili sauce", 300, "filling", "sausage", 300, generateExpectedReceipt("red bun", "sauce", "chili sauce", "filling", "sausage", 1200.000000f)}
        });
    }

    @Test
    public void testBurgerWithIngredients() {
        Bun bun = new Bun(bunName, bunPrice);
        Ingredient ingredient1 = new Ingredient(IngredientType.valueOf(ingredient1Type.toUpperCase()), ingredient1Name, ingredient1Price);
        burger.setBuns(bun);
        burger.addIngredient(ingredient1);

        if (ingredient2Name != null) {
            Ingredient ingredient2 = new Ingredient(IngredientType.valueOf(ingredient2Type.toUpperCase()), ingredient2Name, ingredient2Price);
            burger.addIngredient(ingredient2);
        }

        String actualReceipt = burger.getReceipt();
        String formattedActualReceipt = actualReceipt.replace(",", ".");

        // Отладка вывода, чтобы увидеть разницу
        System.out.println("Expected Receipt:\n" + expectedReceipt);
        System.out.println("Actual Receipt:\n" + actualReceipt);
        System.out.println("Formatted Actual Receipt:\n" + formattedActualReceipt);

        assertEquals(expectedReceipt, formattedActualReceipt);
    }

    private static String generateExpectedReceipt(String bunName, String type1, String name1, String type2, String name2, float expectedPrice) {
        StringBuilder receipt = new StringBuilder();
        receipt.append(String.format("(==== %s ====)%n", bunName));
        if (name1 != null) {
            receipt.append(String.format("= %s %s =%n", type1, name1));
        }
        if (name2 != null) {
            receipt.append(String.format("= %s %s =%n", type2, name2));
        }
        receipt.append(String.format("(==== %s ====)%n", bunName));
        receipt.append(String.format(Locale.US, "%nPrice: %.6f%n", expectedPrice));
        return receipt.toString();
    }

    private static String generateExpectedReceipt(String bunName, String type1, String name1, String type2, float expectedPrice) {
        return generateExpectedReceipt(bunName, type1, name1, null, null, expectedPrice);
    }
}