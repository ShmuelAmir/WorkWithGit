package unittests;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * Test rendering a basic image
 * 
 * @author Dan
 */
public class RenderTests {
	private Camera camera = new Camera(Point3D.ZERO, new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setDistance(100) //
			.setViewPlaneSize(500, 500);

	/**
	 * Produce a scene with basic 3D model and render it into a jpeg image with a
	 * grid
	 */
	@Test
	public void basicRenderTwoColorTest() {

		Scene scene = new Scene("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1)) //
				.setBackground(new Color(75, 127, 90));

		scene.geometries.add(new Sphere(new Point3D(0, 0, -100), 50),
				new Triangle(new Point3D(-100, 0, -100), new Point3D(0, 100, -100), new Point3D(-100, 100, -100)),
				new Triangle(new Point3D(100, 0, -100), new Point3D(0, 100, -100), new Point3D(100, 100, -100)),
				new Triangle(new Point3D(-100, 0, -100), new Point3D(0, -100, -100), new Point3D(-100, -100, -100)),
				new Triangle(new Point3D(100, 0, -100), new Point3D(0, -100, -100), new Point3D(100, -100, -100)));

		ImageWriter imageWriter = new ImageWriter("base render test", 1000, 1000);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setScene(scene) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.printGrid(100, new Color(java.awt.Color.YELLOW));
		render.writeToImage();
	}

	/**
	 * Test for XML based scene - for bonus
	 */
	@Test
	public void basicRenderXml() {
		Scene scene = new Scene("XML Test scene");

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		String ambientLightColor = null, backgroundColor = null, c = null;
		double r = 0;
		List<String[]> ss = new ArrayList<>();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse("basicRenderTestTwoColors.xml");

			Element root = doc.getDocumentElement();
			backgroundColor = root.getAttribute("background-color");
			NodeList n = root.getElementsByTagName("ambient-light");
			Element alc = (Element) n.item(0);
			ambientLightColor = alc.getAttribute("color");

			NodeList geList = root.getElementsByTagName("geometries");
			Element geom = (Element) geList.item(0);

			NodeList spList = geom.getElementsByTagName("sphere");
			Element sp = (Element) spList.item(0);
			r = Double.parseDouble(sp.getAttribute("radius"));
			c = sp.getAttribute("center");

			NodeList triangleList = geom.getElementsByTagName("triangle");
			for (int i = 0; i < triangleList.getLength(); i++) {
				Node t = triangleList.item(i);
				String[] ps = new String[3];
				if (t.getNodeType() == Node.ELEMENT_NODE) {
					Element tt = (Element) t;
					ps[0] = tt.getAttribute("p0");
					ps[1] = tt.getAttribute("p1");
					ps[2] = tt.getAttribute("p2");
				}
				ss.add(ps);
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		scene.setAmbientLight(new AmbientLight(getColor(ambientLightColor), 1))
				.setBackground(getColor(backgroundColor));

		String[] t0 = ss.get(0);
		String[] t1 = ss.get(1);
		String[] t2 = ss.get(2);
		String[] t3 = ss.get(3);
		scene.geometries.add(new Sphere(getPoint(c), r),
				new Triangle(getPoint(t0[0]), getPoint(t0[1]), getPoint(t0[2])), // ^ <
				new Triangle(getPoint(t1[0]), getPoint(t1[1]), getPoint(t1[2])), // ^ >
				new Triangle(getPoint(t2[0]), getPoint(t2[1]), getPoint(t2[2])), // v <
				new Triangle(getPoint(t3[0]), getPoint(t3[1]), getPoint(t3[2]))); // v >

		ImageWriter imageWriter = new ImageWriter("xml render test", 1000, 1000);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setScene(scene) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.printGrid(100, new Color(java.awt.Color.YELLOW));
		render.writeToImage();
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public Point3D getPoint(String str) {
		ArrayList<Double> doubles = getdouble(str);

		return new Point3D(doubles.get(0), doubles.get(1), doubles.get(2));
	}

	/**
	 * 
	 * @param str
	 * @return
	 */
	public Color getColor(String str) {
		ArrayList<Double> doubles = getdouble(str);

		return new Color(doubles.get(0), doubles.get(1), doubles.get(2));
	}

	/**
	 * 
	 * @param str
	 * @return
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
