# VRFanMeeting Service 서버 프로젝트
언리얼을 이용한 VR 팬미팅 서비스의 서버 개발 프로젝트입니다.<br/>
언리얼 클리아언트는 https://github.com/DPRLive/FanMeetingDemo 를 확인해주세요.

# 사용기술
## 백엔드
* SpringBoot
* Spring Data JPA
* MySQL
* WebSocket

## 프론트엔드
* Thymeleaf
* HTML/CSS/Javascript
* Bootstrap5

## Infra
* AWS EC2

# 프로젝트 목적
기존에 존재하는 타 VR 플랫폼인 VR챗에서는 팬미팅을 위한 플랫폼이 아닌 종합 엔터테인먼트 목적 플랫폼이므로 팬미팅 같이 어느정도 관리가 필요한 곳에서는 사용하지 못한다는 단점을 가지고 있습니다. <br/> 
실제로 VR챗에서는 갱이나 테러와 같은 행위를 하는 악성 유저를 컨트롤 할수 없습니다.<br/> 

따라서 관리자 웹페이지라는 특수 목적 웹페이지를 만들어 유저들을 원할히 관리하는것을 목표로 한 프로젝트입니다.<br/>

 팀 NotReal의 프로젝트 메타버스 팬미팅 서비스의 서버를 담당하는 스프링 부트를 활용한 서버입니다.  
 이 서버는 크게 2가지 역할을 수행하고 있습니다.  
 1. 언리얼 서비스 내의 채팅
 2. 팬미팅 특화 관리자 서비스 제공
 
 채팅은 WebSocket STOMP를 이용하여 언리얼 내에서의 채팅과 유저 관리를 보다 효율적으로 할 수 있습니다.
