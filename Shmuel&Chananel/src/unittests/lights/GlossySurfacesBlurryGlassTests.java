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
 * @author shmulik & chananel
 *
 */
public class GlossySurfacesBlurryGlassTests {

//	private Camera camera2 = new Camera(new Point3D(0, 0, 10000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
//			.setViewPlaneSize(2500, 2500).setDistance(10000);
//	private Camera camera3 = new Camera(new Point3D(0, -1000, 250), new Vector(0, 1068, -250), new Vector(0, 250, 1068)) //
//			.setViewPlaneSize(250, 250).setDistance(1000);
//	@Test
//	public void GlossySurfacesTest() {
//
//		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4)) //
//				.setkL(0.00001).setkQ(0.000005));
//
//		scene.geometries.add( //
//				new Sphere(new Point3D(-950, -900, -1000), 400) //
//						.setEmission(new Color(0, 0, 100)) //
//						.setMaterial(new Material().setkD(0.5).setkS(0.5).setnShininess(20)),
////				new Sphere(new Point3D(-950, -900, -1000), 200) //
////						.setEmission(new Color(100, 20, 20)) //
////						.setMaterial(new Material().setkD(0.25).setkS(0.25).setnShininess(20)),
//				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
//						new Point3D(670, 670, 3000)) //
//								.setEmission(new Color(20, 20, 20)) //
//								.setMaterial(new Material().setkR(1).setkG(0.2)),
//				new Triangle(new Point3D(1500, -1500, -1500), new Point3D(-1500, 1500, -1500),
//						new Point3D(-1500, -1500, -2000)) //
//								.setEmission(new Color(20, 20, 20)) //
//								.setMaterial(new Material().setkR(1).setkG(0.2)));
//
//		
//		ImageWriter imageWriter = new ImageWriter("GlossySurfaces", 500, 500);
//		Render render = new Render() //
//				.setImageWriter(imageWriter) //
//				.setCamera(camera2) //
//				.setRayTracer(new RayTracerBasic(scene));
//
//		render.renderImage();
//		render.writeToImage();
//	}
//
//	@Test
//	public void blurryGlassTest0() {
//		scene.geometries.add( //
//				new Sphere(new Point3D(0, 0, -50), 25) //
//						.setEmission(new Color(java.awt.Color.BLUE)) //
//						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
//				new Polygon(new Point3D(50, 70, 0), new Point3D(-50, 70, 0), new Point3D(-50, -70, 0),
//						new Point3D(50, -70, 0)) //
//							.setEmission(new Color(java.awt.Color.RED)) //
//							.setMaterial(new Material().setkT(1).setkB(0.0)));
//
//		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4)) //
//				.setkL(0.00001).setkQ(0.000005));
//		scene.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(-50, -50, 50), new Vector(1, 1, -2)) //
//				.setkL(0.00001).setkQ(0.00000001));
//
//		ImageWriter imageWriter = new ImageWriter("BlurryGlass - 0.0 ", 600, 600);
//		Render render = new Render() //
//				.setImageWriter(imageWriter) //
//				.setCamera(camera) //
//				.setRayTracer(new RayTracerBasic(scene));
//
//		render.renderImage();
//		render.writeToImage();
//	}
//	
//	@Test
//	public void blurryGlassTest1() {
//		scene.geometries.add( //
//				new Sphere(new Point3D(0, 0, -50), 25) //
//						.setEmission(new Color(java.awt.Color.BLUE)) //
//						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
//				new Polygon(new Point3D(50, 70, 0), new Point3D(-50, 70, 0), new Point3D(-50, -70, 0),
//						new Point3D(50, -70, 0)) //
//							.setEmission(new Color(java.awt.Color.BLACK)) //
//							.setMaterial(new Material().setkT(1).setkB(0.15)));
//
//		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4)) //
//				.setkL(0.00001).setkQ(0.000005));
//		scene.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(-50, -50, 50), new Vector(1, 1, -2)) //
//				.setkL(0.00001).setkQ(0.00000001));
//
//		ImageWriter imageWriter = new ImageWriter("BlurryGlass - 0.05 ", 600, 600);
//		Render render = new Render() //
//				.setImageWriter(imageWriter) //
//				.setCamera(camera) //
//				.setRayTracer(new RayTracerBasic(scene));
//
//		render.renderImage();
//		render.writeToImage();
//	}
//	
//	@Test
//	public void blurryGlassTest2() {
//		scene.geometries.add( //
//				new Sphere(new Point3D(0, 0, -50), 25) //
//						.setEmission(new Color(java.awt.Color.BLUE)) //
//						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
//				new Polygon(new Point3D(50, 70, 0), new Point3D(-50, 70, 0), new Point3D(-50, -70, 0),
//						new Point3D(50, -70, 0)) //
//							.setEmission(new Color(java.awt.Color.RED)) //
//							.setMaterial(new Material().setkT(1).setkB(0.1)));
//
//		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4)) //
//				.setkL(0.00001).setkQ(0.000005));
//		scene.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(-50, -50, 50), new Vector(1, 1, -2)) //
//				.setkL(0.00001).setkQ(0.00000001));
//
//		ImageWriter imageWriter = new ImageWriter("BlurryGlass - 0.1 ", 600, 600);
//		Render render = new Render() //
//				.setImageWriter(imageWriter) //
//				.setCamera(camera) //
//				.setRayTracer(new RayTracerBasic(scene));
//
//		render.renderImage();
//		render.writeToImage();
//	}
//	
//	@Test
//	public void blurryGlassTest3() {
//		scene.geometries.add( //
//				new Sphere(new Point3D(0, 0, -50), 25) //
//						.setEmission(new Color(java.awt.Color.BLUE)) //
//						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
//				new Polygon(new Point3D(50, 70, 0), new Point3D(-50, 70, 0), new Point3D(-50, -70, 0),
//						new Point3D(50, -70, 0)) //
//							.setEmission(new Color(java.awt.Color.RED)) //
//							.setMaterial(new Material().setkT(1).setkB(0.2)));
//
//		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4)) //
//				.setkL(0.00001).setkQ(0.000005));
//		scene.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(-50, -50, 50), new Vector(1, 1, -2)) //
//				.setkL(0.00001).setkQ(0.00000001));
//
//		ImageWriter imageWriter = new ImageWriter("BlurryGlass - 0.2 ", 600, 600);
//		Render render = new Render() //
//				.setImageWriter(imageWriter) //
//				.setCamera(camera) //
//				.setRayTracer(new RayTracerBasic(scene));
//
//		render.renderImage();
//		render.writeToImage();
//	}
//	
//	@Test
//	public void blurryGlassTest4() {
//		scene.geometries.add( //
//				new Sphere(new Point3D(0, 0, -50), 25) //
//						.setEmission(new Color(java.awt.Color.BLUE)) //
//						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
//				new Polygon(new Point3D(50, 70, 0), new Point3D(-50, 70, 0), new Point3D(-50, -70, 0),
//						new Point3D(50, -70, 0)) //
//							.setEmission(new Color(java.awt.Color.RED)) //
//							.setMaterial(new Material().setkT(1).setkB(0.4)));
//
//		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4)) //
//				.setkL(0.00001).setkQ(0.000005));
//		scene.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(-50, -50, 50), new Vector(1, 1, -2)) //
//				.setkL(0.00001).setkQ(0.00000001));
//
//		ImageWriter imageWriter = new ImageWriter("BlurryGlass - 0.4 ", 600, 600);
//		Render render = new Render() //
//				.setImageWriter(imageWriter) //
//				.setCamera(camera) //
//				.setRayTracer(new RayTracerBasic(scene));
//
//		render.renderImage();
//		render.writeToImage();
//	}

//	@Test
//	public void glossySurfacesBlurryGlassTest() {
//		scene.geometries.add( //
////				new Sphere(new Point3D(0, 100, -65), 45) //
////						.setEmission(new Color(java.awt.Color.BLUE)) //
////						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100)),
//				new Sphere(new Point3D(0, 100, 0), 25) //
//						.setEmission(new Color(java.awt.Color.YELLOW)) //
//						.setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(100).setkT(0.6)),
//				new Polygon(new Point3D(150, 70, 0), new Point3D(-150, 70, 0), new Point3D(-150, -350, 0),
//						new Point3D(150, -350, 0)) //
//								.setEmission(new Color(java.awt.Color.BLUE)) //
//								.setMaterial(new Material().setkR(1).setkG(0.05)));
//
//		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(0, 100, 0), new Vector(0, 1, -1)) //
//				.setkL(0.00001).setkQ(0.000005));
////		scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point3D(-750, -750, -150), new Vector(-1, -1, -4)) //
////				.setkL(0.00001).setkQ(0.000005));
////		scene.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(-50, -50, 50), new Vector(1, 1, -2)) //
////				.setkL(0.00001).setkQ(0.00000001));
//
//		ImageWriter imageWriter = new ImageWriter("GlossySurfacesBlurryGlass ", 600, 600);
//		Render render = new Render() //
//				.setImageWriter(imageWriter) //
//				.setCamera(camera3) //
//				.setRayTracer(new RayTracerBasic(scene));
//
//		render.renderImage();
//		render.writeToImage();
//	}

//	@Test
//	public void MP1Image() {
////		scene.background = new Color(java.awt.Color.WHITE);
//
//		scene.geometries.add( //
//				new Sphere(new Point3D(25, 25, -50), 25) //
//						.setEmission(new Color(java.awt.Color.BLUE)) //
//						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
//				new Sphere(new Point3D(-25, 25, -50), 25) //
//						.setEmission(new Color(java.awt.Color.RED)) //
//						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
//				new Sphere(new Point3D(-25, -25, -50), 25) //
//						.setEmission(new Color(java.awt.Color.BLUE)) //
//						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
//				new Sphere(new Point3D(0, 0, 0), 25) //
//						.setEmission(new Color(java.awt.Color.CYAN)) //
//						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
//				new Sphere(new Point3D(25, -25, -50), 25) //
//						.setEmission(new Color(java.awt.Color.RED)) //
//						.setMaterial(new Material().setkD(0.4).setkS(0.3).setnShininess(100).setkT(0.3)),
//				new Polygon(new Point3D(50, 70, -100), new Point3D(-50, 70, -100), new Point3D(-50, -70, -100),
//						new Point3D(50, -70, -100)) //
//								.setEmission(new Color(java.awt.Color.RED)) //
//								.setMaterial(new Material())
//		);
//
//		scene.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(50, 50, 50), new Vector(-1, -1, -2)) //
//				.setkL(0.00001).setkQ(0.00000001));
////		scene.lights.add(new SpotLight(new Color(500, 300, 0), new Point3D(50, 50, 50), new Vector(-1, -1, -2)) //
////				.setkL(0.00001).setkQ(0.00000001));
//
//
//		ImageWriter imageWriter = new ImageWriter("MP1Image", 600, 600);
//		Render render = new Render() //
//				.setImageWriter(imageWriter) //
//				.setCamera(camera) //
//				.setRayTracer(new RayTracerBasic(scene));
//
//		render.renderImage();
//		render.writeToImage();
//
//	}

