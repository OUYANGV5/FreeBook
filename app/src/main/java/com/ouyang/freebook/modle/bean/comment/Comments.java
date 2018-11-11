
package com.ouyang.freebook.modle.bean.comment;
import java.util.List;


public class Comments {

    private List<String> attachments;
    private long comment_id;
    private List<String> comments;
    private String content;
    private long create_time;
    private boolean elite;
    private int floor_count;
    private String from;
    private boolean hide;
    private boolean hide_floor;
    private boolean highlight;
    private String ip;
    private String ip_location;
    private String metadata;
    private MetadataAsJson metadataAsJson;
    private int oppose_count;
    private Passport passport;
    private boolean quick;
    private int reply_count;
    private int reply_id;
    private int score;
    private int status;
    private int support_count;
    private boolean top;
    private UserScore userScore;
    private long user_id;
    public void setAttachments(List<String> attachments) {
         this.attachments = attachments;
     }
     public List<String> getAttachments() {
         return attachments;
     }

    public void setComment_id(long comment_id) {
         this.comment_id = comment_id;
     }
     public long getComment_id() {
         return comment_id;
     }

    public void setComments(List<String> comments) {
         this.comments = comments;
     }
     public List<String> getComments() {
         return comments;
     }

    public void setContent(String content) {
         this.content = content;
     }
     public String getContent() {
         return content;
     }

    public void setCreate_time(long create_time) {
         this.create_time = create_time;
     }
     public long getCreate_time() {
         return create_time;
     }

    public void setElite(boolean elite) {
         this.elite = elite;
     }
     public boolean getElite() {
         return elite;
     }

    public void setFloor_count(int floor_count) {
         this.floor_count = floor_count;
     }
     public int getFloor_count() {
         return floor_count;
     }

    public void setFrom(String from) {
         this.from = from;
     }
     public String getFrom() {
         return from;
     }

    public void setHide(boolean hide) {
         this.hide = hide;
     }
     public boolean getHide() {
         return hide;
     }

    public void setHide_floor(boolean hide_floor) {
         this.hide_floor = hide_floor;
     }
     public boolean getHide_floor() {
         return hide_floor;
     }

    public void setHighlight(boolean highlight) {
         this.highlight = highlight;
     }
     public boolean getHighlight() {
         return highlight;
     }

    public void setIp(String ip) {
         this.ip = ip;
     }
     public String getIp() {
         return ip;
     }

    public void setIp_location(String ip_location) {
         this.ip_location = ip_location;
     }
     public String getIp_location() {
         return ip_location;
     }

    public void setMetadata(String metadata) {
         this.metadata = metadata;
     }
     public String getMetadata() {
         return metadata;
     }

    public void setMetadataAsJson(MetadataAsJson metadataAsJson) {
         this.metadataAsJson = metadataAsJson;
     }
     public MetadataAsJson getMetadataAsJson() {
         return metadataAsJson;
     }

    public void setOppose_count(int oppose_count) {
         this.oppose_count = oppose_count;
     }
     public int getOppose_count() {
         return oppose_count;
     }

    public void setPassport(Passport passport) {
         this.passport = passport;
     }
     public Passport getPassport() {
         return passport;
     }

    public void setQuick(boolean quick) {
         this.quick = quick;
     }
     public boolean getQuick() {
         return quick;
     }

    public void setReply_count(int reply_count) {
         this.reply_count = reply_count;
     }
     public int getReply_count() {
         return reply_count;
     }

    public void setReply_id(int reply_id) {
         this.reply_id = reply_id;
     }
     public int getReply_id() {
         return reply_id;
     }

    public void setScore(int score) {
         this.score = score;
     }
     public int getScore() {
         return score;
     }

    public void setStatus(int status) {
         this.status = status;
     }
     public int getStatus() {
         return status;
     }

    public void setSupport_count(int support_count) {
         this.support_count = support_count;
     }
     public int getSupport_count() {
         return support_count;
     }

    public void setTop(boolean top) {
         this.top = top;
     }
     public boolean getTop() {
         return top;
     }

    public void setUserScore(UserScore userScore) {
         this.userScore = userScore;
     }
     public UserScore getUserScore() {
         return userScore;
     }

    public void setUser_id(long user_id) {
         this.user_id = user_id;
     }
     public long getUser_id() {
         return user_id;
     }

}