package com.droidcoding.github.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import org.threeten.bp.Instant;

/**
 * Created by Donglua on 16/1/25.
 */
public class InstantDeserializer implements JsonDeserializer<Instant> {

  @Override
  public Instant deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
      throws JsonParseException {
    return Instant.parse(json.getAsString());
  }
}
