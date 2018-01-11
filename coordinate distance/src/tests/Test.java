/**
 * 
 */
package tests;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.graphhopper.jsprit.core.algorithm.VehicleRoutingAlgorithm;
import com.graphhopper.jsprit.core.algorithm.box.Jsprit;
import com.graphhopper.jsprit.core.problem.Location;
import com.graphhopper.jsprit.core.problem.VehicleRoutingProblem;
import com.graphhopper.jsprit.core.problem.job.Service;
import com.graphhopper.jsprit.core.problem.solution.VehicleRoutingProblemSolution;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleImpl;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleType;
import com.graphhopper.jsprit.core.problem.vehicle.VehicleTypeImpl;
import com.graphhopper.jsprit.core.util.Solutions;

/**
 * @author sawai
 *
 */
public class Test {
	
	
	/*public static double distance(double lat1, double lat2, double lon1,
	        double lon2, double el1, double el2) {

	    final int R = 6371; // Radius of the earth

	    double latDistance = Math.toRadians(lat2 - lat1);
	    double lonDistance = Math.toRadians(lon2 - lon1);
	    double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
	            + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
	            * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
	    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	    double distance = R * c * 1000; // convert to meters

	    double height = el1 - el2;

	    distance = Math.pow(distance, 2) + Math.pow(height, 2);

	    return Math.sqrt(distance);
	}*/
	
	
	
	public static void main(String[] args) {
		System.out.println("-- start -- ");
		
		
		final int WEIGHT_INDEX = 0;
		VehicleTypeImpl.Builder vehicleTypeBuilder = VehicleTypeImpl.Builder.newInstance("vehicleType").addCapacityDimension(WEIGHT_INDEX,1);
		VehicleType vehicleType = vehicleTypeBuilder.build();
		
		VehicleImpl.Builder vehicleBuilder = VehicleImpl.Builder.newInstance("vehicle");
		// my current location is ajmeri gate
		vehicleBuilder.setStartLocation(Location.newInstance(26.916411, 75.818810));
		vehicleBuilder.setType(vehicleType);
		VehicleImpl vehicle = vehicleBuilder.build();
		
		Service service1 = Service.Builder.newInstance("chokhi").addSizeDimension(WEIGHT_INDEX,1).setLocation(Location.newInstance(26.767657, 75.835745)).build();
		Service service2 = Service.Builder.newInstance("off").addSizeDimension(WEIGHT_INDEX,1).setLocation(Location.newInstance(26.845256, 75.776860)).build();
		Service service3 = Service.Builder.newInstance("wtp").addSizeDimension(WEIGHT_INDEX,1).setLocation(Location.newInstance(26.854058, 75.804749)).build();
		Service service4 = Service.Builder.newInstance("random").addSizeDimension(WEIGHT_INDEX,1).setLocation(Location.newInstance(15, 13)).build();
		
		List<Service> services = new ArrayList<Service>();
		services.add(service1);
		services.add(service2);
		services.add(service3);
		services.add(service4);
		
		Map<String, Double> map = new HashMap<String, Double>();
		for (Service service : services) {
			VehicleRoutingProblem.Builder vrpBuilder = VehicleRoutingProblem.Builder.newInstance();
			vrpBuilder.addVehicle(vehicle);
			vrpBuilder.addJob(service); 
			
			VehicleRoutingProblem problem = vrpBuilder.build();
			
			VehicleRoutingAlgorithm algorithm = Jsprit.createAlgorithm(problem);
			
			Collection<VehicleRoutingProblemSolution> solutions = algorithm.searchSolutions();
			
			VehicleRoutingProblemSolution bestSolution = Solutions.bestOf(solutions);
			map.put(service.getId(), bestSolution.getCost());
		}
		
		for(String key : map.keySet()) {
			System.out.println(key + " : "  + map.get(key));
		}
		
		
		
 		/*Shipment shipment1 = Shipment.Builder.newInstance("1").addSizeDimension(0, 1).setPickupLocation(Location.newInstance(1, 1)).setDeliveryLocation(Location.newInstance(6, 13)).build();
		Shipment shipment2 = Shipment.Builder.newInstance("2").addSizeDimension(0, 1).setPickupLocation(Location.newInstance(1, 1)).setDeliveryLocation(Location.newInstance(6, 11)).build();
		Shipment shipment3 = Shipment.Builder.newInstance("3").addSizeDimension(0, 1).setPickupLocation(Location.newInstance(1, 1)).setDeliveryLocation(Location.newInstance(14, 9)).build();
		Shipment shipment4 = Shipment.Builder.newInstance("4").addSizeDimension(0, 1).setPickupLocation(Location.newInstance(1, 1)).setDeliveryLocation(Location.newInstance(14, 11)).build();
		System.out.println("shipment created successfully !!!");
		
		VehicleRoutingProblem.Builder vehicleRoutingProblem = VehicleRoutingProblem.Builder.newInstance();
		vehicleRoutingProblem.addJob(shipment1);
		vehicleRoutingProblem.addJob(shipment2);
		vehicleRoutingProblem.addJob(shipment3);
		vehicleRoutingProblem.addJob(shipment4);
		System.out.println("shipment added into vehicleRoutingProblem created successfully !!!");
		
		VehicleRoutingProblem problem = vehicleRoutingProblem.build();
		System.out.println("VehicleRoutingProblem created successfully !!!");
		
		VehicleRoutingAlgorithm vehicleRoutingAlgorithm = Jsprit.createAlgorithm(problem);
		System.out.println("VehicleRoutingAlgorithm created successfully !!!");
		
		Collection<VehicleRoutingProblemSolution> problemSolutions = vehicleRoutingAlgorithm.searchSolutions();
		System.out.println("solution searches successfully !!");
		
		for (VehicleRoutingProblemSolution vehicleRoutingProblemSolution : problemSolutions) {
			System.out.println(vehicleRoutingProblemSolution);
		}
		
		System.out.println("best : " + Solutions.bestOf(problemSolutions)); 
		
		SolutionPrinter.print(problem, Solutions.bestOf(problemSolutions), Print.VERBOSE);
		System.out.println("-- end --");*/
		
	}
}









