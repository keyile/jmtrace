
public class Test{
	public static void main(String[] args) {
//		int[] a = new int [10];
//		for (int i = 0; i < a.length; i++) {
//			a[i] = 0;
//		}
		System.out.println("main");

		A a = new A();
		double zz = a.f;
		char cc = A.c;
		a.f=3.14;
		A.c ='l';
	}
}
