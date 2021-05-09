package scene;

import java.util.LinkedList;
import java.util.List;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import primitives.Color;


/**
 * Scene class represent the scene each scene has a name, background color, AmbientLight, and geometries
 * 
 * @author Shmulik & Chananel
 */
public class Scene {
	// this is the fields of scene : name, background color, AmbientLight, and geometries
	public String name;
	public Color background = Color.BLACK;
	public AmbientLight ambientLight= new AmbientLight();
	public Geometries geometries = new Geometries();
	public List<LightSource> lights = new LinkedList<LightSource>();
	
	/**
	 * Constructor of scene initialize scene with a name  
	 * @param name - the name of the scene
	 */
	public Scene(String name) {
		this.name = name;
	}
	
	public Scene setlights(List<LightSource> lights) {
		this.lights = lights;
		return this;
	}


	/**
	 * set background
	 * @param background the background to set
	 * @return this
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}

	/** 
	 * set ambient light
	 * @param ambientLight the ambientLight to set
	 * @return this
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	/**
	 * set geometries
	 * @param geometries the geometries to set
	 * @return this
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}
	
}
