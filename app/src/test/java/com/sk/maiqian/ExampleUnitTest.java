package com.sk.maiqian;

import com.google.gson.Gson;

import org.junit.Test;

import java.util.Date;

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
    public void asdfasd() throws Exception {
        int flag=3;
        for (int i = 1; i <= flag; i++) {
            flag++;
            System.out.println(i+"==="+flag);
            if(i==20){
                break;
            }

        }
    }
    @Test
    public void asdf() throws Exception {
        System.out.println(new Date().getYear());
        System.out.println(new Date().getMonth());
    }
    @Test
    public void asdfasf() throws Exception {
        String str="  ab  cd   fser tyjg ";
        String[] split = str.split(" ");
        for (int i = 0; i <split.length ; i++) {
            System.out.println("="+split[i]+"=");
        }
        System.out.println("--------------------------");
        for (int i = 0; i <split.length ; i++) {
            if(split[i]!=null&&split[i].replace(" ","").length()!=0){
                System.out.println("="+split[i]+"=");
            }
        }
    }
    @Test
    public void sadf() throws Exception {
         String json="{\n" +
                 "\t\"code\": 20000,\n" +
                 "\t\"content\": {\n" +
                 "\t\t\"birthType\": 0,\n" +
                 "\t\t\"birthday\": 339206400000,\n" +
                 "\t\t\"cstId\": 200000000118,\n" +
                 "\t\t\"cstName\": \"陈林\",\n" +
                 "\t\t\"cstType\": 1,\n" +
                 "\t\t\"fileTime\": 1404662400000,\n" +
                 "\t\t\"marriage\": 2,\n" +
                 "\t\t\"memType\": 0,\n" +
                 "\t\t\"rakName\": \"青铜会员\",\n" +
                 "\t\t\"rcvMsg\": true,\n" +
                 "\t\t\"realName\": \"陈林\",\n" +
                 "\t\t\"regTime\": 1404662400000,\n" +
                 "\t\t\"sex\": 1,\n" +
                 "\t\t\"totalAmount\": 0,\n" +
                 "\t\t\"totalProfit\": 0,\n" +
                 "\t\t\"verify\": 0\n" +
                 "\t}\n" +
                 "}";
        TestB testB = new Gson().fromJson(json, TestB.class);
        System.out.println(new Gson().toJson(testB));
        if (testB.getContent() != null) {
            System.out.println("=="+testB.getContent().getCstName());
            System.out.println("=="+testB.getContent().getNickName());
            if (testB.getContent().getNickName() != null) {
                System.out.println("fanhui");
            }else{
                System.out.println("没返回");
            }
        }
    }

    private static class TestB{

        /**
         * code : 20000
         * content : {"birthType":0,"birthday":339206400000,"cstId":200000000118,"cstName":"陈林","cstType":1,"fileTime":1404662400000,"marriage":2,"memType":0,"rakName":"青铜会员","rcvMsg":true,"realName":"陈林","regTime":1404662400000,"sex":1,"totalAmount":0,"totalProfit":0,"verify":0}
         */

        private int code;
        private ContentBean content;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public ContentBean getContent() {
            return content;
        }

        public void setContent(ContentBean content) {
            this.content = content;
        }

        public static class ContentBean {
            /**
             * birthType : 0
             * birthday : 339206400000
             * cstId : 200000000118
             * cstName : 陈林
             * cstType : 1
             * fileTime : 1404662400000
             * marriage : 2
             * memType : 0
             * rakName : 青铜会员
             * rcvMsg : true
             * realName : 陈林
             * regTime : 1404662400000
             * sex : 1
             * totalAmount : 0
             * totalProfit : 0
             * verify : 0
             */

            private int birthType;
            private long birthday;
            private long cstId;
            private String cstName;
            private String NickName;
            private int cstType;
            private long fileTime;
            private int marriage;
            private int memType;
            private String rakName;
            private boolean rcvMsg;
            private String realName;
            private long regTime;
            private int sex;
            private int totalAmount;
            private int totalProfit;
            private int verify;

            public int getBirthType() {
                return birthType;
            }

            public String getNickName() {
                return NickName;
            }

            public void setNickName(String nickName) {
                NickName = nickName;
            }

            public void setBirthType(int birthType) {
                this.birthType = birthType;
            }

            public long getBirthday() {
                return birthday;
            }

            public void setBirthday(long birthday) {
                this.birthday = birthday;
            }

            public long getCstId() {
                return cstId;
            }

            public void setCstId(long cstId) {
                this.cstId = cstId;
            }

            public String getCstName() {
                return cstName;
            }

            public void setCstName(String cstName) {
                this.cstName = cstName;
            }

            public int getCstType() {
                return cstType;
            }

            public void setCstType(int cstType) {
                this.cstType = cstType;
            }

            public long getFileTime() {
                return fileTime;
            }

            public void setFileTime(long fileTime) {
                this.fileTime = fileTime;
            }

            public int getMarriage() {
                return marriage;
            }

            public void setMarriage(int marriage) {
                this.marriage = marriage;
            }

            public int getMemType() {
                return memType;
            }

            public void setMemType(int memType) {
                this.memType = memType;
            }

            public String getRakName() {
                return rakName;
            }

            public void setRakName(String rakName) {
                this.rakName = rakName;
            }

            public boolean isRcvMsg() {
                return rcvMsg;
            }

            public void setRcvMsg(boolean rcvMsg) {
                this.rcvMsg = rcvMsg;
            }

            public String getRealName() {
                return realName;
            }

            public void setRealName(String realName) {
                this.realName = realName;
            }

            public long getRegTime() {
                return regTime;
            }

            public void setRegTime(long regTime) {
                this.regTime = regTime;
            }

            public int getSex() {
                return sex;
            }

            public void setSex(int sex) {
                this.sex = sex;
            }

            public int getTotalAmount() {
                return totalAmount;
            }

            public void setTotalAmount(int totalAmount) {
                this.totalAmount = totalAmount;
            }

            public int getTotalProfit() {
                return totalProfit;
            }

            public void setTotalProfit(int totalProfit) {
                this.totalProfit = totalProfit;
            }

            public int getVerify() {
                return verify;
            }

            public void setVerify(int verify) {
                this.verify = verify;
            }
        }
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