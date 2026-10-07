package com.opao.pp_api.common.constants;

public final class ValidationRegexConstants {
    private ValidationRegexConstants() {} // Prevent instantiation

    // 🟢 Pure format validation: matches standard email character structure
    public static final String EMAIL_REGEX = "^([_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,6}))$";
    
    // 🟢 Pure format validation: blocks double or trailing spaces in regular text names
    public static final String FULL_NAME_REGEX = "^(?!.*\\s{2,})[A-Za-z ]+(?<! )$";
    
    // 🟢 Pure format validation: enforces composition (lowercase, uppercase, digit, special character)
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\.@$!%*?&])[A-Za-z\\.\\d@$!%*?&]+$";
    
    // 🟢 Pure format validation: matches a continuous digit character stream
    public static final String PHONE_NUMBER_REGEX = "^\\d+$";
    
    // 🟢 Pure format validation: requires starting with an alphabet char followed by alphanumerics/underscores
    public static final String USERNAME_REGEX = "^[A-Za-z][A-Za-z0-9_]*$";

    // 🟢 Pure format validation: restricts input to flat alphanumeric values
    public static final String JURISDICTION_REGEX = "^[A-Za-z0-9]+$";

    // 🟢 Pure format validation: tracks alphanumeric structures with dashes and underscores
    public static final String PARCEL_ADDRESS_REGEX = "^[A-Za-z0-9\\-_]+$";
}
