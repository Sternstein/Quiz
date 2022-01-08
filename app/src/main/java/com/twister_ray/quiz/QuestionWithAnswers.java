package com.twister_ray.quiz;

import androidx.room.Relation;

import java.util.List;

public class QuestionWithAnswers {

    public long id;

    public String description;

    @Relation(parentColumn = "id", entityColumn = "question")
    public List<Answer> answers;
}
