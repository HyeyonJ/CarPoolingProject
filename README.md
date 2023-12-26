<img width="100%" alt="chatting" src="https://github.com/HyeyonJ/CarPoolingProject/assets/113879120/36001d3c-a22a-4a76-9a39-abc1754885b0.gif">

<div align="center">
  
## Carpooling(카풀 - 링)
추운 겨울, 더운 여름 택시를 잡으려고 했지만 어려움을 겪으셨나요?<br>
"carpooling(카풀링)"은 경로를 탐색하여 운전자와 사용자를 매칭하는 카풀 서비스입니다.<br><br>

</div>

  
## 목차


1. [프로젝트에 대하여](#1-프로젝트에-대하여)
2. [기술 스택](#2-기술-스택)
3. [설계 및 아키텍처](#3-설계-및-아키텍처)
4. [최적화를 위한 고민](#4-최적화를-위한-고민)
5. [Reference](#6-Reference)

<br>

## `1. 프로젝트에 대하여`
### 프로젝트 설명
<blockquote>
너무 추웠던 겨울, 폭설 내리던 날,  <br>
저 많은 차 중에서 내가 탈 수 있는 차는 없을까? <br>
택시를 2시간을 기다려야했던 고민으로 시작했습니다. <br>
</blockquote>


<p align="right"><a href="#목차">⬆ 목차로 돌아가기</a></p>
<br>

## `2. 기술 스택`

<div align="center">
  
<img width="80%" alt="스크린샷 2023-11-16 172640" src="https://github.com/HyeyonJ/CarPoolingProject/assets/113879120/74cfe0b5-2786-42ea-be6d-3711b7911beb">

</div>

<br>

<p align="right"><a href="#목차">⬆ 목차로 돌아가기</a></p>
<br>

## `3. 설계 및 아키텍처`

### ⚙️ Architecture

<div>
<img width="100%" alt="스크린샷 2023-11-16 173007" src="https://github.com/HyeyonJ/CarPoolingProject/assets/113879120/5bdbcb0b-2efe-4a82-be0f-01210cf502e6">
</div>


<br>


### ⚙️ GET

#### 🔹 상품 목록 리스트 요청

<img width="100%" alt="스크린샷 2023-11-28 170311" src="https://github.com/HyeyonJ/IT-Da-Shopping/assets/113879120/a84f2cf1-f5b6-423c-aa32-5e47fc5099fd">

 - 페이지가 정상적으로 요청되었을 시 react query로 상품 리스트를 불러옵니다.
 - Redux에 상품 리스트를 저장합니다.

#### 🔹 IP 주소 요청

<img width="100%" alt="스크린샷 2023-11-28 171208" src="https://github.com/HyeyonJ/IT-Da-Shopping/assets/113879120/1f45b12a-870f-4c93-9890-6242ceb0b696">

 - 해당 쇼핑몰의 접근성을 높이기 위해 회원가입 후 로그인 입장이 아닌, IP 기반으로 사용자를 구분할 수 있도록 만들었습니다.

<br>

### ⚙️ GET, PUT, DELETE

#### 🔹상품 상세 페이지 요청

<img width="100%" alt="스크린샷 2023-11-28 181323" src="https://github.com/HyeyonJ/IT-Da-Shopping/assets/113879120/6cfc211c-b137-4f80-abb2-66f655c70a4d">

- 상품의 상세 페이지를 볼 수 있습니다.
- 해당 제품을 자신이 작성했다면, 수정 버튼을 눌러 상품을 수정할 수 있습니다.
- 해당 제품을 자신이 작성했다면, 삭제 버튼을 눌러 상품을 삭제할 수 있습니다.
<br>

### ⚙️ POST

#### 🔹프로젝트 만들기

<img width="100%" alt="스크린샷 2023-11-28 174253" src="https://github.com/HyeyonJ/IT-Da-Shopping/assets/113879120/2e8f4b9a-c7e5-48ea-a867-f7e3f3c24711">

 - TextField에 값이 없을 시 regex로 유효성 검사를 시행하도록 만들었습니다.
 - 제출하기 버튼을 누를 시 Home으로 가고, 리스트를 추가하도록 만들었습니다.

<br>

<p align="right"><a href="#목차">⬆ 목차로 돌아가기</a></p>
<br>
  
## 4. `최적화를 위한 고민`

### 💡 react query를 선택한 이유

<img width="100%" alt="스크린샷 2023-11-28 193504" src="https://github.com/HyeyonJ/IT-Da-Shopping/assets/113879120/bc082c35-f405-4ec3-89e8-12cec3a5fad7">

- 비용의 관점에서 백엔드로 가는 Call 수 절감이 중요하다고 생각했기 때문에 reactquery를 선택했습니다.
- react query 선택 후 동일한 데이터 호출 시 10번 중 9번은 API를 직접 부르지 않아 90% Call 수 절감을 했습니다.

<br>

<img width="100%" alt="스크린샷 2023-11-28 194118" src="https://github.com/HyeyonJ/IT-Da-Shopping/assets/113879120/792e91df-e4a9-438d-98da-9a531f4aa1b1">

- 좋아요 버튼을 눌렀을 시 react query mutation으로 데이터가 PUT 되지만, 업데이트 된 데이터가 UI에 보이지 않아 업데이트 된 데이터를 화면에 보여주기 위해서 또 다시 react query로 API를 요청해야했습니다.
- react query로 GET한 데이터를 useState로 배열에 저장한 후, UI에 보여주어 DB로 가는 call 수 2번에서 1번으로 50% 절감할 수 있었습니다.
- 현재는 redux로 처리하여 복잡한 로직을 더욱 간단하게 만들었습니다.

<br>

<img width="100%" alt="스크린샷 2023-11-28 194421" src="https://github.com/HyeyonJ/IT-Da-Shopping/assets/113879120/95d09c67-1af8-42fb-b0ca-47551a3c0f26">

- 비용의 관점에서 DB로 가는 Call 수 절감이 중요하다고 생각했기 때문에 redux를 선택했습니다.
- redux 선택 후 메인 화면에서 상세페이지 접속 시 Call 수 2번에서 1번 요청으로 50%를 절감했습니다.

<br>

<p align="right"><a href="#목차">⬆ 목차로 돌아가기</a></p>
<br>

## 5. `테스트`

### 💡 TDD

<img width="100%" alt="스크린샷 2023-11-28 184955" src="https://github.com/HyeyonJ/IT-Da-Shopping/assets/113879120/be9b2820-7452-4d28-83fc-8da5ad00f4d3">

  - regex로 유효성 검사를 만들었습니다.
  - 논리 흐름의 오류는 없는지 TDD를 작성하여 테스트를 완료했습니다.

<br>

<p align="right"><a href="#목차">⬆ 목차로 돌아가기</a></p>
<br>

## 6. `Reference`

- https://ko.legacy.reactjs.org/docs/getting-started.html
- https://www.typescriptlang.org/ko/docs/handbook/2/basic-types.html
- https://tanstack.com/query/latest/docs/react/overview
- https://redux.js.org/introduction/getting-started
- https://tailwindcss.com/
- https://mui.com/

