package fr.greta.UserService.data;

public class InternalDataTest {
    private  static int interUserNumber = 100 ;


    public static void setInterUserNumber(int interUserNumber){
        InternalDataTest.interUserNumber = interUserNumber;
    }

    public static  int getInterUserNumber(){
        return  interUserNumber;
    }
}
