package me.bbfh.webapp;

import me.bbfh.webapp.model.Resume;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Objects;

public class MainReflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Resume resume = new Resume();
        Method method = resume.getClass().getDeclaredMethod("toString");
        String result = (String) method.invoke(resume);

        System.out.printf("Got: `%s`, is it correct? `%b`", result, Objects.equals(result, resume.toString()));
    }
}
