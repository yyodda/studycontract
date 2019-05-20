package member.model.vo;

import java.sql.Date;
import java.util.Calendar;

public class Member {
    private String memberId;
    private String memberPw;
    private String memberName;
    private String birthDate;
    private String gender;
    private String phone;
    private String email;
    private String address;
    private int grade;
    private Date enrollDate;
    private Date recentLogin;
    private int loginCnt;
    private int friendCnt;
    private int status;

    public Member() {
    }

    public Member(String memberId, String memberPw, String memberName, String birthDate, String gender, String phone, String email, String address, int grade, Date enrollDate, Date recentLogin, int loginCnt, int friendCnt, int status) {
        this.memberId = memberId;
        this.memberPw = memberPw;
        this.memberName = memberName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.grade = grade;
        this.enrollDate = enrollDate;
        this.recentLogin = recentLogin;
        this.loginCnt = loginCnt;
        this.friendCnt = friendCnt;
        this.status = status;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberPw() {
        return memberPw;
    }

    public void setMemberPw(String memberPw) {
        this.memberPw = memberPw;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public Date getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(Date enrollDate) {
        this.enrollDate = enrollDate;
    }

    public Date getRecentLogin() {
        return recentLogin;
    }

    public void setRecentLogin(Date recentLogin) {
        this.recentLogin = recentLogin;
    }

    public int getLoginCnt() {
        return loginCnt;
    }

    public void setLoginCnt(int loginCnt) {
        this.loginCnt = loginCnt;
    }

    public int getFriendCnt() {
        return friendCnt;
    }

    public void setFriendCnt(int friendCnt) {
        this.friendCnt = friendCnt;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
    public int getAge(){
        return Calendar.getInstance().get(Calendar.YEAR) -Integer.parseInt("19"+birthDate.substring(0,2))+1;
    }

    @Override
    public String toString() {
        return "Member{" +
                "memberId='" + memberId + '\'' +
                ", memberPw='" + memberPw + '\'' +
                ", memberName='" + memberName + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", grade=" + grade +
                ", enrollDate=" + enrollDate +
                ", recentLogin=" + recentLogin +
                ", loginCnt=" + loginCnt +
                ", friendCnt=" + friendCnt +
                ", status=" + status +
                '}';
    }
}
