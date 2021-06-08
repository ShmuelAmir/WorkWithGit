package elements;

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
	 * ��� �� ����� �� ��� �� �����
	 * 
	 * @param min
	 * @param max
	 * @param ray
	 * @return
	 */
	public boolean checkIntersection(Ray ray) {
		Point3D p0 = ray.getP0();
		Vector dir = ray.getDir();
		Point3D rD = dir.getHead();

		double p0X = p0.getX();
		if (dir.equals(Vector.X) && (p0X > maxX || p0X < minX))
			return false;

		double p0Y = p0.getY();
		if (dir.equals(Vector.Y) && (p0Y > maxY || p0Y < minY))
			return false;

		double p0Z = p0.getZ();
		if (dir.equals(Vector.Z) && (p0Z > maxZ || p0Z < minZ))
			return false;

		double rDX = rD.getX();
		updateTStartTEnd(minX, maxX, p0X, rDX);
		// box is behind
		if (tEnd < 0)
			return false;

		double rDY = rD.getY();
		updateTStartTEnd(minY, maxY, p0Y, rDY);
		// box is missed or box is behind
		if (tStart > tEnd || tEnd < 0)
			return false;

		double rDZ = rD.getZ();
		updateTStartTEnd(minZ, maxZ, p0Z, rDZ);
		// box is missed or box is behind
		if (tStart > tEnd || tEnd < 0)
			return false;

		return true;
	}

	private void updateTStartTEnd(double min, double max, double p0, double rD) {
		double t1 = (min - p0) / rD;
		double t2 = (max - p0) / rD;
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

}