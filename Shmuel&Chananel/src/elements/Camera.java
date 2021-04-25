/**
 * 
 */
package elements;

import static primitives.Util.*;

import primitives.*;

/**
 * Camera class represents the viewing point of the scan the origin of the ray that go to the shape
 * @author Shmulik Chananel
 *
 */
public class Camera {
	/**
	 * camera location
	 */
	private Point3D p0;
	/**
	 * vector upward of the camera
	 */
	private Vector vUp;
	/**
	 * vector forward of the camera
	 */
	private Vector vTo;
	/**
	 * vector in the right direction of the camera
	 */
	private Vector vRight;
	/**
	 * the width of view plane
	 */
	private double width;
	/**
	 * the height of view plane
	 */
	private double height;
	/**
	 * the distance between the camera and the view plan
	 */
	private double distance;

	/**
	 * get method 
	 * @return the p0
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * get method 
	 * @return the vUp
	 */
	public Vector getvUp() {
		return vUp;
	}

	/**
	 * get method 
	 * @return the vTo
	 */
	public Vector getvTo() {
		return vTo;
	}

	/**
	 * get method 
	 * @return the vRight
	 */
	public Vector getvRight() {
		return vRight;
	}

	/**
	 * Camera constructor based on the location point and two vector to direction of the camera 
	 * @param p0 camera location
	 * @param vUp vector upward of the camera
	 * @param vTo vector forward of the camera
	 * @throws IllegalArgumentException in case that vTo and vUp not orthogonal
	 */
	public Camera(Point3D p0, Vector vTo, Vector vUp) {
		this.p0 = p0;
		this.vTo = vTo.normalized();
		this.vUp = vUp.normalized();
		// We check if the vector is orthogonal by dotProduct - if the result is 0 the vectors are orthogonal 
		if (! isZero(vUp.dotProduct(vTo)))
			throw new IllegalArgumentException("vTo and vUp must be ortogonal");
		// After we check if the vector is orthogonal we can calculate the other vector by crossProduct
		vRight = vTo.crossProduct(vUp).normalized();

	}
	/**
	 *  This method set values to view plane size
	 * @param width - the width of the plan
	 * @param height - the height of the plan
	 * @return camera 
	 */
	public Camera setViewPlaneSize(double width, double height) {
		this.width = width;
		this.height = height;
		return this;
	}
	/**
	 * This method set the distance between the camera and the view plan
	 * @param distance - the distance between the camera and the view plan
	 * @return camera
	 */
	public Camera setDistance(double distance) {
		this.distance = distance;
		return this;
	}
	
	/**
	 * This method calculate the ray that begin in the camera and go thorough pixel
	 * @param nX - the number of the pixel in the x axis
	 * @param nY - the number of the pixel in the y axis
	 * @param j -  the the location in the y axis
	 * @param i -  the the location in the x axis
	 * @return ray that begin in the camera and go thorough pixel
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
		/**
		 *  save the image center
		 */
		Point3D pCenter = p0.add(vTo.scale(distance));
		
		// calculate and save the ratio 
		/**
		 * ratio pixel width
		 */
		double rX = width / nX;
		/**
		 *  ratio pixel height
		 */
		double rY = height / nY;
		/**
		 * calculate and save the location in x axis
		 */
		double xJ = (j - ((nX - 1) / 2.0)) * rX;
		/**
		 * calculate and save the location in y axis
		 */
		double yI = (-1) * (i - ((nY - 1) / 2.0)) * rY;
		
		// in the beginning we initialize pIJ in the center location and after we move the point to the desired place 
		Point3D pIJ = pCenter;
		//this if need to prevent scale vector by zero
		if (xJ != 0) 
			pIJ = pIJ.add(vRight.scale(xJ));
		if (yI != 0) 
			pIJ = pIJ.add(vUp.scale(yI));
		
		return new Ray(p0, pIJ.subtract(p0));
	}

}
