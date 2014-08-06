/*
 * Kittinan Ponkaew
 * August 5, 2014
 *
 */

import java.io.*;
import java.util.*;
import java.math.*;

public class RecipeCalc{
	private static ArrayList<Recipe> rList = new ArrayList();

	public enum FileState{
		INGREDIENTS, RECIPES, UINIT
	}
	public static void main(String args[]){
		if(args.length < 1){
			System.out.println("Usage: java RecipeCalc <filename>");
			System.exit(1);
		}
		for(String arg : args){
			if(arg.equals("-t")){
				readInputFile("TestRecipeList.txt");
				
				//test output
				Ingredient.printAllProduce();
				Ingredient.printAllPantry();
				Ingredient.printAllMeat();

				for(Recipe r : rList){
					r.calcCost();
					r.printData();
				}
				System.exit(0);
			}
		}

		//running the actual file
		readInputFile("TestRecipeList.txt");
		for(Recipe r : rList){
			r.calcCost();
			r.printData();
		}
	}

	
	public static void readInputFile(String fileName){
		try{
		BufferedReader in = new BufferedReader(new FileReader(fileName));
		String text;
		Ingredient.IngredientType currentType = Ingredient.IngredientType.PRODUCE;
		FileState currentFS = FileState.UINIT;

		while (in.ready()) {
			text = in.readLine();
			if(text.equals("") || text.charAt(0) == '#'){
				continue;
			}
			if(text.equals("Ingredients")){
				currentFS = FileState.INGREDIENTS;
				continue;
			} else if(text.equals("Recipes")){
				currentFS = FileState.RECIPES;

				continue;
			}

			if(text.equals("Produce")){
				currentType = Ingredient.IngredientType.PRODUCE;
				continue;
			} else if (text.equals("Pantry")){
				currentType = Ingredient.IngredientType.PANTRY;
				continue;
			} else if(text.equals("Meat/Poultry")){
				currentType = Ingredient.IngredientType.MEAT;
				continue;
			}

			if( currentFS == FileState.INGREDIENTS ){
				StringTokenizer tokenizer = new StringTokenizer(text,";"); 
				BigDecimal amount = new BigDecimal( tokenizer.nextToken() );
				String name = tokenizer.nextToken();
				
				String pStr = tokenizer.nextToken().trim();
		
				BigDecimal price = new BigDecimal( pStr);
		
				boolean isOrganic = false;
				if (name.toLowerCase().contains("organic")) {
					isOrganic = true;
					//System.out.println("this is organic : " + name);
				}
				
				new Ingredient(name, isOrganic, price, amount, currentType, true);
			} else if (currentFS == FileState.RECIPES){
				
				Recipe rec = null;
				
				if(text.charAt(0) == '!'){		
					String rName = text.substring(1);
					rec = new Recipe(rName);
					rList.add(rec);
				}
				while(in.ready()){
					text = in.readLine();
					if(text.equals("") || text.charAt(0) == '#'){
						continue;
					}
					
					
					if(text.charAt(0) == '!'){
						
						String rName = text.substring(1);
						rec = new Recipe(rName);
						rList.add(rec);
						
						continue;
					}
					
					//rec = rList.get(rList.size()-1);
					StringTokenizer tokenizer = new StringTokenizer(text,";"); 
					
					BigDecimal amount = new BigDecimal( tokenizer.nextToken() );
					String iName = tokenizer.nextToken();
					Ingredient ind = Ingredient.find(iName);
					
					BigDecimal price = amount.multiply(ind.getAmount()).multiply( ind.getPrice() );
					
					Ingredient rInd = new Ingredient(iName, ind.isOrganic(), 
						price, amount, ind.getIngredientType(), false); 

					
					rec.addIngredient(rInd);
				}
				
			}

		//System.out.println(text);
		}
		in.close();
		} catch(Exception e){
			System.err.println("Exception: " + e.getMessage() );
		}
	}
}