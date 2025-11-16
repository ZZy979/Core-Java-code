package panama;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import jdk.incubator.foreign.CLinker;
import jdk.incubator.foreign.FunctionDescriptor;
import jdk.incubator.foreign.MemoryAddress;
import jdk.incubator.foreign.ResourceScope;

public class PanamaDemo {
    public static void main(String[] args) throws Throwable {
        CLinker linker = CLinker.getInstance();
        MethodHandle printf = linker.downcallHandle(
                CLinker.systemLookup().lookup("printf").get(),
                MethodType.methodType(int.class, MemoryAddress.class),
                FunctionDescriptor.of(CLinker.C_INT, CLinker.C_POINTER));

        try (ResourceScope scope = ResourceScope.newConfinedScope()) {
            var cString = CLinker.toCString("Hello, World!\n", scope);
            int result = (int) printf.invoke(cString.address());
            System.out.println("Printed %d characters.".formatted(result));
        }
    }
}
