package fuzz_targets;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.KryoException;
import java.io.*;
import com.code_intelligence.jazzer.api.FuzzedDataProvider;

public class deserialize {
	public static void fuzzerTestOneInput(byte[] input) {
		Kryo kryo = new Kryo();
		kryo.register(SomeClass.class);

		SomeClass object = new SomeClass();
		// object.value = data.consumeRemainingAsString();

		try {
			Output output = new Output(new FileOutputStream("file.bin"));
			System.out.println("before write");
			kryo.writeObject(output, input);
			System.out.println("after write");
			output.close();
		} catch (FileNotFoundException e) {
			//TODO: handle exception
		} catch (IllegalArgumentException e) {
			//TODO: handle exception
		}
		
		try {
			Input in = new Input(input);
			System.out.println("before read");
			//SomeClass object2 = kryo.readObject(in, SomeClass.class);
			SomeClass object2 = kryo.readObjectOrNull(in, SomeClass.class);
			System.out.println("after read");
			in.close();
		} catch (KryoException e) {
			//TODO: handle exception
		}
		
	}
	static public class SomeClass {
		private String value;
	}
}
