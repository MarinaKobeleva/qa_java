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
public class LionAlexTest {
    @Spy
    private Predator predator;

    private LionAlex lionAlex;

    @Before
    public void setUp() throws Exception {
        lionAlex = new LionAlex(predator);
    }

    @Test
    public void lionAlexDoesHaveMane() throws Exception {
        Assert.assertTrue(lionAlex.doesHaveMane());
    }

    @Test
    public void lionAlexGetKittensZero() throws Exception {
        Assert.assertEquals(0, lionAlex.getKittens());
    }

    @Test
    public void lionAlexGetFoodResultMeatList() throws Exception {
        when(predator.eatMeat()).thenReturn(List.of("Животные", "Птицы", "Рыба"));
        Assert.assertEquals(List.of("Животные", "Птицы", "Рыба"), lionAlex.getFood());
    }

    @Test
    public void lionAlexGetFoodResultsThrowsException() throws Exception {
        when(predator.eatMeat()).thenThrow(new Exception("Неизвестный вид животного, используйте значение Травоядное или Хищник"));
        Exception exception = assertThrows(Exception.class, lionAlex::getFood);
        Assert.assertEquals("Неизвестный вид животного, используйте значение Травоядное или Хищник", exception.getMessage());
    }

    @Test
    public void lionAlexGetFriendsList() throws Exception {
        Assert.assertEquals(List.of("Марти", "Глория", "Мелман"), lionAlex.getFriends());
    }

    @Test
    public void lionAlexGetPlaceOfLivingNYZoo() throws Exception {
        Assert.assertEquals("Нью-Йоркский зоопарк", lionAlex.getPlaceOfLiving());
    }
}
