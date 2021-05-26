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
	 * Specular coefficient in the formula of Phong Model
	 */
	public double kS = 0;
	
	/**
	 * The refraction coefficient in the formula of Phong Model Transparency
	 * coefficient is the transmitted fraction where 𝒌𝑻 = 𝟏 when object is
	 * translucent, 𝒌𝑻 = 𝟎 when the object is opaque.
	 */
	public double kT = 0.0;

	/**
	 * the reflection coefficient in the formula of Phong Model Perfect mirror has a
	 * 𝒌𝑹 = 𝟏 and matt surface has a 𝒌𝑹 = 0
	 */
	public double kR = 0.0;
	
	/**
	 * The shininess of the material
	 */
	public int nShininess = 0;
	
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
	 * @return this
	 */
	public Material setnShininess(int nShininess) {
		this.nShininess = nShininess;
		return this;
	}

	/**
	 * set kT
	 * 
	 * @param kT - the kT to set
	 * @return this
	 */
	public Material setkT(double kT) {
		this.kT = kT;
		return this;
	}

	/**
	 * set kR
	 * 
	 * @param kR - the kR to set
	 * @return this
	 */
	public Material setkR(double kR) {
		this.kR = kR;
		return this;
	}

}
