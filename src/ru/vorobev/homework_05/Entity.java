package ru.vorobev.homework_05;

import java.util.Objects;

public class Entity {
	private final String name;
	private final int weight;
	private final int price;
	
	public Entity(String name, int weight, int price) {
		this.name = name;
		this.weight = weight;
		this.price = price;
	}
	
	public int getPrice() {
		return price;
	}
	
	public int getWeight() {
		return weight;
	}
	
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Entity entity = (Entity) o;
		return price == entity.price && weight == entity.weight && Objects.equals(name, entity.name);
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(name, price, weight);
	}
	
	@Override
	public String toString() {
		return "Entity{" +
			"name='" + name + '\'' +
			", weight=" + weight +
			", price=" + price +
			'}';
	}
}
