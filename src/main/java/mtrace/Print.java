package mtrace;

public class Print {
    void traceArrayRead() {

    }
    void traceArrayWrite() {

    }
    static void traceFieldRead(Object obj, String field, Object value) {
        String rw = "R";
        long treadNumber = Thread.currentThread().getId();
        long id = System.identityHashCode(obj);
        String member = obj.getClass().getCanonicalName() + "." + field;

        System.out.printf("%s %d %x %s\n", rw, treadNumber, id, member);
    }
    void traceFieldWrite(Object obj, String field, Object value) {
        String rw = "W";
        long treadNumber = Thread.currentThread().getId();
        long id = System.identityHashCode(obj);
        String member = obj.getClass().getCanonicalName() + "." + field;

        System.out.printf("%s %d %x %s\n", rw, treadNumber, id, member);
    }
    void traceStaticRead() {

    }
    void traceStaticWrite() {

    }
}
