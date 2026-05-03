package praktikum;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {

    private Burger burger;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient sauce;

    @Mock
    private Ingredient filling;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void setBunsShouldSetBun() {
        burger.setBuns(bun);

        Assert.assertSame("Булка должна быть установлена в бургер", bun, burger.bun);
    }

    @Test
    public void addIngredientShouldAddIngredientToList() {
        burger.addIngredient(sauce);

        Assert.assertEquals("Ингредиент должен добавиться в список", List.of(sauce), burger.ingredients);
    }

    @Test
    public void removeIngredientShouldRemoveIngredientByIndex() {
        burger.addIngredient(sauce);

        burger.removeIngredient(0);

        Assert.assertEquals("Список ингредиентов должен стать пустым", List.of(), burger.ingredients);
    }

    @Test
    public void moveIngredientShouldMoveIngredientToNewIndex() {
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        burger.moveIngredient(0, 1);

        Assert.assertEquals("Ингредиенты должны поменяться местами", List.of(filling, sauce), burger.ingredients);
    }

    @Test
    public void getPriceShouldReturnDoubleBunPricePlusIngredientPrices() {
        when(bun.getPrice()).thenReturn(100f);
        when(sauce.getPrice()).thenReturn(50f);
        when(filling.getPrice()).thenReturn(70f);

        burger.setBuns(bun);
        burger.addIngredient(sauce);
        burger.addIngredient(filling);

        float actualPrice = burger.getPrice();

        Assert.assertEquals("Цена должна считаться по формуле: булка * 2 + ингредиенты", 320f, actualPrice, 0.001f);
    }
}