public class Encapsulation {
    public static void main (String [] args){
        //Encapsulation: hiding data by declaring variable as private and providing controlled access through methods

        Student3 student = new Student3();

        student.setName("Larry");
        student.setAge(21);

        System.out.println(student.getName());
        System.out.println(student.getAge());
    }
}
