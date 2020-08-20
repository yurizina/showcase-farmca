package ie.cct.showcasefarmca;

//CA - Cloud Computing 
//Student: Yuri Andrade 
//Student number: 2019154

public class Livestock {
     
	private String animal; 
    private float price;
    private float weight;
    
    //Empty object to run the controller - default
    public Livestock() {
		
	}

	//We need constructors which each will return information regarding this class
    public Livestock(String animal, float weight, float price) {
		super();
		this.animal = animal;
		this.weight = weight;
		price = priceAnimal(weight, animal);	
	}
    //The following method will return the price of each animal based upon type and weight
    public float priceAnimal(float weight, String animal) {
		
    	if (weight < 0.5 && animal.contains("chicken")) {
			price = 0.0f; 
		} else if (weight >= 0.5 && animal.contains("chicken")){
			price = 5.0f; 
		} else if (weight < 100 && animal.contains("pig")){
			price = 0.0f;  
		} else if (weight >= 100 && animal.contains("pig")) {
			price = 250.0f;
		}else if (weight < 300 && animal.contains("cow")) {
			price = 0.0f; 
		}else if (weight >= 300 && animal.contains("cow")) {
			price = 500.0f;
		}
    	return price;
    }
    
    //We need to generate Getters and Setters to each variable
    //We will define to each animal, its type, price and weight
	public String getAnimal() {
		return animal;
	}
	public void setAnimal(String animal) {
		this.animal = animal;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Float getWeight() {
		return weight;
	}
	public void setWeight(float weight) {
		this.weight = weight;
	} 
    
    
}
