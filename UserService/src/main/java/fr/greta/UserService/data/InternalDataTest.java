package fr.greta.UserService.data;

public class InternalDataTest {
    private  static int internalUserNumber = 1000;


    public static void setInterUserNumber(int internalUserNumber){
        InternalDataTest.internalUserNumber = internalUserNumber;
    }

    public static  int getInterUserNumber(){
        return  internalUserNumber;
    }
}
