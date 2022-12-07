package com.example.foreigncurrency.service;

import com.example.foreigncurrency.model.Price;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

@Service
public class XMLService {

	private final Logger logger = LoggerFactory.getLogger(XMLService.class);

	public Price parsePrice() {

		Price price = null;

		try {
			String URL = "https://sdw-wsrest.ecb.europa.eu/service/data/EXR/M.USD.EUR.SP00" +
											 ".A?includeHistory=false&lastNObservations=1";

			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document doc = builder.parse(URL);

			doc.getDocumentElement().normalize();

			price =
					new Price(Double
												.parseDouble(doc
																					 .getElementsByTagName("ObsValue")
																					 .item(0)
																					 .getTextContent()));

			System.out.println(price);

		} catch (ParserConfigurationException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		} catch (SAXException e) {
			throw new RuntimeException(e);
		}

		return price;
	}
}
