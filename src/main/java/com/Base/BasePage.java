package com.Base;

import java.io.File;
import java.io.FileReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {

	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	public WebDriver wbdriver = null;
	public String browser;
	String url = "";
	JsonObject jsonObject = readJson();

	// public static String fileSeperator=System.getProperty("file.seperator");

	public BasePage() {
		if (driver.get() == null) {

			browser = jsonObject.get("browser").getAsString();

			System.out.println("The browser is retrieved from json");
			if (browser.equals("chrome")) {
				WebDriverManager.chromedriver().setup();
				wbdriver = new ChromeDriver();
				driver.set(wbdriver);

			}

			driver.get().get(jsonObject.get("url").getAsString());
			driver.get().manage().window().maximize();
		}
	}

	public JsonObject readJson() {
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = null;
		try {

			// File f1 = new
			// File(System.getProperty("user.dir")+fileSeperator+"config_Details");
			/*File f1 = new File("./config_Details/url.json");
			Object obj = parser.parse(new FileReader(f1.getAbsolutePath()));*/
			FileReader f1=new FileReader("./config_Details/url.json");
			Object obj=parser.parse(f1);
			jsonObject = (JsonObject) obj;
		} catch (Exception e) {
			e.printStackTrace();

		}

		return jsonObject;
	}
 	public String readXml(String tagName) {
		String e1 = " ";
		try {

			File file = new File("./config_Details/credentials.xml");

			// an instance of factory that gives a document builder
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

			// an instance of builder to parse the specified xml file
			DocumentBuilder db = dbf.newDocumentBuilder();
			Document doc = db.parse(file);
			doc.getDocumentElement().normalize();
			System.out.println("Root element: " + doc.getDocumentElement().getNodeName());

			NodeList nodeList = doc.getElementsByTagName("login");
			// nodeList is not iterable, so we are using for loop
			for (int itr = 0; itr < nodeList.getLength(); itr++) {
				Node node = nodeList.item(itr);
				System.out.println("\nNode Name :" + node.getNodeName());
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) node;
					e1 = eElement.getElementsByTagName(tagName).item(0).getTextContent();

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return e1;

	}

	public void teardown() {
		driver.get().quit();
	}

	public String getTitle1() {
		return driver.get().getTitle();
	}
}
