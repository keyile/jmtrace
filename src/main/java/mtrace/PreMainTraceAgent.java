package mtrace;

import java.lang.instrument.Instrumentation;

public class PreMainTraceAgent {

    public static void premain(String agentArgs, Instrumentation inst) {
        // System.out.println("agentArgs : " + agentArgs);
        inst.addTransformer(new ClassInstrumenter(), true);
    }
}
