/*
 * Kittinan Ponkaew
 * August 5, 2014
 *
 */

import java.util.*;
import java.math.*;
public class Recipe{
	private ArrayList<Ingredient> allIngredients = null;

	private String name;
	private BigDecimal totalPrice;
	private BigDecimal salesTax;
	private BigDecimal discount;
	private BigDecimal cost;

	public Recipe(String name){
		this.name = name;
		this.allIngredients = new ArrayList();
	}
	public void addIngredient(Ingredient ind){
		
		allIngredients.add(ind);
		
	}
	public void calcCost(){
		BigDecimal tp = new BigDecimal("0");
		BigDecimal wd = new BigDecimal("0");
		BigDecimal st = new BigDecimal("0");
		for(Ingredient i : allIngredients){
			tp = i.getPrice().add (tp);
			if(i.isOrganic()){
				wd = wd.add(i.getPrice());
				//System.out.println("organic : " + i.getName());
			}
			if(i.getIngredientType() != Ingredient.IngredientType.PRODUCE){
				st = st.add(i.getPrice()) ;
			}
		}
		
		//calculations and rounding
		st = st.multiply(new BigDecimal("0.086"));
		st = st.movePointLeft(-2);
		st = st.divide(new BigDecimal("7"), 0,RoundingMode.CEILING);
		st = st.multiply(new BigDecimal("0.07"));
		wd = wd.multiply(new BigDecimal("0.05"));
		wd = wd.setScale(2, RoundingMode.CEILING);
		
		totalPrice = tp.setScale(2, RoundingMode.CEILING);
		salesTax = st;
		discount = wd;
		cost = totalPrice.add(salesTax).subtract(discount);
	}
	public void printData(){
		System.out.println(name);
		System.out.println(" - Sales Tax: $" + salesTax);
		System.out.println(" - Wellness Discount: $" + discount);
		System.out.println(" - Total Cost: $" + cost);
	}
}