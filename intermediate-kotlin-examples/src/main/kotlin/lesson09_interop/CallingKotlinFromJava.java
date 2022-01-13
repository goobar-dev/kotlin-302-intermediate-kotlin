package lesson09_interop;

public class CallingKotlinFromJava {

    public void interactWithKotlin() {
        KotlinClass kotlinClass = new KotlinClass();
        kotlinClass.jvmFoo();
    }

}
