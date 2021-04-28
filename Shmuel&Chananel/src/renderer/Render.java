/**
 * 
 */
package renderer;

import java.util.MissingResourceException;

import elements.Camera;
import primitives.Color;
import scene.Scene;

/**
 * @author shmulik
 *
 */
public class Render {
	private Scene scene;
	private Camera camera;
	private ImageWriter imageWriter;
	private RayTracerBase rayTracer;
	
	/**
	 * @param scene the scene to set
	 */
	public Render setScene(Scene scene) {
		this.scene = scene;
		return this;
	}

	/**
	 * @param camera the camera to set
	 */
	public Render setCamera(Camera camera) {
		this.camera = camera;
		return this;
	}

	/**
	 * @param imageWriter the imageWriter to set
	 */
	public Render setImageWriter(ImageWriter imageWriter) {
		this.imageWriter = imageWriter;
		return this;
	}

	/**
	 * @param rayTracer the rayTracer to set
	 */
	public Render setRayTracer(RayTracerBase rayTracer) {
		this.rayTracer = rayTracer;
		return this;
	}

	public void renderImage() {
		if (scene == null)
			throw new MissingResourceException("scene is null", "Render", "scene");
		if (camera == null)
			throw new MissingResourceException("camera is null", "Render", "camera");
		if (imageWriter == null)
			throw new MissingResourceException("imageWriter is null", "Render", "imageWriter");
		if (rayTracer == null)
			throw new MissingResourceException("scene is null", "Render", "rayTracer");
		
		int nX = imageWriter.getNx();
		int nY = imageWriter.getNy();
		
		for (int j = 0; j < nX; j++) {
			for (int i = 0; i < nY; i++) {
				imageWriter.writePixel(j, i, rayTracer.traceRay(camera.constructRayThroughPixel(nX, nY, j, i)));
			}
		}
	}
	
	public void printGrid(int interval, Color color) {
		if (imageWriter == null)
			throw new MissingResourceException("imageWriter is null", "Render", "imageWriter");
		
		for (int j = 0; j < imageWriter.getNx(); j++) {
			for (int i = 0; i < imageWriter.getNy(); i++) {
				if (i % interval == 0 || j % interval == 0)
					imageWriter.writePixel(j, i, color);
			}
		}
	}
	
	public void writeToImage() {
		if (imageWriter == null)
			throw new MissingResourceException("imageWriter is null", "Render", "imageWriter");
		
		imageWriter.writeToImage();
	}
}
