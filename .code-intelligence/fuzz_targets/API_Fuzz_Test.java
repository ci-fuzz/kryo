
package fuzz_targets;

public class API_Fuzz_Test {
	public static void fuzzerTestOneInput(byte[] input) {
		CallYourAPI(input); // TODO call your API here
	}
}
