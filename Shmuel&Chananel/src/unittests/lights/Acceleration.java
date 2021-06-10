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
 * @author user1
 *
 */
public class Acceleration {

	@Test
	public void accelerationTest() {

		Scene scene = new Scene("Test scene") //
				.setAmbientLight(new AmbientLight(new Color(java.awt.Color.WHITE), 0.15));

		Camera camera = new Camera(new Point3D(0, -20, -1000), new Vector(0, 0, 1), new Vector(0, -1, 0)) //
				.setViewPlaneSize(200, 200).setDistance(1000);

		Point3D houseAdding = new Point3D(0, 0, 0);

		double houseX = houseAdding.getX();
		double houseY = houseAdding.getY();
		double houseZ = houseAdding.getZ();

		Point3D flowerAdding = new Point3D(25, 0, -200);

		double flowerX = flowerAdding.getX();
		double flowerY = flowerAdding.getY();
		double flowerZ = flowerAdding.getZ();

		scene.geometries.add(
				/** CENTER WALL **/
				/*
				 * new Triangle(new Color(java.awt.Color.BLACK), new Material(0, 0.8, 60, 0,1),
				 * // new Point3D(300, -100, 2000), new Point3D(-300, -100, 2000), new
				 * Point3D(300, 100, 2000)), new Triangle(new Color(java.awt.Color.BLACK), new
				 * Material(0, 0.8, 60, 0,1), // new Point3D(-300, 100, 2000), new Point3D(-300,
				 * -100, 2000), new Point3D(300, 100, 2000)),
				 */
				/** SKY **/
				new Plane(new Point3D(50, -10, 700), new Point3D(0, 10, 500), new Point3D(10, -10, 700)) //
						.setEmission(new Color(102, 178, 255)),

				/** FLOOR **/
				new Plane(new Point3D(50, 10, 700), new Point3D(0, 10, 500), new Point3D(10, 10, 700)) //
						.setEmission(new Color(java.awt.Color.DARK_GRAY)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.2).setnShininess(60)),

				// SUN
				new Sphere(new Point3D(20, -100, 400), 10) //
						.setEmission(new Color(java.awt.Color.YELLOW)) //
						.setMaterial(new Material().setkS(0.5).setnShininess(30)),

				// ROOFS - FRONT
				new Polygon(new Point3D(-52 + houseX, -9 + houseY, -265 + houseZ),
						new Point3D(-18 + houseX, -9 + houseY, -265 + houseZ),
						new Point3D(-18 + houseX, -25 + houseY, -125 + houseZ),
						new Point3D(-52 + houseX, -25 + houseY, -125 + houseZ)) //
								.setEmission(new Color(153, 0, 0)) //
								.setMaterial(new Material().setkS(0.8).setnShininess(60)),
				// ROOFS - BACK
				new Polygon(new Point3D(-52 + houseX, -25 + houseY, -125 + houseZ),
						new Point3D(-18 + houseX, -25 + houseY, -125 + houseZ),
						new Point3D(-18 + houseX, -9 + houseY, 15 + houseZ),
						new Point3D(-52 + houseX, -9 + houseY, 15 + houseZ)) //
								.setEmission(new Color(153, 0, 0)) //
								.setMaterial(new Material().setkS(0.8).setnShininess(60)),

				// ROOF LEFT SIDE
				new Triangle(new Point3D(-50 + houseX, -10 + houseY, -250 + houseZ),
						new Point3D(-50 + houseX, -25 + houseY, -125 + houseZ),
						new Point3D(-50 + houseX, -10 + houseY, 0 + houseZ)) //
								.setEmission(new Color(java.awt.Color.BLUE)) //
								.setMaterial(new Material().setkS(0.8).setnShininess(60)),

				// ROOF RIGHT SIDE
				new Triangle(new Point3D(-20 + houseX, -10 + houseY, -250 + houseZ),
						new Point3D(-20 + houseX, -25 + houseY, -125 + houseZ),
						new Point3D(-20 + houseX, -10 + houseY, 0 + houseZ)) //
								.setEmission(new Color(java.awt.Color.BLUE)) //
								.setMaterial(new Material().setkS(0.8).setnShininess(60)),

				// ARUBA
//				new Cube(new Color(192, 192, 192), new Material(0, 0, 0, 0, 0),
//						new Point3D(-45 + houseX, -30 + houseY, -200 + houseZ),
//						new Point3D(-40 + houseX, -20 + houseY, -190 + houseZ)),

//				// HOUSE CUBE
//				new Cube(new Color(java.awt.Color.WHITE), new Material(0, 0.8, 60, 0, 0),
//						new Point3D(-50 + houseX, -10 + houseY, -250 + houseZ),
//						new Point3D(-20 + houseX, 10 + houseY, 0 + houseZ)),
//
//				// HOUSE BOTTOM LINE
//				new Cube(new Color(java.awt.Color.GRAY), new Material(0, 0.8, 60, 0, 0),
//						new Point3D(-50.0001 + houseX, 8 + houseY, -250.0001 + houseZ),
//						new Point3D(-19.9999 + houseX, 10 + houseY, 0.0001 + houseZ)),
//				// HOUSE FIRST STAIRS
//				new Cube(new Color(java.awt.Color.GRAY), new Material(0, 0.8, 60, 0, 0),
//						new Point3D(-40 + houseX, 8 + houseY, -252 + houseZ),
//						new Point3D(-30 + houseX, 10 + houseY, -250 + houseZ)),
//
//				// HOUSE SECOND STAIRS
//				new Cube(new Color(java.awt.Color.GRAY), new Material(0, 0.8, 60, 0, 0),
//						new Point3D(-40 + houseX, 9 + houseY, -255 + houseZ),
//						new Point3D(-30 + houseX, 10 + houseY, -250 + houseZ)),

				// YADIT DOOR
				new Sphere(new Point3D(-38 + houseX, 0 + houseY, -250.0001 + houseZ), 0.5) //
						.setEmission(new Color(224, 224, 224)) //
						.setMaterial(new Material().setkS(0.8).setnShininess(60)),

				// DOOR
				new Polygon(new Point3D(-39 + houseX, 8 + houseY, -250.0001 + houseZ),
						new Point3D(-31 + houseX, 8 + houseY, -250.0001 + houseZ),
						new Point3D(-31 + houseX, -7 + houseY, -250.0001 + houseZ),
						new Point3D(-39 + houseX, -7 + houseY, -250.0001 + houseZ)) //
								.setEmission(new Color(51, 25, 0)) //
								.setMaterial(new Material().setkS(0.8).setnShininess(60)),

				// top left -door
				new Polygon(new Point3D(-38 + houseX, -1 + houseY, -250.1001 + houseZ),
						new Point3D(-35.5 + houseX, -1 + houseY, -250.1001 + houseZ),
						new Point3D(-35.5 + houseX, -6 + houseY, -250.1001 + houseZ),
						new Point3D(-38 + houseX, -6 + houseY, -250.1001 + houseZ)) //
								.setEmission(new Color(153, 76, 0)) //
								.setMaterial(new Material().setkS(0.8).setnShininess(60)),
				// bottom left door
				new Polygon(new Point3D(-38 + houseX, 7 + houseY, -250.1001 + houseZ),
						new Point3D(-35.5 + houseX, 7 + houseY, -250.1001 + houseZ),
						new Point3D(-35.5 + houseX, 0 + houseY, -250.1001 + houseZ),
						new Point3D(-38 + houseX, 0 + houseY, -250.1001 + houseZ)) //
								.setEmission(new Color(153, 76, 0)) //
								.setMaterial(new Material().setkS(0.8).setnShininess(60).setkT(1)),

				// top right door
				new Polygon(new Point3D(-35 + houseX, -1 + houseY, -250.1001 + houseZ),
						new Point3D(-32 + houseX, -1 + houseY, -250.1001 + houseZ),
						new Point3D(-32 + houseX, -6 + houseY, -250.1001 + houseZ),
						new Point3D(-35 + houseX, -6 + houseY, -250.1001 + houseZ)) //
								.setEmission(new Color(153, 76, 0)) //
								.setMaterial(new Material().setkS(0.8).setnShininess(60)),

				// bottom right door
				new Polygon(new Point3D(-35 + houseX, 7 + houseY, -250.1001 + houseZ),
						new Point3D(-32 + houseX, 7 + houseY, -250.1001 + houseZ),
						new Point3D(-32 + houseX, 0 + houseY, -250.1001 + houseZ),
						new Point3D(-35 + houseX, 0 + houseY, -250.1001 + houseZ)) //
								.setEmission(new Color(153, 76, 0)) //
								.setMaterial(new Material().setkS(0.8).setnShininess(60)),

				// WINDOWS
				new Polygon(new Point3D(-49 + houseX, 0 + houseY, -250.0001 + houseZ),
						new Point3D(-41 + houseX, 0 + houseY, -250.0001 + houseZ),
						new Point3D(-41 + houseX, -7 + houseY, -250.0001 + houseZ),
						new Point3D(-49 + houseX, -7 + houseY, -250.0001 + houseZ)) //
								.setEmission(new Color(51, 25, 0)) //
								.setMaterial(new Material().setkS(0.8).setnShininess(60)),
				// LEFT TOP WINDOW
				new Polygon(new Point3D(-48.5 + houseX, -0.5 + houseY, -250.0002 + houseZ),
						new Point3D(-45.2 + houseX, -0.5 + houseY, -250.0002 + houseZ),
						new Point3D(-45.2 + houseX, -3.3 + houseY, -250.0002 + houseZ),
						new Point3D(-48.5 + houseX, -3.3 + houseY, -250.0002 + houseZ)) //
								.setEmission(new Color(192, 192, 192)) //
								.setMaterial(new Material().setkS(0.8).setnShininess(60)),
				// LEFT BOTTOM WINDOW
				new Polygon(new Point3D(-48.5 + houseX, -3.7 + houseY, -250.0002 + houseZ),
						new Point3D(-45.2 + houseX, -3.7 + houseY, -250.0002 + houseZ),
						new Point3D(-45.2 + houseX, -6.5 + houseY, -250.0002 + houseZ),
						new Point3D(-48.5 + houseX, -6.5 + houseY, -250.0002 + houseZ)) //
								.setEmission(new Color(192, 192, 192)) //
								.setMaterial(new Material().setkS(0.8).setnShininess(60).setkT(0.8)),
				// right top window
				new Polygon(new Point3D(-44.8 + houseX, -0.5 + houseY, -250.0002 + houseZ),
						new Point3D(-41.5 + houseX, -0.5 + houseY, -250.0002 + houseZ),
						new Point3D(-41.5 + houseX, -3.3 + houseY, -250.0002 + houseZ),
						new Point3D(-44.8 + houseX, -3.3 + houseY, -250.0002 + houseZ)) //
								.setEmission(new Color(192, 192, 192)) //
								.setMaterial(new Material().setkS(0.8).setnShininess(60).setkT(0.8)),
				// LEFT BOTTOM WINDOW
				new Polygon(new Point3D(-44.8 + houseX, -3.7 + houseY, -250.0002 + houseZ),
						new Point3D(-41.5 + houseX, -3.7 + houseY, -250.0002 + houseZ),
						new Point3D(-41.5 + houseX, -6.5 + houseY, -250.0002 + houseZ),
						new Point3D(-44.8 + houseX, -6.5 + houseY, -250.0002 + houseZ)) //
								.setEmission(new Color(192, 192, 192)) //
								.setMaterial(new Material().setkS(0.8).setnShininess(60).setkT(0.8)),

				// MENORAT BERECHA
				new Triangle(new Point3D(80, 10, 0), new Point3D(80, -10, 0), new Point3D(83, -10, 0)) //
						.setEmission(new Color(java.awt.Color.BLACK)) //
						.setMaterial(new Material().setnShininess(60)),
				new Triangle(new Point3D(80, 10, 0), new Point3D(83, 10, 0), new Point3D(83, -10, 0)) //
						.setEmission(new Color(java.awt.Color.BLACK)) //
						.setMaterial(new Material().setnShininess(60)),
				new Sphere(new Point3D(81.5, -10, 0), 5) //
						.setEmission(new Color(java.awt.Color.YELLOW)) //
						.setMaterial(new Material().setkD(0.2).setkS(0.5).setnShininess(30)),

				/**
				 * POOL
				 */
				new Triangle(new Point3D(30, 5, -700), new Point3D(0, 10, 0), new Point3D(80, 10, 0)) //
						.setEmission(new Color(75, 209, 246)) //
						.setMaterial(new Material().setkD(0.4).setkS(0.1).setnShininess(60).setkR(0.5)),
				new Triangle(new Point3D(30, 5, -700), new Point3D(0, 10, 0), new Point3D(0, 5, -700)) //
						.setEmission(new Color(75, 209, 246)) //
						.setMaterial(new Material().setkD(0.4).setkS(0.1).setnShininess(60).setkR(0.5)),
				//
				new Triangle(new Point3D(0, 5, -30), new Point3D(0, 10, 0), new Point3D(80, 10, 0)) //
						.setEmission(new Color(160, 82, 45)) //
						.setMaterial(new Material().setkS(0.8).setnShininess(60)),
				new Triangle(new Point3D(78, 5, -30), new Point3D(0, 5, -30), new Point3D(80, 10, 0)) //
						.setEmission(new Color(160, 82, 45)) //
						.setMaterial(new Material().setkS(0.8).setnShininess(60)),

				new Triangle(new Point3D(30.5, 4.75, -700), new Point3D(30, 5, -700), new Point3D(80, 10, 0)) //
						.setEmission(new Color(160, 82, 45)) //
						.setMaterial(new Material().setkS(0.8).setnShininess(60)),
				new Triangle(new Point3D(30.5, 4.75, -700), new Point3D(78, 5, -30), new Point3D(80, 10, 0)) //
						.setEmission(new Color(160, 82, 45)) //
						.setMaterial(new Material().setkS(0.8).setnShininess(60)),

				new Triangle(new Point3D(0, 5, -700), new Point3D(0, 4.75, -730), new Point3D(30, 5, -700)) //
						.setEmission(new Color(160, 82, 45)) //
						.setMaterial(new Material().setnShininess(60)),
				new Triangle(new Point3D(32, 4.9, -730), new Point3D(0, 4.75, -730), new Point3D(30.5, 5, -700)) //
						.setEmission(new Color(160, 82, 45)) //
						.setMaterial(new Material().setnShininess(60)),

				/**
				 * end pool
				 */
				new Sphere(new Point3D(-40, 9, -600), 0.5).setEmission(new Color(139, 69, 19))
						.setMaterial(new Material()),
				new Sphere(new Point3D(-40, 9, -600), 0.5).setEmission(new Color(139, 69, 19))
						.setMaterial(new Material())
		// GROUND FOR FLOWERS
//				new Cube(new Color(139, 69, 19), new Material(0, 0.8, 60, 0, 0), new Point3D(-40, 9, -600),
//						new Point3D(-2, 10, -340))
		);

