public class staticKeywords {
    public static void main(String[] args) {
        //static - make a variable or222222 method belong to the class rather then any specific object. commonly used for utility method and shared resources

        //proceed to friend


        Friend friend1 = new Friend("Collins");
        Friend friend2 = new Friend("Burundi");
        Friend friend3 = new Friend("Femi");
        Friend friend4 = new Friend("Salim");


        System.out.println(friend1.numOfFriends);
        System.out.println(friend2.numOfFriends);
        System.out.println(friend3.numOfFriends);
        System.out.println(friend4.numOfFriends);

        //accessing the static variable by the class name

        System.out.println(Friend.numOfFriends);


        Friend.showFriends();
        Math.round(3.99); //round is an utility method from class "Math".
    }
}
