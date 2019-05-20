package member.model.dao;

import common.singleton.JDBCTemplate;
import jdk.nashorn.internal.scripts.JD;
import member.model.vo.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberDao {
    public Member SearchToIdPw(Connection connection, String id, String pw) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Member member = null;
        //language=Oracle
        String sql = "select * from member where MEMBER_ID = ? and MEMBER_PW = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,id);
        preparedStatement.setString(2,pw);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            member = new Member(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getInt(9),
                    resultSet.getDate(10),
                    resultSet.getDate(11),
                    resultSet.getInt(12),
                    resultSet.getInt(13),
                    resultSet.getInt(14)
            );
        }
        JDBCTemplate.close(resultSet);
        JDBCTemplate.close(preparedStatement);
        return member;
    }

    public Member SearchToId(Connection connection, String memberId) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Member member = null;
        //language=Oracle
        String sql = "select * from member where MEMBER_ID = ?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,memberId);
        resultSet = preparedStatement.executeQuery();
        if(resultSet.next()){
            member = new Member(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getInt(9),
                    resultSet.getDate(10),
                    resultSet.getDate(11),
                    resultSet.getInt(12),
                    resultSet.getInt(13),
                    resultSet.getInt(14)
            );
        }
        JDBCTemplate.close(resultSet);
        JDBCTemplate.close(preparedStatement);
        return member;
    }

    public int insertMember(Connection connection,Member member) throws SQLException {
        PreparedStatement preparedStatement = null;
        int result = 0;
        String sql = "insert into MEMBER (MEMBER_ID, MEMBER_PW, MEMBER_NAME, BIRTH_DATE, GENDER, PHONE, EMAIL, ADDRESS) values (?,?,?,?,?,?,?,?)";

        preparedStatement = connection.prepareStatement(sql);

        preparedStatement.setString(1,member.getMemberId());
        preparedStatement.setString(2,member.getMemberPw());
        preparedStatement.setString(3,member.getMemberName());
        preparedStatement.setString(4,member.getBirthDate());
        preparedStatement.setString(5,member.getGender());
        preparedStatement.setString(6,member.getPhone());
        preparedStatement.setString(7,member.getEmail());
        preparedStatement.setString(8,member.getAddress());

        result = preparedStatement.executeUpdate();

        JDBCTemplate.close(preparedStatement);

        return result;
    }

    public int increaseLoginCnt(Connection connection, Member member) throws SQLException {
        PreparedStatement preparedStatement = null;
        int result = 0;
        String sql = "update MEMBER set LOGIN_CNT=LOGIN_CNT+1, RECENT_LOGIN = sysdate where MEMBER_ID=?";
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, member.getMemberId());
        result = preparedStatement.executeUpdate();

        JDBCTemplate.close(preparedStatement);
        return result;
    }

    public ArrayList<Member> getFriends(Connection connection, String memberId) throws SQLException {
        ArrayList<Member> friends = new ArrayList<>();
        String sql = "select * from member where member_id in (select friend_id from friend where my_id=? and status = 0)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1,memberId);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            friends.add(
                    new Member(
                            resultSet.getString(1),
                            resultSet.getString(2),
                            resultSet.getString(3),
                            resultSet.getString(4),
                            resultSet.getString(5),
                            resultSet.getString(6),
                            resultSet.getString(7),
                            resultSet.getString(8),
                            resultSet.getInt(9),
                            resultSet.getDate(10),
                            resultSet.getDate(11),
                            resultSet.getInt(12),
                            resultSet.getInt(13),
                            resultSet.getInt(14)
                            ));
        }
        JDBCTemplate.close(resultSet);
        JDBCTemplate.close(preparedStatement);
        return friends;
    }
}
