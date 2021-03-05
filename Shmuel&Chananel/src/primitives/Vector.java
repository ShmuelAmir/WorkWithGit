package primitives;

/**
 * 
 * 
 *
 */
public class Vector {
	private Point3D head;

	/**
	 * constructor with 3 double number
	 * 
	 * @param head
	 */
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		Point3D check = new Point3D(x, y, z);
		if (check.equals(Point3D.ZERO)) {
			throw new IllegalArgumentException("No vector 0");
		}
		this.head = new Point3D(x, y, z);
	}

	/**
	 * constructor with 3 double number
	 * 
	 * @param head
	 */
	public Vector(double x, double y, double z) {
		Point3D check = new Point3D(x, y, z);
		if (check.equals(Point3D.ZERO)) {
			throw new IllegalArgumentException("No vector 0");
		}
		this.head = new Point3D(x, y, z);
	}

	/**
	 * constructor with point
	 * 
	 * @param head
	 */
	public Vector(Point3D head) {
		this.head = new Point3D(head.x, head.y, head.z);
	}

	/**
	 * @return the p0
	 */
	public Point3D getHead() {
		return head;
	}

	/**
	 * Vector addition
	 * 
	 * @param vector
	 * @return result vector
	 */
	public Vector add(Vector vector) {
		return new Vector(this.head.x.coord + vector.head.x.coord, this.head.y.coord + vector.head.y.coord,
				this.head.z.coord + vector.head.z.coord);
	}

	/**
	 * Vector subtraction
	 * 
	 * @param vector
	 * @return result vector
	 */
	public Vector subtract(Vector vector) {
		return new Vector(this.head.x.coord - vector.head.x.coord, this.head.y.coord - vector.head.y.coord,
				this.head.z.coord - vector.head.z.coord);
	}

	/**
	 * Vector multiplication by number
	 * 
	 * @param num - scalar
	 * @return vector
	 */
	public Vector scale(double num) {
		return new Vector(this.head.x.coord * num, this.head.y.coord * num, this.head.z.coord * num);
	}

	/**
	 * Scalar product between tow vector
	 * 
	 * @param vector
	 * @return result number
	 */
	public double dotProduct(Vector vector) {
		double sum;
		sum = this.head.x.coord * vector.head.x.coord + this.head.y.coord * vector.head.y.coord
				+ this.head.z.coord * vector.head.z.coord;
		return sum;
	}

	/**
	 * cross product between tow vector
	 * 
	 * @param vector
	 * @return a new vector that perpendicular for two vectors
	 */
	public Vector crossProduct(Vector vector) {
		Coordinate x = new Coordinate(
				this.head.y.coord * vector.head.z.coord - this.head.z.coord * vector.head.y.coord);
		Coordinate y = new Coordinate(
				this.head.z.coord * vector.head.x.coord - this.head.x.coord * vector.head.z.coord);
		Coordinate z = new Coordinate(
				this.head.x.coord * vector.head.y.coord - this.head.y.coord * vector.head.x.coord);

		return new Vector(x, y, z);
	}

	/**
	 * Calculate the length of the vector squared
	 * 
	 * @return result number
	 */
	public double lengthSquared() {
		return (this.head.x.coord * this.head.x.coord + this.head.y.coord * this.head.y.coord
				+ this.head.z.coord * this.head.z.coord);
	}

	/**
	 * Calculate the length of the vector
	 * 
	 * @return result number
	 */
	public double length() {
		return Math.sqrt(this.lengthSquared());
	}

	/**
	 * A vector normalization operation that will change the vector itself
	 * 
	 * @return current vector normalization
	 */
	public Vector normalize() {
		Point3D newHead = new Point3D(this.head.x.coord / this.length(), this.head.y.coord / this.length(),
				this.head.z.coord / this.length());

		this.head = newHead;
		return this;
	}

	/**
	 * A vector normalization operation that will create new vector
	 * 
	 * @return new vector
	 */
	public Vector normalized() {
		Vector vector = new Vector(this.head);
		return vector.normalize();
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Vector))
			return false;
		Vector other = (Vector) obj;
		return this.head.equals(other.head);
	}

	@Override
	public String toString() {
		return head.toString();
	}
}
