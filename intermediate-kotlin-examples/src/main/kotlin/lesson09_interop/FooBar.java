package lesson09_interop;

import org.jetbrains.annotations.NotNull;

public class FooBar {
    private int bar = 0;

    // this getter doesn't actually return the type of bar
    // so it doesn't support property access syntax from Kotlin
    public void getBar() {
        System.out.println("Called getter!");
        return;
    }

    // this getter would enable property access syntax from Kotlin
//    public int getBar() {
//        return bar;
//    }



    public @NotNull
    String makeString() {
        return null;
    }

    public String returnPlatformType() {
        return "";
    }
}