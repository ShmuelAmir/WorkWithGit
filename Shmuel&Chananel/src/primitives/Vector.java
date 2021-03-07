package primitives;

/**
 * Class Vector represents size and direction (according to the end point when
 * the starting point at the beginning of the axes)
 * 
 * @author shmulik
 *
 */
public class Vector {
	private Point3D head;

	/**
	 * constructor with 3 coordinates
	 * 
	 * @param x - The x coordinate
	 * @param y - The y coordinate
	 * @param z - The z coordinate
	 */
	public Vector(Coordinate x, Coordinate y, Coordinate z) {
		Point3D check = new Point3D(x, y, z);
		if (check.equals(Point3D.ZERO)) {
			throw new IllegalArgumentException("The zero vector is not defined");
		}
		this.head = check;
	}

	/**
	 * constructor with 3 double numbers
	 * 
	 * @param x - number for the x coordinate
	 * @param y - number for the y coordinate
	 * @param z - number for the z coordinate
	 */
	public Vector(double x, double y, double z) {
		Point3D check = new Point3D(x, y, z);
		if (check.equals(Point3D.ZERO)) {
			throw new IllegalArgumentException("The zero vector is not defined");
		}
		this.head = check;
	}

	/**
	 * constructor with point
	 * 
	 * @param head - The head point
	 */
	public Vector(Point3D head) {
		this.head = head;
	}

	/**
	 * @return the p0
	 */
	public Point3D getHead() {
		return head;
	}

	/**
	 * Vector addition [u+v = (u1+v1, u2+v2, u3+v3)]
	 * 
	 * @param vector - The second vector
	 * @return The result vector
	 */
	public Vector add(Vector vector) {
		return new Vector(this.head.x.coord + vector.head.x.coord, this.head.y.coord + vector.head.y.coord,
				this.head.z.coord + vector.head.z.coord);
	}

	/**
	 * Vector subtraction [u-v = (u1-v1, u2-v2, u3-v3)]
	 * 
	 * @param vector - The second vector
	 * @return The result vector
	 */
	public Vector subtract(Vector vector) {
		return new Vector(this.head.x.coord - vector.head.x.coord, this.head.y.coord - vector.head.y.coord,
				this.head.z.coord - vector.head.z.coord);
	}

	/**
	 * Vector multiplication by number [a*v = (a*x, a*y, a*z)]
	 * 
	 * @param num - The scalar
	 * @return The result vector
	 */
	public Vector scale(double num) {
		return new Vector(num * this.head.x.coord, num * this.head.y.coord, num * this.head.z.coord);
	}

	/**
	 * Scalar product between tow vectors [u*v = u1*v1 + u2*v2 + u3*v3]
	 * 
	 * @param vector - The second vector
	 * @return The result number
	 */
	public double dotProduct(Vector vector) {
		return this.head.x.coord * vector.head.x.coord + this.head.y.coord * vector.head.y.coord
				+ this.head.z.coord * vector.head.z.coord;
	}

	/**
	 * cross product between tow vectors [uXv = (u2*v3-u3*v2, u3*v1-u1*v3,
	 * u1*v2-u2*v1)]
	 * 
	 * @param - The second vector
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
	 * Calculate the length of the vector squared [x^2 + y^2 + z^2]
	 * 
	 * @return length of the vector squared
	 */
	public double lengthSquared() {
		return this.head.x.coord * this.head.x.coord + this.head.y.coord * this.head.y.coord
				+ this.head.z.coord * this.head.z.coord;
	}

	/**
	 * Calculate the length of the vector [sqrt(x^2 + y^2 + z^2)]
	 * 
	 * @return length of the vector
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
		this.head = new Point3D(this.head.x.coord / this.length(), this.head.y.coord / this.length(),
				this.head.z.coord / this.length());

		return this;
	}

	/**
	 * A vector normalization operation that will create a new vector
	 * 
	 * @return The new vector
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