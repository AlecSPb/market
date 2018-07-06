package constants;

public enum PaymentMethod {
    ONLINE("Оплата картой онлайн"),
    OFFLINE("Оплата картой при получении"),
    OFFLINE_CASH("Оплата картой при получении");

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