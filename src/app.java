
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class app {
    static ArrayList<user> list = new ArrayList<>();

    static {
        //添加一些用户信息
        list.add(new user("zhangsan","12345678","1234567890","12345678900"));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("welcome to student management system");
            System.out.println("choose your option");
            System.out.println("1.login");
            System.out.println("2.register");
            System.out.println("3.forget password");
            System.out.println("4.exit");
            String choose = sc.next();

            switch (choose) {
                case "1" -> login(list);
                case "2" -> register(list);
                case "3" -> forgetPassword(list);
                case "4" -> {
                    System.exit(0);
                }
                default -> System.out.println("error option");
            }
        }
    }

    private static void forgetPassword(ArrayList<user> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your username");
        String username = sc.next();
        boolean flag = contain(list, username);
        if (!flag){
            System.out.println("Username " + username + " haven't register, try again after register");
            return;
        }

        System.out.println("enter personal id");
        String personalID = sc.next();
        System.out.println("Enter phone Number");
        String phoneNumber = sc.next();

        //比较用户对象的手机号码和身份证号是否相同
        //需要把用户对象通过索引获取出来
        int index = findIndex(list, username);
        user user = list.get(index);
        if (!(user.getPersonID().equalsIgnoreCase(personalID) && user.getPhoneNumber().equals(phoneNumber))){
            System.out.println("personal id or phone number is incorrect!");
            return;
        }

        String password;
        while (true) {
            System.out.println("Enter new password");
            password = sc.next();
            System.out.println("Enter again your new password");
            String newPassword = sc.next();

            if (password.equals(newPassword)){
                System.out.println("successful");
                break;
            }else {
                System.out.println("unsuccessful, try again");
                continue;
            }
        }
        user.setPassword(password);
        System.out.println("password updated!");

    }

    private static int findIndex(ArrayList<user> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            user user = list.get(i);
            if (user.getUsername().equals(username)){
                return i;
            }
        }
        return -1;
    }

    private static void login(ArrayList<user> list) {
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 3; i++) {
            System.out.println("Please enter your username");
            String username = sc.next();
            boolean flag = contain(list, username);
            if (!flag){
                System.out.println("Username " + username + " haven't register, try again after register");
                return;
            }
            System.out.println("Enter your password");
            String password = sc.next();


            while (true) {
                String rightCode = getCode();
                System.out.println("verify code: " + rightCode);
                System.out.println("Enter verify code");
                String code = sc.next();
                if (code.equalsIgnoreCase(rightCode)){
                    System.out.println("verify code successful");
                    break;
                }else {
                    System.out.println("Verify code wrong");
                }
            }

            //定义一个方法可以验证用用户名和密码是否正确
            //封装思想
            //把零散的数据，封装到一个对象里面
            //以后传递数据的时候，只需要传递一个整体就可以了，不用管那些零散的数据
            user userInfo = new user(username,password,null,null);
            boolean result = checkUserInfo(list,userInfo);
            if (result){
                System.out.println("login successful");
                //创建对象调用方法，启动学生管理系统
                StudentSystem ss = new StudentSystem();
                ss.startStudentSystem();
                break;
            }else {
                System.out.println("Login failed, username or password incorrect");
                if (i == 2){
                    System.out.println("current account " + username + " blocked");
                    return;
                }else {
                    System.out.println("username or password incorrect: "+ (2 - i) + " chance left");
                }
            }
        }



    }

    private static boolean checkUserInfo(ArrayList<user> list, user userInfo) {
        //遍历集合，判断用户是否存在，如果存在登录成功吗，不存在则登录失败
        for (int i = 0; i < list.size(); i++) {
            user u = list.get(i);
            if (u.getUsername().equals(userInfo.getUsername()) && u.getPassword().equals(userInfo.getPassword())){
                return true;
            }
        }
        return false;
    }

    public static void register(ArrayList<user> list) {
        user u = new user();
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome to register page");

        while (true) {
            System.out.println("Enter your username");
            String username = sc.next();
            //先验证格式是否正确，在验证唯一
            //只能是字母加数字的组合，但是不能是数字
            //用户名长度为3~15之间
            boolean flag1 = checkUsername(username);
            if (!flag1) {
                System.out.println("current username format is error, Enter again");
                continue;
            }
            //用户名唯一
            boolean flag2 = contain(list, username);
            if (!flag2) {
                System.out.println("username update successful");
                u.setUsername(username);
                break;
            } else {
                System.out.println("username exist, enter again");
            }
        }

        //密码输入两次，两次为一致则进行注册
        while (true) {
            System.out.println("Enter your password");
            String password = sc.next();
            System.out.println("confirm your password");
            String confirmPassword = sc.next();

            if (password.equals(confirmPassword)) {
                System.out.println("password create successful");
                u.setPassword(password);
                break;
            } else {
                System.out.println("password is not match, try again");
            }
        }


        while (true) {
            System.out.println("Enter your personalID");
            String ID = sc.next();
            boolean flag = checkID(ID);
            if (flag){
                System.out.println("ID create successful");
                u.setPersonID(ID);
                break;
            }else {
                System.out.println("ID format error, try again");
            }
        }


        while (true) {
            System.out.println("Enter your phone number");
            String pn = sc.next();
            boolean flag = checkPhoneNumber(pn);
            if (flag){
                System.out.println("phone number update successful");
                u.setPhoneNumber(pn);
                break;
            }else {
                System.out.println("Phone number format error, try again");
            }
        }

        list.add(u);

        printList(list);

    }

    private static void printList(ArrayList<user> list) {
        for (int i = 0; i < list.size(); i++) {
            user user = list.get(i);
            System.out.println(user.getUsername());
            System.out.println(user.getPassword());
            System.out.println(user.getPersonID());
            System.out.println(user.getPhoneNumber());
        }
    }

    private static boolean checkPhoneNumber(String pn) {
        //长度11位
        int len = pn.length();
        if (len != 11){
            return false;
        }
        //不能以0开头
        boolean flag = pn.startsWith("0");
        if (flag){
            return false;
        }
        //必须都是数字
        for (int i = 0; i < pn.length(); i++) {
            char c = pn.charAt(i);
            if (!(c >= '0' && c <= '9')){
                return false;
            }

        }
        return true;

    }

    private static boolean checkUsername(String username) {
        int len = username.length();
        if (len < 3 || len > 15) {
            return false;
        }
        //继续校验，只能是字母加数字，但是不能是纯数字
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || (c >= '0' && c <= '9'))) {
                return false;
            }

        }
        //但是不能是纯数字
        int count = 0;
        for (int i = 0; i < username.length(); i++) {
            char c = username.charAt(i);
            if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
                count++;
                break;
            }
        }
        return count > 0;
    }

    private static boolean contain(ArrayList<user> list, String username) {
        for (int i = 0; i < list.size(); i++) {
            user user = list.get(i);
            String username1 = user.getUsername();
            if (username1.equals(username)) {
                return true;
            }
        }
        return false;

    }

    private static boolean checkID(String id) {
        //长度为10
        if (id.length() != 10) {
            return false;
        }
        //不能以0开头
        boolean flag = id.startsWith("0");
        if (flag) {
            //如果以0为开头，那么返回false
            return false;
        }

        //前9为必须是数字
        for (int i = 0; i < id.length() - 1; i++) {
            char c = id.charAt(i);
            //如果有一个字符不在0~9之间则返回false
            if (!(c >= '0' && c <= '9')) {
                return false;
            }
        }
        //最后一位可以是数字，也可以是大写X或小写x
        char endChar = id.charAt(id.length() - 1);
        if ((endChar >= '0' && endChar <= '9')|| endChar == 'x' || endChar == 'X'){
            return true;
        }else {
            return false;
        }
    }

    private static String getCode() {
        char[] arr = new char[52];
        char[] code = new char[5];
        for (int i = 0; i < arr.length; i++) {
            if (i <= 25) {
                arr[i] = (char) (97 + i);
            } else {
                arr[i] = (char) (65 + i - 26);
            }
        }

        Random r = new Random();
        int randomNumber = r.nextInt(10);

        for (int i = 0; i < 4; i++) {
            int randomIndex = r.nextInt(1, 52);
            code[i] = arr[randomIndex];
        }
        code[code.length - 1] = (char) (randomNumber + '0');

        for (int i = code.length - 1; i > 0; i--) {
            int j = r.nextInt(i + 1);
            char temp = code[i];
            code[i] = code[j];
            code[j] = temp;
        }
        StringBuilder sb = new StringBuilder();
        String result = sb.append(code).toString();

        return result;

    }
}
