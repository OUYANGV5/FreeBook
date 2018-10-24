
package com.ouyang.freebook.modle.bean;

public class BookVote {

    private long BookId;
    private int TotalScore;
    private int VoterCount;
    private double Score;
    public void setBookId(long BookId) {
        this.BookId = BookId;
    }
    public long getBookId() {
        return BookId;
    }

    public void setTotalScore(int TotalScore) {
        this.TotalScore = TotalScore;
    }
    public int getTotalScore() {
        return TotalScore;
    }

    public void setVoterCount(int VoterCount) {
        this.VoterCount = VoterCount;
    }
    public int getVoterCount() {
        return VoterCount;
    }

    public void setScore(double Score) {
        this.Score = Score;
    }
    public double getScore() {
        return Score;
    }

}