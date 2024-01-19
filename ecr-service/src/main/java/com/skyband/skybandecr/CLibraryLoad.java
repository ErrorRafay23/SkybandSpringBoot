package com.skyband.skybandecr;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CLibraryLoad {

	private static Logger logger = Logger.getLogger(CLibraryLoad.class.getName());

	static CLibraryLoad clibraryLoad;

	// static {
	// 	String path = System.getProperty("user.dir");
	// 	logger.info(path);
	// 	String osType = System.getProperty("os.name");
	// 	if (osType.contains("Linux")) {
	// 		logger.info("SO file loaded:");
	// 		System.load(path + "/EcrJava.so");
	// 	} else if (osType.contains("Windows")) {
	// 		logger.info("DLL file loaded:");
	// 		System.load(path + "/EcrJava.dll");
	// 	}

	// }


	
	static {
		String path = System.getProperty("user.dir");
		logger.info(path);
		String osType = System.getProperty("os.name");
		
		String arch = System.getProperty("os.arch").contains("64") ? "64" : "32";
		
		if (osType.contains("Linux")) {
			logger.info("SO file loaded:");
			System.load(path + "/EcrJava.so");
		} 
		else if (osType.contains("Windows")) {
			if (arch.equals("64")) {
				logger.info("DLL file loaded: EcrJava64bit.dll");
				System.load(path + "/EcrJava64bit.dll");
			} else {
				logger.info("DLL file loaded: ECR32bit.dll");
				System.load(path + "/ECR32bit.dll");
			}
		}
	}

	public byte[] getPackData(String reqData, int tranType, String szSignature) {

		logger.info("CLibraryLoad | GetPackData | Entering");

		byte[] packedData = pack(reqData, tranType, szSignature, null);
		String packData = new String(packedData);

		logger.log(Level.INFO, "CLibraryLoad | GetPackData | Exiting {0}", packData);

		return packedData;
	}

	public String getParseData(String respData) {
		logger.info("CLibraryLoad | GetParseData | Entering");

		byte[] parseData = parse(respData, "");
		String parsedData = new String(parseData);

		logger.log(Level.INFO, "CLibraryLoad | GetParseData | Exiting {0}", parsedData);

		return parsedData;
	}

	public static CLibraryLoad getInstance() {

		logger.info("CLibraryLoad | Clibraryload | Entering");

		if (clibraryLoad == null) {
			clibraryLoad = new CLibraryLoad();
		}

		logger.info("CLibraryLoad | Clibraryload | Exiting");

		return clibraryLoad;
	}

	public native byte[] pack(String inputReqData, int transactionType, String szSignature, String szEcrBuffer);

	public native byte[] parse(String respData, String respOutData);

}
