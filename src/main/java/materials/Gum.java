package materials;

import java.math.BigDecimal;

public class Gum extends Vendable {

	public Gum(String productCode, String productName, BigDecimal price) {
		super(productCode, productName, price);
	}

	@Override
	public void makeNoise() {
		System.out.println("Chew Chew, Yum!");

	}
}
