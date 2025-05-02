class LegacyAPI{
    @Deprecated
    void oldFeature(){
        System.out.println("This is the old feature. It is deprecated.");
    }

    void newFeature(){
        System.out.println("This is a new feature.");
    }
}

public class LegacyMain{
    public static void main(String[]args){
        LegacyAPI api = new LegacyAPI();
        api.oldFeature();
        api.newFeature();
    }
}