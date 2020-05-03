package recuperacionSegundo;

public class GestisimalArt {
	private String code;
	private String desc;
	private double sellPrice;
	private double buyPrice;
	private int stock;
	
	public GestisimalArt(String code, String desc, double sp, double bp, int stock) {
		this.code = code;
		this.desc = desc;
		this.sellPrice = sp;
		this.buyPrice = bp;
		this.stock = stock;
	}
	
	public GestisimalArt(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public double getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(double sellPrice) {
		this.sellPrice = sellPrice;
	}

	public double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(String.format("=================================="
				+ "\nCode: %s"
				+ "\nDescription: %s"
				+ "\nSell price: %d"
				+ "\nBuy proce: %d"
				+ "\nStock: %d"
				+ "\n==================================", this.code, this.desc, this.sellPrice, this.buyPrice, this.stock));
		return sb.toString();
	}
}
