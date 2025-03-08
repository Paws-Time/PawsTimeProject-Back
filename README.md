# Paws Time

![image](https://github.com/user-attachments/assets/a97dc960-b726-426f-bf37-f8b04911a38a)


# 📌 프로젝트 명

## 📖 목차
- [🐾 프로젝트 소개](#-프로젝트-소개)
- [👥 팀원 소개](#-팀원-소개)
- [🤝 협업 방식](#-협업-방식)
- [📚 프로젝트 진행 상황 관리](#-프로젝트-진행-상황-관리)
- [🔍 브랜치 전략](#-브랜치-전략)
- [✔ 컨벤션](#-컨벤션)
- [⚙ 배포](#-배포)
- [🛠 개발 도구](#-개발-도구)
- [📆 프로젝트 일정](#-프로젝트-일정)
- [📄 API 명세서 및 ERD 설계도](#-api-명세서-및-erd-설계도)
- [✔ 응답구조](#-응답구조)
- [📋 메뉴 구조도](#-메뉴-구조도)
- [🖥 화면 구현](#-화면-구현)
- [🙋‍♂️ 주요 기능 및 코드 리뷰](#-주요-기능-및-코드-리뷰)
- [💡 느낀점](#-느낀점)

---

## 🐾 프로젝트 소개
이 프로젝트는 반려동물 관련 정보를 효과적으로 공유할 수 있는 커뮤니티 플랫폼입니다. 반려동물 자랑, 정보 공유 등 다양한 게시판을 제공하며, 특히 관리자가 주제에 맞는 게시판을 유동적으로 생성할 수 있어 사용자가 원하는 주제에 대해 자유롭게 정보를 공유하고 소통할 수 있는 유연한 환경을 제공합니다.

또한, 동물병원 및 동물 보호소 목록을 제공하여 사용자가 반려동물과 관련된 필수적인 정보를 쉽게 확인할 수 있도록 하였습니다. 지도 기능을 통해 가까운 병원과 보호소의 위치를 한눈에 파악할 수 있으며, 긴급한 상황 발생 시 신속하게 적절한 조치를 취할 수 있도록 돕습니다. 이를 통해 반려동물 보호와 돌봄이 보다 원활하게 이루어질 수 있는 환경을 구축하고자 하였습니다


## 👥 팀원 소개
| ![백도은](https://i.ytimg.com/vi/5pYOLJ-xXTo/hqdefault.jpg?sqp=-oaymwEnCNACELwBSFryq4qpAxkIARUAAIhCGAHYAQHiAQoIGBACGAY4AUAB&rs=AOn4CLA1vIgbDLHZDEN4WNvfUf87tvNv1Q) | ![안하령](https://i.ytimg.com/vi/5pYOLJ-xXTo/hqdefault.jpg?sqp=-oaymwEnCNACELwBSFryq4qpAxkIARUAAIhCGAHYAQHiAQoIGBACGAY4AUAB&rs=AOn4CLA1vIgbDLHZDEN4WNvfUf87tvNv1Q) | ![심재원](https://i.ytimg.com/vi/5pYOLJ-xXTo/hqdefault.jpg?sqp=-oaymwEnCNACELwBSFryq4qpAxkIARUAAIhCGAHYAQHiAQoIGBACGAY4AUAB&rs=AOn4CLA1vIgbDLHZDEN4WNvfUf87tvNv1Q) | ![박명환](https://i.ytimg.com/vi/5pYOLJ-xXTo/hqdefault.jpg?sqp=-oaymwEnCNACELwBSFryq4qpAxkIARUAAIhCGAHYAQHiAQoIGBACGAY4AUAB&rs=AOn4CLA1vIgbDLHZDEN4WNvfUf87tvNv1Q) |
|---|---|---|---|
| 백도은 | 안하령 | 심재원 | 박명환 |
| [@donna8780](https://github.com/donna8780) | [@dgf0020](https://github.com/dgf0020) | [@S-JaeWon](https://github.com/S-JaeWon) | [@freemuras](https://github.com/freemuras) |


## 🤝 협업 방식
## GitHub Issue 생성
1. 해당 업무에 맞는 GitHub Issue를 생성합니다.
   - 작업할 내용과 이슈 번호를 기록합니다.
   - 예시: `feat/board#1`, `fix/login#2`와 같이 이슈 번호를 포함하여 생성합니다.

## 브랜치 생성 및 작업
2. GitHub Actions 또는 개발자가 직접 해당 이슈에 맞는 브랜치를 생성하고, 해당 브랜치로 전환하여 작업을 시작합니다.
   - 예시: `feat/board#1`, `fix/login#2`와 같이 이슈 번호와 관련된 브랜치를 사용합니다.
   - 브랜치 생성 후, 작업을 진행하고 코드를 수정합니다.

## 코드 푸시
3. 작업이 완료되면, 해당 브랜치에 수정된 코드를 GitHub에 push합니다.
   - `git push origin feat/board#1` 과 같이 브랜치명을 명확히 지정하여 푸시합니다.

## Pull Request (PR) 오픈
4. 각자 생성한 브랜치에서 작업을 마친 후, GitHub에서 Pull Request (PR)를 오픈합니다.
   - PR 제목에 해당 이슈 번호와 작업 내용을 포함하여 작성합니다.
   - 예시: `feat: board 관련 기능 추가 (#1)` 또는 `fix: 로그인 버그 수정 (#2)` 등

## 코드 리뷰 및 승인
5. PR을 오픈하면, **2명 이상의 팀원**이 해당 PR을 리뷰하고 승인을 진행합니다. 
   - 팀 내에서 코드 리뷰는 반드시 2명 이상의 팀원이 진행하여, 코드 품질을 보장하고 문제가 없는지 확인합니다.
   
## PR Merge 및 이슈 상태 변경
6. 2명 이상의 팀원이 PR을 승인한 후, 해당 PR을 merge하여 작업을 완료합니다. PR이 merge되면 GitHub에서 해당 PR은 자동으로 `closed` 상태로 변경됩니다.
7. PR이 merge되면, GitHub에서는 연결된 이슈 번호가 자동으로 `Done` 상태로 변경되어 완료됩니다.
   - 예시: PR이 merge되면, `#1` 이슈가 `Done`으로 상태가 변경됩니다.

## 정기 회의 및 코드 리뷰
- 매주 **화요일**: 팀원들이 모여 **정기 회의**를 진행합니다.
   - 회의에서는 진행 중인 업무 상황을 공유하고, 발생한 문제나 어려움에 대해 논의합니다.

- 매주 **토요일**: **디스코드**를 이용하여 각자 맡은 코드에 대한 **리뷰**를 진행합니다.
   - 프론트엔드 팀은 해당 페이지를 보여주고, 백엔드 팀은 API 및 기능 구현에 대해 의견을 나눕니다.
   - 코드 리뷰는 주로 각자 맡은 부분을 리뷰하며, 다른 팀원들에게 피드백을 주고받습니다.

## 추가 사항
- 코드 리뷰와 스타일 공유는 팀 내에서 효율적인 협업을 위해 항상 진행됩니다.
- 코드 스타일과 규칙을 잘 따르도록 지속적으로 정리하고 조정하는 시간을 가지며, 일관된 코드 품질을 유지합니다.


## 📚 프로젝트 진행 상황 관리
![image](https://github.com/user-attachments/assets/4fff906c-30cd-4189-a848-43f8dd69b4b6)
이슈 관리 방법
GitHub Issues: 각 기능 개발 및 버그 수정에 대한 이슈를 GitHub Issues를 통해 관리합니다. 이슈는 각 팀원이 할당받아 작업을 진행하며, 작업 완료 후에는 이슈를 종료합니다. 각 이슈는 상세한 설명과 함께 등록하며, 관련된 PR(풀 리퀘스트)과 연결합니다.
레이블: 이슈에 레이블을 붙여서 우선순위나 진행 상태(예: 기능 추가, 버그 수정, 완료, 진행 중 등)를 쉽게 파악할 수 있도록 관리합니다.
이슈 상태 업데이트: 이슈 상태는 To Do, In Progress, Done으로 나누어 진행 상태를 명확히 파악할 수 있게 합니다.

PR 템플릿 사용
PR(풀 리퀘스트) 템플릿: 모든 PR은 미리 정의된 템플릿을 사용하여 작성해야 합니다. 템플릿은 수정한 내용과 관련된 이슈 번호, 변경 사항을 자세히 기록하도록 하여 코드 리뷰가 용이하도록 합니다.
PR 작성 규칙: PR 제목은 간단명료하게 작성하며, 각 PR은 하나의 기능 또는 버그 수정에 해당하도록 합니다. 또한, 변경 사항에 대해서는 상세히 설명하고, 테스트 방법을 명시하여 팀원들이 확인할 수 있게 합니다.
리뷰 및 승인: PR은 최소 두 명 이상의 팀원이 리뷰하고 승인해야 머지가 가능합니다. 코드 리뷰는 코드 품질을 높이는 중요한 과정으로, 팀원 간 피드백을 통해 개선합니다.

변경 사항 관리
버전 관리: Git을 사용하여 코드 버전 관리를 진행하며, 각 기능을 개발할 때는 feature 브랜치를 사용하고, 이후 메인 브랜치에 머지합니다. 머지 전에는 반드시 테스트와 리뷰를 완료해야 합니다.
Changelog: 주요 변경 사항은 Changelog에 기록하여, 프로젝트 진행 상황을 쉽게 추적할 수 있도록 합니다.

## 🔍 브랜치 전략

우리는 **이슈 기반 브랜치 전략**을 사용하여 프로젝트를 진행하였습니다. 이 방식은 각 이슈를 별도의 브랜치에서 처리하고, 작업이 완료된 후 `main` 브랜치에 병합하는 방법입니다. 이를 통해 각 기능을 독립적으로 개발하고, 충돌을 최소화하면서 작업을 관리할 수 있습니다.

## 작업 흐름

1. **이슈 생성**
   - 각 작업은 GitHub에서 새로운 **이슈**를 생성하여 시작됩니다.
   - 이슈는 기능 개발, 버그 수정 등 다양한 작업 항목을 나타냅니다.

2. **새 브랜치 생성**
   - 각 이슈에 대해 **이슈 번호**를 포함한 새 브랜치를 만듭니다.
   - 예: `feature/issue-123`, `bugfix/issue-456`
   - 이 브랜치에서 해당 이슈를 해결하기 위한 작업을 진행합니다.

3. **작업 완료 후 커밋**
   - 브랜치에서 기능 구현 또는 버그 수정을 완료한 후, 변경 사항을 커밋합니다.
   - 커밋 메시지는 변경 내용을 간결하게 설명합니다.

4. **원격 저장소에 푸시**
   - 커밋한 후 해당 브랜치를 원격 저장소에 푸시합니다.
   - `git push origin 브랜치명`

5. **Pull Request(PR) 생성**
   - 작업이 완료되면, 해당 브랜치에서 `main` 브랜치로 **Pull Request**를 생성합니다.
   - PR을 통해 팀원들이 코드를 리뷰하고, 최종적으로 `main` 브랜치에 병합됩니다.

## 이슈 기반 브랜치 전략의 장점

- **충돌 최소화**: 각 이슈별로 독립적인 브랜치를 사용하므로, 여러 사람이 동시에 작업할 때 충돌을 최소화할 수 있습니다.
- **명확한 작업 분리**: 각 브랜치가 하나의 이슈를 처리하므로, 작업 단위가 명확하게 구분됩니다.
- **효율적인 코드 리뷰**: PR을 통해 변경 사항을 리뷰하고 병합하므로, 코드 품질을 유지할 수 있습니다.
- **자동화된 배포**: `main` 브랜치에 병합된 후 자동화된 배포 파이프라인을 통해 배포가 진행될 수 있습니다.

## 결론

우리는 **이슈 기반 브랜치 전략**을 통해 작업을 효율적으로 분리하고, 충돌을 최소화하며, 코드 품질을 유지하는 방식으로 개발을 진행하였습니다. 이 방식은 GitHub Flow의 기본 개념을 채택한 것으로, 프로젝트의 관리와 협업을 보다 원활하게 만들어줍니다.


## ✔ 컨벤션

## 기본 설정
- **코드 스페이스**: 2칸으로 설정 (탭 대신 스페이스 사용)
- **구글 코드 스타일**을 따름

## 코드 스타일
- **줄 길이**: 100자 이하로 작성
- **모든 클래스는 한 파일에 하나의 클래스만 포함**
- **클래스 이름**: 첫 글자는 대문자로, 카멜케이스(CamelCase) 방식 사용
- **메서드 이름**: 첫 글자는 소문자로, 카멜케이스(CamelCase) 방식 사용
- **변수 이름**: 첫 글자는 소문자로, 카멜케이스(CamelCase) 방식 사용
- **상수 이름**: 모두 대문자, 단어는 `_`로 구분 (예: `MAX_SIZE`)
- **중괄호**: 항상 새로운 줄에 `{`를 배치
- **문서화**: 메서드 및 클래스에 대한 주석을 작성 (Javadoc 사용)
- **공백**: 연산자 주변에 공백을 추가 (예: `a + b`), `if`, `for`, `while` 괄호 앞에는 공백 없이 작성

## 커밋 컨벤션

### 브랜치 생성
- **이슈 중심**으로 브랜치를 생성합니다.
- 이슈 번호를 포함하여 브랜치를 만듭니다.
  - 예: `feat/board#1` (이슈 번호 `#1`을 포함하여 브랜치 생성)

### 커밋 타입
| 타입 이름   | 내용                                        |
|-------------|---------------------------------------------|
| `enh`       | 새로운 기능에 대한 커밋                    |
| `fix`       | 버그 수정에 대한 커밋                      |
| `build`     | 빌드 관련 파일 수정 / 모듈 설치 또는 삭제에 대한 커밋 |
| `chore`     | 그 외 자잘한 수정에 대한 커밋              |
| `ci`        | CI 관련 설정 수정에 대한 커밋              |
| `docs`      | 문서 수정에 대한 커밋                      |
| `style`     | 코드 스타일 혹은 포맷 등에 관한 커밋       |
| `refactor`  | 코드 리팩토링에 대한 커밋                  |
| `test`      | 테스트 코드 수정에 대한 커밋              |
| `perf`      | 성능 개선에 대한 커밋                      |

위 내용을 기준으로 커밋을 작성하고, 각 커밋의 타입을 적절히 선택하여 관리합니다.


## ⚙ 배포
(배포 방식 및 사용 기술을 설명하세요.)

## 🛠 개발 도구


![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![SpringBoot](https://camo.githubusercontent.com/c5c6f5ba41163a05ef0c9aa47053749f7b2da2edaa4df9002af8345adcf8a9f0/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f737072696e67626f6f742d3644423333463f7374796c653d666f722d7468652d6261646765266c6f676f3d737072696e67626f6f74266c6f676f436f6c6f723d7768697465)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![AWS](https://camo.githubusercontent.com/8e8ac5da5155525bed4d4102a1225ca01bc54b7df3ef0c2711ea6aa6de172d78/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f616d617a6f6e6177732d3233324633453f7374796c653d666f722d7468652d6261646765266c6f676f3d616d617a6f6e266c6f676f436f6c6f723d7768697465)
![Docker](https://img.shields.io/badge/docker-%230db7ed.svg?style=for-the-badge&logo=docker&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)
![GitHub](https://img.shields.io/badge/github-%23121011.svg?style=for-the-badge&logo=github&logoColor=white)
![Swagger](https://img.shields.io/badge/-Swagger-%23Clojure?style=for-the-badge&logo=swagger&logoColor=white)
![postman](https://camo.githubusercontent.com/56cef8df531519e6e51a365ec22f4fa3aa191984eb3bd1b6b5b248fb469bcf0b/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f706f73746d616e2d4646364333373f7374796c653d666f722d7468652d6261646765266c6f676f3d706f73746d616e266c6f676f436f6c6f723d7768697465)
![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)
![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)
![Gradle](https://img.shields.io/badge/Gradle-02303A.svg?style=for-the-badge&logo=Gradle&logoColor=white)
![amazonec2](https://camo.githubusercontent.com/8f7ba4c88a22f2f0274e67e2530c275bb48ea7a21b2aa300a820ddbbaffc46d8/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f616d617a6f6e6563322d4646393930303f7374796c653d666f722d7468652d6261646765266c6f676f3d616d617a6f6e656332266c6f676f436f6c6f723d7768697465)
![githubactions](https://camo.githubusercontent.com/bccbc0c91e2babcf083d1e2bbadb7baa4dc1324494346249a00392a1e20eb4e3/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f676974687562616374696f6e732d3230383846463f7374796c653d666f722d7468652d6261646765266c6f676f3d676974687562616374696f6e73266c6f676f436f6c6f723d7768697465)
![lombok](https://camo.githubusercontent.com/90664690f2b5f02dda8335c4b5a3d7a61720a800d4892ac4ff301807ea5839e0/68747470733a2f2f696d672e736869656c64732e696f2f62616467652f4c6f6d626f6b2d6361303132343f7374796c653d666f722d7468652d6261646765266c6f676f3d646174613a696d6167652f706e673b6261736536342c6956424f5277304b47676f414141414e53556845556741414145414141414241434159414141437161584865414141414358424957584d41414173544141414c457745416d7077594141414565456c45515652346e4f32615734685656526a48503031487a5a4a474a63707555366c303055717a676c347343727167447a35495a5a6a5a51786c45614b424a575641397045454733516b714553727441715768592b5744515a42704632797331444c7a30733373707155324e722f34324776523139655a6156707a4274615a733339776d4c50503376732f612b3231396e64625336536b704b536b704b536b3167454f413134482f6742756b586f446d4d48667643663142484155384c313541484f6c6e6741654e4a332f4175677639514977416a686f4873416b715365416c30336e56307450416a67447542743445396749744141586d2f4e6e41573268382f7233484f6b4a414b63456c7859375a316c6d726e76452f4c356365674c414a4741666c546b453347437533576e4f6651794d6c466f476d42773647576b466c6f5466547763476d577348564868417677446a70525942546e4d6a2f326c48377a5177744a315a7368633455326f4e594958722f4f424f684c37376a5148556a6b632b4148704c7251434d63752f35325a32383730567a333362674e334e387464514b7750326d3455762f7833306e416476436661384339786964743652576f4168664978506475614f426463416d6638376b415a634268775048474e657073364642636763595a7a722f4d3944506e5a2f6d6a4e7a7a51464d48656c2b6161302b55334145576d415976726e422b6d4f74556449383635613848526f645a6f6c356b69764d6b78306e4f414c32417265314e66335064494f437064694c44396d6a4e506973454c75316f2b6c65342f6f4977386e3932346746384b4c6b447244514e66694d594c763362397a2f7561774a7544706e67326d42453156442b615052756c4272772f5732687354716933356e476a30725130396b522b516b594b446b444c44494e667439382f77726f6b364333324767736c4a79686d4d4b32696d4f742f4b77455066554342384c394f717447534d34414c356b4f662b594d345a454a65764f4d5272506b444444654e4c624e6a663738424c32427747366a4d55467968534b442b386730647233352f71754773676d617334314753395a5a494958726976774f374448486479546f6156486b57364d78575849464f44574d637552646c386f4f534e43636154512b7958623067623468594c47757a6b5a7a3179566f39676432475931724a4665412b31794d72694d6557613035515263742f3261314c354b783154396b4776754f4d33784e435a724458416e734b736b52594c687a555330756f30754b313445586a4d62624b544f6f327745476879704f5a49387a6773324a55332b693064435a4e555a79412b67487244454e33653865686872426f516d366a5734783543484a44614350466a5a4e4933584b4c33502b6632786938555272415a48507451346f4f514530414b2f77542b34456e6a504830784b3162334e54503638564941712f764e78312f6f6c77376e6a674d5742366f7659567758314737704b636f45684956726e4f50316d4e7372532b4c73376c4e576356385647457542746335316546616271784b2b2b7072673236537048714e556f7555437849324952476d512b385a6f37505464512b442f6a42364879744b304753417851572b58595834576d4635365a772f7679513969354b4356474271634662524c354a71524e323536616b4e5737556458517572494b32707265504f753174575a53344b507a37624463794d62592f746772366c7742626e4c59575445366f54672b3662756a5775636131687665396f51726c38615556566e32657a6d4a6c68364c69616e64687867304959377567325674336534574644622f4b6f2f2f7257736b46696d31714e7161666d316933312f6637497541425941662f52682f454d38415179516e675764504965304e43596a39486d4443344d645438645550546c634374774d4d685a5931312b306f6458354b4e6c666341632b676531484d733149636c4f514d4d63535773564e7243586a376433486835746957735367416e6835305a4f384d4b725033456a556b4877764857454c4c7139746248517859334953582f4c796b704b536b704b5a48753543382b45545264752b3544364141414141424a52553545726b4a6767673d3d266c6f676f436f6c6f723d7768697465)
![dbeaver](https://img.shields.io/badge/dbeaver-382923?style=for-the-badge&logo=dbeaver&logoColor=white)
![VSCode](https://img.shields.io/badge/VSCode-0078D4?style=for-the-badge&logo=visual%20studio%20code&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![Notion](https://img.shields.io/badge/Notion-%23000000.svg?style=for-the-badge&logo=notion&logoColor=white)
### 백엔드 개발 기술 스택

| **카테고리**        | **기술 스택**        | **설명**                                           |
|---------------------|----------------------|---------------------------------------------------|
| **프로그래밍 언어** | Java 17              | 안정성 및 최신 기능을 제공하는 Java 버전 17 사용. |
| **프레임워크**      | Spring Boot          | 빠르고 효율적인 Spring 기반 애플리케이션 개발을 위한 프레임워크. |
| **데이터베이스**    | MySQL                | 관계형 데이터베이스 관리 시스템 (RDBMS).          |
| **서버**            | AWS                  | 클라우드 환경에서 애플리케이션을 배포하고 관리하기 위한 플랫폼. |
| **컨테이너화**      | Docker               | 애플리케이션을 컨테이너화하여 이식성과 확장성을 제공하는 도구. |
| **버전 관리**       | GitHub               | 소스 코드 버전 관리 및 협업을 위한 GitHub 사용. |
| **파일 업로드**     | Spring Multipart     | 파일 업로드 기능 활성화를 위한 Spring의 `multipart` 설정 사용. |
| **API 문서화**      | Springdoc & Swagger  | OpenAPI를 기반으로 Swagger UI를 통한 API 문서화. |
| **JSON Web Token**  | JWT                  | 사용자 인증 및 권한 관리를 위한 JSON Web Token 기반 인증 처리. |
| **AWS S3**          | AWS SDK              | AWS S3와 연동하여 이미지 및 파일 관리.           |
| **보안**            | Spring Security      | Spring 기반 보안 처리 (JWT 인증 및 권한 관리).    |
| **ORM**             | Hibernate, JPA       | 데이터베이스와 객체를 매핑하기 위한 Hibernate 및 JPA 사용. |

### 주요 설정 내용

- **파일 업로드**  
  - 최대 파일 크기 및 요청 크기: 10MB  
  - 멀티파트 처리 활성화 (`spring.servlet.multipart.enabled: true`)

- **AWS S3**  
  - 이미지 저장소를 위한 S3 연동  
  - AWS 액세스 키 및 비밀 키, 리전, 버킷 이름 설정

- **JPA 및 데이터베이스 설정**  
  - `hibernate.ddl-auto: update` - 자동 데이터베이스 스키마 업데이트  
  - `hibernate.dialect: org.hibernate.dialect.MySQL8Dialect` - MySQL 8 버전 사용  
  - `show-sql: true` - SQL 쿼리 로그 출력

- **JWT 설정**  
  - `expiration_time: 86400000` - JWT 만료 시간 설정 (24시간)  
  - `secret.key` - JWT 암호화 키 설정

- **서버 설정**  
  - 서버 포트: 8080

- **Swagger 설정**  
  - Swagger UI 및 OpenAPI 문서화 관련 설정 (`springdoc.override-with-generic-response: false`)



### 📦 build.gradle (Back-end)

| **라이브러리**                                               | **용도**                                      |
|------------------------------------------------------------|---------------------------------------------|
| `org.springframework.boot:spring-boot-starter-web`           | Spring Boot 웹 애플리케이션 시작을 위한 기본 라이브러리 |
| `org.springframework.boot:spring-boot-starter-data-jpa`      | Spring Data JPA를 위한 스타터 라이브러리      |
| `org.springframework.boot:spring-boot-starter-test`         | Spring Boot 테스트를 위한 스타터 라이브러리  |
| `org.springframework.boot:spring-boot-starter-validation`   | Spring Validation을 위한 스타터 라이브러리   |
| `org.springframework.boot:spring-boot-starter-security`     | Spring Security 관련 기능을 위한 스타터 라이브러리 |
| `org.springframework.boot:spring-boot-starter-json`         | JSON 처리 기능을 위한 Spring Boot 스타터 라이브러리 |
| `org.springframework.security:spring-security-core`         | Spring Security 핵심 기능을 위한 라이브러리 |
| `org.springframework.boot:spring-boot-starter-webmvc-ui`    | OpenAPI 문서화 지원을 위한 라이브러리 |
| `org.mybatis.spring.boot:mybatis-spring-boot-starter`      | MyBatis와 Spring Boot 통합을 위한 스타터 라이브러리 |
| `org.mybatis.spring.boot:mybatis-spring-boot-starter-test` | MyBatis 테스트를 위한 스타터 라이브러리 |
| `com.mysql:mysql-connector-j`                               | MySQL 데이터베이스와의 연결을 위한 JDBC 드라이버 |
| `com.amazonaws:aws-java-sdk-s3`                             | AWS S3 서비스와의 연동을 위한 SDK 라이브러리 |
| `io.jsonwebtoken:jjwt-api`                                   | JWT 생성 및 파싱을 위한 API 라이브러리       |
| `io.jsonwebtoken:jjwt-impl`                                  | JWT 구현을 위한 라이브러리                  |
| `io.jsonwebtoken:jjwt-jackson`                               | JWT와 Jackson 통합을 위한 라이브러리        |
| `org.projectlombok:lombok`                                  | 코드 간소화를 위한 Lombok 라이브러리 (Getter, Setter 등) |
| `org.junit.platform:junit-platform-launcher`                | JUnit 테스트 실행을 위한 런처 라이브러리     |
| `com.fasterxml.jackson.core:jackson-databind`               | JSON 직렬화 및 역직렬화를 위한 라이브러리   |





## 📆 프로젝트 일정
(프로젝트 진행 일정 및 마일스톤을 작성하세요.)

## 📄 API 명세서 및 ERD 설계도
# API 명세서

## API 명세서

### 1. 게시판 API

| **기능**            | **게시판 생성**                      |
|---------------------|--------------------------------------|
| **API 이름**        | createBoard                         |
| **API 주소**        | `/boards`                           |
| **CRUD**            | Create                               |
| **Method**          | POST                                 |
| **요청 예시**       | ```json { "title": "게시판 제목", "description": "게시판 설명" } ``` |
| **응답 예시**       | ```json { "status": "CREATE", "message": "게시판 생성이 완료되었습니다.", "data": null } ``` |

| **기능**            | **게시판 상세 조회**                  |
|---------------------|--------------------------------------|
| **API 이름**        | getBoard                             |
| **API 주소**        | `/boards/{boardId}`                  |
| **CRUD**            | Read                                 |
| **Method**          | GET                                  |
| **응답 예시**       | ```json { "status": "SUCCESS", "message": null, "data": { "title": "게시판 제목", "description": "게시판 설명" } } ``` |

| **기능**            | **게시판 목록 조회**                  |
|---------------------|--------------------------------------|
| **API 이름**        | getBoardList                         |
| **API 주소**        | `/boards`                           |
| **CRUD**            | Read                                 |
| **Method**          | GET                                  |
| **요청 예시**       | ```json { "pageNo": 0, "pageSize": 10, "sortBy": "createdAt", "direction": "DESC" } ``` |
| **응답 예시**       | ```json { "status": "SUCCESS", "message": null, "data": [ { "boardId": 1, "title": "게시판 1", "description": "설명 1" }, { "boardId": 2, "title": "게시판 2", "description": "설명 2" } ] } ``` |

| **기능**            | **게시판 삭제**                      |
|---------------------|--------------------------------------|
| **API 이름**        | deleteBoard                          |
| **API 주소**        | `/boards/{boardId}`                  |
| **CRUD**            | Delete                               |
| **Method**          | DELETE                               |
| **응답 예시**       | ```json { "status": "DELETE", "message": "게시판이 삭제되었습니다.", "data": null } ``` |

| **기능**            | **게시판 수정**                      |
|---------------------|--------------------------------------|
| **API 이름**        | updateBoard                          |
| **API 주소**        | `/boards/{boardId}`                  |
| **CRUD**            | Update                               |
| **Method**          | PUT                                  |
| **요청 예시**       | ```json { "title": "새 제목", "description": "새 설명" } ``` |
| **응답 예시**       | ```json { "status": "UPDATE", "message": "게시판 수정이 완료되었습니다.", "data": null } ``` |

---

### 2. 댓글 API

| **기능**            | **댓글 생성**                        |
|---------------------|--------------------------------------|
| **API 이름**        | createComment                        |
| **API 주소**        | `/posts/{postId}/comments`           |
| **CRUD**            | Create                               |
| **Method**          | POST                                 |
| **요청 예시**       | ```json { "content": "댓글 내용" } ``` |
| **응답 예시**       | ```json { "status": "CREATE", "message": "댓글 생성이 완료되었습니다.", "data": { "commentId": 1, "content": "댓글 내용" } } ``` |

| **기능**            | **댓글 전체 목록 조회**              |
|---------------------|--------------------------------------|
| **API 이름**        | getCommentAll                        |
| **API 주소**        | `/comments`                          |
| **CRUD**            | Read                                 |
| **Method**          | GET                                  |
| **요청 예시**       | ```json { "pageNo": 0, "pageSize": 10, "sortBy": "createdAt", "direction": "DESC" } ``` |
| **응답 예시**       | ```json { "status": "SUCCESS", "message": null, "data": [ { "commentId": 1, "content": "댓글 1" }, { "commentId": 2, "content": "댓글 2" } ] } ``` |

| **기능**            | **특정 게시글 댓글 조회**            |
|---------------------|--------------------------------------|
| **API 이름**        | getCommentByPost                     |
| **API 주소**        | `/posts/{postId}/comments`           |
| **CRUD**            | Read                                 |
| **Method**          | GET                                  |
| **응답 예시**       | ```json { "status": "SUCCESS", "message": null, "data": [ { "commentId": 1, "content": "댓글 1" }, { "commentId": 2, "content": "댓글 2" } ] } ``` |

| **기능**            | **댓글 삭제**                        |
|---------------------|--------------------------------------|
| **API 이름**        | deleteComment                        |
| **API 주소**        | `/posts/{postId}/comments/{commentId}`|
| **CRUD**            | Delete                               |
| **Method**          | DELETE                               |
| **응답 예시**       | ```json { "status": "DELETE", "message": "댓글이 삭제되었습니다.", "data": null } ``` |

| **기능**            | **댓글 수정**                        |
|---------------------|--------------------------------------|
| **API 이름**        | updateComment                        |
| **API 주소**        | `/posts/{postId}/comments/{commentId}`|
| **CRUD**            | Update                               |
| **Method**          | PUT                                  |
| **요청 예시**       | ```json { "content": "수정된 댓글 내용" } ``` |
| **응답 예시**       | ```json { "status": "UPDATE", "message": "댓글 수정이 완료되었습니다.", "data": null } ``` |

---

### 3. Info API

| **기능**            | **지역별 동물 병원 정보 목록 조회**   |
|---------------------|--------------------------------------|
| **API 이름**        | getHospitalInfo                      |
| **API 주소**        | `/info/hospitals/{addNum}`           |
| **CRUD**            | Read                                 |
| **Method**          | GET                                  |
| **요청 예시**       | ```json { "pageNo": 0, "pageSize": 10, "sortBy": "name", "direction": "DESC" } ``` |
| **응답 예시**       | ```json { "status": "SUCCESS", "message": null, "data": [ { "hospitalName": "병원1", "location": "서울" }, { "hospitalName": "병원2", "location": "부산" } ] } ``` |

---

### 4. Post API

| **기능**            | **게시글 생성**                       |
|---------------------|---------------------------------------|
| **API 이름**        | createPost                           |
| **API 주소**        | `/posts`                             |
| **CRUD**            | Create                                |
| **Method**          | POST                                  |
| **요청 예시**       | ```json { "title": "게시글 제목", "content": "게시글 내용" } ``` |
| **응답 예시**       | ```json { "status": "CREATE", "message": "게시글 생성이 완료되었습니다.", "data": { "postId": 1 } } ``` |

| **기능**            | **게시글 이미지 업로드**             |
|---------------------|--------------------------------------|
| **API 이름**        | uploadImages                         |
| **API 주소**        | `/posts/{postId}`                    |
| **CRUD**            | Create                                |
| **Method**          | POST (파일 업로드)                   |
| **응답 예시**       | ```json { "status": "CREATE", "message": "게시글과 이미지가 성공적으로 업로드되었습니다.", "data": null } ``` |

| **기능**            | **게시글 수정**                      |
|---------------------|--------------------------------------|
| **API 이름**        | updatePost                           |
| **API 주소**        | `/posts/{postId}`                    |
| **CRUD**            | Update                               |
| **Method**          | PUT                                  |
| **요청 예시**       | ```json { "title": "수정된 제목", "content": "수정된 내용" } ``` |
| **응답 예시**       | ```json { "status": "UPDATE", "message": "게시글 수정이 완료되었습니다.", "data": null } ``` |

| **기능**            | **게시글 삭제**                      |
|---------------------|--------------------------------------|
| **API 이름**        | deletePost                           |
| **API 주소**        | `/posts/{postId}`                    |
| **CRUD**            | Delete                               |
| **Method**          | DELETE                               |
| **응답 예시**       | ```json { "status": "DELETE", "message": "게시글이 삭제되었습니다.", "data": null } ``` |

---

## 📄 응답구조

### 성공

- **조회 성공**: `success`
- **생성 성공**: `create`
- **수정 성공**: `update`
- **삭제 성공**: `delete`

### 실패

- **요청 데이터가 잘못된 경우**: `invalid`
  
  예시:
  
  - 사용자가 이메일 필드에 이메일 형식이 아닌 값을 입력했을 때.
  - 숫자만 입력되어야 하는 필드에 문자가 포함된 경우.
  - 필수 입력값이 누락된 경우.
  - 로그인 요청에서 비밀번호를 누락했을 때.
  - 비어있는 필드를 보내는 요청.

- **이미 존재하는 데이터로 새롭게 생성하려는 경우**: `duplicate`
  
  예시:
  
  - 동일한 이메일로 회원가입을 시도했을 때.
  - 이미 존재하는 사용자명으로 새 계정을 생성하려는 경우.

- **요청한 리소스가 존재하지 않는 경우**: `notFound`
  
  예시:
  
  - 존재하지 않는 게시글 ID를 조회하려 할 때.
  - 삭제된 리소스를 조회하려 할 때.

- **인증되지 않은 사용자가 접근하려는 경우**: `unauthorized`
  
  예시:
  
  - 로그인하지 않은 사용자가 게시글을 작성하려고 시도하는 경우.
  - 로그인하지 않은 사용자가 프로필을 수정하려고 할 때.
  - 인증이 필요한 API를 인증 토큰 없이 접근할 때.

- **인증은 되었지만 권한이 없는 경우**: `forbidden`
  
  예시:
  
  - 일반 사용자가 관리자 전용 페이지에 접근하려 할 때.
  - 특정 리소스에 대한 읽기/쓰기 권한이 없는 사용자.
  - 관리자가 아닌 사용자가 다른 사용자의 개인정보를 조회하려 할 때.

### 에러

- **예상치 못한 예외가 발생한 경우**: `error`

---

### 예시

#### 게시판 생성

- 생성 성공: `create`

```json
{
  "status": "CREATE",
  "message": "게시판 생성이 완료되었습니다.",
  "data": null
}
```

* 이미 존재하는 데이터로 새롭게 생성하려는 경우: duplicate
```
{
  "status": "DUPLICATE",
  "message": "이미 존재하는 게시판입니다.",
  "data": null
}
```


## 📋 메뉴 구조도
(서비스의 메뉴 및 네비게이션 구조를 설명하세요.)

## 🖥 화면 구현
(화면 UI/UX 관련 설명 및 스크린샷을 첨부하세요.)

## 🙋‍♂️

