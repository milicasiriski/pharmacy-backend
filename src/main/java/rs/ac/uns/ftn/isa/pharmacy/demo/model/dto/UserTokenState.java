package rs.ac.uns.ftn.isa.pharmacy.demo.model.dto;

import java.util.Objects;

public class UserTokenState {

    private String userType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpiresIn;
    private Long refreshTokenExpiresIn;

    public UserTokenState(String userType, String accessToken, String refreshToken, long accessTokenExpiresIn, long refreshTokenExpiresIn) {
        this.userType = userType;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.accessTokenExpiresIn = accessTokenExpiresIn;
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    public UserTokenState() {
        this.userType = null;
        this.accessToken = null;
        this.refreshToken = null;
        this.accessTokenExpiresIn = null;
        this.refreshTokenExpiresIn = null;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Long getAccessTokenExpiresIn() {
        return accessTokenExpiresIn;
    }

    public void setAccessTokenExpiresIn(Long accessTokenExpiresIn) {
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }

    public Long getRefreshTokenExpiresIn() {
        return refreshTokenExpiresIn;
    }

    public void setRefreshTokenExpiresIn(Long refreshTokenExpiresIn) {
        this.refreshTokenExpiresIn = refreshTokenExpiresIn;
    }

    @Override
    public String toString() {
        return "UserTokenState{" +
                "userType='" + userType + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", accessTokenExpiresIn=" + accessTokenExpiresIn +
                ", refreshTokenExpiresIn=" + refreshTokenExpiresIn +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTokenState that = (UserTokenState) o;
        return Objects.equals(userType, that.userType) &&
                Objects.equals(accessToken, that.accessToken) &&
                Objects.equals(refreshToken, that.refreshToken) &&
                Objects.equals(accessTokenExpiresIn, that.accessTokenExpiresIn) &&
                Objects.equals(refreshTokenExpiresIn, that.refreshTokenExpiresIn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userType, accessToken, refreshToken, accessTokenExpiresIn, refreshTokenExpiresIn);
    }
}
