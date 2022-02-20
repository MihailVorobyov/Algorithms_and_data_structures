package ru.vorobev.homework_02;

public class Notebook implements Comparable<Notebook> {
	public final Manufacturer manufacturer;
	public final int memory;
	public final int price;
	
	public Notebook(Manufacturer manufacturer, int memory, int price) {
		this.manufacturer = manufacturer;
		this.memory = memory;
		this.price = price;
	}
	
	@Override
	public String toString() {
		return "price: " + this.price + "\tmemory: " + this.memory + "\tmanufacturer: " + this.manufacturer;
	}
	
	@Override
	public int compareTo(Notebook other) {
		if (this.price - other.price == 0) {
			if (this.memory - other.memory == 0) {
				return this.manufacturer.ordinal() - other.manufacturer.ordinal();
			} else {
				return this.memory - other.memory;
			}
		} else {
			return this.price - other.price;
		}
	}
}
