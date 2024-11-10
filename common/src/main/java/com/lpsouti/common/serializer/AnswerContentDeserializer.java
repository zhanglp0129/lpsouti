package com.lpsouti.common.serializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.lpsouti.common.entity.answer.AnswerContent;

import java.io.IOException;

public class AnswerContentDeserializer extends JsonDeserializer<AnswerContent> {
    @Override
    public AnswerContent deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        // TODO 完成答案的json反序列器
        return null;
    }
}
