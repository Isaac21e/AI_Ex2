import java.io.*;

/**
 * A class who is responsible to read and write from/to file.
 * it gets the path to the file and reads\writes to it
 */
public class FileHandler {
	/**
	 * reads content from file and returns a string array with file lines
	 * @param path path of file (relative)
	 * @return
	 */
	public static String[] Read(String path) {
		try {
			File file = new File(path);
			FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				stringBuffer.append(line);
				stringBuffer.append("\n");
			}
			fileReader.close();
			//System.out.println(stringBuffer.toString());
			String contentAsArray[] = stringBuffer.toString().split("\\r\\n|\\n|\\r");
			return contentAsArray;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * writes string to file
	 * @param path path of file
	 * @param output string to write to file
	 */
	public static void Write(String path,String output){
		try{
			// Create file
			FileWriter fstream = new FileWriter(path);
			BufferedWriter out = new BufferedWriter(fstream);
			out.write(output);
			//Close the output stream
			out.close();
		}catch (Exception e){//Catch exception if any
			System.err.println("Error: " + e.getMessage());
		}
	}
}
