
import java.util.ArrayList;
import java.util.Scanner;

public class StudentSystem {

    private static final String ADD_STUDENT = "1";
    private static final String DELETE_STUDENT = "2";
    private static final String UPDATE_STUDENT = "3";
    private static final String QUERY_STUDENT = "4";
    private static final String EXIT = "5";


    public static void startStudentSystem() {
        ArrayList<Student> list = new ArrayList<>();
        loop:
        while (true) {
            System.out.println("----------Student Menu----------");
            System.out.println("1.add student");
            System.out.println("2.delete student");
            System.out.println("3.change student");
            System.out.println("4.check student");
            System.out.println("5.exit");
            System.out.println("Choose your option: ");
            Scanner sc = new Scanner(System.in);
            String choose = sc.next();
            switch (choose) {
                case ADD_STUDENT -> addStudent(list);
                case DELETE_STUDENT -> deleteStudent(list);
                case UPDATE_STUDENT -> updateStudent(list);
                case QUERY_STUDENT -> queryStudent(list);
                case EXIT -> {
                    System.out.println("exit student");
                    //break loop;
                    System.exit(0);
                }

                default -> System.out.println("error option");
            }
        }
    }

    //添加学生
    public static void addStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        String id = null;
        while (true) {
            System.out.println("Enter student id");
            id = sc.next();
            boolean flag = contain(list, id);
            if (flag){
                System.out.println("Current id has be taken, please try other");
            }else {
                break;
            }
        }

        System.out.println("Enter student name");
        String name = sc.next();

        System.out.println("Enter student age");
        int age = sc.nextInt();

        System.out.println("Enter student address");
        String address = sc.next();

        Student stu = new Student();
        stu.setId(id);
        stu.setName(name);
        stu.setAge(age);
        stu.setAddress(address);

        list.add(stu);
        System.out.println("Student added successfully!");
    }

    //删除学生
    public static void deleteStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter student id you want delete");
        String id = sc.next();
        boolean flag = contain(list, id);
        if (flag){
            int index = getIndex(list, id);
            list.remove(index);
            System.out.println("Student deleted successfully!");
        }else {
            System.out.println("Id not exist");
        }

    }

    //修改学生
    public static void updateStudent(ArrayList<Student> list) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter student id you want update");
        String id = sc.next();

        int index = getIndex(list,id);

        if (index == -1){
            System.out.println("current id " + id + " not exist, try again later.");
            return;
        }

        Student stu = list.get(index);
        System.out.println("Enter new name");
        String newName = sc.next();
        stu.setName(newName);

        System.out.println("Enter new age");
        int newAge = sc.nextInt();
        stu.setAge(newAge);

        System.out.println("Enter new address");
        String newAddress = sc.next();
        stu.setAddress(newAddress);


        System.out.println("student information updated successfully!");
    }

    //查询学生
    public static void queryStudent(ArrayList<Student> list) {
        if (list.size() == 0) {
            System.out.println("list is empty");
            return;
        }

        System.out.println("ID\t\tNAME\tAGE\tADDRESS");
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            System.out.println(stu.getId() + "\t" + stu.getName() + "\t" + stu.getAge() + "\t" + stu.getAddress());
        }
    }

    public static boolean contain(ArrayList<Student> list, String id) {
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            String uid = stu.getId();
            if (uid.equals(id)){
                return true;
            }
        }
        return false;
    }

    public static int getIndex(ArrayList<Student> list, String id){
        for (int i = 0; i < list.size(); i++) {
            Student stu = list.get(i);
            String uid = stu.getId();
            if (uid.equals(id)){
                return i;
            }

        }
        return -1;
    }

}
