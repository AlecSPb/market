package constants;

public enum PaymentMethod {
    ONLINE("Card online"),
    OFFLINE("Card offline"),
    OFFLINE_CASH("Cash");

    private final String description;

    private PaymentMethod(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }

    public static String getString(PaymentMethod method){
        if(method == null)
            return null;
        else
            return method.toString();
    }

    public static PaymentMethod valueOfOrNull(String enumValue){
        if(enumValue == null)
            return null;
        else
            return PaymentMethod.valueOf(enumValue);
    }
}