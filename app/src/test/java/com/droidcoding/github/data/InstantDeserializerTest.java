package com.droidcoding.github.data;

import com.google.common.truth.Truth;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import org.junit.Test;
import org.threeten.bp.Instant;

/**
 * Created by Donglua on 16/1/26.
 */
public class InstantDeserializerTest {
  private static final Instant EPOCH = Instant.ofEpochSecond(0);
  private static final Instant RECENT = Instant.ofEpochSecond(1438337819);

  private final Gson gson =
      new GsonBuilder().registerTypeAdapter(Instant.class, new InstantDeserializer()).create();

  @Test public void deserialization() throws IOException {

    Truth.assertThat(gson.fromJson("\"1970-01-01T00:00:00Z\"", Instant.class)).isEqualTo(EPOCH);
    Truth.assertThat(gson.fromJson("\"2015-07-31T10:16:59Z\"", Instant.class)).isEqualTo(RECENT);
  }
}