	private Scene scene = new Scene("Test scene") //
			.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

	private Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
			.setViewPlaneSize(200, 200).setDistance(1000);

	@Test
	public void mP1Image() {
		scene.geometries.add( //
				new Plane(new Point3D(0, 0, -250), new Vector(0, 0, 1)) //
						.setEmission(new Color(120, 120, 120)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5)), //
				new Plane(new Point3D(0, -50, 0), new Vector(0, 1, 0)) //
						.setEmission(new Color(150, 150, 150)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5)), //

				new Polygon(new Point3D(-125, 70, 0), new Point3D(-10, 70, 0), new Point3D(-10, -50, 0),
						new Point3D(-125, -50, 0)) //
								.setEmission(new Color(25, 25, 100)) //
								.setMaterial(new Material().setkD(0.2).setkS(0.2).setkT(0.6)), //
				new Polygon(new Point3D(-70, 10, -250), new Point3D(-100, 10, 0), new Point3D(-100, 0, 0),
						new Point3D(-70, 0, -250)) //
								.setEmission(new Color(java.awt.Color.DARK_GRAY)) //
								.setMaterial(new Material().setkD(0.5)), //
				new Sphere(new Point3D(-105, 25, -150), 4) //
						.setEmission(new Color(java.awt.Color.RED)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.05)), //
				new Sphere(new Point3D(-105, 18, -150), 8) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.05)), //
				new Sphere(new Point3D(-90, 22, -160), 3) //
						.setEmission(new Color(153, 76, 0)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.05)), //
				new Sphere(new Point3D(-90, 15, -160), 7) //
						.setEmission(new Color(java.awt.Color.GREEN)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.05)), //

				new Polygon(new Point3D(65, 100, -249), new Point3D(120, 100, -249), new Point3D(120, 20, -249),
						new Point3D(65, 20, -249)) //
								.setEmission(new Color(100, 100, 200)) //
								.setMaterial(new Material().setkR(1)), //
				new Polygon(new Point3D(60, 15, -250), new Point3D(100, 15, 40), new Point3D(100, -50, 40),
						new Point3D(60, -50, -250)) //
								.setEmission(new Color(java.awt.Color.DARK_GRAY)) //
								.setMaterial(new Material().setkD(0.5)), //
				new Sphere(new Point3D(110, 30, -150), 4) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.05)), //
				new Sphere(new Point3D(110, 23, -150), 8) //
						.setEmission(new Color(java.awt.Color.RED)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.05)) //
		);

		scene.lights.add(new DirectionalLight(new Color(125, 75, 0), new Vector(0, 0, -1)));
		scene.lights.add(new PointLight(new Color(225, 225, 0), new Point3D(0, 1000, 500)));
		scene.lights.add(new SpotLight(new Color(250, 150, 0), new Point3D(250, 500, 250), new Vector(-1, 1, 1)) //
				.setkL(0.00001).setkQ(0.00000001));

		ImageWriter imageWriter = new ImageWriter("mP1Image", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}

	@Test
	public void mP1Image2() {
		scene.geometries.add( //
				new Plane(new Point3D(0, 0, -250), new Vector(0, 0, 1)) //
						.setEmission(new Color(120, 120, 120)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5)), //
				new Plane(new Point3D(0, -50, 0), new Vector(0, 1, 0)) //
						.setEmission(new Color(150, 150, 150)) //
						.setMaterial(new Material().setkD(0.5).setkS(0.5)), //

				new Polygon(new Point3D(-125, 70, 0), new Point3D(-10, 70, 0), new Point3D(-10, -50, 0),
						new Point3D(-125, -50, 0)) //
								.setEmission(new Color(25, 25, 100)) //
								.setMaterial(new Material().setkT(1).setkB(0.01)), //
				new Polygon(new Point3D(-70, 10, -250), new Point3D(-100, 10, 0), new Point3D(-100, 0, 0),
						new Point3D(-70, 0, -250)) //
								.setEmission(new Color(java.awt.Color.DARK_GRAY)) //
								.setMaterial(new Material().setkD(0.5)), //
				new Sphere(new Point3D(-105, 25, -150), 4) //
						.setEmission(new Color(java.awt.Color.RED)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.05)), //
				new Sphere(new Point3D(-105, 18, -150), 8) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.05)), //
				new Sphere(new Point3D(-90, 22, -160), 3) //
						.setEmission(new Color(153, 76, 0)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.05)), //
				new Sphere(new Point3D(-90, 15, -160), 7) //
						.setEmission(new Color(java.awt.Color.GREEN)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.05)), //

				new Polygon(new Point3D(65, 100, -249), new Point3D(120, 100, -249), new Point3D(120, 20, -249),
						new Point3D(65, 20, -249)) //
								.setEmission(new Color(100, 100, 200)) //
								.setMaterial(new Material().setkR(1).setkG(0.05)), //
				new Polygon(new Point3D(60, 15, -250), new Point3D(100, 15, 40), new Point3D(100, -50, 40),
						new Point3D(60, -50, -250)) //
								.setEmission(new Color(java.awt.Color.DARK_GRAY)) //
								.setMaterial(new Material().setkD(0.5)), //
				new Sphere(new Point3D(110, 30, -150), 4) //
						.setEmission(new Color(java.awt.Color.BLUE)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.05)), //
				new Sphere(new Point3D(110, 23, -150), 8) //
						.setEmission(new Color(java.awt.Color.RED)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.05)) //
		);

		scene.lights.add(new DirectionalLight(new Color(125, 75, 0), new Vector(0, 0, -1)));
		scene.lights.add(new PointLight(new Color(225, 225, 0), new Point3D(0, 1000, 500)));
		scene.lights.add(new SpotLight(new Color(250, 150, 0), new Point3D(250, 500, 250), new Vector(-1, 1, 1)) //
				.setkL(0.00001).setkQ(0.00000001));

		ImageWriter imageWriter = new ImageWriter("mP1Image2", 600, 600);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setRayTracer(new RayTracerBasic(scene));

		render.renderImage();
		render.writeToImage();
	}
}
