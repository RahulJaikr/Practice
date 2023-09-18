package frameWork.Data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<String, String>> getJsonDataToMap(String filePath) throws IOException

	{
// read json to sring
//	String jsonContetnt = FileUtils.readFileToString
//	(new File(System.getProperty("/FrameWork1/src/test/java/frameWork/Data/PurchaseOrder.json"),StandardCharsets.UTF_8);

		String jsonContetnt = FileUtils.readFileToString(new File(filePath));
		// need string to HasMap Jackson databind
		// we are sending the file path as a argument so if we need to call another json so we directly call from method
		ObjectMapper mapper = new ObjectMapper();

		List<HashMap<String, String>> data = mapper.readValue(jsonContetnt,
		new TypeReference<List<HashMap<String, String>>>() {});

		return data;
	}
}
