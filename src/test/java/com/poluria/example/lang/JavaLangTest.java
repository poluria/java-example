package com.poluria.example.lang;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

public class JavaLangTest {

    @Test
    public void test_instanceof() {
        Class obj = new Class();

        assertThat(obj instanceof Class, is(true));
        assertThat(obj instanceof AbstractClass, is(true));
        assertThat(obj instanceof Interface, is(true));
    }

    @Test
    public void isAssignableFrom() {
        assertThat(Class.class.isAssignableFrom(Class.class), is(true));
        assertThat(Class.class.isAssignableFrom(AbstractClass.class), is(false));
        assertThat(Class.class.isAssignableFrom(Interface.class), is(false));

        assertThat(Class.class.isAssignableFrom(Class.class), is(true));
        assertThat(AbstractClass.class.isAssignableFrom(Class.class), is(true));
        assertThat(Interface.class.isAssignableFrom(Class.class), is(true));
    }

    @Test
    public void test_getClass() {
        Class obj = new Class();
        assertThat(obj.getClass().getSimpleName(), is("Class"));

        AbstractClass obj2 = obj;
        assertThat(obj2.getClass().getSimpleName(), is("Class"));

        Interface obj3 = obj;
        assertThat(obj3.getClass().getSimpleName(), is("Class"));
    }

    @Test
    public void arrayHashCode() {
        byte[] arr1 = "test".getBytes();
        byte[] arr2 = "test".getBytes();

        assertThat(arr1.hashCode(), not(arr2.hashCode()));

        assertThat(Arrays.hashCode(arr1), is(Arrays.hashCode(arr2)));
    }

    @Test
    public void arrayEquals() {
        byte[] arr1 = "test".getBytes();
        byte[] arr2 = "test".getBytes();

        assertThat(arr1.equals(arr2), is(false));
        assertThat(arr2.equals(arr1), is(false));

        assertThat(Arrays.equals(arr1, arr2), is(true));
    }

    private interface Interface {
    }

    private abstract class AbstractClass implements Interface {
    }

    private class Class extends AbstractClass {
    }
}
