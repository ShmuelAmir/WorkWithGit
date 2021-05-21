package primitives;

/**
 * Material class represent a material of a body in our scene. This class need
 * to implement The Phong Reflectance Model - that include 3 Components -
 * specular, diffuse and emission
 * 
 * @author Shmulik & Chananel
 *
 */
public class Material {
	/**
	 * diffuse coefficient in the formula of Phong Model
	 */
	public double kD = 0;
	/**
	 * specular coefficient in the formula of Phong Model
	 */
	public double kS = 0;
	/**
	 * the Shininess of the material
	 */
	public int nShininess = 0;
	
	/**
	 * The Refraction  - //in hebrow shkifot
	 */
	public double kT = 0.0;
	
	/**
	 * Reflection   -  //in hebrow histakfot
	 */
	public double kR = 0.0;
	
	

	/**
	 * set kd
	 * 
	 * @param kD - the kD to set
	 * @return this
	 */
	public Material setkD(double kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * set ks
	 * 
	 * @param kS - the kS to set
	 * @return this
	 */
	public Material setkS(double kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * set nShininess
	 * 
	 * @param nShininess - the nShininess to set
	 */
	public Material setnShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}
	
	/**
	 * 
	 * @param kD
	 * @return
	 */
	public Material setkT(double kT) {
		this.kT = kT;
		return this;
	}
	
	/**
	 * 
	 * @param kD
	 * @return
	 */
	public Material setkR(double kR) {
		this.kR = kR;
		return this;
	}
	
	
		
		
		

}
