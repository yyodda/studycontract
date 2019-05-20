package member.model.service;

import common.singleton.JDBCTemplate;
import member.model.dao.MemberDao;
import member.model.vo.Member;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberService {
    public Member loginMember(String id, String pw) throws SQLException {
        Connection connection = JDBCTemplate.getConnection();
        Member member = new MemberDao().SearchToIdPw(connection,id,pw);
        if(member != null){
            int result = 0;
            result = new MemberDao().increaseLoginCnt(connection, member);
            if(result>0){
                JDBCTemplate.commit(connection);
            }
            else{
                JDBCTemplate.rollback(connection);
            }
        }
        JDBCTemplate.close(connection);
        return member;
    }

    public Member searchToId(String memberId) throws SQLException {
        Connection connection = JDBCTemplate.getConnection();
        Member member = new MemberDao().SearchToId(connection,memberId);
        JDBCTemplate.close(connection);
        return member;
    }

    public int insertMember(Member member) throws SQLException {
        Connection connection = JDBCTemplate.getConnection();
        int result = new MemberDao().insertMember(connection, member);
        if(result >0){
            JDBCTemplate.commit(connection);
        }
        else{
            JDBCTemplate.rollback(connection);
        }
        JDBCTemplate.close(connection);
        return result;
    }

    public ArrayList<Member> getFriends(String memberId) throws SQLException {
        Connection connection = JDBCTemplate.getConnection();
        ArrayList<Member> friends = new MemberDao().getFriends(connection, memberId);
        JDBCTemplate.close(connection);
        return friends;
    }
}
