package com.opao.pp_api.common.constants;
import com.opao.pp_api.common.constants.ValidationRangeConstants;

public final class ValidationRegexConstants {
    private ValidationRegexConstants() {} // Prevent instantiation

    public static final String EMAIL_REGEX = "^([_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,}))$";
    
    public static final String FULL_NAME_REGEX = "^(?!.*\\s{2,})[A-Za-z ]{7,50}(?<! )$";
    
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[\\.@$!%*?&])[A-Za-z\\.\\d@$!%*?&]{8,32}$";
    
    public static final String PHONE_NUMBER_REGEX = "^\\d{10}$";
    
    public static final String USERNAME_REGEX = "^[A-Za-z][A-Za-z0-9_]{,24}$";

    public static final String JURISDICTION_REGEX = "^[A-Za-z0-9]{" + ValidationRangeConstants.JURISDICTION_MIN_LENGTH + "," + ValidationRangeConstants.JURISDICTION_MAX_LENGTH + "}$";

    public static final String PARCEL_ADDRESS_REGEX = "^[A-Za-z0-9\\-_]{" + ValidationRangeConstants.PARCEL_ADDRESS_MIN_LENGTH + "," + ValidationRangeConstants.PARCEL_ADDRESS_MAX_LENGTH + "}$";


}
