public class SpellChecker {
	public static void main(String[] args) {
		String word1 = args[0];
		int threshold = Integer.parseInt(args[1]);
		String[] dictionary = readDictionary("dictionary.txt");
		String correction = spellChecker(word1, threshold, dictionary);
		System.out.println(correction);
	}

	public static String head(String str) {
		if (str.isEmpty()){
			return "";
		}
		return str.substring(0, 1);
	}
	
	public static String tail(String str) {
		if (str.length() == 1){
			return "";
		}
		return str.substring(1);
	}

	public static int levenshtein(String word1, String word2) {
		if(word1.length() == 0){
			return word2.length();
		}

		else if(word2.length() == 0){
			return word1.length();
		}
		else if(head(word1) == head(word2)){
            levenshtein(tail(word1), tail(word2));
		}

		int adition = levenshtein(tail(word1), word2);
		int deleretion = levenshtein(word1, tail(word2));
		int subsitution = levenshtein(tail(word1), tail(word2));
		int min = 3;

		int [] bestway = {adition,deleretion,subsitution};
		for(int i = 0; i < 3; i++){
			int temp = bestway[i];
			int shortway = 15;  
			shortway = Math.min((bestway[i]), shortway);
			if(shortway < temp ){
			    min = i;
			}
		}
		return 1 + bestway[min];
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

	public static String spellChecker(String word, int threshold, String[] dictionary) {
		int minerror = 15;
		String min = "";
		int temp = 15;
		for(int i = 0; i < dictionary.length; i++){
			temp = minerror; 
			minerror = Math.min(levenshtein(dictionary[i], word), minerror);
			if(minerror < temp ){
				min = dictionary[i];
			}
		}
		if(threshold >= minerror){
           return min;
		}else{
			return word;
		}
	}

}
