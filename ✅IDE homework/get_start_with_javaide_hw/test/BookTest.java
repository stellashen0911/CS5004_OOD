import static org.junit.Assert.*;

import org.junit.Test;

import Person.Book;
import Person.Person;

public class BookTest {
	
	@Test
	public void testTitle() {
		String expected = "A good book";
		assertEquals(expected, "A good book");
	}
	
	@Test
	public void testPrice() {
		int expected = 15;
		assertEquals(expected, 15);
	}

}
