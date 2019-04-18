package net.lzzy.practicesonline.activities.models;

import net.lzzy.sqllib.Sqlitable;

import java.util.UUID;

/**
 * Created by lzzy_gxy on 2019/4/16.
 * Description:
 */
public class Option extends BaseEntity implements Sqlitable {
    public static final String COL_QUESTION_ID = "questions";
    private String content;
    private String label;
    private UUID questionId;
    private boolean isAnswer;
    private int spiId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLable() {
        return label;
    }

    public void setLable(String label) {
        this.label = label;
    }

    public UUID getQuestionId() {
        return questionId;
    }

    public void setQuestionId(UUID questionId) {
        this.questionId = questionId;
    }

    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(boolean answer) {
        isAnswer = answer;
    }

    public int getSpiId() {
        return spiId;
    }

    public void setSpiId(int spiId) {
        this.spiId = spiId;
    }


    @Override
    public boolean needUpdate() {
        return false;
    }
}
