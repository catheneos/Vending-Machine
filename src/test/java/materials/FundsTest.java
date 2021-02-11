package materials;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;

import org.junit.Test;

import materials.Funds;

public class FundsTest {

	@Test
	public void zero_plus_one_equals_1() {

		Funds testObject = new Funds();

		BigDecimal testInput = new BigDecimal(1.0);

		testObject.setBalance(testInput);

		BigDecimal expectedResult = new BigDecimal(1.0);
		BigDecimal actualResult = testObject.getBalance();

		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void five_minus_one_and_fifty_is_three_and_fifty() {

		Funds testObject = new Funds();

		BigDecimal testInput = new BigDecimal(5);

		testObject.setBalance(testInput);
		BigDecimal testRemove = new BigDecimal(1.5);

		testObject.removeMoney(testRemove);

		BigDecimal expectedResult = new BigDecimal(3.5);
		BigDecimal actualResult = testObject.getBalance();

		assertEquals(expectedResult, actualResult);

	}

	@Test
	public void funds_start_at_zero() {
		Funds testObject = new Funds();
		assertEquals(new BigDecimal(0), testObject.getBalance());
	}

}
