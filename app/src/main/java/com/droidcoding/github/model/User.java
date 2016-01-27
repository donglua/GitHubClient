package com.droidcoding.github.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Donglua on 16/1/26.
 */
public class User {

  @SerializedName("login")               private String login;
  @SerializedName("id")                  private String id;
  @SerializedName("avatar_url")          private String avatar_url;
  @SerializedName("gravatar_id")         private String gravatar_id;
  @SerializedName("url")                 private String url;
  @SerializedName("html_url")            private String html_url;
  @SerializedName("followers_url")       private String followers_url;
  @SerializedName("following_url")       private String following_url;
  @SerializedName("gists_url")           private String gists_url;
  @SerializedName("starred_url")         private String starred_url;
  @SerializedName("subscriptions_url")   private String subscriptions_url;
  @SerializedName("organizations_url")   private String organizations_url;
  @SerializedName("repos_url")           private String repos_url;
  @SerializedName("events_url")          private String events_url;
  @SerializedName("received_events_url") private String received_events_url;
  @SerializedName("type")                private String type;
  @SerializedName("site_admin")          private String site_admin;
  @SerializedName("name")                private String name;
  @SerializedName("company")             private String company;
  @SerializedName("blog")                private String blog;
  @SerializedName("location")            private String location;
  @SerializedName("email")               private String email;
  @SerializedName("hireable")            private String hireable;
  @SerializedName("bio")                 private String bio;
  @SerializedName("public_repos")        private String public_repos;
  @SerializedName("public_gists")        private String public_gists;
  @SerializedName("followers")           private String followers;
  @SerializedName("following")           private String following;
  @SerializedName("created_at")          private String created_at;
  @SerializedName("updated_at")          private String updated_at;
  @SerializedName("private_gists")       private String private_gists;
  @SerializedName("total_private_repos") private String total_private_repos;
  @SerializedName("owned_private_repos") private String owned_private_repos;
  @SerializedName("disk_usage")          private String disk_usage;
  @SerializedName("collaborators")       private String collaborators;

  public String getLogin() {
    return login;
  }

  public String getId() {
    return id;
  }

  public String getAvatarUrl() {
    return avatar_url;
  }

  public String getGravatarId() {
    return gravatar_id;
  }

  public String getUrl() {
    return url;
  }

  public String getHtmlUrl() {
    return html_url;
  }

  public String getFollowersUrl() {
    return followers_url;
  }

  public String getFollowingUrl() {
    return following_url;
  }

  public String getGistsUrl() {
    return gists_url;
  }

  public String getStarredUrl() {
    return starred_url;
  }

  public String getSubscriptionsUrl() {
    return subscriptions_url;
  }

  public String getOrganizationsUrl() {
    return organizations_url;
  }

  public String getReposUrl() {
    return repos_url;
  }

  public String getEventsUrl() {
    return events_url;
  }

  public String getReceivedEventsUrl() {
    return received_events_url;
  }

  public String getType() {
    return type;
  }

  public String getSiteAdmin() {
    return site_admin;
  }

  public String getName() {
    return name;
  }

  public String getCompany() {
    return company;
  }

  public String getBlog() {
    return blog;
  }

  public String getLocation() {
    return location;
  }

  public String getEmail() {
    return email;
  }

  public String getHireable() {
    return hireable;
  }

  public String getBio() {
    return bio;
  }

  public String getPublicRepos() {
    return public_repos;
  }

  public String getPublicGists() {
    return public_gists;
  }

  public String getFollowers() {
    return followers;
  }

  public String getFollowing() {
    return following;
  }

  public String getCreatedAt() {
    return created_at;
  }

  public String getUpdatedAt() {
    return updated_at;
  }

  public String getPrivateGists() {
    return private_gists;
  }

  public String getTotalPrivateRepos() {
    return total_private_repos;
  }

  public String getOwnedPrivateRepos() {
    return owned_private_repos;
  }

  public String getDiskUsage() {
    return disk_usage;
  }

  public String getCollaborators() {
    return collaborators;
  }
}
