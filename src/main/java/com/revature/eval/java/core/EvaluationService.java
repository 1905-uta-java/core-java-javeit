package com.revature.eval.java.core;

import java.time.LocalDateTime;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class EvaluationService {

	/**
	 * 1. Without using the StringBuilder or StringBuffer class, write a method that
	 * reverses a String. Example: reverse("example"); -> "elpmaxe"
	 * 
	 * @param string
	 * @return
	 */
	public String reverse(String string) {
		
		String reversedString = "";
		
		for(int i = string.length() - 1; i >= 0; i--) {
			
			reversedString += string.charAt(i);
		}
		
		return reversedString;
	}
	
	/**
	 * 2. Convert a phrase to its acronym. Techies love their TLA (Three Letter
	 * Acronyms)! Help generate some jargon by writing a program that converts a
	 * long name like Portable Network Graphics to its acronym (PNG).
	 * 
	 * @param phrase
	 * @return
	 */
	public String acronym(String phrase) {
		
		// split the phrase by several types of punctuation and whitespace
		String[] words = phrase.split("[-.,?!_\"\\s]+");
		
		// create a variable to hold the new acronym
		String acronym = "";
		
		// iterate through each new word from the split, and add the first letter
		// of each non-empty word to the acronym
		for(String word : words) {
			if(word.length() > 0)
				acronym += word.charAt(0);
		}
		
		// ensure the acronym is in upper case
		acronym = acronym.toUpperCase();
		
		return acronym;
	}

	/**
	 * 3. Determine if a triangle is equilateral, isosceles, or scalene. An
	 * equilateral triangle has all three sides the same length. An isosceles
	 * triangle has at least two sides the same length. (It is sometimes specified
	 * as having exactly two sides the same length, but for the purposes of this
	 * exercise we'll say at least two.) A scalene triangle has all sides of
	 * different lengths.
	 *
	 */
	static class Triangle {
		private double sideOne;
		private double sideTwo;
		private double sideThree;

		public Triangle() {
			super();
		}

		public Triangle(double sideOne, double sideTwo, double sideThree) {
			this();
			this.sideOne = sideOne;
			this.sideTwo = sideTwo;
			this.sideThree = sideThree;
		}

		public double getSideOne() {
			return sideOne;
		}

		public void setSideOne(double sideOne) {
			this.sideOne = sideOne;
		}

		public double getSideTwo() {
			return sideTwo;
		}

		public void setSideTwo(double sideTwo) {
			this.sideTwo = sideTwo;
		}

		public double getSideThree() {
			return sideThree;
		}

		public void setSideThree(double sideThree) {
			this.sideThree = sideThree;
		}

		public boolean isEquilateral() {
			
			return sideOne == sideTwo && sideTwo == sideThree;
		}

		public boolean isIsosceles() {
			
			return !isScalene();
		}

		public boolean isScalene() {
			if(sideOne == sideTwo)
				return false;
			if(sideOne == sideThree)
				return false;
			if(sideTwo == sideThree)
				return false;
			return true;
		}
	}
	
	static HashMap<Character, Integer> scrabblePoints = new HashMap<Character, Integer>(){{
		put('a', 1);
		put('e', 1);
		put('i', 1);
		put('o', 1);
		put('u', 1);
		put('l', 1);
		put('n', 1);
		put('r', 1);
		put('s', 1);
		put('t', 1);
		put('d', 2);
		put('g', 2);
		put('b', 3);
		put('c', 3);
		put('m', 3);
		put('p', 3);
		put('f', 4);
		put('h', 4);
		put('v', 4);
		put('w', 4);
		put('y', 4);
		put('k', 5);
		put('j', 8);
		put('x', 8);
		put('q', 10);
		put('z', 10);
	}};
	
	/**
	 * 4. Given a word, compute the scrabble score for that word.
	 * 
	 * --Letter Values-- Letter Value A, E, I, O, U, L, N, R, S, T = 1; D, G = 2; B,
	 * C, M, P = 3; F, H, V, W, Y = 4; K = 5; J, X = 8; Q, Z = 10; Examples
	 * "cabbage" should be scored as worth 14 points:
	 * 
	 * 3 points for C, 1 point for A, twice 3 points for B, twice 2 points for G, 1
	 * point for E And to total:
	 * 
	 * 3 + 2*1 + 2*3 + 2 + 1 = 3 + 2 + 6 + 3 = 5 + 9 = 14
	 * 
	 * @param string
	 * @return
	 */
	public int getScrabbleScore(String string) {
		
		string = string.toLowerCase();
		int wordScore = 0;
		
		for(int i = 0; i < string.length(); i++) {
			wordScore += scrabblePoints.get(string.charAt(i));
		}
		
		return wordScore;
	}
	
	/**
	 * valid non-number characters which can be a part of a phone number input for the
	 * cleanPhoneNumber method
	 */
	static final ArrayList<Character> VALID_NONNUMBERS = new ArrayList<Character>() {{
		add('-');
		add('.');
		add('(');
		add(')');
		add('+');
	}};
	
	/**
	 * 5. Clean up user-entered phone numbers so that they can be sent SMS messages.
	 * 
	 * The North American Numbering Plan (NANP) is a telephone numbering system used
	 * by many countries in North America like the United States, Canada or Bermuda.
	 * All NANP-countries share the same international country code: 1.
	 * 
	 * NANP numbers are ten-digit numbers consisting of a three-digit Numbering Plan
	 * Area code, commonly known as area code, followed by a seven-digit local
	 * number. The first three digits of the local number represent the exchange
	 * code, followed by the unique four-digit number which is the subscriber
	 * number.
	 * 
	 * The format is usually represented as
	 * 
	 * 1 (NXX)-NXX-XXXX where N is any digit from 2 through 9 and X is any digit
	 * from 0 through 9.
	 * 
	 * Your task is to clean up differently formatted telephone numbers by removing
	 * punctuation and the country code (1) if present.
	 * 
	 * For example, the inputs
	 * 
	 * +1 (613)-995-0253 613-995-0253 1 613 995 0253 613.995.0253 should all produce
	 * the output
	 * 
	 * 6139950253
	 * 
	 * Note: As this exercise only deals with telephone numbers used in
	 * NANP-countries, only 1 is considered a valid country code.
	 */
	public String cleanPhoneNumber(String string) throws IllegalArgumentException {
		
		String cleanedNumber = "";
		
		for(char c : string.toCharArray()) {
			
			if(c == '1' && cleanedNumber.length() == 0)
				continue;
			
			if(Character.isDigit(c))
				cleanedNumber += c;
			else if(Character.isWhitespace(c) || VALID_NONNUMBERS.contains(c))
				continue;
			else
				throw new IllegalArgumentException();
		}
		
		if(cleanedNumber.length() != 10)
			throw new IllegalArgumentException();
		
		return cleanedNumber;
	}

	/**
	 * 6. Given a phrase, count the occurrences of each word in that phrase.
	 * 
	 * For example for the input "olly olly in come free" olly: 2 in: 1 come: 1
	 * free: 1
	 * 
	 * @param string
	 * @return
	 */
	public Map<String, Integer> wordCount(String string) {
		
		Map<String, Integer> count = new HashMap<String, Integer>();
		
		for(String subString : string.split("\\P{Alpha}+")) {
			if(count.containsKey(subString))
				count.put(subString, count.get(subString) + 1);
			else
				count.put(subString, 1);
		}
		
		return count;
	}

	/**
	 * 7. Implement a binary search algorithm.
	 * 
	 * Searching a sorted collection is a common task. A dictionary is a sorted list
	 * of word definitions. Given a word, one can find its definition. A telephone
	 * book is a sorted list of people's names, addresses, and telephone numbers.
	 * Knowing someone's name allows one to quickly find their telephone number and
	 * address.
	 * 
	 * If the list to be searched contains more than a few items (a dozen, say) a
	 * binary search will require far fewer comparisons than a linear search, but it
	 * imposes the requirement that the list be sorted.
	 * 
	 * In computer science, a binary search or half-interval search algorithm finds
	 * the position of a specified input value (the search "key") within an array
	 * sorted by key value.
	 * 
	 * In each step, the algorithm compares the search key value with the key value
	 * of the middle element of the array.
	 * 
	 * If the keys match, then a matching element has been found and its index, or
	 * position, is returned.
	 * 
	 * Otherwise, if the search key is less than the middle element's key, then the
	 * algorithm repeats its action on the sub-array to the left of the middle
	 * element or, if the search key is greater, on the sub-array to the right.
	 * 
	 * If the remaining array to be searched is empty, then the key cannot be found
	 * in the array and a special "not found" indication is returned.
	 * 
	 * A binary search halves the number of items to check with each iteration, so
	 * locating an item (or determining its absence) takes logarithmic time. A
	 * binary search is a dichotomic divide and conquer search algorithm.
	 * 
	 */
	static class BinarySearch<T extends Comparable<T>> {
		private List<T> sortedList;

		public int indexOf(T t) {
			
			int lowerBound = 0;
			int upperBound = sortedList.size();
			int currentIndex = lowerBound + (upperBound - lowerBound) / 2;
			
			while(upperBound != lowerBound) {
				
				currentIndex = lowerBound + (upperBound - lowerBound) / 2;
				
				if(sortedList.get(currentIndex).compareTo(t) == 0)
					return currentIndex;	
				else if(sortedList.get(currentIndex).compareTo(t) > 0)
					upperBound = currentIndex;
				else
					lowerBound = currentIndex;
			}
			
			return -1;
		}
		
		public BinarySearch(List<T> sortedList) {
			super();
			this.sortedList = sortedList;
		}
		
		public List<T> getSortedList() {
			return sortedList;
		}
		
		public void setSortedList(List<T> sortedList) {
			this.sortedList = sortedList;
		}
	}


	/**
	 * 8. An Armstrong number is a number that is the sum of its own digits each
	 * raised to the power of the number of digits.
	 * 
	 * For example:
	 * 
	 * 9 is an Armstrong number, because 9 = 9^1 = 9 10 is not an Armstrong number,
	 * because 10 != 1^2 + 0^2 = 2 153 is an Armstrong number, because: 153 = 1^3 +
	 * 5^3 + 3^3 = 1 + 125 + 27 = 153 154 is not an Armstrong number, because: 154
	 * != 1^3 + 5^3 + 4^3 = 1 + 125 + 64 = 190 Write some code to determine whether
	 * a number is an Armstrong number.
	 * 
	 * @param input
	 * @return
	 */
	public boolean isArmstrongNumber(int input) {
		
		int product = 0;
		int digit = 0;
		int placeholder = input;
		
		ArrayList<Integer> digits = new ArrayList<Integer>();
		
		while(placeholder != 0) {
			
			digit = placeholder % 10;
			digits.add(digit);
			
			placeholder /= 10;
		}
		
		for(int i : digits) {
			product += (int)Math.pow((double) i, (double) digits.size());
		}
		
		return product == input;
	}

	/**
	 * 9. Determine if a sentence is a pangram. A pangram (Greek: παν γράμμα, pan
	 * gramma, "every letter") is a sentence using every letter of the alphabet at
	 * least once. The best known English pangram is:
	 * 
	 * The quick brown fox jumps over the lazy dog.
	 * 
	 * The alphabet used consists of ASCII letters a to z, inclusive, and is case
	 * insensitive. Input will not contain non-ASCII symbols.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isPangram(String string) {
		
		string = string.toLowerCase();
		
		HashSet<Character> usedLetters = new HashSet<Character>();
		
		char[] inputChars = string.toCharArray();
		
		for(char c : inputChars) {
			if(Character.isAlphabetic(c))
				usedLetters.add(c);
		}
		
		return usedLetters.size() == 26;
	}
	
	
	/**
	 * 10. Create an implementation of the rotational cipher, also sometimes called
	 * the Caesar cipher.
	 * 
	 * The Caesar cipher is a simple shift cipher that relies on transposing all the
	 * letters in the alphabet using an integer key between 0 and 26. Using a key of
	 * 0 or 26 will always yield the same output due to modular arithmetic. The
	 * letter is shifted for as many values as the value of the key.
	 * 
	 * The general notation for rotational ciphers is ROT + <key>. The most commonly
	 * used rotational cipher is ROT13.
	 * 
	 * A ROT13 on the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: nopqrstuvwxyzabcdefghijklm It is
	 * stronger than the Atbash cipher because it has 27 possible keys, and 25
	 * usable keys.
	 * 
	 * Ciphertext is written out in the same formatting as the input including
	 * spaces and punctuation.
	 * 
	 * Examples: ROT5 omg gives trl ROT0 c gives c ROT26 Cool gives Cool ROT13 The
	 * quick brown fox jumps over the lazy dog. gives Gur dhvpx oebja sbk whzcf bire
	 * gur ynml qbt. ROT13 Gur dhvpx oebja sbk whzcf bire gur ynml qbt. gives The
	 * quick brown fox jumps over the lazy dog.
	 */
	static class RotationalCipher {
		private int key;

		public RotationalCipher(int key) {
			super();
			this.key = key;
		}

		public String rotate(String string) {
			
			char[] chars = string.toCharArray();
			char c;
			int placeholder;
			
			for(int i = 0; i < chars.length; i++) {
				
				c = chars[i];
				
				if(Character.isAlphabetic(c)) {
					
					if(Character.isUpperCase(c)) {
						
						placeholder = (int)c - (int)'A';
						
						placeholder = (placeholder + key) % 26;
						
						chars[i] = (char)(placeholder + (int)'A');
						
					} else {
						
						placeholder = (int)c - (int)'a';
						
						placeholder = (placeholder + key) % 26;
						
						chars[i] = (char)(placeholder + (int)'a');
					}
				}
			}
			
			return new String(chars).intern();
		}

	}
	

	/**
	 * 11 & 12. Create an implementation of the atbash cipher, an ancient encryption
	 * system created in the Middle East.
	 * 
	 * The Atbash cipher is a simple substitution cipher that relies on transposing
	 * all the letters in the alphabet such that the resulting alphabet is
	 * backwards. The first letter is replaced with the last letter, the second with
	 * the second-last, and so on.
	 * 
	 * An Atbash cipher for the Latin alphabet would be as follows:
	 * 
	 * Plain: abcdefghijklmnopqrstuvwxyz Cipher: zyxwvutsrqponmlkjihgfedcba It is a
	 * very weak cipher because it only has one possible key, and it is a simple
	 * monoalphabetic substitution cipher. However, this may not have been an issue
	 * in the cipher's time.
	 * 
	 * Ciphertext is written out in groups of fixed length, the traditional group
	 * size being 5 letters, and punctuation is excluded. This is to make it harder
	 * to guess things based on word boundaries.
	 * 
	 * Examples Encoding test gives gvhg Decoding gvhg gives test Decoding gsvjf
	 * rxpyi ldmul cqfnk hlevi gsvoz abwlt gives thequickbrownfoxjumpsoverthelazydog
	 *
	 */
	static class AtbashCipher {
		
		/**
		 * returns a character which represents the encoded
		 * equivalent of that character
		 * @param inputChar
		 * @return
		 */
		public static char encodeChar(char inputChar) {
			if(Character.isAlphabetic(inputChar))
				return (char)((int)'z' - ((int)inputChar - (int)'a'));
			else
				return inputChar;
		}
		
		/**
		 * Question 11
		 * 
		 * @param string
		 * @return
		 */
		public static String encode(String string) {
			
			StringBuilder builder = new StringBuilder();
			
			string = string.toLowerCase();
			
			for(int i = 0; i < string.length(); i++) {
				
				if(Character.isAlphabetic(string.charAt(i)) || Character.isDigit(string.charAt(i))) {
					if(builder.length() % 6 == 5)
						builder.append(' ');
					
					builder.append(encodeChar(string.charAt(i)));
				}
			}
			
			return builder.toString();
		}
		
		/**
		 * Question 12
		 * 
		 * @param string
		 * @return
		 */
		public static String decode(String string) {
			
			StringBuilder builder = new StringBuilder();
			
			string = string.toLowerCase();
			
			for(int i = 0; i < string.length(); i++) {
				
				if(Character.isAlphabetic(string.charAt(i)))
					builder.append(encodeChar(string.charAt(i)));
				else if(Character.isDigit(string.charAt(i)))
					builder.append(string.charAt(i));
			}
			
			return builder.toString();
		}
	}

	/**
	 * 13. (Optional) The ISBN-10 verification process is used to validate book identification
	 * numbers. These normally contain dashes and look like: 3-598-21508-8
	 * 
	 * ISBN The ISBN-10 format is 9 digits (0 to 9) plus one check character (either
	 * a digit or an X only). In the case the check character is an X, this
	 * represents the value '10'. These may be communicated with or without hyphens,
	 * and can be checked for their validity by the following formula:
	 * 
	 * (x1 * 10 + x2 * 9 + x3 * 8 + x4 * 7 + x5 * 6 + x6 * 5 + x7 * 4 + x8 * 3 + x9
	 * * 2 + x10 * 1) mod 11 == 0 If the result is 0, then it is a valid ISBN-10,
	 * otherwise it is invalid.
	 * 
	 * Example Let's take the ISBN-10 3-598-21508-8. We plug it in to the formula,
	 * and get:
	 * 
	 * (3 * 10 + 5 * 9 + 9 * 8 + 8 * 7 + 2 * 6 + 1 * 5 + 5 * 4 + 0 * 3 + 8 * 2 + 8 *
	 * 1) mod 11 == 0 Since the result is 0, this proves that our ISBN is valid.
	 * 
	 * @param string
	 * @return
	 */
	public boolean isValidIsbn(String string) {
		
		int sum = 0;
		int numDigits = 0;
		
		for(int i = 0; i < string.length(); i++) {
			
			if(Character.isDigit(string.charAt(i))) {
				
				sum += (10 - numDigits) * Integer.parseInt("" + string.charAt(i));
				
				numDigits++;
				
			} else if(numDigits == 9 && string.charAt(i) == 'X') {
				
				sum += 10;
				
				numDigits++;
				
			} else if(string.charAt(i) == '-') {
				
				// if there is a dash, it should only be allowed at these indexes
				// if a dash is encountered in another index, it may indicate that
				// the input is in an invalid or inconsistent format
				if(i != 1 && i != 5 && i != 11)
					return false;
				
			} else {
				
				// if any non-valid characters are encountered 
				// (i.e. letter other than 'X', an 'X' in an invalid 
				// position, a '-' in an invalid position, or other 
				// non-digit characters), then return false
				return false;
			}
		}
		
		// handle cases where an incorrect number of symbols were entered
		if(numDigits != 10)
			return false;
		
		return sum % 11 == 0;
	}
	
	static final int DAYS_PER_YEAR = 365;
	static final int DAYS_PER_LEAP_YEAR = 366;
	static final int MINUTES_PER_HOUR = 60;
	static final int SECONDS_PER_MINUTE = 60;
	static final int HOURS_PER_DAY = 24;
	
	/**
	 * 14. (Optional) Calculate the moment when someone has lived for 10^9 seconds.
	 * 
	 * A gigasecond is 109 (1,000,000,000) seconds.
	 * 
	 * @param given
	 * @return
	 */
	public Temporal getGigasecondDate(Temporal given) {
		
		LocalDateTime time = LocalDateTime.of(given.get(ChronoField.YEAR),given.get(ChronoField.MONTH_OF_YEAR), given.get(ChronoField.DAY_OF_MONTH), 0, 0, 0);
		
		if(given.isSupported(ChronoField.HOUR_OF_DAY))
			time = time.plus(given.get(ChronoField.HOUR_OF_DAY), ChronoUnit.HOURS);
		
		if(given.isSupported(ChronoField.MINUTE_OF_HOUR))
			time = time.plus(given.get(ChronoField.MINUTE_OF_HOUR), ChronoUnit.MINUTES);
		
		if(given.isSupported(ChronoField.SECOND_OF_MINUTE))
			time = time.plus(given.get(ChronoField.SECOND_OF_MINUTE), ChronoUnit.SECONDS);
		
		return time.plus(1000000000, ChronoUnit.SECONDS);
	}
	
	static final String PLUS = "plus";
	static final String MINUS = "minus";
	static final String MULTIPLY = "multiplied by";
	static final String DIVIDE = "divided by";
	
	/**
	 * 15. (Optional) Parse and evaluate simple math word problems returning the answer as an
	 * integer.
	 * 
	 * Add two numbers together.
	 * 
	 * What is 5 plus 13?
	 * 
	 * 18
	 * 
	 * Now, perform the other three operations.
	 * 
	 * What is 7 minus 5?
	 * 
	 * 2
	 * 
	 * What is 6 multiplied by 4?
	 * 
	 * 24
	 * 
	 * What is 25 divided by 5?
	 * 
	 * 5
	 * 
	 * @param string
	 * @return
	 */
	public int solveWordProblem(String string) {
		
		string = string.toLowerCase();
		
		string = string.replaceAll(PLUS, "+");
		string = string.replaceAll(MINUS, "-");
		string = string.replaceAll(MULTIPLY, "*");
		string = string.replaceAll(DIVIDE, "/");
		
		ArrayList<String> list = new ArrayList<String>();
		
		for(String str : string.split("[\\s?\\p{Alpha}]+")) {
			if(!str.isEmpty())
				list.add(str);
		}
		
		try {
			
			int int1 = Integer.parseInt(list.get(0));
			int int2 = Integer.parseInt(list.get(2));
			
			switch(list.get(1)) {
			case "+":
				return int1 + int2;
			case "-":
				return int1 - int2;
			case "*":
				return int1 * int2;
			case "/":
				return int1 / int2;
			}
			
			return 0;
			
		} catch(NumberFormatException e) {
			return 0;
		} catch(IndexOutOfBoundsException e) {
			return 0;
		}
	}
}
