package ru.vorobev.homework_05;

import java.util.*;
import java.util.stream.Collectors;

public class BagApp {
	public static void main(String[] args) {
		int maxWeight = 35;
		
		List<Entity> allEntities = new ArrayList<>(Arrays.asList(
		
//			new Entity("Elven Sword", 13, 235),
//			new Entity("Daedric Mace", 20, 1750),
//			new Entity("Daedric Dagger", 6, 500),
			
			new Entity("Elven Dagger", 4, 95),
			new Entity("Dwarven Warhammer", 27, 325),
			new Entity("Glass Dagger", 4, 165),
			new Entity("Ebony Bow", 16, 1440),
			new Entity("Daedric Bow", 18, 2500),
			new Entity("Iron War Axe",11, 30),
			new Entity("Leather Helmet", 2, 60),
			new Entity("Leather Armor", 6, 125),
			new Entity("Leather Bracers", 2, 25),
			new Entity("Leather Boots", 2, 25)
		));
		
		List<Entity> l = findMaxCase(maxWeight, allEntities);
		l.forEach(System.out::println);
		System.out.println("Total weight: " + l.stream().mapToInt(Entity::getWeight).sum());
		System.out.println("Total cost: " + l.stream().mapToInt(Entity::getPrice).sum());

	}
	
	private static List<Entity> findMaxCase(int maxWeight, List<Entity> listOfEntity) {
		
		int totalWeight = listOfEntity.stream().mapToInt(Entity::getWeight).sum();
		
		if (totalWeight <= maxWeight) {
			return new ArrayList<>(listOfEntity);
		}
		
		List<Entity> tempList;
		List<Entity> result = null;
		int maxTotalCost = 0;
		
		for (Entity e : listOfEntity) {
			tempList = listOfEntity.stream().filter(element -> !element.equals(e)).collect(Collectors.toList());
			totalWeight = tempList.stream().mapToInt(Entity::getWeight).sum();
			
			if (totalWeight > maxWeight) {
				tempList = findMaxCase(maxWeight, tempList);
			}
			
			int tempListTotalCost = tempList.stream().mapToInt(Entity::getPrice).sum();
			if (tempListTotalCost > maxTotalCost) {
				result = tempList;
				maxTotalCost = tempListTotalCost;
			}
		}
		return result;
	}
}
