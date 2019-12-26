package test;

public class TestMain {
	public static void main(String[] args) {
		System.out.println("main");

		int[] a = new int[10];
		for (int i = 0; i < a.length; i++) {
			a[i] = 0;
//			Print.traceArrayWrite(a, i, null);
		}
//		Print.traceArrayWrite(a, 8, null);

		SomeClass someObj = new SomeClass();
		SomeClass.staticField = 1;
//		Print.traceStaticWrite(SomeClass.class, "staticField", null);

		someObj.otherField = someObj.field;
//		Print.traceFieldRead(someObj, "field", null);
	}
}

