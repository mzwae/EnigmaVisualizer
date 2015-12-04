package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ MachineBuilderTest.class, ReflectorTest.class, RotorTest.class })
public class AllTestsSuite {

}
