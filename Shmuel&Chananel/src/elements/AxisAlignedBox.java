package elements;

import static primitives.Util.*;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

/**
 * @author shmulik
 *
 */
public class AxisAlignedBox {
	private double minX;
	private double minY;
	private double minZ;
	private double maxX;
	private double maxY;
	private double maxZ;

	private double tStart = Double.NEGATIVE_INFINITY;
	private double tEnd = Double.POSITIVE_INFINITY;

	public AxisAlignedBox() {
		
	}
	/**
	 * 
	 */
	public AxisAlignedBox(double minMax[]) {
		minX = minMax[0];
		minY = minMax[1];
		minZ = minMax[2];
		maxX = minMax[3];
		maxY = minMax[4];
		maxZ = minMax[5];
	}

	/**
	 * האם יש חיתוך של קרן עם קופסא
	 * 
	 * @param ray
	 * @return
	 */
	public boolean checkIntersection(Ray ray) {
		Point3D p0 = ray.getP0();
		Vector dir = ray.getDir();
		Point3D rD = dir.getHead();

		double p0X = p0.getX();
		double rDX = rD.getX();
		if (isZero(rDX) && (p0X > maxX || p0X < minX))
			return false;

		double p0Y = p0.getY();
		double rDY = rD.getY();
		if (isZero(rDY) && (p0Y > maxY || p0Y < minY))
			return false;

		double p0Z = p0.getZ();
		double rDZ = rD.getZ();
		if (isZero(rDZ) && (p0Z > maxZ || p0Z < minZ))
			return false;

		updateTStartTEnd(minX, maxX, p0X, 1 / rDX);
		// box is behind
		if (tEnd < 0)
			return false;

		updateTStartTEnd(minY, maxY, p0Y, 1 / rDY);
		// box is missed or box is behind
		if (tStart > tEnd || tEnd < 0)
			return false;

		updateTStartTEnd(minZ, maxZ, p0Z, 1 / rDZ);
		// box is missed or box is behind
		if (tStart > tEnd || tEnd < 0)
			return false;

		return true;
	}

	private void updateTStartTEnd(double min, double max, double p0, double rD) {
		double t1 = (min - p0) * rD;
		double t2 = (max - p0) * rD;
		if (t1 > t2) {
			double temp = t1;
			t1 = t2;
			t2 = temp;
		}

		if (t1 > tStart)
			tStart = t1;
		if (t2 < tEnd)
			tEnd = t2;
	}

	public double getCenter(char axis) {
		if (axis == 'x')
			return minX + ((maxX - minX) / 2);
		
		else if (axis == 'y')
			return  minY + ((maxY - minY) / 2);

		else
			return  minZ + ((maxZ - minZ) / 2);
	}

}
