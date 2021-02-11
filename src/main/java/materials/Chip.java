package materials;

import java.math.BigDecimal;

public class Chip extends Vendable {

	public Chip(String productCode, String productName, BigDecimal price) {
		super(productCode, productName, price);
	}

	@Override
	public void makeNoise() {
		System.out.println("Crunch Crunch, Yum!");

	}

}
