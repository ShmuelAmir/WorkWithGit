package renderer;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import primitives.*;

/**
 * @author shmulik
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
		 * @param edge
		 */
		public BlackBoard(double r, Ray ray) {
			this.edge = 2 * r * DISTANCE;
			
			center = ray.getPoint(DISTANCE);
			
			Vector vTo = ray.getDir();
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
			xAxis.scale(edge / 2);
			yAxis.scale(edge / 2);
		}
		
	}
	
	Random rand = new Random();
	Point3D p0;
	BlackBoard blackBoard;
	List<Ray> rays = new LinkedList<>();
	private static final int NUM_OF_RAYS = 4;
	
	/**
	 * 
	 * @param ray
	 */
	public RaysBeam(Ray ray) {
		p0 = ray.getP0();
		blackBoard = new BlackBoard(0, ray);
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Ray> generateRays(){
		Point3D point;
		while (rays.size() < NUM_OF_RAYS) {
			point = blackBoard.center.add(blackBoard.xAxis.scale(rand.nextDouble()))
					.add(blackBoard.yAxis.scale(rand.nextDouble()));
			
			rays.add(new Ray(p0, point.subtract(p0)));
			
		}
		
		return rays;
	}
	
	
}
