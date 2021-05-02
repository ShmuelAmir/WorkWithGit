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
 * @author shmulik
 *
 */

public class SceneDescriptor {
	
	public Map<String, String> sceneAttributes  = new HashMap<String, String>();
	public Map<String, String> ambientLightAttributes  = new HashMap<String, String>();
	public List <Map <String,String>> spheres = new LinkedList<>();
	public List <Map <String,String>> triangles = new LinkedList<>();
	
	
	
	public void  getStringFromXml(String fileName ) 
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		String ambientLightColor = null, backgroundColor = null, c = null;
		double r = 0;
		List<String[]> ss = new ArrayList<>();
		try {
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(fileName);

			Element root = doc.getDocumentElement();
			backgroundColor = root.getAttribute("background-color");
			sceneAttributes.put("backgroundColor", backgroundColor);
			
			NodeList n = root.getElementsByTagName("ambient-light");
			Element alc = (Element) n.item(0);
			ambientLightColor = alc.getAttribute("color");
			ambientLightAttributes.put("Color", ambientLightColor);

			NodeList geList = root.getElementsByTagName("geometries");
			Element geom = (Element) geList.item(0);

			NodeList spList = geom.getElementsByTagName("sphere");
			Element sp = (Element) spList.item(0);
			Map<String, String> sphereMap  = new HashMap<String, String>();
			sphereMap.put("radius", sp.getAttribute("radius"));
			sphereMap.put("center", sp.getAttribute("center"));
			spheres.add(sphereMap);
		

			NodeList triangleList = geom.getElementsByTagName("triangle");
			for (int i = 0; i < triangleList.getLength(); i++) {
				Node t = triangleList.item(i);
				Map<String, String> triangleMap  = new HashMap<String, String>();
				if (t.getNodeType() == Node.ELEMENT_NODE) {
					Element tt = (Element) t;
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


