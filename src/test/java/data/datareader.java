package data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import testComponents.BaseTest;

public class datareader extends BaseTest {

	public List<HashMap<String, String>> getJsonDatatoMap(String filepath) throws IOException {
		@SuppressWarnings("deprecation")
		String jsonContent = FileUtils.readFileToString(new File(
				System.getProperty("user.dir") + "\\src\\test\\java\\data\\purchaseorder.json"));
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});
		return data;

	}

}
