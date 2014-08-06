/*
 * Kittinan Ponkaew
 * August 5, 2014
 *
 */

import java.util.*;
import java.math.*;

public class Ingredient{
	
	public enum IngredientType {
    	PRODUCE, PANTRY, MEAT
	}

	static private ArrayList<Ingredient> allProduce = new ArrayList();
	static private ArrayList<Ingredient> allPantry = new ArrayList();
	static private ArrayList<Ingredient> allMeat = new ArrayList();

	private String name;
	private boolean isOrganic;
	private BigDecimal price;
	private BigDecimal amount;
	private IngredientType iType;

	public Ingredient(String name, boolean isOrganic, BigDecimal price, BigDecimal amount, IngredientType iType,
		boolean addToList){
		this.name = name;
		this.isOrganic = isOrganic;
		this.price = price;
		this.amount = amount;
		this.iType = iType;

		if(addToList){
			if(iType == IngredientType.PRODUCE){
				allProduce.add(this);
			} else if( iType == IngredientType.PANTRY){
				allPantry.add(this);
			} else if( iType == IngredientType.MEAT){
				allMeat.add(this);
			}
		}
	}
	

	public static ArrayList<Ingredient> getAllProduce(){
		return allProduce;
	}
	public static ArrayList<Ingredient> getAllPantry(){
		return allPantry;
	}
	public static ArrayList<Ingredient> getAllMeat(){
		return allMeat;
	}
	public String getName(){
		return name;
	}
	public void setName(String n){
		name = n;
	}

	public boolean isOrganic(){
		return isOrganic;
	}
	public void setIsOrganic(boolean b){
		isOrganic = b;
	}
	public BigDecimal getPrice(){
		return price;
	}
	public void setPrice(BigDecimal d){
		price = d;
	}
	public BigDecimal getAmount(){
		return amount;
	}
	public void setAmount(BigDecimal i){
		amount = i;
	}
	public IngredientType getIngredientType(){
		return iType;
	}
	public void setIngredientType(IngredientType it){
		iType = it;
	}

	public static Ingredient find(String name){
		for(Ingredient i : allProduce){
			if(i.getName().equalsIgnoreCase(name))
				return i;
		}
		for(Ingredient i : allMeat){
			if(i.getName().equalsIgnoreCase(name))
				return i;
		}
		for(Ingredient i : allPantry){
			if(i.getName().equalsIgnoreCase(name))
				return i;
		}
		return null;
	}
	public static void printAllProduce(){
		System.out.println("Produce:");
		for(Ingredient i : allProduce){
			System.out.println(i.amount + " " + i.name + " - $" + i.price);
		}
	}
	public static void printAllPantry(){
		System.out.println("Pantry:");
		for(Ingredient i : allProduce){
			System.out.println(i.amount + " " + i.name + " - $" + i.price);
		}
	}
	public static void printAllMeat(){
		System.out.println("Meat/Poultry:");
		for(Ingredient i : allMeat){
			System.out.println(i.amount + " " + i.name + " - $" + i.price);
		}
	}
}