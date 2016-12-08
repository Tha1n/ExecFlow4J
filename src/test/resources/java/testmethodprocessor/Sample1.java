package java.testmethodprocessor;

/**
 * TODO --> Adding Comment
 * Created by alxqu on 16/11/2016.
 */
public class Sample1{

    public void test1() {

    }

    public int test2() {
        test3();

        return 0;
    }

    private int test3() {
        String s = "Test3";

        System.out.println(s);

        System.out.println(test4(6));

        return 0;
    }

    private int test4(int value) {
        int t = value + 2;

        return t * 2;
    }
}
