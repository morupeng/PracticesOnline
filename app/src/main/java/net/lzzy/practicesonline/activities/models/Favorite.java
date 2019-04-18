package net.lzzy.practicesonline.activities.models;

import net.lzzy.sqllib.Ignored;
import net.lzzy.sqllib.Sqlitable;

import java.util.UUID;

/**
 * Created by lzzy_gxy on 2019/4/16.
 * Description:
 */
public class Favorite extends BaseEntity implements Sqlitable {
    @Ignored
    public static final String COL_QUESTION_ID = "questionId";
    private UUID question;

    public UUID getQuestionId() {
        return question;
    }

    public void setQuestionId(UUID question) {
        this.question = question;
    }


    @Override
    public boolean needUpdate() {
        return false;
    }
}
