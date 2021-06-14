package scene;

import java.util.LinkedList;
import java.util.List;

import elements.AmbientLight;
import elements.LightSource;
import geometries.Geometries;
import geometries.Intersectable;
import primitives.Color;

/**
 * Scene class represent the scene each scene has a name, background color,
 * AmbientLight, and geometries
 * 
 * @author Shmulik & Chananel
 */
public class Scene {

	/**
	 * Scene name
	 */
	public String name;
	/**
	 * background color for the scene
	 */
	public Color background = Color.BLACK;
	/**
	 * ambientLight color for the scene
	 */
	public AmbientLight ambientLight = new AmbientLight();
	/**
	 * geometries in the scene
	 */
	public Geometries geometries = new Geometries();
	/**
	 * lights in the scene
	 */
	public List<LightSource> lights = new LinkedList<LightSource>();

	/**
	 * Constructor of scene initialize scene with a name
	 * 
	 * @param name - the name of the scene
	 */
	public Scene(String name) {
		this.name = name;
	}

	/**
	 * set lights
	 * 
	 * @param lights - list of lights
	 * @return this
	 */
	public Scene setlights(List<LightSource> lights) {
		this.lights = lights;
		return this;
	}

	/**
	 * set background
	 * 
	 * @param background the background to set
	 * @return this
	 */
	public Scene setBackground(Color background) {
		this.background = background;
		return this;
	}

	/**
	 * set ambient light
	 * 
	 * @param ambientLight the ambientLight to set
	 * @return this
	 */
	public Scene setAmbientLight(AmbientLight ambientLight) {
		this.ambientLight = ambientLight;
		return this;
	}

	/**
	 * set geometries
	 * 
	 * @param geometries the geometries to set
	 * @return this
	 */
	public Scene setGeometries(Geometries geometries) {
		this.geometries = geometries;
		return this;
	}

	public void buildHierarchy() {
		geometries = buildHierarchy(geometries);
	}

	private Geometries buildHierarchy(Geometries currentGeometries) {
//		if (currentGeometries.getGeometriesList().isEmpty())
//			return null;
		if (currentGeometries.getGeometriesList().size() == 1)
			return new Geometries(currentGeometries);

		double[] minMax = currentGeometries.getMinMax();
		double center;
		char longestAxis;
		double longX = minMax[3] - minMax[0];
		double longY = minMax[4] - minMax[1];
		double longZ = minMax[5] - minMax[2];

		Geometries firstGeometries = new Geometries(); // גדול
		Geometries secondGeometries = new Geometries();

		if (longX >= longY && longX >= longZ) {
			longestAxis = 'x';
			center = minMax[0] + (minMax[3] - minMax[0]) / 2;
			for (Intersectable geometry : currentGeometries.getGeometriesList()) {
				double currentCenter = geometry.getCenter(longestAxis);
				if (currentCenter > center)
					firstGeometries.add(geometry);
				else
					secondGeometries.add(geometry);
			}
			
//			if (firstGeometries.getGeometriesList().size() == 0)
			
			if (firstGeometries.getGeometriesList().isEmpty() && secondGeometries.getGeometriesList().isEmpty())
				return new Geometries();
			if (firstGeometries.getGeometriesList().isEmpty()) {
				if(checkDeadlock(secondGeometries))
					return new Geometries(secondGeometries);
				return new Geometries(buildHierarchy(secondGeometries));
			}
				
			if (secondGeometries.getGeometriesList().isEmpty()) {
				if(checkDeadlock(firstGeometries))
					return new Geometries(firstGeometries);
				return new Geometries(buildHierarchy(firstGeometries));
			}
			return new Geometries(buildHierarchy(firstGeometries), buildHierarchy(secondGeometries));

//			currentGeometries = new Geometries(firstGeometries, secondGeometries);
//			firstGeometries = buildHierarchy(firstGeometries);
//			secondGeometries = buildHierarchy(secondGeometries);
//			return currentGeometries;
		} 
		else if (longY >= longX && longY >= longZ) {
			longestAxis = 'y';
			center = minMax[1] + (minMax[4] - minMax[1]) / 2;
			for (Intersectable geometry : currentGeometries.getGeometriesList()) {
				if (geometry.getCenter(longestAxis) > center)
					firstGeometries.add(geometry);
				else
					secondGeometries.add(geometry);
			}
			
			if (firstGeometries.getGeometriesList().isEmpty() && secondGeometries.getGeometriesList().isEmpty())
				return new Geometries();
			if (firstGeometries.getGeometriesList().isEmpty()) {
				if(checkDeadlock(secondGeometries))
					return new Geometries(secondGeometries);
				return new Geometries(buildHierarchy(secondGeometries));
			}
				
			if (secondGeometries.getGeometriesList().isEmpty()) {
				if(checkDeadlock(firstGeometries))
					return new Geometries(firstGeometries);
				return new Geometries(buildHierarchy(firstGeometries));
			}
			return new Geometries(buildHierarchy(firstGeometries), buildHierarchy(secondGeometries));
			
		} else {
			longestAxis = 'z';
			center = minMax[2] + (minMax[5] - minMax[2]) / 2;
			for (Intersectable geometry : currentGeometries.getGeometriesList()) {
				if (geometry.getCenter(longestAxis) > center)
					firstGeometries.add(geometry);
				else
					secondGeometries.add(geometry);
			}
			if (firstGeometries.getGeometriesList().isEmpty() && secondGeometries.getGeometriesList().isEmpty())
				return new Geometries();
			if (firstGeometries.getGeometriesList().isEmpty()) {
				if(checkDeadlock(secondGeometries))
					return new Geometries(secondGeometries);
				return new Geometries(buildHierarchy(secondGeometries));
			}
				
			if (secondGeometries.getGeometriesList().isEmpty()) {
				if(checkDeadlock(firstGeometries))
					return new Geometries(firstGeometries);
				return new Geometries(buildHierarchy(firstGeometries));
			}
			return new Geometries(buildHierarchy(firstGeometries), buildHierarchy(secondGeometries));
		}

	}

