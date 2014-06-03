package ua.juiceshop.model;

public class Juice {
	private String amount;
	private String description;
	private String image;

	public Juice(String amount, String description, String image) {
		this.amount = amount;
		this.description = description;
		this.image = image;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
