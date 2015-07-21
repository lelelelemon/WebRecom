package operation;

import io.XMLReaderTest;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import relation.Relation;
import browse.BrowserHis;
import code.CodeHis;
import date.DateUtil;

public class relationBuild {
	List<BrowserHis> browserHisArray;
	List<CodeHis> codeHisArray;
	final double A = 0.5;
	final double B = 0.5;

	public void retrieveBrowserHis() {
		Map<String, String> historys = XMLReaderTest
				.readXMLString("history.xml");
		for (String key : historys.keySet()) {
			String value = historys.get(key);
			Date startTime;
			try {
				startTime = DateUtil.parse(value);
				BrowserHis browserHis = new BrowserHis();
				browserHis.setAddress(key);
				browserHis.setStartTime(startTime);
				browserHisArray.add(browserHis);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public void buildRelation(List<BrowserHis> browserHisArray,
			List<CodeHis> codeHisArray) {
		for (CodeHis codeHis : codeHisArray) {
			Date startTime = codeHis.getStartTime();
			Date endTime = codeHis.getEndTime();
			for (BrowserHis browserHis : browserHisArray) {
				Date broStartTime = browserHis.getStartTime();
				Date broEndTime = browserHis.getEndTime();
				// if the start time of browsertime is between the code history
				if (broStartTime.after(startTime)
						&& broStartTime.before(endTime)) {
					double value = 0;
					value = (broEndTime.getTime() - broStartTime.getTime()) * A
							+ (broStartTime.getTime() - startTime.getTime())
							* B;
					Relation relation = new Relation();
					relation.setBrowerHis_id(browserHis.getId());
					relation.setCodeHis_id(codeHis.getId());
					relation.setValue(value);
				}
			}
		}

	}

	public List<BrowserHis> retrieveRecom(CodeHis code) {
		return null;
	}
}
