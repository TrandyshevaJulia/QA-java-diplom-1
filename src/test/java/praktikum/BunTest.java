package praktikum;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

public class BunTest {
    private Database database;

    @Before
    public void setUp() {
        database = mock(Database.class);
        List<Bun> buns = Arrays.asList(
                new Bun("black bun", 100),
                new Bun("white bun", 200),
                new Bun("red bun", 300)
        );
        when(database.availableBuns()).thenReturn(buns);
    }

    @Test
    public void testNumberOfBunsInDatabase() {
        List<Bun> buns = database.availableBuns();
        assertEquals(3, buns.size());
    }

    private Bun findBunByName(List<Bun> buns, String name) {
        for (Bun bun : buns) {
            if (bun.getName().equals(name)) {
                return bun;
            }
        }
        return null; // выбросили исключение, если булочка не найдена
    }

    @Test
    public void testGetNameForBlackBun() {
        List<Bun> buns = database.availableBuns();
        Bun blackBun = findBunByName(buns, "black bun");
        assertEquals("black bun", blackBun.getName());
    }

    @Test
    public void testGetPriceForBlackBun() {
        List<Bun> buns = database.availableBuns();
        Bun blackBun = findBunByName(buns, "black bun");
        assertEquals(100, blackBun.getPrice(), 0);
    }

    @Test
    public void testGetNameForWhiteBun() {
        List<Bun> buns = database.availableBuns();
        Bun whiteBun = findBunByName(buns, "white bun");
        assertEquals("white bun", whiteBun.getName());
    }

    @Test
    public void testGetPriceForWhiteBun() {
        List<Bun> buns = database.availableBuns();
        Bun whiteBun = findBunByName(buns, "white bun");
        assertEquals(200, whiteBun.getPrice(), 0);
    }

    @Test
    public void testGetNameForRedBun() {
        List<Bun> buns = database.availableBuns();
        Bun redBun = findBunByName(buns, "red bun");
        assertEquals("red bun", redBun.getName());
    }

    @Test
    public void testGetPriceForRedBun() {
        List<Bun> buns = database.availableBuns();
        Bun redBun = findBunByName(buns, "red bun");
        assertEquals(300, redBun.getPrice(), 0);
    }
}