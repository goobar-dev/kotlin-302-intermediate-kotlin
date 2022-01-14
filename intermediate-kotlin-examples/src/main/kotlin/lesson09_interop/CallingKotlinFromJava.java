package lesson09_interop;

public class CallingKotlinFromJava {

    public void interactWithKotlin() {
        KotlinClass kotlinClass = new KotlinClass();
        kotlinClass.jvmFoo(); // access from Java as jvmFoo rather than foo

        kotlinClass.setAKotlinProperty("setting the value");
        String property = kotlinClass.getAKotlinProperty();

        Kotlin_java_interopKt.doSomething(kotlinClass); // call the Any.doSomething() extension
    }

}
