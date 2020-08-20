package ie.cct.showcasefarmca;

import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

//There is need to tell Spring that this class is a controller 
//CA - Cloud Computing 
//Student: Yuri Andrade 
//Student number: 2019154

@RestController
public class ShowcaseCAController {
//Creating a method we check if the controller works or not

	ArrayList<Livestock> livestock;

	public ShowcaseCAController() {
		livestock = new ArrayList<Livestock>();
	}

	// Once we have a method, we need to tell SpringWeb to look for this method
	// We want to execute requests in HTTP that end up executing code in JAVA
	// GET requests from //localhost:8080/test
	@GetMapping("farm")
	public String test() {
		return "Welcome my friend, enjoy the options of this lovely farm: "
				+ "1. add-animal (POST Request) or "
				+ "2. average-weight (only for pigs) or "
				+ "3. available-animals or "
				+ "4. farm-price or "
				+ "5. current-value."
				;
	}

	// Endpoint method - Add a new animal
	// We will use a POST request, where the user can insert data to the JAVA code
	@PostMapping("add-animal")
	public Response addAnimal(@RequestBody Livestock item) {
		// @RequestBody annotation maps the HTTP Request body to transfer or domain
		// object enabling object Livestock item onto a JAVA object
		if (!(item.getAnimal().equalsIgnoreCase("cow") || item.getAnimal().equalsIgnoreCase("pig")
				|| item.getAnimal().equalsIgnoreCase("chicken"))) {

			// We have defined a throw class, which once the wrong animal is added, will be
			// a message
			throw new NotFoundException(
					"Not a valid animal. This farm has these type of animals: pig, cow and chicken.");

		} else {

			livestock.add(item);
			item.priceAnimal(item.getWeight(), item.getAnimal());

		}

		return new Response("Animal added! And price is " + item.getPrice());
	}

	// Second end-point to check the average weight of Pig
	@GetMapping("average-weight")
	public Response Weight() {

		if (livestock.size() == 0) {

			//throw new NotFoundException("No animals found in the farm.");
			//Or we throw a HTTP status NO CONTENT
			throw new noContentException("None animals");
			// We throw this exception if the farm has no animal
			// We return an error message instead of NaN, or not a number
		} else {

			int count = 0;
			Float weight = 0.0f;

			for (Livestock item : livestock) {

				if (item.getAnimal().contains("pig")) {
					weight += item.getWeight();
					count++;
				}
			}
			
			if (!(weight == 0)) {
				weight = weight / count;
				return new Response("The average weight of pigs in this farm is: " + weight);
			} else {
				//If there are no pigs in the farm, return no content error! 
				throw new noContentException("There are no pigs in this farm!");
			}
			

		}

	}

	@GetMapping("available-animals")
	public Response availability() {

		ArrayList<Integer> availablePig = new ArrayList<Integer>();
		ArrayList<Integer> availableChicken = new ArrayList<Integer>();
		ArrayList<Integer> availableCow = new ArrayList<Integer>();

		if (livestock.size() == 0) {
			//If no animal in the farm, we can throw this message
			//throw new noContentException("No animals found in the farm.");
			//Or we can throw HTTP status
			throw new noContentException("None animals");
		
		} else {
			//First we have to access the animals added above 
			for (Livestock item : livestock) {

				for (int i = 0; i < livestock.size(); i++) {
					if (item.getAnimal().contains("pig")) {
						if (item.getPrice() > 0) {
							availablePig.add(1);
							break;
						}

					}

				}

				for (int i = 0; i < livestock.size(); i++) {
					if (item.getAnimal().contains("chicken")) {
						if (item.getPrice() > 0) {
							availableChicken.add(1);
							break;
						}

					}

				}

				for (int i = 0; i < livestock.size(); i++) {
					if (item.getAnimal().contains("cow")) {
						if (item.getPrice() > 0) {
							availableCow.add(1);
							break;
						}

					}

				}

			}
		}
		return new Response("Pigs available: " + availablePig.size() + ", chickens available "
				+ availableChicken.size() + ", cows available " + availableCow.size());

	}

	@GetMapping("farm-price")
	public Response farmPrice() {
    //How to check average of animal price for each animal instead the whole farm? 
		if (livestock.size() == 0) {
			//throw new NotFoundException("No animals found in the farm");
			// We throw this exception if the farm has no animal
			// We return an error message instead of NaN, or not a number
			throw new noContentException("Sorry, there are no animals in this farm!");
			
		} else {

			Float price = 0.0f;

			for (Livestock item : livestock) {
				price += item.getPrice();
			}
			return new Response("Full farm stock value: " + price + " euros.");

		}

	}

	
	//Endpoint to calculate the current value of the farm
	@GetMapping("current-value")
	public Response offer(@RequestParam(required = true) float cow, @RequestParam(required = true) float pig,
			@RequestParam(required = true) float chicken) {

		if (livestock.size() == 0) {
			//throw new NotFoundException("No animals found in the farm");
			// We throw this exception if the farm has no animal
			// We return an error message instead of NaN, or not a number
			
			//We throw an NON CONTENT status
			throw new noContentException("None animals");
		} else {

			Float pricecow = 0.0f;
			Float pricepig = 0.0f;
			Float pricechicken = 0.0f;
			Float total = 0.0f;

			for (Livestock item : livestock) {

				for (int i = 0; i < livestock.size(); i++) {
					if (item.getAnimal().contains("cow")) {
						if (item.getPrice() > 0) {
							pricecow = item.getPrice();
							pricecow = cow;
							total += pricecow;
							break;
						}

					} else if (item.getAnimal().contains("chicken")) {
						if (item.getPrice() > 0) {
							pricechicken = item.getPrice();
							pricechicken = chicken;
							total += pricechicken;
							break;
						}

					} else if (item.getAnimal().contains("pig")) {
						if (item.getPrice() > 0) {
							pricepig = item.getPrice();
							pricepig = pig;
							total += pricepig;
							break;
						}

					}

				}

			}
			return new Response("Full farm stock value based upon you evaluation is: " + total + " euros.");

		}
	}
	
	//We will add the possible scenarios of errors
	//Http NO CONTENT but OK error 202 
	//Request valid but returns no object! 
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public class noContentException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		
		public noContentException (String msg) {
			super(msg);
		}
		
	}
	
	//Http Intenal Server Error, error 500 - not needed to this project
	@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
	public class errorInternalException extends RuntimeException{

		/**
		 * 
		 */
		private static final long serialVersionUID = -6728086993332337257L;
		
		public errorInternalException(String msg) {
			super (msg);
		}
	}
}
