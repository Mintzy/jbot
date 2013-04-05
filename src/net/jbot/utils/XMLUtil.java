package net.jbot.utils;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XMLUtil {

	public static void addHook(String hookName, String interfaceName, String identity) {
		try {
			DocumentBuilderFactory dFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBldr = dFactory.newDocumentBuilder();
			Document doc = dBldr.parse(new File("hooks.xml"));

			Element hooks = doc.getDocumentElement();

			Element hook = doc.createElement("hook");
			hook.setAttribute("name", hookName);
			
			Element eIdentity = doc.createElement("identity");
			eIdentity.appendChild(doc.createTextNode(identity));
			hook.appendChild(eIdentity);
			
			Element eInterface = doc.createElement("interface");
			eInterface.appendChild(doc.createTextNode(interfaceName));
			hook.appendChild(eInterface);

			hooks.insertBefore(hook, hooks.getLastChild());
			write(doc);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	public static void buildSkeleton() {
		try {
			DocumentBuilderFactory dFactory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder dBldr = dFactory.newDocumentBuilder();

			Document doc = dBldr.newDocument();
			Element rootElement = doc.createElement("hooks");
			doc.appendChild(rootElement);

			write(doc);
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
	}

	private static void write(Document doc) {
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer;
			transformer = tFactory.newTransformer();
			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File("hooks.xml"));
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(
					"{http://xml.apache.org/xslt}indent-amount", "5");
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
	}

}
