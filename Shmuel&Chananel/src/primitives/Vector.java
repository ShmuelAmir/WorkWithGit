package primitives;

public class Vector {
	Point3D head;

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

	public Vector add(Vector vector) {
		return new Vector(this.head.x.coord + vector.head.x.coord, this.head.y.coord + vector.head.y.coord,
				this.head.z.coord + vector.head.z.coord);

	}

	public Vector subtract(Vector vector) {
		return new Vector(this.head.x.coord - vector.head.x.coord, this.head.y.coord - vector.head.y.coord,
				this.head.z.coord - vector.head.z.coord);

	}

	public Vector scale(double num) {
		return new Vector(this.head.x.coord * num, this.head.y.coord * num, this.head.z.coord * num);

	}

	public double dotProduct(Vector vector) {
		double sum;
		sum = this.head.x.coord * vector.head.x.coord + this.head.y.coord * vector.head.y.coord
				+ this.head.z.coord * vector.head.z.coord;
		return sum;

	}

	public Vector crossProduct(Vector vector) {
		Coordinate x = new Coordinate(
				this.head.y.coord * vector.head.z.coord - this.head.z.coord * vector.head.y.coord);
		Coordinate y = new Coordinate(
				this.head.z.coord * vector.head.x.coord - this.head.x.coord * vector.head.z.coord);
		Coordinate z = new Coordinate(
				this.head.x.coord * vector.head.y.coord - this.head.y.coord * vector.head.x.coord);

		return new Vector(x, y, z);

	}

	public double lengthSquared() {
		return (this.head.x.coord * this.head.x.coord + this.head.y.coord * this.head.y.coord
				+ this.head.z.coord * this.head.z.coord);

	}

	public double length() {
		return Math.sqrt(this.lengthSquared());
	}

	public Vector normalize() {
		Point3D newHead = new Point3D(this.head.x.coord / this.length(), this.head.y.coord / this.length(), this.head.z.coord / this.length());

		this.head = newHead; 
		return this;
	}

	public Vector normalized  (){
		Vector vector = new Vector(this.head);
		return vector.normalize();
	}
				
		
	
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
