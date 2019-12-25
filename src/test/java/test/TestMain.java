package test;

public class TestMain {
	public static void main(String[] args) {
		System.out.println("main");

		int[] a = new int [10];
		for (int i = 0; i < a.length; i++) {
			a[i] = 0;
		}
//		System.out.println(a[0]);

		SomeClass someObj= new SomeClass();
		SomeClass.staticField = 1;
//		Print.traceStaticWrite(SomeClass.class, "staticField", null);

		someObj.otherField = someObj.field;
//		Print.traceFieldRead(someObj, "field", null);
	}
}

