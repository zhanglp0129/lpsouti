package com.lpsouti.common.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.lpsouti.common.constant.QuestionType;
import com.lpsouti.common.entity.answer.*;
import com.lpsouti.common.exception.CommonException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class AnswerContentDeserializer extends JsonDeserializer<AnswerContent> {
    @Override
    public AnswerContent deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        log.debug("answer content json text = {}", jsonParser.getText());
        JsonNode root = jsonParser.readValueAsTree();
        AnswerContent answerContent = parse(root);
        log.debug("answer content = {}", answerContent);
        return answerContent;
    }

    // 解析json树
    private AnswerContent parse(JsonNode root) {
        // 获取题目类型
        int type = root.get("type").asInt();
        AnswerContent answerContent;
        // 根据题目类型解析
        if (QuestionType.SINGLE_CHOICE_QUESTION.intValue() == type) {
            // 单选题
            JsonNode optionNode = root.get("option");
            String index = optionNode.get("index").asText();
            String content = optionNode.get("content").asText();
            Option option = new Option(index, content);
            answerContent = new SingleChoiceQuestionAnswer(option);
        } else if (QuestionType.MULTIPLE_CHOICE_QUESTION.intValue() == type) {
            // 多选题
            JsonNode optionsNode = root.get("options");
            List<Option> options = new ArrayList<>();
            for (JsonNode optionNode : optionsNode) {
                String index = optionNode.get("index").asText();
                String content = optionNode.get("content").asText();
                Option option = new Option(index, content);
                options.add(option);
            }
            answerContent = new MultipleChoiceQuestionAnswer(options);
        } else if (QuestionType.TRUE_FALSE_QUESTION.intValue() == type) {
            // 判断题
            JsonNode answerNode = root.get("answer");
            Boolean answer = answerNode.booleanValue();
            answerContent = new TrueFalseQuestionAnswer(answer);
        } else if (QuestionType.FILL_BLANK_QUESTION.intValue() == type) {
            // 填空题
            JsonNode blanksNode = root.get("blanks");
            List<String> blanks = new ArrayList<>();
            for (JsonNode blankNode : blanksNode) {
                String blank = blankNode.textValue();
                blanks.add(blank);
            }
            answerContent = new FillBlankQuestionAnswer(blanks);
        } else if (QuestionType.ESSAY_QUESTION.intValue() == type) {
            // 解答题
            JsonNode contentNode = root.get("content");
            String content = contentNode.textValue();
            answerContent = new EssayQuestionAnswer(content);
        } else if (QuestionType.QUESTION_GROUP.intValue() == type) {
            // 题组
            JsonNode childrenNode = root.get("children");
            List<AnswerContent> children = new ArrayList<>();
            for (JsonNode childNode : childrenNode) {
                AnswerContent child = parse(childNode);
                children.add(child);
            }
            answerContent = new QuestionGroupAnswer(children);
        } else {
            // 错误
            throw new CommonException("题目类型错误");
        }
        answerContent.setType((byte) type);
        return answerContent;
    }
}
