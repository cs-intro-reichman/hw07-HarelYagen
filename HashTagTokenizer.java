public class HashTagTokenizer {
	public static void main(String[] args) {
		String hashTag = args[0];
		String[] dictionary = readDictionary("dictionary.txt");
		breakHashTag(hashTag.toLowerCase(), dictionary);
	}

	public static String[] readDictionary(String fileName) {
		String[] dictionary = new String[3000];
		In file = new In(fileName);
        for(int i = 0; i < dictionary.length; i++) {
			dictionary[i] = file.readLine();
		}
		file.close();
		return dictionary;
	}

	public static boolean existInDictionary(String word, String []dictionary) {
		for (int i = 0; i < dictionary.length; i++) {
            if (word.equals(dictionary[i]))
		    	return true;
		}
		return false;    
	}

	public static void breakHashTag(String hashTag, String[] dictionary) {
        if (hashTag.isEmpty()) {
            return;
        }

        int N = hashTag.length();
        for (int i = 1; i <= N; i++) {
			if (existInDictionary(hashTag.substring(0,i), dictionary) == true){
				System.out.println(hashTag.substring(0,i));
				breakHashTag(hashTag.substring(i), dictionary);
			}
        }
    }
}
