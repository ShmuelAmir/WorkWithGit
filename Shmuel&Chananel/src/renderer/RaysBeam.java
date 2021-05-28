package renderer;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import primitives.*;
import static primitives.Util.*;

/**
 * RaysBeam class represent beam that generate due to intersect of a ray with uneven material. 
 * This class will generate the list of the rays that rayTracerBasic class will use to determine the color of  a particular pixel
 * @author Shmulik & Chananel
 *
 */
public class RaysBeam {
	 
	/**
	  * BlackBoard class represent a circle that location in a fix place from the intersection point.
	  * This shape will help us to determine the scattering of the beam
	  * 
	  * @author Shmulik & Chananel
	  */
	public class BlackBoard {
		/**
		 * this is a fix value that represent the distance between the intersection point and
		 * the BlackBoard
		 */
		private static final double DISTANCE = 100;
		
		private Point3D center;
		private Vector xAxis;
		private Vector yAxis;
		
		private double halfEdge;  

		
		/**
		 * construct the BlackBoard - center point, halfEdge size and axes.
		 * 
		 * @param r - distance between center to halfEdge
		 * @param ray - for direction and center point
		 */
		public BlackBoard(double r, Ray ray) {
			this.halfEdge = r * DISTANCE;
			
			center = ray.getPoint(DISTANCE);
			
			Vector vTo = ray.getDir();
			
			// finding orthogonal vectors
			double x = vTo.dotProduct(Vector.X);
			double y = vTo.dotProduct(Vector.Y);
			double z = vTo.dotProduct(Vector.Z);
			
			if (x == 0) {
				xAxis = new Vector(0, -z, y).normalize();
			}
			else {
				if (y == 0)
					xAxis = new Vector(z, 0, -x).normalize();
				
				else if (z == 0) 
					xAxis = new Vector(-y, x, 0).normalize();
			}
			
			if(x != 0 && y != 0 && z != 0)
				xAxis = new Vector(-y, x, 0).normalize();
			
			yAxis = xAxis.crossProduct(vTo);
			
		}
		
	}
	
	private Point3D p0; // starting point for all rays
	private BlackBoard blackBoard; // the ending point for all rays
	private Random rand = new Random(); // for the move
	private List<Ray> rays = new LinkedList<>(); // to return
	
	/**
	 * number of rays to generate and return
	 */
	private static final int NUM_OF_RAYS = 80;
	
	/**
	 * construct the start point and the blackBoard according to the ray.
	 * 
	 * @param ray
	 */
	public RaysBeam(Ray ray, double ky) {
		p0 = ray.getP0();
		blackBoard = new BlackBoard(ky, ray);
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
			point = blackBoard.center.add(blackBoard.xAxis.scale((rand.nextDouble()*2-1)*blackBoard.halfEdge))
					.add(blackBoard.yAxis.scale((rand.nextDouble()*2-1)*blackBoard.halfEdge));
			
			// construct a new ray
			if (alignZero(point.distance(blackBoard.center) - blackBoard.halfEdge) < 0)
				rays.add(new Ray(p0, point.subtract(p0)));
			
		}
		
		return rays;
	}
	
}
