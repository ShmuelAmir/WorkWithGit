/**
 * 
 */
package parser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * SceneDescriptor class represents the scene that store in XML file.
 * @author Shmulik & Chananel
 *
 */

public class SceneDescriptor {
	// the scene component - ambient-light , spheres , triangles
	public Map<String, String> sceneAttributes  = new HashMap<String, String>();
	public Map<String, String> ambientLightAttributes  = new HashMap<String, String>();
	public List <Map <String,String>> spheres = new LinkedList<>();
	public List <Map <String,String>> triangles = new LinkedList<>();
	
	
	/**
	 * this method get a fileName and enter the data to scene descriptor 
	 * @param fileName
	 */
	public void  getStringFromXml(String fileName ) 
	{
		//Build the tools for accessing the file
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		String ambientLightColor = null, backgroundColor = null, c = null;
		double r = 0;
		List<String[]> ss = new ArrayList<>();     //                               זה?           את        צריך 
		try {                                      // try to open the fill - may be some exception 
			// access to the XML fill with parse function
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(fileName);
            // Start reading the content from the XML file
			// save the root of the fill
			Element root = doc.getDocumentElement();
			//get background-color
			
			backgroundColor = root.getAttribute("background-color");
			// set the value in the map
			sceneAttributes.put("backgroundColor", backgroundColor);
			
			//get ambient-light
			NodeList n = root.getElementsByTagName("ambient-light");
			Element alc = (Element) n.item(0);
			ambientLightColor = alc.getAttribute("color");
			// set the value in the map
			ambientLightAttributes.put("Color", ambientLightColor);
			
			//get geometries
			NodeList geList = root.getElementsByTagName("geometries");
			Element geom = (Element) geList.item(0);
			//get sphere
			NodeList spList = geom.getElementsByTagName("sphere");
			Element sp = (Element) spList.item(0);
			Map<String, String> sphereMap  = new HashMap<String, String>();
			// set the value in the map
			sphereMap.put("radius", sp.getAttribute("radius"));
			sphereMap.put("center", sp.getAttribute("center"));
			spheres.add(sphereMap);
		
			//get triangle
			NodeList triangleList = geom.getElementsByTagName("triangle");
			//get all the points of triangle
			for (int i = 0; i < triangleList.getLength(); i++) {
				Node t = triangleList.item(i);
				Map<String, String> triangleMap  = new HashMap<String, String>();
				if (t.getNodeType() == Node.ELEMENT_NODE) {
					Element tt = (Element) t;
					// set the value in the map
					triangleMap.put("p0", tt.getAttribute("p0"));
					triangleMap.put("p1", tt.getAttribute("p1"));
					triangleMap.put("p2", tt.getAttribute("p2"));
				}
				triangles.add(triangleMap);		
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
			
			
			
			

}


