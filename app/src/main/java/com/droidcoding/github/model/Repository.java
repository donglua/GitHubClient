package com.droidcoding.github.model;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.gson.annotations.SerializedName;
import org.threeten.bp.Instant;

import static com.droidcoding.github.util.Preconditions.checkNotNull;

public final class Repository {
  @SerializedName("name")        @NonNull public final String name;
  @SerializedName("full_name")   @NonNull public final String full_name;
  @SerializedName("owner")       @NonNull public final User owner;
  @SerializedName("description") @Nullable public final String description;

  @SerializedName("watchers")   public final long watchers;
  @SerializedName("forks")      public final long forks;

  @SerializedName("html_url")   public final String html_url;
  @SerializedName("url")        public final String url;

  @SerializedName("updated_at") public final Instant updated_at;

  private Repository(Builder builder) {
    this.name = checkNotNull(builder.name, "name == null");
    this.full_name = checkNotNull(builder.fullName, "fullName == null");
    this.owner = checkNotNull(builder.owner, "owner == null");
    this.description = builder.description;
    this.watchers = builder.stars;
    this.forks = builder.forks;
    this.html_url = checkNotNull(builder.htmlUrl, "html_url == null");
    this.url = checkNotNull(builder.url, "url == null");
    this.updated_at = checkNotNull(builder.updatedAt, "updated_at == null");
  }

  @Override public String toString() {
    return "Repository{" +
        "name='" + name + '\'' +
        ", full_name=" + full_name +
        ", owner=" + owner +
        ", description='" + description + '\'' +
        ", watchers=" + watchers +
        ", forks=" + forks +
        ", html_url='" + html_url + '\'' +
        ", url='" + url + '\'' +
        ", updated_at=" + updated_at +
        '}';
  }

  public static final class Builder {
    private String name;
    private String fullName;
    private User owner;
    private String description;
    private long stars;
    private long forks;
    private String htmlUrl;
    private String url;
    private Instant updatedAt;

    public Builder name(String name) {
      this.name = name;
      return this;
    }

    public Builder fullName(String name) {
      this.fullName = name;
      return this;
    }

    public Builder owner(User owner) {
      this.owner = owner;
      return this;
    }

    public Builder description(String description) {
      this.description = description;
      return this;
    }

    public Builder stars(long stars) {
      this.stars = stars;
      return this;
    }

    public Builder forks(long forks) {
      this.forks = forks;
      return this;
    }

    public Builder htmlUrl(String htmlUrl) {
      this.htmlUrl = htmlUrl;
      return this;
    }

    public Builder url(String url) {
      this.url = url;
      return this;
    }

    public Builder updatedAt(Instant updatedAt) {
      this.updatedAt = updatedAt;
      return this;
    }

    public Repository build() {
      return new Repository(this);
    }
  }
}