		double oldFlowerX = flowerX;

		for (int i = 0; i < 4; i++) {
			flowerX = oldFlowerX;

			for (int j = 0; j < 4; j++) {

				scene.geometries.add(
						/**
						 * FLOWERS
						 */

						new Triangle(new Point3D(-60 + flowerX, 10 + flowerY, -250 + flowerZ),
								new Point3D(-60 + flowerX, 5 + flowerY, -250 + flowerZ),
								new Point3D(-61 + flowerX, 5 + flowerY, -250 + flowerZ)) //
										.setEmission(new Color(34, 139, 34)) //
										.setMaterial(new Material().setnShininess(60)),
						new Triangle(new Point3D(-60 + flowerX, 10 + flowerY, -250 + flowerZ),
								new Point3D(-61 + flowerX, 10 + flowerY, -250 + flowerZ),
								new Point3D(-61 + flowerX, 5 + flowerY, -250 + flowerZ)) //
										.setEmission(new Color(34, 139, 34)) //
										.setMaterial(new Material().setkS(0.8).setnShininess(60)),

						new Sphere(new Point3D(-60.5 + flowerX, 5 + flowerY, -250 + flowerZ), 2) //
								.setEmission(new Color(java.awt.Color.YELLOW)) //
								.setMaterial(new Material().setkD(0.2).setkS(0.5).setnShininess(30)),
						new Sphere(new Point3D(-60.75 + flowerX, 3.5 + flowerY, -250 + flowerZ), 1) //
								.setEmission(new Color(java.awt.Color.YELLOW)) //
								.setMaterial(new Material().setkD(0.2).setkS(0.5).setnShininess(30).setkT(1)),
						new Sphere(new Point3D(-59.5 + flowerX, 3.75 + flowerY, -250 + flowerZ), 1) //
								.setEmission(new Color(java.awt.Color.YELLOW)) //
								.setMaterial(new Material().setkD(0.2).setkS(0.5).setnShininess(30).setkT(1)),
						new Sphere(new Point3D(-59 + flowerX, 5 + flowerY, -250 + flowerZ), 1) //
								.setEmission(new Color(java.awt.Color.YELLOW)) //
								.setMaterial(new Material().setkD(0.2).setkS(0.5).setnShininess(30).setkT(1)),
						new Sphere(new Point3D(-59.5 + flowerX, 6.25 + flowerY, -250 + flowerZ), 1) //
								.setEmission(new Color(java.awt.Color.YELLOW)) //
								.setMaterial(new Material().setkD(0.2).setkS(0.5).setnShininess(30).setkT(1)),
						new Sphere(new Point3D(-60.8 + flowerX, 6.7 + flowerY, -250 + flowerZ), 1) //
								.setEmission(new Color(java.awt.Color.YELLOW)) //
								.setMaterial(new Material().setkD(0.2).setkS(0.5).setnShininess(30).setkT(1)),
						new Sphere(new Point3D(-62 + flowerX, 5.75 + flowerY, -250 + flowerZ), 1) //
								.setEmission(new Color(java.awt.Color.YELLOW)) //
								.setMaterial(new Material().setkD(0.2).setkS(0.5).setnShininess(30).setkT(1)),
						new Sphere(new Point3D(-62 + flowerX, 4.25 + flowerY, -250 + flowerZ), 1)

				/**
				 * END FLOWERS
				 */

				);

				flowerX += 10;

			}
			flowerZ -= 40;
		}

