package ua.juiceshop.repository;

import java.util.ArrayList;
import java.util.List;

import ua.juiceshop.model.Juice;

public class Repository {
	List<Juice> goods = new ArrayList<Juice>();

	public Repository() {
		goods.add(new Juice("0.5", "Watermelon juice", "images/watermelon.png"));
		goods.add(new Juice("1", "Kiwi juice", "images/kiwi.png"));
	}

	public List<Juice> getGoogs() {
		return goods;
	}
}
