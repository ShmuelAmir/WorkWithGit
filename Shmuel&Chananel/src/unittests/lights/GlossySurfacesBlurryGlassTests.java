/**
 * 
 */
package unittests.lights;

import org.junit.Test;

import elements.*;
import geometries.*;
import primitives.*;
import renderer.*;
import scene.Scene;

/**
 * @author shmulik
 *
 */
public class GlossySurfacesBlurryGlassTests {
	private Scene scene = new Scene("Test scene") //
			.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

	private Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setViewPlaneSize(200, 200).setDistance(1000);

//	@Test
//	public void GlossySurfacesTest() {
//
//		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4)) //
//				.setkL(0.00001).setkQ(0.000005));
//
//		ImageWriter imageWriter = new ImageWriter("GlossySurfaces", 600, 600);
//		Render render = new Render() //
//				.setImageWriter(imageWriter) //
//				.setCamera(camera) //
//				.setRayTracer(new RayTracerBasic(scene));
//
//		render.renderImage();
//		render.writeToImage();
//	}

	@Test
	public void BlurryGlassTest() {
		scene.geometries.add( //
				new Sphere(new Point3D(0, 0, -50), 25) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
				new Polygon(new Point3D(50, 70, 0), new Point3D(-50, 70, 0), new Point3D(-50, -70, 0),
						new Point3D(50, -70, 0)) //
							.setEmission(new Color(java.awt.Color.RED)) //
							.setMaterial(new Material().setkT(1).setkB(0.1)));

		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4)) //
				.setkL(0.00001).setkQ(0.000005));
		scene.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(-50, -50, 50), new Vector(1, 1, -2)) //
				.setkL(0.00001).setkQ(0.00000001));

//		ImageWriter imageWriter = new ImageWriter("BlurryGlass", 1, 1);
		ImageWriter imageWriter = new ImageWriter("BlurryGlass", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}
}
