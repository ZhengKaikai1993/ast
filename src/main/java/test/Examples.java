package test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import parse.DefaultASTVisitor;

public class Examples
{


    public static final Logger logger = LoggerFactory.getLogger(DefaultASTVisitor.class);

    public void test()
    {
        TestClass testClass = new TestClass(1000L, 3.0F);

        int b = (int) testClass.aFloat;

        long d = (int) testClass.aLong;
    }
}
