/**
 * 
 */
package unittests.renderer;

import org.junit.Test;

import primitives.Color;
import renderer.ImageWriter;

/**
 * @author shmulik
 *
 */
public class ImageWriterTest {

	/**
	 * Test method for {@link renderer.ImageWriter#writeToImage()}.
	 * this test try to create simple image with imageWriter method
	 */
	@Test
	public void testWriteToImage() {
		ImageWriter imageWriter = new ImageWriter("FirstImage", 800, 500);
		Color blue = new Color(java.awt.Color.BLUE);
		Color red = new Color(java.awt.Color.RED);

		// Go over each pixel and give it a suitable 
		for (int j = 0; j < imageWriter.getNx(); j++) {
			for (int i = 0; i < imageWriter.getNy(); i++) {
				if (i % 50 == 0 || j % 50 == 0)
					imageWriter.writePixel(j, i, red);
				else
					imageWriter.writePixel(j, i, blue);
			}
		}

		imageWriter.writeToImage();
	}
}
