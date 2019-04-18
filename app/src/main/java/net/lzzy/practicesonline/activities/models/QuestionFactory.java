package net.lzzy.practicesonline.activities.models;

import android.text.TextUtils;

import net.lzzy.practicesonline.activities.constants.DbConstants;
import net.lzzy.practicesonline.activities.utils.AppUtils;
import net.lzzy.sqllib.SqlRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lzzy_gxy on 2019/4/17.
 * Description:
 */
public class QuestionFactory {
    private static final QuestionFactory OUR_INSTANCE = new QuestionFactory();
    private SqlRepository<Question> repository;
    private SqlRepository<Option> optionSqlRepository;

    public static QuestionFactory getInstance() {
        return OUR_INSTANCE;
    }

    private QuestionFactory() {
        repository = new SqlRepository<>(AppUtils.getContext(),Question.class, DbConstants.packager);
        optionSqlRepository =new SqlRepository<>(AppUtils.getContext(),Option.class,DbConstants.packager);

    }

    private void completeQuestion(Question question) throws InstantiationException,IllegalAccessException{
        //todo:4
        List<Option> options = optionSqlRepository.getByKeyword(question.getId().toString(),
                new String[]{Option.COL_QUESTION_ID},true);
        question.setOptions(options);
        question.setDbType(question.getDbType());
    }

    public Question getById(String questionId){
        //todo:5
        try{
            Question question = repository.getById(questionId);
            completeQuestion(question);
            return question;
        }catch (IllegalAccessException | InstantiationException e){
            e.printStackTrace();
            return null;
        }
    }

    public List<Question> getByPractice(String practiceId){
        try{
            List<Question> questions = repository.getByKeyword(practiceId,
                    new String[]{Question.COL_PRACTICE_ID},true);
            for (Question question : questions){
                completeQuestion(question);
            }
            return questions;
        }catch (IllegalAccessException | InstantiationException e ){
            e.printStackTrace();
        }
        return new ArrayList<>();
        //todo:2
    }

    public void insert(Question question){
        //todo:1
        String q = repository.getInsertString(question);
        List<String> sqlActions = new ArrayList<>();
        for (Option option :question.getOptions()){
            sqlActions.add(optionSqlRepository.getInsertString(option));
        }
        sqlActions.add(q);
        repository.exeSqls(sqlActions);
    }

    public List<String> getDeleteString(Question question){
        List<String> sqlActions = new ArrayList<>();
        sqlActions.add(repository.getDeleteString(question));
        for (Option option : question.getOptions()){
            sqlActions.add(optionSqlRepository.getDeleteString(option));
        }
        String f = FavoriteFactory.getInstance().getDeleteString(question.getId().toString());
        if (!TextUtils.isEmpty(f)){
            sqlActions.add(f);
        }
        return sqlActions;
    }
}
