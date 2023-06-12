package fr.greta.UserService.data;

public class InternalDataTest {
    private  static int internalUserNumber = 100;


    public static void setInterUserNumber(int internalUserNumber){
        InternalDataTest.internalUserNumber = internalUserNumber;
    }

    public static  int getInterUserNumber(){
        return  internalUserNumber;
    }
}
