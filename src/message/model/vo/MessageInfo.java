package message.model.vo;

import java.sql.Date;

public class MessageInfo {
    private int messageInfoNo;
    private String sendId;
    private String receiveId;
    private String receiveName;
    private String recentMessageContent;
    private int recentMessageCount;
    private Date recentMessageDate;
    private int status;
    public MessageInfo(int messageInfoNo, String sendId, String receiveId, String receiveName, String recentMessageContent, int recentMessageCount, Date recentMessageDate, int status) {
        this.messageInfoNo = messageInfoNo;
        this.sendId = sendId;
        this.receiveId = receiveId;
        this.receiveName = receiveName;
        this.recentMessageContent = recentMessageContent;
        this.recentMessageCount = recentMessageCount;
        this.recentMessageDate = recentMessageDate;
        this.status = status;
    }

    public MessageInfo() {

    }

    public int getMessageInfoNo() {
        return messageInfoNo;
    }

    public void setMessageInfoNo(int messageInfoNo) {
        this.messageInfoNo = messageInfoNo;
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

    public String getReceiveName() {
        return receiveName;
    }

    public void setReceiveName(String receiveName) {
        this.receiveName = receiveName;
    }

    public String getRecentMessageContent() {
        return recentMessageContent;
    }

    public void setRecentMessageContent(String recentMessageContent) {
        this.recentMessageContent = recentMessageContent;
    }

    public int getRecentMessageCount() {
        return recentMessageCount;
    }

    public void setRecentMessageCount(int recentMessageCount) {
        this.recentMessageCount = recentMessageCount;
    }

    public Date getRecentMessageDate() {
        return recentMessageDate;
    }

    public void setRecentMessageDate(Date recentMessageDate) {
        this.recentMessageDate = recentMessageDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
