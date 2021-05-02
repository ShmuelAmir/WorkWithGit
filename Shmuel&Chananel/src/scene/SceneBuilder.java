/**
 * 
 */
package scene;

import java.io.File;
import java.util.ArrayList;

import elements.AmbientLight;
import geometries.Sphere;
import geometries.Triangle;
import parser.SceneDescriptor;
import primitives.Color;
import primitives.Point3D;

/**
 * SceneBuilder is a class that initialize a scene according to the XML fill
 * we chose to use Document Object Model so the architecture of this way to open XML fill
 *include create scene object
 * 
 * @author shmulik
 *
 */
public class SceneBuilder {
	// will save the string thar represent scene
	SceneDescriptor descriptor;
	// will save the scene
	Scene scene = new Scene("XML Test scene");
	
	/**
	 * this method initialize the scene object from the XML fill
	 * @param fileName - the name of the fill in the workspace
	 * @return scene object
	 */
	public Scene getScaneFromeXml(String fileName) {
		descriptor = new SceneDescriptor();
		descriptor.getStringFromXml(fileName);
		
		scene.setAmbientLight(new AmbientLight(getColor(descriptor.ambientLightAttributes.get("Color")), 1))
				.setBackground(getColor(descriptor.sceneAttributes.get("backgroundColor")));

		scene.geometries.add(new Sphere(getPoint(descriptor.spheres.get(0).get("center")), Double.parseDouble(descriptor.spheres.get(0).get("radius"))),
				new Triangle(getPoint(descriptor.triangles.get(0).get("p0")), getPoint(descriptor.triangles.get(0).get("p1")), getPoint(descriptor.triangles.get(0).get("p2"))), // ^ <
				new Triangle(getPoint(descriptor.triangles.get(1).get("p0")), getPoint(descriptor.triangles.get(1).get("p1")), getPoint(descriptor.triangles.get(1).get("p2"))), // ^ >
				new Triangle(getPoint(descriptor.triangles.get(2).get("p0")), getPoint(descriptor.triangles.get(2).get("p1")), getPoint(descriptor.triangles.get(2).get("p2"))), // v <
				new Triangle(getPoint(descriptor.triangles.get(3).get("p0")), getPoint(descriptor.triangles.get(3).get("p1")), getPoint(descriptor.triangles.get(3).get("p2")))); // v >
		
		return scene;
	}
	
	
	
	
	/**
	 * get point from string
	 * @param str - the string that represent point
	 * @return Point3D object
	 */
	public Point3D getPoint(String str) {
		ArrayList<Double> doubles = getdouble(str);

		return new Point3D(doubles.get(0), doubles.get(1), doubles.get(2));
	}

	/**
	 * get Color from string
	 * @param str - the string that represent Color
	 * @return Color
	 */
	public Color getColor(String str) {
		ArrayList<Double> doubles = getdouble(str);

		return new Color(doubles.get(0), doubles.get(1), doubles.get(2));
	}

	/**
	 * get double from string
	 * @param str - the string that represent double
	 * @return List of number
	 */
	public ArrayList<Double> getdouble(String str) {
		ArrayList<Double> numList = new ArrayList<>();
		String[] numText = str.split(" ");
		for (int i = 0; i < numText.length; i++) {
			numList.add(Double.parseDouble(numText[i]));
		}
		return numList;
	}
	
}
