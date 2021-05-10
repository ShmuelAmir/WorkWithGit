package renderer;

import java.util.MissingResourceException;

import elements.Camera;
import primitives.Color;

/**
 * render class get the scene camera ImageWriter and RayTracerBase and create a
 * image
 * 
 * @author shmulik
 */
public class Render {
	// all the component of the image
	private Camera camera;
	private ImageWriter imageWriter;
	private RayTracerBase rayTracer;

	/**
	 * set to camera
	 * 
	 * @param camera the camera to set
	 */
	public Render setCamera(Camera camera) {
		this.camera = camera;
		return this;
	}

	/**
	 * set to imageWriter
	 * 
	 * @param imageWriter the imageWriter to set
	 */
	public Render setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}

	/**
	 * set to rayTracer
	 * 
	 * @param rayTracer the rayTracer to set
	 */
	public Render setRayTracer(RayTracerBase rayTracer) {
		this.rayTracer = rayTracer;
		return this;
	}

	/**
	 * this method initialize imageWriter by calculate the color of each pixel
	 */
	public void renderImage() {
		if (camera == null)
			throw new MissingResourceException("camera is null", "Render", "camera");
		if (imageWriter == null)
			throw new MissingResourceException("imageWriter is null", "Render", "imageWriter");
		if (rayTracer == null)
			throw new MissingResourceException("scene is null", "Render", "rayTracer");

		// get the number of the pixel in imageWriter
		int nX = imageWriter.getNx();
		int nY = imageWriter.getNy();

		// go over each pixel in view plane and set its color
		for (int j = 0; j < nX; j++) {
			for (int i = 0; i < nY; i++) {
				imageWriter.writePixel(j, i, rayTracer.traceRay(camera.constructRayThroughPixel(nX, nY, j, i)));
			}
		}
	}

	/**
	 * this method print the grid in the image
	 * 
	 * @param interval - the size of the grid
	 * @param color    - the color of the grid
	 */
	public void printGrid(int interval, Color color) {
		if (imageWriter == null)
			throw new MissingResourceException("imageWriter is null", "Render", "imageWriter");

		int nX = imageWriter.getNx();
		int nY = imageWriter.getNy();

		for (int j = 0; j < nX; j++) {
			for (int i = 0; i < nY; i++) {
				if (i % interval == 0 || j % interval == 0)
					imageWriter.writePixel(j, i, color);
			}
		}
	}

	/**
	 * this method create the image as a png fill
	 */
	public void writeToImage() {
		if (imageWriter == null)
			throw new MissingResourceException("imageWriter is null", "Render", "imageWriter");

		imageWriter.writeToImage();
	}
}
