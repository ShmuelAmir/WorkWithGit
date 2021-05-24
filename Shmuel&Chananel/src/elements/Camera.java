package elements;

import static primitives.Util.*;

import primitives.*;

/**
 * Camera class represents the viewing point of the scan the origin of the ray
 * that go to the shape
 * 
 * @author Shmulik & Chananel
 *
 */
public class Camera {
	// camera location and directions
	private Point3D p0;
	private Vector vUp;
	private Vector vTo;
	private Vector vRight;
	// width and height of the view plane
	private double width;
	private double height;
	// distance between the camera and the view plan
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
	 * Camera constructor based on the location point and two vector to direction of
	 * the camera
	 * 
	 * @param p0  camera location
	 * @param vUp vector upward of the camera
	 * @param vTo vector forward of the camera
	 * @throws IllegalArgumentException in case that vTo and vUp not orthogonal
	 */
	public Camera(Point3D p0, Vector vTo, Vector vUp) {
		// check if the vectors are orthogonal
		if (!isZero(vUp.dotProduct(vTo)))
			throw new IllegalArgumentException("vTo and vUp must be ortogonal");

		this.p0 = p0;
		this.vTo = vTo.normalized();
		this.vUp = vUp.normalized();
		this.vRight = vTo.crossProduct(vUp).normalized(); // vRight = vTo x vUp
	}

	/**
	 * This method set values to view plane size
	 * 
	 * @param width  - the width of the plan
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
	 * 
	 * @param distance - the distance between the camera and the view plan
	 * @return camera
	 */
	public Camera setDistance(double distance) {
		this.distance = distance;
		return this;
	}

	/**
	 * This method calculate the ray that begin in the camera and go thorough pixel
	 * 
	 * @param nX - the number of the pixel in the x axis
	 * @param nY - the number of the pixel in the y axis
	 * @param j  - the the location in the y axis
	 * @param i  - the the location in the x axis
	 * @return ray that begin in the camera and go thorough pixel
	 */
	public Ray constructRayThroughPixel(int nX, int nY, int j, int i) {
		Point3D pCenter = p0.add(vTo.scale(distance));

		// calculate the width and height ratio
		double rX = width / nX;
		double rY = height / nY;

		// calculate the location in x and y axis
		double xJ = (j - (nX - 1) / 2.0) * rX;
		double yI = ((nY - 1) / 2.0 - i) * rY;

		// pIJ = pC + vRight*xJ + vUp*yI
		Point3D pIJ = pCenter;
		if (xJ != 0)
			pIJ = pIJ.add(vRight.scale(xJ));
		if (yI != 0)
			pIJ = pIJ.add(vUp.scale(yI));

		return new Ray(p0, pIJ.subtract(p0));
	}

	/**
	 * move the camera to new point.
	 * 
	 * @param locationPoint    - the new location of the camera
	 * @param destinationPoint - where the camera is looking
	 */
	public void moveCamera(Point3D locationPoint, Point3D destinationPoint) {
		this.p0 = locationPoint;

		Vector oldVto = this.vTo;
		this.vTo = destinationPoint.subtract(locationPoint).normalize();

		Vector pivot; // Axis of rotation
		try {
			pivot = oldVto.crossProduct(vTo).normalize();

			double sinChange = vTo.crossProduct(oldVto).length();
			double cosChange = vTo.dotProduct(oldVto);

			updateVectors(sinChange, cosChange, pivot);
		} catch (IllegalArgumentException e) { // if old vTo and new vTo are parallel.

			if (vTo.dotProduct(oldVto) < 0) {
				vRight = vRight.scale(-1);
				vUp = vUp.scale(-1);
			}
		}
	}

	/**
	 * calculate the new vRight and vUp vectors according to Rodrigues' rotation
	 * formula (vRot = v cos(a) + (k x v) sin(a) + k (k * v)(1- cos(a))).
	 * 
	 * @param sinChange - sin(a) in the formula
	 * @param cosChange - cos(a) in the formula
	 * @param pivot     - Axis of rotation
	 */
	private void updateVectors(double sinChange, double cosChange, Vector pivot) {
		Vector newVRight;

		double pDotVright = pivot.dotProduct(this.vRight);

		if (isZero(cosChange)) {
			try {
				newVRight = pivot.crossProduct(this.vRight).scale(sinChange); // (k x v) sin(a)
			} catch (IllegalArgumentException e) {
				newVRight = pivot;
			}

			if (!isZero(pDotVright))
				newVRight = newVRight.add(pivot.scale(pDotVright)); // k (k * v)

		} else {
			newVRight = vRight.scale(cosChange); // v cos(a)
			try {
				newVRight = newVRight.add(pivot.crossProduct(this.vRight).scale(sinChange)); // (k x v) sin(a)

			} catch (IllegalArgumentException e) {
				newVRight = pivot;
			}

			if ((!isZero(pDotVright)) && !(isZero(1 - cosChange)))
				newVRight = newVRight.add(pivot.scale(pDotVright).scale(1 - cosChange)); // k (k * v)(1- cos(a))
		}

		this.vRight = newVRight.normalize();
		this.vUp = vRight.crossProduct(vTo);
	}

	/**
	 * Rotate the camera Counterclockwise.
	 * 
	 * @param angle - the angle to Rotate the camera.
	 */
	public void CameraRotation(double angle) {
		double cosChange = Math.cos((angle * Math.PI) / 180);
		double sinChange = Math.sin((angle * Math.PI) / 180);
		
		Vector newVRight;

		if (isZero(cosChange))
			newVRight = vTo.crossProduct(vRight).scale(sinChange); // (k x v) sin(a)
		else {
			newVRight = vRight.scale(cosChange); // v cos(a)
			if (!isZero(sinChange))
				newVRight = newVRight.add(vTo.crossProduct(vRight).scale(sinChange)); // (k x v) sin(a)
		}

		vRight = newVRight.normalize();
		vUp = vRight.crossProduct(vTo);
	}

}
