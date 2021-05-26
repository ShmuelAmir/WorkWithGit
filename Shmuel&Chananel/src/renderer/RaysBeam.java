package renderer;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import primitives.*;

/**
 * RaysBeam class represent beam that generate due to intersect of ray with uneven
 * @author Shmulik & Chananel
 *
 */
public class RaysBeam {
	 
	public class BlackBoard {
		private static final double DISTANCE = 100;
		
		private Point3D center;
		private Vector xAxis;
		private Vector yAxis;
		
		private double edge;

		
		/**
		 * construct the BlackBoard - center point, edge size and axes.
		 * 
		 * @param r - distance between center to edge
		 * @param ray - for direction and center point
		 */
		public BlackBoard(double r, Ray ray) {
			this.edge = 2 * r * DISTANCE;
			
			center = ray.getPoint(DISTANCE);
			
			Vector vTo = ray.getDir();
			
			// finding orthogonal vectors
			double x = vTo.dotProduct(new Vector(1, 0, 0));
			double y = vTo.dotProduct(new Vector(0, 1, 0));
			double z = vTo.dotProduct(new Vector(0, 0, 1));
			
			if (x == 0) {
				xAxis = new Vector(0, -z, y).normalize();
			}
			else {
				if (y == 0)
					xAxis = new Vector(z, 0, -x).normalize();
				
				else if (z == 0) 
					xAxis = new Vector(-y, x, 0).normalize();
			}
			
			yAxis = xAxis.crossProduct(vTo);
			
			// the length of the vectors
			xAxis.scale(edge / 2);
			yAxis.scale(edge / 2);
		}
		
	}
	
	private Point3D p0; // starting point for all rays
	private BlackBoard blackBoard; // the ending point for all rays
	private Random rand = new Random(); // for the move
	private List<Ray> rays = new LinkedList<>(); // to return
	
	/**
	 * number of rays to generate and return
	 */
	private static final int NUM_OF_RAYS = 4;
	
	/**
	 * construct the start point and the blackBoard according to the ray.
	 * 
	 * @param ray
	 */
	public RaysBeam(Ray ray) {
		p0 = ray.getP0();
		blackBoard = new BlackBoard(0, ray);
	}
	
	/**
	 * generate rays from p0 to the scope of the black board randomly.
	 * 
	 * @return list of rays
	 */
	public List<Ray> generateRays(){
		Point3D point;
		while (rays.size() < NUM_OF_RAYS) {
			// move the center point along the axes of the blackBoard
			point = blackBoard.center.add(blackBoard.xAxis.scale(rand.nextDouble()))
					.add(blackBoard.yAxis.scale(rand.nextDouble()));
			
			// construct a new ray
			rays.add(new Ray(p0, point.subtract(p0)));
			
		}
		
		return rays;
	}
	
}
