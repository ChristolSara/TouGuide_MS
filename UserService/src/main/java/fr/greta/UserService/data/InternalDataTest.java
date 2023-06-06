package fr.greta.UserService.data;

public class InternalDataTest {
    private  static int interUserNumber = 5;


    public static void setInterUserNumber(int interUserNumber){
        InternalDataTest.interUserNumber = interUserNumber;
    }

    public static  int getInterUserNumber(){
        return  interUserNumber;
    }
}
