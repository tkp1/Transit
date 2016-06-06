package model.Exceptions;

public class NoDataReceivedException extends Exception {
    public NoDataReceivedException() {
        super("No Data Received From Request");
    }
}
