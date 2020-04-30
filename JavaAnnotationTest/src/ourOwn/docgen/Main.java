package ourOwn.docgen;

import ourOwn.math.MathUtils;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class Main {
    public static void main(String[] args) {
//        Class<?> clazz = MathUtils.class;
//
//        Method[] methods = clazz.getDeclaredMethods();
//
//        for (Method m: methods) {
//            System.out.println(m.getName());
////            System.out.println(m.getParameterCount());
////            System.out.println(m.getReturnType().getSimpleName());
////            System.out.println(m.getModifiers());
//
//            int mods = m.getModifiers();
//            String modStr = Modifier.toString(mods);
//            System.out.println(modStr);
//
//            System.out.println();
//        }

        // TODO: Process the MathUtils class's annotations
        DocProcessor.process(MathUtils.class);


    }
}