package materials;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

public class AllProductTests {

	@Test
	public void code_is_correct() {
		Vendable test = new Chip("A1", "Lays", new BigDecimal(1.00));
		String expectedResult = "A1";
		String actualResult = test.getProductCode();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void name_is_correct() {
		Vendable test = new Chip("A1", "Lays", new BigDecimal(1.00));
		String expectedResult = "Lays";
		String actualResult = test.getProductName();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void price_is_correct() {
		Vendable test = new Chip("A1", "Lays", new BigDecimal(1.55));
		BigDecimal expectedResult = new BigDecimal(1.55);
		BigDecimal actualResult = test.getPrice();
		assertEquals(expectedResult, actualResult);
	}

	@Test
	public void quantity_is_correct() {
		Vendable test = new Chip("A1", "Lays", new BigDecimal(1.00));
		int expectedResult = 5;
		int actualResult = test.getQuantity();
		assertEquals(expectedResult, actualResult);
	}
}
