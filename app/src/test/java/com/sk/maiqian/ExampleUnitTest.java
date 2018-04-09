package com.sk.maiqian;

import com.google.gson.Gson;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }
    @Test
    public void asdfsf() throws Exception {
        TestA a=new TestA();
        a.setA("1");
        a.setB("2");
        a.setC("3");
        System.out.println(new Gson().toJson(a));

        double resultJifen = Double.parseDouble("012");
        System.out.println(resultJifen);
        System.out.println(Double.parseDouble("02"));
        System.out.println(Double.parseDouble("0"));
        System.out.println(Double.parseDouble("00"));
        String jifen="01";
        if(jifen.indexOf("0")==0){
            jifen=jifen.substring(1,jifen.length());
        }
        System.out.println(jifen);
    }
    public class TestA{
        private String a;
        private String b;
        private String c;

        public String getA() {
            return a;
        }

        public void setA(String a) {
            this.a = a;
        }

        public String getB() {
            return b;
        }

        public void setB(String b) {
            this.b = b;
        }

        public String getC() {
            return c;
        }

        public void setC(String c) {
            this.c = c;
        }
    }
}