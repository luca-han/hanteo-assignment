# 한터글로벌 과제 문제 1번

1. 자료구조
- Category : 게시판의 카테고리
- Board : 게시판 메타데이터
- CategoryRelation : 카테고리의 상하관계 설정

2. 실행
- springboot start시 아래 그림과 같은 카테고리가 입력됨(DemoApplication.java - applicationRunner)


https://drive.google.com/file/d/1yLXVttdX3OnBb0mPoL5J5VNVt2wPXXVm/view?usp=sharing

3. 테스트

- /category
  - 카테고리 이름 및 id로 카테고리 검색 (query param : name, id)
  - name, id 모두 Null 일 경우 모든 카테고리를 트리 구조로 반환