		scene.lights.add(
				// WINDOW LIGHT
				new PointLight(new Color(java.awt.Color.WHITE), new Point3D(-55, -75, 150)));
		scene.lights.add(// PANAS Light
				new PointLight(new Color(java.awt.Color.YELLOW), new Point3D(81.5, -10, 0.001)));

		ImageWriter imageWriter = new ImageWriter("MiniProject2", 1800, 1800);
		Render render = new Render() //
				.setCamera(camera) //
				.setImageWriter(imageWriter) //
				.setTracer(new RayTracerBasic(scene).setCbr(false)) //
				.setMultithreading(3).setDebugPrint();

		render.renderImage();
		render.writeToImage();
	}

	//@Test
	public void hierarchyTest() {
		Camera camera = new Camera(new Point3D(0, 0, 1000), new Vector(0, 0, -1), new Vector(0, 1, 0)) //
				.setDistance(500) //
				.setViewPlaneSize(500, 500);

		Scene scene = new Scene("Test scene")//
				.setAmbientLight(new AmbientLight(new Color(255, 191, 191), 1)) //
				.setBackground(new Color(75, 127, 90));

		Geometries geos1 = new Geometries(new Sphere(new Point3D(330, 330, -100), 30),
				new Sphere(new Point3D(390, 390, -100), 30), new Sphere(new Point3D(330, 390, -100), 30),
				new Sphere(new Point3D(390, 330, -100), 30));

		Geometries geos2 = new Geometries(new Sphere(new Point3D(-330, -330, -100), 30),
				new Sphere(new Point3D(-390, -390, -100), 30), new Sphere(new Point3D(-330, -390, -100), 30),
				new Sphere(new Point3D(-390, -330, -100), 30));

		Geometries geos3 = new Geometries(new Sphere(new Point3D(330, -330, -100), 30),
				new Sphere(new Point3D(390, -390, -100), 30), new Sphere(new Point3D(330, -390, -100), 30),
				new Sphere(new Point3D(390, -330, -100), 30));

		Geometries geos4 = new Geometries(new Sphere(new Point3D(-330, 330, -100), 30),
				new Sphere(new Point3D(-390, 390, -100), 30), new Sphere(new Point3D(-330, 390, -100), 30),
				new Sphere(new Point3D(-390, 330, -100), 30));

		Geometries geos5 = new Geometries(
				new Triangle(new Point3D(300, 200, -100), new Point3D(200, 300, -100), new Point3D(300, 300, -100)),
				geos1);
		Geometries geos6 = new Geometries(new Triangle(new Point3D(-300, -200, -100), new Point3D(-200, -300, -100),
				new Point3D(-300, -300, -100)), geos2);
		Geometries geos7 = new Geometries(
				new Triangle(new Point3D(300, -200, -100), new Point3D(200, -300, -100), new Point3D(300, -300, -100)),
				geos3);
		Geometries geos8 = new Geometries(
				new Triangle(new Point3D(-300, 200, -100), new Point3D(-200, 300, -100), new Point3D(-300, 300, -100)),
				geos4);

		scene.geometries.add(new Sphere(new Point3D(0, 0, -100), 50), geos5, geos6, geos7, geos8);

		ImageWriter imageWriter = new ImageWriter("hierarchy test", 1000, 1000);
		Render render = new Render() //
				.setImageWriter(imageWriter) //
				.setCamera(camera) //
				.setTracer(new RayTracerBasic(scene).setCbr(true));

		render.renderImage();
		render.printGrid(100, new Color(java.awt.Color.YELLOW));
		render.writeToImage();
	}

}