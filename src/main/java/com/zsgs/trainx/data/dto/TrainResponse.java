package com.zsgs.trainx.data.dto;

import java.util.List;

public class TrainResponse {
    private boolean success;
    private String message;
    private List<Train> trains;

    public TrainResponse() {}

    public TrainResponse(boolean success, String message, List<Train> trains) {
        this.success = success;
        this.message = message;
        this.trains = trains;
    }

    public boolean isSuccess() { return success; }
    public void setSuccess(boolean success) { this.success = success; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public List<Train> getTrains() { return trains; }
    public void setTrains(List<Train> trains) { this.trains = trains; }
}
