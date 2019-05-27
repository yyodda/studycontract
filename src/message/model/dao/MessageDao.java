package message.model.dao;

import common.singleton.JDBCTemplate;
import message.model.vo.Message;
import message.model.vo.MessageInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MessageDao {

    public ArrayList<Message> getMyMessages(Connection connection, String sendId, String receiveId) throws SQLException {
        ArrayList<Message> messageArrayList = new ArrayList<>();
        String sql = "select * from (select * from message order by message_date) where (send_id = ? and receive_id = ?)or(send_id = ? and receive_id = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,sendId);
        preparedStatement.setString(2,receiveId);
        preparedStatement.setString(3,receiveId);
        preparedStatement.setString(4,sendId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            messageArrayList.add(new Message(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getDate(5),
                    resultSet.getInt(6)
            ));
        }
        JDBCTemplate.close(resultSet);
        JDBCTemplate.close(preparedStatement);
        return messageArrayList;
    }

    public int updateReadStatus(Connection connection, String sendId, String receiveId) throws SQLException {
        int result = 0;
        String sql = "update MESSAGE set MESSAGE_STATUS = 1 where SEND_ID = ? and RECEIVE_ID = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,receiveId);
        preparedStatement.setString(2,sendId);
        result = preparedStatement.executeUpdate();
        JDBCTemplate.close(preparedStatement);
        return result;
    }

    public ArrayList<MessageInfo> getMessageInfo(Connection connection, String sendId) throws SQLException {
        ArrayList<MessageInfo> messageInfoArrayList = new ArrayList<>();
        String sql = "select * from message_info,member where (receive_id = member_id and send_id = ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,sendId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            messageInfoArrayList.add(new MessageInfo(
                    resultSet.getInt(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString("member_name"),
                    resultSet.getString(4),
                    resultSet.getInt(5),
                    resultSet.getDate(6),
                    resultSet.getInt(7)
                    ));
        }
        JDBCTemplate.close(resultSet);
        JDBCTemplate.close(preparedStatement);
        return messageInfoArrayList;
    }
    public int insertMessage(Connection connection, String sendId, String receiveId, String messageContent) throws SQLException {
        int result = 0;
        String sql = "insert into MESSAGE values (SEQ_MESSAGE_NO.nextval,?,?,?,sysdate,0)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,sendId);
        preparedStatement.setString(2,receiveId);
        preparedStatement.setString(3,messageContent);
        result = preparedStatement.executeUpdate();
        JDBCTemplate.close(preparedStatement);
        return result;
    }

    public int updateMessageInfo(Connection connection, String sendId, String receiveId) throws SQLException {
        int result = 0;
        MessageInfo messageInfo = new MessageInfo();
        String selectSql =    "select * from message join member on(message.receive_id = member.member_id)" +
                "where (send_id=? and receive_id=?) or (send_id=? and receive_id=?)" +
                "order by message_date desc";//주고받은 메세지중 가장 최근 메세지를 가져오는 쿼리


        String countReadMessage1 = "select count(*) as cnt from MESSAGE where (send_id=? and receive_id=?)and MESSAGE_STATUS = 0";
        PreparedStatement preparedStatement = connection.prepareStatement(countReadMessage1);
        preparedStatement.setString(1,receiveId);
        preparedStatement.setString(2,sendId);
        ResultSet countResultSet1 = preparedStatement.executeQuery();
        int readCount1 = 0;
        if(countResultSet1.next()){
            readCount1 = countResultSet1.getInt(1);
        }

        String countReadMessage2 = "select count(*) as cnt from MESSAGE where (send_id=? and receive_id=?)and MESSAGE_STATUS = 0";
        preparedStatement = connection.prepareStatement(countReadMessage2);
        preparedStatement.setString(1,sendId);
        preparedStatement.setString(2,receiveId);
        ResultSet countResultSet2 = preparedStatement.executeQuery();
        int readCount2 = 0;
        if(countResultSet2.next()){
            readCount2 = countResultSet2.getInt(1);
        }



        System.out.println(readCount1);
        System.out.println(readCount2);
        preparedStatement = connection.prepareStatement(selectSql);
        preparedStatement.setString(1,sendId);
        preparedStatement.setString(2,receiveId);
        preparedStatement.setString(3,receiveId);
        preparedStatement.setString(4,sendId);
        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            messageInfo = new MessageInfo(
                    resultSet.getInt("message_no"),
                    resultSet.getString("send_id"),
                    resultSet.getString("receive_id"),
                    resultSet.getString("member_name"),
                    resultSet.getString("message_content"),
                    0,
                    resultSet.getDate("message_date"),
                    resultSet.getInt("message_status")
            );
        }
        String mergeSql = "MERGE into message_Info mi Using dual on(mi.status=0 and mi.send_id=? and mi.receive_id=?) when matched then update set mi.recent_message_content = ?, mi.recent_message_count=?, mi.recent_message_date=sysdate when not matched then insert values (seq_message_info_no.nextval,?,?,?,?,sysdate,0)";
        preparedStatement = connection.prepareStatement(mergeSql);
        preparedStatement.setString(1,messageInfo.getSendId());
        preparedStatement.setString(2,messageInfo.getReceiveId());
        preparedStatement.setString(3,messageInfo.getRecentMessageContent());
        preparedStatement.setInt(4,readCount2);
        preparedStatement.setString(5,messageInfo.getSendId());
        preparedStatement.setString(6,messageInfo.getReceiveId());
        preparedStatement.setString(7,messageInfo.getRecentMessageContent());
        preparedStatement.setInt(8,readCount2);
        result += preparedStatement.executeUpdate();
        preparedStatement = connection.prepareStatement(mergeSql);
        preparedStatement.setString(1,messageInfo.getReceiveId());
        preparedStatement.setString(2,messageInfo.getSendId());
        preparedStatement.setString(3,messageInfo.getRecentMessageContent());
        preparedStatement.setInt(4,readCount1);
        preparedStatement.setString(5,messageInfo.getReceiveId());
        preparedStatement.setString(6,messageInfo.getSendId());
        preparedStatement.setString(7,messageInfo.getRecentMessageContent());
        preparedStatement.setInt(8,readCount1);
        result += preparedStatement.executeUpdate();
        return result;
    }

}
