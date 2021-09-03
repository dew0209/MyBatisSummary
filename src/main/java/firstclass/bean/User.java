package firstclass.bean;

public class User {
    /**
     *   `id` INT(20) NOT NULL AUTO_INCREMENT,
     *   `user_name` VARCHAR(60) DEFAULT NULL COMMENT '用户名称',
     *   `real_name` VARCHAR(60) DEFAULT NULL COMMENT '真实名称',
     *   `sex` TINYINT(3) DEFAULT NULL COMMENT '性别',
     *   `mobile` VARCHAR(20) DEFAULT NULL COMMENT '电话',
     *   `email` VARCHAR(60) DEFAULT NULL COMMENT '邮箱',
     *   `note` VARCHAR(200) DEFAULT NULL COMMENT '备注',
     *   `position_id` INT(20) DEFAULT NULL,
     */
    private String id;
    private String userName;
    private String realName;
    private byte sex;
    private String mobile;
    private String note;
    private int positionId;

    public User() {
    }

    public User(String id, String userName, String realName, byte sex, String mobile, String note, int positionId) {
        this.id = id;
        this.userName = userName;
        this.realName = realName;
        this.sex = sex;
        this.mobile = mobile;
        this.note = note;
        this.positionId = positionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public byte getSex() {
        return sex;
    }

    public void setSex(byte sex) {
        this.sex = sex;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getPositionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", realName='" + realName + '\'' +
                ", sex=" + sex +
                ", mobile='" + mobile + '\'' +
                ", note='" + note + '\'' +
                ", positionId=" + positionId +
                '}';
    }
}
