/**
 * 
 */
package elements;

import static primitives.Util.*;

import primitives.*;

/**
 * @author Shmulik Chananel
 *
 */
public class Camera {
	private Point3D p0;
	private Vector vUp;
	private Vector vTo;
	private Vector vRight;
	private double width;
	private double height;
	private double distance;

	/**
	 * @return the p0
	 */
	public Point3D getP0() {
		return p0;
	}

	/**
	 * @return the vUp
	 */
	public Vector getvUp() {
		return vUp;
	}

	/**
	 * @return the vTo
	 */
	public Vector getvTo() {
		return vTo;
	}

	/**
	 * @return the vRight
	 */
	public Vector getvRight() {
		return vRight;
	}

	/**
	 * @param p0
	 * @param vUp
	 * @param vTo
	 */
	public Camera(Point3D p0, Vector vUp, Vector vTo) {
		this.p0 = p0;
		this.vUp = vUp.normalized();
		this.vTo = vTo.normalized();
		if (! isZero(vUp.dotProduct(vTo)))
			throw new IllegalArgumentException("///////");
		vRight = vTo.crossProduct(vUp).normalized();

	}

	public Camera setViewPlaneSize(double width, double height) {
		this.width = width;
		this.height = height;
		return this;
	}

	public Camera setDistance(double distance) {
		this.distance = distance;
		return this;
	}

	public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
		Point3D pCenter = p0.add(vTo.scale(distance));
		
		double rX = width / nX;
		double rY = height / nY;
		
		double xJ = (j - ((nX - 1) / 2.0)) * rX;
		double yI = (-1) * (i - ((nY - 1) / 2.0)) * rY;
		
		Point3D pIJ = pCenter;
		if (xJ != 0) 
			pIJ = pIJ.add(vRight.scale(xJ));
		if (yI != 0) 
			pIJ = pIJ.add(vUp.scale(yI));
		
		return new Ray(p0, pIJ.subtract(p0));
//		return new Ray(p0, p0.subtract(pIJ));
	}

}
