
package com.ouyang.freebook.modle.bean.comment;
import java.util.List;

public class CommentResponseData {

    private int cmt_sum;
    private List<Comments> comments;
    private List<String> expert;
    private List<Hots> hots;
    private int mode;
    private int outer_cmt_sum;
    private int participation_sum;
    private long topic_id;
    private int total_page_no;
    private List<User_level> user_level;

    public void setCmt_sum(int cmt_sum) {
         this.cmt_sum = cmt_sum;
     }
     public int getCmt_sum() {
         return cmt_sum;
     }

    public void setComments(List<Comments> comments) {
         this.comments = comments;
     }
     public List<Comments> getComments() {
         return comments;
     }

    public void setExpert(List<String> expert) {
         this.expert = expert;
     }
     public List<String> getExpert() {
         return expert;
     }

    public void setHots(List<Hots> hots) {
         this.hots = hots;
     }
     public List<Hots> getHots() {
         return hots;
     }

    public void setMode(int mode) {
         this.mode = mode;
     }
     public int getMode() {
         return mode;
     }

    public void setOuter_cmt_sum(int outer_cmt_sum) {
         this.outer_cmt_sum = outer_cmt_sum;
     }
     public int getOuter_cmt_sum() {
         return outer_cmt_sum;
     }

    public void setParticipation_sum(int participation_sum) {
         this.participation_sum = participation_sum;
     }
     public int getParticipation_sum() {
         return participation_sum;
     }

    public void setTopic_id(long topic_id) {
         this.topic_id = topic_id;
     }
     public long getTopic_id() {
         return topic_id;
     }

    public void setTotal_page_no(int total_page_no) {
         this.total_page_no = total_page_no;
     }
     public int getTotal_page_no() {
         return total_page_no;
     }

    public void setUser_level(List<User_level> user_level) {
         this.user_level = user_level;
     }
     public List<User_level> getUser_level() {
         return user_level;
     }

}