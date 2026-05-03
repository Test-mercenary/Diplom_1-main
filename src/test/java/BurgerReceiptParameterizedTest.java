package praktikum;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerReceiptParameterizedTest {

    private final IngredientType ingredientType;
    private final String expectedIngredientType;

    public BurgerReceiptParameterizedTest(IngredientType ingredientType, String expectedIngredientType) {
        this.ingredientType = ingredientType;
        this.expectedIngredientType = expectedIngredientType;
    }

    @Parameterized.Parameters
    public static Object[][] getTestData() {
        return new Object[][]{
                {IngredientType.SAUCE, "sauce"},
                {IngredientType.FILLING, "filling"}
        };
    }

    @Test
    public void getReceiptShouldReturnCorrectReceipt() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        Ingredient ingredient = mock(Ingredient.class);

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        when(ingredient.getType()).thenReturn(ingredientType);
        when(ingredient.getName()).thenReturn("test ingredient");
        when(ingredient.getPrice()).thenReturn(50f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        String expectedReceipt = String.format(
                "(==== black bun ====)%n" +
                        "= %s test ingredient =%n" +
                        "(==== black bun ====)%n" +
                        "%n" +
                        "Price: %f%n",
                expectedIngredientType,
                250f
        );

        String actualReceipt = burger.getReceipt();

        Assert.assertEquals("Чек должен полностью совпадать с ожидаемым", expectedReceipt, actualReceipt);
    }
}