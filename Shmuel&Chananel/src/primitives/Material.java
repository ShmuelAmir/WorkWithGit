/**
 * 
 */
package primitives;

/**
 * Material class represent a material of a body in our scene. This class need to 
 * implement The Phong Reflectance Model  - that include 3 Components - specular diffuse and emission
 * @author Shmulik & Chananel
 *
 */
public class Material {
	// the coefficient in the formula of Phong Reflectance Model
	//kD - for diffuse, kS for specular
	public double kD = 0, kS = 0;
	//nShininess - the Shininess of the material
	public int nShininess = 0;

	/**
	 * @param kD the kD to set
	 */
	public Material setkD(double kD) {
		this.kD = kD;
		return this;
	}

	/**
	 * @param kS the kS to set
	 */
	public Material setkS(double kS) {
		this.kS = kS;
		return this;
	}

	/**
	 * @param nShininess the nShininess to set
	 */
	public Material setnShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}

}
