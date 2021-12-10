package in.co.ad.customer.portal.backend.util;

public enum ErrorMessage {

    INVALID_INPUT_DATA("CPB001","Invalid input data."),
    NO_DATA_FOUND("CPB002","No data found."),
    SYSTEM_ERROR("SYS001","System Exception occurred please contact support team.");

    private final String errorMessage;
    private final String errorCode;

    ErrorMessage(String errorCode, String errorMessage) {
        this.errorMessage = errorMessage;
        this.errorCode = errorCode;
    }

    public String getDisplayMessage() {
        return errorCode +": " + errorMessage;
    }


}
