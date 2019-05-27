package message.model.vo;

import java.util.Date;

public class Message {
    private int messageNo;
    private String sendId;
    private String receiveId;
    private String messageContent;
    private Date messageDate;
    private int messageStatus;

    public Message(int messageNo, String sendId, String receiveId, String messageContent, Date messageDate, int messageStatus) {
        this.messageNo = messageNo;
        this.sendId = sendId;
        this.receiveId = receiveId;
        this.messageContent = messageContent;
        this.messageDate = messageDate;
        this.messageStatus = messageStatus;
    }

    public int getMessageNo() {
        return messageNo;
    }

    public void setMessageNo(int messageNo) {
        this.messageNo = messageNo;
    }

    public String getSendId() {
        return sendId;
    }

    public void setSendId(String sendId) {
        this.sendId = sendId;
    }

    public String getReceiveId() {
        return receiveId;
    }

    public void setReceiveId(String receiveId) {
        this.receiveId = receiveId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Date getMessageDate() {
        return messageDate;
    }

    public void setMessageDate(Date messageDate) {
        this.messageDate = messageDate;
    }

    public int getMessageStatus() {
        return messageStatus;
    }

    public void setMessageStatus(int messageStatus) {
        this.messageStatus = messageStatus;
    }

    @Override
    public String toString() {
        return "Message{" +
                "messageNo=" + messageNo +
                ", sendId='" + sendId + '\'' +
                ", receiveId='" + receiveId + '\'' +
                ", messageContent='" + messageContent + '\'' +
                ", messageDate=" + messageDate +
                ", messageStatus=" + messageStatus +
                '}';
    }
}
