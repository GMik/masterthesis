package com.vp.plugin.connectors.businessrules;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ISBVRFileConnector {

	SBVRFileElementsContainer loadSBVRData() throws FileNotFoundException, IOException;
}
