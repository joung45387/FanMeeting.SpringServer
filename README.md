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

# 핵심 기능
관리자 웹페이지는 HTTP통신으로 작동하기 때문에 사용자가 서버로 요청을 보내는 단방향 통신밖에 지원하지 않아 제어가 불가합니다.<br/>
이를 해결하기 위해 STOMP 방식의 Websocket을 이용하였습니다.<br/>
Publisher와 Subscriber를 지정하고, Message Broker를 통해 특정 사용자에게만 Message를 전송하는 기능으로 관리자 웹페이지에서의 채팅, 플랫폼 내부에서의 채팅 제한, 플랫폼 내부 Youtube Player에 영상 제어, 음성 채팅 강제 음소거, 채팅 삭제, 특정 이용자 강퇴, 영구정지, 일시 정지 등의 기능을 구현하였습니다.

<img src="https://user-images.githubusercontent.com/45916379/210212333-5514b97d-7bb2-436c-b62b-a538e95f3a55.gif">
<img src="https://user-images.githubusercontent.com/45916379/210212539-8ec5d1f1-3a20-4d08-8872-f036f8f324cc.gif">

# 아키텍처

<img src="https://user-images.githubusercontent.com/45916379/210212945-d193d677-4df3-4023-8c2c-1a65a3a96586.png">

# DB ERD

<img src="https://user-images.githubusercontent.com/45916379/210212665-1586eaca-9521-4a49-bb7a-482a0882f9c1.png">

# 응용방안

팬미팅뿐만 아니라 통제가 필요한 일대다 가상공간 미팅이라면 여러 분야에 충분한 활용이 가능합니다

# 팀원
* 조성윤 : Client 제작 & 프로젝트 총괄
  * github : https://github.com/DPRLive
* 이종훈 : Dedicated Server 빌드 & 관리자 웹페이지 Front & Backend 구성
  * github : https://github.com/joung45387
* 차준혁 : 서버 보조 & Level 디자인
  * github : https://github.com/chalaly