	public boolean checkDeadlock(Geometries currentGeometries) {

		double[] minMax = currentGeometries.getMinMax();
		double center;
		char longestAxis;
		double longX = minMax[3] - minMax[0];
		double longY = minMax[4] - minMax[1];
		double longZ = minMax[5] - minMax[2];

		Geometries firstGeometries = new Geometries(); // גדול
		Geometries secondGeometries = new Geometries();

		if (longX >= longY && longX >= longZ) {
			longestAxis = 'x';
			center = minMax[0] + (minMax[3] - minMax[0]) / 2;
			for (Intersectable geometry : currentGeometries.getGeometriesList()) {
				double currentCenter = geometry.getCenter(longestAxis);
				if (currentCenter > center)
					firstGeometries.add(geometry);
				else
					secondGeometries.add(geometry);
			}

			if (firstGeometries.getGeometriesList().isEmpty() || secondGeometries.getGeometriesList().isEmpty())
				return true;


		} else if (longY >= longX && longY >= longZ) {
			longestAxis = 'y';
			center = minMax[1] + (minMax[4] - minMax[1]) / 2;
			for (Intersectable geometry : currentGeometries.getGeometriesList()) {
				if (geometry.getCenter(longestAxis) > center)
					firstGeometries.add(geometry);
				else
					secondGeometries.add(geometry);
			}
			if (firstGeometries.getGeometriesList().isEmpty() || secondGeometries.getGeometriesList().isEmpty())
				return true;

		} else {
			longestAxis = 'z';
			center = minMax[2] + (minMax[5] - minMax[2]) / 2;
			for (Intersectable geometry : currentGeometries.getGeometriesList()) {
				if (geometry.getCenter(longestAxis) > center)
					firstGeometries.add(geometry);
				else
					secondGeometries.add(geometry);
			}
			if (firstGeometries.getGeometriesList().isEmpty() || secondGeometries.getGeometriesList().isEmpty())
				return true;
		}
		return false;

	}
}
