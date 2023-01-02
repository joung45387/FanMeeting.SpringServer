# VRFanMeeting Service 서버 프로젝트
언리얼을 이용한 VR 팬미팅 서비스의 서버 개발 프로젝트입니다.
언리얼 클리아언트는 https://github.com/DPRLive/FanMeetingDemo 를 확인해주세요.

# 개발환경
*IntelliJ
*Postman
 팀 NotReal의 프로젝트 메타버스 팬미팅 서비스의 서버를 담당하는 스프링 부트를 활용한 서버입니다.  
 이 서버는 크게 2가지 역할을 수행하고 있습니다.  
 1. 언리얼 서비스 내의 채팅
 2. 팬미팅 특화 관리자 서비스 제공
 
 채팅은 WebSocket STOMP를 이용하여 언리얼 내에서의 채팅과 유저 관리를 보다 효율적으로 할 수 있습니다.
