package materials;

import java.math.BigDecimal;
import java.math.RoundingMode;

public abstract class Vendable {

	private String productCode;
	private String productName;
	private BigDecimal price = new BigDecimal(0);
	private Integer quantity = 5;

	public Vendable(String productCode, String productName, BigDecimal price) {
		super();
		this.productCode = productCode;
		this.productName = productName;
		this.price = price;
	}

	public String getProductCode() {
		return productCode;
	}

	public String getProductName() {
		return productName;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public abstract void makeNoise();

	public void reduceInventory() {
		this.quantity -= 1;
	}

}
