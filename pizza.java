public class pizza {
    public static void main(String[] args) {
       
        String pizza = bakePizza("flat bread");
        System.out.println(pizza);

        pizza = bakePizza("flat bread", "mozerella");
        System.out.println(pizza);

        pizza = bakePizza("flat bread", "mozerella", "pepperoni");
        System.out.println(pizza);
    }

    static String bakePizza(String bread){
        return bread + " pizza";
    }

    static String bakePizza(String bread, String cheese){
        return cheese +" "+ bread + " " + "pizza";
    }

    static String bakePizza(String bread, String cheese, String toppings){
        return toppings + " " + cheese + " " + bread + " pizza";
    }
}
