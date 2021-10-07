package com.ssafy.vo;

//import com.fasterxml.jackson.annotation.JsonCreator;
//import com.fasterxml.jackson.annotation.JsonProperty;
//import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SocketVO {
    // 유저의 이름을 저장하기 위한 변수
    private String userName;

    // 메시지의 내용을 저장하기 위한 변수
    private String content;
    
//    @JsonCreator
//    public SocketVO(
//    		@JsonProperty("userName") String userName,
//    		@JsonProperty("content") String content) {
//    	this.userName = userName;
//    	this.content = content;
//    }
    
    public SocketVO() {
    	
    }
    
    public SocketVO(String userName, String content) {
    	this.userName = userName;
    	this.content = content;
    }
}
