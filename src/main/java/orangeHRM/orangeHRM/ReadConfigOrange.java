package orangeHRM.orangeHRM;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ReadConfigOrange {

	public Properties proOrange;

	public ReadConfigOrange() {
		FileInputStream file = null;
		File src = new File("./Configuration/configOrange.properties");
		try {
			file = new FileInputStream(src);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		proOrange = new Properties();

		try {
			proOrange.load(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getURLOrange() {
		String getURLOr = proOrange.getProperty("urlOrange");
		return getURLOr;
	}

	public String urlEmployeeListOrange() {
		String getURLOr = proOrange.getProperty("urlEmployeeList");
		return getURLOr;
	}

	public String getURLLeaveList() {
		String getURLLeaveList = proOrange.getProperty("urlLeaveList");
		return getURLLeaveList;
	}

	public String getUserNameOrange() {
		String usernameOr = proOrange.getProperty("userOr");
		return usernameOr;
	}

	public String getPasswordOrange() {
		String passwordOrange = proOrange.getProperty("passwordOR");
		return passwordOrange;
	}

	public String getPathChr() {
		String pathCh = proOrange.getProperty("pathchr");
		return pathCh;
	}

	public String getPathFF() {
		String pathFF = proOrange.getProperty("firefoxpath");
		return pathFF;
	}

	public String getPathIE() {
		String pathIE = proOrange.getProperty("iepath");
		return pathIE;
	}

	public String assignLeaveEmplName() {
		String assignLeaveEmpName = proOrange.getProperty("emplName");
		return assignLeaveEmpName;
	}

	public String leaveType() {
		String leaveType = proOrange.getProperty("leaveType");
		return leaveType;
	}

	public String fromDate() {
		String fromDate = proOrange.getProperty("fromDate");
		return fromDate;
	}

	public String toDate() {
		String toDate = proOrange.getProperty("toDate");
		return toDate;
	}

	public String balanceAdnotation() {
		String balanceAdnot = proOrange.getProperty("balanceAdd");
		return balanceAdnot;
	}

}
