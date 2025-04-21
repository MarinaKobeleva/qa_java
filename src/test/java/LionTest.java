import com.example.Lion;
import com.example.LionAlex;
import com.example.Predator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class LionTest {
    @Spy
    private Predator predator;
    private Lion lion;

    @Before
    public void setUp() throws Exception {
        lion = new Lion("Самец", predator);
    }

    @Test
    public void lionTestWithInvalidSexThrowsException() {
        Exception exception = assertThrows(Exception.class, () ->
                new Lion("Неизвестный", predator));
        Assert.assertEquals("Используйте допустимые значения пола животного - самец или самка",
                exception.getMessage());
    }

    @Test
    public void lionTestGetKittens() throws Exception {
        when(predator.getKittens()).thenReturn(5);
        Assert.assertEquals(5,lion.getKittens());
    }

    @Test
    public void lionTestGetFoodResultsEatMeat() throws Exception {
        List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
        when(predator.eatMeat()).thenReturn(expectedFood);
        Assert.assertEquals(expectedFood, lion.getFood());
    }

    @Test
    public void lionTestGetFoodResultsThrowsException() throws Exception {
        when(predator.eatMeat()).thenThrow(new Exception("Неизвестный вид животного, используйте значение Травоядное или Хищник"));
        Exception exception = assertThrows(Exception.class, lion::getFood);
        Assert.assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", exception.getMessage());
    }
}
