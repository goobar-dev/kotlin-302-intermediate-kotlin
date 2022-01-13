package lesson01_basics_overview

// Interfaces

// Use the interface keyword to define an interface
interface Foo {
    // functions defined on the interface must be implemented
    // or marked as abstract in implementing classes
    fun doFoo()

    // can provide default implementations of an interface method
    fun doBar() = println("Bar has been done")

    // interfaces may include abstract properties
    val prop: Int

    // but interfaces can't contain state
    // you can't access backing fields
    val propertyWithImplementation: Int get() = 4
}

// interfaces do not require any methods or properties
interface Bar
interface Baz

open class Base

// a class can implement one or more interfaces
// constructor of super class
class Derived(override val prop: Int) :
    Base(), /* super class precedes interface(s) */
    Foo,
    Bar,
    Baz {
    override fun doFoo() = Unit
}

// interfaces can inherit from other interfaces.
// these interfaces can then provide implementations for previously declared
// functions or properties.

// implementing classes don't have to re-implement what was done in an
// interface.

interface Named {
    val name: String
}

interface Person : Named {
    val firstName: String
    val lastName: String

    override val name: String get() = "$firstName $lastName"
}

class Employee(
    // implementing 'name' is not required
    override val firstName: String,
    override val lastName: String,
    val position: String
) : Person


// resolving method name conflicts when implementing multiple interfaces

interface A {
    fun foo() {
        print("A")
    }
}

interface B {
    fun foo() {
        print("B")
    }
}

// If you have multiple interfaces that provide the same function name, use
// `super<InterfaceName>` to distinguish between interface implementations.
class C : A, B {
    override fun foo() {
        super<A>.foo()
        super<B>.foo()
    }
}