package com.example.foreigncurrency.controller;

import com.example.foreigncurrency.model.Price;
import com.example.foreigncurrency.service.XMLService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class CurrencyController {

	@Autowired
	XMLService xmlService;

	private static Logger logger = LoggerFactory.getLogger(CurrencyController.class);

	@GetMapping("/price")
	public Price getPrice() {
		Price price = xmlService.parsePrice();
		return price;
	}


	@PostMapping("/price")
	private ResponseEntity<String> getPrice(@RequestBody Object price) {
// input: A price object contains europrice and currency.

	// https://sdw-wsrest.ecb.europa.eu/service/data/EXR/M.USD.EUR.SP00.A?includeHistory=false&lastNObservations=1


		// The price is calculated as the original price divided by the exchange rate. The price in the business
		//database is assumed to be in Euro.
		//The currency is provided as price object as a request data. The endpoint returns a String
		//representation of the resulting price.

		return new ResponseEntity<String>("Hello", HttpStatus.OK);

	}
}


/* Response looks like this:

<?xml version="1.0" encoding="UTF-8"?>
<message:GenericData xmlns:message="http://www.sdmx.org/resources/sdmxml/schemas/v2_1/message" xmlns:common="http://www.sdmx.org/resources/sdmxml/schemas/v2_1/common" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:generic="http://www.sdmx.org/resources/sdmxml/schemas/v2_1/data/generic" xsi:schemaLocation="http://www.sdmx.org/resources/sdmxml/schemas/v2_1/message https://sdw-wsrest.ecb.europa.eu:443/vocabulary/sdmx/2_1/SDMXMessage.xsd http://www.sdmx.org/resources/sdmxml/schemas/v2_1/common https://sdw-wsrest.ecb.europa.eu:443/vocabulary/sdmx/2_1/SDMXCommon.xsd http://www.sdmx.org/resources/sdmxml/schemas/v2_1/data/generic https://sdw-wsrest.ecb.europa.eu:443/vocabulary/sdmx/2_1/SDMXDataGeneric.xsd">
    <message:Header>
        <message:ID>00c24461-5887-4866-a8c1-59db372cc438</message:ID>
        <message:Test>false</message:Test>
        <message:Prepared>2022-12-06T22:35:53.735+01:00</message:Prepared>
        <message:Sender id="ECB"/>
        <message:Structure structureID="ECB_EXR1" dimensionAtObservation="TIME_PERIOD">
            <common:Structure>
                <URN>urn:sdmx:org.sdmx.infomodel.datastructure.DataStructure=ECB:ECB_EXR1(1.0)</URN>
            </common:Structure>
        </message:Structure>
    </message:Header>
    <message:DataSet action="Replace" validFromDate="2022-12-06T22:35:53.735+01:00" structureRef="ECB_EXR1">
        <generic:Series>
            <generic:SeriesKey>
                <generic:Value id="FREQ" value="M"/>
                <generic:Value id="CURRENCY" value="USD"/>
                <generic:Value id="CURRENCY_DENOM" value="EUR"/>
                <generic:Value id="EXR_TYPE" value="SP00"/>
                <generic:Value id="EXR_SUFFIX" value="A"/>
            </generic:SeriesKey>
            <generic:Attributes>
                <generic:Value id="DECIMALS" value="4"/>
                <generic:Value id="UNIT" value="USD"/>
                <generic:Value id="TIME_FORMAT" value="P1M"/>
                <generic:Value id="TITLE" value="US dollar/Euro"/>
                <generic:Value id="UNIT_MULT" value="0"/>
                <generic:Value id="TITLE_COMPL" value="ECB reference exchange rate, US dollar/Euro, 2:15 pm (C.E.T.)"/>
                <generic:Value id="COLLECTION" value="A"/>
                <generic:Value id="SOURCE_AGENCY" value="4F0"/>
            </generic:Attributes>
            <generic:Obs>
                <generic:ObsDimension value="2022-11"/>
                <generic:ObsValue value="1.0201272727273"/>
                <generic:Attributes>
                    <generic:Value id="OBS_STATUS" value="A"/>
                    <generic:Value id="OBS_CONF" value="F"/>
                </generic:Attributes>
            </generic:Obs>
        </generic:Series>
    </message:DataSet>
</message:GenericData> */